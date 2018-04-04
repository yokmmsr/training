package selfIntro;

import java.util.Scanner;
import selfIntro.Person;

public class Main {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		System.out.print("何人分の情報を入力しますか：");
		int num = scanner.nextInt();
		int maxAge = 0;
		int totalAge = 0;
		for (int i = 1; i <= num; i++) {
			System.out.println(i + "人目");
			System.out.print("名前：");
			String firstName = scanner.next();
			System.out.print("名字：");
			String lastName = scanner.next();
			System.out.print("年齢：");
			int age = scanner.nextInt();
			if (maxAge < age) {
				maxAge = age;
			}
			totalAge += age;
			System.out.print("身長(m)：");
			double height = scanner.nextDouble();
			System.out.print("体重(kg)：");
			double weight = scanner.nextDouble();
			
			Person.printData(Person.fullName(firstName, lastName), age, height, weight);
		}
		System.out.println("最高年齢は" + maxAge + "歳です");
		System.out.println("平均年齢は" + totalAge / num + "歳です");
	}
}
