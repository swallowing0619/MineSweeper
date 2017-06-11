package 扫雷V3;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

public class Flag extends MouseAdapter implements Common{
	 private Graphics g;
	 private JPanel jp;
	 private int fr;
	 private int fl;
	 private  int sign=0;
	 
	 public int getSign(){
		 return sign;
	 }
	 public void setSign(int t){
		  sign=t;
	 }
	 
	 public Flag(){
	 }
	 public void sendPanel(JPanel jpl,Graphics g){
	    	jp=jpl;
	    	this.g=g;
	    	System.out.println(" here is get graphics from panel...");
	    	
     }
	 public void mousePressed(MouseEvent e){
		 int x=e.getX();		
		 int y=e.getY();
		   //计算行列
		 fl=(x-X)/SIZE;
		 fr=(y-Y)/SIZE;		
		 System.out.println("fr="+fr+"   fl="+fl);	
		 
	 }
	 public void mouseReleased(MouseEvent e){
		 Picture p=new Picture(g);
		   Mine m=new Mine(fr,fl);
		   if(!FlagList.check(m)&&!RL.check(m)){	    
		     //画出标志
		     p.drawFlag(fr, fl);
		     //记录已标记的位置
             FlagList.add(m);
		     
		 }else if(FlagList.check(m)){
			//取消标志
		     p.removeFlag(fr, fl);
		     //记录已标记的位置
		     FlagList.remove(m);
		 }
			 
	 }

	  
}
