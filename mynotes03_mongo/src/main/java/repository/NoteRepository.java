package repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import domain.EntNote;

public interface NoteRepository extends JpaRepository<EntNote, Long>{
	public List<EntNote> findByUsername(String username);
}
