
public class AVLtree<T> {
	
	private AVLtreeNodes<T> Head;
	
	public AVLtree()
	{
		Head= new AVLtreeNodes<T>();
	}
	
	public void Insert(T A, int[] Pos)
	{
		if(Head.getLChild()==null)
			Head.SetLChild(new AVLtreeNodes<T>(this.Head));
		
		Head.getLChild().Insert(A, Pos);
	}
	
	public void Delete(T D)
	{
		Head.getLChild().Delete(D);
	}
	
	public T Search(T S)
	{
		return Head.getLChild().Search(S);
	}
	
	public MyLinkedList<int[]> Search(T S,int a)
	{
		return Head.getLChild().Search(S, a);
	}
	
	public AVLtreeNodes<T> getFirstItem()
	{
		return Head.getLChild();
	}
	
	public AVLtreeNodes<T> getHead()
	{
		return Head;
	}
	
}

