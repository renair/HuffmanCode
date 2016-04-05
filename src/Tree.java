import java.util.Comparator;
import java.util.NoSuchElementException;

public class Tree<T>
{
	public Tree<T> father;
	public Tree<T> left;
	public Tree<T> right;
	public int amount = 0;
	public T data;
	
	public Tree(T value)
	{
		if(value == null)
		{
			throw new NullPointerException();
		}
		data = value;
		left = null;
		right = null;
		father = null;
	}
	
	public Tree(Tree<T> father, T value)
	{
		if(value == null)
		{
			throw new NullPointerException();
		}
		data = value;
		left = null;
		right = null;
		this.father = father;
	}
	
	public Tree(Tree<T> father, T value, int amount)
	{
		if(value == null)
		{
			throw new NullPointerException();
		}
		data = value;
		left = null;
		right = null;
		this.father = father;
		this.amount = amount;
	}
	
	public int vertexAmount()
	{
		int i = 1;
		if(isLeaf())
		{
			return 1;
		}
		if(left != null)
		{
			i += left.vertexAmount();
		}
		if(right != null)
		{
			i += right.vertexAmount();
		}
		return i;
	}
	
	public boolean isLeaf()
	{
		if(left == null && right == null)
		{
			return true;
		}
		return false;
	}
	
	public boolean isEmpty()
	{
		return data == null;
	}
	
	public Tree<T> addLeft(T value)
	{
		if(value == null)
		{
			throw new NullPointerException("can't add null");
		}
		if(left != null)
		{
			throw new IllegalArgumentException("left already exist");
		}
		left = new Tree<T>(this, value);
		left.father = this.left;
		return left;
	}
	
	public Tree<T> getLeft()
	{
		return left;
	}
	
	public Tree<T> addRight(T value)
	{
		if(value == null)
		{
			throw new NullPointerException("can't add null");
		}
		if(right != null)
		{
			throw new IllegalArgumentException("right already exist");
		}
		right = new Tree<T>(this, value);
		right.father = this.left;
		return right;
	}
	
	public Tree<T> getRight()
	{
		return right;
	}
	
	public void deleteNode(T element)
	{
		Tree<T> needle = serch(element);
		if(needle == null)
		{
			throw new NoSuchElementException();
		}
		if(needle.left == null && needle.right != null)
		{
			if(needle.father.left == needle)
			{
				needle.father.left = needle.right;
			}
			if(needle.father.right == needle)
			{
				needle.father.right = needle.right;
			}
			return;
		}
		if(needle.left != null)
		{
			if(needle.father.left == needle)
			{
				needle.father.left = needle.left;
				needle.father = needle.father.left;
			}
			if(needle.father.right == needle)
			{
				needle.father.right = needle.left;
				needle.father = needle.father.right;
			}
			if(needle.right != null)
			{
				Tree<T> lft = needle.left;
				while(lft.left != null)
				{
					lft = lft.left;
				}
				lft.left = needle.right;
			}
		}
		
	}
	
	public Tree<T> serch(T value)
	{
		if(value == null)
		{
			throw new NullPointerException("nulls aren't allowed");
		}
		if(this.data.equals(value))
		{
			return this;
		}
		if(right != null)
		{
			Tree<T> p = right.serch(value);
			if(p != null)
			{
				return p;
			}
		}
		if(left != null)
		{
			Tree<T> p = left.serch(value);
			if(p != null)
			{
				return p;
			}
		}
		return null;
	}
	
	public String toString()
	{
		String s = "";
		if(data != null)
		{
			s += data + " ";
			//return s;
		}
		if(left != null)
		{
			s += left.toString();
		}
		if(right != null)
		{
			s += right.toString();
		}
		return s;
	}

	//dont't make it reverse!
	public static class comparator implements Comparator<Tree<Character>>
	{
		public int compare(Tree<Character> o1, Tree<Character> o2)
		{ 
			if(o1.amount < o2.amount)
			{
				return -1;
			}
			if(o1.amount > o2.amount)
			{
				return 1;
			}
			return 0;
		}
	}
}
