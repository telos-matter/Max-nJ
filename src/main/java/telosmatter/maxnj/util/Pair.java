package telosmatter.maxnj.util;

/**
 * A public access pair
 * of objects.
 *
 * @param <F> the first object type
 * @param <S> the second object type
 */
public class Pair <F, S> {

    public F first;
    public S second;

    public Pair (F first, S second) {
        this.first = first;
        this.second = second;
    }

    public Pair () {
        this(null, null);
    }

}
