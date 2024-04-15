/******************************************************************************
Programmer: Jordy Parker 
Date: 07/04/2024
Lab Assignment name: Lab 11
Instructor: Dr. Rafael Azuaje
College: Northwest Vista College
*******************************************************************************/
package lab11;
import java.nio.file.*;
import java.nio.file.attribute.*;
import java.util.Scanner;
public class FileStatistics
{
	public static void main(String[] args)
	{
		Scanner scanner = new Scanner(System.in);

		while(true)
		{
			System.out.print("\nPlease enter the path of the relevant file >> ");
			String input = scanner.nextLine();

			Path filePath = Paths.get(input);

			try
			{
				BasicFileAttributes attr = Files.readAttributes(filePath, BasicFileAttributes.class);
				System.out.println("File Path: " + filePath.toString()); //Path
				System.out.println("File Name: " + filePath.getName(filePath.getNameCount() - 1)); //name
				System.out.println("File Folder: " + filePath.getName(filePath.getNameCount() - 2)); //folder
				System.out.println("File Size: " + attr.size() + " bytes"); //size
				System.out.println("Time of Last Modification: " + attr.lastModifiedTime());
				break;
			}
			catch(Exception e)
			{
				System.out.println("File path not recognized.");
			}
		}
	}
}