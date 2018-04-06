package BicycleAndCar;

public class Bicycle {
	private String name;
	private String color;
	private int distance;
	
	//コンストラクタ
	Bicycle(String name, String color, int distance) {
		this.name = name;
		this.color = color;
		this.distance = distance;
	}
	
	//走行するメソッド
	public void run(int dis, int count) {
		System.out.println(count + "回目：" + dis + "km走ります");
		this.distance += dis;
		System.out.println("走行距離：" + this.distance + "km");
	}
	
	//情報出力メソッド
	public void printData() {
		System.out.println("【自転車の情報】");
		System.out.println("名前：" + this.name);
		System.out.println("色：" + this.color);
		System.out.println("走行距離：" + this.distance + "km");
		
	} 
}
