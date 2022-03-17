import Entity.Survey;
import services.CommandIml;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommandRunner {

    private boolean isEnd = false;

    List<Survey> surveys = new ArrayList<>();

    public void run(String[] args) throws IOException {
        CommandIml commandIml = new CommandIml();
        boolean isSave = args.length>0 && args[0].equals("savefile");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        while (!isEnd){
            System.out.println("Input you command (create<name>, view<name>, delete<name>, list, exit)");
            String command = bufferedReader.readLine();
            if (command.contains("<")){
                Pattern pattern = Pattern.compile("(.+)<(.+)>");
                Matcher matcher = pattern.matcher(command);
                matcher.find();
                String name = matcher.group(2);
                String functionName = matcher.group(1);
                Optional<Object> answer = commandIml.callFunction(functionName, name, surveys, isSave, bufferedReader);
                if(answer.isPresent() && answer.get().getClass().getSimpleName().equals("Survey")){
                    surveys.add((Survey) answer.get());
                }else if(!answer.isPresent()){
                    System.out.println("Incorrect input, please try again (create<name>, view<name>, delete<name>, list, exit)");
                }
            }else {
                Optional<Object> answer = commandIml.callFunction(command, null, surveys, isSave, bufferedReader);
                if(answer.isPresent() && answer.get().getClass().getSimpleName().equals("Boolean")){
                    isEnd = (Boolean) answer.get();
                }else if(!answer.isPresent()){
                    System.out.println("Incorrect input, please try again (create<name>, view<name>, delete<name>, list, exit)");
                }
            }
        }
    }
}
