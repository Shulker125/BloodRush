import java.awt.Graphics;

public class World{
	private Block[][] world;
	public World() {
		world = new Block[100][100];
		for (int i = 0; i < world.length; i++) {
			for (int j = 0; j < world[0].length; j++) {
				world[i][j] = new Block("grass", 0+(i*40), 0+(j*40));
			}
		}
	}
	public int[][] getArray() {
		int[][] arr = new int[world.length][world[0].length];
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[i].length; j++) {
				arr[i][j] = world[i][j].getAsset();
			}
		}
		return arr;
	}
	public void setWorld(int[][] arr) {
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[i].length; j++) {
				world[i][j] = new Block("grass", 0+(i*40), 0+(j*40), arr[i][j]);
			}
		}
	}
	public void paint(Graphics g) {
		for (Block[] r : world) {
			for (Block c : r) {
				c.paint(g);
			}
		}
	}
	public void moveUp() {
		for (Block[] r : world) {
			for (Block c : r) {
				c.y += 5;
			}
		}
	}
	public void moveDown() {
		for (Block[] r : world) {
			for (Block c : r) {
				c.y -= 5;
			}
		}
	}
	public void moveLeft() {
		for (Block[] r : world) {
			for (Block c : r) {
				c.x -= 5;
			}
		}
	}
	public void moveRight() {
		for (Block[] r : world) {
			for (Block c : r) {
				c.x += 5;
			}
		}
	}
}
