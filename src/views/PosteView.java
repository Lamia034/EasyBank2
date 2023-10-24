package views;

import dto.*;
import services.PosteService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class PosteView {
    Poste poste = new Poste();

    private final PosteService posteservice;
    public PosteView(PosteService posteservice){
        this.posteservice = posteservice;
    }
    Scanner scanner = new Scanner(System.in);


    public void affectemployeetoagency(){
        System.out.print("Enter matricule: ");
        String matriculeInput = scanner.nextLine();

        System.out.print("Enter agency code: ");
        int agencyCode = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter end date of this post (dd-MM-yyyy): ");
        String endDateInput = scanner.nextLine();
        LocalDate endDate = null;

        try {
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            endDate = LocalDate.parse(endDateInput, dateFormatter);
        } catch (DateTimeParseException e) {
            System.err.println("Invalid date format. Please use dd-MM-yyyy.");
        }


        poste.setEmployee(new Employee());
        poste.setAgency(new Agency());

        poste.getEmployee().setMatricule(matriculeInput);
        poste.getAgency().setCode(agencyCode);
        poste.setEnddate(endDate);

        Optional<Poste> addedPoste = posteservice.add(poste);

        if (addedPoste.isPresent()) {
            Poste added = addedPoste.get();
            System.out.println("Employee affected successfully!");
            System.out.println("Matricule: " + added.getEmployee().getMatricule());
            System.out.println("Agency Code: " + added.getAgency().getCode());
            System.out.println("Start Date: " + added.getStartdate());
            System.out.println("End Date: " + added.getEnddate());
        } else {
            System.out.println("Failed to add the affectation.");
        }
    }
    public void displayall(){
        List<Poste> allpostes = posteservice.displayall();

        if (allpostes != null && !allpostes.isEmpty()) {
            System.out.println("All postes:");

            for (Poste poste : allpostes) {
                System.out.println("Matricule: " + poste.getEmployee().getMatricule());
                System.out.println("agency code: " + poste.getAgency().getCode());
                System.out.println("hiring date: " + poste.getStartdate());
                System.out.println("end Date: " + poste.getEnddate());

                System.out.println();
            }
        } else {
            System.out.println("No poste affectation found.");
        }
    }
}
