import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.io.*;

public class Main_1285 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] rows = new int[N];

		for (int i = 0; i < N; i++) {
			String line = br.readLine();
			for (int j = 0; j < N; j++) {
				if (line.charAt(j) == 'T') {
					rows[i] |= (1 << j);
				}
			}
		}

		int answer = Integer.MAX_VALUE;

		// 행 뒤집기 조합 완전탐색 2^N
		for (int rowMask = 0; rowMask < (1 << N); rowMask++) {
			int total = 0;

			// 열은 그리디: 각 열의 min(T개수, H개수)
			for (int col = 0; col < N; col++) {
				int tCount = 0;
				for (int row = 0; row < N; row++) {
					int bit = (rows[row] >> col) & 1;
					if (((rowMask >> row) & 1) == 1) bit = 1 - bit; // 행 뒤집기 적용
					tCount += bit;
				}
				total += Math.min(tCount, N - tCount);
			}

			answer = Math.min(answer, total);
		}

		System.out.println(answer);
	}
}