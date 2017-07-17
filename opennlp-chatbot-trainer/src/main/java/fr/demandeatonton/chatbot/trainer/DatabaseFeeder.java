package fr.demandeatonton.chatbot.trainer;

import java.io.IOException;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.wikipedia.Wiki;

import fr.demandeatonton.chatbot.trainer.domain.pojos.WikiPage;
import fr.demandeatonton.chatbot.trainer.domain.pojos.WikiPageRepository;

@Service
@Transactional
public class DatabaseFeeder {
   private Logger logger = Logger.getLogger(DatabaseFeeder.class.getName());
   @Autowired
   private WikiPageRepository repository;

   @Autowired
   private Wiki wiki;

   public WikiPage getPageText(String title) throws IOException {
      WikiPage wikiPage = repository.findByTitle(title);
      if (wikiPage == null) {
         logger.info("Data not found in local database for " + title + ". Fetching...");
         String pageText = wiki.getPageText(title);
         String htmlText = wiki.getRenderedText(title);
         wikiPage = new WikiPage(title, pageText, htmlText);
         wikiPage = repository.save(wikiPage);
      }

      return wikiPage;
   }

}
