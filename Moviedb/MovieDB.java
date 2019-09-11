import java.util.Iterator;
import java.util.NoSuchElementException;
import java.lang.String;

/**
 * Genre, Title 을 관리하는 영화 데이터베이스.
 * 
 * MyLinkedList 를 사용해 각각 Genre와 Title에 따라 내부적으로 정렬된 상태를  
 * 유지하는 데이터베이스이다. 
 */

public class MovieDB {
    
	MyLinkedList<MovieDBItem> MovieDb=null;
	Node<MovieDBItem> curr=null;
	Node<MovieDBItem> prev=null;
	
	public MovieDB() {
        // FIXME implement this
    	
    	// HINT: MovieDBGenre 클래스를 정렬된 상태로 유지하기 위한 
    	// MyLinkedList 타입의 멤버 변수를 초기화 한다.
    	
    	MovieDb=new MyLinkedList<MovieDBItem>();
    }

    public void insert(MovieDBItem item) {
        // FIXME implement this
        // Insert the given item to the MovieDB.

    	// Printing functionality is provided for the sake of debugging.
        // This code should be removed before submitting your work.
    	
    	int Insert=0;
    	
    	if(MovieDb.head.getNext()==null)
    	{
    		MovieDb.head.insertNext(item);
    	}
    	else
    	{
    		curr= MovieDb.head;
    		
    		while(true)
    		{
    			prev=curr;
    			curr=curr.getNext();
    		
    			if(curr==null)
    			{
    				prev.insertNext(item);
    				Insert=1;
    				break;
    			}
    			else if(curr.getItem().getGenre().compareTo(item.getGenre())>0)
    			{	
    				Node<MovieDBItem> Addin=new Node<MovieDBItem>(item,curr);
    				prev.setNext(Addin);
    				Insert=1;
    				break;
    			}
    			else if(curr.getItem().getGenre().compareTo(item.getGenre())==0)
    			{
    				break;
    			}
    			else
    				continue;
    		}
    		
    		if(Insert==0)
    		{	
    			String NewMovie= item.getTitle();
    			String CmpMovie= curr.getItem().getTitle();
    		
    			while(true)
    			{
    				if(curr==null)
        			{
        				Node<MovieDBItem> Addin=new Node<MovieDBItem>(item);
        				prev.setNext(Addin);
        				break;
        			}
					else if(curr.getItem().getGenre().compareTo(item.getGenre())!=0)
					{
						Node<MovieDBItem> Addin=new Node<MovieDBItem>(item,curr);
						prev.setNext(Addin);
						break;
					}
    				else if(CmpMovie.compareTo(NewMovie)==0)
        			{
        				//System.out.println("This Movie is already in DB!");
        				break;
        			}
        			else if(CmpMovie.compareTo(NewMovie)>0)
        			{
        				Node<MovieDBItem> Addin=new Node<MovieDBItem>(item,curr);
        				prev.setNext(Addin);
        				break;
        			}
        			else
    				{
        				prev=curr;
        				curr=curr.getNext();
        				if(curr!=null)
						{	
							CmpMovie=curr.getItem().getTitle();
							continue;
						}
        				else
        					continue;
    				}
    			}
    		}
    	}
    }


	public void delete(MovieDBItem item) {
        // FIXME implement this
        // Remove the given item from the MovieDB.
    	
    	// Printing functionality is provided for the sake of debugging.
        // This code should be removed before submitting your work.
		
		if(MovieDb.head.getNext()==null)
		{
			//System.out.println("There is no element to delete!");
		}
		else
		{
			curr=MovieDb.head.getNext();
			prev=MovieDb.head;
			
			while(true)
			{
				if(curr.getItem().equals(item)==false&&curr.getNext()==null)
				{
					//System.out.printf("There is no movie [%s] [%s]!\n",item.getGenre(),item.getTitle());
					break;
				}
				else if(curr.getItem().equals(item)==true)
				{
					if(curr.getNext()==null)
					{
						prev.setNext(null);
						break;
					}
					else
					{	
						prev.setNext(curr.getNext());
						curr.setNext(null);
						break;
					}
				}
				else if(curr!=null)
				{
					prev=curr;
					curr=curr.getNext();
				}
				
			}
		}
    }

    public MyLinkedList<MovieDBItem> search(String term) {
        // FIXME implement this
        // Search the given term from the MovieDB.
        // You should return a linked list of MovieDBItem.
        // The search command is handled at SearchCmd class.
    	
    	// Printing search results is the responsibility of SearchCmd class. 
    	// So you must not use System.out in this method to achieve specs of the assignment.
    	
        // This tracing functionality is provided for the sake of debugging.
        // This code should be removed before submitting your work.
    	curr=MovieDb.head.getNext();
    	prev=MovieDb.head;
    	
    	MyLinkedList<MovieDBItem> results = new MyLinkedList<MovieDBItem>();
    	 
    	while(true)
    	{
    		if(curr==null)
    			break;
    		
    		if(curr.getItem().getTitle().contains(term)==true)
    		{
    			results.add(curr.getItem());
    			curr=curr.getNext();
    			continue;
    		}
    		else if(curr!=null)
    		{
    			curr=curr.getNext();
    			continue;
    		}
    		else
    		{
    			break;
    		}
    	}
    	
    	// FIXME remove this code and return an appropriate MyLinkedList<MovieDBItem> instance.
    	// This code is supplied for avoiding compilation error. 

        return results;
    }
    
    public MyLinkedList<MovieDBItem> items() {
        // FIXME implement this
        // Search the given term from the MovieDatabase.
        // You should return a linked list of QueryResult.
        // The print command is handled at PrintCmd class.

    	// Printing movie items is the responsibility of PrintCmd class. 
    	// So you must not use System.out in this method to achieve specs of the assignment.

    	// Printing functionality is provided for the sake of debugging.
        // This code should be removed before submitting your work.

    	// FIXME remove this code and return an appropriate MyLinkedList<MovieDBItem> instance.
    	// This code is supplied for avoiding compilation error.   
        MyLinkedList<MovieDBItem> results = new MyLinkedList<MovieDBItem>();
        
        Node<MovieDBItem> Find=this.MovieDb.head;
        
        while(Find.getNext()!=null)
        {
        	Find=Find.getNext();
        	results.add(Find.getItem());
        }
        
    	return results;
    }
}

class Genre extends Node<String> implements Comparable<Genre> {
	public Genre(String name) {
		super(name);
	}
	
	@Override
	public int compareTo(Genre o) {
		boolean isTrue;
		int Result;
		
		isTrue=this.equals(o);
		
		if(isTrue==true)
			Result=1;
		else
			Result=0;
		
		return Result;
	}

	@Override
	public int hashCode() {
		throw new UnsupportedOperationException("not implemented yet");
	}

	@Override
	public boolean equals(Object obj) {
		if(this==obj)
			return true;
		
		if(obj==null)
			return false;
		
		if(this.getClass()!=obj.getClass())
			return false;
		
		Genre other = (Genre)obj;
		
		if(this.getItem()==null){
			if(other.getItem()!=null)
				return false;
		}
		else if(this.getItem()!=other.getItem())
			return false;
		
		return true;
	}
}

class MovieList implements ListInterface<String> {	
	
	MyLinkedList<String> MList;
	int numItems;
	
	public MovieList() {
		MList=new MyLinkedList<String>();
	}

	@Override
	public Iterator<String> iterator() {
		return new MovieListIterator<String>(this);
	}

	@Override
	public boolean isEmpty() {
		return MList.head.getNext()==null;
	}

	@Override
	public int size() {
		return numItems;
	}

	@Override
	public void add(String item) {
		Node<String> last=MList.head;
		
		while(last.getNext()!=null)
			last=last.getNext();
		
		last.insertNext(item);
		numItems++;
	}

	@Override
	public String first() {
		return MList.head.getNext().getItem();
	}

	@Override
	public void removeAll() {
		MList.head.setNext(null);
	}
}

class MovieListIterator<string> implements Iterator<String>{
	
	private MovieList list;
	private Node<String> curr;
	private Node<String> prev;
	
	public MovieListIterator(MovieList NEW)
	{
		this.list=NEW;
		this.prev=null;
		this.curr=NEW.MList.head;
	}
	
	@Override
	public boolean hasNext()
	{
		return curr.getNext()!=null;
	}
	
	@Override
	public String next()
	{
		if(!hasNext())
			throw new NoSuchElementException();
		
		prev=curr;
		curr=curr.getNext();
		
		return curr.getItem();
	}
	
	@Override
	public void remove(){
		if(prev==null)
			throw new IllegalStateException("next() should be called first");
		if(curr==null)
			throw new NoSuchElementException();
		
		prev.removeNext();
		list.numItems--;
		curr=prev;
		prev=null;	
	}
}
