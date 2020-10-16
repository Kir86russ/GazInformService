package repo;

import domain.Account;
import utilDB.Connector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class AccountRepoImpl implements AccountRepo {

    private Connector connector = new Connector();

    @Override
    public Optional<Account> findByName(String name) {
        Connection connection = connector.getConnection();
        String query = String.format("select * from account where first_name = '%s'", name);

        Account account = null;

        try (PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");

                account = new Account(id, firstName, lastName);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            connector.disconnect(connection);
        }
        System.out.println(account);
        return Optional.ofNullable(account);
    }


    // Имя учетной записи УНИКАЛЬНО по ТЗ -> обновляется 1 запись
    @Override
    public boolean updateLastNameByFirstName(String firstName, String newLastName) {
        Connection connection = connector.getConnection();
        String query = String.format("update account set last_name = '%s' where first_name = '%s'", newLastName, firstName);
        boolean result = false;

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            if (preparedStatement.executeUpdate() == 0)
                System.out.printf("An account with first name %s not exist!\n", firstName);
            else {
                result = true;
                System.out.println("Last name was updated successfully!");
            }


        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            connector.disconnect(connection);
        }
        return result;
    }

}
