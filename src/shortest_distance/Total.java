package shortest_distance;
//城市之间的最短总距离
/*
 * 可以用图结构来描述此问题：
 * 每个城市代表图中一个顶点
 * 两个顶点之间的边代表两个城市之间的路径，边的权值代表城市间的距离
 * ————>归结为图的最小生成树问题
 *      对于一个连通图中的子图，满足以下条件则称为原图的一个生成树：
 * 		子图的顶点和原图完全相同
 * 		子图的部分是原图的自己，这一部分边刚好将图中所有顶点联通
 * 		子图的边不构成回路
 * 	证明对于有n个结点的连通图，其生成树有且只有n-1条边，若边小于此数就不可能将各顶点联通，如果边连接边的数量多于n-1，
 * 	        则必须要产生回路
 *  
 *  
 *  求解图中最小生成树的算法:
 *  1.将图中所有顶点的集合即为V，最小生成树的顶点集合即为U，初始时，V中包含所有顶点，U为空集
 *  2.首先从V集合取出一个顶点V0，将其加入到U中
 *  3.从V0的临界点中选择点Vn使得(V0,Vn)边的权值最小，得到最小生成书中的一条边。将Vn点加入集合U
 *  4.接着从V-U集合中再选出一个与V0、Vn临接的顶点，找出权值最小的一条边，得到最小生成树的另一条边，将顶点加入U
 *  5.将上述步骤不断重复
 * */
import java.util.*;

class GraphMatrix{
	static final int MaxNum=20;
	char[] Vertex=new char[MaxNum];					//保留顶点信息
	int Gtype;										//图的类型:0无向图 1有向图
	int VertexNum;									//顶点的数量
	int EdgeNum;									//边的数量
	int[][] EdgeWeight=new int[MaxNum][MaxNum];     //保存边的权
	int[] isTrav=new int[MaxNum];					//遍历标志
}													//定义邻接矩阵图结构
public class Total {
	static final int MaxValue=65535;				//边权值最大值
	static final int USED=0;						//已选用顶点
	static int NoL=-1;
	static Scanner sc=new Scanner(System.in);
	static void CreateGraph(GraphMatrix GM)         //创建邻接矩阵图
	{
		int i,j,k;
		int weight;									//权
		char EstartV,EendV;							//边的起始顶点
		System.out.println("输入图中各顶点信息:");
		for(i=0;i<GM.VertexNum;i++) {
			System.out.printf("第%d个顶点",i+1);
			GM.Vertex[i]=(sc.next().toCharArray())[0];					//保存到各顶点数组元素中
			
		}
		System.out.println("输入构成各边的顶点及权值：");
		for(k=0;k<GM.EdgeNum;k++) {					//输入边的信息
			System.out.printf("第%d条边",k+1);
			EstartV=sc.next().charAt(0);
			EendV=sc.next().charAt(0);
			weight=sc.nextInt();
			for(i=0;EstartV!=GM.Vertex[i];i++);     //在已有顶点中查找始点
			for(j=0;EendV!=GM.Vertex[j];j++);		//在已有顶点中查找终点
			GM.EdgeWeight[i][j]=weight;				//对应位置保存权值，表示有一条边
			if(GM.Gtype==0) {						//若是无向图
				GM.EdgeWeight[j][i]=weight;
			}
		}
	}
	static void ClearGraph(GraphMatrix GM) {
		int i,j;
		for(i=0;i<GM.VertexNum;i++) //清空矩阵
		{
			for(j=0;j<GM.VertexNum;j++) {
				GM.EdgeWeight[i][j]=MaxValue;//设置矩阵个元素的值为MaxValue
			}
		}
	}
	static void OutGraph(GraphMatrix GM) {//输出邻接矩阵
		int i,j;
		for(j=0;j<GM.VertexNum;j++) {
			System.out.printf("\t%c",GM.Vertex[j]);//在第一行输出顶点信息
		}
		System.out.printf("\n");
		for(i=0;i<GM.VertexNum;i++) {
			System.out.printf("%c",GM.Vertex[i]);
			for(j=0;j<GM.VertexNum;j++) {
			if(GM.EdgeWeight[i][j]==MaxValue)
			{System.out.printf("\tZ");}				//以Z表示无穷大
			else {System.out.printf("\t%d",GM.EdgeWeight[i][j]);}//输出边的权值
			
		}
		System.out.printf("\n");
	}
	}
	
	static void PrimeGraph(GraphMatrix GM)         //最小生成树算法
	{
		int i,j,k,min,sum;
		int[] weight=new int[GraphMatrix.MaxNum];  //权值
		char[] vtempx=new char[GraphMatrix.MaxNum];
		sum=0;
		for(i=1;i<GM.EdgeNum;i++) {				   //保存邻接矩阵中的一行数据
			weight[i]=GM.EdgeWeight[0][i];
			if(weight[i]==MaxValue) {
				vtempx[i]=(char)NoL;
			}else {
				vtempx[i]=GM.Vertex[0];				//邻接顶点
			}
			}
			vtempx[0]=USED;							//选用
			weight[0]=MaxValue;
			for(i=1;i<GM.VertexNum;i++) {
				min=weight[0];						//最小权值
				k=i;
				for(j=1;j<GM.VertexNum;j++) {
					if(weight[j]<min&&vtempx[j]>0)  //找到具有更小权值的未使用边
					{
						min=weight[j];
						k=j;
					}
				}
				sum+=min;
				System.out.printf("(%c,%c),",vtempx[k],GM.Vertex[k]);      //输出生成树一条边
				vtempx[k]=USED;						//选用
				weight[k]=MaxValue;
				for(j=0;j<GM.VertexNum;j++) {		//重新选择最小边					
					if(GM.EdgeWeight[k][j]<weight[j]&&vtempx[j]!=0) {
						weight[j]=GM.EdgeWeight[k][j];						//权值
						vtempx[j]=GM.Vertex[k];
					}
				}
				
			}
			System.out.printf("最小生成树的总权值为:%d\n",sum);
			
		}
	public static void main(String[] args) {
		GraphMatrix GM=new GraphMatrix();
		char again;
		String go;
		System.out.println("寻找最小生成树！\n");
		do {
			System.out.printf("请先输入输入生成图的类型:");
			GM.Gtype=sc.nextInt();
			System.out.printf("输入图的顶点数量");
			GM.VertexNum=sc.nextInt();
			System.out.println("输入图的边的数量");
			GM.EdgeNum=sc.nextInt();
			ClearGraph(GM);
			CreateGraph(GM);
			System.out.println("最小生成树的边为:");
			PrimeGraph(GM);
			
			System.out.println("\n继续玩吗(y/n)?");
			go=sc.next();
		}while(go.equalsIgnoreCase("y"));
		System.out.println("游戏结束！");
	}
}
