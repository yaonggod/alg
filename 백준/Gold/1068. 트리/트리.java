import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	static ArrayList<Integer>[] tree;
	static int leaf;
	static int d;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int root = 0;
		tree = new ArrayList[n];
		for (int i = 0; i < n; i++) {
			tree[i] = new ArrayList<Integer>();
		}
		for (int i = 0; i < n; i++) {
			int p = sc.nextInt();
			if (p != -1) {
				tree[p].add(i);
			} else {
				root = i;
			}
		}
		d = sc.nextInt();
		leaf = 0;
		if (d != root) {
			travel(root);
		}
		System.out.println(leaf);
	}
	
	public static void travel(int s) {
		if (tree[s].size() == 0) {
			leaf++;
		} else if (tree[s].size() == 1 && tree[s].get(0) == d) {
			leaf++;
		} else {
			for (int c : tree[s]) {
				if (c != d) {
					travel(c);
				}
			}
		}
	}
}