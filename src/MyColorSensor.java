
import lejos.nxt.*;

import java.util.ArrayList;


/**
 * 
 * @author Peter Markotiç, Kevin Nijmijer, Michiel Tegelberg
 * @version 2.0
 *
 */
public class MyColorSensor extends ColorSensor implements UpdatingSensor  {
	private ArrayList<SensorListener> SensorListeners;  /*!< This is the List of SensorListeners */
	private float oldValue;  /*!< This is the holding variable for the last value */
	private float newValue;  /*!< This is the holding variable for the current value */
	private int sensorNummer; /*!< This is the number of the sensor */
	
	/**
	 * Constructor for MyColorSensor.
	 * 
	 * @param port The port the sensor is connected to.
	 * @param sN The number of the sensor.
	 */
	public MyColorSensor(SensorPort port, int sN) {
		super(port);
		sensorNummer = sN;
		SensorListeners = new ArrayList<SensorListener>();	
	}
	
	/**
	 * Add a SensorListener to pass values on to.
	 * 
	 * @param lst The Listener
	 * @return void
	 */
	public void addListener(SensorListener lst){
		SensorListeners.add(lst);	
	}
	
	/**
	 * Retrieves current sensor value, passes on to known SensorListeners if a change in value is detected.
	 * 
	 * @return void
	 */
	public synchronized void updateState() {
		newValue = getLightValue();
		
		if (newValue != oldValue) {
			for (SensorListener sl: SensorListeners) {
				sl.stateChanged(this, oldValue, newValue);
			}
		}
		oldValue = newValue;
	}
}
