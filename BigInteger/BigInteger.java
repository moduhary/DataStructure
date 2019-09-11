
	import java.io.BufferedReader;
	import java.io.InputStreamReader;
	import java.util.regex.Matcher;
	import java.util.regex.Pattern;
	
	public class BigInteger{
	    public static final String QUIT_COMMAND = "quit";
	    public static final String MSG_INVALID_INPUT = "This input cannot be calculated!";
	 
	    // implement this
	    public static final Pattern EXPRESSION_PATTERN = Pattern.compile("^([+-]?)(\\d{1,100})([*+-]?)([+-]?)(\\d{1,100})$");
	 
	    private char sign;
	    private int [] value;
	    
	    private int Compare(BigInteger a, BigInteger b)
	    {
	    	int i, Comp=0;
	    	for(i=0;i<100;i++)
	    	{
	    		if(a.value[i]>b.value[i])
	    		{
	    			Comp=1;
	    			break;
	    		}
	    		else if(a.value[i]<b.value[i])
	    		{
	    			Comp=-1;
	    			break;
	    		}
	    		else
	    		{
	    			if(i==99)
	    			{
	    				Comp=0;
	    				break;
	    			}
	    			else
	    				continue;
	    		}
	    	}
	    	return Comp; 	
	    }
	    
	    public void SetVal(int [] Val)
	    {
	    	this.value=Val;
	    }
	    
	    public void SetSign(char signal)
	    {
	    	this.sign=signal;
	    }
	    
	    public BigInteger(String s,String signal,int result)
	    {
	    	int index=0;
	    	char sign;
	    	char [] num= new char[100];
	    	char [] numR= new char[201];
	    	if(signal!=null&&signal.length()!=0)
	    		sign=signal.charAt(0);
	    	else
	    		sign='+';
	    	
	    	if(result==0)
	    	{
	    		value = new int[100];
	    		
	    		num=s.toCharArray();
	    	
	    		for(index=0;index<num.length;index++)
	    		{
	    			if(num[index]!=0)
	    				value[100-num.length+index]=(int)num[index]-48;
	    			else
	    			{}
	    		}
	    	}
	    	else
	    	{
	    		value = new int[201];
	    		
	    		numR=s.toCharArray();
	    		
	    		for(index=0;index<numR.length;index++)
	    		{
	    			if(numR[index]!=0)
	    				value[200-numR.length+index]=(int)numR[index]-48;
	    			else
	    			{}
	    		}
	    	}

	    	this.SetSign(sign);
	    	this.SetVal(value);
	    	
	    	return;
	    }
	 
	    public BigInteger add(BigInteger big)
	    {	
	    	int i=0,addResult=0,Carry=0;
	    	
	    	BigInteger Return= new BigInteger("0","+",1);
	    	
	    	for(i=99;i>=0;i--)
	    	{
	    		addResult=this.value[i]+big.value[i]+Carry;
	    		
	    		if(addResult>=10)
	    		{
	    			Return.value[101+i]=addResult-10;
	    			Carry=1;	    			
	    		}
	    		else
	    		{
	    			Return.value[101+i]=addResult;
	    			Carry=0;
	    		}
	    	}
	    	
	    	if(Carry==1)
	    		Return.value[100]=1;
	    	
	    	return Return; 	
	    }
	 
	    public BigInteger subtract(BigInteger big,int Order)
	    {
	    	int i=0,subtractResult=0,Carry=0;
	    	
	    	BigInteger Return= new BigInteger("0","+",1);
	    	
	    	if(Order==1)
	    	{
	    		for(i=99;i>=0;i--)
	    		{
	    			subtractResult=this.value[i]-big.value[i]-Carry;
	    		
	    			if(subtractResult<0)
	    			{
	    				Return.value[101+i]=subtractResult+10;
	    				Carry=1;	    			
	    			}
	    			else
	    			{
	    				Return.value[101+i]=subtractResult;
	    				Carry=0;
	    			}
	    		}
	    	}
	    	else
	    	{	
	    		for(i=99;i>=0;i--)
	    		{
	    			subtractResult=big.value[i]-this.value[i]-Carry;
	    		
	    			if(subtractResult<0)
	    			{
	    				Return.value[101+i]=subtractResult+10;
	    				Carry=1;	    			
	    			}
	    			else
	    			{
	    				Return.value[101+i]=subtractResult;
	    				Carry=0;
	    			}
	    		}
	    	}
	    	
	    	return Return; 	
	    }
	 
	    private BigInteger AddOrSubtract(BigInteger n2, char operator)
	    {
	    	int CompResult= this.Compare(this, n2);
	    	
	    	BigInteger Return= new BigInteger("0","+",1);
	    	
	    	if(this.sign=='+')
	    	{
	    		if(n2.sign=='+')
	    		{
	    			if(operator=='+')
	    			{
	    				Return=this.add(n2);
	    			}
	    			else
	    			{
	    				switch(CompResult){
	    				case 1:
	    					Return=this.subtract(n2,1);
	    					Return.SetSign('+');
	    					break;
	    				
	    				case -1:
	    					Return=this.subtract(n2,0);
	    					Return.SetSign('-');
	    					break;
	    					
						case 0:
							Return=this.subtract(n2,1);
							Return.SetSign('+');
							break;

	    				default:
	    					break;
	    				}
	    					
	    			}
	    		}
	    		else
	    		{
	    			if(operator=='+')
	    			{
	    				switch(CompResult){
	    				case 1:
	    					Return=this.subtract(n2,1);
	    					Return.SetSign('+');
	    					break;
	    					
	    				case -1:
	    					Return=this.subtract(n2,0);
	    					Return.SetSign('-');
	    					break;

						case 0:
							Return=this.subtract(n2,1);
							Return.SetSign('+');
							break;
	    					
	    				default:
	    					break;
	    				}
	    						
	    			}
	    			else
	    			{
	    				Return=this.add(n2);
	    			}
	    		}
	    	}
	    	else
	    	{
	    		if(n2.sign=='+')
	    		{
	    			if(operator=='+')
	    			{
	    				switch(CompResult){
	    				case 1:
	    					Return=this.subtract(n2,1);
	    					Return.SetSign('-');
	    					break;
	    				
	    				case -1:
	    					Return=this.subtract(n2,0);
	    					Return.SetSign('+');
	    					break;
	    					
						case 0:
							Return=this.subtract(n2,1);
							Return.SetSign('+');
							break;

	    				default:
	    					break;
	    				}
	    			}
	    			else
	    			{
	    				Return=this.add(n2);
						if(Return.IsZero()==1)
							Return.SetSign('+');
						else
	    					Return.SetSign('-');
	    			}
	    		}
	    		else
	    		{
	    			if(operator=='+')
	    			{
	    				Return=this.add(n2);
	    				if(Return.IsZero()==1)
							Return.SetSign('+');
						else
							Return.SetSign('-');
	    			}
	    			else
	    			{
	    				switch(CompResult){	
	    				case 1:
	    					Return=this.subtract(n2,1);
	    					Return.SetSign('-');
	    					break;
    					
	    				case -1:
	    					Return=this.subtract(n2,0);
	    					Return.SetSign('+');
	    					break;
    					
						case 0:
							Return=this.subtract(n2,1);
							Return.SetSign('+');
							break;

	    				default:
	    					break;
	    				}
    				}
	    		}
	    	}
	    	return Return;
	    }
	    
	    
	    public BigInteger multiply(BigInteger big)
	    {
	    	BigInteger Result= new BigInteger("0","+",1);
	    	
	    	int index1=0,index2=0,index3=0,carry=0,multiplyResult=0;
	    	
	    	for(index1=99;index1>=0;index1--)
	    	{
	    		for(index2=99;index2>=0;index2--)
	    		{
					multiplyResult=this.value[index1]*big.value[index2];
					Result.value[index1+index2+2]+=multiplyResult;
	    		}
	    	}

			for(index3=200;index3>=0;index3--)
			{
				Result.value[index3]+=carry;
				carry=Result.value[index3]/10;
				Result.value[index3]%=10;
			}

	    	
	    	if(carry>0)
	    		Result.value[0]=carry;
	    	
	    	if(this.sign=='-'&&big.sign=='+')
	    		Result.SetSign('-');
	    	else if(this.sign=='+'&&big.sign=='-')
	    		Result.SetSign('-');
	    	else
	    	{}
	    	
	    	return Result;
	    }
	 
	    
	    public String toString()
	    {
	    	String translate= "";
	    	
	    	int index=0,Valid=0;
	    	
	    	for(index=0;index<=200;index++)
	    	{
	    		if(Valid==0&&this.value[index]!=0)
	    		{
	    			translate+=(char)(this.value[index]+48);
	    			Valid=1;
	    		}
	    		else if(Valid==0&&this.value[index]==0)
	    		{
	    			if(index==200)
					{
						translate+=(char)48;
						this.SetSign('+');
					}
					continue;
	    		}
	    		else
	    			translate+=(char)(this.value[index]+48);
	    	}
	    	
	    	return translate;
	    }
	    
	 
	    static BigInteger evaluate(String input) throws IllegalArgumentException
	    {
	        int index=0;
	    	
	        char operator;
	        
	    	input=input.replaceAll("\\s", "");
	        
	        Matcher m= BigInteger.EXPRESSION_PATTERN.matcher(input);
	        
	        String [] expression = new String[5];
	        while( m.find())
	        {
	        	for(index=0;index<5;index++)
	        	{
	        		expression[index]=m.group(index+1);
	        	}
	        }
	        
	        operator=expression[2].charAt(0);
	        
	        BigInteger operand1= new BigInteger(expression[1],expression[0],0);
	        BigInteger operand2= new BigInteger(expression[4],expression[3],0);
	        
	        BigInteger Result= new BigInteger("0","+",1);
	        
	        if(operator=='+'||operator=='-')
	        {
	        	Result=operand1.AddOrSubtract(operand2,operator);
	        }
	        else
	        {
	        	Result=operand1.multiply(operand2);
	        }
	        
	        
	    	// implement here
	        // parse input
	        // using regex is allowed
	 
	        // One possible implementation
	        // BigInteger num1 = new BigInteger(arg1);
	        // BigInteger num2 = new BigInteger(arg2);
	        // BigInteger result = num1.add(num2);
	        // return result;
	        
	        return Result;
	    }
	 
	    public static void main(String[] args) throws Exception
	    {
	        try (InputStreamReader isr = new InputStreamReader(System.in))
	        {
	            try (BufferedReader reader = new BufferedReader(isr))
	            {
	                boolean done = false;
	                while (!done)
	                {
	                    String input = reader.readLine();
	 
	                    try
	                    {
	                        done = processInput(input);
	                    }
	                    catch (IllegalArgumentException e)
	                    {
	                    	e.printStackTrace();
	                        System.err.println(MSG_INVALID_INPUT);
	                    }
	                }
	            }
	        }
	    }
	 
	    static boolean processInput(String input) throws IllegalArgumentException
	    {
	        boolean quit = isQuitCmd(input);
			int index=0;
	 
	        if (quit)
	        {
	            return true;
	        }
	        else
	        {
	            BigInteger result = evaluate(input);
	            if(result.sign=='+')
	            {
	            	System.out.println(result.toString());
	            }
	            else
				{
					System.out.println(result.sign+result.toString());
	            }
	 
	            return false;
	        }
	    }

		public int IsZero()
		{
			int index=0,iszero=0;

			for(index=0;index<=200;index++)
			{
				if(this.value[index]!=0)
				{
					iszero=0;
					break;
				}
				else
				{
					if(index==200)
					{
						iszero=1;
						break;
					}
					else
						continue;
				}
			}

			return iszero;
		}
	 
	    static boolean isQuitCmd(String input)
	    {
	        return input.equalsIgnoreCase(QUIT_COMMAND);
	    }
}
