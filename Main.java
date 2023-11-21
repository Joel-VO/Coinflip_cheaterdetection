import java.util.*;
class BinomialDistribution{
    /*Class to get binomial distribution percentage in each case, we can use the values obtained to
     * obtain each column of the binomial distribution graph*/
    double binomial;
    double []array = new double[100000];
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
            binomial = (fact_n/(fact_r*fact_nr))*(Math.pow(prob,r)*Math.pow((1-prob),(n-r)));//binomial distribution formula
            array[r] = binomial;
            fact_n=1;
            fact_r=1;
            fact_nr=1;
        }
    }
    void Display(int n){//displays binomial distribution for a particular value of n
        for(int i = 0;i<=n;i++){
            System.out.print(" "+array[i]);
        }
    }
    double SliderValue(int max,int val){//returns sum of probabilities for a max value i and a min value j
        //determined by the main method
        double flag=0;
        for(int i = max+1;val>=0;i--){
            flag+=array[i];
            val--;
        }
        return flag;
    }
}
public class Main {
    public static void main(String[] args) {
        //enter all percentages in the run program in terms of decimals,ie; 60% would be 0.6 and so on
        int flag = 0;
        double fair_sum,cheat_sum;
        BinomialDistribution[] binfair = new BinomialDistribution[100000];
        BinomialDistribution[] bincheat = new BinomialDistribution[100000];
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the acceptable percentage of fair players labelled as cheaters: ");
        double fair = sc.nextDouble();
        System.out.println("Enter the acceptable percentage of cheaters caught: ");
        double cheater = sc.nextDouble();
        System.out.println("Enter the percentage chance the cheaters coin comes up heads: ");
        double percentage = sc.nextDouble();
        // do the binfair array declaration, then assign value using method ArrayDeclaration and then do a test to see if
        //array is displayed
        System.out.println("Calculating...");
        for(int i = 0;flag==0;i++){//
            binfair[i] = new BinomialDistribution(i,0.5);
            bincheat[i] = new BinomialDistribution(i,percentage);
            for(int j = 0;j<=i;j++) {
                fair_sum = binfair[i].SliderValue(i, j);//takes sum of terms from the right most end and works its way towards the start of the array
                cheat_sum = bincheat[i].SliderValue(i, j);
                System.out.println(" "+i+" "+(i-j+1));
                if (fair_sum <= fair && cheat_sum >= cheater) {// checks if the condition for max fair players falsely accused and minimum cheaters caught
                    System.out.println("A minimum of " + (i-j+1) + " heads out of " + (i) + " are required to make a decent assumption that someone is cheating.");
                    flag = 1;
                    break;
                }
            }
            binfair[i] = null;
            bincheat[i] = null;
        }
    }
}
