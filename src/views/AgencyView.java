package views;

import dto.Agency;
import dto.Employee;
import dto.Poste;
import services.AgencyService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Optional;
import java.util.Scanner;

public class AgencyView {
    Agency agency = new Agency();

    private final AgencyService agencyservice;
    public AgencyView(AgencyService agencyservice){
        this.agencyservice = agencyservice;
    }
    Scanner scanner = new Scanner(System.in);

    public void addagency() {
        System.out.print("Enter agency name: ");
        agency.setName(scanner.nextLine());

        System.out.print("Enter agency adresse: ");
        agency.setAdresse(scanner.nextLine());

        System.out.print("Enter agency phone: ");
        agency.setPhone(scanner.nextLine());

            Optional<Agency> addedAgency = agencyservice.addagency(agency);

            if (addedAgency.isPresent()) {
                System.out.println("agency added successfully with code: " +agency.getCode()+  "name: " + agency.getName() + " adresse: " + agency.getAdresse() + " phone: " + agency.getPhone());
            } else {
                System.out.println("failed to add new agency");
            }

    }

    public void searchagencybycode() {
        System.out.print("Enter agency code: ");
        int code = scanner.nextInt();

        Optional<Agency> searchedAgency = agencyservice.searchedbycodeagency(code);

        if (searchedAgency.isPresent()) {
            Agency agency = searchedAgency.get();
            System.out.println("Agency found with code: " + agency.getCode() + " Name: " + agency.getName() + " Address: " + agency.getAdresse() + " Phone: " + agency.getPhone());
        } else {
            System.out.println("Failed to find the agency with the given code.");
        }
    }
    public void deleteagency(){
        System.out.print("Enter agency code: ");
        int code = scanner.nextInt();

        boolean deletedAgency = agencyservice.deletedagency(code);

        if (deletedAgency) {
            System.out.println("Agency deleted successfully");
        } else {
            System.out.println("Failed to delete the agency with the given code.");
        }
    }

    public void searchagencybyadresse(){
        System.out.print("Enter agency adresse: ");
        String adresse = scanner.nextLine();

        Optional<Agency> searchedAgency = agencyservice.searchedbyadresseagency(adresse);

        if (searchedAgency.isPresent()) {
            Agency agency = searchedAgency.get();
            System.out.println("Agency found with code: " + agency.getCode() + " Name: " + agency.getName() + " Address: " + agency.getAdresse() + " Phone: " + agency.getPhone());
        } else {
            System.out.println("Failed to find the agency with the given adresse.");
        }
    }
    public void updateagency(){
        System.out.print("Enter agency code: ");
        Integer code = scanner.nextInt();
        Optional<Agency> searchedAgency = agencyservice.searchedbycodeagency(code);

        if (searchedAgency.isPresent()) {
            Agency agency = searchedAgency.get();
            System.out.println("agency found:");
            System.out.println("Code: " + agency.getCode());
            System.out.println("Name: " + agency.getName());
            System.out.println("adresse: " + agency.getAdresse());
            System.out.println("phone: " + agency.getPhone());
            System.out.println("Enter new agency information (or leave blank to keep existing information):");

            System.out.print("Enter new name: ");
            String newName = scanner.nextLine();
            if (!newName.isEmpty()) {
                agency.setName(newName);
            }

            System.out.print("Enter new adresse: ");
            String newAdresse = scanner.nextLine();
            if (!newAdresse.isEmpty()) {
                agency.setAdresse(newAdresse);
            }

            System.out.print("Enter new phone: ");
            String newPhone = scanner.nextLine();
            if (!newPhone.isEmpty()) {
                agency.setPhone(newPhone);
            }

            if (agencyservice.update(agency).isPresent()) {
                System.out.println("client updated successfully! with name:" + agency.getName() + " , prenoun " +  " , adresse " + agency.getAdresse() + " , phone " + agency.getPhone() );
            } else {
                System.out.println("Failed to update the agency.");
            }
        } else {
            System.out.println("client not found with Number: " + code);
        }
        }


    }


