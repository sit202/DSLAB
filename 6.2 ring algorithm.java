import java.util.Scanner;

class Process {
    public int id;
    public boolean active;

    public Process(int id) {
        this.id = id;
        active = true;
    }
}

public class Ring {
    int noOfProcesses;
    Process[] processes;
    Scanner sc;

    public Ring() {
        sc = new Scanner(System.in);
    }

    public void initialiseRing() {
        System.out.println("Enter the number of processes:");
        noOfProcesses = sc.nextInt();
        processes = new Process[noOfProcesses];
        for (int i = 0; i < processes.length; i++) {
            processes[i] = new Process(i);
        }
    }

    public int getMax() {
        int maxId = -1;
        int maxIdIndex = -1;
        for (int i = 0; i < processes.length; i++) {
            if (processes[i].active && processes[i].id > maxId) {
                maxId = processes[i].id;
                maxIdIndex = i;
            }
        }
        return maxIdIndex;
    }

    public void performElection() {
        System.out.println("Process no " + processes[getMax()].id + " fails");
        processes[getMax()].active = false;

        System.out.println("Enter the initiator process:");
        int initiatorProcess = sc.nextInt();

        int prev = initiatorProcess;
        int next = (prev + 1) % noOfProcesses;

        while (true) {
            if (processes[next].active) {
                System.out.println("Process " + processes[prev].id + " passes election message to " + processes[next].id);
                prev = next;
            }

            next = (next + 1) % noOfProcesses;
            if (next == initiatorProcess) {
                break;
            }
        }

        System.out.println("Process " + processes[getMax()].id + " becomes coordinator");
        int coordinator = processes[getMax()].id;

        prev = coordinator;
        next = (prev + 1) % noOfProcesses;

        while (true) {
            if (processes[next].active) {
                System.out.println("Process " + processes[prev].id + " passes coordinator message to process " + processes[next].id);
                prev = next;
            }

            next = (next + 1) % noOfProcesses;
            if (next == coordinator) {
                System.out.println("End of Election");
                break;
            }
        }
    }

    public static void main(String arg[]) {
        Ring r = new Ring();
        r.initialiseRing();
        r.performElection();
    }
}
