import java.awt.Color;
import java.awt.Graphics;

public class World{
	private Block[][] world;
	private int blockSize, startLocation, worldSize;
	public World() {
		blockSize = 70;
		world = new Block[10][10];
		for (int i = 0; i < world.length; i++) {
			for (int j = 0; j < world[i].length; j++) {
				if (i == 5 && j == 5) {
					world[i][j] = new Block(1, 0+(i*blockSize), 0+(j*blockSize));
				}
				else if(i == 6 && j == 5) {
					world[i][j] = new Block(1, 0+(i*blockSize), 0+(j*blockSize));
				}
				else {
					world[i][j] = new Block(1, 0+(i*blockSize), 0+(j*blockSize));
				}
			}
		}
	}
	public World(int size) {
		startLocation = -17500;
		blockSize = 70;
		worldSize = size;
	}
	public int getBound(String location) {
		switch(location) {
			case "top":
				return world[0][0].y;
			case "bottom":
				return world[0][world.length-1].y+70;
			case "left":
				return world[0][0].x;
			case "right":
				return world[world.length-1][0].x+70;
		}
		return 0;
		
	}
	public void loadNewWorld() {
		PerlinNoise p = new PerlinNoise(worldSize);
		double[][] arr = p.getArray();
		world = new Block[worldSize][worldSize];
		for (int i = 0; i < world.length; i++) {
			for (int j = 0; j < world[0].length; j++) {
				if (arr[i+1][j+1] > 7000000) {
					world[i][j] = new Block("rock", startLocation+(i*blockSize), startLocation+(j*blockSize));
				}
				else if (arr[i+1][j+1] >= 4000000 && arr[i+1][j+1] <= 7000000) {
					world[i][j] = new Block("water", startLocation+(i*blockSize), startLocation+(j*blockSize));
				}
				else if (arr[i+1][j+1] >= -7000000 && arr[i+1][j+1] < 4000000) {
					world[i][j] = new Block("grass", startLocation+(i*blockSize), startLocation+(j*blockSize));
				}
				else {
					world[i][j] = new Block("desert", startLocation+(i*blockSize), startLocation+(j*blockSize));
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
	public Block getLocation() {
		for (Block[] r : world) {
			for (Block c : r) {
				if (c.x >= 430 && c.x <= 500 && c.y >= 350 && c.y <= 420) {
					return c;
				}
			}
		}
		return null;
	}
	public void setWorld(int[][] arr) {
		world = new Block[worldSize][worldSize];
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[i].length; j++) {
				if (arr[i][j] >= 1 && arr[i][j] <= 5) {
					world[i][j] = new Block("grass", startLocation+(i*blockSize), startLocation+(j*blockSize), arr[i][j]);
				}
				else if (arr[i][j] >= 6 && arr[i][j] <= 8) {
					world[i][j] = new Block("desert", startLocation+(i*blockSize), startLocation+(j*blockSize), arr[i][j]);
				}
				else {
					world[i][j] = new Block("rock", startLocation+(i*blockSize), startLocation+(j*blockSize), arr[i][j]);
				}
			}
		}
	}
	public void paint(Graphics g) {
		for (Block[] r : world) {
			for (Block c : r) {
				c.paint(g, blockSize);
			}
		}
	}
	public void moveUp(int speed) {
		for (Block[] r : world) {
			for (Block c : r) {
				c.y += speed;
			}
		}
	}
	public void moveDown(int speed) {
		for (Block[] r : world) {
			for (Block c : r) {
				c.y -= speed;
			}
		}
	}
	public void moveLeft(int speed) {
		for (Block[] r : world) {
			for (Block c : r) {
				c.x -= speed;
			}
		}
	}
	public void moveRight(int speed) {
		for (Block[] r : world) {
			for (Block c : r) {
				c.x += speed;
			}
		}
	}
}
