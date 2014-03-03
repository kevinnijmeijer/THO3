import lejos.nxt.*;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		MovementController m = new MovementController();
		
		Button.waitForAnyPress();

		m.jesusGrabTheWheel();
	}	
}
