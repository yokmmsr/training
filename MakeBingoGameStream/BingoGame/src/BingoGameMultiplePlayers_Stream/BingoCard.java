package BingoGameMultiplePlayers_Stream;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BingoCard {
	private int sideSize;
	private int bingoCount = 0;
	private List<String> randomNumbers = new ArrayList<>(); /* ランダムな数字を生成するためのList */
	private List<List<String>> cardNumbers = new ArrayList<>(); /* N x N ビンゴカードの番号を格納するList */
	final String HIT_MARKER = "○";

	// コンストラクタ
	BingoCard(int sideSize) {
		this.sideSize = sideSize;
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
	public void judgeHit(int lotteryNumber) {
		String n = String.valueOf(lotteryNumber);
		for (List<String> columnNumbers : this.cardNumbers) {
			int hitIndex = 0;
			for (String number : columnNumbers) {
				if (n.equals(number)) {
					System.out.println(n + "が当たりました !");
					columnNumbers.set(hitIndex, HIT_MARKER);
					break;
				}
				hitIndex++;
			}
		}
	}

	// ビンゴしているかどうか判定するメソッド
	public boolean judgeBingo() {
		int hitCount = 0;
		for (List<String> rowNumbers : this.cardNumbers) { /* 横がそろっているかチェック */
			hitCount = 0;
			hitCount = (int) rowNumbers.stream().filter(n -> n.equals(HIT_MARKER)).count();
			if (hitCount == this.sideSize) {
				this.bingoCount++;
			}
		}

		for (int j = 0; j < this.sideSize; j++) { /* 縦がそろっているかチェック */
			hitCount = 0;
			for (List<String> rowNumbers : this.cardNumbers) {
				hitCount += rowNumbers.stream().skip(j).limit(1).filter(n -> n.equals(HIT_MARKER)).count();
			}
			if (hitCount == this.sideSize) {
				this.bingoCount++;
			}
		}

		int takeIndex = 0;
		hitCount = 0;
		for (List<String> rowNumbers : this.cardNumbers) { /* 斜め↘︎がそろっているかチェック */
			hitCount += rowNumbers.stream().skip(takeIndex).limit(1).filter(n -> n.equals(HIT_MARKER)).count();
			takeIndex++;
		}
		if (hitCount == this.sideSize) {
			this.bingoCount++;
		}

		takeIndex = this.sideSize - 1;
		hitCount = 0;
		for (List<String> rowNumbers : this.cardNumbers) { /* 斜め↗︎がそろっているかチェック */
			hitCount += rowNumbers.stream().skip(takeIndex).limit(1).filter(n -> n.equals(HIT_MARKER)).count();
			takeIndex--;
		}
		if (hitCount == this.sideSize) {
			this.bingoCount++;
		}

		return this.bingoCount > 0;
	}

	// 表形式で表示するメソッド
	public void showTable() {
		System.out.print(" ┌");
		for (int i = 0; i < this.sideSize * 2; i++) {
			System.out.print(" -");
		}
		System.out.println(" ┐");

		for (List<String> columnNumbers : this.cardNumbers) {
			System.out.print(" │");
			/* 4桁表示（空白埋め） */
			columnNumbers.stream().forEach(number -> System.out.printf("%4s", number));
			System.out.println(" │");
		}
		System.out.print(" └");
		for (int i = 0; i < this.sideSize * 2; i++) {
			System.out.print(" -");
		}
		System.out.println(" ┘");
	}
}
