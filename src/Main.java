import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;


public class Main {

	static Node[] allNodes=new Node[8];
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		buildUpGraph();
		ArrayList<Node> nodes = findStartNodes();
		ArrayList<Node> sequencedNodes = new ArrayList<>();
		while (!nodes.isEmpty()) {
			Node currentNode = nodes.get(0);
			nodes.remove(0);
			if (sequencedNodes.contains(currentNode)) {
				System.out.println("Cannot complete these tasks. Going to bed.");
				return;
			}
			sequencedNodes.add(currentNode);
			ArrayList<Node> moreNodes = getMorePotentials(currentNode);
			if (!merge(nodes,moreNodes)) {
				System.out.println("Cannot complete these tasks. Going to bed.");
				return;
			}
		}
		
		printNodes(sequencedNodes);
		
	}

	private static boolean merge(ArrayList<Node> nodes, ArrayList<Node> moreNodes) {
		// TODO Auto-generated method stub
		for (Node node:moreNodes) {
			int idx = Collections.binarySearch(nodes, node);
			if (idx>=0) {
				return false;
			}
			
			idx = -idx-1;
			nodes.add(idx, node);
		}
		return true;
	}

	private static void printNodes(ArrayList<Node> sequencedNodes) {
		// TODO Auto-generated method stub
		for (Node node:sequencedNodes) {
			System.out.print(node.id+" ");
		}
		System.out.println();
	}

	private static ArrayList<Node> getMorePotentials(Node currentNode) {
		// TODO Auto-generated method stub
		return currentNode.next;
	}

	private static ArrayList<Node> findStartNodes() {
		// TODO Auto-generated method stub
		ArrayList<Node> nodes = new ArrayList<>();
		for (int i=1;i<=7;i++) {
			if (allNodes[i].previous.isEmpty())
				nodes.add(allNodes[i]));
		}
		Collections.sort(nodes);
		return nodes;
	}

	private static void buildUpGraph() {
		// TODO Auto-generated method stub
		for (int i=1;i<=7;i++) {
			Node node = new Node(i);
			allNodes[i] = node;
		}
		allNodes[1].addNext(allNodes[7]);
		allNodes[1].addNext(allNodes[4]);
		allNodes[2].addNext(allNodes[1]);
		allNodes[3].addNext(allNodes[4]);
		allNodes[3].addNext(allNodes[5]);
		
		Scanner sc = new Scanner(System.in);
		int id1 = sc.nextInt();
		int id2 = sc.nextInt();
		while (id1!=0) {
			allNodes[id1].addNext(allNodes[id2]);
			id1= sc.nextInt();
			id2 = sc.nextInt();
		}
		sc.close();
	}

}

class Node implements Comparable<Node> {
	public Node(int id) {
		// TODO Auto-generated constructor stub
		this.id = id;
	}
	public void addNext(Node next) {
		// TODO Auto-generated method stub
		this.next.add(next);
		next.previous.add(this);
	}
	int id;
	ArrayList<Node> previous = new ArrayList<>();
	ArrayList<Node> next = new ArrayList<>();
	@Override
	public int compareTo(Node o) {
		// TODO Auto-generated method stub
		return this.id-o.id;
	}
	
	
}