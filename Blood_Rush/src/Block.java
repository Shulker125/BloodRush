import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;

public class Block {
	private String type;
	private Image img;
	public Block (String t) {
		type = t;
		if (type.equals("grass")) {
			img = getImage("grass.png");
		}
		else if (type.equals("snow")){
			img = getImage("snow.png");
		}
		else {
			img = getImage("desert.png");
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
}
