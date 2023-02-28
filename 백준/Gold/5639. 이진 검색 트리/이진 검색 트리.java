import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Node {
	int value;
	Node left;
	Node right;
	Node(int value) {
		this.value = value;
	}
}

public class Main {
	static Node root;
	static StringBuilder sb;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		String str;
		// 전위 : 나 - 왼 - 오
		// 후위 : 왼 - 나 - 오
		root = new Node(Integer.parseInt(br.readLine()));
		while (true) {
			str = br.readLine();
			if (str == null || str.equals("")) {
				break;
			}
			Node n = new Node(Integer.parseInt(str));
			findposition(n, root);
		}
		postorder(root);
		System.out.println(sb);
	}
	
	public static void findposition(Node n, Node p) {
		if (n.value < p.value) {
			if (p.left == null) {
				p.left = n;
			} else {
				findposition(n, p.left);
			}
		} else if (n.value > p.value) {
			if (p.right == null) {
				p.right = n;
			} else {
				findposition(n, p.right);
			}
		}
	}
	
	public static void postorder(Node n) {
		if (n.left != null) {
			postorder(n.left);
		}
		if (n.right != null) {
			postorder(n.right);
		}
		sb.append(n.value + "\n");
	}
}