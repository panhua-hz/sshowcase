package services;

import java.util.List;

import form.NoteForm;
import form.NoteShow;
import form.Tag;

public interface NoteService {
	List<Tag> findAllTags();
	void save(NoteForm note);
	List<NoteShow> noteByUsername(String username);
}
