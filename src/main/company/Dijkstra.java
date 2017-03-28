package company;

import java.io.FileReader;
import java.util.PriorityQueue;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.io.BufferedReader;

class Vertex implements Comparable<Vertex>
{
    public final int vertex_id;
    public Edge[] adjacencies;
    public double minDistance = Double.POSITIVE_INFINITY;
    public Vertex previous;
    public Vertex(int newVertexID) { vertex_id = newVertexID; }
    public String toString() { return String.valueOf(vertex_id); }
    public int compareTo(Vertex other)
    {
        return Double.compare(minDistance, other.minDistance);
    }

}


class Edge
{

    public final Vertex target;
    public final double weight;
    public Edge(Vertex argTarget, double argWeight)
    { target = argTarget; weight = argWeight; }
}

public class Dijkstra
{
    public static void computePaths(Vertex source)
    {
        source.minDistance = 0.;
        PriorityQueue<Vertex> vertexQueue = new PriorityQueue();
        vertexQueue.add(source);

        while (!vertexQueue.isEmpty()) {
            Vertex u = vertexQueue.poll();

            // Visit each edge exiting u
            for (Edge e : u.adjacencies)
            {
                Vertex v = e.target;
                double weight = e.weight;
                double distanceThroughU = u.minDistance + weight;
                if (distanceThroughU < v.minDistance) {
                    vertexQueue.remove(v);

                    v.minDistance = distanceThroughU ;
                    v.previous = u;
                    vertexQueue.add(v);
                }
            }
        }
    }

    public static List getShortestPathTo(Vertex target)
    {
        List path = new ArrayList();
        for (Vertex vertex = target; vertex != null; vertex = vertex.previous)
            path.add(vertex);

        Collections.reverse(path);
        return path;
    }

    public static void main(String[] args)
    {
        // mark all the vertices 
        Vertex A = new Vertex(0);
        Vertex B = new Vertex(1);
        Vertex D = new Vertex(2);
        Vertex F = new Vertex(3);
        Vertex K = new Vertex(4);
        Vertex J = new Vertex(5);
        Vertex M = new Vertex(6);
        Vertex O = new Vertex(7);
        Vertex P = new Vertex(8);
        Vertex R = new Vertex(9);
        Vertex Z = new Vertex(10);

        // set the edges and weight
        A.adjacencies = new Edge[]{ new Edge(M, 8) };
        B.adjacencies = new Edge[]{ new Edge(D, 11) };
        D.adjacencies = new Edge[]{ new Edge(B, 11) };
        F.adjacencies = new Edge[]{ new Edge(K, 23) };
        K.adjacencies = new Edge[]{ new Edge(O, 2) };
        J.adjacencies = new Edge[]{ new Edge(K, 25) };
        M.adjacencies = new Edge[]{ new Edge(R, 100) };
        O.adjacencies = new Edge[]{ new Edge(K, 40) };
        P.adjacencies = new Edge[]{ new Edge(Z, 18) };
        R.adjacencies = new Edge[]{ new Edge(P, 15) };
        Z.adjacencies = new Edge[]{ new Edge(P, 18) };

        BufferedReader in = null;
        List<Vertex> vertices = new ArrayList<Vertex>();

        try {
            in = new BufferedReader(new FileReader("C:\\Users\\Wasfi\\IdeaProjects\\gradeSmartt\\Djiksta\\src\\main\\company\\input_2.txt"));
            in.readLine();
            String inputLine;
            boolean emptyLine = false; // boolean to track when a new line exists for a new vertex.

            while((inputLine = in.readLine()) != null) {

                // checks for new vertex and creates it.
                if(inputLine.matches("^[\\d]+$")) {
                    // System.out.println(inputLine); // Uncomment to see all vertices.

                    // create a new vertex
                    // for loop until newline while adding adjacenies.
                    // end loop at newline
                    int vertex_id_create = Integer.parseInt(inputLine);
                    vertices.add(new Vertex(vertex_id_create));
                    int toAssign = vertices.indexOf();
                    System.out.println(vertices.get(toAssign));
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

         System.out.println(vertices);

//        computePaths(A); // run Dijkstra
//        System.out.println("Distance to " + Z + ": " + Z.minDistance);
//        List path = getShortestPathTo(Z);
//        System.out.println("Path: " + path);
    }
}
        