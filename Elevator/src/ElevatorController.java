import java.util.ArrayList;
import java.util.Queue;
import java.util.Random;
import java.util.Scanner;
import java.util.NoSuchElementException;
import java.util.Collections;
import java.util.List;

	public class ElevatorController implements IElevatorController {
		private int numElevators;
		private int numFloors;
		private List<Elevator> elevators = null;
		private ArrayList<Queue<Integer>> passengers = null;
		private Scanner sc;	
		private void  initElevators(int numElevators, int numFloors) {
			if(numElevators<0)  throw new IllegalArgumentException();
			elevators = new ArrayList<Elevator>();
			for (int i=0; i<numElevators;i++) {
				Elevator el = new Elevator(numFloors);
				el.setElID(i);
				elevators.add(el);
				
			}
			Collections.shuffle(elevators);
		}
		
		private void initFloors(int numFloors) {
			if(numFloors<0)  throw new IllegalArgumentException();
			passengers = new ArrayList<Queue<Integer>> (numFloors);
		}

		public ElevatorController (int numElevators, int numFloors) {
			initFloors(numFloors);
			initElevators(numElevators, numFloors);
			sc=new Scanner(System.in);
			this.numElevators = numElevators;
			this.numFloors = numFloors;
		}
		
		private int calculateRoute(int afloor, int bfloor) {
			return Math.abs(afloor - bfloor);
		}		
		
		public Queue<Integer> getPassengers(int floor) {
			if((floor<0) || (floor >= numFloors )  ) throw new IllegalArgumentException();
			return passengers.get(floor);
		}

		public void setPassengers(Queue<Integer> pssgrs, int floor) {
			if(pssgrs == null) throw new NullPointerException();
			if((floor<0) || (floor >= numFloors )  ) throw new IllegalArgumentException();
			this.passengers.add(floor, pssgrs);
		}
		
		@Override
		public  void status() {
			int i = 0;
			for(Elevator el:elevators) {
				System.out.print( " elID =  " + i++ + " CurrentFloor = " + el.getCurrentFloor() + " Moving =  " + el.getMove()  +  " DIRECTION = " + el.getDirection() + "\n");
			}
		}
		
		@Override
		public Elevator getElevator(int elevatorId) {
//			if((elevatorId<0) || (elevatorId >= numElevators ) ) throw new NoSuchElementException();
//			return elevators[elevatorId];
			return null;
		}

		public boolean validate(int floor,int floor_number,boolean direction) {
			if(direction==true && floor_number>floor)
				return true;
			if(direction==false && floor_number<floor)
				return true;
			return false;
		}
		
		@Override
		public void pickup(int floor, boolean direction) {
			if((floor<0) || (floor >= numFloors )) throw new IllegalArgumentException();
			Elevator.DIRECTION userDirection = (direction) ? (Elevator.DIRECTION.UP):(Elevator.DIRECTION.DOWN);
			int minDistance = numFloors;
			Elevator closestElevator = null;
			int d=Integer.MAX_VALUE;
			for (Elevator elevator : elevators ) {
				if(
					((userDirection == Elevator.DIRECTION.UP) && (elevator.getDirection() == Elevator.DIRECTION.UP) && (floor >= elevator.getCurrentFloor())) || 
					((userDirection == Elevator.DIRECTION.DOWN) && (elevator.getDirection() == Elevator.DIRECTION.DOWN) && (floor <= elevator.getCurrentFloor())) ) { 
						d =	calculateRoute(floor,elevator.getCurrentFloor());
						
				}else {
					Elevator.DIRECTION move_direction=floor > elevator.getCurrentFloor()?Elevator.DIRECTION.UP:Elevator.DIRECTION.DOWN;
					elevator.setDirection(move_direction);
					d =	calculateRoute(floor,elevator.getCurrentFloor());
				}
				if(d<minDistance) {					
					minDistance = d;
					closestElevator = elevator;
				}
				
			}
			System.out.println("Got the response from floor number "+floor+" go to the nearest elevator "+closestElevator.getElID()+" which is coming from "+closestElevator.getCurrentFloor());
			System.out.println("------------Doors opened-----------------");
			System.out.print("Enter the floor number ");
			int floor_number=sc.nextInt();
			if(validate(floor,floor_number,direction)) {
				System.out.println("------------Doors closed-----------------");
				System.out.println("-----------Reached floor "+floor_number+" ------------- ");
				closestElevator.setCurrentFloor(floor_number);
				closestElevator.setDirection(Elevator.DIRECTION.NONE);
			}	
		}
		
}
