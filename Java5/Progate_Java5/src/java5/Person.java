package java5;

public class Person {
	private static int count = 0;
	private String firstName;
	private String middleName;
	private String lastName;
	private int age;
	private double height;
	private double weight;
	private String job;
	
	//インスタンスフィールドに値をセットするコンストラクタ
	Person(String firstName, String lastName, int age, double height, double weight, String job) {
		Person.count++;
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		this.age = age;
		this.height = height;
		this.weight = weight;
		this.job = job;
	}
	Person(String firstName, String middleName, String lastName, int age, double height, double weight, String job) {
		this(firstName, lastName, age, height, weight, job);
		Person.count++;
		this.middleName = middleName;
	}
	//middleNameのゲッター
	public String getMiddleName() {
		return this.middleName;
	}
	//middleNameのセッター
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	//jobのゲッター
	public String getJob() {
		return this.job;
	}
	//jobのセッター
	public void setJob(String job) {
		this.job = job;
	}
	
	public String fullName() {
		if (this.middleName == null) {
			return this.firstName + " " + this.lastName;
		} else {
			return this.firstName + " " + this.middleName + " " + this.lastName;
		}
	}
	
	public void printData() {
		System.out.println("私の名前は" + this.fullName() + "です");
		System.out.println("年齢は" + this.age + "歳です");
		System.out.println("BMIは" + Math.round(this.bmi()) + "です");
		System.out.println("仕事は" + this.job + "です");
	}
	
	public double bmi() {
		return this.weight / this.height / this.height;
	}
	
	public static void printCount() {
		System.out.println("合計" + Person.count + "人です");
	} 
	

}