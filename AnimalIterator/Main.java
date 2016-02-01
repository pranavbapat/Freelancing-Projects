import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class Main {

	//ArrayList to store array of Animal Objects
	static ArrayList<Animal> animalArrayList = new ArrayList<Animal>();
	
	//Main method
	public static void main(String[] args) throws FileNotFoundException {
	
		//Method to Read the input text file
		ReadInputFile();
		
		//Method to print the contents of the output file
		PrintOutput();
	}
		
	//Method to Read Input text file
	public static void ReadInputFile() throws FileNotFoundException
	{
		//BufferedReader is used to read text files as input
		BufferedReader inputReader;
		
		//Name of the input file that we are reading
		String fileName = "inputFile.txt";
		
		try {
			inputReader = new BufferedReader(new FileReader(fileName));
			
			//myLine will contain one entire line of the input text file
			String myLine = inputReader.readLine();
			
			//Looping through the entire input file until no new line is encountered
			while(myLine != null)
			{
				//String array to store each individual word
				//Split method splits the entire line seperated by a space " "
				String[] data = myLine.split(" ");
				
				//Adding a new Animal Object to the arrayList
				animalArrayList.add(new Animal(data[0],data[1],data[2],data[3]));		
				
				//Reading the next line of the input file
				myLine = inputReader.readLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//Method to print the output or the contents of the ArrayList animalArrayList
	public static void PrintOutput()
	{
		System.out.println("Name\tClass\t\tWeight\tHeight");
		
		//Creating an object of Iterator to iterate through the ArrayList animalArrayList
		Iterator myIterator = animalArrayList.iterator();
		
		//The iterator will loop through the entire ArrayList until there are no elements in the list
		while(myIterator.hasNext())
		{
			//Iterator.next() returns an object
			//Type casting the object to Animal class
			Animal tempAnimal = (Animal)myIterator.next();
			
			//Displaying the output using getter method
			System.out.println(tempAnimal.getAnimalName() + "\t" + tempAnimal.getAnimalClass() + "\t" + tempAnimal.getAnimalWeight() + "\t" + tempAnimal.getAnimalHeight());
		}
	}
}

