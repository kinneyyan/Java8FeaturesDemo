package Feature_DefaultMethod_2_2;

/**
 * Created by kinney on 16/3/16.
 */
public interface Defaulable {
    // Interfaces now allow default methods, the implementer may or
    // may not implement (override) them.
    default String notRequired() {
        return "Default implementation";
    }
}
