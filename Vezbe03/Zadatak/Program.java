package Zadatak;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashSet;

import graphapi.DiGraph;
import graphapi.DirectedBFS;
import graphapi.DirectedDFS;
import graphapi.In;
import graphapi.SimpleGraph;
import graphapi.TransitiveClosure;
import graphapi.UndirectedDFS;

public class Program {
  public static void main(String[] args) throws NumberFormatException, FileNotFoundException, IOException {
    pokrivajuceStablo();
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
    System.out.println(g.E());
    TransitiveClosure tc = new TransitiveClosure(g);
    if (tc.reachable(0, 5)) {
      DiGraph g2 = tc.createClosureGraph(0, 5);
      System.out.println(g2.E());
    }
  }

  public static void dolazivoIzJednog() throws NumberFormatException, FileNotFoundException, IOException {
    DiGraph g = new DiGraph(new In("Zadatak/res/digraf-konture.txt"));
    for (int v : g.vertices()) {
      DirectedBFS bfs = new DirectedBFS(g, v);
      if (g.V() == bfs.count()) {
        System.out.println("Dolazivo je iz " + v);
        return;
      }
    }
  }

  public static void dolazivoIzSvih() throws NumberFormatException, FileNotFoundException, IOException {
    DiGraph g = new DiGraph(new In("Zadatak/res/digraf-konture.txt"));
    DirectedBFS[] bfs = new DirectedBFS[g.V()];
    HashSet<Integer> set = new HashSet<>();

    // Adding all nodes to set
    for (int v : g.vertices()) {
      set.add(v);
    }

    // Finding reachable nodes from source node
    for (int v : g.vertices()) {
      bfs[v] = new DirectedBFS(g, v);

      // Remove all nodes that are not reachable from
      for (int w : g.vertices()) {
        if (!bfs[v].marked(w)) {
          set.remove(w);
        }
      }
    }

    System.out.print("Reachable nodes:");
    for (int v : set) {
      System.out.print(" " + v);
    }
    System.out.println();
  }

  public static void dolazivoIzSvihAlt() throws NumberFormatException, FileNotFoundException, IOException {
    DiGraph g = new DiGraph(new In("Zadatak/res/digraf-konture.txt")).reverse();
    for (int v : g.vertices()) {
      DirectedBFS bfs = new DirectedBFS(g, v);
      if (g.V() == bfs.count()) {
        System.out.println("Dolazivo je u " + v);
        return;
      }
    }
  }

  public static void pokrivajuceStablo() throws NumberFormatException, FileNotFoundException, IOException {
    SimpleGraph g = new SimpleGraph(new In("Zadatak/res/graf-most.txt"));
    UndirectedDFS dfs = new UndirectedDFS(g, 0);
    SimpleGraph spanningTree = new SimpleGraph(g.V());

    for (int v : g.vertices()) {
      int w = dfs.edgeTo(v);
      if (w != -1) {
        spanningTree.addEdge(v, w);
      }
    }

    System.out.println(g.E() + " " + spanningTree.E());
  }
}