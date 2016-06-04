package io.github.conorolive.jankare;

/**
 * A cluster which can hold pixels as three-dimensional
 * vertices. 
 * 
 * @author	Conor Olive
 */
public class Cluster 
{
	
	private int pixelCount;
	
	// Holds the current centroid.
	private int red;
	private int green;
	private int blue;
	
	// Holds all colors in cluster.
	private int reds;
	private int greens;
	private int blues;

	/**
	 * Constructs the cluster with a given initial pixel.
	 * 
	 * @param pixel a pixel value formatted in TYPE_3BYTE_BGR used to initialize
	 * the cluster  
	 * @see java.awt.image.BufferedImage#TYPE_3BYTE_BGR
	 */
	public Cluster(int pixel) 
	{
		
		int r = pixel >> 16&0xFF;  
        	int g = pixel >> 8&0xFF;  
        	int b = pixel >> 0&0xFF;  
        	
        	red = r;
        	green = g; 
        	blue = b; 
        
        	addPixel(pixel); 
        
	}
	
	/**
	 * Calculates the Euclidean distance of a given pixel to the current 
	 * centroid (also known as geometric center) of this cluster.
	 *  
	 * @param pixel a pixel value formatted in TYPE_3BYTE_BGR
	 * @return distance in euclidean space between this cluster's centroid and 
	 * the given pixel
	 * @see java.awt.image.BufferedImage#TYPE_3BYTE_BGR
	 */
	public int calculateDistance(int pixel) 
	{
		
		int r = pixel >> 16&0xFF;
		int g = pixel >> 8&0xFF;
		int b = pixel >> 0&0xFF;
		
		int rx = Math.abs( red - r );
		int gx = Math.abs( green - g );
		int bx = Math.abs( blue - b );
		
		int distance = ( rx + gx + bx ) / 3;
		
		return distance;
		
	}
	
	/**
	 * Calculates the current centroid (also known as geometric center) of 
	 * the cluster by finding the arithmetic mean of the pixels in this cluster.
	 * 
	 * @return the current centroid of this cluster
	 */
	public int getCentroid() 
	{
		
		int r = reds / pixelCount;
		int g = greens / pixelCount;
		int b = blues / pixelCount;
		
		return 0xff000000 | r << 16 | g << 8 | b;
		
	}
	
	/**
	 * Adds a given pixel value from the pixels in the cluster.
	 * 
	 * @param pixel a pixel value formatted in TYPE_3BYTE_BGR
	 * @see java.awt.image.BufferedImage#TYPE_3BYTE_BGR 
	 */
	public void addPixel(int pixel) 
	{
		
		int r = pixel >> 16&0xFF;
		int g = pixel >> 8&0xFF;
		int b = pixel >> 0&0xFF;
		
		reds += r;
		greens += g;
		blues += b;
		
		pixelCount++;
		
		red = reds / pixelCount;
		green = greens / pixelCount;
		blue = blues / pixelCount;
		
	}
	
	/**
	 * Removes a given pixel value from the pixels in the cluster.
	 * 
	 * @param pixel a pixel value formatted in TYPE_3BYTE_BGR
	 * @see java.awt.image.BufferedImage#TYPE_3BYTE_BGR 
	 */
	public void removePixel(int pixel) 
	{
		
		int r = pixel >> 16&0xFF;
		int g = pixel >> 8&0xFF;
		int b = pixel >> 0&0xFF;
		
		reds -= r;
		greens -= g;
		blues -= b;
		
		pixelCount--;
		
		red = reds / pixelCount;
		green = greens / pixelCount;
		blue = blues / pixelCount;
		
	}
	
	/**
	 * Clears this cluster of all values.
	 */
	public void clearCluster() 
	{
		
		red = green = blue = 0;
		reds = greens = blues = 0;
		pixelCount = 0;
		
	}

}
 
