import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class BCryptTest {


    public static void main(String... args){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String pass = encoder.encode("pass");
        System.out.println(pass);
    }

}
