package Sample01;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.OffsetTime;
import java.time.ZonedDateTime;

public class Main {

	public static void main(String[] args) {
		String msg = "LocalDate       :" + LocalDate.now() + "\n" + "LocalDateTime   :" + LocalDateTime.now() + "\n"
				+ "LocalTime       :" + LocalTime.now() + "\n" + "OffsetDateTime  :" + OffsetDateTime.now() + "\n"
				+ "OffsetTime      :" + OffsetTime.now() + "\n" + "ZoneDateTime    :" + ZonedDateTime.now() + "\n";
		System.out.println(msg);
	}
}
