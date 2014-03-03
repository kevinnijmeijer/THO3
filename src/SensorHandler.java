
import java.util.ArrayList;

/**
 * 
 * @author Peter Markotic
 * @version 2.0
 * 
 * Holds an array of UpdatingSensors and polls each once every
 * given period of time.
 */
public class SensorHandler extends Thread {
	private static SensorHandler theHandler;
	private ArrayList<UpdatingSensor> Sensors;
	
	/**
	 * Constructor for the singleton SensorHandler.
	 * 
	 * @param none
	 * @return none
	 * */
	private SensorHandler(){
		Sensors = new ArrayList<UpdatingSensor>();
		start();
	}
	
	/**
	 * Retrieves a reference to the SensorHandler.
	 * 
	 * @param none
	 * @return SensorHandler
	 */
	public static SensorHandler getInstance() {
		if(theHandler == null) {
			theHandler = new SensorHandler();
		}
		return theHandler;
	}
	
	/**
	 * 
	 * Adds an UpdatingSensor to array for polling
	 * 
	 * @param UpdatingSensor us
	 * @return none
	 */
	public void addSensor(UpdatingSensor us){
		Sensors.add(us);
	}
	
	/**
	 * Starts Thread for SensorHandler
	 * 
	 * @param none
	 * @return none
	 */
	public void run(){
		while(true){
			for(UpdatingSensor us: Sensors){
				us.updateState();	
			}
			try {Thread.sleep(100);}
			catch (InterruptedException e){}
		}
	}
	
}

