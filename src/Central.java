import java.awt.image.BufferedImage;



public class Central {
	
	public static BufferedImage ReduzirImagem(int nh, BufferedImage original) {
        int w = original.getWidth()/nh;
        int h = original.getHeight()/nh;
        
        
        
        // Create result image
        BufferedImage result = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
        
        // Go through every pixel of the image
        for(int x=0; x< w; x++){
            for(int y=0; y< h; y++){
                // Apply mask to original value and save it in result image
                //result.setRGB(x,y, original.getRGB(x, y));
 
                result.setRGB(x,y, Central.calcularPixelMedio(Central.joinPixel(original,x,y,nh)));
            }
        }
        return result;
    }
	
	private static int calcularPixelMedio(int p[]) {
		int ta = 0, tr = 0, tg = 0, tb = 0;
		for (int i : p) {
			ta = ((i >> 24) & 0xFF) + ta;
			tr =   ((i >> 16) & 0xFF) + tr;
			tg = ((i >>  8) & 0xFF) + tg;
			tb =  ((i      ) & 0xFF) + tb;
			
		}
		int avgA = (ta) / p.length;
		int avgR = (tr) / p.length;
		int avgG = (tg) / p.length;
		int avgB = (tb) / p.length;
		//System.out.println(p.length);
		return (avgA<<24) | (avgR<<16) | (avgG<<8) | avgB;
	}
	
	private static int[] joinPixel(BufferedImage original, int w, int h, int p){
		int vet[] = new int[p*p];
		int z = 0;
		for(int i = (w*p) ; i <= ((w*p)+(p-1)); i++) {
			for(int i2 = (h*p) ; i2 <= ((h*p)+(p-1)); i2++) {
				vet[z] = original.getRGB(i, i2);
				z++;
			}	
		}
		System.out.println(vet.length);
		return vet;	
	}

}
