package services;

import dto.Act;
import dto.Poste;
import implementations.PosteImplementation;

import java.util.List;
import java.util.Optional;

public class ActService {
    private final ActImplementation actI;
    public ActService(ActImplementation actI){
        this.actI = actI;
    }
    public Optional<Act> add(Act act) {
        return actI.add(act);
    }
    public List<Act> displayall(){return actI.displayall();}
}
