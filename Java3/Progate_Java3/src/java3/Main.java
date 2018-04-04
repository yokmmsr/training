package java3;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		System.out.print("名前：");
		String firstName = scanner.next();
		System.out.print("苗字：");
		String lastName = scanner.next();
		System.out.print("年齢：");
		int age = scanner.nextInt();
		System.out.print("身長(m)：");
		double height = scanner.nextDouble();
		System.out.print("体重(kg)：");
		double weight = scanner.nextDouble();
		
		Person.printData(Person.fullName(firstName, lastName), age, height, weight);

	}

}
