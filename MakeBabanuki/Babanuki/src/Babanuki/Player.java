package Babanuki;

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

public class Player {
	private List<Card> cards = new ArrayList<>();
	private int cardsNumber;

	// コンストラクタ
	Player(List<Card> cards) {
		this.cards = cards; /* プレイヤーの初期手札をセット */
	}

	// ゲッター
	public List<Card> getPlayerCards() {
		return this.cards;
	}

	// セッター
	public void setPlayerCards(List<Card> cards) {
		this.cards = cards;
	}

	// 手札を表示するメソッド
	public void showPlayerCards() {
		this.cardsNumber = cards.size();
		System.out.println("残り" + this.cardsNumber + "枚");
		for (Card handCard : this.cards) {
			System.out.print(handCard);
		}
		System.out.println("");
	}

	// 番号が重複する2カードを捨てるメソッド
	public void discardPairCard() {
		List<Card> tempList = new ArrayList<>(this.cards);
		this.cards.clear(); /* 一旦手札の要素を全削除 */

		for (Card baseCard : tempList) {
			for (int i = tempList.indexOf(baseCard) + 1; i < tempList.size(); i++) {
				Card tempCard = tempList.get(i);
				if (baseCard.getCardIndex() != 99 && baseCard.getCardIndex() == tempCard.getCardIndex()) {
					System.out.println("=> " + baseCard.getCardMark() + String.valueOf(baseCard.getCardIndex()) + "と"
							+ tempCard.getCardMark() + String.valueOf(tempCard.getCardIndex()) + "がペアなので捨てました");
					Card dummy = new Card("DUMMY", 99); /* 2ペアになる要素を置き換えるダミー要素 */
					tempList.set(tempList.indexOf(baseCard), dummy);
					tempList.set(i, dummy);
					break;
				}
			}
		}
		for (Card tempCard : tempList) {
			if (tempCard.getCardIndex() != 99) {
				this.cards.add(tempCard); /* ダミーではない要素だけを格納し直す */
			}
		}
		System.out.println("");
	}

	// 手札へカードを1枚追加（相手から引く）するメソッド
	public void addCard(Card exchangeCard) {
		this.cards.add(exchangeCard);
		Collections.shuffle(this.cards); /* 追加後に手札シャッフル */
	}

	// 手札からカードを1枚削除（相手から引かれる）するメソッド
	public Card passCard(int drowIndex) {
		Collections.shuffle(this.cards); /* 渡す前に手札シャッフル */
		Card exchangeCard = this.cards.get(drowIndex);
		this.cards.remove(drowIndex);
		return exchangeCard;
	}

}
