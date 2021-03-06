package DAY03.IndexedTree;

import java.util.Arrays;

public class IndexedTreeTest {
	public static void main(String[] args) {
		int[] inputs = { 0,3,2,4,5,1,6,2,7 };
		// depth 3 = leaf 8개
		IndexedTree it = new IndexedTree(inputs);
		System.out.println(it);
		System.out.println(it.query(1, 1, it.leafSize, 3, 7));
		// index 3을 4->3으로 바꿈
		int targetIndex = 3;
		int targetValue = 3;
		int diff = targetValue - it.nums[targetIndex];
		it.update(1, 1, it.leafSize, 3, diff);
		it.nums[targetIndex] = targetValue;
		System.out.println(it);
	}
	
}
class IndexedTree {
	int[] tree;
	int[] nums;
	int leafSize, depth;
	
	public IndexedTree(int[] nums) {
		this.nums = nums;
		this.depth = 0;
		while (Math.pow(2, this.depth) < nums.length -1) {
			this.depth++;
		}
		this.leafSize = (int)Math.pow(2, depth);
		this.tree = new int[(int)Math.pow(2, depth+1)];
	}
	
	// 내부노드에 값을 채워준다.
	public int makeTree(int node, int left, int right) {
		// 리브페 도달하면 데이터를 채워줌
		if (left == right) {
			// 실제 문제를 풀때는 주어진 N 기준으로.
			if (left <= nums.length - 1) {
				return tree[node] = nums[left];
			} else {
				return tree[node] = 0;
			}
		}
		// 내부노드일 경우
		int mid = (left + right) / 2;
		tree[node] = makeTree(node * 2, left, mid);
		tree[node] += makeTree(node * 2 + 1, mid + 1, right);
		return tree[node];
	}
	
	// 원하는 구간의 합 or 문제에 답을 구한다.
	public int query(int node, int left, int right, int qLeft, int qRight) {
		if (qRight < left || right < qRight) { // 관련 없는 케이스
			return 0;
		} else if ( qLeft <= left && right <= qRight) { // 쿼리에 완전 속하는 경우
			return tree[node];
		} else { // 애매하게 걸쳐잇는 경우
			int mid = (left + right) / 2;
			return query(node * 2, left, mid, qLeft, qRight) + query(node * 2 + 1 , left , mid+1, qLeft, qRight);
		}
	}
	
	// 특정값을 갱신한다.
	public void update(int node, int left, int right, int index, int diff) {
		if (index < left || right < index) {
			return;
		} else {
			tree[node] += diff;
			if (left != right) {
				int mid = (left + right) / 2;
				update(node *2, left, mid, index, diff);
				update(node *2+1, left, mid+1, index, diff);
			}
		}
	}
	
	@Override
	public String toString() {
		return "IndexedTree [tree =" + Arrays.toString(tree) 
			+ ", nums=" + Arrays.toString(nums) 
			+ ", leafSize=" + leafSize + ", depth=" + depth + "]";
	}
}
