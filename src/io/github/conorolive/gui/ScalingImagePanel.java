package io.github.conorolive.gui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

/**
 * Creates a JPanel that holds an image and will fit or fill the image as the
 * window is resized.
 *
 * @author Conor Olive
 */
public class ScalingImagePanel extends javax.swing.JPanel {

	/**
	 * Eclipse generated serial version ID
	 */
	private static final long serialVersionUID = -6323449128460888539L;

	/**
	 * Holds the image to be displayed.
	 */
	private BufferedImage image;

	/**
	 * Class constructor using the superclass constructor.
	 */
	public ScalingImagePanel() {

		super();

	}

	/**
	 * {@inheritDoc}
	 */
    @Override
    public void paintComponent(Graphics g)
    {

    	super.paintComponent(g);

    	if (image != null)
    	{

    		Dimension imageDimension = new Dimension(image.getWidth(),
    				image.getHeight());

    		double scaleFactor = Math.min(1.0, getScaleFactorToFill(
    				imageDimension, getSize() ) );

    		int scaleWidth = (int) Math.round(image.getWidth() * scaleFactor);
    		int scaleHeight = (int) Math.round(image.getHeight() * scaleFactor);

    		g.drawImage(image, 0, 0, scaleWidth, scaleHeight, this);

    	}

    }

    public void setImage(BufferedImage bImage)
    {

    	image = bImage;

    }

	/**
	 * Finds the scaling factor needed to fit a whole image within a given
	 * dimension, without stretching.
	 *
	 * @param original the dimension of the original image
	 * @param toFit the dimension to fit the image to
	 * @return the scale factor needed to fit the image
	 */
	private double getScaleFactorToFit(Dimension original, Dimension toFit)
    {

    	double scaleFactor = 1d;

    	if (original != null && toFit != null)
    	{

    		double widthScaleFactor = getScaleFactor(original.width,
    				toFit.width);

    		double heighScaleFactor = getScaleFactor(original.height,
    				toFit.height);

    		scaleFactor = Math.min(widthScaleFactor, heighScaleFactor);

    	}

    	return scaleFactor;

    }

	/**
	 * Finds the scaling factor needed to fill a image within a given
	 * dimension, allowing overflow.
	 *
	 * @param original the dimension of the original image
	 * @param toFit the dimension to fit the image to
	 * @return the scale factor needed to fill the image
	 */
	private double getScaleFactorToFill(Dimension original,
    		Dimension toFit)
    {

    	double widthScaleFactor = getScaleFactor(original.width, toFit.width);
    	double heightScaleFactor = getScaleFactor(original.height,
    			toFit.height);

    	double dScale = Math.max(widthScaleFactor, heightScaleFactor);

    	return dScale;

    }

	/**
	 * Finds the scaling factor between two dimensions.
	 *
	 * @param originalSize the dimension of the original image
	 * @param targetSize the target dimension of the image
	 * @return the scaling factor to scale the original image by in order to
	 * reach the target size
	 */
	private double getScaleFactor(int originalSize, int targetSize)
	{

		return (double) targetSize / (double) originalSize;

	}

}
