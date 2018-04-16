package BingoGame_MultiplePlayers;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Main {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.print("ビンゴカードのサイズを決めてください：");
		int sideSize = scanner.nextInt();
		setSideSize(sideSize);
		System.out.print("何人でプレイしますか？：");
		int playerNumber = scanner.nextInt();
		setPlayerNumber(playerNumber);

		/* プレイヤー人数分のcardオブジェクトを生成 */
		List<BingoCard> card = new ArrayList<>();
		for (int i = 0; i < playerNumber; i++) {
			card.add(new BingoCard(sideSize));
			card.get(i).initialPlace();
			System.out.println("");
			System.out.println("【Player" + (i + 1) + "】");
			card.get(i).showTable();
		}

		/* 当選番号を抽選する */
		Lottery lot = new Lottery();
		int lotCount = 0;
		while (true) {
			System.out.print("番号を引きますか？（1：引く 0：終了する）：");
			if (scanner.nextInt() == 0) {
				break;
			}
			int lotteryNumber = getLotteryNumber(lot, lotCount);
			int bingoPlayerCount = checkBingo(card, playerNumber, lotteryNumber);
			if (bingoPlayerCount > 0) {
				System.out.println("");
				System.out.println(bingoPlayerCount + "人ビンゴしたのでおわり");
				break;
			}
		}
	}

	// ビンゴカードのサイズが適切か確認するメソッド
	public static void setSideSize(int sideSize) {
		while (true) {
			if (sideSize > 0 && sideSize <= 10) {
				break;
			}
			System.out.print("カードのサイズは1〜10の範囲しか選べません！：");
		}
		System.out.println(sideSize + "×" + sideSize + "マスのビンゴを始めます");
	}

	// プレイ人数が適切か確認するメソッド
	public static void setPlayerNumber(int playerNumber) {
		while (true) {
			if (playerNumber > 0) {
				break;
			}
			System.out.print("プレイヤーは1人以上です！：");
		}
	}

	// 当選番号を取得・表示するメソッド
	public static int getLotteryNumber(Lottery lot, int lotCount) {
		int lotteryNumber = lot.lottery();
		lotCount++;
		System.out.println();
		System.out.println(lotCount + "回目：当選番号は" + lotteryNumber + "です");
		return lotteryNumber;
	}

	// ビンゴした人数を返すメソッド
	public static int checkBingo(List<BingoCard> card, int playerNumber, int lotteryNumber) {
		int bingoPlayerCount = 0;
		for (int i = 0; i < playerNumber; i++) {
			System.out.println("");
			System.out.println("【Player" + (i + 1) + "】");
			card.get(i).judgeHit(lotteryNumber);
			card.get(i).showTable();
			if (card.get(i).judgeBingo()) {
				System.out.println(card.get(i).getBingoCount() + "ビンゴ！！！");
				bingoPlayerCount++;
			}
		}
		return bingoPlayerCount;
	}
}