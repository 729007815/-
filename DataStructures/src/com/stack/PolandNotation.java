package com.stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class PolandNotation {
	public static void main(String[] args) {
		//逆波兰表达式(后缀表达)
		//（3+4）*5-6 =》 3 4 + 5 * 6 -
		
		//完成将一个 中缀表达式转成后缀表达式的功能
		//说明
		//1.1+((2+3)*4)-5=> 转成123+4x+5-
		//2.因为直接对str 进行操作，不方便，因此先将"1+((2+3)*4)-5" =》中经的表达式对应的List
		// 即"1+((2+3)*4)-5” => ArrayList [1,+,(,(,2,+,3,),*,4,),-,5]
		//3. 将得到的中缀表达式对应的list 转化成后缀表达式对应的list
		//即ArrayList [1,+,(,(,2,+,3,),*,4,),-,5] =》ArrayList [1,2,3,+,4,*,+,5,-]
		String expression = "1+((2+3)*4)-5";
		List<String> rpnList = expressionList(expression);
		List<String> pareSuffixE = parseSuffixList(rpnList);
		System.out.println(pareSuffixE);
		System.out.println(calculate(pareSuffixE));
		//为了说明方便 逆波兰表达式数字与运算符用空格隔开
//		String suffixExpression = "30 4 + 5 * 6 -";
//		String suffixExpression = "4 5 * 8 - 60 + 8 2 / +";
		//1.先将3 4 + 5 * 6 - 放到ArrayList中
		//2. 将ArrayList传递给一个方法 遍历ArrayList 配合栈完成计算
//		List<String> rpnList = getListString(suffixExpression);
//		System.out.println(rpnList);
//		int res = calculate(rpnList);
//		System.out.println(res);
	}
	
	//方法： 将中缀表达式改成对应的List
	public static List<String> expressionList(String expression){		
		List<String> ls = new ArrayList<String>();
		int i = 0;//指针 用于遍历
		String str;//对多位数的拼接工作
		char c;//每遍历到一个字符 直接放入
		do {//如果c是一个非数字 加入到ls中
			if((c=expression.charAt(i)) < 48 ||(c=expression.charAt(i)) >57 ) {
				ls.add(""+c);
				i++;
			}else {
				//如果是一个数 需要考虑多位数
				str = "";//先将str置成""
				while(i<expression.length() &&(c=expression.charAt(i)) >= 48 && (c=expression.charAt(i)) <= 57 ) {
					str += c;//拼接
					i++;
				}
				ls.add(str);
			}
			
		}while(i<expression.length());
		return ls;
	}
	
	//将中缀转化成后缀
	public static List<String> parseSuffixList(List<String> ls){
		//定义两个栈
		Stack<String> s1 = new Stack<String>();//符号栈
		//Stack<String> s2 = new Stack<String>();//存放后缀表达式
		//s2在整个转化过程中没有pop操作 最后不需要逆序输出 所以不用栈 用List<String>
		List<String> s2  = new ArrayList<String>();
		for(String item : ls) {
			//如果是数加入s2
			if(item.matches("\\d+")) {
				s2.add(item);
			}else if(item.equals("(")) {
				s1.push(item);
			}else if(item.equals(")")) {
				while(!s1.peek().equals("(")) {
					s2.add(s1.pop());
				}
				s1.pop();//将小括号弹出 （消除括号）
			}else {
				//当s1栈顶的运算符优先级大于item的优先级 则取出加入s2
				while(s1.size() != 0  && Operation.getValues(s1.peek()) >= Operation.getValues(item)) {
					s2.add(s1.pop());
				}
				//还需要将item压入栈
				s1.push(item);
			}
		}
		//将s1中剩余的运算符依次加入s2
		while(s1.size() != 0) {
			s2.add(s1.pop());
		}
		return s2;//按顺序输出就是对应的后缀表达式
	}
	
	//一个逆波兰表达式 一次将数据和运算符放入到ArrayList中
	public static List<String> getListString(String suffixExpression){
		String[] split = suffixExpression.split(" ");
		List<String> list = new ArrayList<String>();
		for(String ele : split) {
			list.add(ele);
			
		}
		return list;
	}
	//完成对逆波兰表达式的运算
//	1)从左至右扫描，将3和4压入堆栈;
//	2)遇到+运算符，因此弹出4和3 (4为栈项元素，3为次项元素)，计算出3+4的值，得7,再将7入楼;
//	3)将5入栈;
//	4)接下来是x运算符，因此弹出5和7,计算出7x5=35,将35入栈;
//	5)将6入栈;
//	6)最后是-运算符，计算出35-6的值，即29,由此得出最终结果
	public static int calculate(List<String> ls) {
		//只需要一个栈
		Stack<String> stack = new Stack<String>();
		for(String item : ls) {
			//使用正则表达式取出数
			if(item.matches("\\d+")) {//匹配的是多位数
				//入栈
				stack.push(item);
			}else {
				//是运算符时 pop出两个数进行运算再入栈
				int num2 = Integer.parseInt(stack.pop());
				int num1 = Integer.parseInt(stack.pop());
				int res = 0;//结果
				if(item.equals("+")) {
					res = num1 + num2;
				}else if(item.equals("-")) {
					res = num1 - num2;//注意顺序
				}else if(item.equals("*")) {
					res = num1 * num2;//注意顺序
				}else if(item.equals("/")) {
					res = num1 / num2;//注意顺序
				}else {
					throw new RuntimeException("运算符错误");
				}
				//把结果res入栈
				stack.push("" + res);//简易int转String
			}
		
		}
		return Integer.parseInt(stack.pop());
	}
}
//优先级
class Operation{
	private static int add = 1;
	private static int sub = 1;
	private static int mul = 2;
	private static int div = 2;
	public static int getValues(String oper) {
		int result = 0;
		switch(oper) {
		case "+":
			result = add; 
			break;
		case "":
			result = sub; 
			break;
		case "*":
			result = mul;
			break;
		case "/":
			result = div;
			break;
		default:
			
			break;
		}
		return result;
	}
}
