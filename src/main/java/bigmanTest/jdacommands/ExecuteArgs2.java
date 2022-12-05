package bigmanTest.jdacommands;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.emoji.EmojiUnion;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.events.message.react.MessageReactionAddEvent;
public class ExecuteArgs2 {
    private final String messageID;
    private final Member member;
    private final Guild guild;
    private final EmojiUnion emoji;

    protected ExecuteArgs2(MessageReactionAddEvent event)
    {

        this.member = event.getMember();
        this.messageID=event.getMessageId();
        this.guild = event.getGuild();
        this.emoji=event.getEmoji();
    }

    public  String getMessageId(){return this.messageID;}
    public  Member getMember(){return this.member;}
    public Guild getGuild() {return this.guild;}


    public EmojiUnion getEmoji() { return this.emoji;}
}
