package Models.Actions;

import java.util.ArrayList;
import java.util.HashMap;

import Helpers.JsonObject;
import Models.GameModel;
import Models.JavaPlayer;
import Models.PalaceCard;

public class HoldFestivalAction extends Action {
	HashMap<JavaPlayer, ArrayList<PalaceCard>> cardsToDiscardPerPerson;
	HashMap<JavaPlayer, Integer> famePointsWonPerPerson;
	PalaceCard festivalCard;
	public int[] palaceXY;
	
	public HoldFestivalAction(int actionId, HashMap<JavaPlayer, ArrayList<PalaceCard>> cardsToDiscardPerPerson, HashMap<JavaPlayer, Integer> famePointsWonPerPerson, PalaceCard festCard, int[] palaceXY){
		super(actionId);
		
		this.cardsToDiscardPerPerson = cardsToDiscardPerPerson;
		this.famePointsWonPerPerson = famePointsWonPerPerson;
		this.festivalCard = festCard;
		this.palaceXY = palaceXY;
	}

	@Override
	public Action loadObject(JsonObject json) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean redo(GameModel game) {
		for(JavaPlayer player : game.getPlayers()){
			if(famePointsWonPerPerson.get(player) != null){
				player.changeFamePoints(famePointsWonPerPerson.get(player));
			}
		}
		for(JavaPlayer player : game.getPlayers()){
			if(cardsToDiscardPerPerson.get(player) != null){
				ArrayList<PalaceCard> cardsToDiscard = cardsToDiscardPerPerson.get(player);
				for(PalaceCard card : cardsToDiscard){
					player.removePalaceCard(card);
					game.getShared().discardCard(card);
				}
			}
		}
		game.getShared().discardCard(festivalCard);
		//TODO fuck you guys and not getting all your methods done
		//game.getBoard().
		return false;
	}

}
