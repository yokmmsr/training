package BingoGame;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Lottery {
	private List<String> lotNumbers = new ArrayList<>();

	// コンストラクタ
	Lottery(List<String> randomNumbers) {
		this.lotNumbers = randomNumbers;
		Collections.shuffle(this.lotNumbers);
	}

	// 当選番号一個ずつ返すメソッド
	public int lottery(int lotCount) {
		return Integer.parseInt(this.lotNumbers.get(lotCount));
	}
}
