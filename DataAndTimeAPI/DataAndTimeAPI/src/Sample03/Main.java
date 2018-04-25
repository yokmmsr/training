package Sample03;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;

public class Main {

	public static void main(String[] args) {
		/*書式yyyy*/
//		DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("yyyy-MM-dd").withResolverStyle(ResolverStyle.STRICT);
//		System.out.println(LocalDate.parse("2018-04-24", formatter1));
		
		/*書式Gyyyy*/
//		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("Gyyyy-MM-dd").withResolverStyle(ResolverStyle.STRICT);
//		System.out.println(LocalDate.parse("西暦2018-04-24", formatter));
		
		/*書式uuuu*/
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuu-MM-dd").withResolverStyle(ResolverStyle.STRICT);
		System.out.println(LocalDate.parse("2018-04-24", formatter));

	}

}
