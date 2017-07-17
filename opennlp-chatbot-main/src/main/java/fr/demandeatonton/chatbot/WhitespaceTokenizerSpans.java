package fr.demandeatonton.chatbot;

import opennlp.tools.tokenize.WhitespaceTokenizer;
import opennlp.tools.util.Span;

public class WhitespaceTokenizerSpans {
   public static void main(String args[]) {

      String sent = "Hi. How are you? Welcome to Tutorialspoint. "
            + "We provide free tutorials on various technologies";

      // Instantiating SimpleTokenizer class
      WhitespaceTokenizer whitespaceTokenizer = WhitespaceTokenizer.INSTANCE;

      // Retrieving the tokens
      Span[] tokens = whitespaceTokenizer.tokenizePos(sent);

      // Printing the spans of tokens
      for (Span token : tokens)
         System.out.println(token + sent.substring(token.getStart(), token.getEnd()));
   }
}