package domain;

import java.util.Date;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class DocNote {
	@Id
	private String id;
	@Indexed
	private String username;
	private String message;
	private Date createdAt;
	private Double latitude;
	private Double longitude;
	private String[] tags;
	
	public DocNote(){}
	
	public DocNote(String username, String message, String[] tags) {
		this.username = username;
		this.message = message;
		this.createdAt = new Date();
		this.latitude = 0.0;
		this.longitude = 0.0;
		this.tags = tags;
	}
	
	public DocNote(String username, String message, String[] tags,  Date createdAt) {
		this.username = username;
		this.message = message;
		this.createdAt = createdAt;
		this.latitude = 0.0;
		this.longitude = 0.0;
		this.tags = tags;
	}

	public DocNote(String username, String message, String[] tags,  Date createdAt, Double latitude, Double longitude) {
		this.username = username;
		this.message = message;
		this.createdAt = createdAt;
		this.latitude = latitude;
		this.longitude = longitude;
		this.tags = tags;
	}

	public DocNote(String id, String username, String message, Date createdAt, Double latitude, Double longitude, String[] tags) {
		this.id = id;
		this.username = username;
		this.message = message;
		this.createdAt = createdAt;
		this.latitude = latitude;
		this.longitude = longitude;
		this.tags = tags;
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this,ToStringStyle.JSON_STYLE);
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
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

	public String[] getTags() {
		return tags;
	}

	public void setTags(String[] tags) {
		this.tags = tags;
	}

}
