package BingoGame;

public class Lottery {
	private int lotNum;
	
	//コンストラクタ
	Lottery() {
	}
	
	//当選番号を決めるメソッド
	public int lottery(int count) {
		lotNum = (int)Math.floor(Math.random() * 100 + 1);
		System.out.println("");
		System.out.println(count + "回目：当選番号は" + lotNum + "です");
		return lotNum;
	}

}
