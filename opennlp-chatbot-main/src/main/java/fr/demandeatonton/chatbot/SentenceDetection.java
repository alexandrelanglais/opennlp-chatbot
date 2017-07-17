package fr.demandeatonton.chatbot;

public class SentenceDetection {
   public static void main(String args[]) {

      String sentence = " Hi. How are you? Welcome to Tutorialspoint. "
            + "We provide free tutorials on various technologies";

      String simple = "[.?!]";
      String[] splitString = (sentence.split(simple));
      for (String string : splitString)
         System.out.println(string);
   }
}