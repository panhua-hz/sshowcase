package repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import domain.DocNote;

public interface DocNoteRepository extends MongoRepository<DocNote, String> {
	
	//db.docNote.find({"username" : "junit"}).sort({"createdAt":-1})
	List<DocNote> findByUsernameOrderByCreatedAtDesc(String username);
}
