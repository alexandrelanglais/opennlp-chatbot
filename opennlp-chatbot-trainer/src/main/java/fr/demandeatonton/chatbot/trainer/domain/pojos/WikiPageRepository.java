package fr.demandeatonton.chatbot.trainer.domain.pojos;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WikiPageRepository extends MongoRepository<WikiPage, String> {
   WikiPage findByTitle(String title);
}
