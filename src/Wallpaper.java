import java.awt.image.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Wallpaper {
	
	private BufferedImage imageResource;
	
	// Wallpaper's constructor
	public Wallpaper(String imageLocation) {
		try { 
			imageResource = ImageIO.read(new File(imageLocation));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// Gets the RGB values from the BufferedImage and puts them in an array.
	public byte[] getColorArray() {
		byte[] rgbArray = ((DataBufferByte) imageResource.getRaster().getDataBuffer()).getData();
		return rgbArray;
	}
	
	// Calculates the Euclidean distance between two points in three-dimensional RBG space.
	private double euclideanDistance() {}
	
}

