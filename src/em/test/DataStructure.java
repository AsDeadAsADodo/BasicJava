package em.test;

import static org.hamcrest.CoreMatchers.instanceOf;

import java.util.Arrays;

import org.junit.Test;

public class DataStructure {
	private int plus(int[] a) {
		int temp = 0;
		int max = 0;
		for (int i = 0; i < a.length; i++) {
			temp = 0;
			for (int j = i; j < a.length; j++) {
				temp = temp + a[j];
				if (temp > max) {
					max = temp;
				}
			}
		}
		return max > 0 ? max : 0;
	}

	/**
	 * 遍历求最大子列和
	 */
	@Test
	public void sort() {
		int[] a = { -20, -20, 50, 20, -50 };
		long startTime = System.nanoTime();
		System.out.println("最大子序列和为："+plus(a));
		long endTime = System.nanoTime();
		System.out.println("程序耗时为："+(endTime-startTime)+" ns");
	}

	private int nlognPlus(int[] a, int begin, int end) {
		if (begin == end) {
			return a[begin] > 0 ? a[begin] : 0;
		}
		// 位运算 更快 结果上==> /2
		int center = (begin + end) >> 1;
		int leftMax = nlognPlus(a, begin, center);
		int rightMax = nlognPlus(a, center + 1, end);
		int leftBoderMax = 0;
		int rightBoderMax = 0;
		int curRightBorderSum = 0;
		int curLeftBorderSum = 0;
		for (int i = center; i >= begin; i--) {
			curLeftBorderSum = curLeftBorderSum + a[i];
			if (curLeftBorderSum > leftBoderMax) {
				leftBoderMax = curLeftBorderSum;
			}
		}
		for (int i = center+1; i <= begin; i++) {
			curRightBorderSum = curRightBorderSum + a[i];
			if (curRightBorderSum > rightBoderMax) {
				rightBoderMax = curRightBorderSum;
			}
		}
		int temp = Math.max(leftMax, rightMax);
		return Math.max(temp, rightBoderMax+leftBoderMax);
	}

	/**
	 * 分而治之求最大子列和
	 */
	@Test
	public void nlognSort() {
		int[] a = { -20, -20, 50, 20, -50 };
		System.out.println(nlognPlus(a, 0, 4));
	}

	public int onPlus1(int[] arr) {
		int maxNum = 0;
		int positionNum = 0;
		for (int i = 0; i < arr.length; i++) {
			positionNum += arr[i];
			if (positionNum > maxNum) {
				maxNum = positionNum;
			}
			if (positionNum < 0) {
				positionNum = 0;
			}
		}
		return maxNum;
	}
	
	/**
	 * 求最大子列和 O(n)
	 */
	@Test
	public void testPlus() {
		int[] a = { -20, -20, 50, 20, -50 };
		long startTime = System.nanoTime();
		System.out.println("最大子序列和为："+onPlus1(a));
		long endTime = System.nanoTime();
		System.out.println("程序耗时为："+(endTime-startTime)+" ns");
	}
}
