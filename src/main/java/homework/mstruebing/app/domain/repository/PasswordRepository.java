package homework.mstruebing.app;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 * Repository for a password
 *
 */
public class PasswordRepository extends Repository<Password>
{
	public ArrayList<Password> findByUserId(int userId)
	{
		DatabaseService databaseService = new DatabaseService();
		Connection connection = databaseService.getConnection();

		UserRepository userRepository = new UserRepository();
		User user = userRepository.findById(userId);

		ArrayList<Password> passwords = new ArrayList<Password>();

		if (connection != null && user != null) {
			String stmnt = "SELECT * FROM `" + TABLENAME + "` WHERE `passwordList` = " + user.getId();

			PreparedStatement pst = null;

			try {
				pst = connection.prepareStatement(stmnt);
				ResultSet rs = pst.executeQuery();
				while (rs.next()) {
					PasswordList passwordList = user.getPasswordList();
					int id = rs.getInt("id");
					String title = rs.getString("title");
					String username = rs.getString("username");
					String encrpytedPassword = rs.getString("password");
					Password password = new Password(id, passwordList, title, username, encrpytedPassword);
					passwords.add(password);
				}
			} catch (SQLException e) {
				System.err.println("ERROR: " + e.getMessage());
			} finally {
				databaseService.disconnect(connection);
			}
		}

		return passwords;
	}
}
