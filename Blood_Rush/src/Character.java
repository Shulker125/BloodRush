import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;

public class Character {
	private Image img;
	public Character() {
		img = getImage("imgs/char.png");
	}
	public Image getImage() {
		return img;
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
	public void down() {
		img = getImage("/imgs/charFrontMoving.gif");
	}
	public void up() {
		img = getImage("/imgs/charBackMoving.gif");
	}
	public void right() {
		img = getImage("/imgs/walkRight.gif");
	}
	public void left() {
		img = getImage("/imgs/walkLeft.gif");
	}
	public void reset(int position) {
		switch(position) {
			case 0:
				img = getImage("/imgs/charBack.png");
				break;
			case 1:
				img = getImage("/imgs/charRight.png");
				break;
			case 2:
				img = getImage("/imgs/char.png");
				break;
			case 3:
				img = getImage("/imgs/charLeft.png");
				break;
		}
	}
	
}
