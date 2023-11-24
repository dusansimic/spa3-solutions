package Zadatak;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;

import graphapi.Algorithms;
import graphapi.DiGraph;
import graphapi.In;

public class Program {
  public static void main(String[] args) throws NumberFormatException, FileNotFoundException, IOException {
    DiGraph g = new DiGraph(new In("Zadatak/res/usmereni/dag1.txt"));
    int[] cores = Algorithms.kCoreDecompose(g);
    System.out.println(Arrays.toString(cores));

    boolean hasContour = false;
    for (int shell : cores) {
      if (shell > 2) {
        hasContour = true;
      }
    }
    
    System.out.println(hasContour);

    g.weakComponent(0)
  }
}
