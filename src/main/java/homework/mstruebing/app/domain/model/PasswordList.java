package homework.mstruebing.app;

import java.util.Collection;

/**
 * A model which represents a password list
 *
 */
public class PasswordList 
{
	protected int id;
	protected User user;
	protected Collection<Password> passwords;

	public PasswordList(int id, User user) {
		this.id = id;
		this.user = user;
	}

	public PasswordList(int id, User user, Collection<Password> passwords) {
		this.id = id;
		this.user = user;
		this.passwords = passwords;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return this.id;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public User getUser() {
		return this.user;
	}

	public void addPassword(Password password) {
		passwords.add(password);
	}

	public void removePassword(Password password) {
		passwords.remove(password);
	}
}
