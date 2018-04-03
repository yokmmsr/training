package java2;

public class practice3 {

	public static void main(String[] args) {
	    for (int i = 1; i <= 10; i++) {
	        System.out.println(i + "回目のループです1");
	      }

	    for (int i = 1; i <= 10;  ) {
	        System.out.println(i + "回目のループです2");
	        i++;
	      }

	    int j = 1;
	    for ( ; j <= 10; j++) {
	        System.out.println(j + "回目のループです3");
	        }

	    for (int k = 1; ; k++) {
	        System.out.println(k + "回目のループです4");
	    	if (k > 10) {
	    		break;
	    	}
	    }

	}
}