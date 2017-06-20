package Assignments;

import java.io.IOException;

import org.jointheleague.ecolban.rpirobot.IRobotAdapter;
import org.jointheleague.ecolban.rpirobot.IRobotInterface;
import org.jointheleague.ecolban.rpirobot.SimpleIRobot;

public class Assignment2 extends IRobotAdapter {
	Sonar sonar = new Sonar();
	
	public Assignment2(IRobotInterface iRobot) {
		super(iRobot);
	}

	public static void main(String[] args) throws Exception {
		System.out.println("Try event listner, rev Monday 2030");
		IRobotInterface base = new SimpleIRobot();
		Assignment2 rob = new Assignment2(base);
		rob.setup();
		while(rob.loop()){}
		rob.shutDown();
		
	}

	
	
	private void setup() throws Exception {
		//SETUP CODE GOES HERE!!!!!
		
	}
	
	private boolean loop() throws Exception{
		//LOOP CODE GOES HERE!!!!!
		readSensors(100);
		 driveDirect(1000,1000);
		int[] lightBumpReadings = getLightBumps();
        
         
         if(lightBumpReadings[0] > 0){
 			driveDirect(300, -200);
 			sleep(25);
 		
 		}else if(lightBumpReadings[1] > 0){
 			driveDirect(300, -200);
 			sleep(25);
 		
 		}else if(lightBumpReadings[2] > 0){
 			driveDirect(-500,500);
 			sleep(500);
 		}
 		else if(lightBumpReadings[3] > 0){
 			driveDirect(-500,500);
 			sleep(500);
 		}
 		else if(lightBumpReadings[4] > 0){
 			driveDirect(-200, 300);
 			sleep(25);
 		}
 		
 		else if(lightBumpReadings[5] > 0){
 	 			driveDirect(-200, 300);
 	 			sleep(25);
 		}
 	    
         if(isBumpLeft() || isBumpRight()){
 			driveDirect(-100, -100);
 			sleep(500);
 			driveDirect(-500, 500);
 			sleep(3000);
 			driveDirect(0, 0);
 			
 			return false;
 		}

		return true;
	}
	
	//private void readSensors(int i) {
		// TODO Auto-generated method stub
		
	//}

	private void sleep(int amt){
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