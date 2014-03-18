import lejos.nxt.Button;
import lejos.nxt.LCD;
import lejos.nxt.SensorPort;
import lejos.robotics.Color;

/**
 *
 * @author Peter Markotiç, Kevin Nijmijer, Michiel Tegelberg
 * @version 1.0
 * 
 */
public class Calibrate {
	private MyColorSensor leftEye;  /*!< This is the left ColorSensor */
	private MyColorSensor rightEye; /*!< This is the right ColorSensor */
	
	/**
	 * Constructor for the Calibrate class.
	 * Makes instances of MyColorSensor for the right and left ColorSensor.
	 * Sets the floodlights for the left and right ColorSensor.
	 */
	public Calibrate(){
		rightEye = new MyColorSensor(SensorPort.S2, 2);
		leftEye = new MyColorSensor(SensorPort.S1, 1);
		leftEye.setFloodlight(Color.RED);
		rightEye.setFloodlight(Color.RED);
	}
	
	/**
	 * Function to aid the user with calibrating the sensors
	 * Takes the user through the process of calibrating all ColorSensors.
	 * 
	 * @return void
	 */
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
		
		LCD.drawString("Calibrate Low Right", 0, 0);
		Button.waitForAnyPress();
		rightEye.calibrateLow();
		
		LCD.clear();
		
		Button.waitForAnyPress();
	}
	
	/**
	 * Function to get the right calibrated MyColorSensor.
	 * 
	 * @return MyColorSensor rightEye
	 */
	public MyColorSensor getRight(){
		return rightEye;
	}
	
	/**
	 * Function to get the left calibrated MyColorSensor.
	 * 
	 * @return MyColorSensor leftEye
	 */
	public MyColorSensor getLeft(){
		return leftEye;
	}
}
