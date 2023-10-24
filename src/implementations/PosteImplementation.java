package implementations;

import dto.*;
import helper.DatabaseConnection;
import interfaces.PosteInterface;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PosteImplementation implements PosteInterface {
    private DatabaseConnection db;
    @Override
    public Optional<Poste> add(Poste poste){
        try (   Connection conn = DatabaseConnection.getInstance().getConnection();) {
            LocalDate currentDate = LocalDate.now();
            poste.setStartdate(currentDate);
            String insertQuery = "INSERT INTO poste (employeematricule, agencycode, startdate, enddate) VALUES (?, ?, ?, ?)";
            try (PreparedStatement insertStatement = conn.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS)) {
                insertStatement.setString(1, poste.getEmployee().getMatricule());
                insertStatement.setInt(2, poste.getAgency().getCode());
                insertStatement.setDate(3, java.sql.Date.valueOf(poste.getStartdate()));
                insertStatement.setDate(4, java.sql.Date.valueOf(poste.getEnddate()));

                int rowsInserted = insertStatement.executeUpdate();

                if (rowsInserted > 0) {

                    return Optional.of(poste);

                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Optional.empty();
    }


    @Override
    public List<Poste> displayall(){
        Connection conn = DatabaseConnection.getInstance().getConnection();
        String selectQuery = "SELECT * FROM poste";
        List<Poste> postes = new ArrayList<>();

        try {
            PreparedStatement preparedStatement = conn.prepareStatement(selectQuery);
            ResultSet resultSet = preparedStatement.executeQuery();



            while (resultSet.next()) {
                int code = resultSet.getInt("agencycode");
                String matricule = resultSet.getString("employeematricule");
                LocalDate startdate = resultSet.getDate("startdate").toLocalDate();
                LocalDate enddate = resultSet.getDate("enddate").toLocalDate();

                Poste poste = new Poste();
                poste.setStartdate(startdate);
                poste.setEnddate(enddate);

                if (!resultSet.wasNull()) {
                    Agency agency = new Agency();
                    agency.setCode(code);
                    poste.setAgency(agency);
                }

                if (matricule != null) {
                    Employee employee = new Employee();
                    employee.setMatricule(matricule);
                    poste.setEmployee(employee);
                }
                postes.add(poste);
            }

            return postes;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }


    }

