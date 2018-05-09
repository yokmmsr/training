package MySample;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.time.format.DateTimeFormatter;
import java.util.TimeZone;
import java.time.ZonedDateTime;
import java.util.Calendar;


public class VariousDateAndTimeMethods {
	private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
	
	VariousDateAndTimeMethods() {
	}

	//任意の期間の年、月、日数を表示するメソッド
	public void searchAnyPeriod(LocalDate dateFrom, LocalDate dateTo) {
		long differenceOfYears = ChronoUnit.YEARS.between(dateFrom, dateTo);
		long differenceOfMonths = ChronoUnit.MONTHS.between(dateFrom, dateTo);
		long differenceOfDays = ChronoUnit.DAYS.between(dateFrom, dateTo);
		
		System.out.println(dateFrom.format(formatter) + "〜" + dateTo.format(formatter));
		System.out.println("年：" + differenceOfYears + "年です");
		System.out.println("月：" + differenceOfMonths + "ヶ月です");
		System.out.println("日：" + differenceOfDays + "日です");
	}

	//生年月日を受け取り年齢を表示するメソッド
	public void birthdayToAge(LocalDate birthday) {
		LocalDate now = LocalDate.now();
		long age = ChronoUnit.YEARS.between(birthday, now);
		
		System.out.println(birthday.format(formatter) + "生まれです");
		System.out.println("現在" + age + "歳です");

		thisYearsBirthday(now, birthday);
	}
	
	//今年誕生日を迎えているかどうか判定するメソッド
	public void thisYearsBirthday(LocalDate now, LocalDate birthday) {
		LocalDate thisYearsBirthday = LocalDate.of(now.getYear(), birthday.getMonth(), birthday.getDayOfMonth());
		long differenceOfDays = ChronoUnit.DAYS.between(now, thisYearsBirthday);
		if(differenceOfDays > 0) {
			System.out.println("今年の誕生日まであと" + differenceOfDays + "日です");
		} else if(differenceOfDays < 0) {
			System.out.println("今年は" + Math.abs(differenceOfDays) + "日前に誕生日を迎えています");
		}else {
			System.out.println("今日が誕生日です");
		}
	}
	
	//タイムゾーン別の現在時刻を表示するメソッド
	public void AnyTimeZonesNow(String timeZoneID) {
		TimeZone timeZone = TimeZone.getTimeZone(timeZoneID);
		Calendar calendar = Calendar.getInstance(timeZone);
		System.out.println("タイムゾーンID：" + timeZone.getID());
		System.out.println("タイムゾーン：" + timeZone.getDisplayName());
		System.out.println("現在時刻：" + calendar.get(Calendar.YEAR) + "/"
				+ (calendar.get(Calendar.MONTH) + 1) + "/"
				+ calendar.get(Calendar.DAY_OF_MONTH) + " "
				+ calendar.get(Calendar.HOUR_OF_DAY) + ":"
				+ calendar.get(Calendar.MINUTE) + ":"
				+ calendar.get(Calendar.SECOND)
				);
		System.out.println("");
	}
}
