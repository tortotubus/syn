package io.github.conorolive;

import java.awt.image.*;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.imageio.ImageIO;
import java.util.Random;

public class ImageSegments {
	
	private BufferedImage imageResource;
	
	private int imageHeight;
	private int imageWidth;
	
	private Cluster[] imageClusters = new Cluster[0];
	
	private int[] imageColors = new int[0];

	// Constructor taking a BufferedImage resource.
	public ImageSegments(BufferedImage image) {
		
		imageResource = image;
		
	}
	
	// Constructor taking a file resource.
	public ImageSegments(File file) {
		
		try { 
			
			imageResource = ImageIO.read(file);
			imageHeight = imageResource.getHeight();
			imageWidth = imageResource.getWidth();
			
		} catch (IOException e) {
			
			e.printStackTrace();
			
		}
		
	}
	
	// Constructor taking a URL.
	public ImageSegments(URL url) throws MalformedURLException {
		
		try { 
			
			imageResource = ImageIO.read(url);
			imageHeight = imageResource.getHeight();
			imageWidth = imageResource.getWidth();
			
		} catch (IOException e) {
			
			e.printStackTrace();
			
		}
		
	}

	// Returns k amount of color clusters in the image
	public void colorSegmentation(int k) {
		
		// Initializing clusters
		imageClusters = createClustersRandom(k);
		
		// This is where the magic happens
		for ( int y = 0; y < imageHeight; y++) {
			
			for ( int x = 0; x < imageWidth; x++) {
				
				int pixelRGB = imageResource.getRGB(x, y);

				findNearestCluster(pixelRGB).addPixel(pixelRGB);
				
			}
			
		}
		
		// Allocating memory in imageColors for k colors.
		imageColors = new int[k];
		
		// Sets object's colors variable.
		for ( int i = 0; i < imageClusters.length; i++) {
			
			imageColors[i] = imageClusters[i].getRGB();
			
		}
		
	}
	
	// Getter method for colors.
	public int[] getColors() {
		
		return imageColors;
		
	}
	
	// Getter method returning colors as hex values.
	public String[] getColorsAsHex() {
		
		// Makes a new string array to hold hex colors.
		String[] imageColorsHex = new String[imageColors.length];
		
		// Checks to make sure the image's colors have been found first.
		if ( imageColors.length == 0 ) {
			
			return null;
		
		}
		
		// If colors have been found, copies RGB values into a new array as hex
		// values.
		else {
			
			for ( int i = 0; i < imageColors.length; i++ ) {
				
				int r = imageColors[i] >> 16&0xFF;  
	        	int g = imageColors[i] >> 8&0xFF;  
	        	int b = imageColors[i] >> 0&0xFF;
	        	
	        	String redHex = Integer.toHexString(r);
	        	String greenHex = Integer.toHexString(g);
	        	String blueHex = Integer.toHexString(b);
	        	
	        	if ( redHex.length() < 2 ) {
	        		
	        		redHex = "0" + redHex;
	        	
	        	}
	        	
	        	if ( greenHex.length() < 2 ) {
	        	
	        		greenHex = "0" + greenHex;
	        	
	        	}
	        	
	        	if ( blueHex.length() < 2 ) {
	        	
	        		blueHex = "0" + blueHex;
	        	
	        	}
	        	
	        	imageColorsHex[i] = ( "#" + redHex + greenHex + blueHex );
			
			}
			
			return imageColorsHex;
		
		}	
		
	}
	
	// Finds the nearest existing cluster given a RGB value.
	private Cluster findNearestCluster(int rgb) {
        
		// Creates an empty cluster which an existing cluster will later be set 
		// to.
		Cluster nearestCluster = null; 
		
		// Start with largest possible distance
		int distanceSmallest = Integer.MAX_VALUE;
        
        // Moves through all of the existing clusters until the closest one is 
		// found.
        for ( int i = 0; i < imageClusters.length; i++ ) {
       
            int distanceToCluster = imageClusters[i].calculateDistance(rgb); 
            
            if (distanceToCluster < distanceSmallest) { 
                
            	distanceSmallest = distanceToCluster; 
                nearestCluster = imageClusters[i]; 
            }
            
        }
        
        return nearestCluster; 
	}
	
	// Creates initial clusters evenly spaced out across the image.
	private Cluster[] createClustersDiagonal(int k) {
        
		Cluster[] newClusterArray = new Cluster[k]; 
        
        int x = 0; 
        int y = 0; 
        
        int dx = imageWidth / k; 
        int dy = imageHeight / k; 
        
        for ( int i = 0; i < k; i++ ) { 
        	
        	newClusterArray[i] = new Cluster( i, imageResource.getRGB(x, y) ); 
            x+=dx; y+=dy; 
        
        } 
         
        return newClusterArray; 
	
	}
	
	// Creates initial clusters with centers selected at random.
	private Cluster[] createClustersRandom(int k) {
		
		Cluster[] newClusterArray = new Cluster[k];
		
		for ( int i = 0; i < k; i++ ) {
			
			newClusterArray[i] = new Cluster( i, imageResource.getRGB(
					randomInt(0, imageWidth), randomInt(0, imageHeight) ) );
		
		}
		
		return newClusterArray;
	}
	
	// Returns a random integer.
	private int randomInt(int min, int max) {
	    
		Random rand = new Random();
	    int randomNum = rand.nextInt( ( max - min ) + 1 ) + min;
	    return randomNum;
	
	}
	
}

