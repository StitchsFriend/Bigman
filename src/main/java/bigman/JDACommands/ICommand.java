package bigman.JDACommands;


import bigmanTest.jdacommands.ExecuteArgs2;

import java.util.List;

public interface ICommand {
        void execute(ExecuteArgs event);
        // get name of command
        String getName();

        boolean needOwner();
        // command aliases
        default List<String> getAliases()
        {
            return List.of(); // use Array.asList if on java 8
        }


    }
