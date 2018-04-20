package Othello;

import java.util.ArrayList;
import java.util.List;

public class Board {
	final String BLACK_STONE = "⚫︎"; /* 黒は先手 */ 
	final String WHITE_STONE = "⚪︎"; /* 白は後手 */ 
	final String BLANK = " "; /* 石は置かれていない */ 
	private int sideSize;
	private List<List<Square>> allSquares;

	// コンストラクタ
	Board(int sideSize) {
		this.sideSize = sideSize;
	}

	// ボード上のマス目の番号、石の状態を表示するメソッド
	public void showSquares() {
		System.out.println(" ┌ - - - - - - - - - - - - ┐");
		for (List<Square> squaresRow : this.allSquares) {
			System.out.print(" │");
			for (Square square : squaresRow) {
				if (square.isStoneExist()) {
					System.out.printf("%3s", square.getStone()); /* 石が置かれている */
				} else {
					System.out.printf("%3d", square.getSquareNumber()); /* 石が置かれていない（数字を表示） */
				}
			}
			System.out.println(" │");
		}
		System.out.println(" └ - - - - - - - - - - - - ┘");

	}

	// 8x8のマス目を作成するメソッド
	public void createSquares() {
		List<List<Square>> allSquares = new ArrayList<>();
		int squareNumber = 1;
		for (int i = 0; i < this.sideSize; i++) {
			List<Square> rowSquares = new ArrayList<>();
			for (int j = 0; j < this.sideSize; j++) {
				Square square = new Square(squareNumber, BLANK);
				rowSquares.add(square);
				squareNumber++;
			}
			allSquares.add(rowSquares);
		}
		this.allSquares = allSquares;
	}
	
	//マスの状態を変更するメソッド
	public void changeSquareStatus(int putStoneSquare, int playerNumber) {
		for(List<Square> rowSquares : this.allSquares) {
			for(Square square : rowSquares) {
				if (square.getSquareNumber() == putStoneSquare) {
					square.changeStoneStatus(playerNumber);
				}
			}
		}
	}

}
