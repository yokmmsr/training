package QuizGame;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DataOfCorrectAnswer extends DataOfSentence {

	/* Questionsテーブル内のカラムの番号 */
	final int CORRECTOPTION_ID_INDEX = 1;
	private ResultSet extractedResults;
	private Object anyObject;

	DataOfCorrectAnswer(ResultSet extractedResults) throws SQLException {
		super(extractedResults);
		this.extractedResults = extractedResults;
	}

	// 選択肢番号を返すメソッド
	public int getCorrectAnswerId(int getIndex) throws SQLException {
		return (int) getAnyTypesData(getIndex, CORRECTOPTION_ID_INDEX);
	}
}
