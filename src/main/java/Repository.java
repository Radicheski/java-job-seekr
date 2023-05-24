import java.util.List;

public interface Repository<T> {
    void add(T element);
    void delete(T element);
    List<T> getAll();
    boolean isEmpty();

}
