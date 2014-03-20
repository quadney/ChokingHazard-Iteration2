package ChokingHazard;

import Helpers.JsonObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.JOptionPane;

import Controllers.GameController;

public class GameManager {
	GameController currentGameController;
	
	public GameManager(GameController controller){
		this.currentGameController = controller;
	}
	
	public void createNewGame(int numPlayers, String playersAndTheirNames){
		//parse the string because that has all the player information
		//create new players based on it
		//currentGame = new GameController(new GameModel(numPlayers), new GameContainerPanel(numPlayers), playersAndTheirNames, numPlayers);
	}
	
	public JsonObject loadGame(File loadFile){
		//returns true if successfully loaded
		StringBuilder alpha = new StringBuilder();
		try{
			Scanner input = new Scanner(loadFile);
			while(input.hasNextLine()){
				alpha.append(input.nextLine());
			}
			input.close();
		}catch(FileNotFoundException e){
			JOptionPane.showMessageDialog(null, "File " + loadFile.getName() + " could not be loaded.");
			return null;
		}
			
		String loadString = alpha.toString();
		return new JsonObject(loadString);
	}
	
	public boolean saveGame(String fileName, String currentGameSerializedString){
		File newFile = new File(fileName);
		
		//check to see if the file exists already
		try{
			if (newFile.exists()){
				int selectedSaveGame = JOptionPane.showConfirmDialog(null, "This file name already has a saved game. Would you like to save anyways?", "Save Game", JOptionPane.YES_NO_OPTION);
				if(selectedSaveGame != JOptionPane.YES_OPTION ){
					return false;
				}
			}
		}catch(NullPointerException e){
			return false;
		}
		
		//writing the string to the file:
		FileWriter writer; 
		
		try{
			writer = new FileWriter(newFile);
			writer.write(currentGameSerializedString);
			writer.close();
		}catch(IOException e){
			JOptionPane.showMessageDialog(null, "File cannot be written. Try again.");
			return false;
		}
		
		JOptionPane.showMessageDialog(null, "Game Saved!");
		return true;
	}

}
