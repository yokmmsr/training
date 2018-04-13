package BingoGame;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.print("ビンゴカードのサイズを決めてください：");
		int sideSize;
		while (true) {
			sideSize = scanner.nextInt();
			if (sideSize > 0 && sideSize <= 10) {
				break;
			}
			System.out.print("カードのサイズは1〜10の範囲しか選べません！：");
		}
		System.out.println(sideSize + "×" + sideSize + "マスのビンゴを始めます");
		BingoCard card = new BingoCard(sideSize);
		card.initialPlace();
		card.showTable();

		Lottery lot = new Lottery();
		int lotCount = 0;
		while (true) {
			System.out.print("番号を引きますか？（1：引く 0：終了する）：");
			if (scanner.nextInt() == 0) {
				break;
			}
			int lotteryNumber = lot.lottery();
			lotCount++;
			System.out.println("");
			System.out.println(lotCount + "回目：当選番号は" + lotteryNumber + "です");
			card.judgeHit(lotteryNumber);
			card.showTable();
			if (card.judgeBingo()) {
				System.out.println(card.getBingoCount() + "ビンゴ！！！");
				System.out.println("ビンゴしたのでおわり");
				break;
			}
		}
	}
}