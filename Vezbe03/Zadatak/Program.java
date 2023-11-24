package Zadatak;

import java.io.FileNotFoundException;
import java.io.IOException;

import graphapi.DiGraph;
import graphapi.DirectedDFS;
import graphapi.In;
import graphapi.TransitiveClosure;

public class Program {
  public static void main(String[] args) throws NumberFormatException, FileNotFoundException, IOException {
    tranzitivnoZatvorenje();
  }

  public static void cvoroviIzvori() throws NumberFormatException, FileNotFoundException, IOException {
    DiGraph g = new DiGraph(new In("Zadatak/res/dag1.txt"));
    int count = 0;
    for (int v : g.vertices()) {
      if (g.indegree(v) == 0 && g.outdegree(v) > 0) {
        count++;
      }
    }
    System.out.println(count);
  }

  public static void cvoroviUsca() throws NumberFormatException, FileNotFoundException, IOException {
    DiGraph g = new DiGraph(new In("Zadatak/res/dag1.txt"));
    int count = 0;
    for (int v : g.vertices()) {
      if (g.outdegree(v) == 0 && g.indegree(v) > 0) {
        count++;
      }
    }
    System.out.println(count);
  }

  public static void sticiDoOstalih() throws NumberFormatException, FileNotFoundException, IOException {
    DiGraph g = new DiGraph(new In("Zadatak/res/digraf-konture.txt"));
    DirectedDFS ddfs = new DirectedDFS(g, 0);
    System.out.println(g.V() == ddfs.count());
  }

  public static void sticiDoBarJosNekog() throws NumberFormatException, FileNotFoundException, IOException {
    DiGraph g = new DiGraph(new In("Zadatak/res/digraf-konture.txt"));
    DirectedDFS[] ddfs = new DirectedDFS[g.V()];
    for (int v : g.vertices()) {
      ddfs[v] = new DirectedDFS(g, v);
    }

    if (g.V() == ddfs[0].count()) {
      for (int v : g.vertices()) {
        if (v != 0 && g.V() == ddfs[v].count()) {
          System.out.println("Yes");
          return;
        }
      }
    }
  }

  public static void sticiNazadDoNjega() throws NumberFormatException, FileNotFoundException, IOException {
    DiGraph g = new DiGraph(new In("Zadatak/res/digraf-konture.txt"));
    DirectedDFS ddfs;

    for (int v : g.vertices()) {
      if (v != 0) {
        ddfs = new DirectedDFS(g, v);
        if (ddfs.count() != g.V()) {
          System.out.println("No");
          return;
        }
      }
    }

    System.out.println("Yes");
  }

  public static void tranzitivnoZatvorenje() throws NumberFormatException, FileNotFoundException, IOException {
    DiGraph g = new DiGraph(new In("Zadatak/res/digraf-konture.txt"));
    TransitiveClosure tc = new TransitiveClosure(g);
    if (tc.reachable(0, 5)) {
      System.out.println("Yes");
      g.addEdge(0, 5);
    }
  }
}