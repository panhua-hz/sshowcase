package form;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.validator.constraints.NotBlank;

public class NoteForm {
	private Long[] selTags;
	private String addTags;
	@NotBlank
	private String message;
	private String username;
	
	public NoteForm(){
		
	}
	
	public NoteForm(Long[] selTags, String addTags, String message, String username) {
		super();
		this.selTags = selTags;
		this.addTags = addTags;
		this.message = message;
		this.username = username;
	}

	@Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

	public Long[] getSelTags() {
		return selTags;
	}

	public void setSelTags(Long[] selTags) {
		this.selTags = selTags;
	}

	public String getAddTags() {
		return addTags;
	}

	public void setAddTags(String addTags) {
		this.addTags = addTags;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
}
