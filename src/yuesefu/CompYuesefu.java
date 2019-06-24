package yuesefu;

import java.util.Scanner;

//复杂约瑟夫，n个人循环m，最后只剩一个，每次循环从1开始
public class CompYuesefu {
	static LinkList2 head=null,tail=null;   //头指针，尾指针
	int size=0;
	
	public void addHead(int i,int psw) {
		head=new LinkList2(i, psw,head);
		if(tail==null) {
			tail=head;
		}
		size++;
	}
	public void addTail(int i,int psw) {
		tail.next=new LinkList2(i, psw);
		tail=tail.next;
		tail.next=head;
		size++;
	}
	
	static void CircleFun(LinkList2 list,int m)       				//实现算法
	{
		LinkList2 p,q;
		int i;
		q=p=list;
		while(q.next!=p) {
			q=q.next;
		}
		System.out.printf("游戏者按照如下的顺序出列:\n");
		while(p.next!=p) {
			for(i=0;i<m-1;++i) {
				q=p;p=p.next;
			}
			q.next=p.next;
			System.out.printf("第%d个人出列，其手中的数字为%d。\n",p.no,p.psw);
			m=p.psw;
			p=null;
			p=q.next;
		}
		System.out.printf("\n最后一个人出列，其手中的出列数字为%d。",p.no,p.psw);
		
				
	}
	public static void main(String[] args) {
		CompYuesefu ysf=new CompYuesefu();
		int e,baoshu;
		System.out.printf("约瑟夫环问题求解！\n");
		System.out.printf("请输入约瑟夫环中的人数:\n");
		Scanner sc=new Scanner(System.in);    
		int num=sc.nextInt();
		System.out.printf("按顺序输入每个人手中的出列数字:\n");
		e=sc.nextInt();
		ysf.addHead(1, e);
		for(int i=2;i<=num;++i) {						//构造循环链表
			e=sc.nextInt();
			ysf.addTail(i, e);
		}
		System.out.println("请输入第一次出列的数字:\n");
		baoshu=sc.nextInt();
		CircleFun(head, baoshu);						//求解约瑟夫环
		System.out.printf("\n");
	}
	
	
}
class LinkList2								//结点类
{
	int no;  								//序列
	int psw;
	LinkList2 next;							//下一个结点
	
	public LinkList2(int no,int psw) {
		this.no=no;
		this.psw=psw;
	}
	
	public LinkList2(int no,int psw,LinkList2 next) {   		//构造方法
		this.no=no;
		this.psw=psw;
		this.next=next;
	}
}