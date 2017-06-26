package domain;

import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ToStringBuilder;

@Entity
@Table(name="tags")
public final class EntTag {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name="tag_name")
	private String tagName;
	@Column(name="created_at")
	private Date createdAT;
	@Column(name="created_by")
	private String createdBy;
	
	public EntTag() {
	}
	public EntTag(String tagName) {
		this.tagName = tagName;
	}
	public EntTag(Long id, String tagName) {
		this.id = id;
		this.tagName = tagName;
	}
	public EntTag(String tagName, Date createdAT, String createdBy) {
		this.tagName = tagName;
		this.createdAT = createdAT;
		this.createdBy = createdBy;
	}
	public EntTag(Long id, String tagName, Date createdAT, String createdBy) {
		this.id = id;
		this.tagName = tagName;
		this.createdAT = createdAT;
		this.createdBy = createdBy;
	}
	@Override
	public boolean equals(Object that) {
		if (this == that)
			return true;
		if (that == null)
			return false;
		if (this.hashCode() != that.hashCode()){
			return false;
		}
		if ((that instanceof EntTag)){
			EntTag other = (EntTag)that;
			return Objects.equals(this.tagName, other.tagName);
		}else{
			return false;
		}
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(tagName);
	}
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
	public String getTagName() {
		return tagName;
	}
	public void setTagName(String tagName) {
		this.tagName = tagName;
	}
	public Date getCreatedAT() {
		return createdAT;
	}
	public void setCreatedAT(Date createdAT) {
		this.createdAT = createdAT;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	
	
}
