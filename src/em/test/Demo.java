package em.test;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.junit.Test;

/**
 * 输出一段字符串中第一个只出现一次的字符
 * 
 * @author EM
 *
 */
public class Demo {

	public Character getFirst(String str) {
		char[] array = str.toCharArray();
		return find(array);
	}

	private Character find(char[] array) {
		List<Character> repository = new ArrayList<Character>();
		List<Character> temp = new ArrayList<Character>();
		for (char c : array) {
			if (!repository.contains(c) && !temp.contains(c)) {
				temp.add(c);
			} else {
				repository.add(c);
				temp.remove((Character) c);
			}
		}
		return temp.get(0);
	}

	@Test
	public void test() {
		assertEquals(getFirst("taabtbfcfcddde"), 'e', 0);
		assertEquals(getFirst("taagbtbfcfcddde"), 'g', 0);
		assertEquals(getFirst("taabtbfcfcdddpe"), 'p', 0);
	}
	
	private static int max3(int a,int b,int c){
		int temp = Math.max(a, b);
		return Math.max(temp, c);
	}
	
	private static int maxSumRec(int[] array,int left,int right){
		if (left == right)
			return array[left] > 0 ? array[left] : 0;
		//二分
		int center = (left+right)>>1;//右移位一次，为除法的除以2，但是效率比较高
		int maxLeftSum = maxSumRec(array,left,center);
		int maxRightSum = maxSumRec(array,center+1,right);
 
		int maxLeftBorderSum = 0, curLeftBorderSum = 0;
		for(int i = center;i >= left; i--){
			curLeftBorderSum += array[i] ;
			if(curLeftBorderSum > maxLeftBorderSum)
				maxLeftBorderSum = curLeftBorderSum;
		}
		int maxRightBorderSum = 0, curRightBorderSum = 0;
		for(int i = center+1; i <= right;i++){
			curRightBorderSum += array[i];
			if(curRightBorderSum > maxRightBorderSum)
				maxRightBorderSum = curRightBorderSum;
		}
		
		return max3(maxLeftSum,maxRightSum,maxRightBorderSum+maxLeftBorderSum);
	}
	
	public static int maxSum(int[] array){
		return maxSumRec(array,0,array.length-1);
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] array = new int[]{ -20, -20, 50, 20, -50 };
		long startTime = System.nanoTime();
		System.out.println("最大子序列和为："+maxSum(array));
		long endTime = System.nanoTime();
		System.out.println("程序耗时为："+(endTime-startTime)+" ns");
	}


}
