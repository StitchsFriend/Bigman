package bigman.JDACommands;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.entities.channel.unions.MessageChannelUnion;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.events.message.react.MessageReactionAddEvent;

import java.security.PublicKey;
import java.util.PrimitiveIterator;

public class ExecuteArgs {
    private final TextChannel textChannel;
    private final Member selfMember;
    private final Member member;
    private final Guild guild;
    private final JDA jda;
    private final Message message;
    private final String[] args;
    private final GuildVoiceState selfVoiceState;
    private final GuildVoiceState memberVoiceState;
    private final User author;
    private final boolean fromGuild;
    private  final MessageChannelUnion channel;


    protected ExecuteArgs(MessageReceivedEvent event) {
        this.textChannel = event.getChannel().asTextChannel();
        this.member = event.getMember();
        this.guild = event.getGuild();
        this.jda = event.getJDA();
        this.message = event.getMessage();
        this.selfMember = this.guild.getSelfMember();
        this.args = this.message.getContentRaw().split(" ");
        this.selfVoiceState = this.selfMember.getVoiceState();
        this.memberVoiceState = this.member.getVoiceState();
        this.author = event.getAuthor();
        this.fromGuild= event.isFromGuild();
        this.channel = event.getChannel();
    }


    public TextChannel getTextChannel() {
        return this.textChannel;
    }

    public Member getSelfMember() {
        return this.selfMember;
    }

    public Member getMember() {
        return this.member;
    }

    public Guild getGuild() {
        return this.guild;
    }

    public JDA getJda() {
        return this.jda;
    }

    public Message getMessage() {
        return this.message;
    }

    public String[] getArgs() {
        return this.args;
    }

    public GuildVoiceState getSelfVoiceState() {
        return this.selfVoiceState;
    }

    public GuildVoiceState getMemberVoiceState() {
        return this.memberVoiceState;
    }

    public User getAuthor() {return this.author;}

    public boolean isFromGuild(){return this.fromGuild;}

    public MessageChannelUnion channel() {return this.channel;}

}
