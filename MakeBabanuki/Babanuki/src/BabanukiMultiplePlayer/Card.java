package BabanukiMultiplePlayer;

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

public class Card {
	private String cardMark;
	private int cardIndex;

	// コンストラクタ
	Card(String cardMark, int cardIndex) {
		this.cardMark = cardMark;
		this.cardIndex = cardIndex;
	}
	Card() { /* 特に何もしない、Card型のオブジェクトだけ用意する時 */
	}

	// ゲッター
	public String getCardMark() {
		return this.cardMark;
	}

	public int getCardIndex() {
		return this.cardIndex;
	}

	// JOKERを引いたか判定するメソッド
	public boolean isJoker() {
		return "JOKER".equals(this.cardMark);
	}

	// カードを表示するtoStringメソッド
	public String toString() {
		if (isJoker()) {
			return this.getCardMark() + " ";
		} else {
			return this.getCardMark() + String.valueOf(this.getCardIndex()) + " ";
		}
	}
}
