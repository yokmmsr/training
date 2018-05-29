package QuizGame;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class Answer {

	ResultSet myRecords;
	
	Answer(ResultSet myRecords) throws SQLException {
		this.myRecords = myRecords;
	}
	
	//recordsテーブルの自分の解答を返すメソッド
//	public int getAnswerId() {
//	}
	
	// 正誤判定メソッド
	public void isRight() throws SQLException {
	}

}
