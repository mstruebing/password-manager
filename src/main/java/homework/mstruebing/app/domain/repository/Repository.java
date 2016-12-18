package homework.mstruebing.app;

/**
 * Abstract Repository class
 *
 */
public abstract class Repository<T> implements RepositoryInterface<T>
{

	protected final String repositoryName = this.getClass().getName();

	public boolean save(T entity) {
		System.out.println("should save into: " + repositoryName);
		return true;
	}

	public boolean remove(T entity) {
		System.out.println("should delete from " + repositoryName);
		return true;
	}

	public int count() {
		System.out.println("should count in: " + repositoryName);
		return 0;
	}
}
