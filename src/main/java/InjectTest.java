import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class InjectTest {


    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchFieldException,
            NoSuchMethodException, InvocationTargetException {

        Class clazz = Class.forName("Person");

        //Field[] declaredFields = clazz.getDeclaredFields();
        //for(Field fie:declaredFields){
        //    System.out.println(fie);
        //}
        Object obj = clazz.newInstance();
        //Person obj = (Person)clazz.newInstance();
        //设置属性
        Field age = clazz.getDeclaredField("age");
        age.setAccessible(true);
        age.set(obj,18);
        //System.out.println(obj.age);

        //设置方法
        Method count = clazz.getDeclaredMethod("count");
        Integer invoke = (Integer)count.invoke(obj);
        System.out.println(invoke);
    }
}
