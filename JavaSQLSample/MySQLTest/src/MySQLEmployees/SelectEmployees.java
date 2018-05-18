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

			insertDB(stmt, tableName);
			deleteDB(stmt, tableName);
			updateDB(stmt, tableName);
			ResultSet rset = selectDB(stmt, tableName);
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

	// INSERT
	public static void insertDB(Statement stmt, String tableName) throws SQLException {
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
			stmt.executeUpdate("insert into " + tableName + " (code, name, age, section) values ('" + code + "', '" + name
					+ "', " + age + ", '" + section + "')");
			System.out.println("");
		}
	}

	// DELETE
	public static void deleteDB(Statement stmt, String tableName) throws SQLException {
		Scanner scanner = new Scanner(System.in);
		System.out.print("社員情報を削除しますか？（1:削除する）：");
		if (scanner.nextInt() == 1) {
			System.out.print("データを削除する社員の社員番号を入力してください：");
			int code = scanner.nextInt();
			stmt.executeUpdate("delete from " + tableName + " where code = " + code);
		}
	}

	// UPDATE
	public static void updateDB(Statement stmt, String tableName) throws SQLException {
		Scanner scanner = new Scanner(System.in);
		System.out.print("社員情報を変更しますか？（1:変更する）：");
		if (scanner.nextInt() == 1) {
			System.out.print("データを変更する社員の社員番号を入力してください：");
			int code = scanner.nextInt();
			while (true) {
				System.out.print("変更するデータを指定してください（1:社員番号、2:名前、3:年齢、4:部署）：");
				switch (scanner.nextInt()) {
				case 1:
					System.out.print("変更後の社員番号を入力してください：");
					int initialCode = code;
					code = scanner.nextInt();
					stmt.executeUpdate("update " + tableName + " set code = " + code + " where code = " + initialCode);
					break;
				case 2:
					System.out.print("変更後の名前を入力してください：");
					String updatedName = scanner.next();
					stmt.executeUpdate("update " + tableName + " set name = " + updatedName + " were code = " + code);
					break;
				case 3:
					System.out.print("変更後の年齢を入力してください：");
					int updatedAge = scanner.nextInt();
					stmt.executeUpdate("update " + tableName + " set age = " + updatedAge + " where code = " + code);
					break;
				case 4:
					System.out.print("変更後の社員番号を入力してください：");
					String updatedSection = scanner.next();
					stmt.executeUpdate("update " + tableName + " set section = " + updatedSection + " where code = " + code);
					break;
				}
				System.out.print("データの変更を終了しますか？（1:終了）：");
				if (scanner.nextInt() == 1) {
					break;
				}
			}
		}
	}

	// SELECT
	public static ResultSet selectDB(Statement stmt, String tableName) throws SQLException {
		return stmt.executeQuery("select * from " + tableName);
	}
}