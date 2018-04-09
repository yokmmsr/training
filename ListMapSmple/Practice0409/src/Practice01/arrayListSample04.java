package Practice01;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class arrayListSample04 {

	public static void main(String[] args) {
		//配列からリストへ変換
		String[] array1 = {"s", "a", "m", "u", "r", "a", "i"};
		List<String> list1 = Arrays.asList(array1);
		System.out.println(list1);
		
		//リストから配列へ変換
		List<String> list2 = new ArrayList<>(Arrays.asList("s", "a", "m", "u", "r", "a", "i"));
		String[] array2 = list2.toArray(new String[list2.size()]);
		for(String s : array2) {
			System.out.println(s);
		}
	}

}
