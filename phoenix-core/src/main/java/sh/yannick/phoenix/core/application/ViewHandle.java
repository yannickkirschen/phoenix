package sh.yannick.phoenix.core.application;

public interface ViewHandle {
    default void initialize() {
    }

    void setApplicationHandle(ApplicationHandle applicationHandle);
}
