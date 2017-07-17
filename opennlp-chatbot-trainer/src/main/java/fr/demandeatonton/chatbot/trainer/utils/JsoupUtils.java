package fr.demandeatonton.chatbot.trainer.utils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.safety.Whitelist;

public class JsoupUtils {
   public static String br2nl(String html) {
      if (html == null)
         return html;
      Document document = Jsoup.parse(html);
      document.outputSettings(new Document.OutputSettings().prettyPrint(false));
      document.select("br").append("\\n");
      document.select("p").prepend("\\n\\n");
      String s = document.html().replaceAll("\\\\n", "\n");
      return Jsoup.clean(s, "", Whitelist.none(), new Document.OutputSettings().prettyPrint(false));
   }
}
