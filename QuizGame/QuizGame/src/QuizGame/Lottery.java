package QuizGame;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Lottery {
	private List<Integer> storedNumbers = new ArrayList<>();

	// コンストラクタ
	Lottery(int numberOfStoredQuestions) {
		for (int i = 1; i <= numberOfStoredQuestions; i++) {
			this.storedNumbers.add(i);
		}
	}

	// 問題の番号を一個ずつ返すメソッド
	public int apply() {
		Collections.shuffle(this.storedNumbers);
		int questionNumber = this.storedNumbers.get(0);
		this.storedNumbers.remove(0);
		return questionNumber;
	}
}
