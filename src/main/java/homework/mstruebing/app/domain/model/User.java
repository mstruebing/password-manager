package homework.mstruebing.app;

import java.util.Collection;

/**
 * A model which represents an user
 *
 */
public class User
{
	protected int id;
	protected PasswordList passwordList;

	public User(int id) {
		this.id = id;
	}

	public User(int id, PasswordList passwordList) {
		this.id = id;
		this.passwordList = passwordList;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return this.id;
	}

	public void setPasswordList(PasswordList passwordList) {
		this.passwordList = passwordList;
	}

	public PasswordList getPasswordList() {
		return this.passwordList;
	}
}
