package bigman.JDACommands;

import net.dv8tion.jda.api.entities.MessageReaction;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.events.message.react.MessageReactionAddEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class JDACommands extends ListenerAdapter {

    private ArrayList<ICommand> commands = new ArrayList();
    private final String prefix;

    public String getPrefix() {
        return this.prefix;
    }

    public JDACommands(String prefix) {
        this.prefix = prefix;
    }

    public ArrayList<ICommand> getCommands() {
        return this.commands;
    }

    public void setCommands(ArrayList<ICommand> commands) {
        this.commands = commands;
    }

    public void registerCommand(ICommand command) {
        this.commands.add(command);
    }


    private void init(MessageReceivedEvent event) throws NoSuchElementException {
        Iterator var2 = this.commands.iterator();
        String []comContext = event.getMessage().getContentRaw().split(" ");
        String com =comContext[0]+" "+comContext[1];
        String roles="b!g roles";
        String forture= "b!g 求签";
        ICommand command ;
      //  System.out.println(com ); //b!g play
        do {
            command = (ICommand)var2.next();
            try {
                if (!var2.hasNext()) {
                    event.getChannel().sendMessage("This command wasn't recognized.").queue();
                    return;
                }
            }catch (NoSuchElementException e){}


           // System.out.println(command); // bigman.commands.Play@1c27a836
           // System.out.println(command.getName()); // play
        // when command
        } while(!com.equalsIgnoreCase(this.prefix +" "+ command.getName())  );
       // System.out.println(getPrefix());
        //  !play     =                    // !play
       // while(!event.getMessage().getContentRaw().split(" ").equalsIgnoreCase(this.prefix + command.getName()));
                                                            // b!g play lover= b!g play
        //while(!event.getMessage().getContentRaw().split(" ")[0].equalsIgnoreCase(this.prefix + command.getName()));
        if (command.needOwner()) {
            if (event.getMember().isOwner()) {
                command.execute(new ExecuteArgs(event));

            } else {
                event.getChannel().sendMessage("You don't have the required permissions to use this command.");
            }
        } else {
            command.execute(new ExecuteArgs(event));
        }
    }
    public void onMessageReceived  (@NotNull MessageReceivedEvent event) throws NoSuchElementException{
        if (event == null) {
           // $$$reportNull$$$0(0);
            event.getChannel().sendMessage("message receive event is null");
        }

        if (event.getMessage().getContentRaw().startsWith(this.prefix) && !event.getMessage().getContentRaw().equalsIgnoreCase("b!g roles") && !event.getMessage().getContentRaw().equalsIgnoreCase("b!g help")) {
            this.init(event);
        }
    }

    }



