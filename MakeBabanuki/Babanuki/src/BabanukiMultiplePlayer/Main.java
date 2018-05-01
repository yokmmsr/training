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
		while (endCount < playerNumber - 1) {
			System.out.println("");
			System.out.println("--------【" + turnCount + "ターン目】--------");
			endCount = changeCard(allPlayers, endCount);
			turnCount++;
		}
		System.out.println("");
		System.out.println("+++++++++ ババ抜きおわり +++++++++");
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
			/* カードを引く人drowPlayerの番号を決める */
			drowPlayer = setDrowPlayer(allPlayers, i);
			if (drowPlayer == 999) { /* 前ターンで既に上がっているプレイヤーの番号ならcontinue */
				continue;
			}
			/* カードを引かれる人givePlayerの番号を決める */
			givePlayer = setGivePlayer(allPlayers, drowPlayer);

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

			endCount = renewEndCount(allPlayers, drowPlayer, givePlayer, endCount);
			if (endCount == allPlayers.size() - 1) { /* 上がった人数が全プレイヤー数-1になったら終了 */
				break;
			}
		}
		return endCount;
	}

	// カードを引くdrowPlayerの番号を決めるメソッド
	public static int setDrowPlayer(List<Player> allPlayers, int index) {
		if (allPlayers.get(index).isFinish()) {
			return 999; /* 前ターンで既に上がっているプレイヤーの番号なら999を返し参照元でcontinue */
		} else {
			return index;
		}
	}

	// カードを引かれるgivePlayerの番号を決めるメソッド
	public static int setGivePlayer(List<Player> allPlayers, int drowPlayer) {
		int givePlayer = 999;
		/* 基本的に隣（自分の番号+1の人）から引く */
		/* 隣の人が既に上がっているならそのまた隣の人…というように繰り返す */
		for (int j = drowPlayer + 1; j < allPlayers.size(); j++) {
			if (allPlayers.get(j).isFinish()) {
				continue;
			} else {
				givePlayer = j;
				break;
			}
		}
		/* drowPlayerより後の人が全員上がってしまったら、drowPlayerより */
		/* 前の人から引くことになる */
		if (givePlayer == 999) {
			for (int j = 0; j < drowPlayer; j++) {
				if (allPlayers.get(j).isFinish()) {
					continue;
				} else {
					givePlayer = j;
					break;
				}
			}
		}
		return givePlayer;
	}

	// endCountを更新するメソッド
	public static int renewEndCount(List<Player> allPlayers, int drowPlayer, int givePlayer, int endCount) {
		List<Integer> exchangePlayers = new ArrayList<>();
		exchangePlayers.add(drowPlayer);
		exchangePlayers.add(givePlayer);
		for (int player : exchangePlayers) {
			if (allPlayers.get(player).isFinish()) {
				System.out.println("=> Player" + (player + 1) + "があがりました！！");
				endCount++;
			}
		}
		return endCount;
	}
}