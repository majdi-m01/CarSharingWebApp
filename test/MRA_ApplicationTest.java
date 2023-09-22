package test;

import application.MRA_Application;
import dbadapter.DBFacade;
import junit.framework.TestCase;
import org.junit.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class MRA_ApplicationTest extends TestCase {
    public MRA_ApplicationTest() { super();}

    @Test
    public void testClass() {
        DBFacade stub = mock(DBFacade.class);
        DBFacade.setInstance(stub);

        MRA_Application.getInstance();

        verify(stub, times(1)).getAvaialbleMovies();
    }

}
