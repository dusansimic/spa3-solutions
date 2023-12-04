package Vezbe04;

import java.io.FileNotFoundException;
import java.io.IOException;

import graphapi.DijkstraSP;
import graphapi.EdgeWeightedGraph;
import graphapi.In;
import graphapi.WeightedEdge;

public class Program {
  public static void main(String[] args) throws FileNotFoundException, IOException {
    cvorSaNajtezimGranama();
    cvorSaNajlaksimGranama();
    najkraciPutIzmedjuDva();
    najkraciPuteviOdJednog();
  }

  private static void cvorSaNajtezimGranama() throws FileNotFoundException, IOException {
    EdgeWeightedGraph g = new EdgeWeightedGraph(new In("Vezbe04/res/tinyEWG.txt"));
    int maxV = -1;
    double maxW = -1.0;

    for (int v : g.vertices()) {
      double w = 0.0;
      for (WeightedEdge e : g.adj(v)) {
        w += e.weight();
      }

      if (w > maxW) {
        maxV = v;
        maxW = w;
      }
    }

    System.out.println(maxV);
  }

  private static void cvorSaNajlaksimGranama() throws FileNotFoundException, IOException {
    EdgeWeightedGraph g = new EdgeWeightedGraph(new In("Vezbe04/res/tinyEWG.txt"));
    int minV = Integer.MAX_VALUE;
    double minW = Double.POSITIVE_INFINITY;

    for (int v : g.vertices()) {
      double w = 0.0;
      for (WeightedEdge e : g.adj(v)) {
        w += e.weight();
      }

      if (w < minW) {
        minV = v;
        minW = w;
      }
    }

    System.out.println(minV);
  }

  private static void najkraciPutIzmedjuDva() throws FileNotFoundException, IOException {
    EdgeWeightedGraph g = new EdgeWeightedGraph(new In("Vezbe04/res/tinyEWG.txt"));
    DijkstraSP sp = new DijkstraSP(g, 0);
    System.out.println(sp.hasPathTo(5));
    System.out.println(sp.pathTo(5));
  }

  private static void najkraciPuteviOdJednog() throws FileNotFoundException, IOException {
    EdgeWeightedGraph g = new EdgeWeightedGraph(new In("Vezbe04/res/tinyEWG.txt"));
    DijkstraSP sp = new DijkstraSP(g, 0);
    for (int v : g.vertices()) {
      if (v != 0 && sp.hasPathTo(v)) {
        System.out.printf("%d\t%f\t%s\n", v, sp.distTo(v), sp.pathTo(v));
      }
    }
  }
}