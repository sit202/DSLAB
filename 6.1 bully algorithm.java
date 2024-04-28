import java.util.ArrayList;
import java.util.List;

class BullyNode {
    private int id;
    private boolean isCoordinator;
    private List<BullyNode> nodes;

    public BullyNode(int id, List<BullyNode> nodes) {
        this.id = id;
        this.isCoordinator = false;
        this.nodes = nodes;
    }

    public int getId() {
        return id;
    }

    public boolean isCoordinator() {
        return isCoordinator;
    }

    public void setCoordinator(boolean coordinator) {
        isCoordinator = coordinator;
    }

    public void election() {
        for (BullyNode node : nodes) {
            if (node.getId() > id) {
                System.out.println("Node " + id + " sends election message to Node " + node.getId());
                node.receiveMessage();
            }
        }
    }

    public void receiveMessage() {
        System.out.println("Node " + id + " receives election message.");
        if (!isCoordinator) {
            System.out.println("Node " + id + " becomes coordinator.");
            isCoordinator = true;
            announceVictory();
        }
    }

    private void announceVictory() {
        for (BullyNode node : nodes) {
            if (node.getId() != id) {
                System.out.println("Node " + id + " sends coordinator announcement to Node " + node.getId());
                node.receiveCoordinatorAnnouncement();
            }
        }
    }

    private void receiveCoordinatorAnnouncement() {
        System.out.println("Node " + id + " receives coordinator announcement.");
        // Optionally, perform some action upon receiving the coordinator announcement
    }
}

public class BullyAlgorithm {
    public static void main(String[] args) {
        List<BullyNode> nodes = new ArrayList<>();
        int numNodes = 5; // Number of nodes

        // Create nodes
        for (int i = 0; i < numNodes; i++) {
            nodes.add(new BullyNode(i + 1, nodes));
        }

        // Simulate election process
        nodes.get(0).election();
    }
}
