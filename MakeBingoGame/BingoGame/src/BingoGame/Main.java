package BingoGame;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.print("ビンゴカードのサイズを決めてください：");
		int sideSize;
		do {
			sideSize = scanner.nextInt();
			if(sideSize > 0 && sideSize <= 10) break;
			System.out.print("カードのサイズは1〜10の範囲しか選べません！：");
		} while(true);
		System.out.println(sideSize + "×" + sideSize + "マスのビンゴを始めます");
		BingoCard card = new BingoCard(sideSize);
		Lottery lot = new Lottery();
		card.initialPlace();
		card.showTable();

		int lotCount = 0;
		boolean bingo = false;
		while(!bingo) {
			System.out.print("番号を引きますか？（1:引く　0：終了）：");
			if(scanner.nextInt() == 0) break;
			lot.lottery();
			lotCount++;
			System.out.println("");
			System.out.println(lotCount + "回目：当選番号は" + lot.getLotNumber() + "です");
			card.judgeHit(lot.getLotNumber());
			card.showTable();
			card.judgeBingo();
			if(card.getBingo()) {
				System.out.println(card.getBingoCount() + "ビンゴ！！！");
				System.out.println("ビンゴしたのでおわり");
				break;
			}
		}
	}	
}