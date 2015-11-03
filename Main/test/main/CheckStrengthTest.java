package main;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * classe que irá fazer testes em métodos para averiguar a saúde do código
 * para evitar eventuais complicações futuras!
 * 
 * @author alexsander
 */
public class CheckStrengthTest {
    
    public CheckStrengthTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Teste do método checkPasswordStrength da classe CheckStrength.
     */
    @Test
    public void testCheckPasswordStrength() {
        System.out.println("checkPasswordStrength");
        String passwd = "2hAj5#mne-ix.86H";
        int expResult = 13;
        int result = CheckStrength.checkPasswordStrength(passwd);
        assertEquals(expResult, result);
    }

    /**
     * Teste do método getPasswordLevel da classe CheckStrength.
     */
    @Test
    public void testGetPasswordLevel() throws Exception
 {
     try{
        System.out.println("getPasswordLevel");
        String passwd = "";
        CheckStrength.LEVEL expResult = null;
        CheckStrength.LEVEL result = CheckStrength.getPasswordLevel(passwd);
        assertEquals(expResult, result);
     }
     catch (Exception e)
    {
    assertTrue (true);
    }
    }
    
}
