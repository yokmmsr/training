package Babanuki;

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

	// ゲッター
	public String getCardMark() {
		return this.cardMark;
	}

	public int getCardIndex() {
		return this.cardIndex;
	}

}
