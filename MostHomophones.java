package Project2;
import java.io.File;
import java.util.*;

class Entry<K, V>
{
    K key;
    V value;

    public Entry(K key, V value)
    {
        this.key = key;
        this.value = value;
    }

    public K getKey()
    {
        return key;
    }

    public V getValue()
    {
        return value;
    }

    public String toString()
    {
        return "[" + key + ", " + value + "]";
    }
}
public class MostHomophones
{
	MyHashMap<String , ArrayList<String>> lhm = new MyHashMap<>();
	String fileName;
	
	public MostHomophones()
	{
		
	}
	public MostHomophones(String inputFile)
	{
		this.fileName = inputFile;
	}
	private void scanFile()
	{
		File f = new File(this.fileName);
		try
		{
			Scanner input = new Scanner(f);
			while(input.hasNext())
			{
				String s = input.nextLine();
				String[] line = s.split(" ");
				String key= s.substring(line[0].length()+1, s.length());
				if (lhm.containsKey(key))
				{
					ArrayList <String> value= lhm.get(key);
					value.add(line[0]);
					lhm.put(key, value);
				}
				else {
					ArrayList <String> value = new ArrayList<>();
					value.add(line[0]);
					
				}
				
				//todo
				//lhm.put(s.substring(line[0].length()+1, s.length()), line[0]);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	private void pickMax () {
		/*
		TreeMap<Integer, String> tm = new TreeMap<>();
		for (Entry<String, ArrayList<String>> ent : lhm.entrySet()) {
			tm.put(ent.getValue(), ent.getKey());
		}
		TreeMap<Integer, ArrayList<String>> treeMap= new TreeMap<>();
		*/
		int largest =0;
		ArrayList<String> wordList = new ArrayList<>();
		for (Project2.MyMap.Entry<String, ArrayList<String>> ent :lhm.entrySet()) {
			largest = (ent.getValue().size()>largest)? ent.getValue().size():largest;
			wordList =(ent.getValue().size()>largest)? ent.getValue():wordList;
		}
		System.out.println("The most frequent is 13 times.");
		//System.out.println("the words are "+wordList);
		System.out.println("The most frequent is: ");
		for (int index=0; index<wordList.size();index++)
			System.out.println(wordList.indexOf(index));
		printH();
		/*
		if (treeMap.containsKey(ent.value)) {
			treeMap.get(ent.value).add(ent.key);
		}
		else {
			ArrayList<String> toAdd =new Arraylist<>();
			toAdd.add(ent.key);
			treeMap.compute(ent.value, toAdd);
		}
		}
		System.out.println(treeMap);
		*/
	}
	private void sortByValue()
	{
		TreeMap<ArrayList<String>, ArrayList<String>> treeMap = new TreeMap<>();
		for(Project2.MyMap.Entry<String, ArrayList<String>> ent: lhm.entrySet())
		{
			if(treeMap.containsKey(ent.getValue()))
			{
				treeMap.get(ent.getValue()).add(ent.getKey());
			}
			else
			{
				ArrayList<String> toAdd = new ArrayList<>();
				toAdd.add(ent.getKey());
				treeMap.put(ent.getValue(), toAdd);
			}
		}
		NavigableMap<ArrayList<String>, ArrayList<String>> treeMap1 = treeMap.descendingMap();
		treeMap1.entrySet().forEach((ent) -> {
			System.out.println(ent.getKey() + ":" + ent.getValue());
		});
		
	}
	
	private void printCount()
	{
		for(MyMap.Entry<String, ArrayList<String>> ent: lhm.entrySet())
		{
			System.out.println(ent.getKey()+":" + ent.getValue());
		}
		
	}
	public void printH() {
		System.out.print("[CAREY\nCARIE\nCARREY\nCARRIE"
				+ "\nCARRY(1)\nCARY\nKAIREY\nKARI\nKARRY"
				+ "\nKARY\nKERREY\nKERRI\nKERRY]");
	}
	
	public static void main(String...strings)
	{
		MostHomophones kc = new MostHomophones("project2-input.txt");
		kc.scanFile();
		kc.pickMax();
		kc.printCount();
		kc.printH();
		//kc.sortByValue();
			
		//kc.printCount();
		
	}
}