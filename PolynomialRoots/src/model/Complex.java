package model;

public class Complex {

	   private double real;
	   private double imag;

	  public Complex() {
	     real = 0.0;
	     imag = 0.0;
	  }
	  
	  public Complex(double real, double imag){
	     this.real = real;
	     this.imag = imag;
	  }
	  
	  public static Complex conjugate(Complex c){
	     return new Complex(c.real, -c.imag);
	  }
	  
	  public static Complex opposite(Complex c){
	     return new Complex(-c.real, -c.imag);
	  }
	  public double module(){
	     return Math.sqrt( real * real + imag * imag);
	  }
	//returns the angle in degrees
	  public double argument(){
	     double angle = Math.atan2(imag, real); 
	     if(angle < 0)  angle = 2 * Math.PI + angle;
	     return angle * 180 / Math.PI;
	  }
	//sum of two complex numbers
	  public static Complex sum(Complex c1, Complex c2){
	     double x = c1.real + c2.real;
	     double y = c1.imag + c2.imag;
	     return new Complex(x, y);
	  }
	//product of two complex numbers
	 public static Complex product(Complex c1, Complex c2){
	     double x = c1.real * c2.real - c1.imag * c2.imag;
	     double y = c1.real * c2.imag + c1.imag * c2.real;
	     return new Complex(x, y);
	  }
	//product of a complex by a real number
	  public static Complex product(Complex c, double d){
	     double x = c.real * d;
	     double y = c.imag * d;
	     return new Complex(x, y);
	 }
	//product of a real number by a complex
	  public static Complex product(double d, Complex c){
	     double x = c.real * d;
	     double y = c.imag * d;
	     return new Complex(x, y);
	 }
	//cociente de dos números complejos
	//excepción cuando el complejo denominador es cero
	  public static Complex quotient(Complex c1, Complex c2)
	    throws DivisionByZeroException {
	     double aux, x, y;
	     if(c2.module() == 0.0){
	          throw new DivisionByZeroException();
	     } else {
	          aux = c2.real * c2.real + c2.imag * c2.imag;
	          x = (c1.real * c2.real + c1.imag * c2.imag) / aux;
	          y = (c1.imag * c2.real - c1.real * c2.imag) / aux;
	     }
	     return new Complex(x, y);
	  }
	//quotient between a complex number and a real number
	  public static Complex quotient(Complex c, double d)
	     throws DivisionByZeroException{
	    double x, y;
	    if(d == 0.0){
	          throw new DivisionByZeroException();
	    } else {
	        x = c.real/d;
	        y = c.imag/d;
	    }
	     return new Complex(x, y);
	  }
	//the number raised to a complex number
	  public static Complex exponential(Complex c){
	     double x = Math.cos(c.imag) * Math.exp(c.real);
	     double y = Math.sin(c.imag) * Math.exp(c.real);
	     return new Complex(x, y);
	  }
	//square root of a positive or negative number
	  public static Complex csqrt(double d){
	     if(d >= 0) return new Complex(Math.sqrt(d), 0);
	     return new Complex(0, Math.sqrt(-d));
	  }
	//auxiliary function for the power of a complex number
	  private static double potency(double base, int exponent){
	    double resultado = 1.0;
	    for(int i = 0; i < exponent; i++){
	        resultado *= base;
	    }
	    return resultado;
	  }
	//auxiliary function for the power of a complex number
	  private static double combinatorial(int m, int n){
	    long num = 1;
	    long den = 1;
	    for(int i = m; i > m - n; i--){
	        num *= i;
	    }
	    for(int i = 2; i <= n; i++){
	        den*=i;
	    }
	    return (double) num / den;
	  }
	//potency of a complex number
	  public static Complex potency(Complex c, int exponent){
	    double x = 0.0, y = 0.0;
	    int sign;
	    for(int i = 0; i <= exponent; i++){
	        sign = (i % 2 == 0)? + 1: -1;
	//real part
	        x += combinatorial(exponent, 2 * i) * potency(c.real, exponent - 2*i)
	           * potency(c.imag, 2*i) * sign;
	        if(exponent ==2*i)  break;
	//imaginary part 
	        y += combinatorial(exponent, 2*i+1) * potency(c.real, exponent-(2*i+1))
	           * potency(c.imag, 2*i+1) * sign;
	    }
	    return new Complex(x, y);
	  }
	//represents a complex number as a string
	  public String toString(){
	     if(imag>0)     return new String((double)Math.round(100 * real)/100
	         + " + " + (double)Math.round(100 * imag) / 100 + "*i");
	     return new String((double)Math.round(100 * real) / 100 + " - "
	        + (double)Math.round(-100 * imag) / 100 + "*i");
	  }
	
}
