package DAY01.P1062;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
	static int N, K;
	static String[] words;
	static boolean[] visited;
	static int selectedCount;
	private static int max;
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("src\\DAY01\\P1062\\input.txt"));
		Scanner sc = new Scanner(System.in);
		
		N = Integer.parseInt(sc.next());
		K = Integer.parseInt(sc.next());
		
		words = new String[N];
		visited = new boolean[26];
		
		if (K < 5) {
			System.out.println(0);
			return;
		} else if (K == 26) {
			System.out.println(N);
			return;
		} else {
			visited['a' - 'a'] = true; // 이 알파벳을 배웠다는 뜻. ASCII 값을 빼 준다.
			visited['n' - 'a'] = true;
			visited['t' - 'a'] = true;
			visited['i' - 'a'] = true;
			visited['c' - 'a'] = true;
			
			selectedCount = 5; // default : 5
			max = countWords();
			
//			System.out.println(Arrays.toString(visited));
			
			for (int i = 0; i<N; i++) {
				words[i] = sc.next().replaceAll("[antic]", "");
			}
//			System.out.println(Arrays.toString(words));
		}
		sc.close();
		
	}
    static void dfs(int index) {
//    	1. 체크인 visited [ 0 - 25 ]
    	visited[index] = true; 
    	selectedCount++;
//    	2. 목적지인가? selectCount == K == 최대개수 계산
    	if (selectedCount == K) {
    		// Max = 최대 단어 개수
    		max = Math.max(countWords(), max);
    	} else {
    		// 3. 갈수 있는 곳을 순회 for (0 ~ 25), 이미 돈 부분은 넘어간다
    		for (int i=index+1; i<26; i++) {
    			// 4. 갈수 있는가?
    			if (visited[i] == false) {
    				// 5. 간다. dfs(next)
    				dfs(i);
    			}
    		}
    	}
//    	6. 체크아웃
    	visited[index] = false;
    	selectedCount--;
    }
    // 단어들을 순회하면서 읽을수 있는가를 체크.
    static int countWords() {
    	int count = 0;
    	for (int i=0; i<N; i++) {
    		boolean isPossible = true;
    		String word = words[i];
    		for (int j=0; j<word.length(); j++) {
    			if (visited[word.charAt(j) - 'a'] == false) { // 해당 알파벳을 배운적이 없으면?
    				isPossible = false;
    				break;
    			}
    		}
    		if (isPossible == true) {
    			count++;
    		}
    	}
    	return count;
    }
}
