package interfaces;

import dto.Poste;

import java.util.List;
import java.util.Optional;

public interface PosteInterface {
    Optional<Poste> add(Poste poste);
    List<Poste> displayall();
}
