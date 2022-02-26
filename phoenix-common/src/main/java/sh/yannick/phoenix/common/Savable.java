package sh.yannick.phoenix.common;

/**
 * A {@link Savable} object is able to save or discard its content.
 * <p>
 * Implementations may choose how the actual process of saving and discarding takes place. This interface makes no
 * assumptions on how the actual content looks like neither.
 *
 * @author Yannick Kirschen
 * @since 1.0.0
 */
public interface Savable {
    /**
     * Saves all changes made to the content in the relevant context.
     *
     * @throws PhoenixException if an error happens when saving
     */
    void saveChanges() throws PhoenixException;

    /**
     * Discards all changes made to the content in the relevant context.
     *
     * @throws PhoenixException if an error happens when discarding
     */
    void discardChanges() throws PhoenixException;
}
