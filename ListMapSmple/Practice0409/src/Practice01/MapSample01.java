package Practice01;
import java.util.*;

public class MapSample01 {

	public static void main(String[] args) {
		Map<String, String> map1 = new HashMap<String, String>();
		
		map1.put("key1", "apple");
		map1.put("key2", "orange");
		map1.put("key3", "melon");
		
		System.out.println(map1.get("key1"));
		System.out.println(map1.get("key2"));
		System.out.println(map1.get("key3"));
		
	}

}
