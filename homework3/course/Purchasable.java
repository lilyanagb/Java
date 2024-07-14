package homework3.course;

public interface Purchasable {
    /**
     * Marks the object as purchased.
     */
    void purchase();

    /**
     * Returns true if and only if the object is purchased.
     */
    boolean isPurchased();
}
