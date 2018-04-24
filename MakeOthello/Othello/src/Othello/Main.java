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
		int putStoneCount = 0;/* 置いた石の総数 */
		int putStoneSquare; /* 石を置くマスの番号 */
		int stonesDifference = 0; /* 石の個数差（先手の個数-後手の個数） */
		int passTimes = 0;

		/* 以下、交互に石を置いていく操作 */
		othelloLoop: while (putStoneCount < endOfStones) {
			for (Player player : players) {
				System.out.println("【" + (putStoneCount + 1) + "手目】");
				String playerName = player.getPlayerName();
				int playerNumber = player.getPlayerNumber();

				while (true) {
					System.out.print("＜" + playerName + "＞どこに石を置きますか？：");
					putStoneSquare = scanner.nextInt();
					if (putStoneSquare > 0 && putStoneSquare <= 64) {
						if (board.canPutStone(putStoneSquare)) {
							board.checkSandwitchedStone(putStoneSquare, playerNumber);
							if (board.isStoneSandwiched()) {
								board.turnOverStone(putStoneSquare, playerNumber);
								putStoneCount++;
								passTimes = 0;
								break;
							} else {
								System.out.println("挟める石がないので置けません！！！");
								System.out.print("このターンをパスしますか？（0を押下するとパス）：");
								int passThisTurn = scanner.nextInt();
								if (passThisTurn == 0) {
									System.out.println(playerName + "がパスしました");
									passTimes++;
									break;
								}
							}
						} else {
							System.out.println(putStoneSquare + "番は既に石が置かれているので置けません！！！");
						}
					} else {
						System.out.println("1〜64しか指定できません！！！");
					}
				}
				board.showSquares();
				stonesDifference = board.countStones();
				if (passTimes == 2) { /* 両者続けてパスしたら石を置けなくなったと判断して終了 */
					System.out.println("両者石を置ける場所がないので終了します");
					break othelloLoop;
				}
			}
		}
		/* ゲーム終了 */
		judgeWinner(stonesDifference);
		System.out.println("おわり");
	}

	// 勝者を判定するメソッド
	public static void judgeWinner(int stonesDifference) {
		if (stonesDifference > 0) {
			System.out.println("先手のプレイヤーが勝ちました！！");
		} else if (stonesDifference < 0) {
			System.out.println("後手のプレイヤーが勝ちました！！");
		} else {
			System.out.println("引き分けです");
		}
	}
}