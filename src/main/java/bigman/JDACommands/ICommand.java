package bigman.JDACommands;


import net.dv8tion.jda.api.requests.RestAction;

import java.util.List;

public interface ICommand {
        void execute(ExecuteArgs event);
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
