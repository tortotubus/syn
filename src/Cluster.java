public class Cluster {
	
	int id;
	int pixelCount;
	
	int red;
	int green;
	int blue;
	
	int reds;
	int greens;
	int blues;
	
	// Cluster constructor class
	public Cluster(int id, int rgb) {
		int r = rgb>>16&0x000000FF;  
        int g = rgb>> 8&0x000000FF;  
        int b = rgb>> 0&0x000000FF;  
        
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
	int getID() {
		return id;
	}
	
	// Getter method for RGB values.
	int getRGB() {
		int r = reds / pixelCount;
		int g = greens / pixelCount;
		int b = blues / pixelCount;
		return 0xff000000|r<<16|g<<8|b;
	}
	
	// Adding a RGB value to the cluster.
	void addPixel(int color) {
		int r = color>>16&0x000000FF;
		int g = color>> 8&0x000000FF;
		int b = color>> 0&0x000000FF;
		
		reds += r;
		greens += g;
		blues += b;
		
		pixelCount++;
		
		red = reds/pixelCount;
		green = greens/pixelCount;
		blue = blues/pixelCount;
	}
	
	// Removing a RGB value to the cluster.
	void removePixel(int color) {
		int r = color>>16&0x000000FF;
		int g = color>> 8&0x000000FF;
		int b = color>> 0&0x000000FF;
		
		reds -= r;
		greens -= g;
		blues -= b;
		
		pixelCount--;
		
		red = reds/pixelCount;
		green = greens/pixelCount;
		blue = blues/pixelCount;
	}
	
	int calculateDistance(int color) {
		int r = color>>16&0x000000FF;
		int g = color>> 8&0x000000FF;
		int b = color>> 0&0x000000FF;
		
		int rx = Math.abs(red-r);
		int gx = Math.abs(green-g);
		int bx = Math.abs(blue-b);
		
		int distance = (rx+gx+bx) / 3;
		
		return distance;
	}
}
 