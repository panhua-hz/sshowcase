package dataservices;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import domain.EntRfaPrj;

@Service
@Transactional
public class RfaPrjServiceJpa implements RfaPrjService {
	@PersistenceContext(unitName = "punit-oracle")
	private EntityManager em;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<EntRfaPrj> getRfaPrj() {
		return (List<EntRfaPrj>) em.createQuery("select rfa from EntRfaPrj rfa order by request_status, category, reqid").getResultList();
	}

}
