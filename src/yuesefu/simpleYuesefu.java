package yuesefu;

import java.util.Scanner;

//简单约瑟夫环求解问题
/*顺序自杀，剩下活着的人
 * 
 * 
 * 
 * 
 * */
public class simpleYuesefu {
	static final int Num=41;					//总人数
	static final int KillMan=3;					//每几个人杀一个
	static void Josephus(int alive) {
		int[] man=new int[Num];
		int count=1;
		int i=0,pos=-1;
		while(count<=Num) {
			do {
				pos=(pos+1)%Num;   				//环处理
				if(man[pos]==0) {
					i++;
				}
				if(i==KillMan) {				//该人自杀
					i=0;
					break;
				}
			}while(true);
			man[pos]=count;
			System.out.printf("第%d个人自杀！约瑟夫环编号为%2d",pos+1,man[pos]);
			if(count%2==1) {
				System.out.printf("->");
			}else {
				System.out.printf("->\n");
			}
			count++;
		}
		System.out.printf("\n");
		System.out.printf("这%d需要存活的人的初始位置应排在以下序号：\n",alive);
		alive=Num-alive;
		for(i=0;i<Num;i++) {
			if(man[i]>alive) {
				System.out.printf("初始编号:%d,约瑟夫环编号:%d\n",i+1,man[i]);
			}
		}
		System.out.printf("\n");
	}
	public static void main(String[] args) {
		int alive;
		Scanner sc=new Scanner(System.in);
		System.out.println("约瑟夫问题求解");
		System.out.printf("输入最后需要留存人的数量：");
		alive=sc.nextInt();
		Josephus(alive);
	}
}
