public class InputError {
	
	private String inputString;
	private int Errorcode;
	
	public InputError(String Input){
		inputString=Input;
	}
	
	public void SetInput(String In)
	{
		inputString=In;
	}
	
	public int ErrorCheck()
	{
		int index=0, counter=0, Case=0;
		int Len=inputString.length();
		char A;
		
		while(index<Len)
		{
			A=inputString.charAt(index);
			
			if(index==0)
			{
				if(A!='-'&&A!='('&&OperatorRank(A,0)!=-10)
				{
					Errorcode=1;
					return Errorcode;
				}
				
				if(A=='(')
					counter++;
			}
			else if(index==Len-1)
			{
				if(A!=')'&&OperatorRank(A,0)>0)
				{
					Errorcode=1;
					return Errorcode;
				}
				
				if(A==')')
				{
					counter--;
					if(inputString.charAt(index-1)=='(')
					{
						Errorcode=1;
						return Errorcode;
					}
				}
			}
			else
			{
				if(A=='-'&&inputString.charAt(index-1)!=')'&&OperatorRank(inputString.charAt(index-1),0)!=-10)
					Case=3;
				else
					Case=OperatorRank(inputString.charAt(index),0);
				
				switch(Case)
				{
				case 1:
					
					if((A=='('&&inputString.charAt(index-1)==')')||(A==')'&&inputString.charAt(index-1)=='('))
					{
						Errorcode=1;
						return Errorcode;
					}
					else if(A=='('&&OperatorRank(inputString.charAt(index-1),0)==-10)
					{
						Errorcode=1;
						return Errorcode;
					}
					else if(A=='('&&inputString.charAt(index+1)!='-'&&inputString.charAt(index+1)!='('&&OperatorRank(inputString.charAt(index+1),0)>0)
					{
						Errorcode=1;
						return Errorcode;
					}
					else if(A==')'&&inputString.charAt(index-1)!=')'&&OperatorRank(inputString.charAt(index-1),0)>0)
					{
						Errorcode=1;
						return Errorcode;
					}
					else
					{
						if(A=='(')
							counter++;
						else if(A==')')
							counter--;
					}
						
					break;
				
				case 3:
					
					while(A=='-')
					{
						index++;
						A=inputString.charAt(index);
						
						if(A=='(')
							counter++;
						else if(A==')')
							counter--;
						
						if(index>=inputString.length())
						{
							Errorcode=1;
							return Errorcode;
						}
					}
					
					if(inputString.charAt(index)!='('&&OperatorRank(inputString.charAt(index),0)!=-10)
					{
						Errorcode=1;
						return Errorcode;
					}
					else
					{}
					
					break;
					
				case 2:
				case 4:
				case 5:
					
					if(OperatorRank(inputString.charAt(index-1),0)!=-10&&inputString.charAt(index-1)!=')')
					{
						Errorcode=1;
						return Errorcode;
					}
					else if(OperatorRank(inputString.charAt(index+1),0)!=-10&&inputString.charAt(index+1)!='('&&inputString.charAt(index+1)!='-')
					{
						Errorcode=1;
						return Errorcode;
					}
					else
					{}
					
					break;
					
				default:
					break;
					
				}
			}
			
			index++;
		}
		
		if(counter!=0)
			Errorcode=1;
		
		if(Errorcode!=1)
			return 0;
		else
			return 1;
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
	
	public int SpaceTabCheck()
	{
		int index=0;
		Errorcode=0;
		
		while(index<inputString.length())
		{
			if(index!=0)
			{
				if((inputString.charAt(index)==' '||inputString.charAt(index)=='\t')&&(inputString.charAt(index-1)<='9'&&inputString.charAt(index-1)>='0'))
				{
					while(inputString.charAt(index)==' '||inputString.charAt(index)=='\t')
					{
						index++;
					
						if(index>=inputString.length())
							break;
					}
					
					if(index>=inputString.length())
						break;
				
					if(inputString.charAt(index)>='0'&&inputString.charAt(index)<='9')
					{
						Errorcode=1;
						break;
					}
				}
			
				if(index>=inputString.length())
					break;
			}
			
			index++;
		}
		
		return Errorcode;
	}
	

}
