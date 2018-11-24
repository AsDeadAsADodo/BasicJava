package list;

import java.util.Arrays;
import java.util.ArrayList;

import org.junit.Test;

public class MyArrayList {
	
	@Test
	public void testResize() {
		MyNode<String> myList = new MyNode<>();
		myList.add("1");
		myList.add("2");
		myList.add("3");
		myList.add("4");
		myList.add("5");
		myList.add("6");
		myList.add("7");
		myList.add("8");
		myList.add("9");
		System.out.println(myList.get(8));
	}
	
	@Test
	public void testAddInto1() {
		MyNode<String> myList = new MyNode<>();
		myList.add("1");
		myList.add("2");
		myList.add("3");
		myList.add("4");
		System.out.println("----------");
		for(int i=0;i<4;i++) {
			System.out.println(myList.get(i));
		}
		myList.addInto("0", 0);
		System.out.println("----------");
		for(int i=0;i<5;i++) {
			System.out.println(myList.get(i));
		}
		myList.addInto("5", 5);
		System.out.println("----------");
		for(int i=0;i<6;i++) {
			System.out.println(myList.get(i));
		}
		myList.addInto("10", 2);
		System.out.println("----------");
		for(int i=0;i<7;i++) {
			System.out.println(myList.get(i));
		}
		System.out.println("----------");
	}
	public static void main(String[] args) {
		MyNode<String> myList = new MyNode<>();
		System.out.println(myList.get(0));
//		System.out.println(myList.get(10));
	}
}

@SuppressWarnings("unchecked")
class MyNode<T>{
	private T[] data;
	private int last =-1;

	public MyNode() {
		data = (T[]) new Object[8];
	}
	public MyNode(int size) {
		data = (T[]) new Object[size];
	}

	public int add(T t) {
		last++;
		int length = (int) (data.length*0.75);
		if(last>=length) {
			data = resize();
		}
		data[last] = t;
		return last;
	}
	
	public int addInto(T t,int index) {
		if(last+1>=data.length*0.75) {
			resize();
		}
		if(index<=0) {
			index = last;
			while(index>=0) {
				data[index+1]=data[index];
				index--;
			}
			data[0] = t;
		}
		else if(index>last) {
			add(t);
			last--;
		}else {
			int temp = last;
			while(temp >= index) {
				data[temp+1]=data[temp];
				temp--;
			}
			data[temp+1] = t;
		}
		last++;
		return index;
	}
	
	public T get(int index) {
		return data[index];
	}

	public void remove(int index) {
		while(index<last) {
			data[index] = null;
			data[index]=data[index+1];
			index++;
		}
		last--;
	}
	
	public void clear() {
		data = (T[]) new Object[data.length];
	}
	private T[] resize() {
		return Arrays.copyOf(data, data.length * 2);
	}
}
