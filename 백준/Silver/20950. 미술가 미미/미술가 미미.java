import java.util.Scanner;

public class Main {
	static int minGap, n;
	static int[] gomduri;
	static int[][] color;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		color = new int[n][3];
		for (int i = 0; i < n; i++) {
			color[i][0] = sc.nextInt();
			color[i][1] = sc.nextInt();
			color[i][2] = sc.nextInt();
		}
		gomduri = new int[3];
		for (int i = 0; i < 3; i++) {
			gomduri[i] = sc.nextInt();
		}
		
		minGap = Integer.MAX_VALUE;
		mix(0, 0, 0, 0, 0);
		System.out.println(minGap);
	}
	
	static void mix(int r, int g, int b, int cnt, int i) {
		// 지금까지 n개 넣었다
		if (cnt >= 2) {
			// 지금 만들어진 문두리색과 곰두리색의 gap을 구하기
			int gap = Math.abs(r / cnt - gomduri[0]) + Math.abs(g / cnt - gomduri[1]) + Math.abs(b / cnt - gomduri[2]);
			if (gap < minGap) {
				minGap = gap;
			}
		}
		if (cnt < 7 && i < n) {			
			// i번째 color를 넣을지 말지 정하자
			// 넣자
			mix(r + color[i][0], g + color[i][1], b + color[i][2], cnt + 1, i + 1);
			// 넣지 말자
			mix(r, g, b, cnt, i + 1);
		}
	}
}