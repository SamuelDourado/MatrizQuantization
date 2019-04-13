import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Main2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {      
            // Read in original image
            BufferedImage imgOriginal = ImageIO.read( new File("sourceImg4.jpg"));
            
            histograma h = new histograma(5, imgOriginal);
            
            h.printHistograma(10);
        }
        catch ( IOException e ) {
            System.out.println("Problem reading image.");
            e.printStackTrace();
        }


	}

}
