import java.util.Random;

public class PerlinNoise {
    private double[][] world;
    private double change;
    public PerlinNoise(int size) {
    	world = new double[size+2][size+2];
    	world[0][0] = Math.random();
    	change = 0.1;
    	setWorldValues();
    	for (int i = 0; i < 10; i++) {
    		setAverage(world);
    	}
    	
    }
    public double[][] getArray(){
    	return world;
    }
    public void arrayToString() {
    	for (double[] r : world) {
    		for (double c : r) {
    			System.out.print(c + " ");
    		}
    		System.out.println();
    	}
    }
    private void setAverage(double[][] arr) {
    	for (int i = 1; i < arr.length-1; i++) {
    		for (int j = 1; j < arr[i].length-1; j++) {
    			arr[i][j] = (arr[i][j] + arr[i-1][j] + arr[i][j-1] + arr[i+1][j] + arr[i][j+1])/4;
    		}
    	}
    }
    private void setWorldValues() {
    	for (int i = 0; i < world.length-1; i+=3) {
    		for (int j = 0; j < world[i].length; j++) {
    			if (j == 0 && i == 0) {
    				break;
    			}
    			else if (i == 0) {
    				if (Math.random() > 0.5 && world[i][j-1] < 0.95 || world[i][j-1] < -0.95) {
    					world[i][j] = world[i][j-1] + (Math.random()*change);
    				}
    				else {
    					world[i][j] = world[i][j-1] - (Math.random()*change);
    				}
    				
    			}
    			else if (j == 0){
    				if (Math.random() > 0.5 && world[i-1][j] < 0.95 || world[i-1][j] < -0.95) {
    					world[i][j] = world[i-1][j] + (Math.random()*change);
    				}
    				else {
    					world[i][j] = world[i-1][j] - (Math.random()*change);
    				}
    			}
    			else if (j != world.length-1){
    				if (Math.random() > 0.5 && world[i-1][j] < 0.95 && world[i][j-1] < 0.95 || (world[i-1][j] < -0.95 && world[i][j-1] < -0.95)) {
    					world[i][j] = (world[i-1][j]+world[i][j-1])/2 + (Math.random()*change);
    					world[i+1][j] = (world[i][j]+world[i][j-1]+world[i-1][j])/3;
    					world[i][j+1] = (world[i][j]+world[i][j-1]+world[i-1][j]+world[i+1][j])/4;
    				}
    				else {
    					world[i][j] = (world[i-1][j]+world[i][j-1])/2 - (Math.random()*change);
    					world[i+1][j] = (world[i][j]+world[i][j-1]+world[i-1][j])/3;
    					world[i][j+1] = (world[i][j]+world[i][j-1]+world[i-1][j]+world[i+1][j])/4;
    				}
    			}
    		}
    	}
    }
}