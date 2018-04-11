package BingoGame;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		int iend = 5;
		int jend = 5;
		int count = 1;
		boolean bingo = false;
		
		Scanner scanner = new Scanner(System.in);
		BingoCard card = new BingoCard(iend, jend);
		Lottery lot = new Lottery();
		
		ArrayList<ArrayList<String>> number = new ArrayList<>(5);
		number = card.iniPlace();
		int lotNum;
		while(!bingo) {
			lotNum = lot.lottery(count);
			number = card.judgeHit(number, lotNum);
			card.showTable(number);
			bingo = card.judgeBingo(number);
			count++;
			if(bingo) {
				System.out.println("ビンゴしたのでおわり");
				break;
			}
			System.out.print("終了しますか？（0：終了）：");
			if(scanner.nextInt() == 0) break;
		}
	}	
}
