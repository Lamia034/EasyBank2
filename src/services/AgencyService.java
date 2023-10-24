package services;
import dto.Agency;
import implementations.AgencyImplementation;
import java.util.Optional;

public class AgencyService {
  //  AgencyImplementation agencyI = new AgencyImplementation();
private final AgencyImplementation agencyI;
public AgencyService(AgencyImplementation agencyI){
    this.agencyI = agencyI;
}
public Optional<Agency> addagency(Agency agency) {
        return agencyI.add(agency);
    }
public Optional<Agency> searchedbycodeagency(Integer code){return agencyI.search(code);}
public boolean deletedagency(Integer code){return agencyI.delete(code);}

     public Optional<Agency> searchedbyadresseagency(String adresse){return agencyI.search(adresse);}
public Optional<Agency> update(Agency agency){return agencyI.update(agency);}


}

