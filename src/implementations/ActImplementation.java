package implementations;

import dto.Act;
import dto.Agency;
import dto.Employee;
import dto.Poste;
import helper.DatabaseConnection;
import interfaces.ActInterface;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ActImplementation implements ActInterface{
    @Override
    public Optional<Act> add(Act act){
        try (Connection conn = DatabaseConnection.getInstance().getConnection();) {

            String insertQuery = "INSERT INTO act (employeematricule, agencycode) VALUES ( ?, ?)";
            try (PreparedStatement insertStatement = conn.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS)) {
                insertStatement.setString(1, act.getEmployee().getMatricule());
                insertStatement.setInt(2, act.getAgency().getCode());

                int rowsInserted = insertStatement.executeUpdate();

                if (rowsInserted > 0) {

                    return Optional.of(act);

                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Optional.empty();
    }


    @Override
    public List<Act> displayall(){
        Connection conn = DatabaseConnection.getInstance().getConnection();
        String selectQuery = "SELECT * FROM act";
        List<Act> actes = new ArrayList<>();

        try {
            PreparedStatement preparedStatement = conn.prepareStatement(selectQuery);
            ResultSet resultSet = preparedStatement.executeQuery();



            while (resultSet.next()) {
                int code = resultSet.getInt("agencycode");
                String matricule = resultSet.getString("employeematricule");

                Act act = new Act();

                if (!resultSet.wasNull()) {
                    Agency agency = new Agency();
                    agency.setCode(code);
                    act.setAgency(agency);
                }

                if (matricule != null) {
                    Employee employee = new Employee();
                    employee.setMatricule(matricule);
                    act.setEmployee(employee);
                }
                actes.add(act);
            }

            return actes;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
