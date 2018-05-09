package Sample09;

import java.time.Clock;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

public class Main {

	public static void main(String[] args) {
		test("SystemDefaultZone()", Clock.systemDefaultZone());
		test("fixed()", Clock.fixed(OffsetDateTime.now().toInstant(), ZoneOffset.ofHours(9)));
	}

	private static void test(String tag, Clock clock) {
		System.out.println(tag + " : clock.class = " + clock.getClass().getSimpleName());
		System.out.println(LocalDateTime.now(clock));
		sleep(1000);
		System.out.println(LocalDateTime.now(clock));
		sleep(1000);
		System.out.println(LocalDateTime.now(clock));
		sleep(1000);
	}
	
	private static void sleep(long millis) {
		/* try-catch文 */
		try {
			Thread.sleep(millis); /* sleepメソッド */
		} catch (InterruptedException e){}
	}
}
