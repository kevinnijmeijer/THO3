import lejos.nxt.*;


/**
 * 
 * @author Kevin Nijmeijer
 * @version 2.0
 *
 */
public class RobotController{	
	// Objects
	private SensorController sc;
	private DriveController dc;
	
	private int valueLeft, valueRight ,objectDistanceLimit;

	
	/**
	 * Constructor
	 */
	public RobotController(MyColorSensor right, MyColorSensor left) {
		sc = new SensorController(this, right, left);
		dc = new DriveController();
		objectDistanceLimit = 20;
		
	}

	/**
	 *  Decides what to do with the given Ultrasonic value
	 * @param float oldVal
	 * @param float newVal
	 * 
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
	 *  Decides what to do with the given Color value.
	 *  Left of the robot
	 * @param float oldVal
	 * @param float newVal
	 */
	public void processRight(float oldVal, float newVal) {
		valueRight = (int)newVal;
		//LCD.drawString("right:" + valueRight, 0,1);
		check();
		//dc.correctRight();
	}	
	/**
	 *  Decides what to do with given Light value
	 *  Right of the robot
	 * @param float oldVal
	 * @param float newVal
	 */
	public void processLeft(float oldVal, float newVal) {
		valueLeft = (int)newVal;
		//LCD.drawString("left:" + valueLeft, 0,2);
		check();
		//dc.correctLeft();
	}
	
	public void check(){
		//System.out.println("Checking");
		if (valueRight < dc.threshold && valueLeft > dc.threshold){ // && valueLeft > dc.threshold
			//System.out.println("Correct right");
			dc.correctRight();
		}
		
		else if (valueLeft < dc.threshold && valueRight > dc.threshold){ // && valueRight > dc.threshold
			//System.out.println("Correct left");
			dc.correctLeft();
		}
		else{
			dc.drive(dc.maxSpeed);
		}
	}

	public void driveRobot() {
		dc.drive(dc.maxSpeed);
	}
	
}

