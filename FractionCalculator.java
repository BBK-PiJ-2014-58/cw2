import java.util.Scanner;

public class FractionCalculator {

	public static void main(String args[]) {
		FractionCalculator test = new FractionCalculator();
		test.run();
		System.out.println("Goodbye");
	}

	public void run() {
		System.out.println("Welcome to Phil Buxton's fractions calculator.");
		System.out.println("");
		System.out.println("Welcome to Phil Buxton's fractions calculator for coursework 2 in the PiJ module. It does not meet all the set objectives.");
		System.out.println("");
		System.out.println("Entries must be in the form as follows: fraction (integer, slash (\"/\"), integer), space, operator, space, next fraction etc.). Entries must either end with an \"a\" to negate the result. Example inputs: \"1/3 + 4/5 * 5/8 / 18/21\" or \"1/2 + 2/3 a\". Each user input must be a discrete sum. You cannot use the answer from a previous sum without typing it in again. This operates similar to page 2 instruction 2 in the coursework description, not as per the top of page 3 which looked like a different way of working it to me and I had difficulty reconciling the two.");
		System.out.println("");
		System.out.println("Note 1: This calculator does not use BODMAS. It calculates only from left to right.");
		System.out.println("");
		System.out.println("Note 2: This calculator will not deal with whole numbers in any way.");
		System.out.println("");
		System.out.println("Note 3: To change sign of a fraction (positive to negative or negative to positive), enter \"a\" after the last fraction you enter. The \"a\" cannot go anywhere except at the end of a user input.");
		System.out.println("");
		Scanner sc = new Scanner(System.in);
		String entry;
		boolean calculate = true;
		while (calculate == true) {
			System.out.println("Enter a sum in the correct format or type \"end\"...");
			entry = sc.nextLine();
			if (entry.equals("end")) {
				calculate = false;
			} else {
				String[] entryArray = entry.split(" ");
				int entryArraySize = entryArray.length;
				Fraction[] fractionArray = new Fraction[entryArraySize];
				String[] operatorArray = new String[entryArraySize];

				for (int i = 0; i < entryArraySize; i++) {
					if (i % 2 == 0) {
						String[] fractionSplit = entryArray[i].split("/");
						int numerator = Integer.parseInt(fractionSplit[0]);
						int denominator = Integer.parseInt(fractionSplit[1]);
						fractionArray[i/2] = new Fraction(numerator, denominator);
					} else {
						operatorArray[((i+1)/2)-1] = entryArray[i];
					}
				}

				Fraction answer = fractionArray[0];

				for (int k = 0; k < entryArraySize; k++) {
					String currentOperator = operatorArray[k];
					if (currentOperator != null) {
						Fraction nextFraction = fractionArray[k+1];

						if (currentOperator.equals("*")) {
							answer = answer.multiply(nextFraction);
						}

						if (currentOperator.equals("/")) {
							Fraction upsideDownFraction = new Fraction(nextFraction.getDenominator(),nextFraction.getNumerator());
							answer = answer.multiply(upsideDownFraction);
						}

						if (currentOperator.equals("+")) {
							answer = answer.add(nextFraction);
						}

						if (currentOperator.equals("-")) {
							answer = answer.subtract(nextFraction);
						}

						if (currentOperator.equals("a")) {
							answer = answer.negate();
						}
					}
				} System.out.println(answer);
			}
		} sc.close();
	}
}
