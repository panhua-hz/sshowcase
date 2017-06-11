package services;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import config.ConfigCtxRoot;
import domain.EntTag;
import form.NoteForm;
import form.NoteShow;
import form.Tag;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=ConfigCtxRoot.class)
public class NoteServiceTest {
	@Autowired
	NoteService noteService;
	
	@Test
	public void findAllTagsTest(){
		List<Tag> tags = noteService.findAllTags();
		tags.forEach(System.out::println);
	}
	
	@Test
	@Transactional  //Without this, the fetch may requires FetchType.EAGER
	public void saveNote(){
		NoteForm form1 = new NoteForm(new String[]{}, "超市;支付宝","花费￥230" ,"panhua" );
		noteService.save(form1);
		NoteForm form2 = new NoteForm(new String[]{"1"}, "毛豆","大便" ,"maodou" );
		noteService.save(form2);
		System.out.println("--------------------------------------------------");
		List<NoteShow> tagshowList = noteService.noteByUsername("panhua");
		tagshowList.forEach(System.out::println);
	}
}
