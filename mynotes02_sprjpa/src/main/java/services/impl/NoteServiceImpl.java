package services.impl;

import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import domain.EntNote;
import domain.EntTag;
import form.NoteForm;
import form.NoteShow;
import repository.NoteRepository;
import repository.TagRepository;
import services.NoteService;

@Component
public class NoteServiceImpl implements NoteService {
	private static final Logger logger = LoggerFactory.getLogger(NoteServiceImpl.class);
	@Autowired
	NoteRepository noteRepository;
	@Autowired
	TagRepository tagRepository;
	
	@Override
	public List<EntTag> findAllTags() {
		return tagRepository.findAll();
	}

	@Override
	@Transactional
	public void save(NoteForm note) {
		Set<EntTag> noteTags = new HashSet<>(); //Thread unsafe, no need use multiple thread when using stream.
		List<EntTag> selTags = tagRepository.findByTagIds(note.getSelTags());
		noteTags.addAll(selTags);
		
		if (note.getAddTags() != null){
			String[] addTagArr = note.getAddTags().split(";");
//			Stream<EntTag> addTagStream = Stream.of(addTagArr).filter(item->item!=null&&item.trim().length()>0)
//					.map(EntTag::new)
//					.peek(item->logger.info(item.toString()))
//					.map(item->{
//						EntTag fTag = tagRepository.findByTagName(item.getTagName());
//						if (fTag != null){
//							return fTag;
//						}else{
//							return tagRepository.save(item);
//						}});
//			addTagStream.forEach(item->noteTags.add(item));
			
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
