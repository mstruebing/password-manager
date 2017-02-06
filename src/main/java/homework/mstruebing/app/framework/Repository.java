package homework.mstruebing.app;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Abstract Repository class
 *
 */
public abstract class Repository<T> implements RepositoryInterface<T>
{
	protected final String REPOSITORYNAME = this.getClass().getName();
	protected final String TABLENAME = REPOSITORYNAME.substring(REPOSITORYNAME.lastIndexOf('.') + 1);

	protected final String[] primitiveTypes = {"java.lang.Integer", "java.lang.String"};
	// Config has an extra repository so it isn't needed here
	protected final String[] notPrimitiveTypes = {"User", "Password", "PasswordList"};

    public boolean save(T entity)
    {
        String stmnt = "INSERT INTO " + TABLENAME;
        String fields = "";
        String values = "";

        int index = 0;

        for (Field field : entity.getClass().getDeclaredFields()) {
            try {
                Object value = field.get(entity);
                System.out.println( value.getClass());

                if (value != null) {
                    String targetField = field.getName();
                    String type = value.getClass().getName();
                    boolean isPrimitive = isPrimitive(type);

                    // we need to get the id if the type of the field is not a
                    // primitive datatype
                    if (!isPrimitive) {
                        String targetMethod = getTargetMethod(targetField);
                        Method[] methods = ((Object)entity).getClass().getMethods();

                        for (Method method : methods) {
                            if (method.getName().equals(targetMethod)) {
                                Object notPrimitiveType = method.invoke(entity);

                                // this is real shit code!
                                // dont ever do this in real projects!
                                for (int i = 0; i < notPrimitiveTypes.length; i++) {
                                    value = getNotPrimitiveTypeId(notPrimitiveType, i);
                                    if ((int)value != 0) {
                                        break;
                                    }
                                }
                            }
                        }
                    }

                    fields += (index == 0) ? targetField : ", " + targetField;
                    values += (index == 0) ? value.toString() : ", " + value.toString();
                }
            } catch (Exception e) {
                System.err.println("ERROR: " + e.getMessage());
                return false;
            }

            index++;
        }

        stmnt += " (" + fields + ") VALUES(" + values + ")";
        DatabaseService databaseService = new DatabaseService();

        return databaseService.executeStatement(stmnt);
	}

	public boolean remove(T entity)
	{
		System.out.println("should delete from " + TABLENAME);
		return true;
	}

	public int count()
	{
		System.out.println("should count in: " + TABLENAME);
		return 0;
	}

	protected boolean isPrimitive(String type)
	{
		boolean isPrimitive = false;

		for (String primitiveType : primitiveTypes) {
			if (type.equals(primitiveType))  {
				isPrimitive = true;
				break;
			}
		}

		return isPrimitive;
	}

	protected String getTargetMethod(String targetField)
	{
		String targetMethod = "get" +
			Character.toUpperCase(targetField.charAt(0)) +
			targetField.substring(1);
		return targetMethod;
	}

    /**
     * I think this is the worst function I've ever written.
     *
     */
    protected int getNotPrimitiveTypeId(Object notPrimitiveType, int i) {
        int id = 0;
        switch (i) {
            case 0:
                try {
                    id = ((User)notPrimitiveType).getId();
                } catch (Exception e) {
                    id = 0;
                } finally {
                    break;
                }
            case 1:
                try {
                    id = ((Password)notPrimitiveType).getId();
                } catch (Exception e) {
                    id = 0;
                } finally {
                    break;
                }
            case 2:
                try {
                    id = ((PasswordList)notPrimitiveType).getId();
                } catch (Exception e) {
                    id = 0;
                } finally {
                    break;
                }
            default:
                id = 0;
        }

        return id;
    }
}
