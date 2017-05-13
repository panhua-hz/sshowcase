package dataservices;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import domain.EntSSBPrjGrp;


@Service
@Transactional
public class SSBPrjServiceJpa implements SSBPrjService {
	
	@PersistenceContext(unitName = "punit-oracle")
	private EntityManager em;
	
	@Override
	public EntSSBPrjGrp addSSBPrjGrp(EntSSBPrjGrp ssbPrjGrp) {
		em.persist(ssbPrjGrp);
		return ssbPrjGrp;
	}

	@Override
	public List<EntSSBPrjGrp> getAllSSBPrjGrps() {
		return (List<EntSSBPrjGrp>) em.createQuery("select prjgrp from EntSSBPrjGrp prjgrp").getResultList();
	}

}
