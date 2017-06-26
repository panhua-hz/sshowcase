package repository;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import config.ConfigCtxMongoDB;
import domain.DocNote;

@ContextConfiguration(classes=ConfigCtxMongoDB.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class DocNoteRepositoryTest {
	@Autowired
	DocNoteRepository noteRepo;
	
	@Before
	public void setUp() {
		noteRepo.deleteAll();
		Calendar c1 = Calendar.getInstance();
		c1.set(Calendar.DST_OFFSET, 60*60*1000);
		Date d1= c1.getTime();
		DocNote note1= new DocNote("junit","苹果 0.5kg ￥12.00元", new String[]{"消费","水果"}, d1);
		noteRepo.save(note1);
		DocNote note2= new DocNote("junit","超市 ￥123.45", new String[]{"超市","中行"});
		noteRepo.save(note2);
	}
	@After
	public void cleanUp(){
		noteRepo.deleteAll();
	}
	
	@Test
	public void findByUsernameOrderByCreatedAtDescTest(){
		List<DocNote> noteList = noteRepo.findByUsernameOrderByCreatedAtDesc("junit");
		noteList.forEach(System.out::println);
	}
}
