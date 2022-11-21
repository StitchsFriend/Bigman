package bigman.JDACommands;

import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.requests.RestAction;

public class MessageContext {
    private final Message message;

    public MessageContext(Message message) {
        this.message = message;
    }


    public RestAction<?> reply(String message) {
        return this.message.reply(message);
    }

    public RestAction<?> replyEmbeds(MessageEmbed embed) {
        return this.message.replyEmbeds(embed);
    }

    public MessageChannel getChannel() {
        return message.getChannel();
    }

    public Guild getGuild() {
        return message.getGuild();
    }


    public User getAuthor() {
        return message.getAuthor();
    }


    public User getUser() {
        return getAuthor();
    }


    public Member getMember() {
        return message.getMember();
    }
}
