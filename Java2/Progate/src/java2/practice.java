package java2;

public class practice {

	public static void main(String[] args) {
		int[] numbers = {1, 4, 6, 9, 13, 16};
		int oddSum = 0;
		int evenSum = 0;
		
//		for (int i = 0; i < numbers.length; i++) {
//			if(numbers[i] % 2 != 0) {
//				oddSum += numbers[i];
//			} else {
//				evenSum += numbers[i];
//			}
//		}
		
		for(int num: numbers){
			if(num % 2 != 0) {
				oddSum += num;
			} else {
				evenSum += num;
			}
		}

		
	System.out.println("奇数の和は" + oddSum + "です");
	System.out.println("偶数の和は" + evenSum + "です");
	}
}
