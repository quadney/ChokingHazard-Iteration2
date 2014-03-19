package Models;

import Helpers.JsonObject;

public interface Serializable <E> {

    public String serialize();

    // Java 8 can do static interface methods, but will give Java 7 users 
    // problems if it's tried. Don't.
    public E loadObject(JsonObject json);
}
