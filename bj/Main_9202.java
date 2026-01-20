import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.PriorityQueue;
import java.util.Set;

public class Main_9202 {
	/*
	 */
	static int[][] MOVES = {{0,-1},{-1,0},{0,1},{1,0},{-1,-1},{1,-1},{-1,1},{1,1}};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		boolean[][] visited = new boolean[4][4];
		StringBuilder sb = new StringBuilder();

		Trie trie = new Trie();
		int N = Integer.parseInt(br.readLine());
		for (int i=0;i<N;i++) {
			String line = br.readLine();
			trie.insert(line);
		}
		br.readLine();

		int M = Integer.parseInt(br.readLine());
		int[] results = new int[M];
		List<PriorityQueue<Word>> words = new ArrayList<>();
		for (int i=0;i<M;i++) {
			words.add(new PriorityQueue<>());
		}

		char[][][] grids = new char[M][4][4];
		for (int i=0;i<M;i++) {
			for (int j=0;j<4;j++) {
				grids[i][j] = br.readLine().toCharArray();
			}
			Set<String> set = new HashSet<>();
			for (int j=0;j<4;j++) {
				for (int k=0;k<4;k++) {
					Node next = trie.root.children[grids[i][j][k]-'A'];
					if (next == null) continue;
					if (next.word != null && !set.contains(next.word)) {
						words.get(i).add(new Word(next.word));
						set.add(next.word);
					}
					visited[j][k] = true;
					execute(grids[i], next, j, k, visited, 1, set, words.get(i));
					visited[j][k] = false;
				}
			}
			for (String key: set) {
				results[i] += scoring(key.length());
			}
			sb.append(results[i]).append(" ");
			sb.append(words.get(i).poll().name).append(" ");
			sb.append(set.size()).append("\n");
			br.readLine();
		}
		System.out.println(sb);
	}

	public static void execute(char[][] arr, Node node, int row, int col, boolean[][] visited, int depth, Set<String> set, PriorityQueue<Word> pq) {
		if (depth == 8) return;
		for (int[] move: MOVES) {
			int nr = row+move[0];
			int nc = col+move[1];
			if (nr<0 || nc<0 || nr>=4 || nc>=4) continue;
			if (visited[nr][nc]) continue;
			if (node.children[arr[nr][nc]-'A'] == null) continue;
			Node next = node.children[arr[nr][nc]-'A'];
			if (next.word != null && !set.contains(next.word)) {
				set.add(next.word);
				pq.add(new Word(next.word));
			}
			visited[nr][nc] = true;
			execute(arr, next, nr, nc, visited, depth, set, pq);
			visited[nr][nc] = false;
		}
	}

	public static int scoring(int length) {
		switch (length) {
			case 1:
			case 2:
				return 0;
			case 3:
			case 4:
				return 1;
			case 5: return 2;
			case 6: return 3;
			case 7: return 5;
			case 8: return 11;
		}
		return 0;
	}

	public static class Node {
		Node[] children = new Node[26];
		String word = null;
	}

	public static class Trie {
		Node root = new Node();

		void insert(String word) {
			Node cur = root;
			for (char c: word.toCharArray()) {
				int idx = c-'A';
				if (cur.children[idx] == null) cur.children[idx] = new Node();
				cur = cur.children[idx];
			}
			cur.word = word;
		}
	}

	public static class Word implements Comparable<Word> {
		int length;
		String name;

		Word(String name) {
			this.name = name;
			this.length = name.length();
		}

		@Override
		public int compareTo(Word o) {
			if (this.length == o.length) {
				return this.name.compareTo(o.name);
			}
			return Integer.compare(o.length, this.length);
		}
	}
}