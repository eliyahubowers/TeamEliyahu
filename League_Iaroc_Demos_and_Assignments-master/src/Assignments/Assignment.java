package Assignments;

import java.io.IOException;

import org.jointheleague.ecolban.rpirobot.IRobotAdapter;
import org.jointheleague.ecolban.rpirobot.IRobotInterface;
import org.jointheleague.ecolban.rpirobot.SimpleIRobot;

public class Assignment extends IRobotAdapter {
	// Sonar sonar = new Sonar();
	Camera cam;
	int green = 20;
	int red = 80;
	int i = 0;

	public Assignment(IRobotInterface iRobot) {
		super(iRobot);
	}

	public static void main(String[] args) throws Exception {
		System.out.println("Try event listner, rev Monday 2030");
		IRobotInterface base = new SimpleIRobot();
		Assignment rob = new Assignment(base);
		rob.setup();
		while (rob.loop()) {
		}
		rob.shutDown();

	}

	private void setup() throws Exception {
		// SETUP CODE GOES HERE!!!!!
		cam = new Camera(50, 50);
		cam.enableBurst();
		cam.turnOffPreview();
		cam.setTimeout(2);

	}

	private boolean loop() throws Exception {
		// LOOP CODE GOES
		driveDirect(500, 160);
		readSensors(100);
		i++;
		if (i == 20) {
			System.out.println("Picture taken");
			i = 0;
			cam.takeRGBPicture();

			double g = cam.getGreenPercentage(green, false);
			double r = cam.getRedPercentage(red, false);
			System.out.println(r + "-red");
			System.out.println(g + "-green");
			if (g > red) {
				driveDirect(500, 500);
				sleep(300);
			}
			if (r > g) {
				driveDirect(-500, 500);
				sleep(900);
				driveDirect(500,500);
				System.out.println("turn around roomba");
			}

		}

		int[] lightBumpReadings = getLightBumps();

		if (lightBumpReadings[0] > 0) {
			driveDirect(-200, 400);
			sleep(150);

		} else if (lightBumpReadings[1] > 0) {
			driveDirect(-300, 400);
			sleep(150);

		} else if (lightBumpReadings[2] > 0) {
			driveDirect(-400, 400);
			sleep(150);

		} else if (lightBumpReadings[3] > 0) {
			driveDirect(-400, 400);
			sleep(150);

		} else if (lightBumpReadings[4] > 0) {
			driveDirect(-300, 400);
			sleep(150);

		} else if (lightBumpReadings[5] > 0) {
			driveDirect(-200, 400);
			sleep(150);

		}

		/*
		 * if (isBumpLeft() || isBumpRight()) { driveDirect(-100, -100); sleep(500); driveDirect(-500, 500);
		 * sleep(3000); driveDirect(0, 0);
		 * 
		 * return false; }
		 */

		return true;
	}

	// private void readSensors(int i) {
	// TODO Auto-generated method stub

	// }

	private void sleep(int amt) {
		try {
			Thread.sleep(amt);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void shutDown() throws IOException {
		reset();
		stop();
		closeConnection();
	}
}