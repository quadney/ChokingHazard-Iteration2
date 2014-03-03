package Helpers;

import java.util.Random;

/**
 * A generic "deck" of objects, capable of adding objects to the top of the
 * deck, drawing objects from the top of the deck, and shuffling itself.
 * 
 * @author Cameron Morrow
 * 
 * @param <E>
 *            The type of objects held in the deck.
 */
public class Deck<E> {
	private Stack<E> deck;

	public Deck() {
		deck = new Stack<E>();
	}

	/**
	 * Adds an object to the top of the deck.
	 */
	public void add(E object) {
		deck.add(object);
	}

	/**
	 * Draws an object from the deck, returning it to the caller and removing it
	 * from the deck.
	 */
	public E draw() {
		E obj = deck.top();
		deck.pop();
		return obj;
	}

	/**
	 * Randomizes the order of the objects in the deck.
	 */
	public void shuffle() {
		Random rand = new Random();
		int size = deck.size();

		@SuppressWarnings("unchecked")
		E[] shuffleBoard = (E[]) new Object[size];
		for (int i = 0; i < size; i++) {
			shuffleBoard[i] = deck.top();
			deck.pop();
		}

		for (int i = 0; i < size - 1; i++) {
			int swapIndex = rand.nextInt(size - i) + i;
			E temp = shuffleBoard[i];
			shuffleBoard[i] = shuffleBoard[swapIndex];
			shuffleBoard[swapIndex] = temp;
		}

		for (int i = 0; i < size; i++) {
			deck.add(shuffleBoard[i]);
		}
	}

	/**
	 * Returns the number of Objects in the deck.
	 */
	public int size() {
		return deck.size();
	}

}
