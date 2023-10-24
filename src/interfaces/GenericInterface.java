package interfaces;

import java.net.Inet4Address;
import java.util.List;
import java.util.Optional;

public interface GenericInterface<T> {
    Optional<T> add(T t);
    Optional<T> search(Integer t);
    boolean delete(Integer t);
    Optional<T> search(String t);
    Optional<T> update(T t);
    List<T> displayall();
}

