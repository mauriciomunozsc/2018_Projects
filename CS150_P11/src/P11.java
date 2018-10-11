import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;

public class P11 
{

	public static void main(String[] args) 
	{
		int titleLength = 0;
		int averageReleaseYear = 0;
		int averageChartNum = 0;
		int[] yearsReleased = new int[2];
		String[] songInfo = new String[60];
		
		readFile(args[0], songInfo);
		titleLength = averageTitleLength(songInfo);
		averageReleaseYear = averageYearReleased(songInfo);
		averageChartNum = averageChartNumber(songInfo);
		yearsReleased = determineRangeOfYears(songInfo);
		writeFile(args[1], titleLength, averageReleaseYear, averageChartNum, yearsReleased);
		
	}
	
	public static void readFile(String fileName, String[] songArray)
	{
		try
		{
			File file = new File(fileName);
			Scanner fileScanner = new Scanner(file);
			int index = 0;
			
			while(fileScanner.hasNextLine())
			{
				songArray[index] = fileScanner.nextLine();
				index++;
			}
			
			fileScanner.close();
		}
		catch (Exception e)
		{
			System.out.println("do nothing");
		}
	} 
		
	public static int averageTitleLength(String[] songArray)
	{
		int lengthSum = 0;
		int songCount = 0;
		int average = 0;
		
		for(int i = 0; i < songArray.length; i+=4)
		{
			songCount++;
			lengthSum += songArray[i].length();
		}
		
		average = lengthSum/songCount;
		return average;
	} 
		
	public static int averageYearReleased(String[] songArray) 
	{
		int yearSum = 0;
		int count = 0;
		int average = 0;
		
		for(int i = 2; i < songArray.length; i+=4)
		{
			count++;
			yearSum += Integer.parseInt(songArray[i]);
		}
		
		average = yearSum/count;
		return average;
	}
		
	public static int averageChartNumber(String[] songArray) {
		int chartNum = 0;
		int count = 0;
		int average = 0;
		
		for(int i = 3; i < songArray.length; i+=4)
		{
			count++;
			chartNum += Integer.parseInt(songArray[i]);
		}
		
		average = chartNum/count;
		return average;
	} 
		
	public static int[] determineRangeOfYears(String[] songArray) {
		int min = Integer.parseInt(songArray[2]);
		int max = min;
		int current = 0;
		
		for(int i = 2; i < songArray.length; i+=4)
		{
			current = Integer.parseInt(songArray[i]);
			if(current > max)
			{
				max = current;
			}
			if(current < min)
			{
				min = current;
			}	
		}
		
		int[] range = {min,max};
		return range;	
	}
		
	public static void writeFile(String fileName, int avgTitle, int avgRelease, int avgChartNum, int[] songsRange)
	{
		try 
		{
			PrintWriter writer = new PrintWriter(fileName);
			writer.println("TOP 15 STATISTICS");
			writer.println("Average Title Length: " + avgTitle);
			writer.println("Average Year Released: " + avgRelease);
			writer.println("Average Chart Placement: " + avgChartNum);
			writer.println("Range of Release Years: " + songsRange[0] + "-" + songsRange[1]);
			writer.close();
		}
		catch (Exception e)
		{
			
		}
	}
}
