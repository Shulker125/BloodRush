import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;

public class Block {
	private String type;
	public int x, y;
	private Image img;
	public Block (String t, int x1, int y1) {
		x = x1;
		y = y1;
		type = t;
		double rnd = Math.random();
		if (rnd >= 0 && rnd <= 0.2) {
			img = getImage("/imgs/testBlock-red.png");
		}
		else if (rnd >= 0.21 && rnd <= 0.4) {
			img = getImage("/imgs/testBlock-green.png");
		}
		else if (rnd >= 0.41 && rnd <= 0.6) {
			img = getImage("/imgs/testBlock-blue.png");
		}
		else if (rnd >= 0.61 && rnd <= 0.8) {
			img = getImage("/imgs/testBlock-yellow.png");
		}
		else {
			img = getImage("/imgs/testBlock-orange.png");
		}
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
		g2.drawImage(img, x, y, 40, 40, null);
	}
}
