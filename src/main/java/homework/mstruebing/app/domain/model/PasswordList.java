package homework.mstruebing.app;

import java.util.Collection;

/**
 * A model which represents a password list
 *
 */
public class PasswordList 
{
	protected long id;
	protected User user;
	protected Collection<Password> passwords;

	public PasswordList(long id, User user, Collection<Password> passwords) {
		this.id = id;
		this.user = user;
		this.passwords = passwords;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getId() {
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
