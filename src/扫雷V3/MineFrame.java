package 扫雷V3;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

/**
 * 扫雷界面
 * @author hm
 *
 */
public class MineFrame extends JFrame implements Common{
	public static int combs=10;//地雷数
	private int []mine=new int [40];//地雷位置
	private JTextField jt=new JTextField();
	private Graphics g;
	
	//实例化一个事件处理方法为属性
    private MineListener ml=new MineListener();	
	private static MineFrame mf;
	//主函数部分
	public static void main(String[] args) {
		mf=new MineFrame();
		mf.Sweepuint();
	
	}
	
	//创建雷区面板为属性
		private	JPanel jp = new JPanel(){
				//重写paint方法(重绘)
				public void paint(Graphics g){
					super.paint(g);	
					//画横线
					for(int i=0;i<LIST;i++){
						g.drawLine(X, Y+SIZE*i,X+SIZE*(LIST-1),Y+SIZE*i );
					}//画纵线
					for(int i=0;i<ROW;i++){
						g.drawLine(X+SIZE*i, Y, X+SIZE*i, Y+SIZE*(ROW-1));
					}
					//遍历雷区
					for(int i=0;i<9;i++)
					    for(int j=0;j<9;j++){
					    	//给每个方格画出3D按钮效果
					        for(int k=0;k<5;k++){
			                     Color c=new Color(100+15*k,100+15*k,100+15*k);
			                     g.setColor(c);
			                     g.drawRect((X+SIZE*j)+k, (Y+SIZE*i)+k, SIZE-2*k, SIZE-2*k);
			                   
					        }
					}
					//重绘游戏进程	
				   if(RL.size()>0){
					  for(int i=0;i<RL.size();i++){
						    //获取数组队列中的值
					        Mine m=(Mine)RL.get(i);					        
					        int r=m.getRow();
					        int l=m.getList();
					        //实例化绘制图片的类
			    	        Picture p=new Picture(g);
			    	        //调用绘制图片的方法
			    	        p.drawPicture(r,l);	
					    }
					}
				   //画标志
				   if(FlagList.size()>0){
						  for(int i=0;i<FlagList.size();i++){
							    //获取数组队列中的值
						        Mine m=(Mine)FlagList.get(i);					        
						        int r=m.getRow();
						        int l=m.getList();
						        //实例化绘制图片的类
				    	        Picture p=new Picture(g);
				    	        //调用绘制图片的方法
				    	        p.drawFlag(r,l);	
						    }
						}
	
					
				}
				
			};
		
    //创建扫雷窗体的方法
	public void Sweepuint() {
		//设置窗体属性
		this.setTitle("扫雷soldier");
		this.setSize(new Dimension(300,400));
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(3);
		//调用创建菜单栏的方法
		this.menu();
		//调用创建北边面板的方法
		this.northPanel();
		//添加中间雷区面板
		this.add(jp);
		//设置面板颜色
		jp.setBackground(Color.LIGHT_GRAY);
		//显示窗体
		this.setVisible(true);
		
		g=jp.getGraphics();	
		System.out.print("取得画布！");
		
		this.setDefaultCloseOperation(3);
		System.out.println(" Main frame is visible !");
	
		ml.sendPanel(jp, g,mf);
		f.sendPanel(jp, g);
	}
	
	
	
	//创建北边面板的方法
	private void northPanel() {
		//实例化一个面板对象
		JPanel jp=new JPanel();	
		//设置面板属性
		jp.setPreferredSize(new Dimension(0,50));
		jp.setBackground(Color.ORANGE);
		
		//jp.setLayout(null);
		//设置面板位置
		this.add(jp,BorderLayout.NORTH);
		
		ImageIcon im =new ImageIcon("images/001.jpg");
		//实例化一个按钮对象
		JButton jp1=new JButton(im);
		jp1.setPreferredSize(new Dimension(39,39));
		jp.add(jp1);
		//设置显示框属性
		jt.setPreferredSize(new Dimension(50,40));
		jp.add(jt);
		
//		ImageIcon im2 =new ImageIcon("images/004.jpg");
//		//实例化一个按钮对象
//		JButton jp2=new JButton(im2);	
//		jp2.setPreferredSize(new Dimension(39,39));
//		jp.add(jp2);
//		
//		//实例化一个显示框对象
//		JTextField jt1=new JTextField();	
//		jt1.setPreferredSize(new Dimension(50,40));
//		jp.add(jt1);
	}
	//创建菜单栏的方法
	private void menu() {
		//实例化一个菜单栏对象
		JMenuBar jmb=new JMenuBar();
		String []array={"新开","标记","帮助","排行榜"};
		String [][]item={{"初级","中级","高级","闯关"},{"标记开","标记关"},{"说明","版权"},{"查看"}};
		//遍历数组
		for(int i=0;i<array.length;i++){
			//实例化菜单选项对象
			JMenu jm=new JMenu(array[i]);
			jmb.add(jm);
			for(int j=0;j<item[i].length;j++){
			    //实例化菜单子选项对象
			    JMenuItem jmt=new JMenuItem(item[i][j]);
			    //添加监听器方法
			    jmt.addActionListener(l);
			    jm.add(jmt);
			}	
		}
		this.setJMenuBar(jmb);
	}
	
	
	//针对菜单栏的匿名内部类
	private ActionListener l=new ActionListener(){
		// MineFrame mf=new MineFrame();
		public void actionPerformed(ActionEvent e) {
			String s=e.getActionCommand();
			if(s.equals("初级")){
				combs=10;
				System.out.println("=======>"+combs);
				newPlay(s);
			}else if(s.equals("中级")){
				combs=15;
				System.out.println("=======>"+combs);
				newPlay(s);
			}else if(s.equals("高级")){
				combs=20;
				System.out.println("=======>"+combs);
				newPlay(s);
				
			}else if(s.equals("闯关")){
				combs=10;
				System.out.println("=======>"+combs);
				newPlay(s);
			}else if(s.equals("标记开")&&f.getSign()==0){	
				//画出绿色矩形框
			    g.setColor(Color.GREEN);
			    for(int k=0;k<4;k++){
			        g.drawRect(X-k,Y-k ,X+(ROW-1)*SIZE,Y+(LIST-1)*SIZE-2*k);
			    }
			    System.out.println("ml remove and f add");
				jp.removeMouseListener(ml);				
				//给面板添加监听器方法
				jp.addMouseListener(f);	
				f.setSign(1);
			}else if(s.equals("标记关")&&f.getSign()==1){		
				 g.setColor(Color.LIGHT_GRAY);
				 for(int k=0;k<4;k++){
					 g.drawRect(X-k,Y-k ,X+(ROW-1)*SIZE,Y+(LIST-1)*SIZE-2*k);
				    }
				 System.out.println("ml add and f remove");
				 jp.removeMouseListener(f);				
				 //给面板添加监听器方法
				 jp.addMouseListener(ml);
				 f.setSign(0);
			}

			else if(s.equals("指南")){
				JOptionPane.showMessageDialog(null, "游戏指南：\n点击“新开”菜单，选择游戏难易级别,开始游戏\n" +
						"点击非雷位置，会显示提示图片，表示其周围8个位置的雷数，直至最后找出所有雷，则胜利\n" +
						"点击“标志”菜单，选择是否开启标志，开启则可以用小红旗标志记录雷的位置，关闭标志后继续游戏");
				
			}else if(s.equals("版权")){
				JOptionPane.showMessageDialog(null,"版权所有：胡梦");
				
			}else if(s.equals("查看")){
				
			}
						
		}
	
	};	
		//重新开局的方法
		public void newPlay(String s){
			jt.setText(""+combs);
			//清空一次二维数组
			for(int i=0;i<9;i++)
				for(int j=0;j<9;j++)
					MineArea[i][j]=0;
			//清空数组队列
			if(RL.size()>0){
			    for(int i=RL.size();i>=0;i--)
				    RL.remove(i);
			}
		   
			
			if(FlagList.size()>0){
			    for(int i=FlagList.size();i>=0;i--)
				    FlagList.remove(i);
			}
			 //初始化面板
			jp.paint(g);
			//创建雷的位置
			produceMines();
		
			//根据产生的雷确定整个雷区其余的数字
			setData();
			
			//给面板添加监听器方法
		    jp.addMouseListener(ml);
		    jp.addMouseMotionListener(ml);
            ml.setNew(s);
		   
		}
		
		//初始化雷的位置的方法
		public void produceMines(){
			//创建雷所在的位置,产生不重复的随机数
			Random ran=new Random();
			for(int i=0;i<combs;i++){
				Boolean flag=true;
			    int temp=ran.nextInt(81);   
		        for(int j=0;j<i;j++){     
			    	if(mine[j]==temp){
			    		flag=false ;	
			    		break;
			    	}  
		        }
		        if(flag){
		        	mine[i]=temp;
		        }
		        else 
			      i--;
			 }			
			for(int i=0;i<combs;i++){
				System.out.println(mine[i]);
			}
		}
		
		//设置雷周围的数字的方法
		public void setData(){
			for(int i=0;i<combs;i++){
				//计算每个雷所在的横纵行位置,用二维数组存储雷位置
			    int x=mine[i]/9;
			    int y=mine[i]-9*x;
			    MineArea[x][y]=100;		
			}		
			for(int i=0;i<9;i++)
				for(int j=0;j<9;j++){
					if(MineArea[i][j]==0){
						int count=0;
						if(i>0 && i<8 && j>0 && j<8){
							//对其周围8个位置进行检查
							if(MineArea[i-1][j-1]==100)
								count++;
							if(MineArea[i-1][j]==100)
								count++;
							if(MineArea[i-1][j+1]==100)
								count++;
							if(MineArea[i][j-1]==100)
								count++;
							if(MineArea[i][j+1]==100)
								count++;
							if(MineArea[i+1][j-1]==100)
								count++;
							if(MineArea[i+1][j]==100)
								count++;
							if(MineArea[i+1][j+1]==100)
								count++;						
						}
						else if(i==0 && j==0){
							//对3个位置进行检查
							if(MineArea[i][j+1]==100)
								count++;
							if(MineArea[i+1][j+1]==100)
								count++;
							if(MineArea[i+1][j]==100)
								count++;
						}
						else if(i==0 && j==8){
							if(MineArea[i][j-1]==100)
								count++;
							if(MineArea[i+1][j-1]==100)
								count++;
							if(MineArea[i+1][j]==100)
								count++;
						}
						else if(i==8 && j==0){
							if(MineArea[i-1][j]==100)
								count++;
							if(MineArea[i-1][j+1]==100)
								count++;
							if(MineArea[i][j+1]==100)
								count++;
						}
						else if(i==8 && j==8){
							if(MineArea[i][j-1]==100)
								count++;
							if(MineArea[i-1][j-1]==100)
								count++;
							if(MineArea[i-1][j]==100)
								count++;
							
						}
						else if(i==0 && j>0 && j<8){
							//对5个方向位置进行检查
							if(MineArea[i][j-1]==100)
								count++;
							if(MineArea[i+1][j-1]==100)
								count++;
							if(MineArea[i+1][j]==100)
								count++;
							if(MineArea[i+1][j+1]==100)
								count++;
							if(MineArea[i][j+1]==100)
								count++;
						}
						else if(i==8 && j>0 && j<8){
							//对5个方向位置进行检查
							if(MineArea[i][j-1]==100)
								count++;
							if(MineArea[i-1][j-1]==100)
								count++;
							if(MineArea[i-1][j]==100)
								count++;
							if(MineArea[i-1][j+1]==100)
								count++;
							if(MineArea[i][j+1]==100)
								count++;
						}
						else if(j==0 && i>0 && i<8){
							//对5个方向位置进行检查
							if(MineArea[i-1][j]==100)
								count++;
							if(MineArea[i-1][j+1]==100)
								count++;
							if(MineArea[i][j+1]==100)
								count++;
							if(MineArea[i+1][j+1]==100)
								count++;
							if(MineArea[i+1][j]==100)
								count++;
						}
						else if(j==8 && i>0 && i<8){
							//对5个方向位置进行检查
							if(MineArea[i-1][j]==100)
								count++;
							if(MineArea[i-1][j-1]==100)
								count++;
							if(MineArea[i][j-1]==100)
								count++;
							if(MineArea[i+1][j-1]==100)
								count++;
							if(MineArea[i+1][j]==100)
								count++;
						}
						System.out.println("array["+i+"]"+"["+j+"]="+count);
						MineArea[i][j]=count;
					}
						
				}
		}
		
		

	
	
	
	
	
	

}
