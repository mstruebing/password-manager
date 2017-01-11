package homework.mstruebing.app;

import java.lang.reflect.Field;

/**
 * Abstract Repository class
 *
 */
public abstract class Repository<T> implements RepositoryInterface<T>
{

	protected final String repositoryName = this.getClass().getName();
	protected final String tableName = repositoryName.substring(repositoryName.lastIndexOf('.') + 1);

	protected final String[] primitiveTypes = {"java.lang.Integer", "java.lang.String"};

	public boolean save(T entity) {
		System.out.println("should save into: " + tableName);
		for (Field field : entity.getClass().getDeclaredFields()) {
			try {
				// set cool sneaky accessible modifier to public :)
				field.setAccessible(true); 
				Object value = field.get(entity); 
				if (value != null) {
					String targetField = field.getName();
					String type = ((Object)value).getClass().getName();
					boolean isPrimitive = isPrimitive(type);
					
					System.out.println(type);
					System.out.println(targetField);
					System.out.println(isPrimitive);
					System.out.println("----");

					if (!isPrimitive) {
						// get the id of the field
					} 
				}
			} catch (Exception e) {
				System.err.println("ERROR: " + e.getMessage());
				return false;
			}
		}

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

	protected boolean isPrimitive(String type) {
		boolean isPrimitive = false;

		for (String primitiveType : primitiveTypes) {
			if (type.equals(primitiveType))  {
				isPrimitive = true;
				break;
			}
		}

		return isPrimitive;
	}
}
