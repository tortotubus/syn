package io.github.conorolive;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

@SuppressWarnings("serial")
public class ScalingPane extends javax.swing.JPanel {

	private BufferedImage image;
    
    public void setImage(BufferedImage bImage) {
    	image = bImage;
    }

	public double getScaleFactor(int iMasterSize, int iTargetSize) {    
		double dScale = 1;
		dScale = (double) iTargetSize / (double) iMasterSize;
		
		return dScale;
	}

    public double getScaleFactorToFit(Dimension original, Dimension toFit) {
    	double dScale = 1d;
    	
    	if (original != null && toFit != null) {    
    		double dScaleWidth = getScaleFactor(original.width, toFit.width);
    		double dScaleHeight = getScaleFactor(original.height, toFit.height);
    		
    		dScale = Math.min(dScaleHeight, dScaleWidth);
    	}
    	
    	return dScale;
    }
    
    public double getScaleFactorToFill(Dimension masterSize, Dimension targetSize) {
    	double dScaleWidth = getScaleFactor(masterSize.width, targetSize.width);
    	double dScaleHeight = getScaleFactor(masterSize.height, targetSize.height);
    	double dScale = Math.max(dScaleHeight, dScaleWidth);
    	
    	return dScale;
    }
    
    @Override
    public void paintComponent(Graphics g) {
    	super.paintComponent(g);
    	
    	if (image != null) {
    		double scaleFactor = Math.min(1.0, getScaleFactorToFill(new Dimension(image.getWidth(), image.getHeight()), getSize()));
    		
    		int scaleWidth = (int) Math.round(image.getWidth() * scaleFactor);
    		int scaleHeight = (int) Math.round(image.getHeight() * scaleFactor);
    		
    		g.drawImage(image, 0, 0, scaleWidth, scaleHeight, this);
    	}
    }
}