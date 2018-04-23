package Othello;

import java.util.ArrayList;
import java.util.List;

public class Board {
	final String BLACK_STONE = "[B]"; /* 黒は先手 */
	final String WHITE_STONE = "[W]"; /* 白は後手 */
	final String BLANK = " "; /* 石は置かれていない */
	private int sideSize;
	private int sandwichCount = 0; /* 異なる色の石を挟む回数 */
	private List<List<Square>> allSquares;

	// コンストラクタ
	Board(int sideSize) {
		this.sideSize = sideSize;
	}

	// 1次元的な番号を2次元的な行番号に変換するメソッド
	public int dimension1to2Row(int putStoneSquare) {
		return (int) Math.ceil((putStoneSquare - 1) / 8);
	}

	// 1次元的な番号を2次元的な列番号に変換するメソッド
	public int dimension1to2Column(int putStoneSquare) {
		if (putStoneSquare % this.sideSize == 0) {
			return this.sideSize - 1;
		} else {
			return (int) Math.ceil(putStoneSquare % this.sideSize) - 1;
		}
	}

	// ボード上のマス目の番号、石の状態を表示するメソッド
	public void showSquares() {
		System.out.println(" ┌ - - - - - - - - - - - - - - - - ┐");
		for (List<Square> squaresRow : this.allSquares) {
			System.out.print(" │");
			for (Square square : squaresRow) {
				if (square.isStoneExist()) {
					System.out.printf("%4s", square.getStone()); /* 石が置かれている */
				} else {
					System.out.printf("%4d", square.getSquareNumber()); /* 石が置かれていない（数字を表示） */
				}
			}
			System.out.println(" │");
		}
		System.out.println(" └ - - - - - - - - - - - - - - - - ┘");

	}

	// 8x8のマス目を作成するメソッド
	public void createSquares() {
		List<List<Square>> allSquares = new ArrayList<>();
		int squareNumber = 1;
		for (int i = 0; i < this.sideSize; i++) {
			List<Square> rowSquares = new ArrayList<>();
			for (int j = 0; j < this.sideSize; j++) {
				if (squareNumber == 28 || squareNumber == 37) { /* 最初に中央に置かれる黒石2個 */
					Square square = new Square(squareNumber, BLACK_STONE);
					rowSquares.add(square);
				} else if (squareNumber == 29 || squareNumber == 36) { /* 最初に中央に置かれる石2個 */
					Square square = new Square(squareNumber, WHITE_STONE);
					rowSquares.add(square);
				} else {
					Square square = new Square(squareNumber, BLANK);
					rowSquares.add(square);
				}
				squareNumber++;
			}
			allSquares.add(rowSquares);
		}
		this.allSquares = allSquares;
	}

	// 右、右上、上、左上、左、左下、下、右下方向で異なる色の石を挟んでいる回数を算定するメソッド
	// 挟まれる石の総数ではない！！
	public void checkSandwitchedStone(int putStoneSquare, int playerNumber) {
		int rowSquare = dimension1to2Row(putStoneSquare); /* 石を置いたマスの行番号 */
		int columnSquare = dimension1to2Column(putStoneSquare); /* 石を置いた場所の列番号 */
		String putStone = BLANK;
		if (playerNumber == 1) {
			putStone = BLACK_STONE; /* 先手player（number = 1）なら黒 */
		} else {
			putStone = WHITE_STONE; /* 後手player（number = 2）なら白 */
		}

		/* 石を置いたマスから→方向の確認︎ */
		List<Square> tempSquares = new ArrayList<>();
		for (int j = columnSquare + 1; j < this.sideSize; j++) {
			String searchStone = this.allSquares.get(rowSquare).get(j).getStone();
			if (BLANK.equals(searchStone)) {
				break;
			}
			if (putStone.equals(searchStone)) {
				for (Square square : tempSquares) {
					square.setSandwiched();
				}
				this.sandwichCount++;
				break;
			}
			tempSquares.add(this.allSquares.get(rowSquare).get(j)); /* 一時的にsearchStoneの情報を格納 */
		}

		/* 石を置いたマスから←方向の確認︎ */
		tempSquares = new ArrayList<>();
		for (int j = columnSquare - 1; j >= 0; j--) {
			String searchStone = this.allSquares.get(rowSquare).get(j).getStone();
			if (BLANK.equals(searchStone)) {
				break;
			}
			if (putStone.equals(searchStone)) {
				for (Square square : tempSquares) {
					square.setSandwiched();
				}
				this.sandwichCount++;
				break;
			}
			tempSquares.add(this.allSquares.get(rowSquare).get(j));
		}

		/* 石を置いたマスから↑方向の確認︎ */
		tempSquares = new ArrayList<>();
		for (int i = rowSquare - 1; i >= 0; i--) {
			String searchStone = this.allSquares.get(i).get(columnSquare).getStone();
			if (BLANK.equals(searchStone)) {
				break;
			}
			if (putStone.equals(searchStone)) {
				for (Square square : tempSquares) {
					square.setSandwiched();
				}
				this.sandwichCount++;
				break;
			}
			tempSquares.add(this.allSquares.get(i).get(columnSquare));
		}

		/* 石を置いたマスから↓方向の確認︎ */
		tempSquares = new ArrayList<>();
		for (int i = rowSquare + 1; i < this.sideSize; i++) {
			String searchStone = this.allSquares.get(i).get(columnSquare).getStone();
			if (BLANK.equals(searchStone)) {
				break;
			}
			if (putStone.equals(searchStone)) {
				for (Square square : tempSquares) {
					square.setSandwiched();
				}
				this.sandwichCount++;
				break;
			}
			tempSquares.add(this.allSquares.get(i).get(columnSquare));
		}

		/* 石を置いたマスから↗︎方向の確認︎ */
		tempSquares = new ArrayList<>();
		int j = columnSquare + 1;
		for (int i = rowSquare - 1; i >= 0; i--) {
			if (j == this.sideSize) {
				break;
			}
			String searchStone = this.allSquares.get(i).get(j).getStone();
			if (BLANK.equals(searchStone)) {
				break;
			}
			if (putStone.equals(searchStone)) {
				for (Square square : tempSquares) {
					square.setSandwiched();
				}
				this.sandwichCount++;
				break;
			}
			tempSquares.add(this.allSquares.get(i).get(j));
			j++;
		}

		/* 石を置いたマスから↘︎方向の確認︎ */
		j = columnSquare + 1;
		tempSquares = new ArrayList<>();
		for (int i = rowSquare + 1; i < this.sideSize; i++) {
			if (j == this.sideSize) {
				break;
			}
			String searchStone = this.allSquares.get(i).get(j).getStone();
			if (BLANK.equals(searchStone)) {
				break;
			}
			if (putStone.equals(searchStone)) {
				for (Square square : tempSquares) {
					square.setSandwiched();
				}
				this.sandwichCount++;
				break;
			}
			tempSquares.add(this.allSquares.get(i).get(j));
			j++;
		}

		/* 石を置いたマスから↖︎方向の確認︎ */
		j = columnSquare - 1;
		tempSquares = new ArrayList<>();
		for (int i = rowSquare - 1; i >= 0; i--) {
			if (j < 0) {
				break;
			}
			String searchStone = this.allSquares.get(i).get(j).getStone();
			if (BLANK.equals(searchStone)) {
				break;
			}
			if (putStone.equals(searchStone)) {
				for (Square square : tempSquares) {
					square.setSandwiched();
				}
				this.sandwichCount++;
				break;
			}
			tempSquares.add(this.allSquares.get(i).get(j));
			j--;
		}

		/* 石を置いたマスから↙︎︎方向の確認︎ */
		j = columnSquare - 1;
		tempSquares = new ArrayList<>();
		for (int i = rowSquare + 1; i < this.sideSize; i++) {
			if (j < 0) {
				break;
			}
			String searchStone = this.allSquares.get(i).get(j).getStone();
			if (BLANK.equals(searchStone)) {
				break;
			}
			if (putStone.equals(searchStone)) {
				for (Square square : tempSquares) {
					square.setSandwiched();
				}
				this.sandwichCount++;
				break;
			}
			tempSquares.add(this.allSquares.get(i).get(j));
			j--;
		}
//		turnOverStone();
	}

	// 異なる色の石を挟んでいるか確認するメソッド
	public boolean isStoneSandwiched() {
		boolean isSandwichCount = this.sandwichCount > 0;
//		System.out.println("sandwichCount:" + this.sandwichCount + " " +isSandwichCount);
		this.sandwichCount = 0;
		return isSandwichCount; /* 1方向でも挟めていればtrue */
	}

	// 挟んでいる石をひっくり返すメソッド
	public void turnOverStone(int putStoneSquare, int playerNumber) {
		int rowSquare = dimension1to2Row(putStoneSquare);
		int columnSquare = dimension1to2Column(putStoneSquare);
		this.allSquares.get(rowSquare).get(columnSquare).changePutStone(playerNumber);

		for (List<Square> rowSquares : this.allSquares) {
			for (Square square : rowSquares) {
				if (square.getIsSandwiched()) {
					square.changeSandwichedStone();
				}
			}
		}
	}

	// 石を置くことが出来るか判定するメソッド
	public boolean canPutStone(int putStoneSquare) {
		int rowSquare = dimension1to2Row(putStoneSquare);
		int columnSquare = dimension1to2Column(putStoneSquare);
		return !this.allSquares.get(rowSquare).get(columnSquare).isStoneExist();
	}

	// 石の数をカウントするメソッド
	public void countStones() {
		int blackStoneCount = 0;
		int whiteStoneCount = 0;
		for (List<Square> rowSquares : this.allSquares) {
			for (Square square : rowSquares) {
				if (square.getStone() == BLACK_STONE) {
					blackStoneCount++;
				} else if (square.getStone() == WHITE_STONE) {
					whiteStoneCount++;
				}
			}
		}
		System.out.println("先手◉：" + blackStoneCount);
		System.out.println("後手◎：" + whiteStoneCount);
		System.out.println("");
	}
}
