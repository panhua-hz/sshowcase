package services.impl;

import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import domain.EntNote;
import domain.EntTag;
import form.NoteForm;
import form.NoteShow;
import form.Tag;
import repository.NoteRepository;
import repository.TagRepository;
import services.NoteService;

//@Component
public class NoteServiceImpl implements NoteService {
	//private static final Logger logger = LoggerFactory.getLogger(NoteServiceImpl.class);
	@Autowired
	NoteRepository noteRepository;
	@Autowired
	TagRepository tagRepository;
	
	@Override
	public List<Tag> findAllTags() {
		List<EntTag> entTagList = tagRepository.findAll(); 
		List<Tag> result = Arrays.asList(entTagList.stream().map(Tag::new).toArray(Tag[]::new));
		return result;
	}

	@Override
	@Transactional
	public void save(NoteForm note) {
		Set<EntTag> noteTags = new HashSet<>(); //Thread unsafe, no need use multiple thread when using stream.
		List<EntTag> selTags = tagRepository.findByTagIds(Arrays.stream(note.getSelTags()).map(Long::new).toArray(Long[]::new));
		noteTags.addAll(selTags);
		
		if (note.getAddTags() != null){
			String[] addTagArr = note.getAddTags().split(";");
			noteTags.addAll(tagRepository.saveTags(addTagArr, note.getUsername()));
		}
		EntNote addNote = new EntNote(note.getUsername(), note.getMessage(), new Date(), 0.0, 0.0, noteTags);
		noteRepository.save(addNote);
	}

	@Override
	@Transactional
	public List<NoteShow> noteByUsername(String username) {
		List<EntNote> notesE = noteRepository.findByUsername(username);
		NoteShow[] notesArr = new NoteShow[notesE.size()];
		Arrays.parallelSetAll(notesArr, i->new NoteShow(notesE.get(i)));
		List<NoteShow> noteshowList = Arrays.asList(notesArr);
		return noteshowList;
	}

}
