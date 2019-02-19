/**
 * 
 */
package model;

/**
 * @author Sebastian
 *
 */


public class Graeffe {
	
	private static final double ZERO = 0.0001;
	
    public int n; 
    public double[] realRoots;
    public Complex[] complexRoots = new Complex[4]; 
    public int numReal; 
    public int numComplex;
    private double[][] a;
    private int pot2 = 1;
    private int m;
    private final int MAX_ITER=10; 
    
    private double[] complexModules = new double[2];

    public Graeffe() {
    	
    }
    
    public Graeffe(double[] coefficients) {
        n = coefficients.length-1; 
        realRoots = new double[n]; 
        a = new double[MAX_ITER][n+1];
        
        //The first row of the table maintains the polynomial coefficients.
        for(int j = 0; j < n+1; j++){
            a[0][j] = coefficients[j];
        }
        
        for(int m=1; m<MAX_ITER; m++){
            for(int j=0; j<n+1; j++){
                a[m][j]=0.0;
            }
        }
        numReal=0;
        numComplex=0;
    }

    private void board(){
        int k,l, signo;
        //divide the polynomial by the first coefficient, the roots do not change
        
        for(int i = 1; i < n+1; i++){
            a[0][i]/= a[0][0];
        }
        a[0][0] = 1.0;
        m = 1;
        exterior:
        do{
        	//square
            for(int i = 0; i < n+1; i++){
                a[m][i] = a[m-1][i] * a[m-1][i];
                if(Double.isInfinite(a[m][i])){
                    break exterior;
                }
            }
            //Double products
            for(int i = 1; i < n; i++){
                for(int s = 1; s < n/2+1; s++){
                    k = i - s;      
                    l = i + s;
                    if((k < 0) || (l > n))    break;   //symmetrical terms
                    signo = (s % 2 == 0) ? + 1: -1;
                    a[m][i] += signo * 2 * a[m-1][k] *a[m-1][l];
                    
                    if(Double.isInfinite(a[m][i])){
                        break exterior;
                    }
                }
            }
            m++;
            
        }while(m<MAX_ITER);

        m--;
        //potency of m of 2
        pot2 = 1;
        for(int i = 1; i <= m; i++){
            pot2 *= 2;
        }
    }
    //value of a polynomial for a real variable
    public double polynomialValue(double x){
        double y = 0.0;
        //Successive potency of x, you can also use the Math.pow function
        double[] pot_x = new double[n+1];
        pot_x[0] = 1.0;
        for(int i = 1; i < n+1; i++){
            pot_x[i] = pot_x[i-1] * x;
        }
        //Values ​​of the successive terms.
        for(int i = 0 ; i < n + 1; i++){
            y += a[0][i] * pot_x[n-i];
        }
        return y;
    }
    public Complex polynomialValue(Complex x){
        Complex y = new Complex();
        for(int i = 0; i < n + 1; i++){
            y = Complex.sum(y, Complex.product(a[0][i], 
               Complex.potency(x, (n-i))));
        }
        return y;
    }

    private void simpleRealRoot(int j){
    	//absolute value of the root
       // System.out.println("Simple root");
        double logarithm = (Math.log(a[m][j]) - Math.log(a[m][j-1])) / pot2;
        double raiz = Math.exp(logarithm);
        //determination of the sign , y1 o y2 have to be almost ZERO

       realRoots[numReal] = (Math.abs(polynomialValue(raiz))<
              Math.abs(polynomialValue(-raiz)))? raiz : -raiz;
        numReal++;
    }

    private void doubleRealRoot(int j){
    	//absolute value of the root
        double logarithm = (Math.log(a[m][j+1]) - Math.log(a[m][j-1])) / (2 * pot2);
        double raiz = Math.exp(logarithm);
        boolean bPositive = false, bNegative = false;
        if (Math.abs(polynomialValue(raiz))<ZERO){
           realRoots[numReal] = raiz;
           numReal++;
           bPositive = true;
        }
        if (Math.abs(polynomialValue(-raiz))<ZERO){
           realRoots[numReal] = -raiz;
           numReal++;
           bNegative=true;
        }
        if(bPositive && !bNegative){
          realRoots[numReal] = raiz;
          numReal++;
        }
        if(!bPositive && bNegative){
          realRoots[numReal] = -raiz;
          numReal++;
        }
    }

    private void aComplexRoot(){
        double sum = 0.0;
        for(int i = 0; i < numReal; i++){
            sum += realRoots[i];
        }
        double u, v;
        u =- (a[0][1] + sum) / 2;
        v = Math.sqrt(complexModules[0] * complexModules[0] -u * u);
        complexRoots[0] = new Complex(u, v);
        complexRoots[1] = new Complex(u, -v);
    }
    private void twoComplexRoots(){
        double sum = 0.0;
        double product = 1.0;
        double inverse = 0.0;
        for(int i = 0; i < numReal; i++){
            sum += realRoots[i];
            product *= realRoots[i];
            inverse += 1 / realRoots[i];
        }
        double r1 = complexModules[0];
        double r2 = complexModules[1];
        double y =- (a[0][1]+sum) / 2;
        int sign = ((n-1) % 2 == 0)? + 1: -1;
        double z = sign * a[0][n-1] / (2 * product) -r1 * r1 * r2 * r2 * inverse / 2;
        double u1 = (y * r1 * r1 - z) / (r1 * r1 - r2 * r2);
        double u2 = (-y * r2 * r2 + z) / (r1 * r1 - r2 * r2);
        double v1 = Math.sqrt(r1 * r1 - u1 * u1);
        double v2 = Math.sqrt(r2 * r2 - u2 * u2);
        complexRoots[0] = new Complex(u1, v1);
        complexRoots[1] = new Complex(u1, -v1);
        complexRoots[2] = new Complex(u2, v2);
        complexRoots[3] = new Complex(u2, -v2);
    }

    private boolean changeSign(int j){
        double logarithm;
        for(int k = 2; k <= m; k++){
            if(a[k][j]>0)	continue;
            numComplex++;
            //maximum two complex roots, 4 counting their respective conjugates
            if(numComplex < 3){
            	logarithm = (Math.log(a[m][j+1]) - Math.log(a[m][j-1])) / (2 * pot2);
                complexModules[numComplex-1] = Math.exp(logarithm);
                return true;
            }
        }
        return false;
    }

    public void findRoots(){
        board();
        //the first coefficient a[m][0] It is always 1
        for(int i = 1; i < n + 1; i++){      // is the root
            if(changeSign(i)){
            	//complex root and its corresponding conjugate
                i++;
                continue;
            }
            //single and double roots
            double logarithm = Math.log(a[m][i]) -2 * Math.log(a[m-1][i]);
            if(Math.abs(logarithm) < ZERO){
                simpleRealRoot(i);
            }else{
                doubleRealRoot(i);
                i++;
                continue;
            }
        }
        if(numComplex == 1){
            aComplexRoot();
        }
        if(numComplex==2){
        	twoComplexRoots();
        }
     }


    public void showRoots(){
    	findRoots();
    	//real Roots
        System.out.println("Real roots");
        for(int i = 0; i < numReal; i++){
            System.out.println((double)Math.round(realRoots[i] * 100) / 100 + " ---> " + polynomialValue(realRoots[i]));
        }
        System.out.println("");
        //complex roots
        System.out.println("Complex roots");
        for(int i = 0; i < numComplex; i++){
            System.out.println(complexRoots[2*i] + " --->     " + polynomialValue(complexRoots[2*i]));
            System.out.println(complexRoots[2*i+1] + " --->  " + polynomialValue(complexRoots[2*i]));
        }
        System.out.println("");
    }
}