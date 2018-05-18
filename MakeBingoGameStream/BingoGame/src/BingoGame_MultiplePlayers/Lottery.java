package BingoGame_MultiplePlayers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Lottery {
	private List<Integer> storedNumbers = new ArrayList<>();

	// コンストラクタ
	Lottery() {
		for (int i = 1; i <= 100; i++) {
			this.storedNumbers.add(i);
		}
	}

	// 当選番号を一個ずつ返すメソッド
	public int lottery() {
		Collections.shuffle(this.storedNumbers);
		int lotteryNumber = this.storedNumbers.get(0);
		this.storedNumbers.remove(0);
		return lotteryNumber;
	}
}
