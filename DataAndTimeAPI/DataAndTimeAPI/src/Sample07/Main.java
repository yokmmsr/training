package Sample07;

import java.time.LocalDate;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAdjusters;


public class Main {

	public static void main(String[] args) {
		Temporal temporal = LocalDate.now();
		System.out.println(temporal);
		
		Temporal lastDayOfMonth = temporal.with(TemporalAdjusters.lastDayOfMonth());
		System.out.println(temporal);
	}

}
