import lejos.nxt.*;


public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//stuur
		Button.waitForAnyPress();
		NXTRegulatedMotor motorA = Motor.getInstance(0);
		NXTRegulatedMotor motorB = Motor.getInstance(1);
		
		motorA.setSpeed(300);
		motorB.setSpeed(200);
		
		
		motorB.forward();
		motorA.resetTachoCount();
		motorA.forward();
		
		while(true){
			if(motorA.getTachoCount()> 180){
				motorA.backward();
			}
			else if(motorA.getTachoCount() < -180){
				motorA.forward();
			}	
			
		}
	}	
}
