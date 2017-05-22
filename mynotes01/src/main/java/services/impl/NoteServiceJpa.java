package services.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.apache.directory.api.util.Strings;
import org.springframework.stereotype.Repository;

import domain.EntNote;
import domain.EntTag;
import form.NoteForm;
import form.NoteShow;
import services.NoteService;

@Repository
public class NoteServiceJpa implements NoteService {
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public EntNote save(EntNote note) {
		entityManager.persist(note);
		return note;
	}

	@Override
	public List<EntNote> findByUsername(String username) {
		Query query = entityManager.createQuery("select note from EntNote note where username=:username order by id desc");
		query.setParameter("username", username);
		return (List<EntNote>)query.getResultList();
	}

	@Override
	public EntTag save(EntTag tag) {
		EntTag dbet = findByTagName(tag.getTagName());
		if (dbet==null){
			entityManager.persist(tag);
			dbet = findByTagName(tag.getTagName());
		}
		return dbet;
	}

	@Override
	public EntTag findByTagName(String tagName) {
		Query query = entityManager.createQuery("select tags from EntTag tags where tagName=:tagName");
		query.setParameter("tagName", tagName);
		List<EntTag> result = query.getResultList();
		if (result != null && !result.isEmpty()){
			return result.get(0);
		}
		return null;
	}

	@Override
	public List<EntTag> findAllTags() {
		Query query = entityManager.createQuery("select tags from EntTag tags");
		List<EntTag> result = query.getResultList();
		return result;
	}

	@Override
	public List<String> findAvaTags() {
		List<String> tagStrList = new ArrayList<>();
		List<EntTag> tagList = findAllTags();
		tagList.forEach(item->tagStrList.add(item.getTagName()));
		return tagStrList;
	}

	@Override
	public List<EntTag> findByTagIds(Long[] selTags) {
		List<Long> tagIds = Arrays.asList(selTags);
		Query query = entityManager.createQuery("select tags from EntTag tags where id in (:tagids)");
		query.setParameter("tagids", tagIds);
		return query.getResultList();
	}

	@Transactional
	@Override
	public void save(NoteForm note) {
		Set<EntTag> noteTags = new HashSet<>();
		List<EntTag> selTags = findByTagIds(note.getSelTags());
		noteTags.addAll(selTags);
		
		String addTags = note.getAddTags();
		if (!Strings.isEmpty(addTags)){
			String[] addTagArr = addTags.split(";");
			for(String tag : addTagArr){
				EntTag addTag = new EntTag(tag);
				addTag = save(addTag);
				noteTags.add(addTag);
			}
		}
		EntNote addNote = new EntNote(note.getUsername(), note.getMessage(), new Date(), 0.0, 0.0, noteTags);
		save(addNote);
	}

	@Transactional
	@Override
	public List<NoteShow> noteByUsername(String username) {
		List<EntNote> notesE = findByUsername(username);
		List<NoteShow> noteS = new ArrayList<>();
		notesE.forEach(item->noteS.add(new NoteShow(item)));
		return noteS;
	}

}
