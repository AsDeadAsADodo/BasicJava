package em.string;

import java.util.Scanner;


public class StringOperate {
	public static void toStandardSQL(String string) {
		System.out.println("-------------------------------------------------------------");
		System.out.println("------------------------Translate-----------------------------");
		System.out.println("-------------------------------------------------------------");
		char[] array = string.toUpperCase().toCharArray();
		char temp = ' ';
		for( int i = 0 ; i < array.length ; i++ ) {
			if(temp!=' '&&((array[i]==' ')||(array[i]>=':'&&array[i]<='z')||(array[i]>=40&&array[i]<=47))) {
				array[i-1] = ' ';
				temp=' ';
			}
			else if(array[i] == 96) {
				temp = array[i];
			}
		}
		System.out.println(new String(array));
	}
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		String string = " ";
		StringBuffer stringBuffer = new StringBuffer();
		while(1 == 1) {
			string	= scanner.nextLine();
			stringBuffer.append(string);
			stringBuffer.append("\r\n");
			if(string.endsWith("-")) {
				break;
			}
		}
		toStandardSQL(stringBuffer.toString());
		System.out.println("»Ø³µ¼üÍË³ö");
		scanner.nextLine();
	}
}
