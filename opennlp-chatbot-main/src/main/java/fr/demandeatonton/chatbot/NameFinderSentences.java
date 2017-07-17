package fr.demandeatonton.chatbot;

import java.io.BufferedInputStream;
import java.io.InputStream;

import opennlp.tools.namefind.NameFinderME;
import opennlp.tools.namefind.TokenNameFinderModel;
import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;
import opennlp.tools.util.Span;

public class NameFinderSentences {
   public static void main(String args[]) throws Exception {

      // Loading the tokenizer model
      InputStream inputStreamTokenizer = new BufferedInputStream(
            SentenceDetectionME.class.getResourceAsStream("/models/en-token.bin"));
      TokenizerModel tokenModel = new TokenizerModel(inputStreamTokenizer);

      // Instantiating the TokenizerME class
      TokenizerME tokenizer = new TokenizerME(tokenModel);

      // Tokenizing the sentence in to a string array
      String sentence = "Mike is senior programming manager and William, Mark, Malcolm is a clerk both are working at Tutorialspoint";
      String tokens[] = tokenizer.tokenize(sentence);

      // Loading the NER-person model
      InputStream inputStreamNameFinder = new BufferedInputStream(
            SentenceDetectionME.class.getResourceAsStream("/models/en-ner-person.bin"));
      TokenNameFinderModel model = new TokenNameFinderModel(inputStreamNameFinder);

      // Instantiating the NameFinderME class
      NameFinderME nameFinder = new NameFinderME(model);

      // Finding the names in the sentence
      Span nameSpans[] = nameFinder.find(tokens);

      // Printing the names and their spans in a sentence
      for (Span s : nameSpans)
         System.out.println(s.toString() + "  " + tokens[s.getStart()]);
   }
}