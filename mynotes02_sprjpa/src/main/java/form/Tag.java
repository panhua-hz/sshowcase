package form;

public class Tag {
	private Long id;
	private String tagName;
	
	public Tag(){
		
	}
	
	public Tag(String tagName){
		this.tagName = tagName;
	}

	public Tag(Long id, String tagName) {
		super();
		this.id = id;
		this.tagName = tagName;
	}
	
	@Override
	public String toString() {
		return String.format("Tag[id=%2d, tagName=%s]", id, tagName);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTagName() {
		return tagName;
	}

	public void setTagName(String tagName) {
		this.tagName = tagName;
	}
	
	
	
}
