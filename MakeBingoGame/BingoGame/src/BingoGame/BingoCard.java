package BingoGame;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BingoCard {
	private int sideSize;
	private boolean bingo = false;
	private int bingoCount = 0;
	private List<List<String>> cardNumbers = new ArrayList<>();	/* N x N ビンゴカードの番号を格納するList */
	
	//コンストラクタ
	BingoCard(int sideSize) {
		this.sideSize = sideSize;
	}
	
	//ビンゴカード番号のゲッター、セッター
	public List<List<String>> getCardNumbers() {
		return cardNumbers;
	}
	public void setCardNumbers(List<List<String>> cardNumbers) {
		this.cardNumbers = cardNumbers;
	}
	//ビンゴ判定のゲッター
	public boolean getBingo() {
		return this.bingo;
	}
	
	//ビンゴ表の初期配置を決めるメソッド
	public void initialPlace() {
		List<String> list = new ArrayList<String>();			/* 1~100の数字を格納しておくList */
		for(int i = 1; i <= 100; i++) {
			list.add(String.valueOf(i));
		}
		Collections.shuffle(list); /* 1~100をランダムに並び替え */
		
		int num = 0;
		for(int i = 0; i < this.sideSize; i++) {
			List<String> columnNumbers = new ArrayList<String>();	/* listから5個ずつ数字を格納するList */
			for(int j = 0; j < this.sideSize; j++) {
				columnNumbers.add(list.get(num));
				num++;
			}
			this.cardNumbers.add(columnNumbers);
		}
	}
	
	//当選したかどうか判定・数字を更新するメソッド
	public void judgeHit(int lotNumber) {
		for(int i = 0; i < this.sideSize; i++) {
			for(int j = 0; j < this.sideSize; j++) {
				String n = String.valueOf(lotNumber); 
				if(n.equals(this.cardNumbers.get(i).get(j))) {
					System.out.println(n + "が当たりました !");
					cardNumbers.get(i).set(j, "○");
					break;
				}
			}
		}
	}
	
	//ビンゴしているかどうか判定するメソッド
	public void judgeBingo() {
		int hitCount = 0;
		for(int i = 0; i < this.sideSize; i++) { 				/* 横がそろっているかチェック */
			for(int j = 0; j < this.sideSize; j++) {
				if(!(this.cardNumbers.get(i).get(j).equals("○"))) break;
				hitCount++;
			}
			if(hitCount == this.sideSize) this.bingoCount++;
			hitCount = 0;
		}
		for(int j = 0; j < this.sideSize; j++) {				/* 縦がそろっているかチェック */
			for(int i = 0; i < this.sideSize; i++) {
				if(!(this.cardNumbers.get(i).get(j).equals("○"))) 	break;
				hitCount++;
			}
			if(hitCount == this.sideSize) this.bingoCount++;
			hitCount = 0;
		}
		for(int i = 0; i < this.sideSize; i++) { 				/* 斜め↘︎がそろっているかチェック */
			if(!(this.cardNumbers.get(i).get(i).equals("○"))) break;
			hitCount++;
			if(hitCount == this.sideSize) this.bingoCount++;
		}
		hitCount = 0;
		
		for(int i = 0; i < this.sideSize; i++) { 				/* 斜め↗︎がそろっているかチェック */
			if(!(this.cardNumbers.get(i).get(this.sideSize-i-1).equals("○"))) break;
			hitCount++;
			if(hitCount == this.sideSize) this.bingoCount++;
		}
		hitCount = 0;
		
		if(this.bingoCount > 0) {
			System.out.println(bingoCount + "ビンゴしました！！！");
			this.bingo = true;	
		} else {
			this.bingo = false;	
		}
	}
	
	//表形式で表示するメソッド
	public void showTable() {
		System.out.println(" ┌ ー ー ー ー ー ー ー ー ┐");
		for(int i = 0; i < this.sideSize; i++) {
			System.out.print(" │");
			for(int j = 0; j < this.sideSize; j++) {
				System.out.printf("%4s", this.cardNumbers.get(i).get(j));		/* 4桁表示（空白埋め） */
			}
			System.out.println(" 　│");
		} 
		System.out.println(" └ ー ー ー ー ー ー ー ー ┘");		
	}
}
