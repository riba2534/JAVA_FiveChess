package FiveChess;

public class Stack {
	   private int maxSize;
	   private int[] stackArray;
	   private int top;
	   public Stack(int s) {
	      maxSize = s;
	      stackArray = new int[maxSize];
	      top = -1;
	   }
	   public void push(int j) {//压入
	      stackArray[++top] = j;
	   }
	   public int pop() {//弹出栈顶元素
	      return stackArray[top--];
	   }
	   public int peek() {//查看栈顶元素
	      return stackArray[top];
	   }
	   public boolean isEmpty() {//判断是否为空栈
	      return (top == -1);
	   }
	   public boolean isFull() {//判断是否为已满
	      return (top == maxSize - 1);
	   }
}
