package backjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class bj_1275 {
	static int n, m, answer;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		long[] num = new long[n + 1];
		long[] tree = new long[n * 4];

		// n개의 수 입력
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= n; i++) {
			num[i] = Integer.parseInt(st.nextToken());
		}

		// 입력 배열, 트리, 현재 노드 index, 현재 노드가 담당하는 합의 범위
		init(num, tree, 1, 1, n);

		// x~y까지 합, a를 b로 바꾸기
		for (int j = 0; j < m; j++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			if (x > y) {
				int temp = x;
				x = y;
				y = temp;
			}
			// 트리, 구간 합, 시작 노드, 현재 노드가 담당하는 합의 범위
			System.out.println(sum(tree, x, y, 1, 1, n));
			int a = Integer.parseInt(st.nextToken());
			long b = Long.parseLong(st.nextToken());
			// 트리, 시작 노드, 현재 노드가 담당하는 합의 범위, 값의 차이
			update(tree, 1, 1, n, a, b - num[a]);
			num[a] = b;
		}

	}

	private static void update(long[] tree, int node, int start, int end, int index, long diff) {
		// 범위 밖인 경우
		if (index < start || index > end)
			return;

		// 범위 안에 있는 경우 내려가면서 다른 원소 갱신
		tree[node] += diff;
		// 리프 노드라면
		if (start == end)
			return;

		int mid = (start + end) / 2;
		update(tree, node * 2, start, mid, index, diff);
		update(tree, node * 2 + 1, mid + 1, end,index, diff);

	}

	private static long sum(long[] tree, int x, int y, int node, int start, int end) {
		// 구간 합의 범위 밖인 경우
		if (end < x || y < start)
			return 0;
		// 구간합의 범위 안인 경우
		if (x <= start && end <= y)
			return tree[node];

		// 범위가 겹치는 경우 나눠서 합 구하기
		int mid = (start + end) / 2;

		return sum(tree, x, y, node * 2, start, mid) + sum(tree, x, y, node * 2 + 1, mid + 1, end);

	}

	private static long init(long[] num, long[] tree, int node, int start, int end) {
		if (start == end) {
			return tree[node] = num[start];
		} else {
			int mid = (start + end) / 2;
			// 왼쪽 자식과 오른쪽 자식 분할 정복으로 갱신
			return tree[node] = init(num, tree, node * 2, start, mid) + init(num, tree, node * 2 + 1, mid + 1, end);
		}

	}

}
