import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ActionsToRunTest {

    IRepository iRepository = new RepositoryInMemory();
    IRepository iRepository1 = new RepositoryDb();
    Controller controller = new Controller();

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getActionMethod() {
        Action action = new Action(3);
        ActionsToRun actionsToRun = new ActionsToRun(action);

        assertEquals(3, actionsToRun.getActionMethod());
    }

    @Test
    void runAction() {
        // TODO Na rwthsw an thelei na kanw 14 tests tou runAction (gia method 1,2,3 den mporw na kanw gt empleketai h scannerImport p kanei loop) ek twn opoiwn 7 gia
        // TODO thn RepositoryInMemory k 7 g thn RepositoryDb opou to assert equals tha einai sthn ousia to apotelesma ths kathe repository opws ekana sta repositories mesa.
    }

}