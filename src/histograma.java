import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

class ItemHistograma{
	public int color;
	public int qtd;
	
	public ItemHistograma(int color) {
		this.color = color;
		this.qtd = 1;
	}
	
	public void addQtd() {
		this.qtd++;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + color;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ItemHistograma other = (ItemHistograma) obj;
		if (color != other.color)
			return false;
		return true;
	}
}

public class histograma {
	private int np;
	private int[] histograma;
	private List<ItemHistograma> histograma2;
	private BufferedImage original;
	
	public histograma(int nivelPrecisao, BufferedImage original) {
		this.np = nivelPrecisao;
		//this.histograma = new int[(int) Math.pow(np, 3)];
		this.histograma = new int[555];
		this.histograma2 = new ArrayList<ItemHistograma>();
		this.original = original;
	}
	
	private void gerarHistograma(int startX, int startY, int width, int height) {
		for(int x=startX; x< width; x++){
            for(int y=startY; y< height; y++){
                int p = this.original.getRGB(x, y);
                int color = this.getColor(x, y, p);
                this.histograma[color] = this.histograma[color] + 1; 
            }
        }
	}
	
	public BufferedImage joinColors() {
		this.gerarHistograma2(0, 0, this.original.getWidth(), this.original.getHeight());
		int x = 0, y = 0;
		BufferedImage result = new BufferedImage(this.original.getWidth(), this.original.getHeight(), BufferedImage.TYPE_INT_RGB);
		
		for (ItemHistograma ih : this.histograma2) {
			int i = ih.qtd;
			System.out.println(ih.color + " -> " + ih.qtd);
			while(x < this.original.getWidth() && i > 0) {
				while(y < this.original.getHeight() && i > 0) {
					result.setRGB(x, y, ih.color);
					
					i--;
					y++;
				}
				if(y == this.original.getHeight())
					y=0;
				x++;
			}
			
		}
		
		return result;
	}
	
	public void printHistograma(int x) {
		this.gerarHistograma(0, 0, this.original.getWidth(), this.original.getHeight());
		for (int i = 0; i < this.histograma.length; i++) {
			System.out.println(i + " -> " + this.histograma[i]);
		}
	}
	
	private int getColor(int x, int y, int pixel) {
		int p = pixel;
		int r = 0, g = 0, b = 0;
		
		int m = (255/this.np);
		
		r =   ((p >> 16) & 0xFF) / m;
		g = ((p >>  8) & 0xFF) / m;
		b =  ((p      ) & 0xFF) / m;
		
		System.out.println(String.valueOf(r) + " " +  String.valueOf(g) + " " + String.valueOf(b));
		
		int finalInt = Integer.parseInt(String.valueOf(r) + String.valueOf(g) + String.valueOf(b));
		return finalInt;	
	}
	
	private void gerarHistograma2(int startX, int startY, int width, int height) {
		for(int x=startX; x< width; x++){
            for(int y=startY; y< height; y++){
                int color = this.simplifyColor(this.original.getRGB(x, y));
                //int color = this.original.getRGB(x, y);
                ItemHistograma ih = this.getItemHistogramaByColor(color);
                if(ih == null) {
                	this.histograma2.add(new ItemHistograma(color));
                }
                else {
                	ih.addQtd();
                }
            }
            System.out.println(x );
        }
	}
	
	private int simplifyColor(int color) {
		int a = 0, r = 0, g = 0, b = 0;
		
		int m = (255/this.np);
		a = ((color >> 24) & 0xFF) / m;
		r =   ((color >> 16) & 0xFF) / m;
		g = ((color >>  8) & 0xFF) / m;
		b =  ((color      ) & 0xFF) / m;
		
		//int finalInt = Integer.parseInt(String.valueOf(r) + String.valueOf(g) + String.valueOf(b));
		int finalInt = (a<<24)*m | (r<<16)*m | (g<<8)*m | b*m;
		return finalInt;	
	}
	
	 public ItemHistograma getItemHistogramaByColor(int color){
		 for(ItemHistograma ih: this.histograma2) {
			 if(ih.color == color)
				 return ih;
		 }
		 return null;
	 }

}
