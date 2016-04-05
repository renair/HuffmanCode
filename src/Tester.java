import java.util.HashMap;

public class Tester
{
	public static void main(String[] args)
	{
		Tree<Integer> test = new Tree<Integer>(0);
		test.addLeft(1);
		test.addRight(2);
		test.deleteNode(1);
		System.out.println(test.toString());
		/*
		ArrayTree<Integer> arr = new ArrayTree<Integer>(new Integer[100], 0);
		arr.addLeft(arr.addRight(arr.addRight(arr.addLeft(0, 4), 3), 2), 1);
		System.out.println(arr.toString());
		*/
	}
}


/*
Huffman coder = new Huffman();
Tree<Character> map = coder.generateEncodingMap("qqqqstmn");
HashMap<Character, String> code = coder.generateDecodingMap(map);
String encoded = Huffman.encodeString("qqqqstmn", code);
System.out.println(encoded);
System.out.println(Huffman.decodeString(encoded, map));
*/