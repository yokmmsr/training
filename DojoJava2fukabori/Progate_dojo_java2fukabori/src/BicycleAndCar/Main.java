package BicycleAndCar;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		Bicycle bicycle1 = new Bicycle("ビアンキ", "緑", 0);
	    Car car1 = new Car("フェラーリ", "赤", 0, 100);
	    
	    int count = 1;
	    int flag = 0;
	    
	    while(count > 0) {
			bicycle1.printData();
		    System.out.println("-----------------");
		    System.out.print("走る距離を入力してください：");
		    int disBicycle = scanner.nextInt();
		    bicycle1.run(disBicycle, count);
		    System.out.println("=================");
		    car1.printData();
		    System.out.println("-----------------");
		    System.out.print("走る距離を入力してください：");
		    int disCar = scanner.nextInt();
		    flag = car1.run(disCar, count, flag);
		    System.out.println("-----------------");
		    System.out.print("給油しますか？（給油する場合は1を入力）：");
		    if(scanner.nextInt() == 1) {
			    System.out.print("給油する量を入力してください：");
			    int chargeFuel = scanner.nextInt();
			    car1.charge(chargeFuel);		    	
		    }
		    
		    count++;
		    System.out.print("終了しますか？（終わる場合は1を入力）：");
		    if(scanner.nextInt() == 1) break;
		    System.out.println("=================");
	    }
		
	}

}
