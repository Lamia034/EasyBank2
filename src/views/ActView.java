package views;

import dto.*;
import services.ActService;
import services.AgencyService;

import java.util.Optional;
import java.util.Scanner;

public class ActView {

    Act act = new Act();

    private final ActService actservice;
    public ActView(ActService actservice){
        this.actservice = actservice;
    }
    Scanner scanner = new Scanner(System.in);
    public void affectaccounttoagency(){
        System.out.print("Enter account number: ");
        Integer number = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter agency code: ");
        int agencyCode = scanner.nextInt();
        scanner.nextLine();

        int choice;
        switch(choice){
            case 1:

                break;
        }

        act.setAccount(new Account());
        act.setAgency(new Agency());

        act.getEmployee().setMatricule(number);
        act.getAgency().setCode(agencyCode);

        Optional<Act> addedAct = actservice.add(act);

        if (addedAct.isPresent()) {
            Act added = addedAct.get();
            System.out.println("Account affected successfully to your agency!");
            System.out.println("Matricule: " + addedAct.getEmployee().getMatricule());
            System.out.println("Agency Code: " + addedAct.getAgency().getCode());

        } else {
            System.out.println("Failed to add the affectation.");
        }
    }
}
