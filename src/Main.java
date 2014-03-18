

public class Main {
	/**
	 *
	 * @author Peter Markotiç, Kevin Nijmijer, Michiel Tegelberg
	 * @version 3.0
	 *
	 * The main thread starts by making a new instance of the Calibrate class.
	 * Then it calls Calibrate.calibrateSensors() which prompts the user to go through the calibration process.
	 * After that the main thread makes an instance of RobotController with the calibrated left and right MyColorSensors.
	 * Lastly the main calls RobotController.driveRobot() which makes the robot drive autonomously. 
	 */
	public static void main(String[] args) {

		Calibrate c = new Calibrate();
		c.calibrateSenors();
		
		RobotController rc = new RobotController(c.getRight(),c.getLeft());
		rc.driveRobot(); 
	}	
}
