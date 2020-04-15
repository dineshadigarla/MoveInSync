
public class ElevatorService {
   public static void main(String[] args) {
	   ElevatorController elevatorController = new ElevatorController(Constants.ELEVATORS,Constants.FLOORS);
	   elevatorController.pickup(37,true);
	   elevatorController.pickup(38,true);
	   elevatorController.pickup(2,false);
	   elevatorController.pickup(3,false);
   }
}
