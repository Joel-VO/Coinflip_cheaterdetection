import java.util.*;

class BinomialDistribution {
    double[] probabilities; // Array to store the probabilities
    int trials; // Number of trials
    double probability; // Probability of success

    // Constructor
    BinomialDistribution(int trials, double probability) {
        this.trials = trials;
        this.probability = probability;
        this.probabilities = new double[trials + 1]; // Initialize the probabilities array
        calculateProbabilities(); // Calculate the probabilities
    }

    // Method to calculate the probabilities
    private void calculateProbabilities() {
        for (int successes = 0; successes <= trials; successes++) {
            double failureProbability = 1 - probability; // Probability of failure
            int failures = trials - successes; // Number of failures
            double combination = calculateCombination(trials, successes); // Calculate the combination
            // Calculate the probability using the binomial distribution formula
            probabilities[successes] = combination * Math.pow(probability, successes) * Math.pow(failureProbability, failures);
        }
    }

    // Method to calculate the combination (nCr)
    private double calculateCombination(int n, int r) {
        return factorial(n) / (factorial(r) * factorial(n - r));
    }

    // Method to calculate the factorial of a number
    private double factorial(int n) {
        double result = 1;
        for (int i = 1; i <= n; i++) {
            result *= i;
        }
        return result;
    }

    // Method to display the probabilities
    void display() {
        for (double probability : probabilities) {
            System.out.print(" " + probability);
        }
    }

    // Method to calculate the sum of probabilities for a given range
    double sliderValue(int max, int val) {
        double sum = 0;
        for (int i = max; i >= val; i--) {
            sum += probabilities[i];
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
                double fair_sum = binfair.sliderValue(i, j);
                double cheat_sum = bincheat.sliderValue(i, j);
                // Check if the condition for max fair players falsely accused and minimum cheaters caught is met
                if (fair_sum <= fair && cheat_sum >= cheater) {
                    System.out.println("A minimum of " + (j) + " heads out of " + i + " are required to make a decent assumption that someone is cheating.");
                    return;
                }
            }
        }
    }
}
