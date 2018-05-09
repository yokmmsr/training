package Sample06;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;


public class Main {

	public static void main(String[] args) {
		Date date1 = new Date();
		Instant instant1 = date1.toInstant();
		LocalDateTime localDateTime1 = LocalDateTime.ofInstant(instant1, ZoneOffset.ofHours(9));
		System.out.println("date" + date1);
		System.out.println("localDateTime" + localDateTime1);
		
		LocalDateTime localDateTime2 = LocalDateTime.now();
		Instant instant2 = localDateTime2.toInstant(ZoneOffset.ofHours(9));
		Date date2 = Date.from(instant2);
		System.out.println("localDateTime" + localDateTime2);
		System.out.println("date" + date2);
		
	}

}
