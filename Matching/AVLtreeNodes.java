
public class AVLtreeNodes<T> {

	private AVLtreeNodes<T> parent;
	private AVLtreeNodes<T> leftChild;
	private AVLtreeNodes<T> rightChild;
	
	private MyLinkedList<int[]> Substrings;
	private T Key;
	private int Rfactor;
	private int height;
	
	public AVLtreeNodes()
	{
		parent=null;
		leftChild=null;
		rightChild=null;
		height=0;
		Substrings=new MyLinkedList<int[]>();
	}
	
	public AVLtreeNodes(AVLtreeNodes<T> Parent)
	{
		parent=Parent;
		leftChild=null;
		rightChild=null;
		height=Parent.height+1;
		Substrings=new MyLinkedList<int[]>();
	}
	
	public void Insert(T A, int[] Pos)
	{
		if(leftChild==null&&rightChild==null&&Key==null)
		{
			Substrings.add(Pos);
			Key=A;
			Rfactor=0;
		}
		else
		{
			if(A.toString().compareTo(Key.toString())==0)
			{
				Substrings.add(Pos);
			}
			else if(A.toString().compareTo(Key.toString())>0)
			{
				if(rightChild==null)
					rightChild=new AVLtreeNodes<T>(this);
				
				rightChild.Insert(A,Pos);
				
				Rotate();
			}
			else
			{
				if(leftChild==null)
					leftChild=new AVLtreeNodes<T>(this);
				
				leftChild.Insert(A, Pos);
				
				Rotate();
			}
		}
	}
	
	public void Delete(T D)
	{
		if(parent==null&&leftChild==null&&rightChild==null)
		{
			System.out.println("Can't delete because AVLtree is Empty!");
			return;
		}
		else if(parent!=null&&leftChild==null&&rightChild==null)
		{
			if(Key==null)
			{
				System.out.println("The Key cannot be found!");
				return;
			}
			else
			{
				if(D.toString().compareTo(Key.toString())==0)
				{
					if(this==parent.leftChild)
					{
						Substrings.removeAll();
						parent.SetLChild(null);
					}
					else
					{
						Substrings.removeAll();
						parent.SetRChild(null);
					}
				}
				else
				{
					System.out.println("The Key cannot be found!");
					return;
				}
			}
		}
		else
		{
			if(D.toString().compareTo(Key.toString())==0)
			{
				if(leftChild.Key.toString().length()>=rightChild.Key.toString().length())
				{
					leftChild.SetRChild(rightChild);
					rightChild.SetParent(leftChild);
					leftChild.height-=1;
					SetRChild(null);
					
					if(parent.leftChild==this)
					{
						parent.SetLChild(leftChild);
						leftChild.SetParent(parent);
					}
					else
					{
						parent.SetRChild(leftChild);
						leftChild.SetParent(parent);
					}
				}
				else
				{
					rightChild.SetRChild(leftChild);
					leftChild.SetParent(rightChild);
					rightChild.height-=1;
					SetRChild(null);
					
					if(parent.leftChild==this)
					{
						parent.SetLChild(rightChild);
						rightChild.SetParent(parent);
					}
					else
					{
						parent.SetRChild(rightChild);
						rightChild.SetParent(parent);
					}
				}
			}
			else if(D.toString().compareTo(Key.toString())>0)
			{
				rightChild.Delete(D);
				Rotate();
			}
			else
			{
				leftChild.Delete(D);
				Rotate();
			}
		}
	}
	
	public T Search(T S)
	{
		if(S.toString().compareTo(this.Key.toString())==0)
		{
			return this.Key;
		}
		else
		{
			if(S.toString().compareTo(this.Key.toString())>0)
				return this.rightChild.Search(S);
			else
				return this.leftChild.Search(S);
		}
	}
	
	public MyLinkedList<int[]> Search(T S,int a)
	{
		if(S.toString().compareTo(this.Key.toString())==0)
		{
			return this.Substrings;
		}
		else
		{
			if(S.toString().compareTo(this.Key.toString())>0)
			{
				if(this.rightChild==null)
				{
					return null;
				}
				else
				{
					return this.rightChild.Search(S,0);
				}
			}
			else
			{
				if(this.leftChild==null)
				{
					return null;
				}
				else
				{
					return this.leftChild.Search(S,0);
				}
			}
		}
	}
	
	public void Print()
	{
		System.out.printf("%s",this.Key);
	}
	
	private void Rotate()
	{
		if(this.leftChild==null&&this.rightChild==null)
			return;
		
		int left=0,right=0;
		
		if(this.leftChild!=null)
			left=Height(this.leftChild)+1;
		else
			left=1;
		
		if(this.rightChild!=null)
			right=Height(this.rightChild)+1;
		else
			right=1;
		
		this.Rfactor=left-right;
		
		if(Rfactor>=2)
		{
			if(this.leftChild.Rfactor==1)
			{
				Rotateright(this);
			}
			else if(this.leftChild.Rfactor==-1)
			{
				Rotateleft(this.leftChild);
				Rotateright(this);
			}
		}
		else if(Rfactor<=-2)
		{
			if(this.rightChild.Rfactor==-1)
			{
				Rotateleft(this);
			}
			else if(this.rightChild.Rfactor==1)
			{
				Rotateright(this.rightChild);
				Rotateleft(this);
			}
		}
		else
			return;
	}
	
	public void SetParent(AVLtreeNodes<T> P)
	{
		parent=P;
	}
	
	public void SetLChild(AVLtreeNodes<T> L)
	{
		leftChild=L;
	}
	
	public void SetRChild(AVLtreeNodes<T> R)
	{
		rightChild=R;
	}
	
	public AVLtreeNodes<T> getParent()
	{
		if(this.parent!=null)
			return this.parent;
		else
			return null;
	}
	
	public AVLtreeNodes<T> getLChild()
	{
		if(this.leftChild!=null)
			return this.leftChild;
		else
			return null;
	}
	
	public AVLtreeNodes<T> getRChild()
	{
		if(this.rightChild!=null)
			return this.rightChild;
		else
			return null;
	}
	
	public boolean peekNodes(int a)
	{
		switch(a)
		{
		case 0:
			
			if(this.parent==null)
				return false;
			else
				return true;
			
		case 1:
			if(this.leftChild==null)
				return false;
			else
				return true;
			
		case 2:
			if(this.rightChild==null)
				return false;
			else
				return true;
			
		default:
			
			System.out.println("Illegal choice!");
			return false;
			
		}
	}
	
	private int Height(AVLtreeNodes<T> Me)
	{	
		int left=0,right=0;
		
		if(Me==null)
			return 0;
		else
		{
			if(Me.leftChild!=null)
				left=Height(Me.leftChild)+1;
			else
			{
				left=1;
			}
			
			if(Me.rightChild!=null)
				right=Height(Me.rightChild)+1;
			else
			{
				right=1;
			}
			
			
			if(left>right)
				return left;
			else
				return right;
		}
	}
	
	private void Rotateleft(AVLtreeNodes<T> Pivot)
	{
		AVLtreeNodes<T> Temp=new AVLtreeNodes<T>();
		Temp.Copy(Pivot);
		
		AVLtreeNodes<T> Temp2=null;
		
		if(Pivot.rightChild.leftChild!=null)
			Temp2=Pivot.rightChild.leftChild;
		
		Pivot.rightChild.SetLChild(Temp);
		Temp.SetParent(Pivot.rightChild);
		if(Temp2!=null)
		{
			Temp2.SetParent(Temp);
			Temp.SetLChild(Temp2);
		}
		
		if(Pivot.parent.leftChild==Pivot)
		{
			Pivot.parent.SetLChild(Pivot.rightChild);
			Pivot.rightChild.SetParent(Pivot.parent);
		}
		else if(Pivot.parent.rightChild==Pivot)
		{
			Pivot.parent.SetRChild(Pivot.rightChild);
			Pivot.rightChild.SetParent(Pivot.parent);
		}
		
		Pivot.Substrings.removeAll();
		Pivot=null;
		
		Temp.height=Temp.parent.height+1;
	}
	
	private void Rotateright(AVLtreeNodes<T> Pivot)
	{
		AVLtreeNodes<T> Temp=new AVLtreeNodes<T>();
		Temp.Copy(Pivot);
		
		AVLtreeNodes<T> Temp2=null;
		if(Pivot.leftChild.rightChild!=null)
			Temp2=Pivot.leftChild.rightChild;
		
		if(Temp2!=null)
		{
			Temp2.SetParent(Temp);
			Temp.SetLChild(Temp2);
		}
		
		Pivot.leftChild.SetRChild(Temp);
		Temp.SetParent(Pivot.leftChild);
		
		if(Pivot.parent.leftChild==Pivot)
		{
			Pivot.leftChild.SetParent(Pivot.parent);
			Pivot.parent.SetLChild(Pivot.leftChild);
		}
		else if(Pivot.parent.rightChild==Pivot)
		{
			Pivot.leftChild.SetParent(Pivot.parent);
			Pivot.parent.SetRChild(Pivot.leftChild);
		}
		
		Pivot.Substrings.removeAll();
		Pivot=null;
	
		Temp.height=Temp.parent.height+1;
		
	}
	
	private void Copy(AVLtreeNodes<T> Src)
	{
		this.Key=Src.Key;
		
		Node<int[]> curr=Src.Substrings.head; 
		
		while(curr.getNext()!=null)
		{
			curr=curr.getNext();
			this.Substrings.add(curr.getItem());
		}
	}
	
}
