import java.math.BigDecimal;
import java.util.Scanner;

class BinomialDistribution {
    BigDecimal[] probabilities; // Array to store the probabilities
    int trials; // Number of trials
    BigDecimal probability; // Probability of success

    // Constructor
    BinomialDistribution(int trials, double probability) {
        this.trials = trials;
        this.probability = BigDecimal.valueOf(probability);
        this.probabilities = new BigDecimal[trials + 1]; // Initialize the probabilities array
        calculateProbabilities(); // Calculate the probabilities
    }

    // Method to calculate the probabilities
    private void calculateProbabilities() {
        for (int successes = 0; successes <= trials; successes++) {
            BigDecimal failureProbability = BigDecimal.ONE.subtract(probability); // Probability of failure
            int failures = trials - successes; // Number of failures
            BigDecimal combination = calculateCombination(trials, successes); // Calculate the combination
            // Calculate the probability using the binomial distribution formula
            probabilities[successes] = combination.multiply(probability.pow(successes)).multiply(failureProbability.pow(failures));
        }
    }

    // Method to calculate the combination (nCr)
    private BigDecimal calculateCombination(int n, int r) {
        BigDecimal result = BigDecimal.ONE;
        for (int i = 1; i <= r; i++) {
            result = result.multiply(BigDecimal.valueOf(n - i + 1)).divide(BigDecimal.valueOf(i));
        }
        return result;
    }

    // Method to display the probabilities
    void display() {
        for (BigDecimal probability : probabilities) {
            System.out.print(" " + probability);
        }
    }

    // Method to calculate the sum of probabilities for a given range
    BigDecimal sliderValue(int max, int val) {
        BigDecimal sum = BigDecimal.ZERO;
        for (int i = max; i >= val; i--) {
            sum = sum.add(probabilities[i]);
        }
        return sum;
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // Prompt the user to enter the acceptable percentage of fair players labelled as cheaters
        System.out.println("Enter the acceptable percentage of fair players labelled as cheaters: ");
        double fair = sc.nextDouble();
        // Prompt the user to enter the acceptable percentage of cheaters caught
        System.out.println("Enter the acceptable percentage of cheaters caught: ");
        double cheater = sc.nextDouble();
        // Prompt the user to enter the percentage chance the cheaters coin comes up heads
        System.out.println("Enter the percentage chance the cheaters coin comes up heads: ");
        double percentage = sc.nextDouble();

        System.out.println("Calculating...");
        for (int i = 0; ; i++) {
            // Calculate the binomial distribution for fair and cheating players
            BinomialDistribution binfair = new BinomialDistribution(i, 0.5);
            BinomialDistribution bincheat = new BinomialDistribution(i, percentage);
            for (int j = 0; j <= i; j++) {
                // Calculate the sum of probabilities for a given range
                BigDecimal fair_sum = binfair.sliderValue(i, j);
                BigDecimal cheat_sum = bincheat.sliderValue(i, j);
                // Check if the condition for max fair players falsely accused and minimum cheaters caught is met
                if (fair_sum.doubleValue() <= fair && cheat_sum.doubleValue() >= cheater) {
                    System.out.println("A minimum of " + j + " heads out of " + i + " are required to make a decent assumption that someone is cheating.");
                    return;
                }
            }
        }
    }
}
