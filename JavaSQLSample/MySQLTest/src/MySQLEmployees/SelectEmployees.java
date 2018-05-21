package MySQLEmployees;

import java.util.Scanner;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SelectEmployees {

	public static void main(String[] args) {

		String serverName = "localhost";
		String databaseName = "sampledb";
		String tableName = "employees";
		String user = "root";
		String password = "";
		String serverencoding = "UTF-8";
		String url = "jdbc:mysql://" + serverName + "/" + databaseName + "?useSSL=false";

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection(url, user, password);
			Statement stmt = conn.createStatement();

			insertEmployee(stmt, tableName);
			deleteEmployee(stmt, tableName);
			updateEmployee(stmt, tableName);
			ResultSet rset = executeSelectSQL(stmt, tableName);
			while (rset.next()) {
				System.out.println(
						rset.getInt(1) + "\t" + rset.getString(2) + "\t" + rset.getInt(3) + "\t" + rset.getString(4));
			}
			rset.close();
			stmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 社員情報を追加するメソッド
	public static void insertEmployee(Statement stmt, String tableName) throws SQLException {
		Scanner scanner = new Scanner(System.in);
		System.out.print("社員情報を追加しますか？（1:追加する）：");
		if (scanner.nextInt() == 1) {
			System.out.println("各情報を入力してください");
			System.out.println("社員番号：");
			int code = scanner.nextInt();
			System.out.println("名前：");
			String name = scanner.next();
			System.out.println("年齢：");
			int age = scanner.nextInt();
			System.out.println("部署：");
			String section = scanner.next();
			executeInsertSQL(tableName, code, name, age, section, stmt);
		}
	}

	// INSERTのSQL文を生成するメソッド
	public static void executeInsertSQL(String tableName, int code, String name, int age, String section,
			Statement stmt) throws SQLException {
			stmt.executeUpdate(String.format("insert into %s (code, name, age, section) values (%d, %s, %d, %s)", code, name, age, section));
	}

	// 社員情報を削除するメソッド
	public static void deleteEmployee(Statement stmt, String tableName) throws SQLException {
		Scanner scanner = new Scanner(System.in);
		System.out.print("社員情報を削除しますか？（1:削除する）：");
		if (scanner.nextInt() == 1) {
			System.out.print("データを削除する社員の社員番号を入力してください：");
			int deleteCode = scanner.nextInt();
			executeDeleteSQL(tableName, deleteCode, stmt);
		}
	}

	// DELETEのSQL文を生成するメソッド
	public static void executeDeleteSQL(String tableName, int deleteCode, Statement stmt) throws SQLException {
		stmt.executeUpdate(String.format("delete from %s where code = %d", tableName, deleteCode));
		
	}

	// 社員情報を更新するメソッド
	public static void updateEmployee(Statement stmt, String tableName) throws SQLException {
		Scanner scanner = new Scanner(System.in);
		System.out.print("社員情報を変更しますか？（1:変更する）：");
		if (scanner.nextInt() == 1) {
			System.out.print("データを変更する社員の社員番号を入力してください：");
			int updateCode = scanner.nextInt();
			while (true) {
				System.out.print("変更するデータを指定してください（1:社員番号、2:名前、3:年齢、4:部署）：");
				String changeDataLabel = null;
				switch (scanner.nextInt()) {
				case 1:
					System.out.print("変更後の社員番号を入力してください：");
					int initialCode = updateCode;
					updateCode = scanner.nextInt();
					changeDataLabel = "code";
					executeUpdateSQL(tableName, updateCode, stmt, changeDataLabel, initialCode);
					break;
				case 2:
					System.out.print("変更後の名前を入力してください：");
					String updatedName = scanner.next();
					changeDataLabel = "name";
					executeUpdateSQL(tableName, updatedName, stmt, changeDataLabel, updateCode);
					break;
				case 3:
					System.out.print("変更後の年齢を入力してください：");
					int updatedAge = scanner.nextInt();
					changeDataLabel = "age";
					executeUpdateSQL(tableName, updatedAge, stmt, changeDataLabel, updateCode);
					break;
				case 4:
					System.out.print("変更後の部署を入力してください：");
					String updatedSection = scanner.next();
					changeDataLabel = "section";
					executeUpdateSQL(tableName, updatedSection, stmt, changeDataLabel, updateCode);
					break;
				}
				System.out.print("データの変更を終了しますか？（1:終了）：");
				if (scanner.nextInt() == 1) {
					break;
				}
			}
		}
	}

	// UPDATEのSQL文を生成するメソッド（int型のデータを変更）
	public static void executeUpdateSQL(String tableName, int updatedData, Statement stmt, String changeDataLabel,
			int updateEmployeeCode) throws SQLException {
			stmt.executeUpdate(String.format("update %s set %s = %d where code = %d", tableName, changeDataLabel, updatedData, updateEmployeeCode));
	}

	// UPDATEのSQL文を生成するメソッド（String型のデータを変更）
	public static void executeUpdateSQL(String tableName, String updatedData, Statement stmt, String changeDataLabel,
			int updateEmployeeCode) throws SQLException {
			stmt.executeUpdate(String.format("update %s set %s = %s where code = %d", tableName, changeDataLabel, updatedData, updateEmployeeCode));
	}

	// SELECTのSQL文を生成するメソッド
	public static ResultSet executeSelectSQL(Statement stmt, String tableName) throws SQLException {
		return stmt.executeQuery(String.format("select * from %s", tableName));
	}
}