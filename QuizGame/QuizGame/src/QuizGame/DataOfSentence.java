package QuizGame;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DataOfSentence {

	/* Questions+Optionsテーブル内のカラムの番号 */
	final int QUESTIONS_CONTENT_INDEX = 1;
	final int OPTIONS_CONTENT_INDEX = 2;
	private ResultSet extractedResults;

	DataOfSentence(ResultSet extractedResults) throws SQLException {
		this.extractedResults = extractedResults;
	}

	// 問題文を返すメソッド
	public String getQuestionContents(int getIndex) throws SQLException {
		return (String) getAnyTypesData(getIndex, QUESTIONS_CONTENT_INDEX);
	}

	// 選択肢を返すメソッド
	public String getOptionContents(int getIndex) throws SQLException {
		return (String) getAnyTypesData(getIndex, OPTIONS_CONTENT_INDEX);
	}

	// データをObject型の変数として取得するメソッド
	public Object getAnyTypesData(int getIndex, int columnNumber) throws SQLException {
		int questionsCount = 1;
		while (this.extractedResults.next()) {
			if (questionsCount == getIndex) {
				Object anyObject = this.extractedResults.getObject(columnNumber);
				this.extractedResults.beforeFirst();
				return anyObject;
			}
			questionsCount++;
		}
		return null;
	}
}