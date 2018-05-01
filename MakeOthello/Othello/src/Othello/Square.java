package Othello;

public class Square {
	private int squareNumber;
	private String stone;
	private final String BLACK_STONE = "[B]"; /* 黒は先手 */
	private final String WHITE_STONE = "[W]"; /* 白は後手 */
	private final String BLANK = " "; /* 石は置かれていない */
	private boolean isSandwiched = false; /* マス上の石が他の色の石に挟まれていればtrue */

	// コンストラクタ
	Square(int squareNumber, String stone) {
		this.squareNumber = squareNumber;
		this.stone = stone;

	}

	Square() { /* Square型のオブジェクトを生成するだけで特に何も設定しない */
	}

	// ゲッター
	public int getSquareNumber() {
		return this.squareNumber;
	}

	public String getStone() {
		return this.stone;
	}
	
	public boolean getIsSandwiched() {
		return this.isSandwiched;
	}
	
	// 置かれた石に応じて色を変更するメソッド
	public void changePutStone(String putStone) {
		this.stone = putStone;
	}

	// 挟まれた時に石の状態を変更する（黒石⇄白石）メソッド
	public void changeSandwichedStone() {
		if (this.stone == BLACK_STONE) {
			this.stone = WHITE_STONE;
		} else {
			this.stone = BLACK_STONE;
		}
		this.isSandwiched = false;
	}

	// マスに石が置かれているか判定するメソッド
	public boolean isStoneExist() {
		return !(BLANK.equals(this.stone)); /* 石が置かれていない（" "）ならfalse */
	}

	// 異なる色の石に挟まれていると判定するメソッド
	public void setSandwiched() {
		this.isSandwiched = true;
	}

}
