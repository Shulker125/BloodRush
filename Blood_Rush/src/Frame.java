import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.AffineTransform;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.util.Random;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame; 
import javax.swing.JPanel;
import javax.swing.Timer;
//access key: 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

public class Frame extends JPanel implements ActionListener, MouseListener, KeyListener {
	World w = new World(500);
	Character c = new Character();
	Block currentLocation = w.getLocation();
	public boolean up, down, left, right, opp;
	public void paint(Graphics g) {
		barrier();
		obstacle();
		currentLocation = w.getLocation();
		Graphics2D g2 = (Graphics2D) g;
		move();
		g.setColor(Color.black);
		g.fillRect(0, 0, 1000, 800);
		//currentLocation.paint(g, 70);
		w.paint(g, 0);
		g2.drawImage(c.getImage(), 460, 360, 80, 80, null);
		//g.drawRect(480, 360, 37, 80); //player hit box
		/*g.setColor(new Color(0, 0, 0, 100));  //night mode 
		g.fillRect(0, 0, 1000, 800);*/
	}
	public static void main(String[] arg) {
		Frame f = new Frame();
	}
	public Frame() {
		JFrame f = new JFrame("Blood Rush");
		f.setSize(new Dimension(1000, 800));
		f.setBackground(Color.black);
		f.add(this);
		f.setResizable(false);
		f.setLayout(new GridLayout(1,2));
		f.addMouseListener(this);
		f.addKeyListener(this);
		Timer t = new Timer(16, this);
		t.start();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
	}
	private Image getImage(String path) {
		Image tempImage = null;
		try {
			URL imageURL = Frame.class.getResource(path);
			tempImage = Toolkit.getDefaultToolkit().getImage(imageURL);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tempImage;
	}
	
	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
		
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		repaint();
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		int key = arg0.getKeyCode();
		//System.out.println(key);
		
		switch(key) {
		case 87: //w
			up = true;
			break;
		case 68: //a
			left = true;
			break;
		case 83: //s
			down = true;
			break;
		case 65: //d
			right = true;
			break;
		case 32: //space
			saveWorld();
			break;
		case 76: //L
			loadWorld();
			break;
		}
	}
	public void barrier() {
		if (w.getBound("top") >= 360) {
			up = false;
		}
		if (w.getBound("left") >= 480) {
			right = false;
		}
		if (w.getBound("bottom") <= 445) {
			down = false;
		}
		if(w.getBound("right") <= 520) {
			left = false;
		}
	}
	public void obstacle() {
		if (currentLocation.isObstructed) {
			if (currentLocation.y >= 350 && currentLocation.y <= 357 && currentLocation.x >= 450 && currentLocation.x <= 490) {
				up = false;
			}
			if (currentLocation.x >= 480) {
				left = false;
			}
			if (currentLocation.y <= 410 && currentLocation.y >= 406 && currentLocation.x >= 450 && currentLocation.x <= 490) {
				down = false;
			}
			if(currentLocation.x <= 480) {
				right = false;
			}
		}
		else if(currentLocation.getAsset() == 11) {
			w.setSpeed(4);
		}
		else {
			w.setSpeed(7);
		}
	}
	public void move() {
		if (up) {
			w.moveUp();
			c.up();
		}
		if (down) {
			w.moveDown();
			c.down();
		}
		if (left) {
			w.moveLeft();
			c.right();
		}
		if (right) {
			w.moveRight();
			c.left();
		}
	}
	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		int key = arg0.getKeyCode();
		//System.out.println(key);
		switch(key) {
			case 87: //w
				up = false;
				c.reset(0);
				break;
			case 68: //a
				left = false;
				c.reset(1);
				break;
			case 83: //s
				down = false;
				c.reset(2);
				break;
			case 65: //d
				right = false;
				c.reset(3);
				break;
		}
		
		
	}
	public void saveWorld() {
		StringBuilder builder = new StringBuilder();
		int[][] world = w.getArray();
		for(int i = 0; i < world.length; i++)//for each row
		{
		   for(int j = 0; j < world[i].length; j++)//for each column
		   {
		      builder.append(world[i][j]+"");//append to the output string
		      if(j < world.length - 1)//if this is not the last row element
		         builder.append(",");//then add comma (if you don't like commas you can use spaces)
		   }
		   builder.append("\n");//append new line at the end of the row
		}
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter("world.txt"));
			writer.write(builder.toString());
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//save the string representation of the board
		
	}
	public void loadWorld() {
		String savedGameFile = "world.txt";
		int[][] board = new int[w.getArray().length][w.getArray().length];
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(savedGameFile));
			String line = "";
			int row = 0;
			while((line = reader.readLine()) != null)
			{
			   String[] cols = line.split(","); //note that if you have used space as separator you have to split on " "
			   int col = 0;
			   for(String  c : cols)
			   {
			      board[row][col] = Integer.parseInt(c);
			      col++;
			   }
			   row++;
			}
			reader.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		w.setWorld(board);
		
	}
	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
