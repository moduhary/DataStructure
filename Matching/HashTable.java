
public class HashTable<T> {
	
	private AVLtree<T>[] Table;
	
	@SuppressWarnings("unchecked")
	public HashTable()
	{
		Table=new AVLtree[100];
	}
	
	public void Save(T A, int[] Pos)
	{
		int Hash=0;
		
		Hash=AddressCal(A.toString());
		
		if(Table[Hash]==null)
			Table[Hash]=new AVLtree<T>();
			
		Table[Hash].Insert(A, Pos);
	}
	
	public int AddressCal(String A)
	{
		int index=0,rawAdr=0;
		
		for(index=0;index<A.length();index++)
			rawAdr+=(int)A.charAt(index);
		
		rawAdr%=100;
		
		return rawAdr;
	}
	
	public AVLtree<T> Search(int A)
	{
		A=A%100;
		
		return Table[A];
	}
	
}
