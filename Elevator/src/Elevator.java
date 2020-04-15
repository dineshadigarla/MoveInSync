public class Elevator {
	public enum DIRECTION {
		NONE, UP, DOWN
	}
	private DIRECTION direction = DIRECTION.NONE;
	private Boolean move = false;
	
	private int elID;		
	private int cf = 0;
	private int min = Constants.MIN_FLOOR;
	private int max = Constants.MAX_FLOOR;
	
	public Elevator(int numFloors) {
		if(numFloors<0)  throw new IllegalArgumentException();
	}


	public Integer getCurrentFloor() {
		return cf;
	}
	
	public void setCurrentFloor(Integer i) {
		this.cf=i;
	}
	
	public int getGoalFloor() {
		if(direction == DIRECTION.UP ) return max;
		if(direction == DIRECTION.DOWN ) return min;
		return -1;
	}
	
	public boolean getMove() {
		return move;
	}
	public DIRECTION getDirection() {
		return direction;
	}
	public void setDirection(Elevator.DIRECTION direction) {
		this.direction=direction;
	}
	public int getElID() {
		return elID;
	}
	public void setElID(int id) {
		this.elID=id;
	}
}