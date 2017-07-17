package fr.demandeatonton.chatbot;

import java.io.BufferedInputStream;
import java.io.InputStream;

import opennlp.tools.sentdetect.SentenceDetectorME;
import opennlp.tools.sentdetect.SentenceModel;
import opennlp.tools.util.Span;

public class SentencesAndPosDetection {

   public static void main(String args[]) throws Exception {

      String sen = "Hi. How are you? Welcome to Tutorialspoint."
            + " We provide free tutorials on various technologies";
      // Loading a sentence model
      InputStream inputStream = new BufferedInputStream(
            SentenceDetectionME.class.getResourceAsStream("/models/en-sent.bin"));
      SentenceModel model = new SentenceModel(inputStream);

      // Instantiating the SentenceDetectorME class
      SentenceDetectorME detector = new SentenceDetectorME(model);

      // Detecting the position of the sentences in the paragraph
      Span[] spans = detector.sentPosDetect(sen);

      // Printing the sentences and their spans of a paragraph
      for (Span span : spans)
         System.out.println(sen.substring(span.getStart(), span.getEnd()) + " " + span);
   }
}