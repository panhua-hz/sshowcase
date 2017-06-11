package repository;

import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import config.ConfigCtxRoot;
import domain.EntTag;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=ConfigCtxRoot.class)
public class TagRepositoryTest {
	@Autowired
	TagRepository tagService;
	
	@Test
	public void findAllTags(){
		List<EntTag> tagList = this.tagService.findAll();
		tagList.forEach(System.out::println);
	}
	
	@Test
	public void findByTagName(){
		EntTag tag = this.tagService.findByTagName("水果");
		System.out.println(tag);
	}
	
	
	@Test
	@Transactional
	public void saveTag(){
		EntTag tag = new EntTag("学习", new Date(), "a");
		tagService.save(tag);
		EntTag tag1 = this.tagService.findByTagName("学习");
		System.out.println(tag1);
	}
	
	@Test
	public void findByTagIdsTest(){
		Long[] tagids = new Long[]{2L,3L};
		List<EntTag> tags = tagService.findByTagIds(tagids);
		tags.forEach(System.out::println);
	}
	
	@Test
	@Transactional
	public void saveTagsTest(){
		String loginUser = "a";
		Date createdAt = new Date();
		EntTag tag1 = new EntTag("学习", createdAt, loginUser);
		EntTag tag2 = new EntTag("消费", createdAt, loginUser);
		EntTag tag3 = new EntTag("中行", createdAt, loginUser);
		Set<EntTag> tags = new HashSet<>();
		tags.add(tag1);
		tags.add(tag2);
		tags.add(tag3);
		Set<EntTag> tagFinal = tagService.saveTags(tags);
		tagFinal.forEach(System.out::println);
	}
	
	@Test
	public void findByTagNamesTest(){
		String loginUser = "a";
		Date createdAt = new Date();
		EntTag tag1 = new EntTag("学习", createdAt, loginUser);
		EntTag tag2 = new EntTag("消费", createdAt, loginUser);
		EntTag tag3 = new EntTag("中行", createdAt, loginUser);
		Set<EntTag> tags = new HashSet<>();
		tags.add(tag1);
		tags.add(tag2);
		tags.add(tag3);
		Stream<String> tagNamesStream = tags.parallelStream().map(item->item.getTagName());
		String[] tagNames = tagNamesStream.toArray(String[]::new);
		List<String> tagNameList = Arrays.asList(tagNames);
		tagNameList.forEach(System.out::println);
		
		List<EntTag> dbTags = tagService.findByTagNames(tagNames);
		dbTags.forEach(System.out::println);
	}
	
	@Test
	public void findByTagNamesTest2(){
		String loginUser = "a";
		Date createdAt = new Date();
		EntTag tag1 = new EntTag("学习", createdAt, loginUser);
		EntTag tag2 = new EntTag("消费", createdAt, loginUser);
		EntTag tag3 = new EntTag("中行", createdAt, loginUser);
		Set<EntTag> tags = new HashSet<>();
		tags.add(tag1);
		tags.add(tag2);
		tags.add(tag3);
		Stream<String> tagNamesStream = tags.parallelStream().map(item->item.getTagName());
		String[] tagNames = tagNamesStream.toArray(String[]::new);
		List<String> tagNameList = Arrays.asList(tagNames);
		tagNameList.forEach(System.out::println);
		
		Set<EntTag> dbTags = tagService.findByTagNames2(tagNames);
		dbTags.forEach(System.out::println);
	}
}
