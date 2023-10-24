package services;

import dto.Agency;
import dto.Poste;

import implementations.PosteImplementation;

import java.util.List;
import java.util.Optional;

public class PosteService {
    private final PosteImplementation posteI;
    public PosteService(PosteImplementation posteI){
        this.posteI = posteI;
    }
    public Optional<Poste> add(Poste poste) {
        return posteI.add(poste);
    }
    public List<Poste> displayall(){return posteI.displayall();}
}
