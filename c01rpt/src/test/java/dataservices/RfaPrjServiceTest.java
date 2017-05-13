package dataservices;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import domain.EntRfaPrj;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath*:/context_app.xml"})
public class RfaPrjServiceTest {
	@Autowired
	RfaPrjService rfaPrjService;
	
	@Test
	public void getRfaPrjTest(){
		List<EntRfaPrj> prjList = this.rfaPrjService.getRfaPrj();
		for (EntRfaPrj prj : prjList){
			System.out.println(prj.toString());
		}
	}
	
}
