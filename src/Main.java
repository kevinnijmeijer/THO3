

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		Calibrate c = new Calibrate();
		c.calibrateSenors();
		
		RobotController rc = new RobotController(c.getRight(),c.getLeft());
		rc.driveRobot(); 
		
		//Button.waitForAnyPress();

		//m.jesusGrabTheWheel();
	}	
}
