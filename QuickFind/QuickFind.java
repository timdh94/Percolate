package Module1.QuickFind;

public class QuickFind {
  private int[] id;

  public void QuickFindUF(int N) {
    id = new int[N];
    for (int i = 0; i < N; i++) {
      id[i] = i;
    }
  }
  
  public boolean connected(int p, int q) {
    Boolean areNodesConnected = id[p] == id[q];
    String printConnected;
    if (areNodesConnected) {
      printConnected = "";
    } else {
      printConnected = "not ";
    }
    System.out.printf("Nodes %d and %d are %sconnected\n", p, q, printConnected);
    return areNodesConnected;
  }
  
  public void union(int p, int q) {
    int pid = id[p];
    int qid = id[q];
    for (int i = 0; i < id.length; i++) {
      if (id[i] == pid) id[i] = qid;
    }
  }
}