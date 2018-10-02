package haxballTools;

public interface HTScript {

    void start(int duration);

    default void stop(){

    }

    int getIndicator();

}