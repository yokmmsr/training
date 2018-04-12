package BingoGame;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BingoCard {
	private int sideSize;
	private int bingoCount = 0;
	private List<String> randomNumbers = new ArrayList<>(); /* ランダムな数字を生成するためのList */
	private List<List<String>> cardNumbers = new ArrayList<>(); /* N x N ビンゴカードの番号を格納するList */
	final String hitMarker = "○";

	// コンストラクタ
	BingoCard(int sideSize) {
		this.sideSize = sideSize;
	}

	// ランダムな数字生成用Listのゲッター
	public List<String> getRandomNumbers() {
		return this.randomNumbers;
	}

	// ビンゴカード番号のセッター
	public void setCardNumbers(List<List<String>> cardNumbers) {
		this.cardNumbers = cardNumbers;
	}

	// ビンゴした列数のゲッター
	public int getBingoCount() {
		return this.bingoCount;
	}

	// ビンゴ表の初期配置を決めるメソッド
	public void initialPlace() {
		for (int i = 1; i <= 100; i++) {
			this.randomNumbers.add(String.valueOf(i));
		}
		Collections.shuffle(this.randomNumbers);

		int num = 0;
		for (int i = 0; i < this.sideSize; i++) {
			List<String> columnNumbers = new ArrayList<String>(); /* listから5個ずつ数字を格納するList */
			for (int j = 0; j < this.sideSize; j++) {
				columnNumbers.add(this.randomNumbers.get(num));
				num++;
			}
			this.cardNumbers.add(columnNumbers);
		}
	}

	// 当選したかどうか判定・数字を更新するメソッド
	public void judgeHit(int lotNumber) {
		String n = String.valueOf(lotNumber);
		for (List<String> columnNumbers : this.cardNumbers) {
			int hitIndex = 0;
			for (String number : columnNumbers) {
				if (n.equals(number)) {
					System.out.println(n + "が当たりました !");
					columnNumbers.set(hitIndex, hitMarker);
					break;
				}
				hitIndex++;
			}
		}
	}

	// ビンゴしているかどうか判定するメソッド
	public boolean judgeBingo() {
		int hitCount = 0;
		for (List<String> columnNumbers : this.cardNumbers) { /* 横がそろっているかチェック */
			for (String number : columnNumbers) {
				if (!(number.equals(hitMarker)))
					break;
				hitCount++;
			}
			if (hitCount == this.sideSize)
				this.bingoCount++;
			hitCount = 0;
		}
		for (int j = 0; j < this.sideSize; j++) { /* 縦がそろっているかチェック */
			for (List<String> rowNumbers : this.cardNumbers) {
				if (!(rowNumbers.get(j).equals(hitMarker)))
					break;
				hitCount++;
			}
			if (hitCount == this.sideSize)
				this.bingoCount++;
			hitCount = 0;
		}
		for (int i = 0; i < this.sideSize; i++) { /* 斜め↘︎がそろっているかチェック */
			if (!(this.cardNumbers.get(i).get(i).equals(hitMarker)))
				break;
			hitCount++;
			if (hitCount == this.sideSize)
				this.bingoCount++;
		}
		hitCount = 0;

		for (int i = 0; i < this.sideSize; i++) { /* 斜め↗︎がそろっているかチェック */
			if (!(this.cardNumbers.get(i).get(this.sideSize - i - 1).equals(hitMarker)))
				break;
			hitCount++;
			if (hitCount == this.sideSize)
				this.bingoCount++;
		}
		hitCount = 0;

		return this.bingoCount > 0;
	}

	// 表形式で表示するメソッド
	public void showTable() {
		System.out.print(" ┌");
		for (int i = 0; i < this.sideSize * 2; i++)
			System.out.print(" -");
		System.out.println(" ┐");

		for (List<String> columnNumbers : this.cardNumbers) {
			System.out.print(" │");
			for (String number : columnNumbers) {
				System.out.printf("%4s", number); /* 4桁表示（空白埋め） */
			}
			System.out.println(" │");
		}
		System.out.print(" └");
		for (int i = 0; i < this.sideSize * 2; i++)
			System.out.print(" -");
		System.out.println(" ┘");
	}
}
