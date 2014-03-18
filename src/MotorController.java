import lejos.nxt.*;
import lejos.robotics.Color;

public class MotorController {
	
	boolean objectFound = false;
	//declare peripherals used in movement
	NXTRegulatedMotor leftEngine = Motor.getInstance(0);
	NXTRegulatedMotor rightEngine = Motor.getInstance(1);
	
	ColorSensor leftEye = new ColorSensor(SensorPort.S1);
	ColorSensor rightEye = new ColorSensor(SensorPort.S2);
	
	UltrasonicSensor ears = new UltrasonicSensor(SensorPort.S3);
	
	static final int threshold = 60;
	static final int maxSpeed = 150;

	boolean leftOfLine;
	boolean rightOfLine;
	int distance;
	
	public MotorController(){
		//leftEye.setFloodlight(Color.BLUE);
		//rightEye.setFloodlight(Color.BLUE);
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
		
		Button.waitForAnyPress();
		//some engine speed stuff
		leftEngine.setSpeed(maxSpeed);
		rightEngine.setSpeed(maxSpeed);
		leftEngine.forward();
		rightEngine.forward();
		
		//basic running program
		while(!Button.ESCAPE.isDown() && !objectFound){
			
			distance = ears.getDistance();
			LCD.drawString("LeftValue:  " + leftEye.getLightValue(), 0, 0);
			LCD.drawString("rightValue: " + rightEye.getLightValue(), 0, 1);
			LCD.drawString("HearingAid: " + distance, 0, 2);
			
			
			if (rightEye.getLightValue() < threshold && leftEye.getLightValue() > threshold){
				rightEngine.setSpeed(maxSpeed/10);
				leftEngine.setSpeed(maxSpeed);
			}
			
			else if (leftEye.getLightValue() < threshold && rightEye.getLightValue() > threshold){
				rightEngine.setSpeed(maxSpeed);
				leftEngine.setSpeed(maxSpeed/10);
			}
			
			else{
				leftEngine.setSpeed(maxSpeed);
				rightEngine.setSpeed(maxSpeed);
			}
			
			if (distance < 30){
				objectFound = true;
			}
			
		}
		
		return 1;
	
	}
	
}

