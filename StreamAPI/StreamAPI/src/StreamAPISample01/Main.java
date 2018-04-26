package StreamAPISample01;

import java.util.List;
import java.util.ArrayList;


public class Main {

	public static void main(String[] args) {
		List<String> names = new ArrayList<>();
		names.add("おかべ");
		names.add("ゆうま");
		names.add("すぎやま");
		names.add("みながわ");
		names.add("もとやま");
		names.add("のざき");
		names.add("すずき");
		names.add("さとう");

		for (String name : names) {
			if (name.contains("ま")) {
				System.out.println(name);
			}
		}
		System.out.println(names.stream().filter(name -> name.contains("ま")));
	}

}
