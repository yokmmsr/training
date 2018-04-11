package BingoGame;

public class Lottery {
	private int lotNumber;
	
	//コンストラクタ
	Lottery() {
	}
	//当選番号のゲッター
	public int getLotNumber() {
		return this.lotNumber;
	}
	
	//当選番号を決めるメソッド
	public void lottery() {
		this.lotNumber = (int)Math.floor(Math.random() * 100 + 1);
	}

}
