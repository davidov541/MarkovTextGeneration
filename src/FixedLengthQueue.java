/**
 * Implements a fixed-length queue. 
 * 
 * @author Matthew Boutell
 */
public class FixedLengthQueue {
	 /** Points to the next available location. */ 
	private int insertPoint;
	private boolean isFull;
	private int capacity;
	private Object[] ar;
	
	/**
	 * Creates a new queue.
	 * 
	 * @param capacity The capacity of the queue.
	 */
	public FixedLengthQueue(int capacity) {
		if (capacity < 1) {
			System.out.println("Cannot create a queue of length < 1");
			System.exit(1);
		}
		this.capacity = capacity;
		this.insertPoint = 0;
		this.ar = new Object[capacity];
		this.isFull = false;
	} 

	/**
	 * Adds an object to the queue, overwriting the old head element
	 * if necessary to make room.
	 * 
	 * @param obj
	 */
	public void add(Object obj) {
		this.ar[this.insertPoint] = obj;
		this.insertPoint = (this.insertPoint + 1)%this.capacity;
		if (this.insertPoint == 0) {
			this.isFull = true;
		}
	}
	
	/** Returns a string representation of the queue.
	 *  
	 * @return A string representation of the queue.
	 */
	@Override
	public String toString() {
		String string = "";

		// If not filled to capacity, only prints the filled items
		if (!this.isFull) {
			for (int i = 0; i < this.insertPoint; i++) {
				string += this.ar[i].toString() + " ";
			}	
		} 
		
		// If filled, it prints in the correct "queue order".
		// The index wraps around the queue, and the modulus 
		// handles the wrap for us.
		else {
			for (int i = this.insertPoint; i < this.insertPoint + this.capacity; i++) {
				string += this.ar[i%this.capacity].toString() + " ";
			}	
		}	
		return string;
	}
}
