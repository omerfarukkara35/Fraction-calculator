import java.util.Scanner;
public class FractionCalculation {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		introductionMethod();
		String operation = "";

	while(true){
		System.out.println("-----------------------------------------------------");
		operation = getOperation(scanner);
		if(operation.equalsIgnoreCase("q")){
			break;
		}
		Fraction frac1 = getFraction(scanner);
		Fraction frac2 = getFraction(scanner);
		switch (operation) {
			case "+":
				System.out.println(frac1.toString() + " " + operation + " " + frac2 + " = " + frac1.add(frac2));
				break;
			case "-":
				System.out.println(frac1.toString() + " " + operation + " " + frac2 + " = " + frac1.subtract(frac2));
				break;
			case "/":
				System.out.println(frac1.toString() + " " + operation + " " + frac2 + " = " + frac1.divide(frac2));
				break;
			case "*":
				System.out.println(frac1.toString() + " " + operation + " " + frac2 + " = " + frac1.multiply(frac2));
				break;
			case "=":
				System.out.println(frac1.toString() + " " + operation + " " + frac2 + " is " + frac1.equals(frac2));
				break;
		}
	}
}

	//Asks the user to enter in a valid mathematical operation. If the user enters anything except "+", ";
	public static String getOperation(Scanner scanner) {
		char mathOperation;
		System.out.print("Please enter an operation (+, -, /, *, = or Q to quit): ");
		while (true) {
			mathOperation = scanner.next().toUpperCase().charAt(0);
			String op = String.valueOf(mathOperation);
			switch (mathOperation) {
				case '+':
				case '-':
				case '/':
				case '*':
				case '=':
				case 'Q':
					return op;
				default:
					System.out.print("Invalid input (+, -, /, *, = or Q to quit): ");
			}
		}
	}

	//Validity of a fraction, it should be in the form of a/b where and b any possible integers.
	public static boolean validFraction(String otherFraction) {

		//Removing the "-" sign with StringBuilder.deleteChatAt("String") method.
		StringBuilder modifiedFraction = new StringBuilder(otherFraction);
		for (int i = 0; i < modifiedFraction.length(); i++) {
			if (modifiedFraction.charAt(i) == '-') {
				modifiedFraction.deleteCharAt(i);
			}
		}

		String denominator = "", numerator = "";
		int indexOfSlash;
		indexOfSlash = getIndexOfSlash(modifiedFraction);
		if(indexOfSlash == 0 && isNumber(modifiedFraction)){
			return true; // in case of "a" number.

		}else if(indexOfSlash !=0) {
			for (int i = 0; i < modifiedFraction.length(); i++) {
				if (modifiedFraction.charAt(i) == '/') {
					numerator = modifiedFraction.substring(0, indexOfSlash - 1);
					denominator = modifiedFraction.substring(indexOfSlash);
					if(denominator.equalsIgnoreCase("0")){
						System.out.println("Denominator cannot be zero!");
						return false;
					}
					break;
				}
			}
			//Because they are StringBuilder, we need to create an isNumber overload method which takes StringBuilders.
			if(isNumber(numerator) && isNumber(denominator)){
				return true;
			}else{
				return false;
			}
		}
		return false;
	}


	/*  It prompts the user for a String that is a validFraction. If they enter any thing that is not a valid Fraction,
	 it should re-prompt them until it is valid */
	public static Fraction getFraction(Scanner scan) {

		System.out.print("Please enter a fraction (a/b) or integer (a): ");
		while (true) {
			String fraction = scan.next();
			if (validFraction(fraction)) {

				StringBuilder modifiedFraction = new StringBuilder(fraction);

				String denominator, numerator;
				int indexOfSlash = getIndexOfSlash(modifiedFraction);

				if (indexOfSlash != 0) {
					numerator = modifiedFraction.substring(0, indexOfSlash - 1);
					denominator = modifiedFraction.substring(indexOfSlash);
					return new Fraction(Integer.parseInt(numerator), Integer.parseInt(denominator));
				}else if(indexOfSlash == 0){
					numerator = modifiedFraction.substring(0);
					return new Fraction(Integer.parseInt(numerator));
				}

			}else{
				System.out.print("Invalid fraction. Please enter (a/b) or (a), where a and b are integers and b is " +
						"not zero: ");
			}
		}
	}


	//Finds the slash of the string and return its value.
	public static int getIndexOfSlash(StringBuilder fraction) {
		for (int i = 0; i < fraction.length(); i++) {
			if (fraction.charAt(i) == '/') {
				return (i + 1);
			}
		}
		return 0;
	}


	//Making sure that every substring has digits in them, otherwise return false.
//There are two isNumber method overloaded because of the StringBuilder.
	public static boolean isNumber(StringBuilder fraction) {
		for (int i = 0; i < fraction.length(); i++) {
			if (!Character.isDigit(fraction.charAt(i))) {
				return false;
			}
		}
		return true;
	}
	public static boolean isNumber(String fraction) {
		for (int i = 0; i < fraction.length(); i++) {
			if (!Character.isDigit(fraction.charAt(i))) {
				return false;
			}
		}
		return true;
	}

	public static void introductionMethod(){
		System.out.println("This program is a fraction calculator");
		System.out.println("It will add, subtract, multiply, and divide fractions until you type Q to quit.");
		System.out.println("Please enter your fractions in the from a/b, where a and b are integers.");
	}
}