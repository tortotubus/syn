package io.github.conorolive;

import java.util.Arrays;
import java.net.MalformedURLException;
import java.net.URL;

public class Jankare {

	public static void main(String[] args) {
		
		ImageSegments test = null;
		
		try {
			test = new ImageSegments(new URL("http://stock-wallpapers.com/wp-content/uploads/2014/05/G3_wallpaper_06.png"));
		} catch (MalformedURLException e){
			e.printStackTrace();
		}
		
		test.colorSegmentation(6);
		
		System.out.println(Arrays.toString(test.getColorsAsHex()));
		System.out.println("Done!?");
	}
}
