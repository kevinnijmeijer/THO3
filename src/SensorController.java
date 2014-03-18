import lejos.nxt.*;
import lejos.robotics.Color;

/**
 * @author Peter Markotiç, Michiel Tegelberg
 * @version 2.0
 * 
 * SensorController is the receiver of all changed states and values
 * of the sensors. After receiving, it has the ability to decide what is to be done
 * with those values, calling actions from MotorController
 */
public class SensorController implements SensorListener {
	
	private RobotController rc; /*!< This is the instance of RobotController */
	private MyColorSensor leftEye; /*!< This is the instance of the left ColorSensor */
	private MyColorSensor rightEye;/*!< This is the instance of the right ColorSensor */
	private MyUltrasonicSensor glasses;/*!< This is the instance of the UltrasonicSensor */
	private SensorHandler sh;/*!< This is the instance of SensorHandler */
	
	/**
	 * Constructor for SensorController
	 * Sets the RobotController Instance and adds the Sensors to the SensorHandler.
	 * 
	 * @param rc The instance of RobotController
	 * @param right The right MyColorSensor
	 * @param left The left MyColorSensor
	 */
	public SensorController(RobotController rc, MyColorSensor right, MyColorSensor left) {
		this.rc = rc;
		
		rightEye =right;
		rightEye.addListener(this);
		leftEye = left;
		leftEye.addListener(this);
		glasses = new MyUltrasonicSensor(SensorPort.S3);
		glasses.addListener(this);
		
		sh = SensorHandler.getInstance();
		sh.addSensor(leftEye);
		sh.addSensor(glasses);
		sh.addSensor(rightEye);
	}
	
	
	/**
	 * Inherited from interface, receiver values from polled sensors,
	 * then sends these through to individual methods for each type of sensor.
	 * 
	 * @param s
	 * @param oldVal
	 * @param newVal
	 * @return void
	 */
	public void stateChanged(UpdatingSensor s, float oldVal, float newVal) {
		// Pass on to the designated methods
		LCD.drawString("LEFT " + leftEye.getLow() + "<" + leftEye.getLightValue() + "<" + leftEye.getHigh(), 0, 1);
		LCD.drawString("RIGHT " + rightEye.getLow() + "<" + rightEye.getLightValue() + "<" + rightEye.getHigh(), 0, 2);
		if (s == glasses) {
			rc.processUltrasonic(oldVal, newVal);
		}
		
		if (s == rightEye) {
			rc.processRight(oldVal, newVal); 
		}
		
		if (s == leftEye) {
			rc.processLeft(oldVal, newVal);
		}
	}
}
