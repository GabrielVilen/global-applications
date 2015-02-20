package MockitoTests;



import static org.junit.Assert.assertTrue;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class MockitoTest {

  // assume there is a class MyDatabase
    //@Mock
    // UserBean databaseMock;
    @Test
    public void testQuery() {
    // assume there is a class called ClassToTest
        // which could be tested
        //  U t  = new ClassToTest(databaseMock);

    // call a method
//    boolean check = t.query("* from t");
    // test the return type
        //  assertTrue(check);
    // test that the query() method on the 
        // mock object was called
//    Mockito.verify(databaseMock).query("* from t");
    }
}
