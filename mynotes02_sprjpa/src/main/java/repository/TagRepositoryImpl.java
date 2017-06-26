package repository;

import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import domain.EntTag;

public class TagRepositoryImpl implements TagMoreRep{
	//Please note the name should be TagRepositoryImpl not TagMoreRepImpl
	private static final Logger logger = LoggerFactory.getLogger(TagRepositoryImpl.class);
	@PersistenceContext
	private EntityManager entityManager;
	
	@Autowired
	private TagRepository tagRepository;
	
	
	@Override
	public Set<EntTag> saveTags(Set<EntTag> tags) {
		logger.info("saveTags start.");
		String[] tagNames = tags.parallelStream().map(item->item.getTagName()).toArray(String[]::new);
		Set<EntTag> existedTagSet = findByTagNames2(tagNames);
				
		Set<EntTag> newTagSet = new HashSet<>();
		newTagSet.addAll(tags);
		newTagSet.removeAll(existedTagSet);
		
		newTagSet.forEach(item->entityManager.persist(item));
		existedTagSet = findByTagNames2(tagNames);
		logger.info("saveTags end.");
		return existedTagSet;		
	}
	

	@Override
	public Set<EntTag> findByTagNames2(String[] tagNames) {
		List<EntTag> existedTagList = this.tagRepository.findByTagNames(tagNames);
		Set<EntTag> existedTagSet = new HashSet<>();
		existedTagSet.addAll(existedTagList);
		return existedTagSet;
	}


	@Override
	public Set<EntTag> saveTags(String[] tagNames, String loginUser) {
		Set<EntTag> existedTagSet = findByTagNames2(tagNames);
		
		Set<EntTag> newTagSet = new HashSet<>();
		newTagSet.addAll(Arrays.asList(Arrays.stream(tagNames)
				.map(tagName->new EntTag(tagName, new Date(), loginUser))
				.toArray(EntTag[]::new)));
		newTagSet.removeAll(existedTagSet);
		
		newTagSet.forEach(item->entityManager.persist(item));
		
		existedTagSet = findByTagNames2(tagNames);
		logger.info("saveTags end.");
		return existedTagSet;	
	}
}
