import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Main {

	public static void main(String[] args) {
        try {      
            // Read in original image
            BufferedImage imgOriginal = ImageIO.read( new File("sourceImg.jpg"));
            
            // Process image and obtain result
            BufferedImage quantImg =  Central.ReduzirImagem(3, imgOriginal);
            
            // Save quantized version
            ImageIO.write(quantImg, "png", new File("resultImg.png"));
        }
        catch ( IOException e ) {
            System.out.println("Problem reading image.");
            e.printStackTrace();
        }

	}
	
	

}
