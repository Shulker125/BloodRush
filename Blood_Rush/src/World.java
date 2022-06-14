
public class World {
	private Chunk[][] world;
	public World() {
		world = new Chunk[100][100];
		for (int i = 0; i < 100; i++) {
			for (int j = 0; j < 100; j++) {
				world[i][j] = new Chunk("grass");
			}
		}
	}
}
