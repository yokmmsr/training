package Practice01;
import java.util.HashMap;

public class MapSample02 {

    public static void main(String[] args) {
        HashMap<String, String> hashmap = new HashMap<String, String>();//[1]
        hashmap.put("性別", "りんご");//[2]
        hashmap.put("年齢", "みかん");//[3]
        hashmap.put("身長", "もも");//[4]
        hashmap.put("体重", "もも");//[4]
        System.out.println("[5] 値[0]:" + hashmap.get("apple"));
        System.out.println("[6] 値[1]:" + hashmap.get("orange"));
        System.out.println("[7] 値[2]:" + hashmap.get("peach"));
        System.out.println("[8] 値[3]:" + hashmap.get("mango"));
    }

}
