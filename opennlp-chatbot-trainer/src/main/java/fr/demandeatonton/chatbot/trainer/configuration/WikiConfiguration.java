package fr.demandeatonton.chatbot.trainer.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.wikipedia.Wiki;

@Configuration
public class WikiConfiguration {

   @Bean
   public Wiki wiki() {
      return new Wiki();
   }
}
