import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.PriorityQueue;

public class Main_1181 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        PriorityQueue<Word> pq = new PriorityQueue<>();
        HashSet<String> wordSet = new HashSet<>();

        for (int i = 0; i < N; i++) {
            String input = br.readLine();
            if (!wordSet.contains(input)) {
                wordSet.add(input);
                pq.add(new Word(input));
            }
        }

        StringBuilder sb = new StringBuilder();
        while (!pq.isEmpty()) {
            sb.append(pq.poll().str).append("\n");
        }
        System.out.println(sb);
    }

    static class Word implements Comparable<Word> {
        String str;
        Word(String str) {
            this.str = str;
        }

        @Override
        public int compareTo(Word o) {
            if (this.str.length()-o.str.length()!=0) {
                return this.str.length()-o.str.length();
            } else {
                return this.str.compareTo(o.str);  // 길이가 같으면 사전 순
            }
        }
    }
}
