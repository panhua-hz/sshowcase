package domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

@Entity
@Table(name="C_SSB_PRJ_GRP")
public class EntSSBPrjGrp {
	@Id
	@Column(name="PRJ_GRP_ID")
	private String prjGrpId;
	
	@Column(name="PRJ_GRP_NAME")
	private String prjGrpName;
	
	public EntSSBPrjGrp(){
		
	}

	public EntSSBPrjGrp(String prjGrpId, String prjGrpName) {
		super();
		this.prjGrpId = prjGrpId;
		this.prjGrpName = prjGrpName;
	}

	public String getPrjGrpId() {
		return prjGrpId;
	}

	public void setPrjGrpId(String prjGrpId) {
		this.prjGrpId = prjGrpId;
	}

	public String getPrjGrpName() {
		return prjGrpName;
	}

	public void setPrjGrpName(String prjGrpName) {
		this.prjGrpName = prjGrpName;
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
	
	
}
