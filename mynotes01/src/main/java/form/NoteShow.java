package form;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;

import domain.EntNote;

public class NoteShow {
	private Long noteID;
	private String createAt;
	private Double latitude;
	private Double longitude;
	private List<String> tags;
	private String message;
	
	public NoteShow(){
		
	}
	
	public NoteShow(EntNote note){
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String createAt = note.getCreatedAt()==null?null:df.format(note.getCreatedAt());
		String[] tags = note.getTagArr();
		this.noteID = note.getId();
		this.createAt = createAt;
		this.latitude = note.getLatitude();
		this.longitude = note.getLongitude();
		this.tags = Arrays.asList(tags);
		this.setMessage(note.getMessage());
	}
	
	public NoteShow(Long noteID, String createAt, Double latitude, Double longitude, String[] tags, String message) {
		super();
		this.noteID = noteID;
		this.createAt = createAt;
		this.latitude = latitude;
		this.longitude = longitude;
		this.tags = Arrays.asList(tags);;
		this.setMessage(message);
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	public Long getNoteID() {
		return noteID;
	}

	public void setNoteID(Long noteID) {
		this.noteID = noteID;
	}

	public String getCreateAt() {
		return createAt;
	}

	public void setCreateAt(String createAt) {
		this.createAt = createAt;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public List<String> getTags() {
		return tags;
	}

	public void setTags(List<String> tags) {
		this.tags = tags;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
