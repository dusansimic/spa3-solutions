import graphapi.Graph;
import graphapi.In;

public class App {
    public static void main(String[] args) throws Exception {
        In stream = new In("res/tinyG.txt");
        Graph g = new Graph(stream);
        stream.end();
        System.out.println(g);
        System.out.println(g.components());
        System.out.println(g.hasPath(0, 9));
        System.out.println(g.hasPath(0, 1));
        System.out.println(g.path(0, 9));
        System.out.println(g.path(0, 4));
        System.out.println(g.distances(0));
        System.out.println(g.hasContours(7));
    }
}
