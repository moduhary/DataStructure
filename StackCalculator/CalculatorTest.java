import java.io.*;
import java.util.*;

public class CalculatorTest
{
	private static int ErrorNumber;
	
	public static void main(String args[])
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		while (true)
		{
			try
			{
				String input = br.readLine();
				if (input.compareTo("q") == 0)
					break;

				command(input);
			}
			catch (Exception e)
			{
				System.out.println("입력이 잘못되었습니다. 오류 : " + e.toString());
				e.printStackTrace();
			}
		}
	}

	private static void command(String input)
	{
		long result=0;
		int Error=0;
		
		InputError Check= new InputError(input);
		Error=Check.SpaceTabCheck();
		
		if(Error==1)
		{
			System.out.println("ERROR");
			return;
		}
		
		input=input.replaceAll(" ", "");
		Check.SetInput(input);
		
		Stack<String> PostFix= new Stack<String>();
		
		Error=Check.ErrorCheck();
		if(Error==1)
		{
			System.out.println("ERROR");
			return;
		}
		input=InfixtoPost(input,PostFix);
		
		result=Caculate(input);
		if(input.endsWith(".")==true)
			input=input.substring(0, input.length()-1);
		input=input.replaceAll("[.]", " ");
		
		if(result==Long.MIN_VALUE&&ErrorNumber==1)
		{
			System.out.println("ERROR");
			return;
		}
		else
		{
			System.out.println(input);
			System.out.println(result);
		}
		
		return;
	}

	private static String InfixtoPost(String IN, Stack<String> Post)
	{
		int index=0, Select=0, counter=0;
		char A=' ';
		String Parsed="";
		String Nested="";
		
		while(index<IN.length())
		{
			A=IN.charAt(index);
			
			if(A>='0'&&A<='9')
			{
				Parsed+=A;
				if(index==IN.length()-1)
					break;
				index++;
			}
			else
			{
				if(Parsed=="")
				{}
				else
				{	
					if(Parsed.endsWith(".")==true)
					{}
					else
						Parsed+='.';
				}
				
				if(A=='-'&&index==0)
					Select=1;
				else if(A=='-'&&OperatorRank(IN.charAt(index-1),0)%10!=0&&IN.charAt(index-1)!=')')
					Select=1;
				else
					Select=0;
				
				if(A=='(')
				{
					Nested="";
					counter=0;
					counter++;
					
					index++;
					while(index<IN.length())
					{
						A=IN.charAt(index);
						if(A=='(')
						{
							counter++;
						}
						if(A==')')
						{
							counter--;

							if(counter==0)
								break;
						}
						Nested+=A;
						index++;
					}
					
					Nested=InfixtoPost(Nested,new Stack<String>());
					Parsed+=Nested;
					index++;
				}
				else if(A=='-'&&Select==1)
				{
					Nested="";
					counter=0;
					Post.push("~");
					index++;
					
					if(index>=IN.length())
						break;
					
					A=IN.charAt(index);
					
					while(OperatorRank(A,Select)<=3||counter>0)
					{
						Nested+=A;
						
						if(A=='(')
						{
							counter++;
						}
						if(A==')')
						{
							counter--;
						}
						
						index++;
						
						if(index>=IN.length())
							break;
						
						A=IN.charAt(index);
					
						if(counter==0)
						{	
							if(OperatorRank(A,Select)>3)
							{	
								Select=0;
								break;
							}
							else if(A=='-'&&IN.charAt(index-1)!=')'&&OperatorRank(IN.charAt(index-1),0)>0)
								continue;
							else
							{
								Select=0;
								continue;
							}
						}
						else
						{
							if(index>=IN.length())
								break;
							else
								continue;
						}
					}
						
					Nested=InfixtoPost(Nested,new Stack<String>());
					Parsed+=Nested;
						
					if(Parsed.endsWith(".")==true)
					{}
					else if(Parsed=="")
					{}
					else
						Parsed+='.';
					
					Parsed+=Post.pop();
					Parsed+='.';
				}
				else if(A=='^')
				{
					Nested="";
					counter=0;
					Post.push("^");
					index++;
					
					if(index>=IN.length())
						break;
					
					A=IN.charAt(index);
					
					while(OperatorRank(A,Select)<=2||counter>0)
					{
						Nested+=A;
						
						if(A=='(')
						{
							counter++;
						}
						if(A==')')
						{
							counter--;
							if(counter==0)
								break;
						}
						
						index++;
						
						if(index>=IN.length())
							break;
						
						A=IN.charAt(index);
						
						if(counter==0)
						{
							if(index>=IN.length())
								break;
							else if(OperatorRank(A,Select)>2)
								break;
							else
								continue;
						}
						else
							if(index>=IN.length())
								break;
							else
								continue;
					}
					
					Nested=InfixtoPost(Nested,new Stack<String>());
					Parsed+=Nested;
					
					if(Parsed.endsWith(".")==true)
					{}
					else if(Parsed=="")
					{}
					else
						Parsed+='.';
					
					Parsed+=Post.pop();
					Parsed+='.';
						
				}
				else
				{
					if(IN.charAt(index)==')')
					{
						index++;
						continue;
					}
					
					if(Post.isEmpty()==true)
					{
						Post.push(String.valueOf(A));
					}
					else
					{	
						if(OperatorRank(A,Select)<OperatorRank(Post.peek().charAt(0),0))
						{
							Post.push(String.valueOf(A));
						}
						else
						{
							while(OperatorRank(Post.peek().charAt(0),0)<=OperatorRank(A,Select))
							{
								Parsed+=Post.pop();
								Parsed+=".";
								if(Post.isEmpty()==true)
									break;
							}

							Post.push(String.valueOf(A));
						}
					}
					
					index++;
					
					if(Parsed.endsWith(".")==true)
					{}
					else if(Parsed=="")
					{}
					else
						Parsed+='.';
				}
			}
		}
		
		while(Post.isEmpty()==false)
		{
			if(Parsed.endsWith(".")==true)
			{}
			else
				Parsed+='.';
			Parsed+=Post.pop();
		}
		
		return Parsed;
	}
	
	private static int OperatorRank(char op,int Mode)
	{
		if(op=='('||op==')')
			return 1;
		else if(op=='^')
			return 2;
		else if(op=='-'&&Mode==1)
			return 3;
		else if(op=='~')
			return 3;
		else if(op=='*'||op=='/'||op=='%')
			return 4;
		else if(op=='+'||(op=='-'&&Mode==0))
			return 5;
		else if(op=='.')
			return 100;
		else if(op>='0'&&op<='9')
			return -10;
		else 
			return 1000;
	}
	
	private static long Caculate(String input) {
		
		int index=0, opvalue=0, errorcode=0;
		Long Arg=(long)0;
		Long A;
		Long B;
		Stack<Long> Expression=new Stack<Long>();
		String Number="";
		
		while(index<input.length())
		{	
			opvalue=OperatorRank(input.charAt(index),0);
			
			if(opvalue==-10)
			{
				Number="";
				
				while(true)
				{
					if(index>=input.length())
						break;
					opvalue=OperatorRank(input.charAt(index),0);
					if(opvalue<0)
					{
						Number+=input.charAt(index);
						index++;
					}
					else
						break;
				}
				
				Arg=Long.valueOf(Number);
				Expression.push(Arg);
			}
			else if(opvalue>=2&&opvalue<=5)
			{
				switch(opvalue)
				{
				case 2:
					A=Expression.pop();
					B=Expression.pop();
					if(A<0&&B==0)
					{
						errorcode=1;
						break;
					}
					Arg=(long)Math.pow(B,A);
					Expression.push(Arg);
					break;
					
				case 3:
					A=Expression.pop();
					Arg=-A;					
					Expression.push(Arg);
					break;
					
				case 4:
					A=Expression.pop();
					B=Expression.pop();
					
					switch(input.charAt(index))
					{
					case '*':
						Arg=A*B;
						break;
						
					case '/':
						if(A==0)
						{
							errorcode=1;
							break;
						}
						Arg=B/A;
						break;
						
					case '%':
						if(A==0)
						{
							errorcode=1;
							break;
						}
						Arg=B%A;
						break;
						
					default:
						Arg=(long)0;
						errorcode=1;
						break;
					}
					
					Expression.push(Arg);
					break;
					
				case 5:
					A=Expression.pop();
					B=Expression.pop();
					switch(input.charAt(index))
					{
					case '+':
						Arg=(long)(A+B);
						break;
						
					case '-':	
						Arg=(long)(B-A);
						break;
					
					default:
						Arg=(long)0;
						errorcode=1;
						break;
					}
					Expression.push(Arg);
					break;
				}
			}
			else
			{
				if(input.charAt(index)!='.')
					errorcode=1;
			}
			
			if(errorcode==1)
				break;
			
			index++;
			continue;
		}
		
		if(errorcode==1)
		{
			ErrorNumber=1;
			return Long.MIN_VALUE;
		}
		else if(errorcode==0&&Expression.peek()==Long.MIN_VALUE)
		{
			ErrorNumber=0;
			return Expression.pop();
		}
		else
			return Expression.pop();
	}
}