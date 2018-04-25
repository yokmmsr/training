package Sample08;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;

public class Main {

	public static void main(String[] args) {
		Temporal temporal = LocalDate.now();
		System.out.println(temporal);
		
		Temporal nextDay = temporal.with(temp -> {
			return temp.plus(1, ChronoUnit.DAYS);
		});
		System.out.println(nextDay);
	}
}
