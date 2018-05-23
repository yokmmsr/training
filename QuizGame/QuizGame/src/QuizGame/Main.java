package QuizGame;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Scanner;

public class Main {
	static int numberOfQuestions; /* 出題される問題数 */
	static int numbrOfStoredQuestions; /* DBに保存されている全問題数 */

	public static void main(String[] args) {

		try {
			LocalDateTime startDateTime = LocalDateTime.now(); /* クイズをスタートした日時を取得 */;
			Questions questions = new Questions(startDateTime);
			numbrOfStoredQuestions = questions.countStoredQuestions(); /* DBに保存されている全問題数をカウントする */
			Lottery lottery = new Lottery(numbrOfStoredQuestions); /* ランダムに出題するためのオブジェクト */
			setNumberOfQuestions();
			
			for (int i = 1; i <= numberOfQuestions; i++) {
				System.out.println("【第" + i + "問】");
				int questionNumber = lottery.apply();
				questions.showQuestionsAndOptions(questionNumber);
				questions.answerToQuestions(questionNumber);
			}
			questions.showRecords();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//問題数を設定するメソッド
	public static void setNumberOfQuestions() {
		Scanner scanner = new Scanner(System.in);
		while(true) {
			System.out.print("何問挑戦しますか？ " + numbrOfStoredQuestions + "問まで選べます：");
			int inputNumber = scanner.nextInt();   
			if(inputNumber <= numbrOfStoredQuestions) {
				numberOfQuestions = inputNumber;
				break;
			}
			System.out.print(numbrOfStoredQuestions + "問までしか選べません！");
		}
	}
}
