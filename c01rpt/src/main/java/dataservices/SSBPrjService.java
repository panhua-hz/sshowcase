package dataservices;

import java.util.List;

import domain.EntSSBPrjGrp;

public interface SSBPrjService {
	EntSSBPrjGrp addSSBPrjGrp(EntSSBPrjGrp ssbPrjGrp);
	List<EntSSBPrjGrp> getAllSSBPrjGrps();
}
