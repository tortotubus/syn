package io.github.conorolive.jankare;

import java.awt.image.*;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * A class which takes an image and uses a continuous k-means algorithm to 
 * partition an image into k amount of clusters.
 * <p>
 * A continuous version of the k-means algorithm partitions all pixels
 * in the set. This is done by seeding each cluster with a random sampling 
 * of k amount of pixels, which is assumed to be representative of the larger
 * data set. Additional randomly-chosen pixels from the image are added to a 
 * cluster chosen by shortest Euclidean distance, and then the cluster's 
 * centroid is updated by finding the arithmetic mean of all pixels in the given
 * cluster. 
 * <p>
 * This method is expected not to find the global optimum, but will still 
 * converge upon a local optimum very close to the global optimum in a much 
 * shorter amount of time for larger data sets (such as higher-resolution 
 * images), where the normal k-means algorithm or Lloyd's algorithm would take
 * much more time to converge on an local optimum. Because it also converges 
 * much quicker, no additional iterative passes are needed such as with normal 
 * k-means clustering.
 * <p>
 * This algorithm is NP-hard.
 * 
 * @author	Conor Olive
 * @since	1.0
 * @see 	java.awt.image.BufferedImage	
 */
public class ImageSegmentor {
	
	/**
	 * The image to be segmented
	 */
	private BufferedImage imageResource;
	
	/**
	 * Height of the image resource.
	 */
	private int imageHeight;
	
	/**
	 * Width of the image resource.
	 */
	private int imageWidth;
	
	/** 
	 * Contains all clusters used in the algorithm.
	 */
	private List<Cluster> pixelClusters;
	
	/**
	 * Contains all pixels from imageResource.
	 */
	private List<Integer> imagePixels;
	
	/**
	 * Contains the pixels at the center of each pixel cluster.
	 */
	private List<Integer> imageColors;

	/**
	 * Class constructor specifying image to segment
	 * 
	 * @param	image	the image to be segmented
	 * @see 		java.awt.image.BufferedImage
	 */
	public ImageSegmentor(BufferedImage image) 
	{
		
		imageResource = image;
		
		imageHeight = imageResource.getHeight();
		imageWidth = imageResource.getWidth();
		
		shufflePixelList();
		
	}
	
	/**
	 * Uses a continuous k-means algorithm to find k amount of representative 
	 * colors in the image.
	 * 
	 * @param	k	the amount of colors to find from the image
	 */
	public void segmentPixels(int k) 
	{
		
		createClusters(k);
		imageColors = new ArrayList<Integer>(k);
		
		for (Integer pixel : imagePixels) 
		{
			
			findNearestCluster(pixel).addPixel(pixel);
			
		}
		
		for (Cluster c : pixelClusters) 
		{
			
			imageColors.add(c.getCentroid());
			
		}
		
	}
	
	/**
	 * Puts all pixels of imageResource in a list and shuffles the list 
	 */
	public void shufflePixelList() 
	{
		
		// Creates a large enough ArrayList
		imagePixels = new ArrayList<Integer>( imageWidth * imageHeight );
		
		// Adds all pixels in the image to imagePixels
		for ( int x = 0; x < imageWidth; x++ ) 
		{
			
			for ( int y = 0; y < imageHeight; y++ ) 
			{
				
				imagePixels.add(imageResource.getRGB(x, y));
				
			}
			
		}
		
		// Shuffles imagePixels
		Collections.shuffle(imagePixels);
		
	}
	
	/**
	 * Gets the pixels at the centroids of each cluster.
	 *  
	 * @return		the representative colors of the image in TYPE_3BYTE_BGR
	 * @see			java.awt.image.BufferedImage#TYPE_3BYTE_BGR
	 */
	public List<Integer> getColors() 
	{
		
		if ( imageColors == null ) 
		{
			
			throw new NullPointerException("segmentPixels must first be "
					+ "called");
		
		}
		
		return imageColors;
		
	}
	
	/**
	 * Gets the hexadecimal color of pixels at the centroids of each cluster.
	 * 
	 * @return 		the representative colors of the image in hexadecimal format
	 */
	public List<String> getColorsAsHex() 
	{
		
		if ( imageColors == null ) 
		{
			
			throw new NullPointerException("segmentPixels must first be "
					+ "called");
		
		}
		
		List<String> imageColorsHex = new ArrayList<String>(imageColors.size());
		
		
		for (Integer pixel : imageColors) {
				
			int r = pixel >> 16&0xFF;  
	        	int g = pixel >> 8&0xFF;  
	        	int b = pixel >> 0&0xFF;
	        	
	        	String redHex = Integer.toHexString(r);
	        	String greenHex = Integer.toHexString(g);
	        	String blueHex = Integer.toHexString(b);
	        	
	        	if ( redHex.length() < 2 ) 
	        	{
	        			
	        		redHex = "0" + redHex;
	        	
	        	}
	        	
	        	if ( greenHex.length() < 2 ) 
	        	{
	        		
	        		greenHex = "0" + greenHex;
	        		
	        	}
	        	
	        	if ( blueHex.length() < 2 ) 
	        	{
	        			
	       			blueHex = "0" + blueHex;
	        		
	        	}
	        	
	       		imageColorsHex.add( "#" + redHex + greenHex + blueHex );
			
			}
			
		return imageColorsHex;
		
	}	
	
	/**
	 * Finds the nearest cluster to the given pixel 
	 * 
	 * @param	pixel	a pixel in TYPE_3BYTE_BGR format
	 * @return		the cluster which is closest to pixel
	 */
	private Cluster findNearestCluster(int pixel) 
	{
		
		Cluster nearestCluster = null; 

		int distanceSmallest = Integer.MAX_VALUE;
        
        for ( Cluster c : pixelClusters ) 
        {
       
            int distanceToCluster = c.calculateDistance(pixel); 
            
            if (distanceToCluster < distanceSmallest) 
            { 
                
            	distanceSmallest = distanceToCluster; 
                nearestCluster = c;
                
            }
            
        }
        
        return nearestCluster;
        
	}
	
	/**
	 * Creates a list of new clusters seeded with pixels from imagePixels.
	 * 
	 * @param 	k	the amount of clusters to create
	 * @return		a list of Clusters with seeded 
	 * @see 		Cluster
	 * @see			ImageSegments#imagePixels
	 */
	private void createClusters(int k) 
	{
		
		pixelClusters = new ArrayList<Cluster>(k);
		
		for ( int i = 0; i < k; i++ ) 
		{
			
			pixelClusters.add(new Cluster(imagePixels.get(0)));
			imagePixels.remove(0);
			
		}
		
	}
	
}

