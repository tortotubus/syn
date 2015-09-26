package io.github.conorolive;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

public class ScalingPane extends javax.swing.JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2457491039467603953L;
	
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
    protected void paintComponent(Graphics g) {
    	super.paintComponent(g);
    	double scaleFactor = Math.min(1d, getScaleFactorToFit(new Dimension(image.getWidth(), image.getHeight()), getSize()));
    	
    	// double scaleFactor = Math.min(1d, getScaleFactorToFill(new Dimension(image.getWidth(), image.getHeight()), getSize()));
    	int scaleWidth = (int) Math.round(image.getWidth() * scaleFactor);
    	int scaleHeight = (int) Math.round(image.getHeight() * scaleFactor);
    	
    	Image scaled = image.getScaledInstance(scaleWidth, scaleHeight, Image.SCALE_SMOOTH);
    	int width = getWidth() - 1;
    	int height = getHeight() - 1;

    	int x = (width - scaled.getWidth(this)) / 2;
    	int y = (height - scaled.getHeight(this)) / 2;

    	g.drawImage(scaled, x, y, this);
    }
}