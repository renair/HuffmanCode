import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class Huffman
{
	public PriorityQueue<Tree<Character>> sorting = new PriorityQueue<Tree<Character>>(100, new Tree.comparator());
	public Tree<Character> DecodingMap = new Tree<Character>('\0');
	public HashMap<Character, String> EncodingMap = new HashMap<Character, String>();
	
	public Tree<Character> generateEncodingMap(String s)
	{
		splitStringWithPriority(s);
		while(sorting.size() > 1)
		{
			Tree<Character> father = new Tree<Character>('\0');
			Tree<Character> one = sorting.poll();
			one.father = father;
			//System.out.println(one.data +" -> "+ one.amount);
			Tree<Character> two = sorting.poll();
			//System.out.println(two.data +" -> "+ two.amount);
			two.father = two;
			father.left = one;
			father.right = two;
			father.amount = one.amount + two.amount;
			sorting.add(father);
		}
		DecodingMap = sorting.poll();
		DecodingMap.toString();
		return DecodingMap;
	}

	public HashMap<Character, String> generateDecodingMap(Tree<Character> keys)
	{
		if(keys.left != null)
		{
			generateDecodingMap(keys.left, "0");
		}
		if(keys.right != null)
		{
			generateDecodingMap(keys.right, "1");
		}
		return EncodingMap;
	}
	
	private void generateDecodingMap(Tree<Character> keys, String path)
	{
		if(keys.data != '\0')
		{
			EncodingMap.put(keys.data, path+"");
		}
		if(keys.left != null)
		{
			generateDecodingMap(keys.left, path + "0");
		}
		if(keys.right != null)
		{
			generateDecodingMap(keys.right, path + "1");
		}
	}
	
	public static String encodeString(String input, HashMap<Character, String> map)
	{
		String s = "";
		char[] str = input.toCharArray();
		for(int i = 0;i < str.length;i++)
		{
			s += map.get(str[i]);
		}
		return s;
	}
	
	public static String decodeString(String input, Tree<Character> decoder)
	{
		String s = "";
		char[] chars = input.toCharArray();
		int pointer = 0;
		Tree<Character> firstNode = decoder;
		while(pointer < chars.length)
		{
			if(chars[pointer] == '0')
			{
				firstNode = firstNode.left;
				//System.out.print(firstNode.data+",");
			}
			if(chars[pointer] == '1')
			{
				firstNode = firstNode.right;
				//System.out.print(firstNode.data+",");
			}
			if(firstNode.data != '\0')
			{
				s += firstNode.data;
				firstNode = decoder;
			}
			pointer++;
		}
		return s;
	}
	
	private void splitStringWithPriority(String s)
	{
		char[] array = s.toCharArray();
		HashMap<Character, Integer> num = new HashMap<Character, Integer>();
		for(int i = 0;i < array.length;i++)
		{
			if(num.containsKey(array[i]))
			{
				num.put(array[i], num.get(array[i])+1);
			}
			else
			{
				num.put(array[i], 1);
			}
		}
		for(Map.Entry<Character, Integer> element:num.entrySet())
		{
			sorting.add(new Tree<Character>(null, element.getKey(), element.getValue()));
		}
	}
}
