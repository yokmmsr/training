package java2;

public class practice2 {

	public static void main(String[] args) {
		for (int i = 1; i <= 10; i++) {
			switch (10 % i) {
			case 0:
				System.out.println(i + "a");
				break;
			case 1:
				System.out.println(i + "b");
				break;
			case 2:
				System.out.println(i + "c");
				break;
			case 3:
				System.out.println(i + "d");
				break;
			default:
				System.out.println(i + "e");
				break;				
			}
			System.out.println(i + "end");
			
		}
		
	}

}
