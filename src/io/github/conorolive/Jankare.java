package io.github.conorolive;

import java.util.Arrays;
import java.net.MalformedURLException;
import java.net.URL;

public class Jankare {

	public static void main(String[] args) {
		
		Wallpaper test = null;
		
		try {
			test = new Wallpaper(new URL("http://i.imgur.com/mpHgYA0.jpg"));
		} catch (MalformedURLException e){
			e.printStackTrace();
		}
		
		test.colorSegmentation(6);
		
		System.out.println(Arrays.toString(test.getColors()));
		System.out.println("Done!?");
	}
}
