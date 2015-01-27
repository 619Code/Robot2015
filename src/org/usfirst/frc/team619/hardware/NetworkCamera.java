package org.usfirst.frc.team619.hardware;

import edu.wpi.first.wpilibj.image.HSLImage;
import edu.wpi.first.wpilibj.image.NIVisionException;
import edu.wpi.first.wpilibj.vision.AxisCamera;

public class NetworkCamera {

	AxisCamera camera;
	
	String cameraHost = "10.6.19.2";
	
	public NetworkCamera(){
		camera = new AxisCamera(cameraHost);
	}
	
	public HSLImage getImage() throws NIVisionException{
		return camera.getImage();
	}
	
}
