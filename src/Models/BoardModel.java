package Models;

public class BoardModel {

	public int placePalaceTile() {
		/* 
		 * can only be placed if: 
			 	- on top of a village tile
				- there is no developer on the space underneath
				- must be the highest ranked player in that city
				- refer to Highest Ranking Player under Developer
				- count the number of villages connected to the village you are placing the palace on top of (include the village you are placing on top of)
					* if this number is less that n
						- not allowed
					* if this number is greater than or equal to n
						- allowed
				- all the villages that are connected to the village you are placing the palace are on top of are not connected to another palace (this would be attempting to have two palaces in the same city)
					* not allowed


			return : Player scores (n/2) fame points, -1 if not allowed
		 */
		// TODO Auto-generated method stub
		return -1;
	}

}
