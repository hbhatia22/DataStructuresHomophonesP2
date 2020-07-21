
package Project2;
import java.io.*;
import java.util.*;


//tree filter pseudocode
/*String[] inputs = input.split(" ");
for (String word : inputs) {
    Node curr = ROOT; // begin at the root of the tree
    boolean isBad = true;
    for (int i = 0; i < word.length; i++) {
        char c = word.charAt(i);
        if (the curr node of the tree has a child whose char = c)
            curr = that child node;
        else {
            isBad = false;
            break; // current word is ok
        }
    }
    if (isBad)
      // replace the bad word that was just found
}
 * 
 * 
 * 
 */
public class MostHomephones1
{

	public static void main(String[] args) 
	{
		MyHashMap<String, ArrayList<String>> hash = new MyHashMap<>();
		ArrayList<String> words = new ArrayList<>();
		int max = 0;

		File file = new File("Project2-input.txt");

		try {
			Scanner rob = new Scanner(file);

			while (rob.hasNextLine()) 
			{
				String line = rob.nextLine();
				comparing p = new comparing (line);
				String key = p.getSound();
				String val = p.getWords();

				if ( hash.containsKey(key) )
				{
					ArrayList<String> values = hash.get(key);
					values.add(val);

					if ( values.size() > max )
					{                    
						max = values.size();
					
						words = values;
					}

					hash.put(key, values);
				}

				else
				{
					ArrayList<String> value = new ArrayList<>();
					value.add(val);
					hash.put(key, value);
				}
			}
			rob.close();
		} 

		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}

		System.out.println("The number of words with the most homophones is: " + max + " and are the following ones:" );

		for(String printing : words)
		{
			System.out.println(printing);
		}
	}
}

class comparing implements Comparable<comparing> {

	private String words;
	private String sound;
	private ArrayList<String> homophenes = new ArrayList<String>(); 

	comparing (String p) {

		int i = p.indexOf(' ');
		words = p.substring(0, i);
		sound = p.substring(i+1);
	}

	public String getWords() 
	{
		return words;
	}

	public void instertWords(String r)
	{
		homophenes.add(r);
	}

	public String getSound() 
	{
		return sound;
	}

	public ArrayList getWordList() 
	{
		return homophenes;
	}

	public String toString()
	{
		String s = words + ": " + sound;
		return s;
	}

	public int compareTo(comparing r) 
	{
		return this.sound.compareTo(r.sound);
	}
}
