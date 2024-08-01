import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
public class Main_1991 {
	static Map<String, String[]> tree;
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		tree = new HashMap<>();
		for (int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			tree.put(st.nextToken(), new String[] {st.nextToken(),st.nextToken()});
		}
		
		preOrder("A");
		System.out.println();
		inOrder("A");
		System.out.println();
		postOrder("A");
		
		
	}
	
	public static void preOrder(String root) {
		if (root.equals(".")) return;
		System.out.print(root);
		preOrder(tree.get(root)[0]);
		preOrder(tree.get(root)[1]);
	}
	
	public static void inOrder(String root) {
		if (root.equals(".")) return;
		inOrder(tree.get(root)[0]);
		System.out.print(root);
		inOrder(tree.get(root)[1]);
	}
	
	public static void postOrder(String root) {
		if (root.equals(".")) return;
		postOrder(tree.get(root)[0]);
		postOrder(tree.get(root)[1]);
		System.out.print(root);
	}
}
