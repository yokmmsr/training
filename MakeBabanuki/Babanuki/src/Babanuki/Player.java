package Babanuki;

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

public class Player {
	private List<Card> playerHand = new ArrayList<>();
	private int handNumber;

	// コンストラクタ
	Player(List<Card> playerHand) {
		this.playerHand = playerHand; /* プレイヤーの初期手札をセット */
	}

	// ゲッター
	public List<Card> getPlayerHand() {
		return this.playerHand;
	}

	// セッター
	public void setPlayerHand(List<Card> playerHand) {
		this.playerHand = playerHand;
	}

	// 手札を表示するメソッド
	public void showPlayerHand() {
		this.handNumber = playerHand.size();
		System.out.println("残り" + this.handNumber + "枚");
		for (Card handCard : this.playerHand) {
			if (handCard.getCardMark().equals("JOKER")) {
				System.out.print(handCard.getCardMark() + " ");
			} else {
				System.out.print(handCard.getCardMark() + String.valueOf(handCard.getCardIndex()) + " ");
			}
		}
		System.out.println("");
	}

	// 番号が重複する2カードを捨てるメソッド
	public void discardPairCard() {
		List<Card> tempList = new ArrayList<>(this.playerHand);
		this.playerHand.clear(); /* 一旦手札の要素を全削除 */
		int baseCardNumber = 0;
		int targetCardNumber = 0;

		for (Card baseCard : tempList) {
			for (int i = tempList.indexOf(baseCard) + 1; i < tempList.size(); i++) {
				if (baseCard.getCardIndex() != 99 && baseCard.getCardIndex() == tempList.get(i).getCardIndex()) {
					System.out.println("=> " + baseCard.getCardMark() + String.valueOf(baseCard.getCardIndex()) + "と"
							+ tempList.get(i).getCardMark() + String.valueOf(tempList.get(i).getCardIndex())
							+ "がペアなので捨てました");
					Card dummy = new Card("DUMMY", 99); /* 2ペアになる要素を置き換えるダミー要素 */
					tempList.set(tempList.indexOf(baseCard), dummy);
					tempList.set(i, dummy);
					break;
				}
			}
		}
		for (Card tempCard : tempList) {
			if (tempCard.getCardIndex() != 99) {
				this.playerHand.add(tempCard); /* ダミーではない要素だけを格納し直す */
			}
		}
		System.out.println("");
	}
}
