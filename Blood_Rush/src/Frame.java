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
import java.net.URL;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame; 
import javax.swing.JPanel;
import javax.swing.Timer;
//access key: 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

public class Frame extends JPanel implements ActionListener, MouseListener, KeyListener {
	World w = new World();
	public boolean up, down, left, right;
	public void paint(Graphics g) {
		move();
		g.setColor(Color.black);
		g.fillRect(0, 0, 1000, 800);
		w.paint(g);
		g.fillRect(475, 375, 50, 50);
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
		}
		
	}
	public void move() {
		if (up) {
			w.moveUp();
		}
		if (down) {
			w.moveDown();
		}
		if (left) {
			w.moveLeft();
		}
		if (right) {
			w.moveRight();
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
				break;
			case 68: //a
				left = false;
				break;
			case 83: //s
				down = false;
				break;
			case 65: //d
				right = false;
				break;
		}
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
