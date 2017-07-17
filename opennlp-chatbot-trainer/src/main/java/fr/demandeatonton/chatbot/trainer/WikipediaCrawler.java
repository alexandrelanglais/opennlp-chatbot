package fr.demandeatonton.chatbot.trainer;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.wikipedia.Wiki;

import fr.demandeatonton.chatbot.trainer.domain.pojos.WikiPage;
import fr.demandeatonton.chatbot.trainer.utils.JsoupUtils;

@SpringBootApplication
public class WikipediaCrawler implements CommandLineRunner {
   private static final int RELEVENT_SENTENCE_LENGTH = 100;

   private static final int SENTENCES_GOAL = 400;

   private Logger logger = Logger.getLogger(WikipediaCrawler.class.getName());

   @Autowired
   private Wiki wiki;

   @Autowired
   private DatabaseFeeder feeder;

   public static void main(String[] args) throws IOException {
      SpringApplication.run(WikipediaCrawler.class, args);
   }

   @Override
   public void run(String... arg0) throws Exception {
      int sentences = 0;
      StringBuilder finalText = new StringBuilder();
      do {
         String random = wiki.random();

         // Get wiki page
         WikiPage wikiPage = feeder.getPageText(random);
         String pageText = wikiPage.getRawContent();
         String htmlText = wikiPage.getHtmlContent();
         logger.info(pageText);

         // Fetch names from page
         Set<String> nouns = getNouns(pageText);
         // Format names in wikipedia text for NER
         String printText = JsoupUtils.br2nl(htmlText);

         // Keep only long enough sentences
         String trainText = extractRelevantSentences(printText);
         for (String noun : nouns) {
            trainText = tagNer(trainText, noun);
         }

         sentences += countSentences(trainText);
         // Clean remaining characters
         finalText.append(clean(trainText));

         logger.info("Got " + sentences + " sentences so far...");
      } while (sentences < SENTENCES_GOAL);
      // Write to fr-ner-person.train
      writeToFile(finalText.toString());
   }

   private int countSentences(String trainText) {
      return trainText.split("\n").length;
   }

   private String clean(String trainText) {
      String s = trainText.replaceAll("&nbsp;", "");
      s = s.replaceAll("\\[(.+?)\\]", "");
      return s;
   }

   private String extractRelevantSentences(String printText) {
      StringBuilder sb = new StringBuilder();
      String[] split = printText.split("\n");
      for (String sentence : split) {
         if (sentence != null && sentence.length() > RELEVENT_SENTENCE_LENGTH) {
            sb.append(sentence).append("\n");
         }
      }
      return sb.toString();
   }

   private void writeToFile(String printText) throws IOException {
      String filePath = "result/fr-ner-person.train";
      Writer writer = new BufferedWriter(
            new OutputStreamWriter(new FileOutputStream(filePath), StandardCharsets.UTF_8));
      writer.write(printText);
      writer.close();
   }

   private String tagNer(String printText, String noun) {
      return printText.replaceAll(noun, "<START:person> " + noun + " <END>");
   }

   private Set<String> getNouns(String pageText) throws IOException {
      Set<String> nouns = new HashSet<>();
      Pattern patt = null;
      Matcher m = null;

      patt = Pattern.compile("\\[\\[(.+?)\\]\\]");
      m = patt.matcher(pageText);
      while (m.find()) {
         String text = m.group(1);

         if (text.indexOf('|') < 0) {
            if (wikiPageContainsBirthDate(text)) {
               nouns.add(text);
            }
         }
      }
      return nouns;
   }

   private boolean wikiPageContainsBirthDate(String title) throws IOException {
      WikiPage wikiPage = feeder.getPageText(title);
      String pageText = wikiPage.getRawContent();
      if (pageText != null && pageText.length() > 0) {
         return (pageText.toLowerCase().indexOf("date de naissance") >= 0);
      } else {
         return false;
      }
   }

}
