package Sample02;

import java.time.LocalDate;
import java.time.OffsetDateTime;

public class Main {

	public static void main(String[] args) {
		LocalDate localDate = LocalDate.parse("2018-04-24");
		System.out.println(localDate);
		
		OffsetDateTime offsetDateTime = OffsetDateTime.parse("2018-04-24T16:57:30+09:00");
		System.out.println(offsetDateTime);
	}

}
