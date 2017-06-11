package 扫雷V3;



public class RecordList {
	private int size=0;
	private Mine []array;
	
	public RecordList(){
		array=new Mine[0];
	}
	public RecordList(int length){
		array=new Mine[length];
	}
	//添加元素的方法
	public void add(Mine stu){
		//實例化一個學生數組長度為原數組加1
		Mine []tempArray=new Mine[size+1];
		//把原數組中的元素賦給新數組
		for(int i=0;i<size;i++){
			tempArray[i]=array[i];
		}
		//把新的元素繼續賦值
		tempArray[size]=stu;
		array=tempArray;
		size++;
		
	}
	public void add(Mine stu,int index){
		if(index>=0 && index<size){
		//實例化一個學生數組長度為原數組加1
		Mine []tempArray=new Mine[size+1];
		//把原數組中的元素賦給新數組
		for(int i=0;i<index;i++){
			tempArray[i]=array[i];
		}
		tempArray[index]=stu;
		//繼續把原數組中的元素賦給新數組
		for(int i=index+1;i<size+1;i++){
			tempArray[i]=array[i-1];
		}
		array=tempArray;
		size++;	
		}
	}
	
	//更新元素
	public Object set(Mine stu,int index){
		if(index>=0 && index<size){
			Mine s=array[index];
			//實例化一個學生數組長度為原數組長度
			Mine []tempArray=new Mine[size];
			//把原數組中的元素賦給新數組
			for(int i=0;i<index;i++){
				tempArray[i]=array[i];
			}
			tempArray[index]=stu;
			//繼續把原數組中的元素賦給新數組
			for(int i=index+1;i<array.length+1;i++){
				tempArray[i]=array[i];
			}
			array=tempArray;
			return s;
			}
		else 
			return null;	
		
	}
	//删除元素
	public Object remove(Mine stu){
		int t=0;
		//實例化一個學生數組長度為原數組減1
		Mine []tempArray=new Mine[size-1];
		//查找原數組中的元素為stu的位置
		for(int i=0;i<size;i++){
			if(array[i].equals(stu)){
				t=i;
				break;		
			}
		}//把原數組中的元素賦給新數組
		if(t>0){
		for(int i=0;i<t;i++){
	    	tempArray[i]=array[i];	
		}
		
		//繼續把原數組中剩餘的元素賦給新數組
		for(int i=t;i<size-1;i++){
			tempArray[i]=array[i+1];
		}
		}
			
		array=tempArray;
		size--;
		return null;
	}
	//移除元素的方法
	public Object remove(int index){
		if(index>=0 && index<size){
			Mine s=array[index];
			//實例化一個學生數組長度為原數組減1
			Mine []tempArray=new Mine[size-1];
			//把原數組中的元素賦給新數組
			for(int i=0;i<index;i++){
				tempArray[i]=array[i];
			}
			//繼續把原數組中剩餘的元素賦給新數組
			for(int i=index;i<size-1;i++){
				tempArray[i]=array[i+1];
			}
			array=tempArray;
			size--;	
			return s;
			}
		else 
			return null;
	}
	//检查元素是否存在
	public boolean check(Mine c){
		int cnt=0;
		for(int i=0;i<size;i++)
			if(array[i].getRow()==c.getRow() && array[i].getList()==c.getList()){
				cnt=1;
				break;
			}
		if(cnt==0)
		    return false;
		else 
			return true;
								
	}
	//获取元素
	public Object get(int index){
		return array[index];
		
	}
	//获取元素总数
	public int size(){
		return size;
	}
	



}
