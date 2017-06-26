package services;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import config.ConfigCtxRoot;
import domain.EntNote;
import domain.EntTag;
import form.NoteForm;
import form.NoteShow;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=ConfigCtxRoot.class)
public class NoteServiceTest {
	@Autowired
	NoteService noteService;
	
	@Test
	public void findAllTagsTest(){
		List<EntTag> tags = noteService.findAllTags();
		tags.forEach(System.out::println);
	}
	
	@Test
	@Transactional
	public void saveTags(){
		EntTag addTag = new EntTag("门诊");
		noteService.save(addTag);
		List<EntTag> tags = noteService.findAllTags();
		tags.forEach(System.out::println);
	}
	
	@Test
	public void findTagsByNameTest(){
		EntTag tag = noteService.findByTagName("门诊");
		System.out.println(tag);
		EntTag tag2 = noteService.findByTagName("消费");
		System.out.println(tag2);
	}
	
	@Test
	public void findByTagIdsTest(){
		Long[] tagids = new Long[]{2L,3L};
		List<EntTag> tags = noteService.findByTagIds(tagids);
		tags.forEach(System.out::println);
	}
	
	@Test
	@Transactional  //Without this, the fetch may requires FetchType.EAGER
	public void saveNote(){
		NoteForm form1 = new NoteForm(new Long[]{}, "超市;支付宝","花费￥230" ,"panhua" );
		noteService.save(form1);
		NoteForm form2 = new NoteForm(new Long[]{1l}, "毛豆","大便" ,"maodou" );
		noteService.save(form2);
				
		List<EntNote> noteList = noteService.findByUsername("panhua");
		noteList.forEach(System.out::println);
		
		List<NoteShow> tagshowList = noteService.noteByUsername("panhua");
		tagshowList.forEach(System.out::println);
	}
	
	@Test
	public void testDate(){
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date dt = new Date();
		String value = df.format(dt);
		System.out.println(value);
	}
}
