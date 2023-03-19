package Module1.QuickUnion;

public class QuickUnion {
  private int[] nodes;

  public void init(int numNodes) {
    nodes = new int[numNodes];
    for (int i = 0; i < numNodes; i++) {
      nodes[i] = i;
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
    nodes[node2Root] = node1Root;
    System.out.printf("Connected node %d and node %d\n", node1, node2);
  }
  
  private int findRoot(int node) {
    while (node != nodes[node]) {
      node = nodes[node];
    }
    return node;
  }
}
