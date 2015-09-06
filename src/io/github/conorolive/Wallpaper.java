package io.github.conorolive;

import java.awt.image.*;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import javax.imageio.ImageIO;
import java.util.Random;

public class Wallpaper {
	
	private BufferedImage imageResource;
	private int imageHeight;
	private int imageWidth;
	private Cluster[] imageClusters;
	
	// Wallpaper's constructor
	public Wallpaper(String imageLocation) {
		try { 
			imageResource = ImageIO.read(new File(imageLocation));
			imageHeight = imageResource.getHeight();
			imageWidth = imageResource.getWidth();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// Returns k amount of color clusters in the image
	public void colorSegmentation(int k) {
		// Initializing clusters
		firstPassClusters = createClusters(imageResource, k);
		
		
	}
	
	// Finds the geometric center between given points.
	private Cluster calculateCentroid(int rgb) {
		
	}
	
	// Creates initial clusters evenly spaced out across the image.
	private Cluster[] createClustersDiagonal(BufferedImage imageResource, int k) {
        Cluster[] newClusterArray = new Cluster[k]; 
        
        int x = 0; 
        int y = 0; 
        
        int dx = imageWidth/k; 
        int dy = imageHeight/k; 
        
        for (int i=0;i<k;i++) { 
        	newClusterArray[i] = new Cluster(i,imageResource.getRGB(x, y)); 
            x+=dx; y+=dy; 
        } 
         
        return newClusterArray; 
	}
	
	// Creates initial clusters with centers selected at random.
	private Cluster[] createClustersRandom(BufferedImage imageResource, int k) {
		Cluster[] newClusterArray = new Cluster[k];
		
		for (int i=0;i<k;i++) {
			newClusterArray[i] = new Cluster(i,imageResource.getRGB(randomInt(0, imageWidth), randomInt(0, imageHeight)));
		}
		
		return newClusterArray;
	}
	
	// Returns a random integer.
	private int randomInt(int min, int max) {
	    Random rand = new Random();
	    int randomNum = rand.nextInt((max - min) + 1) + min;
	    return randomNum;
	}
	
}

