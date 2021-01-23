package cl.proxy;

/**
 * @author Administrator
 */
public class StudentServiceImpl implements StudentService {

    private static volatile String name;

    @Override
    public String get() {
        System.out.println("get ..." + StudentServiceImpl.name);
        return StudentServiceImpl.name;
    }

    @Override
    public int add(String name) {
        System.out.println("add..." + name);
        StudentServiceImpl.name = name;
        return 0;
    }
}
