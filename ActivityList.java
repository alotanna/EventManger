package com.example.dsaproject;

public class ActivityList {
    public ActivityNode front;
    public ActivityNode rear;
    private int size;

    /**
     * Constructs an empty ActivityList.
     * Initializes the front and rear nodes to null and size to 0.
     */
    public ActivityList() {
        this.front = null; // Initializing the front node to null
        this.rear = null;  // Initializing the rear node to null
        this.size = 0;     // Setting the initial size to 0
    }

    /**
     * Adds a new activity to the end of the queue.
     * @param activity The activity to be added to the queue.
     */
    public void enqueue(Activity activity) {
        ActivityNode newNode = new ActivityNode(activity); // Create a new node with the activity
        if (rear == null) {
            front = newNode; // If the queue is empty, set the new node as both front and rear
            rear = newNode;
        } else {
            rear.setNext(newNode); // Link the new node to the end of the queue
            rear = newNode; // Update the rear to the new node
        }
        size++; // Increase the size of the queue
    }

    /**
     * Removes and returns the activity at the front of the queue.
     * @return The activity removed from the front of the queue, or null if the queue is empty.
     */
    public Activity dequeue() {
        if (front == null) {
            return null; // Return null if the queue is empty
        }
        Activity activity = front.getActivity(); // Get the activity at the front
        front = front.getNext(); // Move the front to the next node
        if (front == null) {
            rear = null; // If the queue becomes empty, set rear to null
        }
        size--; // Decrease the size of the queue
        return activity; // Return the removed activity
    }

    /**
     * Returns the activity at the front of the queue without removing it.
     * @return The activity at the front of the queue.
     * @throws IllegalStateException if the queue is empty.
     */
    public Activity peek() {
        if (front == null) {
            throw new IllegalStateException("Queue is empty."); // Throw exception if the queue is empty
        }
        return front.getActivity(); // Return the activity at the front
    }

    /**
     * Checks if the queue is empty.
     * @return true if the queue is empty, false otherwise.
     */
    public boolean isEmpty() {
        return size == 0; // Return true if size is 0
    }

    /**
     * Returns the number of activities in the queue.
     * @return The size of the queue.
     */
    public int getSize() {
        return size; // Return the current size of the queue
    }

    /**
     * Returns a string representation of all activities in the queue.
     * @return A string containing details of all activities in the queue.
     */
    public String getAllActivitiesDetails() {
        StringBuilder sb = new StringBuilder(); // Use StringBuilder to build the result string
        ActivityNode current = front; // Start with the front node
        while (current != null) {
            sb.append(current.getActivity().toString()).append("\n"); // Append activity details
            current = current.getNext(); // Move to the next node
        }
        return sb.toString(); // Return the final string with all activities
    }
}
