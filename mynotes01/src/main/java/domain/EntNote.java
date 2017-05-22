package domain;

import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ToStringBuilder;

@Entity
@Table(name="note")
public class EntNote {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column
	private String username;
	@Column
	private String message;
	@Column(name="created_at")
	private Date createdAt;
	@Column
	private Double latitude;
	@Column
	private Double longitude;
	
	@ManyToMany //(fetch=FetchType.EAGER) //@Transactional cloud make the get after save to fetch otherwise, will close the session.
	@JoinTable(name="note_with_tags",
			joinColumns=
				@JoinColumn(name="note_id", referencedColumnName="id"),
			inverseJoinColumns=
			    @JoinColumn(name="tag_id", referencedColumnName="id"))
	
	private Set<EntTag> tags; 
	
	public EntNote(){
		
	}

	
	public EntNote(String username, String message, Date createdAt, Double latitude, Double longitude,
			Set<EntTag> tags) {
		super();
		this.username = username;
		this.message = message;
		this.createdAt = createdAt;
		this.latitude = latitude;
		this.longitude = longitude;
		this.tags = tags;
	}


//	public EntNote(Long id, String[] tagArr,String username, String message, Date createdAt, Double latitude, Double longitude) {
//		this.id = id;
//		this.username = username;
//		this.message = message;
//		this.createdAt = createdAt;
//		this.latitude = latitude;
//		this.longitude = longitude;
//		setTagArr(tagArr);
//	}
//
//	public EntNote(String[] tagArr, String username, String message, Date createdAt, Double latitude, Double longitude) {
//		this.username = username;
//		this.message = message;
//		this.createdAt = createdAt;
//		this.latitude = latitude;
//		this.longitude = longitude;
//		setTagArr(tagArr);
//	}
//	public EntNote(Set<String> tagset, String username, String message, Date createdAt, Double latitude, Double longitude) {
//		this.username = username;
//		this.message = message;
//		this.createdAt = createdAt;
//		this.latitude = latitude;
//		this.longitude = longitude;
//		setTagArr(tagset);
//	}
//	public EntNote(String username, String message, Date createdAt, Double latitude, Double longitude, Set<EntTag> tags) {
//		this.username = username;
//		this.message = message;
//		this.createdAt = createdAt;
//		this.latitude = latitude;
//		this.longitude = longitude;
//		this.tags = tags;
//	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
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

	public Set<EntTag> getTags() {
		return tags;
	}

	public void setTags(Set<EntTag> tags) {
		this.tags = tags;
	}

	public String[] getTagArr() {
		if (this.tags != null && !this.tags.isEmpty()){
			String[] tagArr = new String[this.tags.size()];
			int i = 0;
			for (EntTag tag : this.tags){
				tagArr[i++] = tag.getTagName();
			}
			return tagArr;
		}
		return null;
	}
	

	public void setTagArr(String[] tagArr) {
		Set<String> strSet = new HashSet<>();
		Collections.addAll(strSet, tagArr);
		setTagArr(strSet);
	}
	
	public void setTagArr(Set<String> tagset){
		Set<EntTag> belongTags = new HashSet<>();
		tagset.forEach(s->belongTags.add(new EntTag(s)));
		this.tags = belongTags;
	}
	
	
}
