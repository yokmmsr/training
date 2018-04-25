package Sample04;

import java.time.LocalDate;
import	java.time.LocalDateTime;
import	java.time.format.DateTimeFormatter;

public class Main {

	public static void main(String[] args) {
		LocalDateTime localDateTime = LocalDateTime.parse("2018-04-24T17:41:30", DateTimeFormatter.ISO_DATE_TIME);
		System.out.println(localDateTime);
		
		DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("yyyy/MM/dd");
		System.out.println(LocalDate.now().format(formatter2));
	}

}
