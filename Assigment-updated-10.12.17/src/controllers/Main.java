package controllers;

import java.io.File;
import java.util.Collection;
import com.google.common.base.Optional;

import model.Movies;
import model.User;
import utils.Serializer;
import utils.XMLSerializer;

public class Main
{
		public MoviesAPI movie;
	
	public Main() throws Exception {
		File movies = new File ("datastore.xml");
		Serializer serializer = new XMLSerializer(movies);
		movie = new MoviesAPI(serializer);
		if(movies.isFile())
		{
			movie.load();
		}
	}
	
	//Main 
	public static void main(String[] args) throws Exception 
	{
		Main api = new Main();
		
		//User variables
		String FName ;
		String LName;
		String gender;
		String age;
		String job;
		
		//Movie variables
		String MovieTitle;
		String RealeaseDate;
		String url;
		
		//rating variables
		
		long  movieId; 
		long UserId;
		int choice=0;
		double RatingGiven;
		char answer;
		
	
	api.movie.initalLoad();
	do {
		Menu();
		System.out.println("Select an option between 1-7: ");
		choice= EasyScanner.nextInt();
		
		switch (choice)
		{
		case 1:
  		 System.out.println("Enter the movie IDentification: ");
		       movieId = EasyScanner.nextLong();
		       
			   System.out.println("Enter the title of the movie: ");
		       MovieTitle = EasyScanner.nextString();
		        
		       System.out.println("Enter movie realease date [dd/mmm/yyy]: ");
		       RealeaseDate = EasyScanner.nextString();
		       
		       System.out.println("Enter url link of the movie you want to add: ");
		       url = EasyScanner.nextString();
		       
		       api.addMovie(movieId, MovieTitle, RealeaseDate, url);
		       api.movie.store();	
		break;	      
		

			case 2:

		 System.out.println("Enter your user IDentification: ");
		       UserId = EasyScanner.nextLong();
		       
		       System.out.println("Enter film IDentification: ");
		       movieId = EasyScanner.nextLong();
		        
		       System.out.println("Enter rating: ");
		       RatingGiven = EasyScanner.nextDouble();
		       
		       api.addRatings(UserId, movieId, RatingGiven);
		break;

		
		case 3:
			   System.out.println("Enter your first name: ");
		       FName = EasyScanner.nextString();
		       
		       System.out.println("Enter your last name: ");
		       LName = EasyScanner.nextString();
		       
		       System.out.println("Enter your age: ");
		       age = EasyScanner.nextString();
		       
		       System.out.println("Enter your gender: ");
		       gender = EasyScanner.nextString();
		       
		       System.out.println("Enter your job: ");
		       job = EasyScanner.nextString();
		       
		       api.addUser(FName, LName, age, gender, job);
		       api.movie.store();		     			       
		break;		
		
		case 4:	
			System.out.println("Enter the first name of user you want to remove: ");
		      FName = EasyScanner.nextString();
		      api.deleteUser(FName);
				
		case 5:		     
			 System.out.println("Get user using their first name: ");
			 FName = EasyScanner.nextString();
	         api.getUserByfName(FName);
	         api.getAllUsers();
		break;
			
		case 6:
			 System.out.println("Save current data entered? [Y/N]: ");
			 answer = EasyScanner.nextChar();
			 if (answer == 'y' || answer == 'Y')
			 {
				 api.movie.store();
			 }
		break;
			
		case 7:
			 System.out.println("Thank you for using this recommender program!");
		break;
			
		default:
			 System.out.println("Please choose of one of the following options 1-7.");
		break;
		}
		
	}while(choice!=7);
	
}

	//Adding movie
		private void addMovie(long Id, String MovieTitle, String ReleaseDate, String url) {
			Optional<User> movies = Optional.fromNullable(movie.getUser(Id));
			if(movies.isPresent())
			{
				movie.addMovie(Id, MovieTitle, ReleaseDate, url);
			}
			
		}

		//Adding a Rating
		private void addRatings(long UserId, long movieId, double ratingLeft) {
			Optional<Movies> ratings = Optional.fromNullable(movie.getMovie(movieId));
			if(ratings.isPresent())
			{
				movie.createRating( UserId,movieId, ratingLeft);
			}
			
		}

		//Adding user
		private void addUser(String fName, String lName, String age, String gender, String job) {
			movie.createUser(fName, lName, age, gender, job);

		}

		//Deleting user
		private void deleteUser(String fName) {
			Optional<User> user = Optional.fromNullable(movie.getUserByfName(fName));
			if(user.isPresent())
			{
				movie.deleteUser(user.get().UserId);
			}
			
		}

		//Getting all the users
		private void getAllUsers() {
			Collection<User> user = movie.getUsers();
			System.out.println(user);
		}
		

		//Getting User using their first name
		private void getUserByfName(String fName) {
			User user = movie.getUserByfName(fName);
			System.out.println(user);
			}

	

	//Menu
	public static void Menu()
	{
		System.out.println("1. Add Movie");
		System.out.println("2. Add rating");
		System.out.println("3  Create User");
		System.out.println("4. Remove User");
		System.out.println("5. Get user by their first name");
		System.out.println("6. Store/Save");
		System.out.println("7. Exit");
		
	}
}
