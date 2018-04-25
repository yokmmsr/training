package Sample05;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.chrono.JapaneseDate;
import java.time.chrono.JapaneseEra;

public class Main {

	public static void main(String[] args) {
		checkLocalDate();
		checkOffsetDateTime();
	}

	private static void checkLocalDate() {
		System.out.println("##### checkLocalDate()");
		LocalDate a = LocalDate.parse("2018-04-24");
		LocalDate b = LocalDate.parse("2018-04-24");
		JapaneseDate jp = JapaneseDate.of(JapaneseEra.HEISEI, 30, 4, 24);

		System.out.println("a.isEqual(b) :" + a.isEqual(b));
		System.out.println("a.equals(b) :" + a.equals(b));
		System.out.println("a.isEqual(jp) :" + a.isEqual(jp));
		System.out.println("a.equals(jp) :" + a.equals(jp));
	}
	
	private static void checkOffsetDateTime() {
		System.out.println("##### checkOffsetDateTime()");
		OffsetDateTime a = OffsetDateTime.parse("2018-04-24T09:00:00+09:00");
		OffsetDateTime b = OffsetDateTime.parse("2018-04-24T04:00:00+04:00");
		OffsetDateTime c = OffsetDateTime.parse("2018-04-24T04:00:00+04:00");
		
		System.out.println("a.isEqual(b) :" + a.isEqual(b));
		System.out.println("a.equals(b) :" + a.equals(b));
		System.out.println("a.equals(c) :" + a.equals(c));
	}
}
