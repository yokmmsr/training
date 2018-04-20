package Othello;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		int sideSize = 8;
		int endOfSquares = (int) Math.pow(sideSize, 2);

		Board board = new Board(sideSize);
		board.createSquares();
		board.showSquares();

		Player firstPlayer = new Player(1);
		Player secondPlayer = new Player(2);

		Scanner scanner = new Scanner(System.in);
		int putStoneCount = 0;
		int putStoneSquare;
		while (putStoneCount < endOfSquares) {
			while (true) {
				System.out.print("＜先手＞どこに石を置きますか？：");
				putStoneSquare = scanner.nextInt();
				if (putStoneSquare > 0 && putStoneSquare <= 64) {
					break;
				}
				System.out.println("1〜64しか指定できません！！！");
			}
			board.changeSquareStatus(putStoneSquare, firstPlayer.getPlayerNumber());
			putStoneCount++;
			board.showSquares();

			while (true) {
				System.out.print("＜後手＞どこに石を置きますか？：");
				putStoneSquare = scanner.nextInt();
				if (putStoneSquare > 0 && putStoneSquare <= 64) {
					break;
				}
				System.out.println("1〜64しか指定できません！！！");
			}			board.changeSquareStatus(putStoneSquare, secondPlayer.getPlayerNumber());
			putStoneCount++;
			board.showSquares();
		}
	}

}
