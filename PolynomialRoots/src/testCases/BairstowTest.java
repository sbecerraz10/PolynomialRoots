package testCases;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import model.Bairstow;
import model.Graeffe;

class BairstowTest {

	
	private Bairstow bairstowMethod;
	private String[] coefficients;
	private int grade;
	
	private void stage1() {
		String[] coef = {"1.0","2.0","3.0","-4.0"};
		coefficients = coef;
		grade = 3;
		bairstowMethod = new Bairstow(coef,grade);
	}
	
	private void stage2() {
		String[] coef = {"1.0","2.0","3.0","4.0"};
		grade = 3;
		coefficients = coef;
		bairstowMethod = new Bairstow(coef,grade);
	}

	
	@Test
	public void testSolveRealRoots() {
		stage1();
		if(bairstowMethod.getReal().size() > 0) {
			assert(true);
		}else {
			fail("Didn't expect this");
		}
	}
	
	
	
	@Test
	public void testSolveComplexRoots() {
		stage2();
		System.out.println(bairstowMethod.getComplex().size());
		if(bairstowMethod.getComplex().size() > 0) {
			assert(true);
		}else {
			fail("Didn't expect this");
		}
		
	}
	
	
	@Test
	void test() {
		fail("Not yet implemented");
	}

}
