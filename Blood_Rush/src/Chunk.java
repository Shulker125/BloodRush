import java.awt.Graphics;

public class Chunk {
	private String biome;
	public int x, y;
	private Block[][] chunk;
	public Chunk(String b, int x1, int y1) {
		x = x1;
		y = y1;
		biome = b;
		chunk = new Block[5][5];
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				chunk[i][j] = new Block(biome, x+(i*40), y+(j*40));
			}
		}
	}
	public void paint(Graphics g) {
		for (Block[] r : chunk) {
			for (Block c : r) {
				c.paint(g);
			}
		}
	}
	public void moveUp() {
		for (Block[] r : chunk) {
			for (Block c : r) {
				c.y += 5;
			}
		}
	}
	public void moveDown() {
		for (Block[] r : chunk) {
			for (Block c : r) {
				c.y -= 5;
			}
		}
	}
	public void moveLeft() {
		for (Block[] r : chunk) {
			for (Block c : r) {
				c.x -= 5;
			}
		}
	}
	public void moveRight() {
		for (Block[] r : chunk) {
			for (Block c : r) {
				c.x += 5;
			}
		}
	}
}
