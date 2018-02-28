package skleppie.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import skleppie.model.User;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void findByEmail() {
        User user = prepareUser();
        testEntityManager.persist(user);

        User found = userRepository.findByEmail(user.getEmail());

        assertEquals(user.getEmail(), found.getEmail());
        assertEquals(user.getFirstName(), found.getFirstName());
        assertEquals(user.getLastName(), user.getLastName());

    }

    private User prepareUser() {
        User user = new User();
        user.setEmail("a@a.pl");
        user.setPassword("password");
        user.setFirstName("Fname");
        user.setLastName("Lname");

        return user;
    }

}