import lejos.nxt.LCD;
import lejos.nxt.Motor;
import lejos.nxt.NXTRegulatedMotor;
/**
 *
 * @author Peter Markotic
 * @version 2.0
 *
 */
public class DriveController {

	// The left motor
	private NXTRegulatedMotor motorA;
	// The right motor
	private NXTRegulatedMotor motorC;
	final int threshold = 50;
	final int maxSpeed = 200;


	/**
	 * Constructor for the MotorController
	 * Calls NXT motor instances.
	 *
	 */
	public DriveController() {
		motorA = Motor.getInstance(0);
		motorC = Motor.getInstance(2);
		
	}

	/**
	 *
	 * @param v as velocity in cm/s
	 * @return void
	 *
	 *
	 */
	public void drive(int v) {
		motorA.setSpeed(v);
		motorC.setSpeed(v);
		motorA.forward();
		motorC.forward();

	}

	public void stop() {
		//motorA.setSpeed(4);
		//motorC.setSpeed(4);
		//motorA.forward();
		//motorC.forward();
		motorC.stop();
		motorA.stop();
	}

	public void resetTachoCounts() {
		motorA.resetTachoCount();
		motorC.resetTachoCount();
	}

	public int getTachoA() {
		return motorA.getTachoCount();
	}

	public int getTachoC() {
		return motorC.getTachoCount();
	}

	public void correctRight() {
		motorA.stop();
		motorC.setSpeed(maxSpeed);
		//motorA.forward();
	}

	public void correctLeft() {
		motorC.stop();
		motorA.setSpeed(maxSpeed);
		//motorC.forward();
	}
	public void evade() {
		stop();
		motorC.setSpeed(maxSpeed);
		motorA.setSpeed(maxSpeed);
		motorA.resetTachoCount();
		motorC.backward();
		motorA.forward();
		while (getTachoA() < 180) {
			System.out.println("WATCH THE F OUT!");
		}
		stop();
		motorA.setSpeed(maxSpeed/2);
		motorC.resetTachoCount();
		motorA.forward();
		motorC.forward();
		while (getTachoC() < 1400){
			System.out.println("ALRIGHT PASSIN' THROUGH!");
		}
		stop();
		motorC.setSpeed(maxSpeed);
		motorA.setSpeed(maxSpeed);
		motorA.resetTachoCount();
		motorC.backward();
		motorA.forward();
		while (getTachoA() < 90) {
			System.out.println("WATCH THE F OUT!");
		}
		stop();
	}
}


