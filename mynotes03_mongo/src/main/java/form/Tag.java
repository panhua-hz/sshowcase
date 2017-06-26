package form;

import domain.EntTag;

public class Tag {
	private String id;
	private String tagName;
	
	public Tag(){
		
	}
	
	public Tag(String tagName){
		this.tagName = tagName;
	}

	public Tag(String id, String tagName) {
		super();
		this.id = id;
		this.tagName = tagName;
	}
	
	public Tag(EntTag ent){
		this.id = ent.getId().toString();
		this.tagName = ent.getTagName();
	}
	
	@Override
	public String toString() {
		return String.format("Tag[id=%s, tagName=%s]", id, tagName);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTagName() {
		return tagName;
	}

	public void setTagName(String tagName) {
		this.tagName = tagName;
	}
	
	
	
}
