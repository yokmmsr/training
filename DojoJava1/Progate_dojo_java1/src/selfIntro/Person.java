package selfIntro;

public class Person {
	public static void printData(String name, int age, double height, double weight) {
		System.out.println("私の名前は" + name + "です");
		System.out.println("私の年齢は" + age + "歳です");
		if (age >= 20) {
			System.out.println("成年者です");
		} else {
			System.out.println("未成年者です");			
		}
		System.out.println("私の身長は" + height + "mです");
		System.out.println("私の体重は" + weight + "kgです");
		double bmi = Math.round(bmi(height, weight));
		System.out.println("私のBMIは" + bmi + "です");
		if (isHealthy(bmi)) {
			System.out.println("健康です");
		} else {
			System.out.println("健康ではありません");			
		}
	}
	public static String fullName(String firstName, String lastName) {
		return firstName + " " + lastName;
	}
	
	public static double bmi(double height, double weight) {
		return weight / height / height;
	}
	
	public static boolean isHealthy(double bmi) {
		return bmi >= 18.5 && bmi < 25;
	}
}
