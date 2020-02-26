public class Fraction {

	private int denominator, numerator;

	public Fraction(int numerator, int denominator) {

		//Throwing an error if the denominator is 0.
		if (denominator == 0) {
			throw new IllegalArgumentException("Denominator cannot be zero!");
		}
		//When both numbers are negative
		if (denominator < 0 && numerator < 0) {
			denominator *= -1;
			numerator *= -1;
		}
		//When the numerator is negative
		if (denominator < 0 && numerator > 0) {
			denominator *= -1;
			numerator *= -1;
		}

		this.denominator = denominator;
		this.numerator = numerator;
	}

	public Fraction(int numerator) {
		this.numerator = numerator;
		this.denominator = 1;
	}

	public Fraction() {
		this.numerator = 0;
		this.denominator = 1;
	}


	//GETTERS
	public int getDenominator(){
		return denominator;
	}
	public int getNumerator(){
		return numerator;
	}

	//"numerator/denominator", a String representation of the Fraction
	public String toString(){
		if(denominator == 1){
			return String.valueOf(numerator);
		}else{
			return (numerator + "/"+ denominator);
		}

	}

	//the result of numerator / denominator
	public double toDouble(){
		return (double)numerator/denominator;
	}


	//returns a new Fraction that is the sum of other and this fractions
	public Fraction add(Fraction otherFraction){
		int up = (numerator * otherFraction.getDenominator()) + (otherFraction.getNumerator() * denominator);
		int bottom = denominator * otherFraction.getDenominator();
		return new Fraction(up, bottom);
	}

	//returns a new Fraction that is the difference between the other and this fraction
	public Fraction subtract(Fraction otherFraction){
		int up = (numerator * otherFraction.getDenominator()) - (otherFraction.getNumerator() * denominator);
		int bottom = denominator * otherFraction.getDenominator();
		return new Fraction(up, bottom);
	}

	//returns a new Fraction that is the product of the other and this fraction
	public Fraction multiply(Fraction otherFraction){
		int up = numerator * otherFraction.getNumerator();
		int bottom = denominator * otherFraction.getDenominator();
		Fraction frac = new Fraction(up, bottom);
		frac.toLowestTerms();
		return frac;
	}

	//returns a new Fraction that is the division of the other and this fraction, throw an IllegalArgumentException() if the user asks you to divide by 0
	public Fraction divide(Fraction otherFraction){
		int up = numerator * otherFraction.getDenominator();
		int bottom = denominator * otherFraction.getNumerator();
		if (denominator == 0 || otherFraction.denominator ==0) {
			throw new IllegalArgumentException("Denominator cannot be zero!");
		}
		Fraction frac = new Fraction(up, bottom);
		frac.toLowestTerms();
		return frac;
	}

	//converts the current fraction to the lowest terms
	public void toLowestTerms() {
		int reminder = 0, gcd = 0;
		int up = numerator, bottom = denominator;

		while (up != 0 && bottom != 0) {
			reminder = up % bottom;
			up = bottom;
			bottom = reminder;
			gcd = up;
		}
		numerator /= gcd;
		denominator /= gcd;
	}

	@Override
//must take in an "Object" to properly override the Object class's equals method, but should ultimately check if two fractions are equal
	public boolean equals(Object obj) {

		// If the object is compared with itself then return true
		if(obj == this){
			return true;
		}

    /* Check if o is an instance of Fraction or not
      "null instanceof [type]" also returns false */
		if (!(obj instanceof Fraction)) {
			return false;
		}

		//Objects are created for comparing in their lowest possible values. Otherwise, we would adjust their values
		// by using 'this'.
		Fraction compareObject = new Fraction(this.getNumerator(), this.getDenominator());
		compareObject.toLowestTerms();
		Fraction compareObject2 = new Fraction(((Fraction) obj).getNumerator(), ((Fraction) obj).getDenominator());
		compareObject2.toLowestTerms();

		// Compare the data members and return accordingly
		return (compareObject.getNumerator()== compareObject2.getNumerator() && compareObject.getDenominator() == compareObject2.getDenominator());
	}

	//takes in two ints and determines the greatest common divisor of the two ints.
	public static int gcd(int num1, int num2){
		while (num1 != num2) {
			if(num1 > num2)
				num1 = num1 - num2;
			else
				num2 = num2 - num1;
		}
		return num2;
	}
}