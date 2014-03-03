

import lejos.nxt.*;


/**
 * 
 * @author Mathijs van der Sangen
 * @version 2.0
 * 
 * SensorController is the receiver of all changed states and values
 * of the sensors. After receiving, it has the ability to decide what is to be done
 * with those values, calling actions from MotorController
 */
public class SensorController implements SensorListener {
	private RobotController rc;
	
	private MyLightSensor mcs;
	private MyUltrasonicSensor mus;
	private MyLightSensor mls;
	
	private SensorHandler sh;
	
	/**
	 * Constructor for SensorController
	 * 
	 * @param none
	 * @return none
	 */
	public SensorController(RobotController rc) {
		this.rc = rc;
		
		mls = new MyLightSensor(SensorPort.S3, 2);
		mls.addListener(this);
		mcs = new MyLightSensor(SensorPort.S2, 1);
		mcs.addListener(this);
		mus = new MyUltrasonicSensor(SensorPort.S1);
		mus.addListener(this);
		
		
		sh = SensorHandler.getInstance();
		sh.addSensor(mcs);
		sh.addSensor(mus);
		sh.addSensor(mls);
	}
	
	
	/**
	 * Inherited from interface, receiver values from polled sensors,
	 * then sends these through to individual methods for each type of sensor.
	 * 
	 * @param UpdatingSensor s
	 * @param float oldVal
	 * @param float newVal
	 * @return none
	 */
	public void stateChanged(UpdatingSensor s, float oldVal, float newVal) {
		// Pass on to the designated methods
		if (s == mus) {
			rc.processUltrasonic(oldVal, newVal);
		}
		
		if (s == mcs) {
			rc.processRight(oldVal, newVal);
		}
		
		if (s == mls) {
			rc.processLeft(oldVal, newVal);
		}
	}
}
