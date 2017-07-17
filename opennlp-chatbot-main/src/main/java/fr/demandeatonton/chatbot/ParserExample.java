package fr.demandeatonton.chatbot;

import java.io.BufferedInputStream;
import java.io.InputStream;

import opennlp.tools.cmdline.parser.ParserTool;
import opennlp.tools.parser.Parse;
import opennlp.tools.parser.Parser;
import opennlp.tools.parser.ParserFactory;
import opennlp.tools.parser.ParserModel;

public class ParserExample {

   public static void main(String args[]) throws Exception {
      // Loading parser model
      InputStream inputStream = new BufferedInputStream(
            SentenceDetectionME.class.getResourceAsStream("/models/en-parser-chunking.bin"));
      ParserModel model = new ParserModel(inputStream);

      // Creating a parser
      Parser parser = ParserFactory.create(model);

      // Parsing the sentence
      String sentence = "Birds are flying in the sky.";
      Parse topParses[] = ParserTool.parseLine(sentence, parser, 1);

      for (Parse p : topParses)
         p.show();
   }
}