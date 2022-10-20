package bigman.JDACommands;


import java.util.List;

public interface ICommand {
        void execute(ExecuteArgs var1);

        String getName();

        String helpMessage();

        boolean needOwner();
        default List<String> getAliases()
        {
            return List.of();
        }
    }
