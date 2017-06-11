package 扫雷V3;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.ImageIcon;

public class Picture implements Common {
	private Graphics g;
	
	private ImageIcon im =new ImageIcon("images/s雷.jpg");
	private ImageIcon icn=new ImageIcon("images/s旗.jpg");
	private ImageIcon icn2=new ImageIcon("images/slme.png");
	private ImageIcon icon0 = new ImageIcon("images/s00.jpg");
	private ImageIcon icon1 = new ImageIcon("images/s01.jpg");
	private ImageIcon icon2 = new ImageIcon("images/s02.jpg"); 
	private ImageIcon icon3 = new ImageIcon("images/s03.jpg");
	private ImageIcon icon4 = new ImageIcon("images/s04.jpg");
	private ImageIcon icon5 = new ImageIcon("images/s05.jpg"); 
	private ImageIcon icon6 = new ImageIcon("images/s06.jpg"); 
	private ImageIcon icon7 = new ImageIcon("images/s07.jpg"); 
		
	public Picture (Graphics g){
		this.g=g;	
		//System.out.println("画出图片");
	}
	public void drawSoldier(int x,int y){
		if(y<9)
		g.drawImage(icn2.getImage(), (X+SIZE*y+1), (Y+SIZE*x+1), null);
	}
	
	public void drawFlag(int x,int y){
		
		g.drawImage(icn.getImage(), (X+SIZE*y+1), (Y+SIZE*x+1), null);
		
	}
	public void remove(int x,int y){
		//给每个方格画出3D按钮效果
        for(int k=0;k<5;k++){
             Color c=new Color(100+15*k,100+15*k,100+15*k);
             g.setColor(c);
             g.drawRect((X+SIZE*y)+k, (Y+SIZE*x)+k, SIZE-2*k, SIZE-2*k);
        }
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect((X+SIZE*y)+5, (Y+SIZE*x)+5, SIZE-9, SIZE-9);
      //重绘游戏进程	
		   if(RL.size()>0){
			  for(int i=0;i<RL.size();i++){
				    //获取数组队列中的值
			        Mine m=(Mine)RL.get(i);					        
			        int r=m.getRow();
			        int l=m.getList();		
	    	        //调用绘制图片的方法
	    	        drawPicture(r,l);	
			    }
			}
		   if(FlagList.size()>0){
				  for(int i=0;i<FlagList.size();i++){
					    //获取数组队列中的值
				        Mine m=(Mine)FlagList.get(i);					        
				        int r=m.getRow();
				        int l=m.getList();		
		    	        //调用绘制图片的方法
		    	        drawFlag(r,l);	
				    }
				}

     
	}
	
	public void removeFlag(int x,int y){
		//给每个方格画出3D按钮效果
        for(int k=0;k<5;k++){
             Color c=new Color(100+15*k,100+15*k,100+15*k);
             g.setColor(c);
             g.drawRect((X+SIZE*y)+k, (Y+SIZE*x)+k, SIZE-2*k, SIZE-2*k);
        }
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect((X+SIZE*y)+5, (Y+SIZE*x)+5, SIZE-9, SIZE-9);
      //重绘游戏进程	
		   if(RL.size()>0){
			  for(int i=0;i<RL.size();i++){
				    //获取数组队列中的值
			        Mine m=(Mine)RL.get(i);					        
			        int r=m.getRow();
			        int l=m.getList();		
	    	        //调用绘制图片的方法
	    	        drawPicture(r,l);	
			    }
			}
        
	}
	
	public void drawPicture(int x,int y){
		int t=MineArea[x][y];
		if(t==1){
			
			g.drawImage(icon1.getImage(), (X+SIZE*y+1), (Y+SIZE*x+1), null);
		}
		else if(t==2){
			
			g.drawImage(icon2.getImage(), (X+SIZE*y+1), (Y+SIZE*x+1), null);
		}
		else if(t==3){
			
			g.drawImage(icon3.getImage(), (X+SIZE*y+1), (Y+SIZE*x+1), null);
		}
		else if(t==4){
			
			g.drawImage(icon4.getImage(), (X+SIZE*y+1), (Y+SIZE*x+1), null);
		}
		else if(t==5){
			
		g.drawImage(icon5.getImage(), (X+SIZE*y+1), (Y+SIZE*x+1), null);
		}
		else if(t==6){
			
		g.drawImage(icon6.getImage(), (X+SIZE*y+1), (Y+SIZE*x+1), null);
		}
		else if(t==7){
			
		g.drawImage(icon7.getImage(), (X+SIZE*y+1), (Y+SIZE*x+1), null);
		}
		else if(t==0){
		
			g.drawImage(icon0.getImage(), (X+SIZE*y+1), (Y+SIZE*x+1), null);
		}
		else if(t==100){
			g.drawImage(im.getImage(), (X+SIZE*y+1), (Y+SIZE*x+1), null);
		}

		 
	}

}
