package org.chap1;

public class CalculatorTestV2 {

	private int nbErrors = 0;

	public void testAdd() {
		Calculator calculator = new Calculator();

		double result = calculator.add(10, 50);
		if(result != 60) {
			throw new IllegalStateException("Bad result: " + result);
		}
		System.out.println("Good result = " + result);
	}
	public static void main(String[] args) {
		CalculatorTestV2 test = new CalculatorTestV2();
		try {
			test.testAdd();
		} catch (Throwable e) {
			test.nbErrors++;
			e.printStackTrace();
		}
		if (test.nbErrors > 0) {
			throw new IllegalStateException("There were " + test.nbErrors + " error(s)");
		}

	}
}
