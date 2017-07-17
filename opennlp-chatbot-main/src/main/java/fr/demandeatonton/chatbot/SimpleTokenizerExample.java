package fr.demandeatonton.chatbot;

import opennlp.tools.tokenize.SimpleTokenizer;

public class SimpleTokenizerExample {
   public static void main(String args[]) {

      String sentence = "Hi. How are you? Welcome to Tutorialspoint. "
            + "We provide free tutorials on various technologies";

      // Instantiating SimpleTokenizer class
      SimpleTokenizer simpleTokenizer = SimpleTokenizer.INSTANCE;

      // Tokenizing the given sentence
      String tokens[] = simpleTokenizer.tokenize(sentence);

      // Printing the tokens
      for (String token : tokens) {
         System.out.println(token);
      }
   }
}