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
		double rnd2 = Math.random();
		x = x1;
		y = y1;
		type = t;
		if (type.equals("grass")) {
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
		else if (type.equals("desert")) {
			if (rnd2 >= 0.97 && rnd2 < 0.98) {
				img = getImage("/imgs/desert/desert2.png");
				asset = 6;
			}
			else if (rnd2 >= 0.98) {
				img = getImage("/imgs/desert/desert3.png");
				asset = 7;
			}
			else {
				img = getImage("/imgs/desert/desert1.png");
				asset = 8;
			}
		}
		else if (type.equals("rock")) {
			if (Math.random() > 0.15) {
				img = getImage("/imgs/rock/rock1.png");
				asset = 9;
			}
			else {
				img = getImage("/imgs/rock/rock2.png");
				asset = 10;
			}
		}
		else {
			img = getImage("/imgs/water.gif");
			asset = 11;
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
		case 6:
			img = getImage("/imgs/desert/desert2.png");
			asset = 6;
			break;
		case 7:
			img = getImage("/imgs/desert/desert3.png");
			asset = 7;
			break;
		case 8:
			img = getImage("/imgs/desert/desert1.png");
			asset = 8;
			break;
		case 9:
			img = getImage("/imgs/rock/rock1.png");
			asset = 9;
			break;
		case 10:
			img = getImage("/imgs/rock/rock2.png");
			asset = 10;
			break;
		case 11:
			img = getImage("/imgs/water.gif");
			asset = 11;
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
	public void paint(Graphics g, int size) {
		Graphics2D g2 = (Graphics2D) g;
		if (x >= -100 && x <= 1100 && y >= -100 && y <= 900) {
			g2.drawImage(img, x, y, size, size, null);
		}
		
	}
}
