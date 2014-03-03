import lejos.nxt.Motor;
import lejos.nxt.NXTRegulatedMotor;
/**
 *
 * @author Peter Markotic
 * @version 2.0
 *
 */
public class DriveController {
	//// Static Variables
	private static double WIDTH = 15;
	// Diameter of the wheels in cm
	private static double WHEELDIAMETER = 6;

	// The left motor
	private NXTRegulatedMotor motorA;
	// The right motor
	private NXTRegulatedMotor motorC;


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
	 * @param s as distance to travel in cm
	 * @return void
	 *
	 * The distance to drive and the speed are first recalculated into
	 * an understandable format for the prototype, namely circular degrees.
	 *
	 * These values are set after both motor's tachocount has been reset.
	 * Tachocount will be used to track driven distance.
	 * Both motors are instructed to drive forward, and a loop checking
	 * whether the determined distance has been driven will trigger
	 * them to stop.
	 */
	public void drive(double v, double s) {
		double distanceDegrees = (360 * (s/Math.PI/WHEELDIAMETER));
		double speedDegrees = (360 * (v/Math.PI/WHEELDIAMETER));

		motorA.setSpeed((int)speedDegrees);
		motorC.setSpeed((int)speedDegrees);
		motorA.forward();
		motorC.forward();


	}

	/*public void rotateRight(int dgrs) {
		motorA.setSpeed((int)(360 * (10/Math.PI/WHEELDIAMETER)));
		motorA.rotate(dgrs);
	}

	public void rotateLeft(int dgrs) {
		motorC.setSpeed((int)(360 * (10/Math.PI/WHEELDIAMETER)));
		motorC.rotate(dgrs);
	}

	public void turnToLaneLeft() {
		rotateRight(-465);
		resetTachoCounts();
		drive(10, 12);
		resetTachoCounts();
		rotateLeft(380);
	}

	public void turnToLaneRight() {
		rotateLeft(-465);
		resetTachoCounts();
		drive(10, 12);
		resetTachoCounts();
		rotateRight(380);
	}*/

	public void stop() {
		motorA.setSpeed(4);
		motorC.setSpeed(4);
		motorA.forward();
		motorC.forward();
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

	public boolean isDriving() {
		if (motorA.isMoving() || motorC.isMoving()) {
			return true;
		}
		else {
			return false;
		}
	}

	public void correctRight() {
		motorC.setSpeed(5);
		motorA.setSpeed(30);
		motorA.forward();
		motorC.forward();
	}


	public void correctLeft() {
		motorA.setSpeed(5);
		motorC.setSpeed(30);
		motorC.forward();
		motorA.forward();
	}

	/**
	 *
	 * @param v as velocity in cm/s
	 * @param r as circumference of circle in cm
	 * @param f as fraction of the circle to be driven
	 * @return void
	 *
	 * driveArc for a rightward turn.
	 *
	 * Calculates all required parts for the function first.
	 * The robot is sent into a rightward arc by setting different
	 * speeds to each wheel. It drives until its distance * f(fraction) that
	 * will determine the amount of circles to drive.
	 *
	 * If the given speed is not 0 yet the given distance is, the robot
	 * is sent to drive around it's own axis, spinning in place.
	 * It will spin as much as the given fraction.
	 */
	public void driveArcRight(double v, double r, double f) {
		//v = velocity in cm/s
		//r = circumference circle in cm
		//f = fraction of cirle (1 = full, 0.25 = quarter)
		
		//double omtrekWiel = (Math.PI * D);
		//double tijd = (2 * Math.PI * (r - (0.5 * B)) / v);
		
		//berekening voor buitenste wiel
		double afstandBuitenCM = (2 * Math.PI *(r + (0.5 * WIDTH))); // cm
		double afstandBuitenG = (720 * (r + (0.5 * WIDTH)) / WHEELDIAMETER); // graden
		double snelheidBuitenCM = v; // cm/s
		double snelheidBuitenG = afstandBuitenG / (afstandBuitenCM / snelheidBuitenCM); // graden/s
		
		//berekening voor binnenste wiel
		double afstandBinnenG = (720 * (r - (0.5 * WIDTH)) / WHEELDIAMETER); // graden
		double snelheidBinnenG = afstandBinnenG / (afstandBuitenCM / snelheidBuitenCM); // graden/s
		
		if (r != 0) {
			motorA.resetTachoCount();
			motorC.resetTachoCount();
			motorC.setSpeed((int)snelheidBuitenG);
			motorA.setSpeed((int)snelheidBinnenG);
			motorA.forward();
			motorC.forward();
			
			// loop until destination reached yo
			
			while (true) {
				if (motorA.getTachoCount() > (afstandBuitenG * f) ||
						motorC.getTachoCount() > (afstandBinnenG * f)) {
					motorA.stop();
					motorC.stop();
					break;
				}
			}	
			//int tachoCountA = motorA.getTachoCount();
			//int tachoCountC = motorC.getTachoCount();
			System.out.println("a:" + snelheidBuitenG);
			System.out.println("c:" + snelheidBinnenG);
		}
		else {
			motorC.setSpeed((int)(360 * v / Math.PI / WHEELDIAMETER));
			motorA.setSpeed((int)(360 * v / Math.PI / WHEELDIAMETER));
			
			motorA.forward();
			motorC.backward();
			
			while (true) {
				if (motorA.getTachoCount() > ((360*WIDTH/WHEELDIAMETER) * f) ||
						motorC.getTachoCount() > ((360*WIDTH/WHEELDIAMETER) * f)) {
					motorA.stop();
					motorC.stop();
					break;
				}
			}
		}
	}

	/**
	 *
	 * @param v as velocity in cm/s
	 * @param r as circumference of circle in cm
	 * @param f as fraction of the circle to be driven
	 * @return void
	 *
	 * driveArc for a leftward turn.
	 *
	 * A copy from driveArcRight, the change is that all
	 * cases of motorA. and motorC. are switched.
	 *
	 * Calculates all required parts for the function first.
	 * The robot is sent into a leftward arc by setting different
	 * speeds to each wheel. It drives until its distance * f(fraction) that
	 * will determine the amount of circles to drive.
	 *
	 * If the given speed is not 0 yet the given distance is, the robot
	 * is sent to drive around it's own axis, spinning in place.
	 * It will spin as much as the given fraction.
	 */
	public void driveArcLeft(double v, double r, double f) {
		//v = velocity in cm/s
		//r = circumference circle in cm
		//f = fraction of cirle (1 = full, 0.25 = quarter)
		
		//double omtrekWiel = (Math.PI * D);
		//double tijd = (2 * Math.PI * (r - (0.5 * B)) / v);
		
		//berekening voor buitenste wiel
		double afstandBuitenCM = (2 * Math.PI *(r + (0.5 * WIDTH))); // cm
		double afstandBuitenG = (720 * (r + (0.5 * WIDTH)) / WHEELDIAMETER); // graden
		double snelheidBuitenCM = v; // cm/s
		double snelheidBuitenG = afstandBuitenG / (afstandBuitenCM / snelheidBuitenCM); // graden/s
		
		//berekening voor binnenste wiel
		double afstandBinnenG = (720 * (r - (0.5 * WIDTH)) / WHEELDIAMETER); // graden
		double snelheidBinnenG = afstandBinnenG / (afstandBuitenCM / snelheidBuitenCM); // graden/s
		
		if (r != 0) {
			motorA.resetTachoCount();
			motorC.resetTachoCount();
			motorA.setSpeed((int)snelheidBuitenG);
			motorC.setSpeed((int)snelheidBinnenG);
			motorA.forward();
			motorC.forward();
			
			// loop until destination reached yo
			
			while (true) {
				if (motorA.getTachoCount() > (afstandBuitenG * f) ||
						motorC.getTachoCount() > (afstandBinnenG * f)) {
					motorA.stop();
					motorC.stop();
					break;
				}
			}
			
			
			
			//int tachoCountA = motorA.getTachoCount();
			//int tachoCountC = motorC.getTachoCount();
			System.out.println("a:" + snelheidBuitenG);
			System.out.println("c:" + snelheidBinnenG);
		}
		else {
			motorA.setSpeed((int)(360 * v / Math.PI / WHEELDIAMETER));
			motorC.setSpeed((int)(360 * v / Math.PI / WHEELDIAMETER));
			
			motorA.forward();
			motorC.backward();
			
			while (true) {
				if (motorA.getTachoCount() > ((360*WIDTH/WHEELDIAMETER) * f) ||
						motorC.getTachoCount() > ((360*WIDTH/WHEELDIAMETER) * f)) {
					motorA.stop();
					motorC.stop();
					break;
				}
			}

		}
	}
}


