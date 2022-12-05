package bigman.commands;

import bigman.JDACommands.ExecuteArgs;
import bigmanTest.jdacommands.ExecuteArgs2;
import bigman.JDACommands.ICommand;
import net.dv8tion.jda.api.entities.emoji.Emoji;

public class react implements ICommand {
    public static final Emoji HEART = Emoji.fromUnicode("U+2764");
    @Override
    // Add heart reaction to a message
    public void execute(ExecuteArgs event) {
        event.getTextChannel().sendMessage("You got it, boss!").queue();
        event.getMessage().addReaction(HEART).queue();
    }

    @Override
    public String getName() {
        return "react";
    }


    @Override
    public boolean needOwner() {
        return false;
    }
}


