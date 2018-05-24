package QuizGame;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class OperateSQL {

	final String SERVER_NAME = "localhost";
	final String DATABASE_NAME = "quizGame";
	final String USER = "root";
	final String PASSWORD = "";
	final String SERVER_ENCORDING = "UTF-8";
	final String URL = "jdbc:mysql://" + SERVER_NAME + "/" + DATABASE_NAME + "?useSSL=false";

	private Statement stmt;
	private Connection conn;

	OperateSQL() throws SQLException {
		conn = DriverManager.getConnection(URL, USER, PASSWORD);
		stmt = conn.createStatement();
	}

	// 2つのテーブルを結合してからデータをSELECTするメソッド
	public ResultSet executeJoinSelectSQL(String selectedColumn, String table1, String table2, String joinConditions)
			throws SQLException {
		return stmt.executeQuery(
				String.format("select %s from %s join %s on %s;", selectedColumn, table1, table2, joinConditions));
	}

	// テーブルからデータをSELECTするメソッド（whereあり）
	public ResultSet executeSelectSQL(String selectedColumn, String table, String selectConditions)
			throws SQLException {
		return stmt.executeQuery(String.format("select %s from %s where %s;", selectedColumn, table, selectConditions));
	}
	// テーブルからデータをSELECTするメソッド（whereなし）
	public ResultSet executeSelectSQL(String selectedColumn, String table)
			throws SQLException {
		return stmt.executeQuery(String.format("select %s from %s;", selectedColumn, table));
	}


	// テーブルにデータをINSERTするメソッド
	public void executeInsertSQL(String insertedTable, String insertValues) throws SQLException {
		stmt.executeUpdate(String.format("insert into %s values (%s);", insertedTable, insertValues));
	}

	// StatementとConnectionを閉じるメソッド
	public void closeSQL() throws SQLException {
		stmt.close();
		conn.close();
	}

}
