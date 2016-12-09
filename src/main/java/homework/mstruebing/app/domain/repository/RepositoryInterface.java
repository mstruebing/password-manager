package homework.mstruebing.app;

/**
 * Interface which every repository class has to implement
 */
interface RepositoryInterface<T> {

	public boolean save(T entity);
	public boolean remove(T entity);

}
