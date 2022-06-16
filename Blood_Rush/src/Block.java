import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;
import java.util.Random;

public class Block{
	private String type;
	public int x, y, asset;
	private Image img;
	public Block (String t, int x1, int y1) {
		Random random = new Random();
		int rnd = random.nextInt(5)+1;
		x = x1;
		y = y1;
		type = t;
		switch(rnd) {
			case 1:
				img = getImage("/imgs/grass/grass1.png");
				asset = 1;
				break;
			case 2:
				img = getImage("/imgs/grass/grass2.png");
				asset = 2;
				break;
			case 3:
				img = getImage("/imgs/grass/grass3.png");
				asset = 3;
				break;
			case 4:
				img = getImage("/imgs/grass/grass4.png");
				asset = 4;
				break;
			case 5:
				img = getImage("/imgs/grass/grass5.png");
				asset = 5;
				break;
		}
	}
	public Block(String t, int x1, int y1, int blockType) {
		x = x1;
		y = y1;
		type = t;
		switch(blockType) {
		case 1:
			img = getImage("/imgs/grass/grass1.png");
			asset = 1;
			break;
		case 2:
			img = getImage("/imgs/grass/grass2.png");
			asset = 2;
			break;
		case 3:
			img = getImage("/imgs/grass/grass3.png");
			asset = 3;
			break;
		case 4:
			img = getImage("/imgs/grass/grass4.png");
			asset = 4;
			break;
		case 5:
			img = getImage("/imgs/grass/grass5.png");
			asset = 5;
			break;
		}
	}
	public int getAsset() {
		return asset;
	}
	private Image getImage(String path) {
		Image tempImage = null;
		try {
			URL imageURL = Block.class.getResource(path);
			tempImage = Toolkit.getDefaultToolkit().getImage(imageURL);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tempImage;
	}
	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		if (x >= -100 && x <= 1100 && y >= -100 && y <= 900) {
			g2.drawImage(img, x, y, 40, 40, null);
		}
		
	}
}
