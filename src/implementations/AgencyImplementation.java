package implementations;

import dto.Agency;
import dto.Client;
import dto.Employee;
import helper.DatabaseConnection;
import interfaces.GenericInterface;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AgencyImplementation implements GenericInterface<Agency> {
    private DatabaseConnection db;

    public AgencyImplementation() {
        db = DatabaseConnection.getInstance();
    }
    @Override
    public Optional<Agency> add(Agency agency) {
        String insertQuery = "INSERT INTO agency (name, adresse , phone) VALUES (?, ? , ?)";

        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS)) {

            preparedStatement.setString(1, agency.getName());
            preparedStatement.setString(2, agency.getAdresse());
            preparedStatement.setString(3, agency.getPhone());

            int rowsInserted = preparedStatement.executeUpdate();

            if (rowsInserted > 0) {
                ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    int generatedAgencyCode = generatedKeys.getInt(1);
                    agency.setCode(generatedAgencyCode);
                    return Optional.of(agency);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Optional.empty();

    }

    @Override
    public Optional<Agency> search(Integer code) {
        String searchQuery = "SELECT * FROM agency WHERE code = ?";

        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(searchQuery)) {

            preparedStatement.setInt(1, code);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                Agency agency = new Agency();
                agency.setCode(resultSet.getInt("code"));
                agency.setName(resultSet.getString("name"));
                agency.setAdresse(resultSet.getString("adresse"));
                agency.setPhone(resultSet.getString("phone"));
                return Optional.of(agency);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Optional.empty();
    }

    @Override
    public Optional<Agency> search(String adresse) {
        String searchQuery = "SELECT * FROM agency WHERE adresse = ?";

        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(searchQuery)) {

            preparedStatement.setString(1, adresse);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                Agency agency = new Agency();
                agency.setCode(resultSet.getInt("code"));
                agency.setName(resultSet.getString("name"));
                agency.setAdresse(resultSet.getString("adresse"));
                agency.setPhone(resultSet.getString("phone"));
                return Optional.of(agency);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Optional.empty();
    }

    @Override
    public boolean delete(Integer code){
        Connection conn = db.getConnection();
        String deleteQuery = "DELETE FROM agency WHERE code = ?";

        try {
            PreparedStatement preparedStatement = conn.prepareStatement(deleteQuery);

            preparedStatement.setInt(1, code);

            int rowsDeleted = preparedStatement.executeUpdate();

            return rowsDeleted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    @Override
    public Optional<Agency> update(Agency agency){
        String updateQuery = "UPDATE agency SET name = ?, adresse = ?, phone = ? WHERE code = ?";
        try {
            Connection conn = db.getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement(updateQuery);

            preparedStatement.setString(1, agency.getName());
            preparedStatement.setString(2, agency.getAdresse());
            preparedStatement.setString(3, agency.getPhone());
            preparedStatement.setInt(4, agency.getCode());
            int rowsUpdated = preparedStatement.executeUpdate();

            if (rowsUpdated > 0) {
                return Optional.of(agency);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Optional.empty();
    }
    @Override
    public List<Agency> displayall(){
        Connection conn = db.getConnection();
        String selectQuery = "SELECT * FROM agency";
        List<Agency> agencies = new ArrayList<>();

        try {
            PreparedStatement preparedStatement = conn.prepareStatement(selectQuery);
            ResultSet resultSet = preparedStatement.executeQuery();



            while (resultSet.next()) {
                Agency agency = new Agency();
                agency.setCode(resultSet.getInt("code"));
                agency.setName(resultSet.getString("name"));

                agency.setPhone(resultSet.getString("phone"));
                agency.setAdresse(resultSet.getString("adresse"));
                agencies.add(agency);
            }

            return agencies;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
