
import java.io.*;
import java.util.ArrayList;

public class Matching
{
	public static void main(String args[])
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		ArrayList<String> data= new ArrayList<String>();
		HashTable<String> fstring= new HashTable<String>();

		while (true)
		{
			try
			{
				String input = br.readLine();
				if (input.compareTo("QUIT") == 0)
					break;

				command(input,data,fstring);
			}
			catch (IOException e)
			{
				System.out.println("입력이 잘못되었습니다. 오류 : " + e.toString());
			}
		}
	}

	private static void command(String input, ArrayList<String> data, HashTable<String> fstring)
	{	
		if(input.substring(1,2).compareTo(" ")!=0)
		{
			System.out.println("Wrong command format!");
			return;
		}
		
		String[] In=new String[2];
		
		In[0]=input.substring(0, 1);
		In[1]=input.substring(2);
		
		if(In[0].compareTo("<")==0)
		{
			InputCmd(In[1],data,fstring);
		}
		else if(In[0].compareTo("@")==0)
		{
			PrintCmd(In[1],fstring);
		}
		else if(In[0].compareTo("?")==0)
		{
			PatternCmd(In[1],data,fstring);
		}
		else
			System.out.println("This command is not exist!");
	}
	
	private static void InputCmd(String input,ArrayList<String> data, HashTable<String> fstring)
	{
		FileReader f=null;
		BufferedReader R=null;
		String In="",hash="";
		int index=0, index2=0;
		int[] Pos=new int[2];
		
		try
		{
			f=new FileReader(input);
		}
		catch(FileNotFoundException ddd)
		{
			System.out.println("The file doesn't exist!");
			return;
		}
		
		R=new BufferedReader(f);
		
		while(true)
		{
				
			try {
				In=R.readLine();
				
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			if(In==""||In==null)
				break;
			
			data.add(In);
			In="";
		}
		
		try {
			f.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		for(index=0;index<data.size();index++)
		{
			Pos[0]=index+1;
			
			for(index2=0;index2<data.get(index).length()-5;index2++)
			{
				hash=data.get(index).substring(index2, index2+6);
				
				Pos[1]=index2+1;
				
				int[] now=new int[2];
				now[0]=Pos[0];
				now[1]=Pos[1];
				
				fstring.Save(hash, now);
				
				Pos[1]=0;
			}
		}
	}
	
	private static void PrintCmd(String input, HashTable<String> fstring)
	{
		int Key=Integer.parseInt(input);
		AVLtree<String> Strings= fstring.Search(Key);
		
		if(Strings!=null)
		{
			Print(Strings.getFirstItem());
			System.out.println("");
		}
		else
		{
			System.out.println("EMPTY");
			return;
		}
	}
	
	private static void PatternCmd(String input,ArrayList<String> data,HashTable<String> fstring)
	{
		int len=input.length();
		int Result=0;
		int[] tempPos=new int[2];
		Node<int[]> Iter=null;
		AVLtree<String> find=null;
		MyLinkedList<int[]> poslist=null;
		MyLinkedList<int[]> findlist=null;
		String compare="";
		String Part=input.substring(0,6);
		
		find=fstring.Search(fstring.AddressCal(Part));
		
		if(find!=null)
		{
			poslist=find.Search(Part,0);
		}
		else
		{
			System.out.println("EMPTY");
			return;
		}
		
		if(len==6)
		{
			Print(poslist);
		}
		else
		{
			findlist=new MyLinkedList<int[]>();
			if(poslist==null)
			{
				System.out.println("EMPTY");
				return;
			}
			
			Iter=poslist.head;
			
			while(Iter.getNext()!=null)
			{
				Iter=Iter.getNext();
				tempPos=Iter.getItem();
				
				if(tempPos[1]+len-2<data.get(tempPos[0]-1).length())
					compare=data.get(tempPos[0]-1).substring(tempPos[1]-1, tempPos[1]+len-1);
				
				Result=input.compareTo(compare);
				
				if(Result==0)
					findlist.add(tempPos);
				else
				{}
				
				compare="";
			}
			
			Print(findlist);
		}
		
		System.out.println("");
	}
	
	private static void Print(AVLtreeNodes<String> All)
	{
		if(All==null)
		{
			System.out.println("EMPTY");
			return;
		}
		else
		{
			All.Print();
		}
		
		if(All.peekNodes(1)==false)
		{}
		else
		{
			System.out.printf(" ");
			Print(All.getLChild());
		}
		
		if(All.peekNodes(2)==false)
		{}
		else
		{
			System.out.printf(" ");
			Print(All.getRChild());
		}
		
		return;
	}
	
	private static void Print(MyLinkedList<int[]> All)
	{
		Node<int[]> curr=null;
		
		if(All==null)
		{
			System.out.println("EMPTY");
			return;
		}
		if(All.head.getNext()==null)
		{
			System.out.println("EMPTY");
			return;
		}
		else
		{
			curr=All.head;
		}
		
		while(curr.getNext()!=null)
		{
			curr=curr.getNext();
			System.out.printf("(%d, %d)",curr.getItem()[0],curr.getItem()[1]);
			if(curr.getNext()==null)
				break;
			System.out.printf(" ");
		}
	}
}

