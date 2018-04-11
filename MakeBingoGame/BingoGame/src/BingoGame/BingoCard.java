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
	public ArrayList<ArrayList<String>> iniPlace() {
		ArrayList<String> list = new ArrayList<String>(100);			/* 1~100の数字を格納しておくList */
		for(int i = 1; i <= 100; i++) {
			list.add(String.valueOf(i));
		}
		Collections.shuffle(list); /* 1~100をランダムに並び替え */
		
		int num = 0;
		ArrayList<ArrayList<String>> number2 = new ArrayList<>(5);	/* number1を5個ずつ格納するList */
		for(int i = 0; i < this.iend; i++) {
			ArrayList<String> number1 = new ArrayList<String>(5);			/* listから5個ずつ数字を格納するList */
			for(int j = 0; j < this.jend; j++) {
				number1.add(list.get(num));
				num++;
			}
			number2.add(number1);
		}

		showTable(number2);
		return number2;
	}
	
	//当選したかどうか判定・数字を更新するメソッド
	public ArrayList<ArrayList<String>> judgeHit(ArrayList<ArrayList<String>> number, int lotNum) {
		for(int i = 0; i < this.iend; i++) {
			for(int j = 0; j < this.jend; j++) {
				String n = String.valueOf(lotNum); 
				if(n.equals(number.get(i).get(j))) {
					System.out.println(n + "が当たりました !");
					number.get(i).set(j, "○");
					break;
				}
			}
		}
		return number;
	}
	
	//ビンゴしているかどうか判定するメソッド
	public boolean judgeBingo(ArrayList<ArrayList<String>> number) {
		int bingoCount = 0;
		int hitCount = 0;
		for(int i = 0; i < this.iend; i++) { 				/* 横がそろっているかチェック */
			for(int j = 0; j < this.jend; j++) {
				if(!(number.get(i).get(j).equals("○"))) break;
				hitCount++;
			}
			if(hitCount == this.jend) bingoCount++;
			hitCount = 0;
		}
		for(int j = 0; j < this.jend; j++) {				/* 縦がそろっているかチェック */
			for(int i = 0; i < this.iend; i++) {
				if(!(number.get(i).get(j).equals("○"))) 	break;
				hitCount++;
			}
			if(hitCount == this.iend) bingoCount++;
			hitCount = 0;
		}
		for(int i = 0; i < this.iend; i++) { 				/* 斜め↘︎がそろっているかチェック */
			if(!(number.get(i).get(i).equals("○"))) break;
			hitCount++;
			if(hitCount == this.iend) bingoCount++;
		}
		hitCount = 0;
		
		for(int i = 0; i < this.iend; i++) { 				/* 斜め↗︎がそろっているかチェック */
			if(!(number.get(i).get(this.jend-i-1).equals("○"))) break;
			hitCount++;
			if(hitCount == this.iend) bingoCount++;
		}
		hitCount = 0;
		
		if(bingoCount > 0) {
			System.out.println(bingoCount + "ビンゴしました！！！");
			return true;	
		} else {
			return false;	
		}
	}
	
	//表形式で表示するメソッド
	public void showTable(ArrayList<ArrayList<String>> number) {
		System.out.println(" ┌ ー ー ー ー ー ー ー ー ┐");
		for(int i = 0; i < this.iend; i++) {
			System.out.print(" │");
			for(int j = 0; j < this.jend; j++) {
				System.out.printf("%4s", number.get(i).get(j));		/* 4桁表示（空白埋め） */
			}
			System.out.println(" 　│");
		} 
		System.out.println(" └ ー ー ー ー ー ー ー ー ┘");		
	}
}
