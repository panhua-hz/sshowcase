package services.impl;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import domain.DocNote;
import domain.EntTag;
import form.NoteForm;
import form.NoteShow;
import form.Tag;
import repository.DocNoteRepository;
import repository.TagRepository;
import services.NoteService;

@Component
public class NoteServiceImpl2 implements NoteService {
	@Autowired
	TagRepository tagRepo;
	@Autowired
	DocNoteRepository noteRepo;
	
	@Override
	public List<Tag> findAllTags() {
		List<EntTag> entTagList = tagRepo.findAll(); 
		List<Tag> result = Arrays.asList(entTagList.stream().map(Tag::new).toArray(Tag[]::new));
		return result;
	}

	@Transactional
	@Override
	public void save(NoteForm note) {
		Set<EntTag> noteTags = new HashSet<>(); //Thread unsafe, no need use multiple thread when using stream.
		List<EntTag> selTags = tagRepo.findByTagIds(Arrays.stream(note.getSelTags()).map(Long::new).toArray(Long[]::new));
		noteTags.addAll(selTags);
		
		if (note.getAddTags() != null){
			String[] addTagArr = note.getAddTags().split(";");
			noteTags.addAll(tagRepo.saveTags(addTagArr, note.getUsername()));
		}
		String[] tags = noteTags.stream().map(EntTag::getTagName).toArray(String[]::new);
		DocNote docNote = new DocNote(note.getUsername(), note.getMessage(), tags);
		noteRepo.save(docNote);
	}

	@Override
	public List<NoteShow> noteByUsername(String username) {
		List<DocNote> noteList = noteRepo.findByUsernameOrderByCreatedAtDesc(username);
		NoteShow[] notesArr = new NoteShow[noteList.size()];
		Arrays.parallelSetAll(notesArr, i->new NoteShow(noteList.get(i)));
		List<NoteShow> noteshowList = Arrays.asList(notesArr);
		return noteshowList;
	}

}
