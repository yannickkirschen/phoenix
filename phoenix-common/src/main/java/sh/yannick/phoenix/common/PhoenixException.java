package sh.yannick.phoenix.common;

/**
 * This {@link RuntimeException} is meant to serve as a wrapper for all exceptions thrown within a Phoenix application.
 * <p>
 * It can also be used to wrap a checked exception to make it unchecked this way. There is no rule of thumb when to
 * explicitly throw a checked exception or when to wrap it in a {@link PhoenixException} to "sneaky throw" it.
 *
 * @author Yannick Kirschen
 * @since 1.0.0
 */
public class PhoenixException extends RuntimeException {
    /**
     * Constructs a new instance based on another exception.
     *
     * @param throwable throwable to wrap in this exception
     */
    public PhoenixException(Throwable throwable) {
        super(throwable);
    }

    /**
     * Constructs a new instance based on a message an optional format arguments.
     * <p>
     * The arguments are used to format the message by calling {@link String#formatted(Object...)}.
     *
     * @param message as message related to this exception
     * @param args    optional arguments to format the message
     */
    public PhoenixException(String message, Object... args) {
        super(message.formatted(args));
    }
}
