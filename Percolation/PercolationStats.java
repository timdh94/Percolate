/* *****************************************************************************
 *  Name:              Timothy Hunter
 *  Coursera User ID:  123456
 *  Last modified:     March 19, 2023
 **************************************************************************** */

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
  /** happy. */
  private double[] probabilityOnFinished;
  /** happy. */
  private int numTrials;
  /** happy. */
  private final double CONFIDENCE_95 = 1.96;

  /**
   * Make the linter happy.
   * @param n
   * @param trials
   */
  public PercolationStats(final int n, final int trials) {
    if (n <= 0 || trials <= 0) {
      throw new IllegalArgumentException("n and trials must be greater than 0");
    }
    numTrials = trials;
    probabilityOnFinished = new double[trials];
    int totalSites = n * n;
    for (int i = 0; i < trials; i++) {
      Percolation currentGrid = new Percolation(n);
      while (!currentGrid.percolates()) {
        int row = StdRandom.uniformInt(n);
        int col = StdRandom.uniformInt(n);
        currentGrid.open(row + 1, col + 1);
      }
      probabilityOnFinished[i] =
        (double) currentGrid.numberOfOpenSites() / totalSites;
      updateStats();
    }
  }

  /**
   * Make the linter happy.
   */
  private void updateStats() {
  }

  /**
   * Returns the mean probability of percolation.
   * @return double
   */
  public double mean() {
    return StdStats.mean(probabilityOnFinished);
  }

  /**
   * Returns the standard deviation of the average probability that the
   * grid will percolate.
   * @return double
   */
  public double stddev() {
    return StdStats.stddev(probabilityOnFinished);
  }

  /**
   * Returns low high 95% confidence interval.
   * @return double
   */
  public double confidenceLo() {
    return mean() - (CONFIDENCE_95 * (stddev()) / Math.sqrt(numTrials));
  }

  /**
   * Returns the high 95% confidence interval.
   * @return double
   */
  public double confidenceHi() {
    return mean() + (CONFIDENCE_95 * (stddev()) / Math.sqrt(numTrials));
  }

  /**
   * Make the linter happy.
   * @param args
   */
  public static void main(final String[] args) {
    if (args.length != 2) {
      throw new IllegalArgumentException("Must provide both n and trials");
    }
    int gridSize;
    int numTrials;

    gridSize = Integer.parseInt(args[0]);
    numTrials = Integer.parseInt(args[1]);
    if (gridSize <= 0 || numTrials <= 0) {
      throw new IllegalArgumentException(
        "Must provide integer values > 0 for both n and trials"
      );
    }

    PercolationStats percolator = new PercolationStats(gridSize, numTrials);
    String mean = "mean                    = " + percolator.mean();
    String stddev = "stddev                  = " + percolator.stddev();
    String conf = "95% confidence interval = ["
      + percolator.confidenceLo() + ", " +  percolator.confidenceHi() + "]";
    System.out.printf("%s\n%s\n%s\n", mean, stddev, conf);
  }
}
