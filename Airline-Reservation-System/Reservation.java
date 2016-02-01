import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class Reservation {
	String passengerID;
	String passengerName;
	String seatNumber;
	String flightNumber;
	
	//Function to Check if a seat is available
	public static int CheckSeatAvailability(String filePath, String flightNumber, int seatRow, int seatColumn) throws IOException
	{
		String oneLine;
		char mySeatArray[][] = new char[10][8];
		char tempSeatArray[] = new char[20];
		int count=0;
		
		BufferedReader myFileReader = new BufferedReader(new FileReader(filePath + flightNumber + ".txt"));
		while((oneLine = myFileReader.readLine()) != null)
		{
			oneLine = oneLine.replaceAll("\\s+","");
			tempSeatArray = oneLine.toCharArray();
			
			for(int loop=0;loop<8;loop++)
				mySeatArray[count][loop] = tempSeatArray[loop];
			count++;
		}
		myFileReader.close();
		System.out.println("HERE IN CHECK: " + mySeatArray[seatRow][seatColumn+1]);
		if(mySeatArray[seatRow][seatColumn+1] == 'X')
			return 0;
		else
			return 1;
	}
	
	//Function to Display ALL passengers and ALL flights
	public static void DisplayPassengersAndFlights(Flight[] flights, int flightCount, Reservation[] reservations, int passengerReservationCount)
	{
		System.out.println("Reservations of ALL passengers on ALL Flights" + flightCount);
		String flightNumber;
		for(int loop=0;loop<flightCount;loop++)
		{
			flightNumber = flights[loop].flightNumber;
			System.out.println("LOOP: " + loop + " " + flightNumber);
			DisplayFlightReservations(reservations, flightNumber, passengerReservationCount);
		}
	}
	
	public static void	DisplayFlightReservations(Reservation[] reservations, String flightNumber, int passengerReservationCount)
	{
		System.out.println("Reservations for flight: " + flightNumber);
		for(int loop=0;loop<passengerReservationCount;loop++)
		{
			System.out.println("PRANAV: " + reservations[loop].passengerName);
			if(reservations[loop].flightNumber.equals(flightNumber))
			{
				System.out.println(reservations[loop].passengerID + "\t" + reservations[loop].passengerName + "\t"  + reservations[loop].seatNumber + "\t");
			}
		}
	}
	
}
