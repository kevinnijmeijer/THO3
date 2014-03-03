import lejos.nxt.*;

public class MovementController {
	//declare peripherals used in movement
	NXTRegulatedMotor wheel;
	NXTRegulatedMotor engine;
	LightSensor eye;
	int threshhold = 45;
	
	public MovementController(NXTRegulatedMotor w, NXTRegulatedMotor e, LightSensor s) {
		
		//define peripherals
		eye = s;
		engine = e;
		wheel = w;
	}

	public void jesusGrabTheWheel(){
		
		Button.waitForAnyPress();
		int white = eye.getLightValue();
		LCD.drawString(""+ white, 0, 0, true);
		
		Button.waitForAnyPress();
		int black = eye.getLightValue();
		LCD.drawString(""+ black, 0, 1, true);
		
		engine.setSpeed(25);
		engine.forward();
		
		while(!Button.ESCAPE.isDown()){
			if (eye.readValue() > threshhold){
				wheel.rotate(20);
			}
			else{
				wheel.rotate(-20);
			}
		}
	}
}

