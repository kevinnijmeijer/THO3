

import lejos.nxt.*;
import java.util.ArrayList;

/**
 * 
 * @author Peter Markotic
 * @version 2.0
 * 
 * Extended version of UltrasonicSensor. Passes new values from polling
 * on to known listeners.
 *
 */
public class MyUltrasonicSensor extends UltrasonicSensor implements UpdatingSensor  {
	private ArrayList<SensorListener> SensorListeners;
	private float oldValue;
	private float newValue;	
	
	/**
	 * 
	 * @param SensorPort port
	 * @return none
	 * 
	 * Constructor. Defines port and instantiates array.
	 */
	public MyUltrasonicSensor(SensorPort port) {
		super(port);
		SensorListeners  = new ArrayList<SensorListener>();
	}	
	
	/**
	 * 
	 * @param SensorListener lst
	 * @return none
	 * 
	 * Add a SensorListener to pass values on to.
	 */
	public void addListener(SensorListener lst){
		SensorListeners.add(lst);	
	}
		
	/**
	 * @param none
	 * @return none
	 * 
	 * Retrieves current sensor value, passes on to known
	 * SensorListeners.
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
