package Stats12U;
import java.util.Scanner;

public class SimpleLinearRegression {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);

		// Input x and y values
		System.out.println("How many x and y values do you have?");
		int n = input.nextInt();
		double[] x = new double[n];
		double[] y = new double[n];
		double[] yt = new double[n];
		System.out.println("Enter x values:");
		for (int i = 0; i < n; i++) {
			x[i] = input.nextDouble();
		}
		System.out.println("Enter y values:");
		for (int i = 0; i < n; i++) {
			y[i] = input.nextDouble();
		}
		System.out.println("Enter true y values:");
		for (int i = 0; i < n; i++) {
			yt[i] = input.nextDouble();
		}


		double MSE = MSEcalc(n,y,yt);
		boolean[] newY = outlierFinder(n,y,yt,MSE);

		// Calculate alpha and beta coefficients
		double xSum = 0, ySum = 0, xySum = 0, xSquaredSum = 0;
		for (int i = 0; i < n; i++) {
			if(newY[i] == false) {
				xSum += x[i];
				ySum += y[i];
				xySum += x[i] * y[i];
				xSquaredSum += x[i] * x[i];
			}else {
				n--; 
			}
		}
		double beta = (n * xySum - xSum * ySum) / (n * xSquaredSum - xSum * xSum);
		double alpha = (ySum - beta * xSum) / n;

		// Output alpha and beta coefficients
		System.out.println("alpha = " + alpha);
		System.out.println("beta = " + beta);

		// Predict y for a given x value
		System.out.println("Enter an x value to predict its corresponding y value:");
		double xPred = input.nextDouble();
		double yPred = alpha + beta * xPred;
		System.out.println("The predicted y value is: " + yPred);
	}

	private static boolean[] outlierFinder(int n, double[] y, double[] yt, double mSE) {
		// TODO Auto-generated method stub
		boolean[] arr = new boolean[n]; 
		for(int i = 0;i<n;i++) {
			if((yt[i] - y[i])*(yt[i] - y[i])< mSE){
				arr[i] = false; 
			}else {
				arr[i] = true;
			}
		}
		return arr;
	}

	private static double MSEcalc(int n,double[]y,double[]yt) {
		double finalVal = 0;
		for(int i = 0;i<n;i++) {
			finalVal =  finalVal + (yt[i] - y[i])*(yt[i] - y[i]); 
		}
		finalVal = finalVal/n; 
		return finalVal;

	}


}