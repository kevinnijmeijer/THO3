import lejos.nxt.*;
import lejos.robotics.Color;

public class MovementController {
	//declare peripherals used in movement
	NXTRegulatedMotor leftEngine = Motor.getInstance(0);
	NXTRegulatedMotor rightEngine = Motor.getInstance(1);
	
	ColorSensor leftEye = new ColorSensor(SensorPort.S1);
	ColorSensor rightEye = new ColorSensor(SensorPort.S2);
	
	static final int threshold = 60;
	static final int maxSpeed = 100;

	boolean leftOfLine;
	boolean rightOfLine;
	
	public MovementController(){
		leftEye.setFloodlight(Color.BLUE);
		rightEye.setFloodlight(Color.BLUE);
	}
	public int jesusGrabTheWheel(){
		
		//Calibrate highs
		LCD.drawString("Calibrate highs", 0 , 0);
		Button.waitForAnyPress();
		leftEye.calibrateHigh();
		rightEye.calibrateHigh();
		
		LCD.clear();
		
		//Calibrate lows
		LCD.drawString("Calibrate lows", 0, 0);
		Button.waitForAnyPress();
		leftEye.calibrateLow();
		rightEye.calibrateLow();
		
		LCD.clear();
		
		//some engine speed stuff
		leftEngine.setSpeed(maxSpeed);
		rightEngine.setSpeed(maxSpeed);
		leftEngine.forward();
		rightEngine.forward();
		
		//basic running program
		while(!Button.ESCAPE.isDown()){
			LCD.clear();
			LCD.drawString("LeftValue:" + leftEye.getLightValue(),0,0);
			LCD.drawString("rightValue" + rightEye.getLightValue(), 0, 1);
			
			
			if (rightEye.getLightValue() < threshold){
				rightEngine.setSpeed(maxSpeed/2);
				leftEngine.setSpeed(maxSpeed);
			}
			
			else if (leftEye.getLightValue() < threshold){
				rightEngine.setSpeed(maxSpeed);
				leftEngine.setSpeed(maxSpeed/2);
			}
			
			else{
				leftEngine.setSpeed(maxSpeed);
				rightEngine.setSpeed(maxSpeed);
			}
			
		}
		
		return 1;
	
	}
	
}

