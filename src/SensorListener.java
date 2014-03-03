


/**
 * 
 * @author Peter Markotic
 * @version 1.0
 * 
 * Defines the stateChanged method for SensorListeners
 */
public interface SensorListener {

	public abstract void stateChanged(UpdatingSensor s, float oldVal, float newVal);

}