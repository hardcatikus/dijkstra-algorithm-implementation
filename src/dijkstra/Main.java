package dijkstra;

import java.util.*;

public class Main {

    public static void main(String args[]){

        String startPoint = "E";
        String endPoint = "A";

        //directed graph creation
        List<Node> graphNodes = PathBuilder.prepareNodeListForGraph(startPoint,endPoint);
        Graph graph = new Graph();
        for (Node node: graphNodes){
            graph.addNode(node);
        }

        /*
        System.out.println(graph.getNodes().size());
        for (Node node: graph.getNodes()) {
            System.out.println(node.getName() + ": "
                    + node.getDistance() + ", " + node.getAdjacentNodes());
        }*/

        //searching for the shortest path
        Node firstNode = graphNodes.get(0);
        graph = Dijkstra.calculateShortestPathFromSource(graph, firstNode);

        //print distance
        System.out.print("The shortest path from " + startPoint + " to " + endPoint + ": ");
        for(Node node: graph.getNodes()){
            if(node.getName().equals(endPoint)){
                System.out.println(node.getDistance());
                System.out.print("Path: ");
                for(Node nodeOfPath : node.getShortestPath()){
                    System.out.print(nodeOfPath.getName() + " -> ");
                }
                System.out.println(node.getName());
                break;
            }
        }

    }

}
