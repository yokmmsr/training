package BingoGame;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		int sideSize = 5;
		
		Scanner scanner = new Scanner(System.in);
		BingoCard card = new BingoCard(sideSize);
		Lottery lot = new Lottery();
		
		card.initialPlace();
		card.showTable();

		int lotCount = 1;
		boolean bingo = false;
		while(!bingo) {
			lot.lottery();
			System.out.println("");
			System.out.println(lotCount + "回目：当選番号は" + lot.getLotNumber() + "です");
			card.judgeHit(lot.getLotNumber());
			card.showTable();
			card.judgeBingo();
			lotCount++;
			if(card.getBingo()) {
				System.out.println("ビンゴしたのでおわり");
				break;
			}
//			System.out.print("終了しますか？（0：終了）：");
//			if(scanner.nextInt() == 0) break;
		}
	}	
}
