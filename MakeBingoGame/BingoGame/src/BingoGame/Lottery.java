package BingoGame;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Lottery {
	private List<Integer> storedNumbers = new ArrayList<>();
	private int lotteryNumber;

	// コンストラクタ
	Lottery() {
		for (int i = 1; i <= 100; i++) {
			this.storedNumbers.add(i);
		}
	}

	// 当選番号を一個ずつ返すメソッド
	public int lottery() {
		Collections.shuffle(this.storedNumbers);
		this.lotteryNumber = this.storedNumbers.get(0);
		this.storedNumbers.remove(0);
		return this.lotteryNumber;
	}
}
