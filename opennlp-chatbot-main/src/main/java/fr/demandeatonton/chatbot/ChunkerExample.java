package fr.demandeatonton.chatbot;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import opennlp.tools.chunker.ChunkerME;
import opennlp.tools.chunker.ChunkerModel;
import opennlp.tools.cmdline.postag.POSModelLoader;
import opennlp.tools.postag.POSModel;
import opennlp.tools.postag.POSTaggerME;
import opennlp.tools.tokenize.WhitespaceTokenizer;

public class ChunkerExample {

   public static void main(String args[]) throws IOException {
      // Tokenizing the sentence
      String sentence = "Hi welcome to Tutorialspoint";
      WhitespaceTokenizer whitespaceTokenizer = WhitespaceTokenizer.INSTANCE;
      String[] tokens = whitespaceTokenizer.tokenize(sentence);

      // Generating the POS tags
      // Load the parts of speech model
      File file = new File("C:/OpenNLP_models/en-pos-maxent.bin");
      POSModel model = new POSModelLoader().load(file);

      // Constructing the tagger
      POSTaggerME tagger = new POSTaggerME(model);

      // Generating tags from the tokens
      String[] tags = tagger.tag(tokens);

      // Loading the chunker model
      InputStream inputStream = new BufferedInputStream(
            SentenceDetectionME.class.getResourceAsStream("/models/en-chunker.bin"));
      ChunkerModel chunkerModel = new ChunkerModel(inputStream);

      // Instantiate the ChunkerME class
      ChunkerME chunkerME = new ChunkerME(chunkerModel);

      // Generating the chunks
      String result[] = chunkerME.chunk(tokens, tags);

      for (String s : result)
         System.out.println(s);
   }
}