import lejos.nxt.Button;
import lejos.nxt.LCD;
import lejos.nxt.SensorPort;
import lejos.robotics.Color;

public class Calibrate {
	// arraylist met sensoren maken
	private MyColorSensor leftEye;
	private MyColorSensor rightEye;
	
	public Calibrate(){
		rightEye = new MyColorSensor(SensorPort.S2, 2);
		leftEye = new MyColorSensor(SensorPort.S1, 1);
		leftEye.setFloodlight(Color.RED);
		rightEye.setFloodlight(Color.RED);
	}
	
	public void calibrateSenors(){
		LCD.drawString("Calibrate high left", 0 , 0);
		Button.waitForAnyPress();
		leftEye.calibrateHigh();
		
		LCD.clear();
		
		LCD.drawString("Calibrate high Right", 0 , 0);
		Button.waitForAnyPress();
		rightEye.calibrateHigh();
		
		LCD.clear();
		
		LCD.drawString("Calibrate Low left", 0 , 0);
		Button.waitForAnyPress();
		leftEye.calibrateLow();
		
		LCD.clear();
		
		//Calibrate lows
		LCD.drawString("Calibrate Low Right", 0, 0);
		Button.waitForAnyPress();
		rightEye.calibrateLow();
		
		LCD.clear();
		
		Button.waitForAnyPress();
	}
	
	public MyColorSensor getRight(){
		return rightEye;
	}
	
	public MyColorSensor getLeft(){
		return leftEye;
	}
}
