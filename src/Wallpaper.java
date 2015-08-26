import java.awt.image.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Wallpaper {
	
	public Wallpaper() {}
	
	private static BufferedImage getImageResource(String imageLocation) {
		BufferedImage imageResource = null;
		
		try { 
			imageResource = ImageIO.read(new File(imageLocation));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return imageResource;
	}
	
	public static byte[] getColorArray(String imageLocation) {
		BufferedImage imageResource = getImageResource(imageLocation);
		byte[] rgbArray = ((DataBufferByte) imageResource.getRaster().getDataBuffer()).getData();
		
		return rgbArray;
	}
	
}

