package MySample;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		VariousDateAndTimeMethods dateAndTime = new VariousDateAndTimeMethods();

		System.out.println("任意の期間の年数、月数、日数を求めます");
		System.out.print("期間の最初の日付を入力してください(yyyy-MM-dd)：");
		LocalDate dateFrom = LocalDate.parse(scanner.nextLine());
		System.out.print("期間の最後の日付を入力してください(yyyy-MM-dd)：");
		LocalDate dateTo = LocalDate.parse(scanner.nextLine());
		dateAndTime.searchAnyPeriod(dateFrom, dateTo);
		System.out.println("");
		
		System.out.println("生年月日から年齢を計算します");
		System.out.print("生年月日を入力してください(yyyy-MM-dd)：");
		LocalDate birthday = LocalDate.parse(scanner.nextLine());
		dateAndTime.birthdayToAge(birthday);
		System.out.println("");
		
		System.out.println("指定したタイムゾーンの現在時刻を表示します");
		System.out.print("タイムゾーンIDを指定してください(US/Hawaiiなど)：");
		String timeZoneID = scanner.nextLine();
		dateAndTime.AnyTimeZonesNow("Asia/Tokyo");
		dateAndTime.AnyTimeZonesNow(timeZoneID);
	}

}
