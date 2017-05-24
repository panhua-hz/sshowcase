package services;

import java.util.List;

import domain.EntTag;
import form.NoteForm;
import form.NoteShow;

public interface NoteService {
	List<EntTag> findAllTags();
	void save(NoteForm note);
	List<NoteShow> noteByUsername(String username);
}
