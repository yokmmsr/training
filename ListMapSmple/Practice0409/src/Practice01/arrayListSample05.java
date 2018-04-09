package Practice01;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class arrayListSample05 {

	public static void main(String[] args) {
		//ListからSetへの変換
		List<Integer> list1 = new ArrayList<>();
		list1.add(1);
		list1.add(2);
		list1.add(3);
		list1.add(1);
		Set<Integer> set1 = new HashSet<>(list1);
		System.out.println(set1);
		
		//SetからListへの変換
		Set<Integer> set2 = new HashSet<>();
		set2.add(1);
		set2.add(2);
		set2.add(3);
		List<Integer> list2 = new ArrayList<>(set2);
		System.out.println(list2);
		}
}

