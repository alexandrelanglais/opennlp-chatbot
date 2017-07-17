package fr.demandeatonton.chatbot;

import java.io.BufferedInputStream;
import java.io.InputStream;

import opennlp.tools.postag.POSModel;
import opennlp.tools.postag.POSSample;
import opennlp.tools.postag.POSTaggerME;
import opennlp.tools.tokenize.WhitespaceTokenizer;

public class PosTaggerExample {

   public static void main(String args[]) throws Exception {

      // Loading Parts of speech-maxent model
      InputStream inputStream = new BufferedInputStream(
            SentenceDetectionME.class.getResourceAsStream("/models/en-pos-maxent.bin"));
      POSModel model = new POSModel(inputStream);

      // Instantiating POSTaggerME class
      POSTaggerME tagger = new POSTaggerME(model);

      String sentence = "Hi welcome to Tutorialspoint";

      // Tokenizing the sentence using WhitespaceTokenizer class
      WhitespaceTokenizer whitespaceTokenizer = WhitespaceTokenizer.INSTANCE;
      String[] tokens = whitespaceTokenizer.tokenize(sentence);

      // Generating tags
      String[] tags = tagger.tag(tokens);

      // Instantiating the POSSample class
      POSSample sample = new POSSample(tokens, tags);
      System.out.println(sample.toString());

   }
}