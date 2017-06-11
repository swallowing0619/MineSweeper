package 扫雷V3;

public interface Common {
	
	public static final int ROW=10;//横线数目
	public static final int LIST=10;//纵线数目
	public static final int X=6;//起始坐标
	public static final int Y=10;
	public static final int SIZE=30;//大小
	public static final int MineArea[][]=new int [ROW-1][LIST-1];//二维雷区数组存储
	public static final RecordList RL=new RecordList();//利用数组队列进行记录已显示图片的位置
	public static final int Flag[][]=new int[ROW-1][LIST-1];
	public static final RecordList FlagList=new RecordList();//利用数组队列进行记录已显示標記图片的位置
	public static final int Score=0;
	public static final Flag f=new Flag();

		

}
