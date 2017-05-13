package domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

@Entity
@Table(name="TBL_PROJECT")
public class EntRfaPrj {
	@Id
	@Column(name="REQID")
	public String reqId;
	
	@Column(name="REQUEST_NAME")
	public String requestName;
	
	@Column(name="REQUEST_STATUS")
	public String requestStatus;
	
	@Column(name="CATEGORY")
	public String category;
	
	public EntRfaPrj(){
		
	}

	public EntRfaPrj(String reqId, String requestName, String requestStatus, String category) {
		super();
		this.reqId = reqId;
		this.requestName = requestName;
		this.requestStatus = requestStatus;
		this.category = category;
	}

	@Override
	public boolean equals(Object that) {
		return EqualsBuilder.reflectionEquals(this, that);
	}

	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	public String getReqId() {
		return reqId;
	}

	public void setReqId(String reqId) {
		this.reqId = reqId;
	}

	public String getRequestName() {
		return requestName;
	}

	public void setRequestName(String requestName) {
		this.requestName = requestName;
	}

	public String getRequestStatus() {
		return requestStatus;
	}

	public void setRequestStatus(String requestStatus) {
		this.requestStatus = requestStatus;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}
}
