package KuohaoPipei;
import java.io.BufferedReader;
import java.io.InputStreamReader;
//括号匹配 {}[]()<>
/*
 * 使用的是栈结构，算法:
 * 1.输入1个字符，当该字符是括号字符是，程序进入循环处理
 * 2.若是左括号，将其入栈，继续执行步骤1
 * 3.是右括号，取出栈顶数据进行对比，如果匹配则不进行操作；否则必须先将刚才取出的栈顶数据重新入栈，
 * 	再将刚才输入的括号字符也入栈
 * 4.继续输入下一字符，重复执行直到所有字符都得到操作
 * 
 * 
 * */
import java.util.*;
import java.io.*;
class Stack{											//栈结构
	char[] data;
	int maxSize;
	int top;
	Scanner sc=new Scanner(System.in);
	public Stack(int maxSize) {
		this.maxSize=maxSize;
		data=new char[maxSize];
		top=-1;
	}
	public int getSize() {
		return maxSize;
	}
	public int getElementCount() {
		return top;
	}
	public boolean isEmpty() {
		return top==-1;
	}
	public boolean isFull() {
		return top+1==maxSize;
	}
	public boolean push(char data) {
		if(isFull()) {
			System.out.println("栈已满！");
			return false;
		}
		this.data[++top]=data;
		return true;
	}
	public char pop() throws Exception{
		if(isEmpty()) {
			throw new Exception("栈已空！");
		}
		return this.data[top--];
	}
	public char peek() {
		return this.data[getElementCount()];
	}
	void pipei() throws Exception{
		Stack stack;
		char ch,temp;
		int match;
		stack=new Stack(0);									//初始化一个栈结构，空栈
		BufferedReader reader=new BufferedReader(new InputStreamReader(System.in));
		ch=(char)reader.read();								//输入一个字符
		while(ch!='0')										//循环处理
		{
			if(getElementCount()==-1) {
				push(ch);
			}else {
				temp=pop();									//取出栈顶元素
				match=0;									//判断是否匹配
				
				if(temp=='('&&ch==')') {
					match=1;
				}
				if(temp=='['&&ch==']') {
					match=1;
				}
				if(temp=='{'&&ch=='}') {
					match=1;
				}
				if(temp=='<'&&ch=='>') {
					match=1;
				}
				
				if(match==0) {								//如果不匹配
					push(temp);								//原栈顶元素重新入栈
					push(ch);
				}
			}
			ch=(char)reader.read();
		}
		if(getElementCount()==-1) {
			System.out.println("输入括号完全匹配");
		}else {
			System.out.println("输入括号不完全匹配~");
		}
	}
}								


public class kuohaoMatch{
	public static void main(String[] args) throws Exception{
		String go;
		Scanner sc=new Scanner(System.in);
		Stack s=new Stack(20);
		System.out.println("括号匹配");
		do {
			System.out.printf("请先输入一组括号组合，以0表示结束。括号类型{}[]<>()\n");
			s.pipei();
			System.out.printf("\n要继续吗(y/n)？");
			go=sc.next();
		}while(go.equalsIgnoreCase("y"));
		System.out.println("游戏结束！");
	}
}