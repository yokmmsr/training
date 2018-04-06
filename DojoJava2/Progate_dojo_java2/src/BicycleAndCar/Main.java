package BicycleAndCar;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		Bicycle bicycle1 = new Bicycle("ビアンキ", "緑", 0);
		bicycle1.printData();
	    System.out.println("-----------------");
	    System.out.print("走る距離を入力してください：");
	    int disBicycle = scanner.nextInt();
	    bicycle1.run(disBicycle);
	    
	    System.out.println("=================");
	    Car car1 = new Car("フェラーリ", "赤", 0, 100);
	    car1.printData();
	    System.out.println("-----------------");
	    System.out.print("走る距離を入力してください：");
	    int disCar = scanner.nextInt();
	    car1.run(disCar);
	    System.out.println("-----------------");
	    System.out.print("給油する量を入力してください：");
	    int chargeFuel = scanner.nextInt();
	    car1.charge(chargeFuel);
		
	}

}
