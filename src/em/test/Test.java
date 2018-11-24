package em.test;


public class Test {
	private static int count = 0;
	private static int minus = 0;
	
	public static void count(String str,char[] people,int length) {
		for(int i = people.length-length;i<people.length;i++) {
			str = people[i]+"";
			count(str,people, length-1);
			count++;
			if(rule(str)) {
				minus++;
			}
		}
	}
	
	private static boolean rule(String string) {
		return string.contains("aaa")||string.contains("bbb")||string.contains("ccc");
	}

	public static void main(String[] args) {
		char[] people = {'a','a','a','b','b','b','c','c','c'};
		count("",people,people.length);
		System.out.println("count:"+count);
		System.out.println("minus:"+minus);
		System.out.println("should be:"+(count-minus));
	}
}
