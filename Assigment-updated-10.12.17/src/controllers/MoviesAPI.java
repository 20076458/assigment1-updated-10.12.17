package controllers;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import com.google.common.base.Optional;

import model.Movies;
import model.Rating;
import model.User;
import utils.Serializer;

public class MoviesAPI 
{

	private static final Long UserId = null;

	private Serializer serializer;
	
	 private static Map<Long, User> userIndex = new HashMap<Long, User>();
	 private Map<String, User> fNameIndex = new HashMap<String, User>();
	 private static Map<Long, Movies > movieIndex = new HashMap<Long, Movies>();
	 
	 

	 
	 public MoviesAPI(Serializer serializer)
	  {
	    this.serializer = serializer;
	  }

	 //throws Exception
	 @SuppressWarnings("unchecked")
	  public void load() throws Exception
	  {
	   serializer.read();
	    {
	      
	      userIndex       = (Map<Long, User>)    serializer.pop() ;
	      movieIndex     = (Map<Long, Movies>)  serializer.pop();
	     }
	  }
	 
	 //throws exception
	 void store() throws Exception
	  {
	 
		  serializer.push(userIndex);
		  serializer.push(movieIndex);
		  serializer.write();
	 }
	 
	 //Collection
	  public Collection<User> getUsers ()
	  {
	    return userIndex.values();
	  }

	  //Constructor
	  public User createUser(String FName, String LName, String age, String gender, String job ) 
	  {
    User user = new User (FName, LName, age, gender, job, UserId );
    userIndex.put(user.UserId, user);
    fNameIndex.put(FName, user);
    return user;
  }
  
	//Getting user by their first name
  public User getUserByfName(String fName) {
		
		return fNameIndex.get(fName) ;
	}

  //Getting user by their id
  public User getUser(Long id) 
  {
	  return userIndex.get(id);
  }

  //Removing user by id
  public void deleteUser(Long id) 
  {
	  User user = userIndex.remove(id);
	  fNameIndex.remove(user.FName);
  }
  
  //Removing user from the  array
  public  void deleteUsers() 
  {
	  userIndex.clear();
	  fNameIndex.clear();
  }

@SuppressWarnings("unlikely-arg-type")
public void removeUser(String fName)
{
	User user = userIndex.remove(fName);
	userIndex.remove(user.FName);
}

  
  public Movies addMovie (long id, String MovieTitle, String RealeaseDate, String url )
  {
	  Movies movies = null;
	  Optional<User> user = Optional.fromNullable(userIndex.get(id));
	  if(user.isPresent())
	  {
		  movies = new Movies(MovieTitle, RealeaseDate, url);		 
		  movieIndex.put(movies.MovieId , movies);
	  }
	  return movies;
  }


public Movies getMovie(Long id) 
{
	return movieIndex.get(id);
}
  
public void initalLoad() throws IOException {
	String delims = "[|]";
	Scanner scanner = new Scanner(new File("./data/users5.dat"));
	while (scanner.hasNextLine()) {
		String userDetails = scanner.nextLine();
	
		String[] userTokens = userDetails.split(delims);

		if (userTokens.length == 7) {
			createUser(userTokens[1], userTokens[2], userTokens[3], userTokens[4], userTokens[5]);
		} else {
			scanner.close();
			throw new IOException("The name of the user is to long !: " + userTokens.length);
		}
	}
	scanner.close();
}


public Rating createRating(Long userID, Long movieID, double ratingLeft) {
	Optional <Movies> movies = Optional .fromNullable(movieIndex.get(movieID));
	 if (movies.isPresent())
	 {
		 movies.get().movie.add(new Rating(userID , movieID , ratingLeft));
	 }
	return null;
}
}