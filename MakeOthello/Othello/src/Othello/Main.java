package Othello;

import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
		int sideSize = 8;
		int endOfStones = (int) Math.pow(sideSize, 2) - 4; /* 最初に4個石が置かれるので全マス数-4 */

		Board board = new Board(sideSize);
		board.createSquares();
		board.showSquares();

		List<Player> players = new ArrayList<>();
		for (int i = 1; i <= 2; i++) {
			players.add(new Player(i));
		}

		Scanner scanner = new Scanner(System.in);
		int putStoneCount = 0;
		int putStoneSquare;
		while (putStoneCount < endOfStones) {
			for (Player player : players) {
				System.out.println("【" + (putStoneCount + 1) + "手目】");
				while (true) {
					System.out.print("＜" + player.getPlayerName() + "＞どこに石を置きますか？：");
					putStoneSquare = scanner.nextInt();
					if (putStoneSquare > 0 && putStoneSquare <= 64) {
						if (board.canPutStone(putStoneSquare)) {
							board.checkSandwitchedStone(putStoneSquare, player.getPlayerNumber());
							if (board.isStoneSandwiched()) {
								board.turnOverStone(putStoneSquare, player.getPlayerNumber());
								break;
							} else {
								System.out.println("挟める石がないので置けません！！！");
							}
						} else {
							System.out.println(putStoneSquare + "番は既に石が置かれているので置けません！！！");
						}
					} else {
						System.out.println("1〜64しか指定できません！！！");
					}
				}
				putStoneCount++;
				board.showSquares();
				board.countStones();
			}
		}
		System.out.println();
	}
}