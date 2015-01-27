package org.usfirst.frc.team619.hardware;

import edu.wpi.first.wpilibj.CameraServer;

public class Camera {

	CameraServer camera;
	//The current name of the camera is cam0 as of 1/24/15
	
	/*this can be found by accessing the webdashboard with the camera plugged into Athena*/
	public Camera(String cameraName){
		camera = CameraServer.getInstance();
		camera.setQuality(50);
		camera.startAutomaticCapture(cameraName);
	}
	
}
