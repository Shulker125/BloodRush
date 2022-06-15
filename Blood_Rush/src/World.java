import java.awt.Graphics;

public class World {
	private Chunk[][] world;
	private Chunk chunk;
	public World() {
		world = new Chunk[10][10];
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				world[i][j] = new Chunk("grass", 0+(i*200), 0+(j*200));
			}
		}
	}
	public void paint(Graphics g) {
		for (Chunk[] r : world) {
			for (Chunk c : r) {
				c.paint(g);
			}
		}
	}
	public void moveUp() {
		for (Chunk[] r : world) {
			for (Chunk c : r) {
				c.moveUp();
			}
		}
	}
	public void moveDown() {
		for (Chunk[] r : world) {
			for (Chunk c : r) {
				c.moveDown();
			}
		}
	}
	public void moveLeft() {
		for (Chunk[] r : world) {
			for (Chunk c : r) {
				c.moveLeft();
			}
		}
	}
	public void moveRight() {
		for (Chunk[] r : world) {
			for (Chunk c : r) {
				c.moveRight();
			}
		}
	}
}
