import lejos.nxt.LCD;
import lejos.nxt.Motor;
import lejos.nxt.NXTRegulatedMotor;
/**
 *
 * @author Peter Markotiç, Kevin Nijmijer, Michiel Tegelberg
 * @version 2.0
 *
 */
public class DriveController {
	private NXTRegulatedMotor motorA; /*!< This is the left motor */
	private NXTRegulatedMotor motorC;/*!< This is the right motor */
	final int threshold = 50;/*!< The threshold for the Colorsensors. A higher value is considered white, a lower value is considered black. */
	final int maxSpeed = 200; /*!< The maximum speed the robot drives at. */

	/**
	 * Constructor for the MotorController
	 * Calls NXT motor instances.
	 */
	public DriveController() {
		motorA = Motor.getInstance(0);
		motorC = Motor.getInstance(2);
		
	}

	/**
	 * Sets speed v and sets both motors to drive forward.
	 * 
	 * @param v velocity of the robot
	 * @return void
	 */
	public void drive(int v) {
		motorA.setSpeed(v);
		motorC.setSpeed(v);
		motorA.forward();
		motorC.forward();

	}

	/**
	 * Stops the robot 
	 * 
	 * @return void
	 */
	public void stop() {
		motorC.stop();
		motorA.stop();
	}

	/**
	 * Resets the TachoCount for both motors
	 * 
	 * @return void
	 */
	public void resetTachoCounts() {
		motorA.resetTachoCount();
		motorC.resetTachoCount();
	}

	/**
	 * Returns the tachoCount for the left motor.
	 * 
	 * @return int
	 */
	public int getTachoA() {
		return motorA.getTachoCount();
	}

	/**
	 * Returns the tachoCount for the right motor.
	 * 
	 * @return int
	 */
	public int getTachoC() {
		return motorC.getTachoCount();
	}

	/**
	 * Stops the left motor so the robot turns right.
	 * 
	 * @return void
	 */
	public void correctRight() {
		motorA.stop();
		motorC.setSpeed(maxSpeed);
	}

	/**
	 * Stops the right motor so the robot turns left.
	 * 
	 * @return void
	 */
	public void correctLeft() {
		motorC.stop();
		motorA.setSpeed(maxSpeed);
	}
	
	/**
	 * Initiates an evasive maneuver in which the robot attempts to drive around an obstacle in a clockwise arc.
	 * 
	 * @return void
	 */
	public void evade() {
		stop();
		motorC.setSpeed(maxSpeed);
		motorA.setSpeed(maxSpeed);
		motorA.resetTachoCount();
		motorC.backward();
		motorA.forward();
		while (getTachoA() < 180) {
			System.out.println("TURNING!");
		}
		stop();
		motorA.setSpeed(maxSpeed/2);
		motorC.resetTachoCount();
		motorA.forward();
		motorC.forward();
		while (getTachoC() < 1400){
			System.out.println("ARCING!");
		}
		stop();
		motorC.setSpeed(maxSpeed);
		motorA.setSpeed(maxSpeed);
		motorA.resetTachoCount();
		motorC.backward();
		motorA.forward();
		while (getTachoA() < 90) {
			System.out.println("TURNING!");
		}
		stop();
	}
}


