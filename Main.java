import java.util.*;
class BinomialDistribution{
    /*Class to get binomial distribution percentage in each case, we can use the values obtained to
     * obtain each column of the binomial distribution graph*/
    double binomial;
    double []array = new double[1000];
    double fact_n=1,fact_r=1,fact_nr=1;
    double sum_fair=0,sum_cheat=0;
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
            binomial = (fact_n/(fact_r*fact_nr))*(Math.pow(prob,r)*Math.pow((1-prob),(n-r)));//heart and soul of the
            // program, the binomial distribution formula
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
    int probability(BinomialDistribution binfair,BinomialDistribution bincheat,double fair,double cheater){
        int flag=0;
        for(int i=binfair.array.length-1;i>0;i--){
            sum_fair += binfair.array[i];
            sum_cheat+=bincheat.array[i];
            if(sum_fair>fair || sum_cheat>cheater){
                break;
            }else if(sum_fair<=fair && sum_cheat<=cheater){
                //System.out.println("We need to do at least "+i+" out of "+binfair.array.length+" tosses");
                flag=1;
                break;
            }
        }
        return flag;
    }
}
public class Main {
    public static void main(String[] args) {
        int flag=0,val = 0;
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
        /*int val = 0;
        for(int f=0;i<=5;i++){
            binfair[i] = new BinomialDistribution(val,0.5);
            val++;
        }
        binfair[4].Display(10);*/
        for(int i=0; flag!=1;i++){
            binfair[i] = new BinomialDistribution(val,0.5);
            bincheat[i] = new BinomialDistribution(val,percentage);
            flag = binfair[i].probability(binfair[i],bincheat[i],fair,cheater);
            //problem here is that I can't way of terminating the program without
            //a return value, but that prevents text from being displayed....or is that the case???
            //checking would be useful
        }

    }
}
