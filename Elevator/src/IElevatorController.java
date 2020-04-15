
public interface IElevatorController {
	public void status ();
	public Elevator getElevator(int elevatorID);
	public void pickup(int floor , boolean direction);
}
