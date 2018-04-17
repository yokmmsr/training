package Babanuki;

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

public class Card {
	private List<String> allCards = new ArrayList<>();

	// コンストラクタ
	Card() {
	}

	// ゲッター
	public List<String> getAllCards() {
		return this.allCards;
	}
}
