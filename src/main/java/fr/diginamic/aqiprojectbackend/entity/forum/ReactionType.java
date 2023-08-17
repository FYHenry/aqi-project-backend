package fr.diginamic.aqiprojectbackend.entity.forum;

/** Reaction type */
public enum ReactionType {
    /** +1 */
    PLUS_ONE(1),
    /** -1 */
    MINUS_ONE(-1),
    /** 0 */
    ZERO(0);
    /** Value */
    private final int VALUE;

    /**
     * Constructor with parameters.
     * @param VALUE Value
     */
    ReactionType(int VALUE) {
        this.VALUE = VALUE;
    }

    /** Value getter */
    public int getValue() {
        return this.VALUE;
    }
}
