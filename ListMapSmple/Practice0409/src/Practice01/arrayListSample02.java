package Practice01;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class arrayListSample02 {

	public static void main(String[] args) {
		List<String> list = new ArrayList<>();
		list.add("a");
		list.add("b");
		list.add("c");
		
		for(String s : list) {
			System.out.println(s);
		}
		list.forEach(System.out::println);
	}
}
	

