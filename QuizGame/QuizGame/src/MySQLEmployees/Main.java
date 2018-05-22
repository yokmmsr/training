package MySQLEmployees;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Scanner;

public class Main {
	private static final LocalDate DateTime = null;
	static int numberOfQuestions = 5; /* とりあえず最初は5問で固定 */
	static int numbrOfStoredQuestions;
	// static LocalDateTime startDateTime = LocalDateTime.now(); /* クイズをスタートした日時を取得
	// */;

	public static void main(String[] args) {
		String serverName = "localhost";
		String databaseName = "quizGame";
		String user = "root";
		String password = "";
		String serverencoding = "UTF-8";
		String url = "jdbc:mysql://" + serverName + "/" + databaseName + "?useSSL=false";

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection(url, user, password);
			Statement stmt = conn.createStatement();

			clearRecords(stmt); /* 前回の結果が残っているとおかしいので始める前にクリアする */
			numbrOfStoredQuestions = countStoredQuestions(stmt);
			Lottery lottery = new Lottery(numbrOfStoredQuestions); /* ランダムに出題するためのオブジェクト */

			for (int i = 1; i <= numberOfQuestions; i++) {
				System.out.println("【第" + i + "問】");
				int questionNumber = lottery.lottery();
				showQuestionsAndOptions(stmt, questionNumber);
				answerToQuestions(stmt, questionNumber);
			}
			showRecords(stmt);
			stmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 問題文と選択肢を取得して表示するメソッド
	public static void showQuestionsAndOptions(Statement stmt, int questionNumber) throws Exception {
		ResultSet questionAndOption = executeJoinSelectSQL(stmt, questionNumber);
		int count = 0;
		while (questionAndOption.next()) {
			if (count == 0) {
				System.out.println(questionAndOption.getString(1)); /* 抽出したデータの1カラム目は問題文 */
				System.out.println("==============================");
			}
			count++;
			System.out.println(count + ". " + questionAndOption.getString(2)); /* 抽出したデータの2カラム目は選択肢 */
		}
		System.out.println("------------------------------");
	}

	// 回答番号と正解の判定を行うメソッド
	public static void answerToQuestions(Statement stmt, int questionNumber) throws SQLException {
		Scanner scanner = new Scanner(System.in);
		System.out.print("答えは？：");
		int answer = scanner.nextInt();
		boolean myJudge = true;
		ResultSet correctAnswer = executeSelectSQL(stmt, questionNumber);
		while (correctAnswer.next()) {
			myJudge = answer == correctAnswer.getInt(1);
			String message = myJudge ? "正解！" : "不正解…";
			System.out.println(message);
			System.out.println("");
		}
		executeInsertSQL(stmt, questionNumber, answer, myJudge);
	}

	// 成績（回答の履歴）を表示するメソッド
	public static void showRecords(Statement stmt) throws SQLException {
		System.out.println("**** 成 績 ****");
		ResultSet myRecords = executeSelectRecordsSQL(stmt);
		System.out.println("\t " + "あなたの解答" + "\t" + "正誤");
		int correctCount = 0;
		while (myRecords.next()) {
			System.out.println(myRecords.getInt(1) + "問目：   " + "\t" + myRecords.getInt(3) + " \t "
					+ (myRecords.getBoolean(4) ? "⚪︎" : "×"));
			if (myRecords.getBoolean(4)) {
				correctCount++;
			}
		}
		System.out.println("");
		System.out.println(numberOfQuestions + "問中" + correctCount + "問正解しました。（" + (correctCount * 20) + "/100 点）");
	}

	// 問題テーブルからデータをSELECTするメソッド
	public static ResultSet executeSelectSQL(Statement stmt, int questionNumber) throws SQLException {
		return stmt
				.executeQuery(String.format("select correctOption_id from questions where id = %d;", questionNumber));
	}

	// 問題テーブルと選択肢テーブルを結合してSELECTするメソッド
	public static ResultSet executeJoinSelectSQL(Statement stmt, int questionNumber) throws SQLException {
		String table1 = "questions";
		String table2 = "options";
		String column1 = table1 + ".content";
		String column2 = table2 + ".content";
		return stmt.executeQuery(String.format("select %s, %s from %s join %s on %s.id = question_id and %s.id = %d;",
				column1, column2, table1, table2, table1, table1, questionNumber));
	}

	// 回答結果をDBにINSERTするメソッド
	public static void executeInsertSQL(Statement stmt, int questionNumber, int answer, boolean myJudge)
			throws SQLException {
		stmt.executeUpdate(
//				"insert into quizGame.records values (0," + questionNumber + "," + answer + "," + myJudge + ", 0);");
				String.format("insert into quizGame.records values (0, %d, %d, %b, 0);", questionNumber, answer, myJudge));
	}

	// 成績をSELECTするメソッド
	public static ResultSet executeSelectRecordsSQL(Statement stmt) throws SQLException {
		return stmt.executeQuery("select * from records;");
	}

	// 成績を全て削除するメソッド
	public static void clearRecords(Statement stmt) throws SQLException {
		stmt.executeUpdate("truncate table records;");
	}

	// DBに保存されている全問題数（ランダム出題する時に使う）をカウントするメソッド
	public static int countStoredQuestions(Statement stmt) throws SQLException {
		ResultSet allQuestionsDB = stmt.executeQuery("select count(*) from questions;");
		int allQuestions = 0;
		while (allQuestionsDB.next()) {
			allQuestions = allQuestionsDB.getInt(1);
		}
		return allQuestions;
	}
}
