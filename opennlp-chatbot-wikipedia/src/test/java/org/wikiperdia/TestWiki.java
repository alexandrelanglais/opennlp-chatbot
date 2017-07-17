package org.wikiperdia;

import java.io.IOException;

import org.junit.Test;
import org.wikipedia.Wiki;

public class TestWiki {

   private Wiki wiki = new Wiki();

   @Test
   public void testWikiCategories() throws IOException {
      String page = "Jean_Moulin";
      String[] categories = wiki.getCategories(page);
      for (String category : categories) {
         System.out.println(category);
      }

   }

   @Test
   public void testRenderedText() throws IOException {
      String page = "Jean_Moulin";
      String renderedText = wiki.getRenderedText(page);
      System.out.println(renderedText);

   }

}
