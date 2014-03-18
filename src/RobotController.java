import lejos.nxt.*;


/**
 * 
 * @author Kevin Nijmeijer, Michiel Tegelberg
 * @version 2.0
 *
 */
public class RobotController{	
	
	private SensorController sc; /*!< This is a private instance of SensorController */
	private DriveController dc; /*!< This is a private instance of DriveController */
	
	private int valueLeft;  /*!< This is the holding variable for the value of the left ColorSensor */
	private int	valueRight; /*!< This is the holding variable for the value of the right ColorSensor */
	private int	objectDistanceLimit;  /*!< This is the value of the ultraSonicSensor at which an evasive maneuver will be initiated */

	
	/**
	 * Constructor for RobotController
	 * 
	 * Makes an instance of SensorController with the calibrated left and right ColorSensor.
	 * Makes an instance of DriveController 
	 * Sets the objectDistanceLimit to 20
	 * 
	 * @param right the right calibrated MyColorSensor from the Calibrate class.
	 * @param left the left calibrated MyColorSensor from the Calibrate class.
	 * 
	 */
	public RobotController(MyColorSensor right, MyColorSensor left) {
		sc = new SensorController(this, right, left);
		dc = new DriveController();
		objectDistanceLimit = 20;
		
	}

	/**
	 * Decides what to do with the given Ultrasonic value
	 * 
	 * @param oldVal the last updated value for the Ultrasonic Value
	 * @param newVal the current value for Ultrasonic
	 * @return void
	 */
	public void processUltrasonic(float oldVal, float newVal) {
		//LCD.drawString("ultra:" + newVal, 0,0);
		if (newVal < objectDistanceLimit) {
			dc.evade();
		}else {
			dc.drive(dc.maxSpeed);
		}
	}
	
	/**
	 * Decides what to do with the given Light value of the right ColorSensor.
	 * 
	 * @param oldVal is not used
	 * @param newVal is the current value
	 * @return void
	 */
	public void processRight(float oldVal, float newVal) {
		valueRight = (int)newVal;
		check();
	}	
	/**
	 * Decides what to do with given Light valueof the left ColorSensor.
	 * 
	 * @param oldVal is not used
	 * @param newVal is the current value
	 * @return void
	 */
	public void processLeft(float oldVal, float newVal) {
		valueLeft = (int)newVal;
		check();
	}
	
	/**
	 * Checks the current values for the ColorSensors versus the Threshold
	 * 
	 * @return void
	 */
	public void check(){
		if (valueRight < dc.threshold && valueLeft > dc.threshold){ 
			dc.correctRight();
		}
		
		else if (valueLeft < dc.threshold && valueRight > dc.threshold){ 
			dc.correctLeft();
		}
		else{
			dc.drive(dc.maxSpeed);
		}
	}

	/**
	 * Sets the robot to drive forward at maxSpeed.
	 * 
	 * @return void
	 */
	public void driveRobot() {
		dc.drive(dc.maxSpeed);
	}
	
}

