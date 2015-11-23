package ascensor;

import java.util.Scanner;


public class Main {
	
	Ascensor ascensores[];

	public static void main(String[] args) {
		Main exercise = new Main();
		exercise.menu();

	}
	public void menu(){
		int option = 0;
		Scanner keyboard = new Scanner(System.in);
		int floor = 0, elevator = 0;
		
		ascensores = new Ascensor[3];
		
		ascensores[0]= new Ascensor(0, "A", false);
		ascensores[1]= new Ascensor(0, "B", false);
		ascensores[2]= new Ascensor(0, "C", false);
		
		
			floor = 0;
			elevator = 0;
			
			System.out.println("1. Mover ascensor");
			System.out.println("2. Llamar ascensor");
			System.out.println("0. Exit");
			
			option = keyboard.nextInt(); keyboard.nextLine();
			
			switch (option){
			
			case 1:
				System.out.print("Introduce numero ascensor: A(0), B(1), C(2) ");
				elevator = keyboard.nextInt(); keyboard.nextLine();
				System.out.print("Introduce la planta a la que desea ir: ");
				floor = keyboard.nextInt(); keyboard.nextLine();
				ascensores[elevator].setFloor(floor);
				
				break;
			case 2:
				System.out.print("Introduce la planta el la que es: ");
				floor = keyboard.nextInt(); keyboard.nextLine();
				
				for(int i=0; i<3; i++){
					if(ascensores[i].distance(floor) < ascensores[elevator].distance(floor)){
						elevator = i;
					}
				}
				System.out.println("Tiene que insertar "+ascensores[elevator].nombre+" ascensor");
				ascensores[elevator].setFloor(floor);
				
				
				break;
			
	
		}while (option!=0);

	}

}
