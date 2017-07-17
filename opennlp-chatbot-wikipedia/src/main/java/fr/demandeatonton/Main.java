package fr.demandeatonton;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.wikipedia.Wiki;

public class Main {

   public static void main(String[] args) throws IOException {
      Wiki wiki = new Wiki();

      // Page au hasard
      String random = wiki.random();

      // données JSON
      String export = wiki.export(random);
      // System.out.println(export);

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

   private static String[] getNouns(String pageText) {
      List<String> nouns = new ArrayList<>();
      Pattern patt = null;
      Matcher m = null;
      // Pattern patt = Pattern.compile("'''''(.+?)'''''");
      // Matcher m = patt.matcher(pageText);
      // while (m.find()) {
      // String text = m.group(1);
      // nouns.add(text);
      // }
      //
      patt = Pattern.compile("'''(.+?)'''");
      m = patt.matcher(pageText);
      while (m.find()) {
         String text = m.group(1);
         nouns.add(text);
      }
      return nouns.toArray(new String[nouns.size()]);
   }

}
