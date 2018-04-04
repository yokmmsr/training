package java3;

import java.util.Scanner;

public class fukabori1 {

	public static void main(String[] args) {
		double[] array = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
		int iend = 10;
		setArray(array,iend);
		calMaxMin(array, iend);
		calExp(array, iend);
	}
	
	public static double[] setArray(double[] array, int iend) {
		//ランダムな数字をいくつか配列に格納し、色々計算する
		for (int i = 0; i < iend; i++) {
			array[i] = Math.round(Math.random() * 100);
			System.out.println("array["+ i +"] = " + (int)array[i]);
		}
		return array;		
	}
	
	public static void calMaxMin(double[] array, int iend) {
		//生成した配列要素の最大値、最小値を返す
		double max = 0;
		double min = 999;
		for (int i = 0; i < iend; i++) {
			if (max < array[i]) {
				max = array[i];
			}
			if (min > array[i]) {
				min = array[i];
			}
		}
		System.out.println("最大値：" + (int)max);
		System.out.println("最小値：" + (int)min);		
	}

	public static void calExp(double[] array, int iend) {
		//配列の各要素を何乗かした値を返す
		Scanner scanner = new Scanner(System.in);
		int exp;
		while (true) {
			System.out.print("何乗しますか？（0〜4乗の範囲）：");
			exp = scanner.nextInt();
			if (exp >= 0 && exp <= 4) {
				break;
			} else {
				System.out.print("0〜4乗しか指定出来ません！");				
			}
		}

		for (int i = 0; i < iend; i++) {
			System.out.println((int)array[i] + "の" + exp + "乗は" + (int)Math.pow(array[i],exp));
		}
	}
}
