package Babanuki;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
	public static void main(String[] args) {

		List<String> allCards = new ArrayList<>();
		System.out.print("何人でババ抜きをプレイしますか？：");

		int playerNumber = setPlayerNumber();
		playerNumber = 2; /* とりあえず今は強制的に2人！！！ */

		List<List<String>> playerCards = new ArrayList<>();
		playerCards = devideCards(createAllCards(), playerNumber);

		/* プレイヤー人数分のhabdCards（手札）オブジェクトを生成 */
		List<HandCard> handCards = new ArrayList<>();
		for (int i = 0; i < playerNumber; i++) {
			handCards.add(new HandCard(playerCards.get(i), playerNumber));
			HandCard handCard = handCards.get(i);
			System.out.println("【Player" + (handCards.indexOf(handCard) + 1) + "の手札】");
			handCard.showHandCard();
		}
		
		Player player = new Player(playerCards);
	}

	// プレイ人数が適切か確認するメソッド
	public static int setPlayerNumber() {
		Scanner scanner = new Scanner(System.in);
		int playerNumber;
		while (true) {
			playerNumber = scanner.nextInt();
			if (playerNumber > 0) {
				break;
			}
			System.out.print("プレイヤーは1人以上です！：");
		}
		return playerNumber;
	}

	// 全カードを生成・シャッフルするメソッド
	public static List<String> createAllCards() {
		List<String> allCards = new ArrayList<>();
		List<String> marks = new ArrayList<>();
		marks.add("♤︎︎");
		marks.add("♧︎");
		marks.add("♡");
		marks.add("♢");
		for (String mark : marks) {
			for (int i = 1; i <= 13; i++) {
				allCards.add(mark + String.valueOf(i));
			}
		}
		allCards.add("JOKER");
		Collections.shuffle(allCards);
		return allCards;
	}

	// カードを分配するメソッド
	public static List<List<String>> devideCards(List<String> allCards, int playerNumber) {
		List<String> card = new ArrayList<>();
		List<List<String>> playerCards = new ArrayList<>();
		int rest = allCards.size() % playerNumber;
		int cardNumber = (allCards.size() - rest) / playerNumber;
		/* まずは余りを除いて等配分する */
		for (int i = 0; i < playerNumber; i++) {
			int fromIndex = cardNumber * i;
			int toIndex = cardNumber * (i + 1);
			card = allCards.subList(fromIndex, toIndex);
			playerCards.add(card);
		}
		/* 最後に余りを分ける */
		for (int i = 0; i < rest - 1; i++) {
			int number = allCards.size() - rest + i;
			playerCards.get(i).add(allCards.get(number));
		}
		return playerCards;
	}
}