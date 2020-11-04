import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.InputMismatchException;
import java.util.Scanner;
public class Assignment_2_17AV26_20105817 {
	/*
	 * 
	 * This program consists of multiple methods which were written to calculate 
	 * pi with different formulas.
	 *  
	 * Student Number: 20105817
	 * Date: February 7th 2020
	 * by Ayaz Vural for CISC124.
	 */

	// Used to count iterations//
	public static int count;
	// For screen input
	private static Scanner screenInput = new Scanner(System.in);

	// This method uses the Leibniz Formula to calculate pi(using floats). The maximum number of iterations are fixed.//
	// The output will be the approximation of pi, iterations it took to get to that value and the percentage of error.//
	public static float piCalculatorLeibniz() {
		float estimateOfLeibniz = 1;
		float lastEstimate = 0;
		float k = 1;
		while (estimateOfLeibniz != lastEstimate) {
			lastEstimate = estimateOfLeibniz;
			float terms = 1f/((2f*k)+1f);
			if (k % 2 == 0)
				estimateOfLeibniz += terms;
			else
				estimateOfLeibniz -= terms;
			k ++;
			count ++;
		}
		estimateOfLeibniz *= 4f;
		return estimateOfLeibniz;
	}



	// This method uses the Taylor series arctan Formula to calculate pi (using floats). The maximum number of iterations are fixed.//
	// The output will be the approximation of pi, iterations it took to get to that value and the percentage of error.//
	public static float arcTanPiCalculatorFloat() {
		float arcTanFloat = 0;
		float lastValue = 1;
		float k = 0;
		float[] arcTanFractions = {(1f/10f),(1f/515f),(1f/239f)};
		float[] arcTan = {0f,0f,0f};
		float arcTanPart1 = 0;
		while (arcTanFloat != lastValue) {
			lastValue = arcTanFloat;
			if (k % 2 == 0)
				arcTanPart1 = 1f/((2f*k)+1f);
			else
				arcTanPart1 = (-1f)/((2f*k)+1f);
			for (int i = 0; i <= 2; i ++) {
				float next = arcTanPart1 * arcTanFractions[i];
				arcTan[i] += next;
			}
			arcTanFractions[0] *= ((1f/10f)*(1f/10f));
			arcTanFractions[1] *= ((1f/515f)*(1f/515f));
			arcTanFractions[2] *= ((1f/239f)*(1f/239f));
			k ++;
			count ++;
			arcTanFloat = (8f * arcTan[0]) - (4f * arcTan[1]) - (arcTan[2]);
		}
		arcTanFloat *= 4f;
		return arcTanFloat;
	}



	// This method uses the Taylor series arctan Formula to calculate pi (this time using doubles). The maximum number of iterations are fixed.//
	// The output will be the approximation of pi, iterations it took to get to that value and the percentage of error.//
	public static double arcTanPiCalculatorDouble() {
		double arcTanDouble = 0;
		double lastValue = 1;
		double k = 0;
		double[] arcTan = {0d, 0d, 0d};
		double[] arcTanFractions = {(1d/10d),(1d/515d),(1d/239d)};
		double index = 0;
		while (arcTanDouble != lastValue) {
			lastValue = arcTanDouble;
			for (int i = 0; i <= 2; i ++) {
				index = ((Math.pow(-1d, k))/(2d*k + 1d))*(Math.pow(arcTanFractions[i], (2d*k + 1d)));
				arcTan[i] += index;
			}
			k ++;
			count ++;
			arcTanDouble = (8d * arcTan[0]) - (4d * arcTan[1]) - (arcTan[2]);
		}
		arcTanDouble *= 4d;
		return arcTanDouble;
	}
	// This method attempts to modify the previous method to come up with a better approximation of pi (I failed, sadly...)//
	// The output will be the approximation of pi, iterations it took to get to that value and the percentage of error.//
	public static double arcTanPiCalculatorDoubleModified() {
		double arcTanModified = 0;
		double lastValue = 1;
		double k = 0;
		double[] arcTans = {0d, 0d, 0d};
		double[] arcTanFractions = {(1d/10d),(1d/515d),(1d/239d)};
		double[] remainders = {0d, 0d, 0d};
		double term = 0;
		double totalTerm = 0;
		while (arcTanModified != lastValue) {
			lastValue = arcTanModified;
			for (int i = 0; i <= 2; i ++) {
				term = ((Math.pow(-1d, k))/(2d*k + 1d))*(Math.pow(arcTanFractions[i], (2d*k + 1d)));
				totalTerm = term + remainders[i];
				double tempTan = arcTans[i] + totalTerm;
				double portionOfTermInTan = tempTan - arcTans[i];
				remainders[i] = totalTerm - portionOfTermInTan;
				arcTans[i] = tempTan;
			}
			k ++;
			count ++;
			arcTanModified = (8d * arcTans[0]) - (4d * arcTans[1]) - (arcTans[2]);
		}
		arcTanModified *= 4d;
		return arcTanModified;
	}


	// This method uses the BBP formula to calculate pi using doubles.//
	// The output will be the approximation of pi, iterations it took to get to that value and the percentage of error.//
	public static double piCalculatorBBP() {
		double piBBP = 0;
		double lastValue = 1;
		double k = 0;
		double term = 0;
		while (piBBP != lastValue) {
			lastValue = piBBP;
			term = (1/(Math.pow(16d, k)))*((4d/(8d*k+1d))-(2d/(8d*k+4d))-(1d/(8d*k+5d))-(1d/(8d*k+6d)));
			piBBP += term;
			k ++;
			count ++;
		}
		return piBBP;
	}

	// This method uses the BBP method to calculate pi using doubles but this time also including big decimals to yield a 16 digit number and also a 100 digit number.//
	// The output will be the approximation of pi, iterations it took to get to that value and the percentage of error.//
	public static BigDecimal piCalculatorBBPBig(int numDigits) {
		//This method is the direct implementation of the BBP formula written with the supplied BigDecimal methods given for this assignment.
		int k = 0;
		int comparison = 1;
		int divisor = 16;
		int numSegments = (int) Math.ceil((numDigits-1d)/16d);
		double[] roundedDoubles = new double[numSegments];

		do {
			BigDecimal one = new BigDecimal(1.0);

			if (k > 0) {
				BigDecimal[] originalSegments = new BigDecimal[numSegments];
				for (int i = numSegments-1; i >= 0; i --) {
					BigDecimal temp = new BigDecimal(roundedDoubles[i]);
					originalSegments[i] = temp.movePointLeft(divisor*(i+1));
				}

				BigDecimal[] tempAdditions = new BigDecimal[numSegments];
				for (int i = 0; i <= numSegments-1; i ++) {
					if (i == 0) {
						tempAdditions[i] = originalSegments[i].multiply(one);
					}
					else {
						tempAdditions[i] = tempAdditions[i-1].add(originalSegments[i]);
					}
				}

				BigDecimal previousEstimate = tempAdditions[numSegments-1].multiply(one);

				BigDecimal sixteen = new BigDecimal(16.0);
				BigDecimal div1 = sixteen.pow(k);
				BigDecimal term1 = one.divide(div1, numDigits, RoundingMode.HALF_EVEN);

				BigDecimal eight = new BigDecimal(8.0);
				BigDecimal val_k = new BigDecimal(k);
				BigDecimal eightK = eight.multiply(val_k);
				BigDecimal div2 = eightK.add(one);
				BigDecimal four = new BigDecimal(4.0);
				BigDecimal term2 = four.divide(div2, numDigits, RoundingMode.HALF_EVEN);

				BigDecimal div3 = eightK.add(four);
				BigDecimal two = new BigDecimal(2.0);
				BigDecimal term3 = two.divide(div3, numDigits, RoundingMode.HALF_EVEN);

				BigDecimal five = new BigDecimal(5.0);
				BigDecimal div4 = eightK.add(five);
				BigDecimal term4 = one.divide(div4, numDigits, RoundingMode.HALF_EVEN);

				BigDecimal six = new BigDecimal(6.0);
				BigDecimal div5 = eightK.add(six);
				BigDecimal term5 = one.divide(div5, numDigits, RoundingMode.HALF_EVEN);

				BigDecimal sub1 = term2.subtract(term3);
				BigDecimal sub2 = sub1.subtract(term4);
				BigDecimal sub3 = sub2.subtract(term5);

				BigDecimal totalTerm = term1.multiply(sub3);
				BigDecimal currentEstimate = totalTerm.add(previousEstimate);

				BigDecimal[] totalTerms = new BigDecimal[numSegments];
				BigDecimal[] segmented = new BigDecimal[numSegments];
				BigDecimal[] rounded = new BigDecimal[numSegments];

				int counter = 1;
				for (int i = 0; i < numSegments; i++) {
					if (i == 0) {
						totalTerms[i] = currentEstimate.multiply(one);
					}
					else {
						BigDecimal shiftedBack = rounded[i-1].movePointLeft(divisor*(counter-1));
						totalTerms[i] = totalTerms[i-1].subtract(shiftedBack);
					}
					segmented[i] = totalTerms[i].movePointRight(divisor*counter);
					rounded[i] = segmented[i].setScale(0, RoundingMode.HALF_UP);
					roundedDoubles[i] = rounded[i].doubleValue();
					counter ++;
				} 
				comparison = currentEstimate.compareTo(previousEstimate);    			
			}

			else {
				BigDecimal sixteen = new BigDecimal(16.0);
				BigDecimal div1 = sixteen.pow(k);
				BigDecimal term1 = one.divide(div1, numDigits, RoundingMode.HALF_EVEN);

				BigDecimal eight = new BigDecimal(8.0);
				BigDecimal val_k = new BigDecimal(k);
				BigDecimal eightK = eight.multiply(val_k);
				BigDecimal div2 = eightK.add(one);
				BigDecimal four = new BigDecimal(4.0);
				BigDecimal term2 = four.divide(div2, numDigits, RoundingMode.HALF_EVEN);

				BigDecimal div3 = eightK.add(four);
				BigDecimal two = new BigDecimal(2.0);
				BigDecimal term3 = two.divide(div3, numDigits, RoundingMode.HALF_EVEN);

				BigDecimal five = new BigDecimal(5.0);
				BigDecimal div4 = eightK.add(five);
				BigDecimal term4 = one.divide(div4, numDigits, RoundingMode.HALF_EVEN);

				BigDecimal six = new BigDecimal(6.0);
				BigDecimal div5 = eightK.add(six);
				BigDecimal term5 = one.divide(div5, numDigits, RoundingMode.HALF_EVEN);

				BigDecimal sub1 = term2.subtract(term3);
				BigDecimal sub2 = sub1.subtract(term4);
				BigDecimal sub3 = sub2.subtract(term5);

				BigDecimal totalTerm = term1.multiply(sub3);

				BigDecimal[] totalTerms = new BigDecimal[numSegments];
				BigDecimal[] segmented = new BigDecimal[numSegments];
				BigDecimal[] rounded = new BigDecimal[numSegments];

				int counter = 1;
				for (int i = 0; i < numSegments; i++) {
					if (i == 0) {
						totalTerms[i] = totalTerm.multiply(one);
					}
					else {
						BigDecimal shiftedBack = rounded[i-1].movePointLeft(divisor*(counter-1));
						totalTerms[i] = totalTerms[i-1].subtract(shiftedBack);
					}
					segmented[i] = totalTerms[i].movePointRight(divisor*counter);
					rounded[i] = segmented[i].setScale(0, RoundingMode.HALF_UP);
					roundedDoubles[i] = rounded[i].doubleValue();
					counter ++;
				}

				BigDecimal previousEstimate = new BigDecimal(0.0);
				BigDecimal currentEstimate = totalTerm.add(previousEstimate);
				comparison = currentEstimate.compareTo(previousEstimate);
			}
			k++;
			count++;
		} while (comparison != 0);

		BigDecimal one = new BigDecimal(1.0);
		BigDecimal[] originalSegments = new BigDecimal[numSegments];
		for (int i = numSegments-1; i >= 0; i --) {
			BigDecimal temp = new BigDecimal(roundedDoubles[i]);
			originalSegments[i] = temp.movePointLeft(divisor*(i+1));
		}

		BigDecimal[] tempAdditions = new BigDecimal[numSegments];
		for (int i = 0; i <= numSegments-1; i ++) {
			if (i == 0) {
				tempAdditions[i] = originalSegments[i].multiply(one);
			}
			else {
				tempAdditions[i] = tempAdditions[i-1].add(originalSegments[i]);
			}
		}

		BigDecimal finalEstimate = tempAdditions[numSegments-1].multiply(one);
		BigDecimal finalTruncatedEstimate = finalEstimate.setScale(numDigits,RoundingMode.HALF_UP);
		return finalTruncatedEstimate;
	}





	// Aids in displaying BigDecimal numbers to the screen using 100
	// digits per line.
	public static void displayResult(BigDecimal aNum) {
		var asString = aNum.toString();
		for(int i = 0; i < asString.length(); i++) {
			System.out.print(asString.charAt(i));
			if (i > 0 && (i + 1) % 100 == 0)
				System.out.println();
		}
		System.out.println();
	} // end displayResult

	// Simplifies reporting the execution time and the number of iterations
	public static void timeIterationsReport(long start) {
		long finishTime = System.nanoTime();
		long diff = finishTime - start;
		if (diff <= 1e3)
			System.out.print("Took " + diff + " nanosec., ");
		else if (diff <= 1e6)
			System.out.print("Took " + Math.round(diff / 10.0) / 100.0 + " microsec. ");
		else if (diff <= 1e9)
			System.out.print("Took " + Math.round(diff / 1e4) / 100.0 + " millisec. ");
		else
			System.out.print("Took " + Math.round(diff / 1e7) / 100.0 + " sec. ");
		System.out.println("and required " + count + " iterations.");
		count = 0;
	} // end timeReport

	// Used to calculate and display the accuracy of a 16 digit result using the value of
	// pi stored in the Math class.
	public static void accuracyReport(double estimate) {
		var error = 100 * (estimate - Math.PI) / Math.PI;
		System.out.printf("Error is %.2e percent.\n\n", error);
	} // end accuracyReport

	// Copied from IOHelper (only method needed)
	public static int getInt(int low, String prompt, int high) {
		int numFromUser = 0;
		String dummy;
		boolean numericEntryOK;
		do {
			System.out.print(prompt);
			numericEntryOK = false;
			try {
				numFromUser = screenInput.nextInt();
				numericEntryOK = true;
			} catch (InputMismatchException e) {
				dummy = screenInput.nextLine();
				System.out.println(dummy + " is not an integer!");
				numFromUser = low;
			} // end try-catch
			// Indicate to the user why he is being prompted again.
			if (numFromUser < low || numFromUser > high) {
				System.out.println("The number is outside the legal limits.");
			}
		} while (!numericEntryOK || numFromUser < low || numFromUser > high);
		return numFromUser;
	} // end full parameter getInt method


	// This supplied main method uses assumed method names that you may certainly change.
	public static void main(String[] args) {

		long startTime;
		double estimate;
		int numDigitsDesired;

		System.out.printf("Math.PI is:\n%.16f\n\n", Math.PI);

		startTime = System.nanoTime();
		estimate = piCalculatorLeibniz();
		System.out.printf("%.16f - using Leibniz formula with float.\n", estimate);
		timeIterationsReport(startTime);
		accuracyReport(estimate);

		startTime = System.nanoTime();
		estimate = arcTanPiCalculatorFloat();
		System.out.printf("%.16f - using arcTan formula with float.\n", estimate);
		timeIterationsReport(startTime);
		accuracyReport(estimate);

		startTime = System.nanoTime();
		estimate = arcTanPiCalculatorDouble();
		System.out.printf("%.16f - using arcTan formula with double.\n", estimate);
		timeIterationsReport(startTime);
		accuracyReport(estimate);

		startTime = System.nanoTime();
		estimate = arcTanPiCalculatorDoubleModified();
		System.out.printf("%.16f - using Modified arcTan formula with double.\n", estimate);
		timeIterationsReport(startTime);
		accuracyReport(estimate);

		startTime = System.nanoTime();
		estimate = piCalculatorBBP();
		System.out.printf("%.16f - using BBP formula with double.\n", estimate);
		timeIterationsReport(startTime);
		accuracyReport(estimate);

		startTime = System.nanoTime();
		System.out.println("Using BBP formula with BigDecimals for 16 digits:");
		var estimateBigD = piCalculatorBBPBig(16);
		System.out.println(estimateBigD);
		timeIterationsReport(startTime);
		accuracyReport(estimateBigD.doubleValue());

		startTime = System.nanoTime();
		System.out.println("Using BBP formula with BigDecimals for 100 digits:");
		System.out.println(piCalculatorBBPBig(100));
		timeIterationsReport(startTime);
		System.out.println();

		numDigitsDesired = getInt(1000, "How many digits do you want to try for? ", 10000);

		startTime = System.nanoTime();
		System.out.println("Using BBP formula with BigDecimals for " + numDigitsDesired + " digits:");
		displayResult(piCalculatorBBPBig(numDigitsDesired));
		timeIterationsReport(startTime);

		screenInput.close();

	} 

} 