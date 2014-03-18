
import lejos.nxt.*;

import java.util.ArrayList;


/**
 * 
 * @author Peter Markotic
 * @version 2.0
 *
 */
public class MyColorSensor extends ColorSensor implements UpdatingSensor  {
	private ArrayList<SensorListener> SensorListeners;
	private float oldValue;
	private float newValue;
	private int sensorNummer;
	
	public MyColorSensor(SensorPort port, int sN) {
		super(port);
		sensorNummer = sN;
		SensorListeners = new ArrayList<SensorListener>();	
	}
	
	public void addListener(SensorListener lst){
		SensorListeners.add(lst);	
	}
	
	public synchronized void updateState() {
		//this.setFloodlight(BLUE);
		newValue = getLightValue();
		
		if (newValue != oldValue) {
			for (SensorListener sl: SensorListeners) {
				sl.stateChanged(this, oldValue, newValue);
			}
		}
		oldValue = newValue;
	}
}
