import java.util.*;
class BinomialDistribution{
    /*Class to get binomial distribution percentage in each case, we can use the values obtained to
     * obtain each column of the binomial distribution graph*/
    double binomial;
    double []array = new double[1000];
    double fact_n=1,fact_r=1,fact_nr=1;
    BinomialDistribution(int n,double prob){
        for(int r=0;r<=n;r++) {
            //if statements used to prevent division by 0
            if(n!=0){//for the n'th term
                for(double i=0;i<=n;i++){
                    if(i!=0){
                        fact_n*=i;
                    }
                }
            }
            if(r!=0){//for the r'th term
                for (double j=0; j<=r;j++) {
                    if(j!=0){
                        fact_r *= j;
                    }
                }
            }
            if((n-r)!=0) {//for the (n-r)'th term
                for(double k=0;k<=(n-r);k++){
                    if(k!=0) {
                        fact_nr *= k;
                    }
                }
            }
            binomial = (fact_n/(fact_r*fact_nr))*(Math.pow(prob,r)*Math.pow((1-prob),(n-r)));//heart and soul of the program, the binomial distribution formula
            array[r] = binomial;
            fact_n=1;
            fact_r=1;
            fact_nr=1;
        }
    }
    void Display(int n){
        for(int i = 0;i<n;i++){
            System.out.println(array[i]);
        }
    }
    void probability(BinomialDistribution binfair,BinomialDistribution bincheat){
        //do the slider check here and then return value which is suppose is preferable 
    }
}
public class Main {
    public static void main(String[] args) {
        BinomialDistribution[] binfair = new BinomialDistribution[1000];
        BinomialDistribution[] bincheat = new BinomialDistribution[1000];
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the acceptable percentage of fair players labelled as cheaters: ");
        double fair = sc.nextDouble();
        System.out.println("Enter the acceptable percentage of cheaters caught: ");
        double cheater = sc.nextDouble();
        System.out.println("Enter the percentage chance the cheaters coin comes up heads: ");
        double percentage = sc.nextDouble();
        // do the binfair array declaration, then assign value using method ArrayDeclaration and then do a test to see if
        //array is displayed
        /*int val = 2;
        for(int i=0;i<=5;i++){
            binfair[i] = new BinomialDistribution(val,0.5);
            val++;
        }
        binfair[5].Display(8);*/
    }
}
