package domain;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class LinkVO {
	String linkName;
	String linkUrl;

	public LinkVO(String linkName, String linkUrl) {
		super();
		this.linkName = linkName;
		this.linkUrl = linkUrl;
	}
	public String getLinkName() {
		return linkName;
	}
	public void setLinkName(String linkName) {
		this.linkName = linkName;
	}
	public String getLinkUrl() {
		return linkUrl;
	}
	public void setLinkUrl(String linkUrl) {
		this.linkUrl = linkUrl;
	}
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
	
	
}
