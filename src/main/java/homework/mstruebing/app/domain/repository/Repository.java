package homework.mstruebing.app;

/**
 * Abstract Repository class
 *
 */
public abstract class Repository<T> implements RepositoryInterface<T>
{

	protected final String repositoryName = this.getClass().getName();
	protected final String tableName = repositoryName.substring(repositoryName.lastIndexOf('.') + 1);

	public boolean save(T entity) {
		System.out.println("should save into: " + tableName);
		return true;
	}

	public boolean remove(T entity) {
		System.out.println("should delete from " + tableName);
		return true;
	}

	public int count() {
		System.out.println("should count in: " + tableName);
		return 0;
	}
}
