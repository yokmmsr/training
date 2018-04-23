package Othello;

import java.util.Scanner;

public class Player {
	private int playerNumber;
	private int putStoneSquare;
	private String playerName;

	// コンストラクタ
	Player(int playerNumber) {
		this.playerNumber = playerNumber;
		if(playerNumber == 1) {
			this.playerName = "先手";
		} else {
			this.playerName = "後手";
		}
	}

	// ゲッター
	public int getPlayerNumber() {
		return this.playerNumber;
	}
	
	public String getPlayerName() {
		return this.playerName;
	}

}
