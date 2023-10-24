package interfaces;

import dto.Act;
import dto.Poste;

import java.util.List;
import java.util.Optional;

public interface ActInterface {
    Optional<Poste> add(Poste poste);
    List<Act> displayall();
}