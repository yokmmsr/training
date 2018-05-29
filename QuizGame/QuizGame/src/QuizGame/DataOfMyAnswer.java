package QuizGame;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DataOfMyAnswer extends DataOfSentence{

	/* Recordsテーブル内のカラムの番号 */
	final int ANSWER_ID_INDEX = 3;
	final int JUDGE_INDEX = 4;
	private ResultSet extractedResults;

	DataOfMyAnswer(ResultSet extractedResults) throws SQLException {
		super(extractedResults);
		this.extractedResults = extractedResults;
	}

	// 選択肢番号を返すメソッド
	public int getAnswerId(int getIndex) throws SQLException {
		return (int) getAnyTypesData(getIndex, ANSWER_ID_INDEX);
	}

	// 正誤の判定（真偽値）を返すメソッド
	public boolean isRight(int getIndex) throws SQLException {
		return (Boolean) getAnyTypesData(getIndex, JUDGE_INDEX);
	}
}
