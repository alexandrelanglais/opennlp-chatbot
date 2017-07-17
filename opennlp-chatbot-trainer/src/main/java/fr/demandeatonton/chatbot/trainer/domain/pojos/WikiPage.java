package fr.demandeatonton.chatbot.trainer.domain.pojos;

import org.springframework.data.annotation.Id;

public class WikiPage {
   @Id
   private String id;

   private String title;

   private String rawContent;

   private String htmlContent;

   public WikiPage() {
   }

   public WikiPage(String title, String rawContent, String htmlContent) {
      this.title = title;
      this.rawContent = rawContent;
      this.htmlContent = htmlContent;
   }

   public String getId() {
      return id;
   }

   public void setId(String id) {
      this.id = id;
   }

   public String getTitle() {
      return title;
   }

   public void setTitle(String title) {
      this.title = title;
   }

   public String getRawContent() {
      return rawContent;
   }

   public void setRawContent(String rawContent) {
      this.rawContent = rawContent;
   }

   public String getHtmlContent() {
      return htmlContent;
   }

   public void setHtmlContent(String htmlContent) {
      this.htmlContent = htmlContent;
   }

   @Override
   public String toString() {
      return "WikiPage [id=" + id + ", rawContent=" + rawContent + "]";
   }

}
