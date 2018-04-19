package BabanukiMultiplePlayer;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
	public static void main(String[] args) {

		List<String> allCards = new ArrayList<>();
		System.out.print("何人でババ抜きをプレイしますか？：");

		int playerNumber = setPlayerNumber();

		/* 53枚のカードを生成しプレイヤー人数分の手札に分ける */
		List<List<Card>> playerHands = new ArrayList<>();
		playerHands = devideCards(createAllCards(), playerNumber);

		/* プレイヤーを生成し初期手札のダブりを捨てる */
		List<Player> allPlayers = new ArrayList<>();
		for (int i = 0; i < playerNumber; i++) {
			Player player = new Player(playerHands.get(i), i + 1);
			allPlayers.add(player);
			player.showPlayerCards();
			player.discardPairCard();
			player.showPlayerCards();
			System.out.println("");
		}

		/* カードの交換と番号重複削除を繰り返してババ抜きを進めていく */
		int endCount = 0;
		int turnCount = 1;
		while (!(endCount == playerNumber - 1)) {
			System.out.println("");
			System.out.println("【" + turnCount + "ターン目】");
			endCount = changeCard(allPlayers, endCount);
			turnCount++;
		}
		System.out.println("+++++ ババ抜きおわり ++++");
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
	public static List<Card> createAllCards() {
		List<Card> allCards = new ArrayList<>();
		List<String> marks = new ArrayList<>();
		marks.add("♤︎︎");
		marks.add("♧︎");
		marks.add("♡");
		marks.add("♢");
		for (String mark : marks) {
			for (int i = 1; i <= 13; i++) {
				Card card = new Card(mark, i);
				allCards.add(card);
			}
		}
		Card card = new Card("JOKER", 0); /* 他のカードと重複しないように0とする */
		allCards.add(card);
		Collections.shuffle(allCards);
		return allCards;
	}

	// カードを分配するメソッド
	public static List<List<Card>> devideCards(List<Card> allCards, int playerNumber) {
		List<List<Card>> playerHands = new ArrayList<>();
		final int ALL_CARDS = 53;
		int rest = ALL_CARDS % playerNumber;
		int cardNumber = (ALL_CARDS - rest) / playerNumber;
		/* まずは余りを除いて等配分する */
		for (int i = 0; i < playerNumber; i++) {
			int fromIndex = cardNumber * i;
			int toIndex = cardNumber * (i + 1);
			List<Card> hand = new ArrayList<>(allCards.subList(fromIndex, toIndex));
			playerHands.add(hand);
		}
		/* 最後に余りを分ける */
		for (int i = 0; i < rest; i++) {
			int number = ALL_CARDS - rest + i;
			playerHands.get(i).add(allCards.get(number));
		}
		return playerHands;
	}

	// プレイヤー同士のカードの交換を制御するメソッド
	public static int changeCard(List<Player> allPlayers, int endCount) {
		int drowPlayer;
		int givePlayer;
		for (int i = 0; i < allPlayers.size(); i++) {
			if (allPlayers.get(i).isFinish()) {
				continue;
			}
			drowPlayer = i;

			givePlayer = drowPlayer + 1; /* 最後じゃない人がカードを引く時は隣の人の手札から引く */
			while (true) {
				if (givePlayer == allPlayers.size()) {
					givePlayer = drowPlayer;
					break;
				}
				if (allPlayers.get(givePlayer).isFinish()) {
					givePlayer++;
					if (givePlayer == allPlayers.size()) {
						givePlayer = drowPlayer;
						break;
					}
					continue;
				}
				break;
			}

			/* drowPlayerはgivePlayer手札（毎回シャッフルされる）の先頭カードを引く */
			int drowIndex = 0; /* 先頭のカードを引く */
			Card exchangeCard = new Card();
			exchangeCard = allPlayers.get(givePlayer).passCard(drowIndex);
			allPlayers.get(drowPlayer).addCard(exchangeCard);

			if (exchangeCard.isJoker()) {
				System.out.println("=> Player" + (drowPlayer + 1) + "がPlayer" + (givePlayer + 1) + "の"
						+ exchangeCard.getCardMark() + "を引きました");
			} else {
				System.out.println("=> Player" + (drowPlayer + 1) + "がPlayer" + (givePlayer + 1) + "の"
						+ exchangeCard.getCardMark() + exchangeCard.getCardIndex() + "を引きました");
			}

			System.out.println("");
			allPlayers.get(drowPlayer).showPlayerCards();
			allPlayers.get(drowPlayer).discardPairCard();
			allPlayers.get(drowPlayer).showPlayerCards();

			if (allPlayers.get(drowPlayer).isFinish()) {
				System.out.println("=> Player" + (drowPlayer + 1) + "があがりました！！");
				endCount++;
				continue;
			} else if (allPlayers.get(givePlayer).isFinish()) {
				System.out.println("=> Player" + (givePlayer + 1) + "があがりました！！");
				endCount++;
				continue;
			}
		}
		return endCount;

	}

	// カードを引くプレイヤー、引かれるプレイヤーの番号を制御するメソッド
	public static void setDrowGiveNumber(List<Player> allPlayers, int drowPlayer) {
		if (allPlayers.get(drowPlayer).isFinish()) {

		}
	}

}