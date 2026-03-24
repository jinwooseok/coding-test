import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_13711 {
	/*
	최장 공통 부분 수열
	두 수열이 주어졌을 때 공통이 큰것
	-> lcs 알고리즘으로 해결해보자.
	문자열과 다를 바 없다.
	하지만 숫자라는 차이. 오히려 역추적이 없음
	  1 2
	2
	1
	N+1의 2차원 배열 생성, 첫행과 첫열은 0으로 채우기
	100000인데 N*N으로 한다면 시간초과가 된다.
	두 수열은 길이가 같고, 각 정수가 단 한번씩 등장

	인덱스를 나열 (첫번째 배열에서 숫자가 두번째 배열의 몇번째에 등장하는가)
	그리고, 이 등장하는 배열이 오름차순으로 최대인 경우가 가장 길 것으로 보여짐
	분명 최장 공통 부분 수열이지만 LIS 로 해결 가능할 것으로 보임
	이 경우 이분탐색을 사용하면 NLOGN 가능함

	1. 해당하는 인덱스를 배열로 표시함.
	2. 그 인덱스에서 가장 긴 증가하는 부분 수열 수행
		- 배열과 이분탐색을 통해 해당 인덱스가 들어갈 자리를 찾음(상한 탐색)
		- lis 배열은 배열의 크기가 i일 때 최소값을 저장하는 것
		- 배열의 현재 최대를 항상 갖고 있는게 편할듯함.
	 */
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		int[] arr1 = new int[N];
		int[] arr2Idx = new int[N+1];
		int[] idxArr = new int[N];
		int[] lis = new int[N];

		st = new StringTokenizer(br.readLine());
		for (int i=0;i<N;i++) {
			int a = Integer.parseInt(st.nextToken());
			arr1[i] = a;

		}
		st = new StringTokenizer(br.readLine());
		for (int i=0;i<N;i++) {
			int b = Integer.parseInt(st.nextToken());
			arr2Idx[b] = i;
		}

		for (int i=0;i<N;i++) {
			idxArr[i] = arr2Idx[arr1[i]]+1;
		}
		// System.out.println(Arrays.toString(idxArr));
		int max = 0;
		for (int i=0;i<N;i++) {
			int left = 0;
			int right = max-1;
			while (left<=right) {
				int mid = (left+right)/2;
				if (idxArr[i]>lis[mid]) {
					left = mid+1;
				} else {
					right = mid-1;
				}
			}
			lis[left] = idxArr[i];
			if (left==max) {
				max++;
			}
			// System.out.println(Arrays.toString(lis));
		}
		System.out.println(max);
		// System.out.println(Arrays.toString(lis));
	}
}
/*
10
1 9 5 8 7 6 8 3 2 4
1 8 9 5 7 9 3 2 4 1
5
1 3 2 4 5
5 2 3 1 4
5
1 3 5 2 4
5 3 2 4 1
4
1 2 3 4
1 3 2 4
 */