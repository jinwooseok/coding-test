import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main_1927 {
	static MinHeap heap;
	static StringBuilder sb;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		initHeap();
		sb = new StringBuilder();

		for (int i = 0; i < N; i++) {
			execute(Integer.parseInt(br.readLine()));
		}
		System.out.println(sb);
	}

	private static void initHeap() {
		heap = new MinHeap();
	}

	static class MinHeap {
		List<Integer> array;

		MinHeap() {
			array = new ArrayList<>();
			array.add(0);
		}

		int pop() {
			if (array.size() == 1) {
				return 0;
			}

			int top = array.get(1);
			array.set(1, array.get(array.size() - 1));
			array.remove(array.size() - 1);

			// 힙이 비어있게 된 경우
			if (array.size() == 1) {
				return top;
			}

			// 힙 재정렬
			int n = 1;
			while (2 * n < array.size()) {
				int leftChild = 2 * n;
				int rightChild = 2 * n + 1;
				int smallest = n;

				// 왼쪽 자식과 비교
				if (leftChild < array.size() && array.get(leftChild) < array.get(smallest)) {
					smallest = leftChild;
				}

				// 오른쪽 자식과 비교
				if (rightChild < array.size() && array.get(rightChild) < array.get(smallest)) {
					smallest = rightChild;
				}

				// 더 이상 교환할 필요가 없으면 종료
				if (smallest == n) {
					break;
				}

				swap(n, smallest);
				n = smallest;
			}

			return top;
		}

		void push(int num) {
			array.add(num);
			int n = array.size() - 1;
			int parentIdx = n / 2;

			// 힙 재정렬
			while (parentIdx > 0 && array.get(parentIdx) > array.get(n)) {
				swap(parentIdx, n);
				n = parentIdx;
				parentIdx = parentIdx / 2;
			}
		}

		private void swap(int idx1, int idx2) {
			int temp = array.get(idx1);
			array.set(idx1, array.get(idx2));
			array.set(idx2, temp);
		}
	}

	static void execute(int command) {
		if (command == 0) {
			int minNum = heap.pop();
			sb.append(minNum).append("\n");
		} else {
			heap.push(command);
		}
	}
}
