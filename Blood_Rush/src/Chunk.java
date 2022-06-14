
public class Chunk {
	private String biome;
	private Block[][] chunk;
	public Chunk(String b) {
		biome = b;
		chunk = new Block[5][5];
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				chunk[i][j] = new Block(biome);
			}
		}
	}
}
