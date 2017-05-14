package andrew.pizza.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import andrew.pizza.domain.Payment;

@Repository
public class PaymentServiceImpl implements PaymentService {
	private static final Logger logger = LoggerFactory.getLogger(PaymentServiceImpl.class);

	@Override
	public void takePayment(Payment payment) {
		switch (payment.getPaymentType()) {
		case CASH:
			logger.info("Pay CASH for "+payment.getPayAmount());
			break;
		case CHECK:
			logger.info("Pay with CHECK for "+payment.getPayAmount());
			break;
		case CREDIT_CARD:
			logger.info("Pay with CREDIT_CARD for "+payment.getPayAmount());
			break;
		default:
			break;
		}
	}

}
