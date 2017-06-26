package repository;

import java.util.Set;
import domain.EntTag;

public interface TagMoreRep {
	Set<EntTag> saveTags (Set<EntTag> tags);
	Set<EntTag> findByTagNames2(String[] tagNames);
	Set<EntTag> saveTags (String[] tagNames, String loginUser);
}
