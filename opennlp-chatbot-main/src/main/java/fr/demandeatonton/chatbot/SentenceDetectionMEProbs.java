package fr.demandeatonton.chatbot;

import java.io.BufferedInputStream;
import java.io.InputStream;

import opennlp.tools.sentdetect.SentenceDetectorME;
import opennlp.tools.sentdetect.SentenceModel;

public class SentenceDetectionMEProbs {

   public static void main(String args[]) throws Exception {

      String sentence = "Hi. How are you? Welcome to Tutorialspoint. "
            + "We provide free tutorials on various technologies";

      // Loading sentence detector model
      InputStream inputStream = new BufferedInputStream(
            SentenceDetectionME.class.getResourceAsStream("/models/en-sent.bin"));
      SentenceModel model = new SentenceModel(inputStream);

      // Instantiating the SentenceDetectorME class
      SentenceDetectorME detector = new SentenceDetectorME(model);

      // Detecting the sentence
      String sentences[] = detector.sentDetect(sentence);

      // Printing the sentences
      for (String sent : sentences)
         System.out.println(sent);

      // Getting the probabilities of the last decoded sequence
      double[] probs = detector.getSentenceProbabilities();

      System.out.println("  ");

      for (int i = 0; i < probs.length; i++)
         System.out.println(probs[i]);
   }
}