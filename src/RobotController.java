

import lejos.nxt.*;


/**
 * 
 * @author Peter Markotic
 * @version 2.0
 *
 */
public class RobotController implements SensorListener{	
	// Objects
	private SensorController sc;
	private MotorController mc;
	
	
	/**
	 * Constructor
	 */
	public RobotController() {
		this.sc = new SensorController(this);
		this.mc = new MotorController();	
	}
	
	/**
	 *  Decides what to do with the given Ultrasonic value
	 * @param float oldVal
	 * @param float newVal
	 * 
	 */
	public void processUltrasonic(float oldVal, float newVal) {
		LCD.drawString("ultra:" + newVal, 0,0);
		if (newVal < 20) {
			mc.stop();
			Sound.playTone(400,500);
		}else{
			mc.drive(PAR_NORMALSPEED, 100);
		}			
	}
	
	/**
	 *  Decides what to do with the given Color value.
	 *  Left of the robot
	 * @param float oldVal
	 * @param float newVal
	 */
	public void processRight(float oldVal, float newVal) {
		LCD.drawString("Right:" + newVal, 0,1);
		LCD.drawString("Tacho:" + mc.getTachoA(), 0,4);
		valueLightRight = newVal;	
		if ((mc.getTachoA() > 1550) || (needCorrection))  {
	
			
			
			
			
	/**
	 *  Decides what to do with given Light value
	 *  Right of the robot
	 * @param float oldVal
	 * @param float newVal
	 */
	public void processLeft(float oldVal, float newVal) {
		LCD.drawString("Left:" + newVal, 0,2);
		valueLightLeft = newVal;
		if ((mc.getTachoA() > 1550) || (needCorrection)) {
			if (valueLightLeft >= (LIGHT_EXPWHITE - 5) && valueLightLeft <= (LIGHT_EXPWHITE + 5)) {
				mc.stop();
					if (valueLightRight >= (LIGHT_EXPWHITE - 8) && valueLightRight <= (LIGHT_EXPWHITE)) {
						mc.stop();
						turnToNextLane();
					}else{
						mc.stop();
						mc.correctLeft();
						needCorrection = false;
					}
			}
		}
	}

	
	/*public void correctForward() {
		needCorrection = true;
		mc.drive(PAR_NORMALSPEED/2, 1000);
		
	}*/
	
	public void turnToNextLane() {
		if (lanesDriven % 2 == 0) {
			mc.driveArcRight(10.0,10.0,0.5);
			mc.resetTachoCounts();
			driveLane();
		}
		else if (lanesDriven % 2 == 1) {
			mc.driveArcLeft(10.0,10.0,0.5);
			mc.resetTachoCounts();
			driveLane();
		}
		/*else if (!continueLeft && (lanesDriven % 2 == 0)) {
			mc.turnToLaneLeft();
			correctForward();
			driveLane();
		}
		else if (!continueLeft && (lanesDriven % 2 == 1)) {
			mc.turnToLaneRight();
			correctForward();
			driveLane();
		}*/
	}
	
	public void driveLane() {
		//mc.resetTachoCounts();
		mc.drive(PAR_NORMALSPEED, 100);
		lanesDriven++;
	}

	@Override
	public void stateChanged(UpdatingSensor s, float oldVal, float newVal) {
		// TODO Auto-generated method stub
		
	}
	
}

