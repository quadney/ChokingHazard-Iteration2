package Helpers;

/**
 * A generic, variable-size stack class.
 * 
 * @author Cameron Morrow
 * @param <E>
 *            The type of object the stack will hold.
 */
public class Stack<E> {
	private StackNode<E> top;

	public Stack() {
		top = null;
	}

	/**
	 * Returns the object on the top of the stack. Returns null if the stack is
	 * empty.
	 */
	public E top() {
		if (top != null)
			return top.getObject();
		else
			return null;
	}

	/**
	 * Returns true if the stack is empty, false otherwise.
	 */
	public Boolean isEmpty() {
		return (top == null);
	}

	/**
	 * Adds an object to the top of the stack.
	 * 
	 * @param object
	 *            The object to be added. Must be of the generic type of the
	 *            stack.
	 */
	public void add(E object) {
		top = new StackNode<E>(object, top);
	}

	/**
	 * Removes the object at the top of the stack. If the stack is empty,
	 * nothing will happen.
	 */
	public void pop() {
		if (top != null)
			top = top.getBelowNode();
	}

}

class StackNode<E> {
	private E object;
	private StackNode<E> belowNode;

	public StackNode(E object, StackNode<E> belowNode) {
		this.object = object;
		this.belowNode = belowNode;
	}

	public E getObject() {
		return object;
	}

	public StackNode<E> getBelowNode() {
		return belowNode;
	}
}