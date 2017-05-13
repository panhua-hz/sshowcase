package andrew.pizza.domain;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class Payment implements Serializable{
	private static final long serialVersionUID = 4624755919669495445L;
	
	private PaymentType paymentType;
	private double payAmount;

	private String creditCardNumber;

	public PaymentType getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(PaymentType paymentType) {
		this.paymentType = paymentType;
	}

	public double getPayAmount() {
		return payAmount;
	}

	public void setPayAmount(double payAmount) {
		this.payAmount = payAmount;
	}

	public String getCreditCardNumber() {
		return creditCardNumber;
	}

	public void setCreditCardNumber(String creditCardNumber) {
		this.creditCardNumber = creditCardNumber;
	}

	@Override
	public String toString() {
		if (this.paymentType == PaymentType.CREDIT_CARD) {
			return ToStringBuilder.reflectionToString(this);
		} else {
			return "PaymentType: " + this.paymentType + "\tpayAmount: " + this.payAmount;
		}
	}
}
