package hackstyle.keyboard;

public interface OnAction<T> {

    void perform(EventListener<T> listener);

}
