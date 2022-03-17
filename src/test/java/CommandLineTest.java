import Entity.Survey;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import services.CommandIml;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class CommandLineTest {

    BufferedReader mockitoBuf = Mockito.mock(BufferedReader.class);

    List<Survey> surveys = new ArrayList<>();

    CommandIml commandIml = new CommandIml();

    @BeforeEach
    public void beforeAll() throws IOException {
        Mockito.when(mockitoBuf.readLine()).thenReturn("2");
        commandIml.callFunction("create", "name", surveys, true, mockitoBuf).ifPresent(o -> surveys.add((Survey) o));
    }

    @AfterEach
    public void after(){
        if(surveys.size()>1){
            for (int i = 1; i<surveys.size(); i++) {
                surveys.remove(1);
            }
        }
    }


    @Test
    public void createTest() throws IOException {
        Mockito.when(mockitoBuf.readLine()).thenReturn("2");
        commandIml.callFunction("create", "names", surveys, true, mockitoBuf).ifPresent(o -> surveys.add((Survey) o));
        assertEquals(2,surveys.size());
    }

    @Test
    public void deleteTest() {
        assertEquals(1,surveys.size());
        commandIml.delete("name", surveys);
        assertEquals(0,surveys.size());
    }

    @Test
    public void exitTest() throws IOException {
        commandIml.exit(true, surveys, mockitoBuf);
        File file = new File(String.valueOf(Paths.get("src", "main", "resources", "file.txt")));
        assertNotEquals(0, file.length());
    }

}
