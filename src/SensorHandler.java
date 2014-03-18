import java.util.ArrayList;

/**
 * @author Peter Markotiç
 * @version 2.0
 * 
 * Holds an array of UpdatingSensors and polls each once every given period of time.
 */
public class SensorHandler extends Thread {
	private static SensorHandler theHandler; /*!< This is the static instance of SensorHandler */
	private ArrayList<UpdatingSensor> Sensors; /*!< This is the ArrayList of UpdatingSensors */
	
	/**
	 * Constructor for the singleton SensorHandler.
	 */
	private SensorHandler(){
		Sensors = new ArrayList<UpdatingSensor>();
		start();
	}
	
	/**
	 * Retrieves a reference to the SensorHandler.
	 * 
	 * @return SensorHandler
	 */
	public static SensorHandler getInstance() {
		if(theHandler == null) {
			theHandler = new SensorHandler();
		}
		return theHandler;
	}
	
	/**
	 * Adds an UpdatingSensor to array for polling
	 * 
	 * @param us
	 * @return void
	 */
	public void addSensor(UpdatingSensor us){
		Sensors.add(us);
	}
	
	/**
	 * Starts Thread for SensorHandler
	 * 
	 * @return void
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

