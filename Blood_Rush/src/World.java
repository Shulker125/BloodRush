import java.awt.Graphics;

public class World{
	private Block[][] world;
	private int blockSize;
	private int speed;
	public World(int size) {
		blockSize = 70;
		speed = 7;
		PerlinNoise p = new PerlinNoise(size);
		double[][] arr = p.getArray();
		world = new Block[size][size];
		for (int i = 0; i < world.length; i++) {
			for (int j = 0; j < world[0].length; j++) {
				if (arr[i+1][j+1] > 7000000) {
					world[i][j] = new Block("rock", 0+(i*blockSize), 0+(j*blockSize));
				}
				else if (arr[i+1][j+1] >= 4000000 && arr[i+1][j+1] <= 7000000) {
					world[i][j] = new Block("water", 0+(i*blockSize), 0+(j*blockSize));
				}
				else if (arr[i+1][j+1] >= -7000000 && arr[i+1][j+1] < 4000000) {
					world[i][j] = new Block("grass", 0+(i*blockSize), 0+(j*blockSize));
				}
				else {
					world[i][j] = new Block("desert", 0+(i*blockSize), 0+(j*blockSize));
				}
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
				if (arr[i][j] >= 1 && arr[i][j] <= 5) {
					world[i][j] = new Block("grass", 0+(i*blockSize), 0+(j*blockSize), arr[i][j]);
				}
				else if (arr[i][j] >= 6 && arr[i][j] <= 8) {
					world[i][j] = new Block("desert", 0+(i*blockSize), 0+(j*blockSize), arr[i][j]);
				}
				else {
					world[i][j] = new Block("rock", 0+(i*blockSize), 0+(j*blockSize), arr[i][j]);
				}
			}
		}
	}
	public void paint(Graphics g, int value) {
		for (Block[] r : world) {
			for (Block c : r) {
				c.paint(g, blockSize);
			}
		}
	}
	public void moveUp() {
		for (Block[] r : world) {
			for (Block c : r) {
				c.y += speed;
			}
		}
	}
	public void moveDown() {
		for (Block[] r : world) {
			for (Block c : r) {
				c.y -= speed;
			}
		}
	}
	public void moveLeft() {
		for (Block[] r : world) {
			for (Block c : r) {
				c.x -= speed;
			}
		}
	}
	public void moveRight() {
		for (Block[] r : world) {
			for (Block c : r) {
				c.x += speed;
			}
		}
	}
}
