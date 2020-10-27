import com.minnela.issue.domain.Users;
import com.minnela.issue.repository.IssueRepositoryImpl;
import com.minnela.issue.repository.UserRepository;
import com.minnela.issue.service.UserServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyString;
import static org.mockito.MockitoAnnotations.initMocks;

public class UserServiceTest {
    @Mock
    private UserRepository mockUserRepository;

    @Mock
    private IssueRepositoryImpl mockIssueRepository;

    private UserServiceImpl userServiceUnderTest;

    private Users user;

    @Before
    public void setUp(){
        initMocks(this);
        userServiceUnderTest = new UserServiceImpl(mockUserRepository,mockIssueRepository);

        user.setId(10);
        user.setName("luna");
        user.setSurname("cat");
        user.setUserRole("user");
        user.setPassword("34567");
        user.setUsername("luna@gmail.com");

        Mockito.when(mockUserRepository.getUserByUserName(anyString()))
                .thenReturn(user);

    }

    @Test
    public void testFindUserByEmail() {
        // Setup
        final String email = "luna@gmail.com";

        // Run the test
        final Users result = userServiceUnderTest.getUserByUserName(email);

        // Verify the results
        assertEquals(email, result.getUsername());
    }

}
