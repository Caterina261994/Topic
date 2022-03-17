package services;

import Entity.Survey;
import Entity.Topic;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;


public class CommandIml implements Command {

    @Override
    public void view(String name, List<Survey> surveys){

        for (Survey survey:surveys){
            if(survey.getName().equals(name)){
                System.out.println(survey.getName() + ":\n" + "  " + survey.getTopic().toString());
            }else {
                System.out.println("No survey with name " + name);
            }
        }
    }

    @Override
    public Survey create(String name, BufferedReader bufferedReader, List<Survey> surveys) throws IOException {
        if (surveys.isEmpty()){
            return new Survey(name, new Topic().topicMaker(bufferedReader));
        }else {
            if (surveys.stream().anyMatch(survey -> survey.getName().equals(name))) {
                System.out.println("Survey with name " + name + " has already exist. Please try again");
            } else {
                return new Survey(name, new Topic().topicMaker(bufferedReader));
            }
        }
        return null;
    }

    @Override
    public void delete(String name, List<Survey> surveys) {

        Survey surveyForDelete = null;
        for (Survey survey : surveys) {
            if (survey.getName().equals(name)) {
                surveyForDelete = survey;
            } else {
                System.out.println("No survey with name " + name);
            }
        }
        if (surveyForDelete != null) {
            surveys.remove(surveyForDelete);
        }
    }
    @Override
    public void list(List<Survey> surveys) {
        surveys.forEach(survey -> System.out.println(survey.getName() + "(" + survey.getTopic().getName() + ")\n"));
    }

    @Override
    public boolean exit(boolean isSave, List<Survey> surveys, BufferedReader bufferedReader) throws IOException {
        if(isSave) {
            File file = new File(String.valueOf(Paths.get("src", "main", "resources", "file.txt")));
            FileWriter fileWriter = new FileWriter(file, false);
            for(Survey survey: surveys){
                fileWriter.write(survey.getName() + ":" + System.lineSeparator());
                fileWriter.write("  " + survey.getTopic().getName() + ":" + System.lineSeparator());
                fileWriter.write(String.join(System.lineSeparator(),survey.getTopic().getVariants()) + System.lineSeparator() + System.lineSeparator());
            }
            fileWriter.flush();
            fileWriter.close();
        }
        bufferedReader.close();
        return true;
    }



    public Optional<Object> callFunction(String functionName, String name, List<Survey> surveys, boolean isSave, BufferedReader bufferedReader) throws IOException{
        switch (functionName){
            case "create":
                return Optional.ofNullable(create(name,bufferedReader, surveys));
            case "view":
                view(name, surveys);
                return Optional.of(-1);
            case "list":
                list(surveys);
                return Optional.of(-1);
            case "delete":
                delete(name, surveys);
                return Optional.of(-1);
            case "exit":
                return Optional.of(exit(isSave, surveys, bufferedReader));
        }
        return Optional.empty();
    }

}
