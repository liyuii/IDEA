import org.junit.Test;

public class junitTest {


    @Test
    public void method(){

        System.out.println("hello");

        Person person = new Person();
        person.setAge(15);
        int age = person.getAge();
        System.out.println(age);

    }

}
