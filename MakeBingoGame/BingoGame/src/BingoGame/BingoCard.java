package BingoGame;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BingoCard {
	private int iend;
	private int jend;
	
	//コンストラクタ
	BingoCard(int iend, int jend) {
		this.iend = iend;
		this.jend = jend;
	}
	
	//ビンゴ表の初期配置を決めるメソッド
	public void iniPlace() {
		ArrayList<Integer> list = new ArrayList<Integer>(100);			/* 1~100の数字を格納しておくList */
		for(int i = 1; i <= 100; i++) {
			list.add(i);
		}
		Collections.shuffle(list); /* 1~100をランダムに並び替え */
		
		int num = 0;
		ArrayList<ArrayList<Integer>> number2 = new ArrayList<>(5);	/* number1を5個ずつ格納するList */
		for(int i = 0; i < this.iend; i++) {
			ArrayList<Integer> number1 = new ArrayList<Integer>(5);			/* listから5個ずつ数字を格納するList */
			for(int j = 0; j < this.jend; j++) {
				number1.add(list.get(num));
				num++;
			}
			number2.add(number1);
		}

		showTable(number2);
//		return number;
	}
	
	//表形式で表示するメソッド
	public void showTable(ArrayList<ArrayList<Integer>> number) {
		System.out.println(" ┌ ー ー ー ー ー ー ー ー ┐");
		for(int i = 0; i < this.iend; i++) {
			System.out.print(" │");
			for(int j = 0; j < this.jend; j++) {
				System.out.printf("%4d", number.get(i).get(j));		/* 4桁表示（空白埋め） */
			}
			System.out.println(" 　│");
		} 
		System.out.println(" └ ー ー ー ー ー ー ー ー ┘");		
	}
	
	
}
