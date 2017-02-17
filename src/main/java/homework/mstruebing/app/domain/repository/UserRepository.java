package homework.mstruebing.app;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Repository for a user
 *
 */
public class UserRepository extends Repository<User>
{
	public User findById(int id)
	{
		DatabaseService databaseService = new DatabaseService();
		Connection connection = databaseService.getConnection();
		User user = null;

		if (connection != null) {
			String stmnt = "SELECT * from " + TABLENAME + " WHERE id = " + id;

			PreparedStatement pst = null;

			try {
				pst = connection.prepareStatement(stmnt);
				ResultSet rs = pst.executeQuery();
				rs.next();
				user = new User(rs.getInt("id"));
				PasswordList passwordList = new PasswordList(rs.getInt("passwordList"), user);
				user.setPasswordList(passwordList);
			} catch (SQLException e) {
				System.err.println("ERROR: " + e.getMessage());
			} finally {
				databaseService.disconnect(connection);
			}
		}

		return user;
	}
}
