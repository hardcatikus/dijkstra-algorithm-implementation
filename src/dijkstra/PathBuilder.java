package dijkstra;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PathBuilder {

    private static List<Path> buildAllPaths() {

        List<Path> paths = new ArrayList<>();

        Node nodeA = new Node("A");
        Node nodeB = new Node("B");
        Node nodeC = new Node("C");
        Node nodeD = new Node("D");
        Node nodeE = new Node("E");
        Node nodeF = new Node("F");

        paths.add(new Path(nodeA, nodeB,10));
        paths.add(new Path(nodeA, nodeC,15));
        paths.add(new Path(nodeB, nodeD,12));
        paths.add(new Path(nodeB, nodeF,15));
        paths.add(new Path(nodeD, nodeF,1));
        paths.add(new Path(nodeD, nodeE,2));
        paths.add(new Path(nodeF, nodeE,5));
        paths.add(new Path(nodeC, nodeE,10));

        return paths;
    }

    //creating list of nodes with their parameters (distance, adjacentNodes)
    public static List<Node> prepareNodeListForGraph(String startNode, String endNode){

        List<Node> result = new ArrayList<>();

        //nodes and paths creation
        List<Path> paths = buildAllPaths();

        //first node determination
        Node currentNode = new Node(startNode);
        for (Path path: paths) {
            if(path.getStartNode().getName().equals(startNode)){
                currentNode = path.getStartNode();
                break;
            }
            else if(path.getEndNode().getName().equals(startNode)){
                currentNode = path.getEndNode();
                break;
            }
        }

        List<Node> notAddedNodes = new ArrayList<>();
        Set<String> addedNodes = new HashSet<>();
        notAddedNodes.add(currentNode);

        int listIndex = 0;
        int pathsCounter = 0;

        //setting directions to the paths
        while(pathsCounter <= paths.size()){

            if(currentNode.getName().equals(endNode)){
                result.add(currentNode);
                if(paths.size() == pathsCounter || notAddedNodes.size() == listIndex+1){
                    break;
                }
                else {
                    listIndex++;
                    currentNode = notAddedNodes.get(listIndex);
                    continue;
                }
            }

            for(Path path: paths){
                if(path.getStartNode().getName().equals(currentNode.getName()) &&
                        !addedNodes.contains(path.getEndNode().getName())){
                    currentNode.addDestination(path.getEndNode(),path.getLength());
                    if (!notAddedNodes.contains(path.getEndNode())){
                        notAddedNodes.add(path.getEndNode());
                    }

                    System.out.println("Edge: " + currentNode.getName() + " - " + path.getEndNode().getName()
                            + " - " + path.getLength());
                    pathsCounter++;
                }
                else if(path.getEndNode().getName().equals(currentNode.getName()) &&
                        !addedNodes.contains(path.getStartNode().getName())){
                    currentNode.addDestination(path.getStartNode(),path.getLength());
                    if (!notAddedNodes.contains(path.getStartNode())){
                        notAddedNodes.add(path.getStartNode());
                    }

                    System.out.println("Edge: " + currentNode.getName() + " - " + path.getStartNode().getName()
                            + " - " + path.getLength());
                    pathsCounter++;
                }
            }

            result.add(currentNode);
            addedNodes.add(currentNode.getName());
            listIndex++;
            if(listIndex < notAddedNodes.size()){
                currentNode = notAddedNodes.get(listIndex);
            }
            else{
                break;
            }

        }

        return result;
    }

}
