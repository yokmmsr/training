package StreamAPISample02;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.BinaryOperator;
import java.util.ArrayList;
import java.util.stream.Stream;

public class Main {

	public static void main(String[] args) {
		List<Integer> intList1 = Arrays.asList(1, 21, 30, 46, 10, 5, 7, 199, 2, 15);

		System.out.print("intList1 : ");
		intList1.stream().forEach(i -> System.out.print(i + " "));
		System.out.println("");
		System.out.println("-----------------------------");

		// 奇数だけ表示する
		intList1.stream().filter(i -> i % 2 != 0).forEach(i -> System.out.print(i + " "));
		System.out.println("");
		System.out.println("含まれる奇数は" + intList1.stream().filter(i -> i % 2 != 0).count() + "個");
		System.out.println("-----------------------------");

		// 20より大きい数字だけ表示する
		intList1.stream().filter(i -> i > 20).forEach(i -> System.out.print(i + " "));
		System.out.println("");
		System.out.println("-----------------------------");

		// 各数字を2乗して表示する
		intList1.stream().map(i -> i + "の2乗は" + (int) Math.pow(i, 2) + "です").forEach(i -> System.out.println(i));
		System.out.println("-----------------------------");

		// 偶数と奇数に分けて表示する
		Stream<Integer> evenNumbers = intList1.stream().filter(i -> i % 2 == 0);
		System.out.println("含まれる偶数は" + evenNumbers.count() + "個");
		intList1.stream().filter(i -> i % 2 == 0).map(i -> i + "の2乗は" + (int) Math.pow(i, 2) + "です")
				.forEach(i -> System.out.println(i));
		System.out.println("");
		Stream<Integer> oddNumbers = intList1.stream().filter(i -> i % 2 != 0);
		System.out.println("含まれる奇数は" + oddNumbers.count() + "個");
		intList1.stream().filter(i -> i % 2 != 0).map(i -> i + "の2乗は" + (int) Math.pow(i, 2) + "です")
				.forEach(i -> System.out.println(i));
		System.out.println("");

		System.out.println("=============================");

		List<String> stringList1 = Arrays.asList("nora", "neko", "works");
		List<String> stringList2 = Arrays.asList("n", "o", "r", "a", "n", "e", "k", "o", "w", "o", "r", "k", "s");
		System.out.print("stringList1 : " + stringList1);
		System.out.print("stringList2 : " + stringList2);
		System.out.println("");
		System.out.println("-----------------------------");

		// 特定の文字列を含むかどうか
		System.out.println("anyMatch : " + stringList1.stream().anyMatch(i -> i.equals("neko")));
		System.out.println("allMatch : " + stringList1.stream().allMatch(i -> i.equals("neko")));
		System.out.println("noneMatch : " + stringList1.stream().noneMatch(i -> i.equals("inu")));

		// 重複する要素を削除する
		stringList2.stream().distinct().forEach(i -> System.out.print(i));
		System.out.println("");

		// 要素の個数を制限する
		stringList2.stream().limit(10).forEach(i -> System.out.print(i));
		System.out.println("");

		System.out.println("=============================");
		// reduceを使う
		List<Integer> intList2 = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
		List<String> stringList3 = Arrays.asList("aa", "bb", "cc", "dd");
		
		/* 引数が1個の時 */
		Optional<Integer> result0 = intList2.stream().reduce((accum, value) -> {
			return accum + value;
		});
		System.out.println("引数1個(Integer)：" + result0.orElse(0));
		
		Optional<String> result1 = stringList3.stream().reduce((accum, value) -> {
			return accum + "-" + value;
		});
		System.out.println("引数1個(String)：" + result1.orElse(""));

		/* 引数が2個の時 */
		BinaryOperator<String> operation = (accum, value) -> {
			return accum + "-" + value;
		};
		String result2 = stringList3.stream().reduce("initial", operation); 
		System.out.println("引数2個：" + result2);

	}
}
