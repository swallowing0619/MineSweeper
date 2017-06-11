package 扫雷V3;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class MineListener extends MouseAdapter implements Common{
	private Graphics g;
    private JPanel jp;
    private int r;
    private int l;
    private String newCommand="";
    private int tr=0;
    private int tl=0;
    private MineFrame mf;
    
    public MineListener(){   	
    }
    public void setNew(String s){
	    newCommand=s;
    }
    
    public void sendPanel(JPanel jpl,Graphics g,MineFrame mf){
    	jp=jpl;
    	this.g=g;
    	this.mf=mf;
    	System.out.println(" here is get graphics from panel...");
    }
    
	public void mousePressed(MouseEvent e){
		int x=e.getX();		
	    int y=e.getY();
	   //计算行列
	   l=(x-X)/SIZE;
	   r=(y-Y)/SIZE;		
	   System.out.println("r="+r+"   l="+l);		
	}
	public void mouseReleased(MouseEvent e){
	
		int temp=MineArea[r][l];	
		System.out.println("temp="+temp);
		//点击的位置是否在雷的位置
		this.click(temp);
	    //检查是否赢了
		this.win();
			 	    										
	}	
    public void mouseMoved(MouseEvent e){
    	
    	if(f.getSign()==0){
    	    //获取当前行和列  	
    	   int x=e.getX();
    	   int y=e.getY();
    	   int ll=(x-X)/SIZE;
 	       int rr=(y-Y)/SIZE;
 	      // System.out.println("ll="+ll+"rr="+rr);
 	 
    	 //实例化绘制图片的类
	        Picture p=new Picture(g);
    	    if(tr==rr && tl==ll  && ll<9 &&rr<9){    	   
    	    //调用绘制图片的方法
    	        p.drawSoldier(rr,ll);
    	    
    	   
    	    }else if(( tr!=rr || tl!=ll  )&& ll<9 &&rr<9){
    		//把上一个位置移除
    		 p.remove(tr, tl);
    		//把当前位置改为下一个位的前一位置
    		tr=rr;
    	    tl=ll;
    	    //绘制当前位置的图片
    	    p.drawSoldier(rr,ll);
    		}
    	
    	}
    }
	
	//判断赢的方法
	public void win(){
		System.out.println("RL.size="+RL.size());
		if(RL.size()==81-MineFrame.combs){
			for(int i=0;i<9;i++)
				for(int j=0;j<9;j++){
					if(MineArea[i][j]==100){						
						//实例化绘制图片的类
				    	Picture p=new Picture(g);
				    	//调用绘制图片的方法
				    	p.drawFlag(i,j);					    	
				    	//记录已显示过的位置
						Mine m=new Mine(i,j);
						RL.add(m);
						if(!FlagList.check(m))
						    FlagList.add(m);
					}
				}	
			if(newCommand.equals("闯关")){
				JOptionPane.showMessageDialog(null,"进入第二关！！！");
				MineFrame.combs=15;
				mf.newPlay("闯关");
		    	jp.removeMouseListener(this);	
				jp.removeMouseListener(f);
			}else {
	            	JOptionPane.showMessageDialog(null,"You are winner！！！");
			    	jp.removeMouseListener(this);	
					jp.removeMouseListener(f);
			}
			    
		}
			
	}
	
	//根据点击的位置显示图片的方法
	public void click(int t){
		if(t==100){
			
			System.out.println("踩雷啦!!!!!!!");


			//清空标志队列
			if(FlagList.size()>0){
		    for(int i=FlagList.size();i>=0;i--)
			    FlagList.remove(i);
		}	
						
			//显示所有图片并保存数组位置到数组队列
			for(int i=0;i<9;i++)
			    for(int j=0;j<9;j++){
			    	Mine m=new Mine(i,j);
			    	//if(!RL.check(m)){
			    	System.out.println("i="+i+" j="+j);
						   RL.add(m);
						  //实例化绘制图片的类
					      Picture p=new Picture(g);
					      //调用绘制图片的方法
					      p.drawPicture(i,j);	
						//}	
			}
			
			JOptionPane.showMessageDialog(null,"踩雷！Game Over！！！");
			jp.removeMouseListener(this);
			jp.removeMouseListener(f);
		}
		
		//若点击的不是雷的位置则显示数字
		else if(t==1 ||t==2 ||t==3 ||t==4 ||t==5 ||t==6 ||t==7){
			System.out.println("数字");						
			//记录已显示过的位置
			Mine m=new Mine(r,l);
			System.out.println("Check:"+RL.check(m));
			if(!RL.check(m)){
			   RL.add(m);
			  //实例化绘制图片的类
		      Picture p=new Picture(g);
		      //调用绘制图片的方法
		      p.drawPicture(r,l);	
			}
			
		}
		//若点击位置为空白
		else if(t==0){
			this.searchAround(r, l);		
		}
	}
	
	//检查该位置周围的8个位置的情况的方法
	public void searchAround(int x,int y){
			System.out.println("====>search");
			Mine m=new Mine(x,y);
			//实例化绘制图片的类
		    Picture p=new Picture(g);
		    //若该位置没有画过图片则画出图片
	    	if(!RL.check(m)){
				   RL.add(m);				  
			      //调用绘制图片的方法
			      p.drawPicture(x,y);	
			}
	    	else if (RL.check(m))
	    		return;
		    //对该位置的周围进行判断是否为空，是则显示并检查8个位置的周围是否为空！递归	
			//若已经显示图片则不遍历
			//左上角
		    if(x-1>-1 && x-1<9 && y-1>-1 && y-1<9 && MineArea[x-1][y-1]==0)				    
			    this.searchAround(x-1,y-1);    	
			else if(x-1>-1 && x-1<9 && y-1>-1 && y-1<9 &&MineArea[x-1][y-1]!=0 && MineArea[x-1][y-1]!=100){
				Mine m1=new Mine(x-1,y-1);
				//若该位置没有画过图片则画出图片
		    	if(!RL.check(m1)){
					   RL.add(m1);				  
				      //调用绘制图片的方法
				      p.drawPicture(x-1,y-1);	
					}	
		
			}
		    //正上方	
		    if(x-1>-1 && x-1<9 && y>-1 && y<9 && MineArea[x-1][y]==0)
			    this.searchAround(x-1,y); 
			else if(x-1>-1 && x-1<9 && y>-1 && y<9 && MineArea[x-1][y]!=0 && MineArea[x-1][y]!=100){
				Mine m1=new Mine(x-1,y);
				//若该位置没有画过图片则画出图片
		    	if(!RL.check(m1)){
					   RL.add(m1);				  
				      //调用绘制图片的方法
				      p.drawPicture(x-1,y);	
					}	
			}
            //右上角
		    if(x-1>-1 && x-1<9 && y+1>-1 && y+1<9 && MineArea[x-1][y+1]==0)
			    this.searchAround(x-1,y+1);
		    else if(x-1>-1 && x-1<9 && y+1>-1 && y+1<9 &&MineArea[x-1][y+1]!=0 && MineArea[x-1][y+1]!=100){
		    	Mine m1=new Mine(x-1,y+1);
				//若该位置没有画过图片则画出图片
		    	if(!RL.check(m1)){
					   RL.add(m1);				  
				      //调用绘制图片的方法
				      p.drawPicture(x-1,y+1);	
					}	
		    }
		    //正右方
		    if(x>-1 && x<9 && y+1>-1 && y+1<9 && MineArea[x][y+1]==0)
		    	this.searchAround(x,y+1);
		    else if(x>-1 && x<9 && y+1>-1 && y+1<9 && MineArea[x][y+1]!=0 && MineArea[x][y+1]!=100){
		    	Mine m1=new Mine(x,y+1);
				//若该位置没有画过图片则画出图片
		    	if(!RL.check(m1)){
					   RL.add(m1);				  
				      //调用绘制图片的方法
				      p.drawPicture(x,y+1);	
					}	
		    }
		    //右下角
		    if(x+1>-1 && x+1<9 && y+1>-1 && y+1<9 && MineArea[x+1][y+1]==0)
			    this.searchAround(x+1,y+1);
			else if(x+1>-1 && x+1<9 && y+1>-1 && y+1<9 && MineArea[x+1][y+1]!=0 && MineArea[x+1][y+1]!=100){
				Mine m1=new Mine(x+1,y+1);
				//若该位置没有画过图片则画出图片
		    	if(!RL.check(m1)){
					   RL.add(m1);				  
				      //调用绘制图片的方法
				      p.drawPicture(x+1,y+1);	
					}	
			}
	        		
		    //正下方
		    if(x+1>-1 && x+1<9 && y>-1 && y<9 && MineArea[x+1][y]==0)
     			this.searchAround(x+1,y);
		    else if(x+1>-1 && x+1<9 && y>-1 && y<9 && MineArea[x+1][y]!=0 && MineArea[x+1][y]!=100){
		    	Mine m1=new Mine(x+1,y);
				//若该位置没有画过图片则画出图片
		    	if(!RL.check(m1)){
					   RL.add(m1);				  
				      //调用绘制图片的方法
				      p.drawPicture(x+1,y);	
					}	
		    }
	        	
		    //左下角
		    if(x+1>-1 && x+1<9 && y-1>-1 && y-1<9 && MineArea[x+1][y-1]==0)
				this.searchAround(x+1,y-1);
		    else if(x+1>-1 && x+1<9 && y-1>-1 && y-1<9 && MineArea[x+1][y-1]!=0 && MineArea[x+1][y-1]!=100){
		    	Mine m1=new Mine(x+1,y-1);
				//若该位置没有画过图片则画出图片
		    	if(!RL.check(m1)){
					   RL.add(m1);				  
				      //调用绘制图片的方法
				      p.drawPicture(x+1,y-1);	
					}	
		    }
	        	
		    //正左方
    	    if(x>-1 && x<9 && y-1>-1 && y-1<9 && MineArea[x][y-1]==0)
    			this.searchAround(x,y-1);
   	        else if(x>-1 && x<9 && y-1>-1 && y-1<9 && y-1<9 && MineArea[x][y-1]!=0 && MineArea[x][y-1]!=100){
   	        	Mine m1=new Mine(x,y-1);
				//若该位置没有画过图片则画出图片
		    	if(!RL.check(m1)){
					   RL.add(m1);				  
				      //调用绘制图片的方法
				      p.drawPicture(x,y-1);	
					}	
   	        }
	        		
		 
	}

	

	
 
	
	

}
