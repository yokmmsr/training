package QuizGame;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Scanner;

public class Questions {

	/* DB名 */
	final String DB_NAME = "quizGame";
	/* DBのテーブル名 */
	final String QUESTIONS_TABLE = "questions";
	final String OPTIONS_TABLE = "options";
	final String RECORDS_TABLE = "records";
	/* Questionsテーブルのカラム名 */
	final String QUESTIONS_CONTENT = QUESTIONS_TABLE + ".content";
	final String CORRECTOPTION_ID = "correctOption_id";
	/* Optionsテーブルのカラム名 */
	final String OPTIONS_CONTENT = OPTIONS_TABLE + ".content";
	final String QUESTION_ID = "question_id";
	/* Recordsテーブルのカラム名 */
	final String RECORDS_PLAY_DATETIME = "play_datetime";

	OperateSQL quizSQL; /* SQL文を生成するオブジェクト */
	LocalDateTime startDateTime;

	Questions(LocalDateTime startDateTime) throws SQLException {
		OperateSQL quiz = new OperateSQL();
		this.quizSQL = quiz;
		this.startDateTime = startDateTime;
	}

	// DBに保存されている全問題数（ランダム出題する時に使う）をカウントするメソッド
	public int countStoredQuestions() throws SQLException {

		String selectedColumn = "count(*)";
		ResultSet allQuestionsDB = quizSQL.executeSelectSQL(selectedColumn, QUESTIONS_TABLE);

		int allQuestions = 0;
		while (allQuestionsDB.next()) {
			allQuestions = allQuestionsDB.getInt(1);
		}
		return allQuestions;
	}

	// 問題文と選択肢を取得して表示するメソッド
	public void showQuestionsAndOptions(int questionNumber) throws Exception {
		String selecteColumn = QUESTIONS_CONTENT + "," + OPTIONS_CONTENT;
		String joinConditions = String.format("%s.id = %s and %s.id = %d", QUESTIONS_TABLE, QUESTION_ID,
				QUESTIONS_TABLE, questionNumber);
		ResultSet questionAndOption = quizSQL.executeJoinSelectSQL(selecteColumn, QUESTIONS_TABLE, OPTIONS_TABLE,
				joinConditions);
		DataOfSentence content = new DataOfSentence(questionAndOption);

		for (int i = 1; i <= 4; i++) {
			if (i == 1) {
				System.out.println(content.getQuestionContents(i)); /* 抽出したデータの1カラム目は問題文 */
				System.out.println("==============================");
			}
			System.out.println(i + ". " + content.getOptionContents(i)); /* 抽出したデータの2カラム目は選択肢 */
		}
		System.out.println("------------------------------");
	}

	// 回答番号と正解の判定を行うメソッド
	public void answerToQuestions(int questionNumber) throws SQLException {
		Scanner scanner = new Scanner(System.in);
		System.out.print("答えは？：");
		int inputAnswer = scanner.nextInt();
		boolean myJudgement = true;

		String selectConditions = "id = " + questionNumber;
		ResultSet correctAnswer = quizSQL.executeSelectSQL(CORRECTOPTION_ID, QUESTIONS_TABLE, selectConditions);
		DataOfCorrectAnswer answer = new DataOfCorrectAnswer(correctAnswer);
		myJudgement = inputAnswer == answer.getCorrectAnswerId(1); /* 正しい答えは各問題につき1個なので第１引数（ループ回数）は1 */
		System.out.println(myJudgement ? "正解！" : "不正解…");
		System.out.println("");

		String insertedTable = DB_NAME + "." + RECORDS_TABLE;
		String insertValues = String.format("0, %d, %d, %b, ", questionNumber, inputAnswer, myJudgement);
		insertValues += "cast('" + this.startDateTime + "' as datetime)";
		quizSQL.executeInsertSQL(insertedTable, insertValues); /* 結果をrecordsテーブルに格納 */
	}

	// 成績（回答の履歴）を表示するメソッド
	public void showRecords() throws SQLException {

		String selectedColumn = "*";
		String selectConditions = RECORDS_PLAY_DATETIME + " = " + "cast('" + this.startDateTime + "' as datetime)";
		ResultSet myRecords = quizSQL.executeSelectSQL(selectedColumn, RECORDS_TABLE, selectConditions);
		DataOfMyAnswer records = new DataOfMyAnswer(myRecords);

		System.out.println("* * * *  成 績  * * * *");
		System.out.println("\t " + "あなたの解答" + "\t" + "正誤");
		int correctCount = 0;
		for (int i = 1; i <= Main.numberOfQuestions; i++) {
			System.out.println(
					String.format("%d問目：   \t%d \t %s", i, records.getAnswerId(i), (records.isRight(i) ? "⚪︎" : "×")));
			if (records.isRight(i)) {
				correctCount++;
			}
		}
		System.out.println("");
		int getScore = correctCount * 20;
		int fullScore = Main.numberOfQuestions * 20;
		System.out.println(
				String.format("%d問中%d問正解しました。（%d/%d 点）", Main.numberOfQuestions, correctCount, getScore, fullScore));
		quizSQL.closeSQL(); /* クイズが終了したのでStatementとConnectionを閉じる */
	}

}
