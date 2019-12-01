package main_pack;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

public class map {
	 public int Map[][];
	 public int brickWidth;
	 public int brickHeight;
	 
	 public map(int row,int col)
	 {
		 Map=new int [row] [col];
		 for(int i=0;i<Map.length;i++)
		 {
			 for(int j=0;j<Map[0].length;j++)
			 {
				 Map[i][j]=1;
			 }
		 }
		 brickWidth =540/col;
		 brickHeight=150/row;
	 }
	 public void draw(Graphics2D g) {
		 for(int i=0;i<Map.length;i++)
		 {
			 for(int j=0;j<Map[0].length;j++)
			 {
				 if(Map[i][j]>0)
				 {
					 g.setColor(Color.white);
					 g.fillRect(j*brickWidth+80, i*brickHeight+50, brickWidth, brickHeight);
					 g.setStroke(new BasicStroke(3));
					 g.setColor(Color.black);
					 g.drawRect(j*brickWidth+80, i*brickHeight+50, brickWidth, brickHeight);
				 }
			 }
		 }
	 }
	 public void setBrickvalue(int value,int row,int col)
	 {
		 Map[row][col]=value;
	 }
	
	 
}
