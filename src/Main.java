import lejos.nxt.*;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		//Instantiate the motors
		Button.waitForAnyPress();
		
		NXTRegulatedMotor wheel = Motor.getInstance(0);
		NXTRegulatedMotor engine = Motor.getInstance(1);
		engine.resetTachoCount();
		wheel.resetTachoCount();
		
		//Instantiate the sensors
		LightSensor eye = new LightSensor(SensorPort.S2, true);
		
		MovementController m = new MovementController(wheel, engine, eye);
		m.jesusGrabTheWheel();
	}	
}
