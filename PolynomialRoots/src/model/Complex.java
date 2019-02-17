package model;

public class Complex {

	   private double real;
	   private double imag;

	  public Complex() {
	     real=0.0;
	     imag=0.0;
	  }
	  public Complex(double real, double imag){
	     this.real=real;
	     this.imag=imag;
	  }
	  public static Complex conjugado(Complex c){
	     return new Complex(c.real, -c.imag);
	  }
	  public static Complex opuesto(Complex c){
	     return new Complex(-c.real, -c.imag);
	  }
	  public double modulo(){
	     return Math.sqrt(real*real+imag*imag);
	  }
	//devuelve el ángulo en grados
	  public double argumento(){
	     double angulo=Math.atan2(imag, real); 
	     if(angulo<0)  angulo=2*Math.PI+angulo;
	     return angulo*180/Math.PI;
	  }
	//suma de dos números complejos
	  public static Complex suma(Complex c1, Complex c2){
	     double x=c1.real+c2.real;
	     double y=c1.imag+c2.imag;
	     return new Complex(x, y);
	  }
	//producto de dos números complejos
	 public static Complex producto(Complex c1, Complex c2){
	     double x=c1.real*c2.real-c1.imag*c2.imag;
	     double y=c1.real*c2.imag+c1.imag*c2.real;
	     return new Complex(x, y);
	  }
	//producto de un complejo por un número real
	  public static Complex producto(Complex c, double d){
	     double x=c.real*d;
	     double y=c.imag*d;
	     return new Complex(x, y);
	 }
	//producto de un número real  por un complejo
	  public static Complex producto(double d, Complex c){
	     double x=c.real*d;
	     double y=c.imag*d;
	     return new Complex(x, y);
	 }
	//cociente de dos números complejos
	//excepción cuando el complejo denominador es cero
	  public static Complex cociente(Complex c1, Complex c2)
	    throws DivisionByZeroException{
	     double aux, x, y;
	     if(c2.modulo()==0.0){
	          throw new DivisionByZeroException();
	     }else{
	          aux=c2.real*c2.real+c2.imag*c2.imag;
	          x=(c1.real*c2.real+c1.imag*c2.imag)/aux;
	          y=(c1.imag*c2.real-c1.real*c2.imag)/aux;
	     }
	     return new Complex(x, y);
	  }
	//cociente entre un número complejo y un número real
	  public static Complex cociente(Complex c, double d)
	     throws DivisionByZeroException{
	    double x, y;
	    if(d==0.0){
	          throw new DivisionByZeroException();
	    }else{
	        x=c.real/d;
	        y=c.imag/d;
	    }
	     return new Complex(x, y);
	  }
	//el número e elevado a un número complejo
	  public static Complex exponencial(Complex c){
	     double x=Math.cos(c.imag)*Math.exp(c.real);
	     double y=Math.sin(c.imag)*Math.exp(c.real);
	     return new Complex(x, y);
	  }
	//raíz cuadrada de un número positivo o negativo
	  public static Complex csqrt(double d){
	     if(d>=0) return new Complex(Math.sqrt(d), 0);
	     return new Complex(0, Math.sqrt(-d));
	  }
	//función auxiliar  para la potencia de un número complejo
	  private static double potencia(double base, int exponente){
	    double resultado=1.0;
	    for(int i=0; i<exponente; i++){
	        resultado*=base;
	    }
	    return resultado;
	  }
	//función auxiliar para la potencia de un número complejo
	  private static double combinatorio(int m, int n){
	    long num=1;
	    long den=1;
	    for(int i=m; i>m-n; i--){
	        num*=i;
	    }
	    for(int i=2; i<=n; i++){
	        den*=i;
	    }
	    return (double)num/den;
	  }
	//potencia de un número complejo
	  public static Complex potencia(Complex c, int exponente){
	    double x=0.0, y=0.0;
	    int signo;
	    for(int i=0; i<=exponente; i++){
	        signo=(i%2==0)?+1:-1;
	//parte real
	        x+=combinatorio(exponente, 2*i)*potencia(c.real, exponente-2*i)
	           *potencia(c.imag, 2*i)*signo;
	        if(exponente==2*i)  break;
	//parte imaginaria
	        y+=combinatorio(exponente, 2*i+1)*potencia(c.real, exponente-(2*i+1))
	           *potencia(c.imag, 2*i+1)*signo;
	    }
	    return new Complex(x, y);
	  }
	//representa un número complejo como un string
	  public String toString(){
	     if(imag>0)     return new String((double)Math.round(100*real)/100
	         +" + "+(double)Math.round(100*imag)/100+"*i");
	     return new String((double)Math.round(100*real)/100+" - "
	        +(double)Math.round(-100*imag)/100+"*i");
	  }
	

}
