package java4;

public class Fukabori2Sub {
	private String str1;
	private String str2;
	private String str3;
	
	Fukabori2Sub(String str) {
		this("EEE", str); /* 引数2個のコンストラクタを参照する */
	}	
	Fukabori2Sub(String str1, String str2) { /* 引数2個のコンストラクタ */
		this.str1 = str1;
		this.str2 = str2;
	}
	Fukabori2Sub(String str1, String str2, String str3) { 
		this(str1); /* 引数1個のコンストラクタを参照する */
		this.str3 = str3;
	}
	public void print() {
		System.out.println(str1);
		System.out.println(str2);
		System.out.println(str3);
		System.out.println("----------");
	}
}
