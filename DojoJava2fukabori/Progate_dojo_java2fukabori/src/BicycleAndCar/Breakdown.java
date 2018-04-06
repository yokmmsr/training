package BicycleAndCar;
import java.util.Scanner;

public class Breakdown {
	Scanner scanner = new Scanner(System.in);
	private int ff;
	private int flag;
	
	//故障するかどうか判定するメソッド
	public int judgeBreak(String name) {
		ff = (int)Math.floor(Math.random() * 10);
		if(ff > 5) {
			System.out.println(name + "が故障しました！！！！！！！");
			flag = 1;
		} 
		return flag;
	}
	
	//修理するかどうか判定するメソッド
	public int judgeRepair(int flag) {
		if(flag == 1) {
			System.out.println("修理に出さないと乗れません！！");
			System.out.print("修理に出しますか？（修理に出す場合は1を入力）：");
			if(scanner.nextInt() == 1) {
				System.out.println("車を修理しました！");
				flag = 0;
			} else {
				System.out.println("今は修理しません");
				flag = 1;
			}
		}
		return flag;
	}
	
}
