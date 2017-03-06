package homework.mstruebing.app;

/**
 * A model which represents a password
 *
 */
public class Password
{
	protected int id;
	protected PasswordList passwordList;
	protected String title;
	protected String username;
	protected String password;

	public Password(int id, PasswordList passwordList, String title, String username, String password)
	{
		this.id = id;
		this.passwordList = passwordList;
		this.title = title;
		this.username = username;
		this.password = password;
	}

	public Password(PasswordList passwordList, String title, String username, String password)
	{
		this.passwordList = passwordList;
		this.title = title;
		this.username = username;
		this.password = password;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public int getId()
	{
		return this.id;
	}

	public void setPasswordList(PasswordList passwordList)
	{
		this.passwordList = passwordList;
	}

	public PasswordList getPasswordList()
	{
		return this.passwordList;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public String getTitle()
	{
		return this.title;
	}

	public void setUsername(String username)
	{
		this.username = username;
	}

	public String getUsername()
	{
		return this.username;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

	public String getPassword()
	{
		return this.password;
	}
}
