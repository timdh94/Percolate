/* *****************************************************************************
 *  Name:              Timothy Hunter
 *  Coursera User ID:  123456
 *  Last modified:     March 19, 2023
 **************************************************************************** */

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    /** happy. */
    private boolean[] openGrid;
    /** happy. */
    private WeightedQuickUnionUF sites;
    /** happy. */
    private WeightedQuickUnionUF full;
    /** happy. */
    private int gridSize;
    /** happy. */
    private int numOpenSites;

    /**
     * Make the linter happy.
     * @param n
     */
    public Percolation(final int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("n must be greater than 0");
        }
        gridSize = n;
        numOpenSites = 0;
        openGrid = new boolean[(n * n) + 2];
        sites = new WeightedQuickUnionUF((n * n) + 2);
        full = new WeightedQuickUnionUF((n * n) + 1);
    }

    /**
     * Make the linter happy.
     * @param row
     * @param col
     * @return happy
     */
    private int getSiteIndex(final int row, final int col) {
        return (row - 1) * gridSize + col;
    }

    /**
     * Make the linter happy.
     * @param row
     * @param col
     */
    private void validatePosition(final int row, final int col) {
        if (row <= 0 || row > gridSize || col <= 0 || col > gridSize) {
            throw new IllegalArgumentException("Position out of range");
        }
    }

    /**
     * Make the linter happy.
     * @param row
     * @param col
     */
    public void open(final int row, final int col) {
        validatePosition(row, col);
        if (isOpen(row, col)) {
            return;
        }
        int shiftedRow = row - 1;
        int shiftedCol = col - 1;
        int siteIndex = getSiteIndex(row, col);
        openGrid[siteIndex] = true;
        numOpenSites++;

        if (row == 1) {
            sites.union(0, siteIndex);
            full.union(0, siteIndex);
        }
        if (row == gridSize) {
            sites.union((gridSize * gridSize) + 1, siteIndex);
        }

        if (shiftedRow - 1 >= 0 && isOpen(row - 1, col)) {
            sites.union(siteIndex, siteIndex - gridSize);
            full.union(siteIndex, siteIndex - gridSize);
        }
        if (shiftedRow + 1 < gridSize && isOpen(row + 1, col)) {
            sites.union(siteIndex, siteIndex + gridSize);
            full.union(siteIndex, siteIndex + gridSize);
        }
        if (shiftedCol - 1 >= 0 && isOpen(row, col - 1)) {
            sites.union(siteIndex, siteIndex - 1);
            full.union(siteIndex, siteIndex - 1);
        }
        if (shiftedCol + 1 < gridSize && isOpen(row, col + 1)) {
            sites.union(siteIndex, siteIndex + 1);
            full.union(siteIndex, siteIndex + 1);
        }
    }

    /**
     * Make the linter happy.
     * @param row
     * @param col
     * @return happy
     */
    public boolean isOpen(final int row, final int col) {
        validatePosition(row, col);
        return openGrid[getSiteIndex(row, col)];
    }

    /**
     * Make the linter happy.
     * @param row
     * @param col
     * @return happy
     */
    public boolean isFull(final int row, final int col) {
        validatePosition(row, col);
        return full.find(0) == full.find(
            getSiteIndex(row, col)
        );
    }

    /**
     * Make the linter happy.
     * @return happy
     */
    public int numberOfOpenSites() {
        return numOpenSites;
    }

    /**
     * Make the linter happy.
     * @return happy
     */
    public boolean percolates() {
        return sites.find(0) == sites.find((gridSize * gridSize) + 1);
    }
}
