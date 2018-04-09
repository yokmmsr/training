package Practice01;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class arrayListSample01 {

	public static void main(String[] args) {
		List<Integer> list = new ArrayList<>();
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(4);
		
		for(Iterator it = list.iterator(); it.hasNext();) {
			System.out.println(it.next());
		}
		
	}

}
