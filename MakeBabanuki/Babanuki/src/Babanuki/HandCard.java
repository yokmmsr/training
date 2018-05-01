package Babanuki;

import java.util.ArrayList;
import java.util.List;

public class HandCard {
	private List<String> card = new ArrayList<>();
	private int playerNumber;

	// コンストラクタ
	HandCard(List<String> card, int playerNumber) {
		this.card = card;
		this.playerNumber = playerNumber;
	}
	
	//セッター
	public void setHandCard(List<String> card) {
		this.card = card;
	}

	// 手札を表示するメソッド
	public void showHandCard() {
		System.out.println(this.card);

	}
}
