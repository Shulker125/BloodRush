import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;
import java.util.Random;

public class Block{
	private String type;
	public int x, y, asset, health;
	public Image img;
	public boolean isObstructed;
	public Block(int type, int x1, int y1) {
		x = x1;
		y = y1;
		if (type == 1) {
			img = getImage("/imgs/grass/grass1.png");
			asset = 1;
			isObstructed = false;
		}
		else {
			img = getImage("/imgs/grass/grass2.png");
			asset = 2;
			isObstructed = true;
		}
	}
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
				isObstructed = false;
				break;
			case 2:
				img = getImage("/imgs/grass/grass2.png"); //tree
				asset = 2;
				isObstructed = true;
				health = 100;
				break;
			case 3:
				img = getImage("/imgs/grass/grass3.png");
				asset = 3;
				isObstructed = false;
				break;
			case 4:
				img = getImage("/imgs/grass/grass4.png");
				asset = 4;
				isObstructed = false;
				break;
			case 5:
				img = getImage("/imgs/grass/grass5.png");
				asset = 5;
				isObstructed = false;
				break;
			}
		}
		else if (type.equals("desert")) {
			if (rnd2 >= 0.97 && rnd2 < 0.98) {
				img = getImage("/imgs/desert/desert2.png"); //cactus
				asset = 6;
				isObstructed = true;
				health = 100;
			}
			else if (rnd2 >= 0.98) {
				img = getImage("/imgs/desert/desert3.png"); //small stone
				asset = 7;
				isObstructed = true;
				health = 200;
			}
			else {
				img = getImage("/imgs/desert/desert1.png");
				asset = 8;
				isObstructed = false;
			}
		}
		else if (type.equals("rock")) {
			if (Math.random() > 0.15) {
				img = getImage("/imgs/rock/rock1.png");
				asset = 9;
				isObstructed = false;
			}
			else {
				img = getImage("/imgs/rock/rock2.png"); //rock
				asset = 10;
				isObstructed = true;
				health = 300;
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
	public Block(int blockType) {
		switch(blockType) {
			case 12:
				img = getImage("/imgs/pod/podBlack.png");
				asset = 12;
				break;
			case 13:
				img = getImage("/imgs/pod/podFloor.png");
				asset = 13;
				break;
			case 14:
				img = getImage("/imgs/pod/podWall.png");
				asset = 14;
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
	public void paintPod(Graphics g, int x, int y) {
		Graphics2D g2 = (Graphics2D) g;
		this.x = x;
		this.y = y;
		g2.drawImage(img, x, y, 70, 70, null);
	}
}
