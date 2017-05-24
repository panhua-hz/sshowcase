package repository;

import java.util.List;

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
		EntTag tag = new EntTag("学习");
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
}
