import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {

	//Scanner object to handle input output
	public static Scanner myScanner = new Scanner(System.in);
	
	//Menu Choice
	public static int mainMenuChoice=0;
	
	//Array of flight objects
	public static Flight[] flights = new Flight[100];

	//Total Flights in the system counter
	public static int flightCount=0;
	
	//Array of reservation objects
	public static Reservation[] reservations = new Reservation[100];
	//Total Passenger Reservation Count
	public static int passengerReservationCount=0;
	
	//Folder where the files are stored
	public static String filePath = "C:\\Users\\Pranav\\workspace\\Airline Reservation System\\";
	public static String tempFlightNumber;
	
	public static void main(String[] args) throws Exception{

		//Method that reads files and updates the flights and reservations objects
		ReadFiles();
			
		//Main Menu
		do
		{
			DisplayMainMenu();
			mainMenuChoice = myScanner.nextInt();

			switch(mainMenuChoice)
			{
				case 1: 
					flightCount = Flight.AddNewFlight(filePath, flights, flightCount);
					break;
				case 2:
					System.out.println("Make a new reservation");
					MakeReservation();
					break;
				case 3:
					System.out.println("Display passengers seats map");
					System.out.println("Enter Flight Number: ");
					tempFlightNumber = myScanner.next();
					DisplayPassengerSeatMap(tempFlightNumber);
					break;
				case 4:
					System.out.println("List all flights");
					Flight.ListAllFlights(filePath);
					break;
				case 5:
					System.out.println("List all passenger reservations for all flights");
					Reservation.DisplayPassengersAndFlights(flights, flightCount, reservations, passengerReservationCount);
					break;
				case 6:
					System.out.println("List all passenger reservations for a specific flight");
					System.out.println("Enter Flight Number: ");
					tempFlightNumber = myScanner.next();
					Reservation.DisplayFlightReservations(reservations, tempFlightNumber, passengerReservationCount);
					break;
				case 7:
					System.out.println("Exit!");
					System.exit(0);
					break;
			}
			
		}while(mainMenuChoice != 7);
		
	}
	
	//Method to Display Menu
	public static void DisplayMainMenu()
	{
		System.out.println("\n");
		System.out.println("Main Menu");
		System.out.println("1. Add a new flight");
		System.out.println("2. Make a new reservation");
		System.out.println("3. Display passengers seats map");
		System.out.println("4. List all flights");
		System.out.println("5. List all passenger reservations for all flights");
		System.out.println("6. List all passenger reservations for a specific flight");
		System.out.println("7. EXIT");
		System.out.println("Please enter your choice: ");
	}
	
	//Method to read files flights.txt and reservations.txt
	public static void ReadFiles() throws FileNotFoundException, IOException
	{
		//Flights.txt
		BufferedReader myFileReader = new BufferedReader(new FileReader(filePath + "flights.txt"));
		String oneLine;
		String flightArray[];
		Flight tempObject = new Flight();
		//int line=0;
		myFileReader.readLine();
		while((oneLine = myFileReader.readLine()) != null)
		{
			//line++;
			flightArray = oneLine.split("\t");
			tempObject.flightNumber = flightArray[0];
			tempObject.flightDate = flightArray[1];
			tempObject.departureTime = flightArray[2];
			tempObject.arrivalTime = flightArray[3];
			tempObject.departureCity = flightArray[4];
			tempObject.destinationCity = flightArray[5];
			tempObject.availableSeats = Integer.parseInt(flightArray[6]);
									
			//System.out.println("FLIGHT: " + tempObject.flightDate + " " + tempObject.availableSeats);
			flights[flightCount] = tempObject;

			CreateSeatMap(flights[flightCount].flightNumber);
			flightCount++;
					
		}
		myFileReader.close();
		
		//Reservations.txt
		myFileReader = new BufferedReader(new FileReader(filePath + "reservations.txt"));
		String reservationsArray[];
		Reservation tempReservation = new Reservation();
		oneLine = myFileReader.readLine();		
		while((oneLine = myFileReader.readLine()) != null)
		{
			reservationsArray = oneLine.split("\t");
			
			tempReservation.passengerID = reservationsArray[0];
			tempReservation.passengerName = reservationsArray[1];
			tempReservation.seatNumber = reservationsArray[2];
			tempReservation.flightNumber = reservationsArray[3];
			reservations[passengerReservationCount] = tempReservation;
			passengerReservationCount++;
		}
		myFileReader.close();
			
	}
	
	public static void MakeReservation() throws Exception
	{
		System.out.println("IN MR: " + passengerReservationCount);
		Reservation tempReservation = new Reservation();
		String tempSeatNumber;
		String tempFlightNumber;
		String newReservationString;
		
		System.out.println("Enter Passenger ID: ");
		tempReservation.passengerID = myScanner.next();
		newReservationString = tempReservation.passengerID + "\t";
		
		System.out.println("Enter Passenger Name: ");
		tempReservation.passengerName = myScanner.next();
		newReservationString += tempReservation.passengerName + "\t";
		
		System.out.println("Enter Flight Number: ");
		tempReservation.flightNumber = myScanner.next();
				
		DisplayPassengerSeatMap(tempReservation.flightNumber);
		
		System.out.println("Enter Seat Number: ");
		tempReservation.seatNumber = myScanner.next();
		ReserveSeat(tempReservation.flightNumber, tempReservation.seatNumber);
		
		reservations[passengerReservationCount] = tempReservation;
		passengerReservationCount++;
		
		System.out.println("ADDED: " + passengerReservationCount);
		newReservationString += tempReservation.seatNumber + "\t";
		newReservationString += tempReservation.flightNumber;
		
		BufferedWriter myFileWriter = new BufferedWriter(new FileWriter(filePath + "reservations.txt",true));
		
		myFileWriter.newLine();
		myFileWriter.write(newReservationString);
		myFileWriter.close();
		
		BufferedReader myFileReader = new BufferedReader(new FileReader(filePath + "flights.txt"));
		myFileWriter = new BufferedWriter(new FileWriter(filePath + "tempFlights.txt",false));
		String flightDetailArray[];
		String oneLine;
		String tempReservationString = null;
		int loop=0;
		
		while((oneLine = myFileReader.readLine()) != null)
		{
			tempReservationString = "";
			flightDetailArray = oneLine.split("\t");
			if(flightDetailArray[0].equals(tempReservation.flightNumber))
			{
				loop = Integer.parseInt(flightDetailArray[6]);
				loop--;
				flightDetailArray[6] = Integer.toString(loop);
			}
			
			for(loop=0;loop<6;loop++)
				tempReservationString += flightDetailArray[loop] + "\t";
				tempReservationString += flightDetailArray[loop];
			myFileWriter.write(tempReservationString);
			myFileWriter.newLine();
		}
		myFileWriter.close();
		
		//Copying tempFlights into flights.txt
		myFileReader = new BufferedReader(new FileReader(filePath + "tempFlights.txt"));
		myFileWriter = new BufferedWriter(new FileWriter(filePath + "flights.txt",false));
		
		while((oneLine = myFileReader.readLine()) != null)
		{
			myFileWriter.write(oneLine);
			myFileWriter.newLine();
		}
		
		myFileReader.close();
		myFileWriter.close();
		
			
		System.out.println(tempReservation.passengerName + " is booked on flight " + tempReservation.flightNumber);
	}
	
	public static void CreateSeatMap(String fileName) throws IOException
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
	
	public static void ReserveSeat(String fileName, String seatNumber) throws IOException
	{
		//char mySeatMap[][] = new char[10][8];
		BufferedReader myFileReader = new BufferedReader(new FileReader(filePath + fileName + ".txt"));
		String oneLine;
		String tempSeatNumber = seatNumber;
		int loop=0,count=0;
		int seatRow=0;
		int seatColumn=0;
		char mySeatArray[][] = new char[10][8];
		char tempSeatArray[] = new char[20];
		
		//Retrieving seat row and column from the seatNumber string
		tempSeatNumber = tempSeatNumber.replaceAll("[^0-9]+", "");
		seatRow = Integer.parseInt(tempSeatNumber);
		
		seatColumn = seatNumber.charAt(seatNumber.length() - 1);
		seatColumn -= (int)'A';
		
		while(Reservation.CheckSeatAvailability(filePath, fileName, seatRow, seatColumn) == 0)
		{
			System.out.println("Seat is already booked!");
			System.out.println("Please enter another seat number: ");
			seatNumber = myScanner.next();
		}

		//Reading seat file into char array
		while((oneLine = myFileReader.readLine()) != null)
		{
			oneLine = oneLine.replaceAll("\\s+","");
			tempSeatArray = oneLine.toCharArray();
			
			for(loop=0;loop<8;loop++)
				mySeatArray[count][loop] = tempSeatArray[loop];
			count++;
		}
		myFileReader.close();
		
		//Updating seat map character array
		mySeatArray[seatRow-1][seatColumn+1] = 'X';
		
		//Writing character data into SEAT MAP into flightNumber.txt
		BufferedWriter myFileWriter = new BufferedWriter(new FileWriter(filePath + "tempSeatMap.txt",false));
		String seatString = "";
		
		for(count=0;count<10;count++)
		{
			for(loop=0;loop<7;loop++)
				seatString += mySeatArray[count][loop] + "\t";
			seatString += mySeatArray[count][loop];
			System.out.println("SeatString: " + seatString);
			myFileWriter.write(seatString);
			myFileWriter.newLine();
			seatString = "";
		}
		
		myFileWriter.close();
		
		myFileReader = new BufferedReader(new FileReader(filePath + "tempSeatMap.txt"));
		myFileWriter = new BufferedWriter(new FileWriter(filePath + fileName + ".txt",false));
		
		while((oneLine = myFileReader.readLine()) != null)
		{
			myFileWriter.write(oneLine);
			myFileWriter.newLine();
		}
		
		myFileWriter.close();
		myFileReader.close();
		System.out.println("Booked!");
	}
		
	//Function to display seat map for a specific flight
	public static void DisplayPassengerSeatMap(String flightNumber) throws Exception
	{
		BufferedReader myFileReader = new BufferedReader(new FileReader(filePath + flightNumber + ".txt"));
		System.out.println("Seat map for Flight: " + flightNumber);
		String oneLine;
		
		while((oneLine = myFileReader.readLine()) != null)
			System.out.println(oneLine);
		myFileReader.close();
	}
	
}
