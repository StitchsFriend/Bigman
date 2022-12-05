package bigman.commands;

import bigman.JDACommands.ExecuteArgs;
import bigmanTest.jdacommands.ExecuteArgs2;
import bigman.JDACommands.ICommand;

public class PingPong implements ICommand {
        @Override
    public void execute(ExecuteArgs event) {
        event.getTextChannel().sendMessage("pong!").queue();
    }

    @Override
    public String getName() {
        return "ping";
    }


    @Override
    public boolean needOwner() {
        return false;
    }
}
