package MFES;

import java.util.*;

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

	private void averageUsersDistance(boolean b) {
		// TODO Auto-generated method stub
		
	}

	private void mostFamousGroup(boolean b) {
		// TODO Auto-generated method stub
		
	}

	private void mostFamousUser(boolean b) {
		// TODO Auto-generated method stub
		
	}

	private void searchUsersByName(boolean b) {
		// TODO Auto-generated method stub
		
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
		
		printDivision("Groups Info");
		String group = selectGroup(hasParent);
		
		
		ArrayList<String> options = new ArrayList<>();
		options.add("Add existing user to group");
		options.add("Add a new group");
		
		String input = printOptions(options, hasParent);
		switch(input){
			case "Add existing user to group":
				addUserToGroup(true);
				groupsInfo(hasParent);
				break;
			case "Add a new group":
				addNewGroup(true);
				groupsInfo(hasParent);
				break;
			case BACK_INPUT:
				return;
			case MENU_INPUT: 
				mainMenu();
				break;
		}
	}

	private void addUserToGroup(boolean b) {
		// TODO Auto-generated method stub
		
	}

	private String selectGroup(boolean hasParent) {
		// TODO Auto-generated method stub
		return null;
	}

	private void addNewGroup(boolean b) {
		// TODO Auto-generated method stub
		
	}

	public void usersMenu(boolean hasParent){
		printDivision("List of users");
		printListUsers();
		
		ArrayList<String> options = new ArrayList<>();
		options.add("See User's Profile");
		options.add("Add User");
		options.add("Add a new connection between two users");
		options.add("Distance between two users");
		options.add("Average distance of an user to other users");
		
		String input = printOptions(options, hasParent);
		switch(input){
			case "See User's Profile":
				userProfile(true);
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
				averageDistanceUser(true);
				usersMenu(true);
				break;
			case BACK_INPUT:
				return;
			case MENU_INPUT: 
				mainMenu();
				break;
				
		}
	}

	private void averageDistanceUser(boolean b) {
		// TODO Auto-generated method stub
		
	}

	private void distanceBetweenTwoUsers(boolean b) {
		// TODO Auto-generated method stub
		
	}

	private void addConnectionUsers(boolean b) {
		// TODO Auto-generated method stub
		
	}

	private void addUser(boolean b) {
		// TODO Auto-generated method stub
	}

	private void userProfile(boolean hasParent) {
		printDivision("User");
		
		String user = selectUser();
		
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
				userProfile(true);
				break;
			case "Update CV":
				updateCv(true, user);
				userProfile(true);
				break;
			case "Delete CV":
				deleteCV(true, user);
				userProfile(true);
				break;
			case "Send a message to a group":
				sendMessageToGroup(true, user);
				userProfile(true);
				break;
			case "Check messages from a group":
				checkMessagesFromGroup(true, user);
				userProfile(true);
				break;
			case BACK_INPUT:
				return;
			case MENU_INPUT: 
				mainMenu();
				break;
		}
		
	}

	private void checkMessagesFromGroup(boolean b, String user) {
		// TODO Auto-generated method stub
		
	}

	private void deleteCV(boolean b, String user) {
		// TODO Auto-generated method stub
		
	}

	private void sendMessageToGroup(boolean b, String user) {
		// TODO Auto-generated method stub
		
	}

	private void updateCv(boolean b, String user) {
		// TODO Auto-generated method stub
		
	}

	private void addConnectionToUser(boolean b, String user) {
		// TODO Auto-generated method stub
		
	}

	private String selectUser() {
		// TODO Auto-generated method stub
		return null;
	}

	private void printListUsers() {
		// TODO Auto-generated method stub
		
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
				printWrongOptionMessage();
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

	public void printWrongOptionMessage() {
		System.out.println("Invalid input!!!");
		
	}
	
	
}