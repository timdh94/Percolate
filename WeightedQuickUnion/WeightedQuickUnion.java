package Module1.WeightedQuickUnion;

public class WeightedQuickUnion {
  private int[] parent;
  private int[] treeSize;

  public void init(int numNodes) {
    parent = new int[numNodes];
    treeSize = new int[numNodes];
    for (int i = 0; i < numNodes; i++) {
      parent[i] = i;
      treeSize[i] = 1;
    }
  }
  
  public Boolean connected(int node1, int node2) {
    int node1Root = findRoot(node1);
    int node2Root = findRoot(node2);
    Boolean connected = node1Root == node2Root;
    System.out.printf(
      "%d and %d are %sconnected\n",
      node1, node2, connected ? "" : "not ");
    return connected;
  }
  
  public void union(int node1, int node2) {
    int node1Root = findRoot(node1);
    int node2Root = findRoot(node2);
    if (node1Root == node2Root) {
      System.out.printf("%d and node %d are already connected", node1, node2);
      return;
    }
    // Connect the smaller tree to the larger tree
    if (treeSize[node1] < treeSize[node2]) {
      parent[node1Root] = node2Root;
      treeSize[node2Root] += treeSize[node1Root];
    } else {
      parent[node2Root] = node1Root;
      treeSize[node1Root] += treeSize[node2Root];
    }
    System.out.printf("Connected node %d and node %d\n", node1, node2);
  }
  
  private int findRoot(int node) {
    while (node != parent[node]) {
      // Optimization, make the node point to its grandparent, flattenening
      // the tree as we find the root.
      parent[node] = parent[parent[node]];
      node = parent[node];
    }
    return node;
  }
}
