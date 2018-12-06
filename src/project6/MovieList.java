package project6;

/**
 * This class extends the self-implemented BST class to create a Binary Search Tree that
 * hold Movies in the Nodes. 
 * 
 * @author Sammy Chuang
 * @version 2018.12.05
 */

//import java.util.Collections;

public class MovieList extends BST<Movie> {

	/**
	 * Iterates through the list of movies to check if any movies match
	 * the keyword provided by the user
	 *
	 * @param movieName
	 * 
	 * @return matchingMovieTitles a list of Movie objects with the 
	 * same title as the user specifies
	 */
	public MovieList getMatchingTitles( String movieName ) {
		
		//return null if theNo matches found. Try again.No matches found. Try again. parameter is an empty string or null
		if (movieName == null || movieName == "") 
		{
			return null;
		}
		
		//create a MovieList to hold all of the movies that match the movieName (parameter)
		MovieList matchingMovieTitles = new MovieList();
		
		//iterate through the movies in the list and retrieve those whose title has the movieName keyword in it
		MyIterator movieIterator = this.iterator();
		
		while (movieIterator.hasNext()) {
			Movie thisMovie = movieIterator.next();
			if (thisMovie.getTitle().toLowerCase().contains(movieName.toLowerCase())) {
				matchingMovieTitles.add(thisMovie);
			}
		}
		
//		matchingMovieTitles.sort();
		
		if (matchingMovieTitles.isEmpty()) {
			return null;
		}
		
		//return a sorted list of movies
		return matchingMovieTitles; 
	}
	
	/**
	 * Iterates through the list of movies to check if any actors have the same 
	 * name as provided by the user
	 * 
	 * @param actorName
	 * 
	 * @return matchingActors a list of Movie objects that have Actors that match
	 * the name as provided by the user
	 */

	public MovieList getMatchingActor( String actorName ) {
		
		//return null for an empty string or null parameter
		if (actorName == null || actorName == "") 
		{
			return null;
		}
		
		//create a list to store the matching actors found 
		MovieList matchingActors = new MovieList();
		
		MyIterator actorIterator = this.iterator();
		
		//iterate through the list of actors for each movie and retrieve the movies that have an actor whose name matches 
		//the actorName user input
		while (actorIterator.hasNext()) {
			Movie thisMovie = actorIterator.next();
			
			for (int i = 0; i < thisMovie.getActors().size(); i++) {
				if (thisMovie.getActors().get(i).getActorName().toLowerCase().contains(actorName.toLowerCase())){
					//add the movie to the list of moies with a found actor 
					matchingActors.add(thisMovie);
				}
			}
		}
		
		//return the sorted ArrayList of actors
		return matchingActors; 
	}
	
	/** Overrides the .toString() method 
	 * 
	 * @param 
	 * 
	 * @return a large, singular String of the list of movies in the current MovieList
	 */
	@Override
	public String toString() {
		
		//create a string builder to make a new string to return (of all of the titles) 
		StringBuilder listOfMoviesString = new StringBuilder();
		
		for (Movie m : this ) 
		{
			String movieTitle = m.getTitle();
			listOfMoviesString.append(movieTitle + "; ");
		}
		return listOfMoviesString.toString();
	}
}