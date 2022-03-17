package services;

import Entity.Survey;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

public interface Command {

    void view(String name, List<Survey> surveys);

    Survey create(String name, BufferedReader bufferedReader, List<Survey> surveys) throws IOException;

    void delete(String name, List<Survey> surveys);

    void list(List<Survey> surveys);

    boolean exit(boolean isSave, List<Survey> surveys, BufferedReader bufferedReader) throws IOException;
}
