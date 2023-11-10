import java.util.*;
class BinomialDistribution{
    /*Class to get binomial distribution percentage in each case, we can use the values obtained to
    * obtain each column of the binomial distribution graph*/
    double binomial;
    double fact_n=1,fact_r=1,fact_nr=1;
    BinomialDistribution(int n,int r,double prob){
        for(int i=1;i<=n;i++){
            fact_n*=i;
        }
        for(int j=1;j<=r;j++){
            fact_r*=j;
        }
        for(int k=1;k<=(n-r);k++){
            fact_nr*=k;
        }
        binomial = (fact_n/(fact_r*fact_nr))*(Math.pow(prob,r)*Math.pow((1-prob),(n-r)));
    }
}
public class Main {
    public static void main(String[] args) {
        //BinomialDistribution bin = new BinomialDistribution();
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the acceptable percentage of fair players labelled as cheaters: ");
        double fair = sc.nextDouble();
        System.out.println("Enter the acceptable percentage of cheaters caught: ");
        double cheater = sc.nextDouble();
    }
}