

import lejos.nxt.*;
import java.util.ArrayList;

/**
 * 
 * @author Peter Markotiç
 * @version 2.0
 * 
 * Extended version of UltrasonicSensor. Passes new values from polling
 * on to known listeners.
 *
 */
public class MyUltrasonicSensor extends UltrasonicSensor implements UpdatingSensor  {
	private ArrayList<SensorListener> SensorListeners; /*!< This is the List of SensorListeners */
	private float oldValue; /*!< This is the holding variable for the last value */
	private float newValue;	 /*!< This is the holding variable for the current value */
	
	/**
	 * Constructor. Defines port and instantiates array.
	 * 
	 * @param port
	 */
	public MyUltrasonicSensor(SensorPort port) {
		super(port);
		SensorListeners  = new ArrayList<SensorListener>();
	}	
	
	/**
	 * Add a SensorListener to pass values on to.
	 * 
	 * @param lst
	 */
	public void addListener(SensorListener lst){
		SensorListeners.add(lst);	
	}
		
	/**
	 * Retrieves current sensor value, passes on to known SensorListeners if a change in value is detected.
	 * 
	 * @return void
	 */
	public void updateState() {
		newValue = getDistance();
		if (newValue != oldValue) {
			for (SensorListener sl: SensorListeners) {
				sl.stateChanged(this, oldValue, newValue);
			}
		}
		oldValue = newValue;
	}
}
