package bigmanTest.jdacommands;

import bigman.JDACommands.ICommand;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Iterator;

public class jdacommands extends ListenerAdapter {

    private ArrayList<IcommandTest> commands = new ArrayList();
    private final String prefix;

    public String getPrefix() {
        return this.prefix;
    }

    public jdacommands(String prefix) {
        this.prefix = prefix;
    }

    public ArrayList<IcommandTest> getCommands() {
        return this.commands;
    }

    public void setCommands(ArrayList<IcommandTest> commands) {
        this.commands = commands;
    }

    public void registerCommand(IcommandTest command) {this.commands.add(command);
    }

    private void init(MessageReceivedEvent event) {
        Iterator var2 = this.commands.iterator();

        IcommandTest command;
        do {
            if (!var2.hasNext()) {
                event.getChannel().sendMessage("This command wasn't recognized.").queue();
                return;
            }

            command = (IcommandTest)var2.next();
        } while(!event.getMessage().getContentRaw().split(" ")[0].equalsIgnoreCase(this.prefix + command.getName()));

        if (command.needOwner()) {
            if (event.getMember().isOwner()) {
                command.execute(new executeargs(event));
            } else {
                event.getChannel().sendMessage("You don't have the required permissions to use this command.");
            }
        } else {
            command.execute(new executeargs(event));
        }
    }

    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        if (event == null) {
            //$$$reportNull$$$0(0);
            event.getChannel().sendMessage("message receive event is null");
        }

        if (event.getMessage().getContentRaw().startsWith(this.prefix)) {
            this.init(event);
        }

    }
}

