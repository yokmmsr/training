package java4;

public class Fukabiri2Main {

	public static void main(String[] args) {
		String str = "AAA";
		Fukabori2Sub sample1 = new Fukabori2Sub(str);  /* 引数1個のコンストラクタで初期化 */
		Fukabori2Sub sample2 = new Fukabori2Sub(str, "BBB");  /* 引数2個のコンストラクタで初期化 */
		Fukabori2Sub sample3 = new Fukabori2Sub(str, "CCC", "DDD");  /* 引数3個のコンストラクタで初期化 */
		sample1.print();
		sample2.print();
		sample3.print();
	}
}
