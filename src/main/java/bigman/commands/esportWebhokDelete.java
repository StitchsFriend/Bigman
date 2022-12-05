package bigman.commands;

import bigman.JDACommands.ExecuteArgs;
import bigman.JDACommands.ICommand;

public class esportWebhokDelete implements ICommand {


    @Override
    public void execute(ExecuteArgs event) {
        if (event.getGuild().getTextChannelsByName("big-esports-results", true).size() == 0)
        {
            event.getTextChannel().sendMessage("This channel/Webhook does not currently exist.").queue();
        }
        else
        {
            event.getGuild().getTextChannelsByName("big-esports-results", true).get(0).delete().queue();

            try{event.getGuild().getRolesByName("big sports", true).get(0).delete().queue();}
            catch(IndexOutOfBoundsException e)
            { }

            event.getTextChannel().sendMessage("This channel and Webhook were successfully deleted.").queue();
        }
    }

    @Override
    public String getName() {
        return "eDelete";
    }


    @Override
    public boolean needOwner() {
        return false;
    }
}
