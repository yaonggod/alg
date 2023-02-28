import java.util.Scanner;

public class Main {
	static int[][] tree = new int[26][2];
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		for (int i = 0; i < n; i++) {
			char p = sc.next().charAt(0);
			// 17 42 사이
			char c1 = sc.next().charAt(0);
			char c2 = sc.next().charAt(0); 
			if (c1 - '0' >= 17 && c1 - '0' <= 42) {
				tree[p - '0' - 17][0] = c1 - '0' - 17;
			}
			if (c2 - '0' >= 17 && c2 - '0' <= 42) {
				tree[p - '0' - 17][1] = c2 - '0' - 17;
			}
		}
		StringBuilder pretree = new StringBuilder();
		StringBuilder intree = new StringBuilder();
		StringBuilder posttree = new StringBuilder();
		pret(0, pretree);
		intr(0, intree);
		postr(0, posttree);
		System.out.println(pretree);
		System.out.println(intree);
		System.out.println(posttree);
	}

	private static void pret(int i, StringBuilder pretree) {
		pretree.append((char) (i + 65));
		if (tree[i][0] != 0) {
			pret(tree[i][0], pretree);
		}
		if (tree[i][1] != 0) {
			pret(tree[i][1], pretree);
		}
	}
	private static void intr(int i, StringBuilder intree) {
		if (tree[i][0] != 0) {
			intr(tree[i][0], intree);
		}
		intree.append((char) (i + 65));
		if (tree[i][1] != 0) {
			intr(tree[i][1], intree);
		}
	}
	private static void postr(int i, StringBuilder posttree) {
		if (tree[i][0] != 0) {
			postr(tree[i][0], posttree);
		}
		if (tree[i][1] != 0) {
			postr(tree[i][1], posttree);
		}
		posttree.append((char) (i + 65));
	}
}