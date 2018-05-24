package QuizGame;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Results {

	ResultSet extractedResults;

	Results(ResultSet extractedResults) throws SQLException {
		this.extractedResults = extractedResults;
	}

	// 問題文、選択肢を返すメソッド
	public String getContent(int getIndex, int columnNumber) throws SQLException {
		int questionsCount = 1;
		while (extractedResults.next()) {
			if (questionsCount == getIndex) {
				String content = extractedResults.getString(columnNumber);
				extractedResults.beforeFirst();
				return content;
			}
			questionsCount++;
		}
		return null;
	}

	// 選択肢番号を返すメソッド
	public int getAnswerId(int getIndex, int columnNumber) throws SQLException {
		int questionsCount = 1;
		while (extractedResults.next()) {
			if (questionsCount == getIndex) {
				int myAnswer = extractedResults.getInt(columnNumber);
				extractedResults.beforeFirst();
				return myAnswer;
			}
			questionsCount++;
		}
		return 0;
	}

	// 正誤の判定（真偽値）を返すメソッド
	public boolean isRight(int getIndex, int columnNumber) throws SQLException {
		int questionsCount = 1;
		while (extractedResults.next()) {
			if (questionsCount == getIndex) {
				boolean myJudgement = extractedResults.getBoolean(columnNumber);
				extractedResults.beforeFirst();
				return myJudgement;
			}
			questionsCount++;
		}
		return false;
	}

}
