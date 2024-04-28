import java.util.ArrayList;
import java.util.List;

class Node {
    private String name;
    private int clock;

    public Node(String name, int clock) {
        this.name = name;
        this.clock = clock;
    }

    public String getName() {
        return name;
    }

    public int getClock() {
        return clock;
    }

    public void setClock(int clock) {
        this.clock = clock;
    }
}

public class BerkeleyAlgorithm {
    public static void main(String[] args) {
        // Create nodes with their initial clocks
        Node node1 = new Node("Node 1", 10);
        Node node2 = new Node("Node 2", 15);
        Node node3 = new Node("Node 3", 20);

        // Add nodes to the list
        List<Node> nodeList = new ArrayList<>();
        nodeList.add(node1);
        nodeList.add(node2);
        nodeList.add(node3);

        // Calculate the average clock time
        int sum = 0;
        for (Node node : nodeList) {
            sum += node.getClock();
        }
        int average = sum / nodeList.size();

        // Adjust the clock of each node
        for (Node node : nodeList) {
            int difference = average - node.getClock();
            node.setClock(node.getClock() + difference);
            System.out.println(node.getName() + " - Adjusted Clock: " + node.getClock());
        }
    }
}
