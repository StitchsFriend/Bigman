package bigman.commands;

import bigman.JDACommands.ExecuteArgs;
import bigmanTest.jdacommands.ExecuteArgs2;
import bigman.JDACommands.ICommand;

public class mention implements ICommand {
    @Override
    //@the user when they ask for it
    public void execute(ExecuteArgs event) {
        event.getTextChannel().sendMessage("Hello, <@" + event.getAuthor().getId()+">").queue();

    }
    @Override
    public String getName() {
        return "mention";
    }

    @Override
    public boolean needOwner() {
        return false;
    }
}
