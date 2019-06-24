package shortest_distance;
//n个城市，选择路线使得某个城市到另一指定城市距离最短
/*
 * 算法：
 * 1.将图中所有顶点的集合即为V，最小生成树中的顶点集合为U。初始时，V中包含所有顶点，而U中只有一个顶点V0
 * 2.计算下一个顶点到顶点V0的最短路径，并将该顶点加入集合U中
 * 3.将将上述步骤不断重复，直到全部顶点都加入集合U，这样便得到每个顶点到达顶点V0的最短路径
 *  
 * */
import java.util.*;
class GraphMartrix2{
	final static int MaxNum=20;
	char[] Vertex=new char[MaxNum];					//保存顶点信息：序号或者字母
	int GType;
	int VertexNum;									//顶点的数量
	int EdgeNum;									//边的数量
	int[][] EdgeWeight=new int[MaxNum][MaxNum];     //保存边的权
	int[] isTrav=new int[MaxNum];					//遍历标志
}
public class shortest_distance {
	static final int MaxValue=65535;				//边权值最大值
	static int[] path=new int[GraphMartrix2.MaxNum]; //两点经过的顶点集合的数组
	static int[] tmpvertex=new int[GraphMartrix2.MaxNum];	//最短路径的起始点集合
	static Scanner sc=new Scanner(System.in);
	static void CreatGraph(GraphMartrix2 GM) {		//创建邻接矩阵图
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
			if(GM.GType==0) {						//若是无向图
				GM.EdgeWeight[j][i]=weight;
			}
		}
	}
	static void ClearGraph(GraphMartrix2 GM) {
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
	
	static void DistMin(GraphMartrix2 GM,int vend) {//最短路径算法
		int[] weight=new int[GraphMartrix2.MaxNum];
		int i,j,k,min;
		vend--;
		for(i=0;i<GM.VertexNum;i++) {				//初始weight数组
			weight[i]=GM.EdgeWeight[vend][i];		//保存最小权值
		}
		for(i=0;i<GM.VertexNum;i++) {				//初始path数组
			if(weight[i]<MaxValue&&weight[i]>0) {   //有效权值
				path[i]=vend;						//保存边
			}
		}
		for(i=0;i<GM.VertexNum;i++) {				//初始tmpvertex数组
			
				tmpvertex[i]=0;						//初始化顶点集合为空
			
		}
		tmpvertex[vend]=1;							//选入顶点vend
		weight[vend]=0;
		for(i=0;i<GM.VertexNum;i++) {
			min=MaxValue;
			k=vend;
			for(j=0;j<GM.VertexNum;j++) {			//查找未使用顶点的最小权值
				if(tmpvertex[j]==0&&weight[j]<min) {
					min=weight[j];
					k=j;
				}
			}
			tmpvertex[k]=1;							//将顶点k选入;
			for(j=0;j<GM.VertexNum;j++) {			//以顶点k为中间点，重新计算权值
				if(tmpvertex[j]==0&&weight[k]+GM.EdgeWeight[k][j]<weight[j]) {
					weight[j]=weight[k]+GM.EdgeWeight[k][j];
					path[j]=k;
				}
			}
		}
	}
	public static void main(String[] args) {
		GraphMartrix2 GM=new GraphMartrix2();
		String go;
		int vend;
		int i,k;
		String ii;
		System.out.println("求解最短路径问题");
		do {
			System.out.printf("请先输入输入生成图的类型:");
			GM.GType=sc.nextInt();	
			System.out.printf("输入图的顶点的数量:");
			GM.VertexNum=sc.nextInt();
			System.out.printf("输入图的边的数量:");
			GM.EdgeNum=sc.nextInt();
			ClearGraph(GM);
			CreatGraph(GM);
			System.out.printf("\n请输入结束点:");
			vend=sc.nextInt();
			DistMin(GM, vend);
			vend--;									//因为表述时候是从1开始，而数组是从0开始
			System.out.printf("\n各顶点到达顶点%c的最短路径分别为(起始点-结束点):\n",GM.Vertex[vend]);
			for(i=0;i<GM.VertexNum;i++) {			//输出结果
				if(tmpvertex[i]==1) {
					k=i;
					while(k!=vend) {
						System.out.printf("顶点%c->",GM.Vertex[k]);
						k=path[k];
					}
					System.out.printf("顶点%c\n",GM.Vertex[k]);
				}else {
					System.out.printf("%c->%c无路径\n",GM.Vertex[i],GM.Vertex[vend]);
				}
			}
			System.out.println("\n继续玩吗(y/n)?");
			go=sc.next();
		}while(go.equalsIgnoreCase("y"));
		System.out.println("游戏结束");
	}
}
