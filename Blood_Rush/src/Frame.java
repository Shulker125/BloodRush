import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.MouseInfo;
import java.awt.Point;
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
import javax.swing.SwingUtilities;
import javax.swing.Timer;
//access key: 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

public class Frame extends JPanel implements ActionListener, MouseListener, KeyListener {
	World w = new World(500);
	JFrame f = new JFrame("Blood Rush");
	Character c = new Character();
	Weapon weapon = new Weapon("pick");
	Block currentLocation;
	Block[][] podInterior = getPod();
	Font[] fonts;
	Color newWorldColor = new Color(255, 255, 255);
	Color loadWorldColor = new Color(255, 255, 255);
	Image pod = getImage("/imgs/pod.png");
	Point p = MouseInfo.getPointerInfo().getLocation();
	Cursor cursor;
	public int podX = 200, podY = 200, speed = 7, interiorX = 180, interiorY = -180, locationx = 0, locationy = 0, cursorX, cursorY, iterate = 0, loadType = 0;
	public boolean up, down, left, right, isInPod, isOnHomescreen = true, loading = false;
	public void paint(Graphics g) {
		pointerSet();
		cursorHover(g);
		//System.out.println(cursorX + "," + cursorY);
		g.setColor(Color.black);
		g.fillRect(0, 0, 1000, 800);
		if (isOnHomescreen) {
			g.setFont(new Font("Comic Sans MS", Font.PLAIN, 50));
			g.setColor(Color.white);
			g.drawString("Title Placeholder", 300, 200);
			g.setColor(newWorldColor);
			g.drawString("New World", 350, 400);
			try {
				if (isFileEmpty()) {
					loadWorldColor = new Color(150, 150, 150);
				}
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			g.setColor(loadWorldColor);
			g.drawString("Load World", 350, 500);
			if (loading) {
				g.setColor(Color.black);
				g.fillRect(0, 0, 1000, 800);
				g.setColor(Color.white);
				g.drawString("Loading...", 400, 400);
				loading = false;
				isOnHomescreen = false;
			}
		}
		else {
			if (loadType == 1 && iterate == 0) {
				w.loadNewWorld();
			}
			else if (loadType == 2 && iterate == 0) {
				loadWorld();
			}
			iterate++;
			currentLocation = w.getLocation();
			if (!isInPod) {
				barrier();
				obstacle();
				podObstacle();
			}
			currentLocation = w.getLocation();
			Graphics2D g2 = (Graphics2D) g;
			move();
			//currentLocation.paint(g, 70);
			w.paint(g);
			g2.drawImage(pod, podX, podY, 300, 300, null);
			if (isInPod) {
				speed = 7;
				g.setColor(Color.black);
				g.fillRect(0, 0, 1000, 800);
				for (int i = 0; i < podInterior.length; i++) {
					for (int j = 0; j < podInterior.length; j++) {
						podInterior[i][j].paintPod(g, interiorX+(i*70), interiorY+(j*70));
					}
				}
			}
			g2.drawImage(c.getImage(), 460, 360, 80, 80, null);
			//g.drawRect(480, 360, 37, 80); //player hit box
			/*g.setColor(new Color(0, 0, 0, 100));  //night mode 
			g.fillRect(0, 0, 1000, 800);*/
		}
		p = MouseInfo.getPointerInfo().getLocation();
	}
	public void pointerSet() {
		int winX = f.getX(), winY = f.getY();
		p.x -= winX;
		p.y -= winY;
		if (p.x <= 1000 && p.x >= 0 && p.y <= 800 && p.y >= 0) {
			cursorX = p.x;
			cursorY = p.y;
		}
		else {
			cursorX = 0;
			cursorY = 0;
		}
	}
	public static void main(String[] arg) {
		Frame f = new Frame();
		
		
	}
	public Frame() {
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        fonts = ge.getAllFonts();
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
		int key = arg0.getButton();
		switch(key) {
			case 1:
				if (cursorX >= 360 && cursorX <= 630 && cursorY >= 400 && cursorY <= 440) {
					loading = true;
					loadType = 1;
					SwingUtilities.invokeLater(() ->cursor = new Cursor(Cursor.DEFAULT_CURSOR));
					SwingUtilities.invokeLater(() -> {f.setCursor(cursor);});
				} else
					try {
						if(cursorX >= 360 && cursorX <= 630 && cursorY >= 500 && cursorY <= 540 && !isFileEmpty()) {
							loading = true;
							loadType = 2;
							SwingUtilities.invokeLater(() ->cursor = new Cursor(Cursor.DEFAULT_CURSOR));
							SwingUtilities.invokeLater(() -> {f.setCursor(cursor);});
						}
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
				}
				break;
		}
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
				if (currentLocation.isObstructed) {
					currentLocation.health -= weapon.damage;
					if (currentLocation.health <= 0) {
						switch(currentLocation.getAsset()) {
							case 2:
								currentLocation.img = getImage("/imgs/grass/grass1.png");
								currentLocation.isObstructed = false;
								currentLocation.asset = 1;
								break;
							case 6:
								currentLocation.img = getImage("/imgs/desert/desert1.png");
								currentLocation.isObstructed = false;
								currentLocation.asset = 8;
								break;
							case 7:
								currentLocation.img = getImage("/imgs/desert/desert1.png");
								currentLocation.isObstructed = false;
								currentLocation.asset = 8;
								break;
							case 10:
								if (weapon.type.equals("pick")) {
									currentLocation.img = getImage("/imgs/rock/rock1.png");
									currentLocation.isObstructed = false;
									currentLocation.asset = 9;
								}
								break;
						}
					}
				}
		
				//attack button
				break;
			case 76: //L
				loadWorld();
				break;
			case 69: //e
				if (podX >= 300 && podX <= 400 && podY >= 140 && podY <= 165 && !isInPod) {
					isInPod = true;
				}
				else if (interiorX >= 160 && interiorX <= 210 && interiorY <= -150 && interiorY >= -190 && isInPod){
					isInPod = false;
				}
				break;
			case 27: //Esc
				saveWorld();
				isOnHomescreen = true;
				iterate = 0;
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
	public void podObstacle() {
		if (podX <= 460 && podX >= 449 && podY <= 410 && podY >= 160) {
			left = false;
		}
		if(podX >= 245 && podX <= 249 && podY <= 410 && podY >= 160) {
			right = false;
		}
		if(podY <= 410 && podY >= 400 && podX <= 460 && podX >= 245) {
			down = false;
		}
		if (podY >= 160 && podY <= 170 && podX <= 460 && podX >= 245) {
			up = false;
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
			speed = 4;
		}
		else {
			speed = 7;
		}
	}
	public void move() {
		if (!isInPod) {
			if (up) {
				w.moveUp(speed);
				c.up();
				podY += speed;
			}
			if (down) {
				w.moveDown(speed);
				c.down();
				podY -= speed;
			}
			if (left) {
				w.moveLeft(speed);
				c.right();
				podX -= speed;
			}
			if (right) {
				w.moveRight(speed);
				c.left();
				podX += speed;
			}
		}
		else {
			if (up) {
				interiorY += speed;
				c.up();
			}
			if (down) {
				interiorY -= speed;
				c.down();
			}
			if (left) {
				interiorX -= speed;
				c.right();
			}
			if (right) {
				interiorX += speed;
				c.left();
			}
		}
	}
	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		int key = arg0.getKeyCode();
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
		int[][] board = new int[500][500];
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
	public boolean isFileEmpty() throws FileNotFoundException {
		BufferedReader reader = new BufferedReader(new FileReader("world.txt"));
		try {
			int num = reader.read();
			reader.close();
			if (num == -1) {
				return true;
			}
			else {
				return false;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			
		}
		return true;
	}
	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	public void cursorHover(Graphics g) {
		if (isOnHomescreen) {
			try {
				if (cursorX >= 360 && cursorX <= 630 && cursorY >= 400 && cursorY <= 440) {
					newWorldColor = new Color(255, 0, 0);
					SwingUtilities.invokeLater(() ->cursor = new Cursor(Cursor.HAND_CURSOR));
					SwingUtilities.invokeLater(() -> {f.setCursor(cursor);});
				}
				else if (cursorX >= 360 && cursorX <= 630 && cursorY >= 500 && cursorY <= 540 && !isFileEmpty()) {
					loadWorldColor = new Color(255, 0, 0);
					SwingUtilities.invokeLater(() ->cursor = new Cursor(Cursor.HAND_CURSOR));
					SwingUtilities.invokeLater(() -> {f.setCursor(cursor);});
				}
				else {
					newWorldColor = new Color(255, 255, 255);
					loadWorldColor = new Color(255, 255, 255);
					SwingUtilities.invokeLater(() ->cursor = new Cursor(Cursor.DEFAULT_CURSOR));
					SwingUtilities.invokeLater(() -> {f.setCursor(cursor);});
				}
			}
			catch(FileNotFoundException e) {
				e.printStackTrace();
			}
			
		}
	}	
	public Block[][] getPod(){
		Block[][] pod = {{new Block(12), new Block(12), new Block(12), new Block(14), new Block(14), new Block(14),new Block(12), new Block(12), new Block(12)},
						 {new Block(12), new Block(14), new Block(14), new Block(13), new Block(13), new Block(13),new Block(14), new Block(14), new Block(12)},
						 {new Block(12), new Block(14), new Block(13), new Block(13), new Block(13), new Block(13),new Block(13), new Block(14), new Block(12)},
						 {new Block(14), new Block(13), new Block(13), new Block(13), new Block(13), new Block(13),new Block(13), new Block(13), new Block(14)},
						 {new Block(14), new Block(13), new Block(13), new Block(13), new Block(13), new Block(13),new Block(13), new Block(13), new Block(13)},
						 {new Block(14), new Block(13), new Block(13), new Block(13), new Block(13), new Block(13),new Block(13), new Block(13), new Block(14)},
						 {new Block(12), new Block(14), new Block(13), new Block(13), new Block(13), new Block(13),new Block(13), new Block(14), new Block(12)},
						 {new Block(12), new Block(14), new Block(14), new Block(13), new Block(13), new Block(13),new Block(14), new Block(14), new Block(12)},
						 {new Block(12), new Block(12), new Block(12), new Block(14), new Block(14), new Block(14),new Block(12), new Block(12), new Block(12)}
				};
		return pod;
	}
	public void podMoveUp(int speed) {
		for (Block[] r : podInterior) {
			for (Block c : r) {
				c.y += speed;
			}
		}
	}
	public void podMoveDown(int speed) {
		for (Block[] r : podInterior) {
			for (Block c : r) {
				c.y -= speed;
			}
		}
	}
	public void podMoveLeft(int speed) {
		for (Block[] r : podInterior) {
			for (Block c : r) {
				c.x -= speed;
			}
		}
	}
	public void podMoveRight(int speed) {
		for (Block[] r : podInterior) {
			for (Block c : r) {
				c.x += speed;
			}
		}
	}
}
