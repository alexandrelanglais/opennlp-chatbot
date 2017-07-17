package fr.demandeatonton.chatbot;

import java.io.BufferedInputStream;
import java.io.InputStream;

import opennlp.tools.sentdetect.SentenceDetectorME;
import opennlp.tools.sentdetect.SentenceModel;
import opennlp.tools.util.Span;

public class SentencePosDetection {

   public static void main(String args[]) throws Exception {

      String paragraph = "Hi. How are you? Welcome to Tutorialspoint. "
            + "We provide free tutorials on various technologies";

      // Loading sentence detector model
      InputStream inputStream = new BufferedInputStream(
            SentenceDetectionME.class.getResourceAsStream("/models/en-sent.bin"));
      SentenceModel model = new SentenceModel(inputStream);

      // Instantiating the SentenceDetectorME class
      SentenceDetectorME detector = new SentenceDetectorME(model);

      // Detecting the position of the sentences in the raw text
      Span spans[] = detector.sentPosDetect(paragraph);

      // Printing the spans of the sentences in the paragraph
      for (Span span : spans)
         System.out.println(span);
   }
}