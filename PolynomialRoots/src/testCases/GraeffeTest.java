package testCases;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import model.Graeffe;

class GraeffeTest {

	private Graeffe graeffeMethod;
	private double[] coefficients;
	
	
	private void stage1() {
		double[] coef = {1.0,2.0,3.0,-4.0};
		coefficients = coef;
		graeffeMethod = new Graeffe(coef);
	}
	
	private void stage2() {
		double[] coef = {1.0,2.0,3.0,4.0};		
		coefficients = coef;
		graeffeMethod = new Graeffe(coef);
	}

	@Test
	void testFindRealRoots() {
		stage1();
		if(graeffeMethod.realRoots.length > 0) {
			assert(true);
		}else {
			fail("Didn't expect this");
		}
	}
	
	@Test
	void testFindComplexRoots() {
		stage2();
		if(graeffeMethod.complexRoots.length > 0) {
			assert(true);
		}else {
			fail("Didn't expect this");
		}
		
	}
	
//	@Test
//	void test() {
//		fail("Not yet implemented");
//	}

}
