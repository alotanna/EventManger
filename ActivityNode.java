package com.example.dsaproject;

public class ActivityNode {
    private Activity activity; // Activity associated with this node
    private ActivityNode next; // Reference to the next node in the list

    /**
     * Constructs an ActivityNode with the specified activity.
     * The next node reference is initialized to null.
     *
     * @param activity The activity to be stored in this node.
     */
    public ActivityNode(Activity activity) {
        this.activity = activity; // Set the activity for this node
        this.next = null; // Initialize the next node reference to null
    }

    /**
     * Returns the activity stored in this node.
     *
     * @return The activity stored in this node.
     */
    public Activity getActivity() {
        return activity;
    }

    /**
     * Sets the activity for this node.
     *
     * @param activity The activity to be set for this node.
     */
    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    /**
     * Returns the next node in the list.
     *
     * @return The next node in the list.
     */
    public ActivityNode getNext() {
        return next;
    }

    /**
     * Sets the reference to the next node in the list.
     *
     * @param next The next node to be linked.
     */
    public void setNext(ActivityNode next) {
        this.next = next;
    }
}
