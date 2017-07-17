package fr.demandeatonton;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.wikipedia.Wiki;

public class Main {
   private static Wiki wiki = new Wiki();

   public static void main(String[] args) throws IOException {

      // Page au hasard
      String random = wiki.random();
      random = "Jean_Moulin";

      // données JSON
      String export = wiki.export(random);
      System.out.println(export);

      // Liens de la page
      String[] linksOnPage = wiki.getLinksOnPage(random);
      for (String link : linksOnPage) {
         System.out.println("Lien: " + link);
      }

      String pageText = wiki.getPageText(random);
      System.out.println(pageText);

      String[] nouns = getNouns(pageText);
      System.out.println("Noms");
      for (String noun : nouns) {
         System.out.println("Nom: " + noun);
      }

   }

   private static String[] getNouns(String pageText) throws IOException {
      List<String> nouns = new ArrayList<>();
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
      return nouns.toArray(new String[nouns.size()]);
   }

   private static boolean wikiPageContainsBirthDate(String text) throws IOException {
      String pageText = wiki.getPageText(text);
      if (pageText != null && pageText.length() > 0) {
         return (pageText.toLowerCase().indexOf("date de naissance") >= 0);
      } else {
         return false;
      }
   }

}
