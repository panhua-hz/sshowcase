package repository;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import config.ConfigCtxRoot;
import domain.EntNote;
import domain.EntTag;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=ConfigCtxRoot.class)
public class NoteRepositoryTest {
	@Autowired
	NoteRepository noteRepository;
	
	@Test
	@Transactional
	public void saveNote(){
		String loginUser = "a";
		Date createdAt = new Date();
		EntTag tag1 = new EntTag("学习", createdAt, loginUser);
		EntTag tag2 = new EntTag("消费", createdAt, loginUser);
		EntTag tag3 = new EntTag("中行", createdAt, loginUser);
		Set<EntTag> tags = new HashSet<>();
		tags.add(tag1);
		tags.add(tag2);
		tags.add(tag3);
		String message = "字典 ￥25";
		EntNote note = new EntNote(loginUser, message, createdAt, 0.0, 0.0, tags );
		noteRepository.save(note);
		List<EntNote> noteList = noteRepository.findByUsername(loginUser);
		noteList.forEach(System.out::println);
	}
}
