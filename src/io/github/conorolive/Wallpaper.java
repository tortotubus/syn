package io.github.conorolive;

import java.awt.image.*;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import javax.imageio.ImageIO;

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

	// Returns k clusters of colors in the image.
	public void colorSegmentation(int k, int iterationCount) {
		
		// Creating new Clusters
		imageClusters = createClusters(imageResource, k);
		
		// Cluster table
		int[] clusterTable = new int[imageWidth*imageHeight];
		Arrays.fill(clusterTable, -1);
		
		for (int y=0;y<imageHeight;y++) {
			for (int x=0;x<imageWidth;x++) {
				int rgbValue = imageResource.getRGB(x, y);
				Cluster rgbCluster = calculateCentroid(rgbValue);
				
				if (clusterTable[imageWidth*imageHeight+x] != rgbCluster.getID()) {
					if (clusterTable[imageWidth*imageHeight+x] != -1) {
						// Clearing out pixels from a previous cluster, if cluster is being overwritten.
						imageClusters[clusterTable[imageWidth*imageHeight+x]].removePixel(rgbValue);
					}
					
					// Adds the pixel to the cluster.
					rgbCluster.addPixel(rgbValue);	
				}
				
				// Updating clusterTable.
				clusterTable[imageWidth*imageHeight+x] = rgbCluster.getID();
			}
		}	
	}
	
	// Finds the geometric center between given points.
	private Cluster calculateCentroid(int rgb) {
		Cluster colorCluster = null;
		
		int minimum = Integer.MAX_VALUE;
		for (int i=0;i<imageClusters.length;i++) {
			int distance = imageClusters[i].calculateDistance(rgb);
			
			if (distance > minimum) {
				minimum = distance;
				colorCluster = imageClusters[i];
			}
		}
		
		return colorCluster;
	}
	
	// Creates clusters.
	private Cluster[] createClusters(BufferedImage image, int k) {
		Cluster[] newClusters = new Cluster[k];
		int x = 0;
		int y = 0;
		
		int dx = imageWidth/k;
		int dy = imageHeight/k;
		
		for (int i=0;i<k;i++) {
			newClusters[i] = new Cluster(i, image.getRGB(x, y));
			x += dx;
			y += dy;
		}
		
		return newClusters;
	}
	
	
}

