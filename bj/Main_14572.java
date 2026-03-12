import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class Main_14572 {
	/*
	가장 잘하는 학생과 못하는 학생의 실력 차이가 D이하
	효율성 E
	E = 그룹내 학생이 아는 알고리즘 수(합집합) - 그룹 내 모든 학생이 아는 알고리즘 수(교집합)) * 그룹원 수
	조건을 만족하는 학생들 중 효율성 최대.

	N(10만),K(최대 30)
	러프한 풀이
	모든 부분 집합을 구함->E를 구함
	하지만 학생 수가 너무 많아서 불가능함

	효율성이 높으려면? 알고리즘 합집합은 크고 교집합은 작아야 함.
	그러면 알고리즘이 30개니까 알고리즘 별로 어떤 학생이 풀었는지를 기록

	교집합은 줄어들 가능성이 없음.
	근데 사람이 1명 추가되면 줄어들 수 있음.

	합집합과 교집합을 매번 구하는 것은 안됨
	합집합 교집합 체크를 30으로 줄이기
	개수를 저장하고, 교집합은 모든 사람이 아는가?로 체크, 합집합은 1이상인가 로 체크

	 */
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		long D = Long.parseLong(st.nextToken());
		Student[] students = new Student[N];
		int[] algorithms = new int[K+1];
		// 알고리즘 개수 초기화
		List<Integer> known;
		int cnt;
		long grade;
		for (int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			cnt = Integer.parseInt(st.nextToken());
			grade = Long.parseLong(st.nextToken());
			known = new ArrayList<>();
			st = new StringTokenizer(br.readLine());
			for (int j=0;j<cnt;j++) {
				int algorithm = Integer.parseInt(st.nextToken());
				known.add(algorithm);
			}
			students[i] = new Student(i, grade, known);
		}

		// grade 순으로 배치
		Arrays.sort(students);

		for (int algorithm:students[0].known) {
			algorithms[algorithm]++;
		}
		int left = 0;
		int right = 0;
		int E = 0;
		int sum;
		int dup;
		while (true) {
			// 현재의 값 계산
			sum = 0;
			dup = 0;
			// System.out.println(K);
			for (int i=1;i<=K;i++) {
				if (algorithms[i]==(right-left+1)) {
					dup++;
				}
				if (algorithms[i]>0) {
					sum++;
				}
			}
			// System.out.println(left+" "+right+" "+sum+" "+dup);
			E = Math.max(E, (sum - dup) *(right-left+1));

			if (right>=N-1) break;
			// 다음 파트 진행을 위해 right + 1은 반드시 하기
			if (right+1<N) {
				right++;
				for (int algorithm:students[right].known) {
					algorithms[algorithm]++;
				}
			}

			// System.out.println(students[right].grade-students[left].grade);
			// System.out.println(D);
			while (left<right && (students[right].grade-students[left].grade) > D) {
				// System.out.println("들어옴");
				for (int algorithm:students[left].known) {
					algorithms[algorithm]--;
				}
				left++;
			}
		}

		System.out.println(E);
	}
}
class Student implements Comparable<Student> {
	int index;
	long grade;
	List<Integer> known;
	Student(int index, long grade, List<Integer> known) {
		this.index = index;
		this.grade = grade;
		this.known = known;
	}

	@Override
	public int compareTo(Student o) {
		return Long.compare(this.grade, o.grade);
	}
}
/*
1 1 10
1 20
1
 */