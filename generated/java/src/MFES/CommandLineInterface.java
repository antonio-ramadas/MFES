package MFES;

import java.util.*;

import org.overture.codegen.runtime.VDMSet;

public class CommandLineInterface{
	public static final String BACK_INPUT = "b";
	public static final String MENU_INPUT = "m";
	
	Linkedin linkedIn = Linkedin.getInstance();
	
	public CommandLineInterface(){}
	
	public static void main(String[] args){
		CommandLineInterface cli = new CommandLineInterface();
		
		cli.mainMenu();
	}
	
	
	public void mainMenu(){
		System.out.println("Welcome to LinkedIn!!!");
		printDivision("Main Menu");

		ArrayList<String> options = new ArrayList<>();
		options.add("Users");
		options.add("Groups");
		options.add("Search users by name");
		options.add("Stats");
		
		String input = printOptions(options, false);
		switch(input){
			case "Users":
				usersMenu(true);
				mainMenu();
				break;
			case "Groups":
				groupsMenu(true);
				mainMenu();
				break;
			case "Search users by name":
				searchUsersByName(true);
				mainMenu();
				break;
			case "Stats":
				statsMenu(true);
				mainMenu();
				break;
			case BACK_INPUT:
				break;
			case MENU_INPUT: 
				break;
		}
	}
	
	private void statsMenu(boolean hasParent) {
		printDivision("Stats");

		ArrayList<String> options = new ArrayList<>();
		options.add("Most famous user");
		options.add("Most famous group");
		options.add("Average User Distance");
		
		String input = printOptions(options, hasParent);
		switch(input){
			case "Most famous user":
				mostFamousUser(true);
				statsMenu(hasParent);
				break;
			case "Most famous group":
				mostFamousGroup(true);
				statsMenu(hasParent);
				break;
			case "Average User Distance":
				averageUsersDistance(true);
				statsMenu(hasParent);
				break;
			case BACK_INPUT:
				return;
			case MENU_INPUT: 
				mainMenu();
				break;
		}
		
	}

	private void averageUsersDistance(boolean hasParent) {
		System.out.println();
		System.out.print("Average user info: ");
		Number avgUserDist = linkedIn.averageUserDistance();

		System.out.println(""+avgUserDist);
		
		try {
			wait(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private void mostFamousGroup(boolean hasParent) {
		System.out.println();
		System.out.print("Most famous group: ");
		String group= linkedIn.mostPopularGroup();

		System.out.println(group);
		
		try {
			wait(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}

	private void mostFamousUser(boolean hasParent) {
		System.out.println();
		System.out.println("Most famous user: ");
		User user = linkedIn.mostFamousUser();
		
		System.out.println("Name: "+user.name);
		System.out.println("Id: "+user.id);
		try {
			wait(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}

	private void searchUsersByName(boolean hasParent) {
		printDivision("Search");
		System.out.println("Search: ");
		Scanner scanner = new Scanner(System.in);
		String search = scanner.nextLine();
		
		Set<User> searchResult = linkedIn.searchByName(search);
		
		printSearcResult(searchResult);
		
		ArrayList<String> options = new ArrayList<>();
		options.add("Go to user profile");
		
		String input = printOptions(options, hasParent);
		switch(input){
			case "Go to user profile":
				User user = enterUserId();
				userProfile(true, user);
				searchUsersByName(hasParent);
				break;
			case BACK_INPUT:
				return;
			case MENU_INPUT: 
				mainMenu();
				break;
		}
		
		
		
	}

	private void printSearcResult(Set<User> searchResult) {
		System.out.println("Results:");
		printListUsers(searchResult);	
		System.out.println();
	}

	private void groupsMenu(boolean hasParent) {
		printDivision("List of Groups");

		ArrayList<String> options = new ArrayList<>();
		options.add("See group's info");
		options.add("Add a new group");
		
		String input = printOptions(options, hasParent);
		switch(input){
			case "See group's info":
				groupsInfo(true);
				groupsMenu(hasParent);
				break;
			case "Add a new group":
				addNewGroup(true);
				groupsMenu(hasParent);
				break;
			case BACK_INPUT:
				return;
			case MENU_INPUT: 
				mainMenu();
				break;
		}
		
	}

	private void groupsInfo(boolean hasParent) {
		
		printDivision("Group's Info");
		String groupName = selectGroup(hasParent);
		
		Group group = linkedIn.getGroupInfo(groupName);
		
		while(group == null){
			System.out.println("Wrong name!!!");
			groupName = selectGroup(hasParent);
			group = linkedIn.getGroupInfo(groupName);
		}
		
		ArrayList<String> options = new ArrayList<>();
		options.add("Add existing user to group");
		options.add("Remove user from group");
		
		String input = printOptions(options, hasParent);
		switch(input){
			case "Add existing user to group":
				addUserToGroup(true, groupName);
				groupsInfo(hasParent);
				break;
			case "Remove user from group":
				removeUserFromGroup(true, groupName);
				groupsInfo(hasParent);
				break;
			case BACK_INPUT:
				return;
			case MENU_INPUT: 
				mainMenu();
				break;
		}
	}

	private void removeUserFromGroup(boolean hasParent, String group) {
		User user = enterUserId();
		
		linkedIn.removeGroupUser(group , user);	
	}

	private void addUserToGroup(boolean hasParent, String group) {
		User user = enterUserId();
		
		linkedIn.addGroupUser(group , user);
	}

	private User enterUserId(){
		System.out.print("Enter user's id: ");
		Scanner scanner = new Scanner(System.in);
		String id = scanner.nextLine();
		
		while(!isValidUserId(id)){
			printInvalidUsersId();
			System.out.print("Enter user's id: ");
			id = scanner.nextLine();
		}
		
		return getUserById(id);
	}
	
	private User getUserById(String idStr) {
		
		int id;
		try{
			id= Integer.parseInt(idStr);
		}catch(Exception e){
			return null;
		}
		Set<User> users = linkedIn.users;
		for(User user : users){
			if(id == user.id.intValue()){
				return user;
			}
		}
		return null;
	}

	private boolean isValidUserId(String idStr) {
		
		try{
			int id = Integer.parseInt(idStr);
			
			Set<User> users = linkedIn.users;
			
		
			for(User user : users){
				
				//System.out.println("LOG: Avaliar ids: user_"+user.id + " e id_"+id);
				
				if(user.id.intValue() == id){
					return true;
				}
			}
			
			//System.out.println("LOG: id não válido" );
			
			return false;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}

	}

	private String selectGroup(boolean hasParent) {
		System.out.println();
		System.out.print("Enter group's name: ");
		Scanner scanner = new Scanner(System.in);
		String id = scanner.nextLine();
		
		
		
		return id;
	}

	private void addNewGroup(boolean hasParent) {
		User user = enterUserId();
		
		System.out.print("Enter group's name: ");
		Scanner scanner = new Scanner(System.in);
		String group = scanner.nextLine();
		
		linkedIn.addGroup(group, user);
	}

	private void printUserInfo(User user){
		System.out.println("ID: "+user.id);
		System.out.println("Name: "+user.name);
		System.out.println("CV: \n"+user.cv);
		System.out.println();
		System.out.println();
	}
	
	public void usersMenu(boolean hasParent){
		printDivision("List of users");
		System.out.println();
		printListUsers();
		System.out.println();
		
		ArrayList<String> options = new ArrayList<>();
		options.add("See User's Profile");
		options.add("Add User");
		options.add("Add a new connection between two users");
		options.add("Distance between two users");
		options.add("Average distance of an user to other users");
		
		String input = printOptions(options, hasParent);
		switch(input){
			case "See User's Profile":
				User user = enterUserId();
				userProfile(true, user);
				usersMenu(true);
				break;
			case "Add User":
				addUser(true);
				usersMenu(true);
				break;
			case "Add a new connection between two users":
				addConnectionUsers(true);
				usersMenu(true);
				break;
			case "Distance between two users":
				distanceBetweenTwoUsers(true);
				usersMenu(true);
				break;
			case "Average distance of an user to other users":
				averageDistanceToUser(true);
				usersMenu(true);
				break;
			case BACK_INPUT:
				return;
			case MENU_INPUT: 
				mainMenu();
				break;
				
		}
	}

	private void averageDistanceToUser(boolean hasParent) {
		User user = enterUserId();
		System.out.println("Average distance to user "+user.name+": "+linkedIn.averageDistanceToOthers(user));
	}

	private void distanceBetweenTwoUsers(boolean hasParent) {
		System.out.print("User 1.");
		User user1 = enterUserId();
		
		System.out.print("User 2.");
		
		User user2 = enterUserId();
		
		Number distance = linkedIn.distance(user1, user2);
		
		System.out.println("Distance between the user "+user1.name+" and user "+user2.name+": "+distance);
		
		try {
			wait(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}

	private void addConnectionUsers(boolean hasParent) {
		System.out.print("User 1.");
		User user1 = enterUserId();
		
		System.out.print("User 2.");
		
		User user2 = enterUserId();
		
		linkedIn.addConnection(user1, user2);
		System.out.println("Connection Added");
		
		try {
			wait(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private void addUser(boolean hasParent) {
		System.out.print("Enter new user's name: ");
		Scanner scanner = new Scanner(System.in);
		String username = scanner.nextLine();
		
		User user = new User(username);
		
		linkedIn.addUser(user);
	}

	private void userProfile(boolean hasParent, User user) {
		

		printDivision("User");
		printUserInfo(user);
		
		ArrayList<String> options = new ArrayList<>();
		options.add("Add connection to other users");
		options.add("Update CV");
		options.add("Delete CV");
		options.add("Send a message to a group");
		options.add("Check messages from a group");
		
		String input = printOptions(options, hasParent);
		switch(input){
			case "Add connection to other users":
				addConnectionToUser(true, user);
				userProfile(true, user);
				break;
			case "Update CV":
				updateCv(true, user);
				userProfile(true, user);
				break;
			case "Delete CV":
				deleteCV(true, user);
				userProfile(true, user);
				break;
			case "Send a message to a group":
				sendMessageToGroup(true, user);
				userProfile(true, user);
				break;
			case "Check messages from a group":
				checkMessagesFromGroup(true, user);
				userProfile(true, user);
				break;
			case BACK_INPUT:
				return;
			case MENU_INPUT: 
				mainMenu();
				break;
		}
		
	}

	private void checkMessagesFromGroup(boolean hasParent, User user) {
		//TODO Auto-generated method 
	}

	private void deleteCV(boolean hasParent, User user) {
		boolean delete = confirmationDialog();
		
		if(delete){
			user.deleteCV();
		}else{
			return;
		}
	}
	
	private boolean confirmationDialog(){
		System.out.println("Are you sure? (Y\\N)");
		Scanner scanner = new Scanner(System.in);
		String input = scanner.nextLine().toLowerCase();
		boolean validInput = false;
		
		
		while(!validInput){
			switch(input){
				case "y":
					validInput = true;
					return true;
				case "n":
					validInput = true;
					return false;
				default: 
					printInvalidInputMessage();
					input = scanner.nextLine().toLowerCase();
					break;
			}
		}
		
		return false;
		
	}

	private void sendMessageToGroup(boolean hasParent, User user) {
		// TODO Auto-generated method stub
		
	}

	private void updateCv(boolean hasParent, User user) {
		// TODO Auto-generated method stub
		
	}

	private void addConnectionToUser(boolean hasParent, User user) {		
		User user2 = enterUserId();
		user.addConnection(user2);
		
		System.out.println("Connection added!");
		try {
			wait(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}

	private void printListUsers() {
		Set<User> users = linkedIn.users;
		printListUsers(users);
		
	}
	
	private void printListUsers(Set<User> users){
		for(User user : users){
			System.out.println("Name: "+user.name+spacesFill(16,user.name.length())+"ID: "+user.id);
		}
	}

	public void printDivision(String menu){
		System.out.println();
		System.out.println("-------------------------"+menu+"------------------------");

	}
	
	public String printOptions(ArrayList<String> options, boolean hasParent){
		for(int i = 0; i < options.size(); i++){
			System.out.println((i+1) +"- "+options.get(i));
			
		}
		
		if(hasParent){
			System.out.println("'b' to go back or 'm' to go to the main menu.");		
		}
		System.out.print("Select one of the options: ");
		
		boolean validInput = false;
		Scanner scanner = new Scanner(System.in);
		String input = "";
		while(!validInput){
			input = scanner.nextLine();
			validInput = isValidInput(options, hasParent, input);
			if(!validInput){
				printInvalidInputMessage();
			}
		}
		
		
		if(input.equals(BACK_INPUT) || input.equals(MENU_INPUT)){
			return input;
		}else{
			return options.get(Integer.parseInt(input)-1);
		}		
	}

	public boolean isValidInput(ArrayList<String> options, boolean hasParent, String input) {
		if(hasParent){
			if(input.equals(BACK_INPUT) || input.equals(MENU_INPUT)){
				return true;
			}
		}
		try{
			int i = Integer.parseInt(input);
			
			if(i > 0 && i <= options.size()){
				return true;
			}else{
				return false;
			}			
		}catch(Exception e){
			return false;
		}
	}

	public void printInvalidInputMessage() {
		System.out.println("Invalid input!!!");
		
	}
	
	public void printInvalidUsersId(){
		System.out.println("Invalid user's id");
	}
	
	public String spacesFill(int maxSize, int used){
		String retStr = "";
		int iMax= maxSize-used;
		
		if (iMax <= 0){
			return " ";
		}
		
		for(int i = 0; i < iMax; i++){
			retStr += " ";
		}
		
		return retStr;
	}
	
}