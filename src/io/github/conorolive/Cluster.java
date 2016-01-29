package io.github.conorolive;

public class Cluster {
	
	private int id;
	private int pixelCount;
	
	// Holds the current centroid.
	private int red;
	private int green;
	private int blue;
	
	// Holds all colors in cluster.
	private int reds;
	private int greens;
	private int blues;
	
	// Cluster constructor class
	public Cluster(int id, int rgb) {
		
		int r = rgb >> 16&0xFF;  
        int g = rgb >> 8&0xFF;  
        int b = rgb >> 0&0xFF;  
        
        red = r; 
        green = g; 
        blue = b; 
        
        this.id = id;
        
        addPixel(rgb); 
        
	}
	
	// Clears cluster variables.
	public void clearCluster() {
		
		red = green = blue = 0;
		reds = greens = blues = 0;
		pixelCount = 0;
		
	}
	
	// Getter method for id.
	public int getID() {
		
		return id;
		
	}
	
	// Gets current centroid.
	public int getRGB() {
		
		int r = reds / pixelCount;
		int g = greens / pixelCount;
		int b = blues / pixelCount;
		
		return 0xff000000 | r << 16 | g << 8 | b;
		
	}
	
	// Adding a RGB value to the cluster.
	public void addPixel(int color) {
		
		int r = color >> 16&0xFF;
		int g = color >> 8&0xFF;
		int b = color >> 0&0xFF;
		
		reds += r;
		greens += g;
		blues += b;
		
		pixelCount++;
		
		red = reds / pixelCount;
		green = greens / pixelCount;
		blue = blues / pixelCount;
		
	}
	
	// Removing a RGB value from the cluster.
	public void removePixel(int color) {
		
		int r = color >> 16&0xFF;
		int g = color >> 8&0xFF;
		int b = color >> 0&0xFF;
		
		reds -= r;
		greens -= g;
		blues -= b;
		
		pixelCount--;
		
		red = reds / pixelCount;
		green = greens / pixelCount;
		blue = blues / pixelCount;
		
	}
	
	// Calculates Euclidean distance between given pixel and centroid.
	public int calculateDistance(int color) {
		
		int r = color >> 16&0xFF;
		int g = color >> 8&0xFF;
		int b = color >> 0&0xFF;
		
		int rx = Math.abs( red - r );
		int gx = Math.abs( green - g );
		int bx = Math.abs( blue - b );
		
		int distance = ( rx + gx + bx ) / 3;
		
		return distance;
		
	}
}
 