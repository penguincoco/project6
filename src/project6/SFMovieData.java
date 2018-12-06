package project6;

/**
 * The main method class that reads data in from a csv, stores information in to Movie object and allows
 * a user to enter prompts searching for movies whose titles or actors contain a certain keyword.
 * 
 * @author Sammy Chuang
 * @version 2018.12.05
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class SFMovieData {
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		
		//verify that the command line argument exists 
		if (args.length == 0) 
		{
			System.err.println("Usage Error: the program expects file name as an argument.\n");
			System.exit(1);
		}

		// verify that command line argument contains a name of an existing file
		File SFMovieList = new File(args[0]);
		if (!SFMovieList.exists()) 
		{
			System.err.println("Error: the file " + SFMovieList.getAbsolutePath() + " does not exist.\n");
			System.exit(1);
		}
		if (!SFMovieList.canRead()) 
		{
			System.err.println("Error: the file " + SFMovieList.getAbsolutePath() + " cannot be opened for reading.\n");
			System.exit(1);
		}

		// open the file for reading
		Scanner inMovies = null;
		try 
		{
			inMovies = new Scanner(SFMovieList);
		} 
		catch (FileNotFoundException e) {
			System.err.println("Error: the file " + SFMovieList.getAbsolutePath() + " cannot be opened for reading.\n");
			System.exit(1);
		}
		
		MovieList movieBST = new MovieList(); 
		inMovies.nextLine(); 
		
		while (inMovies.hasNext()) 
		{
			//create variables for everything the Movie class needs/can hold
			String movieTitle; 
			String movieYear; 
			int movieYearInt;
			String movieLocation; 
			String movieFunFact;
			String productionComp;
			String distributor; 
			String movieDirector; 
			String movieWriter; 
			
			Actor actor1; 
			Actor actor2 = null;
			Actor actor3 = null;
			
			//initialise the first line for splitCSVLine method to read
			String line = inMovies.nextLine();
			
			//send the current line to splitCSVLine method to parse and return as an
			//ArrayList of Strings
			ArrayList<String> currentMovie = splitCSVLine (line);

			if (currentMovie.size() < 9) {
				continue;
			}
			
			//using the data from the parsed file, set the variables 
			movieTitle = currentMovie.get(0);
			movieYear = currentMovie.get(1);
			//double check to make sure that the year isn't just an empty string or null
			try 
			{
				movieYearInt = Integer.parseInt(movieYear);
			}
			catch (NumberFormatException ex) 
			{
				continue;
			}
			movieLocation = currentMovie.get(2);
			movieFunFact = currentMovie.get(3);
			
			//these two things are never used
			//productionComp = currentMovie.get(4);
			//distributor = currentMovie.get(5);
			
			movieDirector = currentMovie.get(6);
			movieWriter = currentMovie.get(7);
			
			
			//check to make sure there is at least one actor, if not, skip this entire line
			try 
			{
				actor1 = new Actor(currentMovie.get(8));
				//listOfActors.add(actor1);
			}
			catch (IllegalArgumentException e) 
			{
				//move on to next line if there is an illegal argument exception 
				continue; 
			}
			
			//check to see if there are two actors
			if (currentMovie.size() > 9) 
			{
				try 
				{
					actor2 = new Actor(currentMovie.get(9));
				}
				catch (IllegalArgumentException e) 
				{
				}
			}
			
			//check to see if there are three actors 
			if (currentMovie.size() > 10) 
			{
				try 
				{
					actor3 = new Actor(currentMovie.get(10));
				}
				catch (IllegalArgumentException e) 
				{
				}
			}
			
			//create a new Movie with the object just read in
			//create a variable to hold the movie if it is already in the list and the make updates to its locations
			Movie thisMovie = new Movie(movieTitle, movieYearInt, movieDirector, movieWriter, actor1, actor2, actor3);
			Movie alreadyInListMovie = null;
			
			//if the tree is size is 0, add the movie
			if (movieBST.size() == 0) {
				movieBST.add(thisMovie);
			}
			
			//otherwise, search through the tree and if the movie already exists, set it equal to alreadyInListMovie
			else if (movieBST.size() != 0) {
				MovieList.MyIterator currentMovies = movieBST.iterator();
				
				while (currentMovies.hasNext()) {
					Movie tempMovie = currentMovies.next();
					if (tempMovie.equals(thisMovie)) {
						alreadyInListMovie = tempMovie;
						break;
					}
				}
				
				//if a movie was found, just add the location
				if (alreadyInListMovie != null) {
					Location currentLocation = new Location(movieLocation, movieFunFact);
					alreadyInListMovie.addLocation(currentLocation);
				}
				//otherwise, add the whole new movie to the tree! 
				else {
					movieBST.add(thisMovie);
					Location currentLocation = new Location(movieLocation, movieFunFact);
					thisMovie.addLocation(currentLocation);
				}
			}
			
		}

		//create a scanner to read user input
		Scanner input = new Scanner(System.in);
		boolean keepGoing = true; 
		
		//allow the user to enter a title or an actor or end the program if they enter "quit" 
		while (keepGoing) 
		{
			//print directions to the user
			System.out.println("Enter a title or an actor's name (type 'quit' to exit): ");
			
			//read the user's input 
			String userInput = input.nextLine();
			
			//end program if the user enters quit
			if (userInput.equalsIgnoreCase("quit")) 
			{
				break;
			}
			
			//split the string along spaces to detect what the first word is:
			//title? or actor? 
			String[] splitInput = userInput.split(" ", 2);
			
			//it will be a minimum two strings because of the query and then the keyword
			if (splitInput.length < 2) {
				System.out.println("This is not a valid query. Try again.");
				continue;
			}
			
			String searchType = splitInput[0].toLowerCase();
			String query = splitInput[1].toLowerCase();
			
			//let the user know if they entered an invalid search query
			if (!searchType.equals("title") && !searchType.equals("actor")) {
				System.out.println("This is not a valid query. Try again.");
				continue;
			}
			
			//search type is movie; search through the entire movieBST and get the list of movies that have the keyword
			//in the title 
			if (searchType.equalsIgnoreCase("title")) {
				MovieList moviesWithQuery = movieBST.getMatchingTitles(query);
				if (moviesWithQuery == null || moviesWithQuery.size() == 0) {
					System.out.println("No matches found. Try again");
					continue;
				}
				
				for (Movie movie : moviesWithQuery) {
					System.out.println(movie.toString());
				}
			}
			
			//if user enters actor, iterate through the list of movies and the list of actors 
			//within each movie to find the ones that feature the actor specified by the user
			else if (searchType.equalsIgnoreCase("actor")) {
				MovieList moviesWithQuery = movieBST.getMatchingActor(query);
				if (moviesWithQuery == null || moviesWithQuery.size() == 0) {
					System.out.println("No matches found. Try again");
					continue;
				}
				
				for (Movie movie : moviesWithQuery) {
					System.out.println(movie.toString());
				}
			}
		}
		
		//close the scanner
		input.close();
	}

	/**
	 * Splits the given line of a CSV file according to commas and double quotes
	 * (double quotes are used to surround multi-word entries so that they may contain commas)
	 * @author Joanna Klukowska
	 * @param textLine a line of text to be passed
	 * @return an Arraylist object containing all individual entries found on that line
	 */
		
	public static ArrayList<String> splitCSVLine(String textLine) {
		if (textLine == null ) 
			{
			return null;
			}
		ArrayList<String> entries = new ArrayList<String>();
		int lineLength = textLine.length();
		StringBuffer nextWord = new StringBuffer();
		char nextChar;
		boolean insideQuotes = false;
		boolean insideEntry= false;
		
		// iterate over all characters in the textLine
		for (int i = 0; i < lineLength; i++) 
		{
			nextChar = textLine.charAt(i);
			// handle smart quotes as well as regular quotes
			
			if (nextChar == '"' || nextChar == '\u201C' || nextChar == '\u201D') 
			{
				 // change insideQuotes flag when nextChar is a quote
				if (insideQuotes) 
				{
					insideQuotes = false;
					insideEntry = false;
				}
				else 
				{
					insideQuotes = true;
					insideEntry = true;
				}
			}
			else if (Character.isWhitespace(nextChar)) 
			{
				if ( insideQuotes || insideEntry ) 
				{
					// add it to the current entry
					nextWord.append( nextChar );
				}
				else { // skip all spaces between entries
					continue;
				}
			}
			else if ( nextChar == ',') 
			{
				if (insideQuotes)
				{ // comma inside an entry
					nextWord.append(nextChar);
				}
				else 
				{ // end of entry found
					insideEntry = false;
					entries.add(nextWord.toString());
					nextWord = new StringBuffer();
				}
			}
			else 
			{
				// add all other characters to the nextWord
				nextWord.append(nextChar);
				insideEntry = true;
			}
		}
		
				
		// add the last word ( assuming not empty )
		// trim the white space before adding to the list
		if(!nextWord.toString().equals("")) 
		{
			entries.add(nextWord.toString().trim());
		}
		return entries;
	}
}