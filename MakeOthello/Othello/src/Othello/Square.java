package Othello;

public class Square {
	private int squareNumber;
	private String stone;
	private final String BLACK_STONE = "⚫︎"; /* 黒は先手 */
	private final String WHITE_STONE = "⚪︎"; /* 白は後手 */
	private final String BLANK = " "; /* 石は置かれていない */

	// コンストラクタ
	Square(int squareNumber, String stone) {
		this.squareNumber = squareNumber;
		this.stone = stone;
	}

	// ゲッター
	public int getSquareNumber() {
		return this.squareNumber;
	}

	public String getStone() {
		return this.stone;
	}

	// マスに石が置かれているか判定するメソッド
	public boolean isStoneExist() {
		return !(BLANK.equals(this.stone)); /* 石が置かれていない（" "）ならfalse */
	}

	// マスに置かれた石の状態を変更するメソッド
	public void changeStoneStatus(int playerNumber) {
		if (playerNumber == 1) {
			this.stone = BLACK_STONE; /* 先手player（number = 1）なら黒*/
		} else {
			this.stone = WHITE_STONE; /* 後手player（number = 2）なら白*/
		}
		System.out.println("石をおきました");
	}

}
