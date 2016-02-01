import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;


public class Flight {
	
	String flightNumber;
	String flightDate;
	String departureTime;
	String arrivalTime;
	String departureCity;
	String destinationCity;
	int availableSeats;
	
	public static Scanner myScanner = new Scanner(System.in);
	
	//Method to check if a flight is already in the system
	//Returns 1 if flight is present
	//Returns 0 if flight is absent
	public int isFlightPresent(String flightNumber, Flight flightArray[],int flightCount)
	{
		for(int loop=0;loop<flightCount;loop++)
		{
			if(flightArray[loop].flightNumber.equals(flightNumber))
			{
				return 1;
			}
		}
		return 0;
	}
	
	//Function to add new flight into the system
	public static int AddNewFlight(String filePath, Flight[] flights, int flightCount) throws Exception
	{
		Flight tempFlight = new Flight();
		String tempFlightNumber;
		int tempFlightSeats=0;
		String newFlightString;
		
		//Accepting flight details from user
		System.out.println("Add a new flight");
		System.out.println("Enter Flight Number: ");
		tempFlightNumber = myScanner.next();
		
		//Checking for incorrect flight number
		while(tempFlight.isFlightPresent(tempFlightNumber, flights, flightCount) == 1)
		{
			System.out.println("This flight already exists. Please try again.");
			System.out.println("Enter Flight Number: ");
			tempFlightNumber = myScanner.next();
		}
		
		tempFlight.flightNumber = tempFlightNumber;		
				
		System.out.println("Enter Flight Date: ");
		tempFlight.flightDate = myScanner.next();
		
		System.out.println("Enter Departure Time: ");
		tempFlight.departureTime = myScanner.next();
		
		System.out.println("Enter Arrival Time: ");
		tempFlight.arrivalTime = myScanner.next();
		
		System.out.println("Enter Departure City: ");
		tempFlight.departureCity = myScanner.next();
		
		System.out.println("Enter Arrival City: ");
		tempFlight.destinationCity = myScanner.next();
		
		System.out.println("Enter Available Seats: ");
		tempFlightSeats = myScanner.nextInt();
		
		//Valid seats available check!
		while(tempFlightSeats < 1)
		{
			System.out.println("Please enter a valid seat count: ");
			tempFlightSeats = myScanner.nextInt();
		}
		tempFlight.availableSeats = tempFlightSeats;
		
		flights[flightCount] = tempFlight;
		flightCount++;
	
		//Appending flight details to flights.txt
		BufferedWriter fileWriter = new BufferedWriter(new FileWriter(filePath + "flights.txt",true));
		newFlightString = tempFlight.flightNumber + "\t";
		newFlightString += tempFlight.flightDate + "\t";
		newFlightString += tempFlight.departureTime + "\t";
		newFlightString += tempFlight.arrivalTime + "\t";
		newFlightString += tempFlight.departureCity + "\t";
		newFlightString += tempFlight.destinationCity + "\t";
		newFlightString += tempFlight.availableSeats;
		
			fileWriter.newLine();
			fileWriter.write(newFlightString);
			
			fileWriter.close();	
			
			//Creating a seat map file for the new flight
			CreateSeatMap(filePath, tempFlight.flightNumber);
			
			return flightCount;
		}
	
	//Function to Create a seat map file for new flight
	public static void CreateSeatMap(String filePath, String fileName) throws IOException
	{
		BufferedWriter fileWriter = new BufferedWriter(new FileWriter(filePath + fileName + ".txt",false));
		
		String seatMap = "A	B	C	D	E	F	G";
		
		for(int loop=0;loop<10;loop++)
		{
			fileWriter.write((loop+1) + "\t" + seatMap);
			fileWriter.newLine();
		}
		fileWriter.close();
	}
	
	//Function to display all flights
	public static void ListAllFlights(String filePath) throws Exception
	{
		BufferedReader myFileReader = new BufferedReader(new FileReader(filePath + "flights.txt"));
		String oneLine;
		System.out.println("Flight Details: ");
		
		while((oneLine = myFileReader.readLine()) != null)
			System.out.println(oneLine);
		
		myFileReader.close();
	}
	
}
