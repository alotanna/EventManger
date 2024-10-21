package com.example.dsaproject;

/**
 * Represents a guest with personal information, a unique ID, and reservation details.
 */
public class Guest extends Person {
    private String guestId;
    private String reservationDate;

    /**
     * Constructs a Guest object with the specified details.
     *
     * @param name             the name of the guest
     * @param age              the age of the guest
     * @param email            the email address of the guest
     * @param guestId          the unique ID of the guest
     * @param reservationDate  the reservation date of the guest
     */
    public Guest(String name, int age, String email, String guestId, String reservationDate) {
        super(name, age, email);
        this.guestId = guestId;
        this.reservationDate = reservationDate;
    }

    /**
     * Returns the unique ID of the guest.
     *
     * @return the guest ID
     */
    public String getGuestId() {
        return guestId;
    }

    /**
     * Sets the unique ID of the guest.
     *
     * @param guestId the new guest ID
     */
    public void setGuestId(String guestId) {
        this.guestId = guestId;
    }

    /**
     * Returns the reservation date of the guest.
     *
     * @return the reservation date
     */
    public String getReservationDate() {
        return reservationDate;
    }

    /**
     * Sets the reservation date of the guest.
     *
     * @param reservationDate the new reservation date
     */
    public void setReservationDate(String reservationDate) {
        this.reservationDate = reservationDate;
    }

    /**
     * Displays the guest's details, including inherited details from Person.
     */
    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Guest ID: " + guestId);
        System.out.println("Reservation Date: " + reservationDate);
    }

    /**
     * Checks if the guest has a reservation.
     *
     * @return true if the reservation date is not null or empty; false otherwise
     */
    public boolean hasReservation() {
        return reservationDate != null && !reservationDate.isEmpty();
    }

    /**
     * Returns a string representation of the guest, including inherited details from Person.
     * The format is: "name, age, email, GuestID=guestId, ReservationDate=reservationDate".
     *
     * @return a string representation of the guest
     */
    @Override
    public String toString() {
        return super.toString() + ", GuestID=" + guestId + ", ReservationDate=" + reservationDate;
    }
}
