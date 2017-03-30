/**
 * Wasfi Momen
 * 3/28/17
 *
 *
 */

package company;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.PriorityQueue;
import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;
import java.io.BufferedReader;

class Vertex implements Comparable<Vertex>
{
    public final int vertex_id;
    public List<Edge> adjacencies;
    public Integer minDistance = 100000; // for priority queue set all verticies to infinity except source.
    public Vertex previous;
    public Vertex(int newVertexID) { vertex_id = newVertexID; }
    public String toString() { return String.valueOf(vertex_id); }
    public int compareTo(Vertex other)
    {
        return Integer.compare(minDistance, other.minDistance);
    }

    public void addEdge(Edge give) {
        adjacencies.add(give);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Vertex other = (Vertex) obj;
        if (vertex_id != other.vertex_id)
            return false;
        return true;
    }




}


class Edge
{
    public final Vertex target;
    public final int weight;
    public Edge(Vertex argTarget, int argWeight)
    { target = argTarget; weight = argWeight; }

    public String toString () {

        String array = "[" + target.toString() + "," + Integer.toString(weight) +"]";
        return array;
    }

}





public class Dijkstra
{
    public static void computePaths(Vertex source)
    {
        source.minDistance = 0;
        PriorityQueue<Vertex> vertexQueue = new PriorityQueue();
        vertexQueue.add(source);

        while (!vertexQueue.isEmpty()) {
            Vertex u = vertexQueue.poll();

            for (Edge e : u.adjacencies)
            {
                Vertex v = e.target;
                int weight = e.weight;
                int distanceThroughU = u.minDistance + weight;
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

    public static void makeEdge (){
        BufferedReader input;
        Scanner outputLineScan;
        String scanoutputLine;
        int numberOfInputs;
        int counter;
        int i;

        List<Edge> allEdges = new ArrayList<Edge>();
        List<Vertex> allVertices = new ArrayList<Vertex>();


        try {
            input = new BufferedReader(new FileReader("output.txt"));
            input.readLine();

            while( (scanoutputLine = input.readLine()) != null){

                counter = 0;
                i=0;
                outputLineScan = new Scanner(scanoutputLine);

                int edges [] = new int[2];
                int edgesIndicies = 0;
                String [] scanning = new String [2];

                    while(outputLineScan.hasNextLine()) {

                        int vertexID = Integer.parseInt(outputLineScan.next());
//                        System.out.println(vertexID);
                        Vertex curVertex = new Vertex(vertexID);
//                            System.out.println("curv" +curVertex);
                        if (outputLineScan.hasNext()) {
                            String token = outputLineScan.next();
//                            System.out.println("token: " + token);
                            if (!token.isEmpty()) {
//                                System.out.println("v" + vertexID);

                                int weight = Integer.parseInt(token);
                                Edge curEdge = new Edge(curVertex, weight);
                                allEdges.add(curEdge);
                            } else {
//                                System.out.println(vertexID);
                                if (!allEdges.isEmpty()) {
                                    int len = allVertices.size();
                                    Vertex lastV = allVertices.get(len);
                                    lastV.adjacencies = allEdges;
                                    allEdges = new ArrayList<Edge>();
                                }
                                allVertices.add(curVertex);
//                                System.out.println("test" );
                            }

                        }

                        if (outputLineScan.hasNextLine()) {
                            outputLineScan.nextLine();
                        }
                    }
//                            edges[counter] = outputLineScan.nextInt();
//                            counter++;
//                            edgesIndicies++;
                    }
//                    computePaths(allVertices.get(0));
//                int toBeVer = edges[0];

//                allEdges.add(new Edge(new Vertex(toBeVer), edges[1]));
//                System.out.println(allEdges.toString());

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public static void main(String[] args)
    {

        Scanner input = new Scanner (System.in);
        List<Vertex> vertices = new ArrayList<Vertex>();

        try {

            // ***CHANGE FILE HERE ***
            BufferedReader in = new BufferedReader(new FileReader("C:\\Users\\Wasfi\\IdeaProjects\\gradeSmartt\\Djiksta\\src\\main\\company\\input_2.txt"));
            PrintWriter output = new PrintWriter(new FileWriter("output.txt"));
            String inputLine;


            while((inputLine = in.readLine()) != null) {
                inputLine = inputLine.trim();
                if (!inputLine.isEmpty()) {
                    output.println(inputLine);
                }
            }




        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

        makeEdge();

//         System.out.println(vertices);

//        computePaths(A); // run Dijkstra
//        System.out.println("Distance to " + Z + ": " + Z.minDistance);
//        List path = getShortestPathTo(Z);
//        System.out.println("Path: " + path);
    }
}
        