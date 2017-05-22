package services;

import java.util.List;
import domain.EntNote;
import domain.EntTag;
import form.NoteForm;
import form.NoteShow;

public interface NoteService {
	EntNote save(EntNote note);
	List<EntNote> findByUsername(String username);
	EntTag save(EntTag tag);
	EntTag findByTagName(String tagName); 
	List<EntTag> findAllTags();
	List<String> findAvaTags();
	List<EntTag> findByTagIds(Long[] selTags);
	
	void save(NoteForm note);
	
	List<NoteShow> noteByUsername(String username);
}
