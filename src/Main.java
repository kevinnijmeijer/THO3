import lejos.nxt.Motor;
import lejos.nxt.NXTRegulatedMotor;


public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//stuur
		NXTRegulatedMotor motorA = Motor.getInstance(0);
		NXTRegulatedMotor motorB = Motor.getInstance(1);
		
		motorA.setSpeed(100);
		motorB.setSpeed(100);
		
		while(true){
			motorA.forward();
			motorB.forward();
		}
	}
}
