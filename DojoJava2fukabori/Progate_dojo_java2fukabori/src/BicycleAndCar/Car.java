package BicycleAndCar;

public class Car {
	private String name;
	private String color;
	private int distance;
	private int fuel;
	private int flag;
	
	//コンストラクタ
	Car(String name, String color, int distance, int fuel) {
		this.name = name;
		this.color = color;
		this.distance = distance;
		this.fuel = fuel;
	}
	
	//走行するメソッド
	public int run(int dis, int count, int flag) {
		Breakdown brkdwn = new Breakdown();
		
		System.out.println(count + "回目：" + dis + "km走ります");
		int aa = brkdwn.judgeRepair(flag);
		if(aa == 0) {
			if(dis <= this.fuel) {
				this.distance += dis;
				this.fuel -= dis;
			} else {
				System.out.println("ガソリンが足りません");
			}				
		}
		System.out.println("走行距離：" + this.distance + "km");
		System.out.println("ガソリン量：" + this.fuel + "L");
		if(aa == 0) {
			flag= brkdwn.judgeBreak(this.name);
		}
		return flag;
	}
	
	//給油するメソッド
	public void charge(int chargeFuel) {
		if(chargeFuel <= 0) {
			System.out.println("給油できません");
		} else if (chargeFuel + this.fuel >= 100) {
			System.out.println("満タンまで給油します");
			this.fuel = 100;
		} else {
			this.fuel += chargeFuel;
		}
		System.out.println("ガソリン量：" + this.fuel + "L");
	}
	
	//情報出力メソッド
	public void printData() {
		System.out.println("【車の情報】");
		System.out.println("名前：" + this.name);
		System.out.println("色：" + this.color);
		System.out.println("走行距離：" + this.distance + "km");
		System.out.println("ガソリン量：" + this.fuel + "L");
	} 
	
	//名前のゲッター
	public String getCarName() {
		return this.name;
	}

}
