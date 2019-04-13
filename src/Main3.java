import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Main3 {

	public static void main(String[] args) {
		try {      
            // Read in original image
            BufferedImage imgOriginal = ImageIO.read( new File("sourceImg5.jpg"));
            
            histograma h = new histograma(5, imgOriginal);
            
            BufferedImage quantImg =  h.joinColors();
            
            // Save quantized version
            ImageIO.write(quantImg, "png", new File("resultJoinColor.png"));
        }
        catch ( IOException e ) {
            System.out.println("Problem reading image.");
            e.printStackTrace();
        }


	}

}
