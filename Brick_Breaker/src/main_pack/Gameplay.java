package main_pack;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Gameplay extends JPanel implements KeyListener,ActionListener {

	
	private boolean play=false;
	private int totalBricks=21;
	private int score=0;
	private Timer timer;
	private int delay=8;
	private int playerX=350;
	private int ballposX=390;
	private int ballposY=530;
	private int ballXdir=-1;
	private int ballYdir=-2;
	private map m;;
	public Gameplay()
	{
		m=new map(3,7);
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		timer= new Timer(delay,this);
		timer.start();
	}
	
	
	public void paint(Graphics g)
	{
		//background
		g.setColor(Color.black);
		g.fillRect(1,1, 692, 592);
		
		m.draw((Graphics2D)g);
		//border
		g.setColor(Color.yellow);
		g.fillRect(0,0, 3, 592);
		g.fillRect(0,0, 692, 3);
		g.fillRect(691,0, 3, 592);
		
		//font
		g.setColor(Color.WHITE);
		g.setFont(new Font("serif",Font.BOLD,20));
		g.drawString(""+score, 590, 30);
		
		//paddle
		g.setColor(Color.green);
		g.fillRect(playerX, 550, 100, 8);
		
		//ball
		g.setColor(Color.yellow);
		g.fillOval(ballposX, ballposY, 20, 20);
		
		
		if(ballposY>570)
		{
			play=false;
			ballXdir=0;
			ballYdir=0;
			
			g.setColor(Color.white);
			g.setFont(new Font("serif",Font.BOLD,30));
			g.drawString("Game Over , Scores: "+score,190 , 300);
			
			
			g.setFont(new Font("serif",Font.BOLD,14));
			g.drawString("Press Enter To Restart",230 , 350);
			
		}
		
		g.dispose();
	}
	
	
	
	public void actionPerformed(ActionEvent e) {
	timer.start();
	if(play)
	{
		if(new Rectangle(ballposX,ballposY,20,20).intersects(new Rectangle(playerX,550,100,8)))
		{
			ballYdir =-ballYdir;
		}
		
	A:	for(int i=0;i<m.Map.length;i++)
		{
			for(int j=0;j<m.Map[0].length;j++)
			{
				if(m.Map[i][j]>0)
				{
					int brickX=j*m.brickWidth+80;
					int brickY=i*m.brickHeight+50;
					int brickWidth=m.brickWidth;
					int brickHeight=m.brickHeight;
					
					Rectangle rect=new Rectangle(brickX,brickY,brickWidth,brickHeight);
					Rectangle ballRect=new Rectangle(ballposX,ballposY,20,20);
					Rectangle brickRect=rect;
					
					if(ballRect.intersects(brickRect))
					{
						m.setBrickvalue(0, i, j);
						totalBricks--;
						score+=5;
						
						if(ballposX +19<=brickRect.x || ballposX +1>=brickRect.x+ brickRect.width)
						{
							ballXdir=-ballXdir;
							
						}
						else
						{
							ballYdir=-ballYdir;
						}
						break A; 
					}
					
				}
			}
		}
		
		ballposX +=ballXdir;
		ballposY += ballYdir;
		if(ballposX < 0)
		{
			ballXdir = -ballXdir;
		}
		if(ballposY < 0)
		{
			ballYdir = -ballYdir;
		}
		if(ballposX > 670)
		{
			ballXdir =- ballXdir;
		}
		
	}
	repaint();
		
	}

	
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode()== KeyEvent.VK_RIGHT)
		{
			if(playerX >= 600)
			{
				playerX=600;
			}
			else
			{
				moveRight();
			}
		}
		if(e.getKeyCode()== KeyEvent.VK_LEFT)
		{
			if(playerX < 10)
			{
				playerX=10;
			}
			else
			{
				moveLeft();
			}
		}
		if(e.getKeyCode()== KeyEvent.VK_ENTER)
		{
			if(!play)
			{
				play=true;
				ballposX=120;
				ballposY=350;
				ballXdir=-1;
				ballYdir=-2;
				playerX=310;
				score=0;
				totalBricks=21;
				m=new map(3,7);
				
				repaint();
				
			}
		}
		
	}
	public void moveRight()
	{
	play=true;
	playerX +=20;
	}
	public void moveLeft()
	{
		play=true;
		playerX -=20;
	}

	
	public void keyReleased(KeyEvent e) {		
	}
	
	public void keyTyped(KeyEvent e) {
	}

}
