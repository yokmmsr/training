package Practice01;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class arrayListSample03 {

	public static void main(String[] args) {
		List<Integer> list1 = new ArrayList<>();
		list1.add(1);
		list1.add(2);
		list1.add(3);
		list1.add(4);
		System.out.println("list1 = " + list1);
		
		List<Integer> list2 = new ArrayList<>();
		list2.add(5);
		list2.add(6);
		list2.add(7);
		list2.add(8);
		list1.addAll(list2);
		System.out.println("list1 = " + list1);
		
		List<Integer> list3 = new ArrayList<>();
		for(int i = 0; i < 5; i++) {
			list3.add(i);
		}
		System.out.println("list3 = " + list3);
		list3.set(0,5);
		list3.set(1,6);
		list3.set(2,7);
		list3.set(3,8);
		System.out.println("list3 = " + list3);
		
		List<String> list4 = new ArrayList<>();
		list4.add("apple");
		list4.add("orange");
		list4.add("melon");
		System.out.println(list4.get(1));
		
		List<String> list5 = new ArrayList<>(Arrays.asList("s", "a", "m", "u", "r", "a", "i"));
		System.out.println("list5 = " + list5);
		System.out.println("要素数 = " + list5.size());
		int index = list5.indexOf("a");
		System.out.println("要素番号" + index);
		
		List<Integer> list6 = new ArrayList<>();
		for(int i = 0; i < 5; i++) {
			list6.add(i);
		}
		List<Integer> list6sub = list6.subList(0, 5);
		//subListはインデックスの終わりを含まない（上の場合、要素0~4まで）
		System.out.println("list6 = " + list6);
		System.out.println("list6sub = " + list6sub);
		
		List<String> list7 = new ArrayList<>(Arrays.asList("e", "n", "g", "i", "n", "e", "r"));
		List<String> list8 = new ArrayList<>();
		for(String a : list5) {
			if(list7.contains(a)) {
				list8.add(a);
			}
		}
		System.out.println("list8 = " + list8);
		
		List<String> list9 = new ArrayList<>(Arrays.asList("侍", "samurai", "さむらい", "サムライ"));
		list9.remove(1);
		System.out.println("list9 = " + list9);
		
		list5.stream().distinct().forEach(System.out::print);
		System.out.println("");
		list7.stream().distinct().forEach(System.out::print);
		System.out.println("");
		
		ArrayList<String> list10 = new ArrayList<>();
		list10.add("a");
		list10.add("b");
		list10.add("c");
		ArrayList<String> list11 = (ArrayList<String>)list10.clone();
		System.out.println("list10 = " + list10);
		System.out.println("list11 = " + list11);
		
		List<Integer> list12 = new ArrayList<>();
		list12.add(10);
		list12.add(30);
		list12.add(50);
		list12.add(1);
		list12.add(9);
		list12.add(99);
		System.out.println("list12 = " + list12);
		Collections.sort(list12);
		System.out.println("list12 = " + list12);
	}

}
