package homework.mstruebing.app;

import java.util.Collection;

/**
 * A model which represents an user
 *
 */
public class User 
{
	protected long id;
	protected PasswordList passwordList;
	

	/**
	 *  @TODO later
	 *	protected String username;
	 *	protected String accessKey;
	 *	protected String email;
	 *	protected Collection<PasswordList> passwordLists;
	 */

	public User(long id, PasswordList passwordList) {
		this.id = id;
		this.passwordList = passwordList;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getId() {
		return this.id;
	}

	public void setPasswordList(PasswordList passwordList) {
		this.passwordList = passwordList;
	}

	public PasswordList getPasswordList() {
		return this.passwordList;
	}
}
