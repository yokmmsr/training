package Othello;

import java.util.Scanner;

public class Player {
	private int playerNumber;
	private int putStoneSquare;

	// コンストラクタ
	Player(int playerNumber) {
		this.playerNumber = playerNumber;
	}

	// ゲッター
	public int getPlayerNumber() {
		return this.playerNumber;
	}

}
