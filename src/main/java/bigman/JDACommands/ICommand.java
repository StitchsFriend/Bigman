package bigman.JDACommands;


import java.util.List;

public interface ICommand {
        void execute(ExecuteArgs var1);
        // get name of command
        String getName();

        String helpMessage();

        boolean needOwner();
        // command aliases
        default List<String> getAliases()
        {
            return List.of(); // use Array.asList if on java 8
        }
    }
