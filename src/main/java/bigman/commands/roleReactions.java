package bigman.commands;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.entities.emoji.Emoji;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.events.message.react.*;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import javax.annotation.Nonnull;
import java.awt.*;
import java.util.List;
import java.util.function.Consumer;

public class roleReactions extends ListenerAdapter
{
    public String  prefix = "b!g ";
    public static final Emoji MUSIC = Emoji.fromUnicode("U+1F3B6");
    public static final Emoji GAMING = Emoji.fromUnicode("U+1F3AE");
    public static final Emoji FORTUNE = Emoji.fromUnicode("U+1F52E");
    public static final Emoji SQUID = Emoji.fromUnicode("U+1F991");
    public String embedID;
    @Override
    public void onMessageReceived(@Nonnull MessageReceivedEvent event)
    {
        Message message = event.getMessage();
        Guild guild = event.getGuild();

        if(message.getContentRaw().startsWith(prefix + "roles"))
        {
            Role eSporter = null;
            try{eSporter = event.getGuild().getRolesByName("Big Sports", false).get(0);}
            catch(IndexOutOfBoundsException e)
            { }
            if(eSporter == null) {
                guild.createRole().setName("Big Sports").complete();
            }

            Role DJ = null;
            try{DJ = event.getGuild().getRolesByName("DJ", false).get(0);}
            catch(IndexOutOfBoundsException e)
            { }
            if(DJ == null) {
                guild.createRole().setName("DJ").complete();
            }

            Role funStuff = null;
            try{funStuff = event.getGuild().getRolesByName("Big Random", false).get(0);}
            catch(IndexOutOfBoundsException e)
            { }
            if(funStuff == null) {
                guild.createRole().setName("Big Random").complete();
            }

            Role woomy = null;
            try{woomy = event.getGuild().getRolesByName("Woomy", false).get(0);}
            catch(IndexOutOfBoundsException e)
            { }
            if(woomy == null) {
                guild.createRole().setName("Woomy").complete();
            }

            EmbedBuilder roleEmbed = new EmbedBuilder()
                    .setTitle("Big Man Role Reaction Setup", null)
                    .setColor(new Color(87, 84, 255))
                    .setDescription("React to this message to toggle the role associated")
                    .addField("", "\uD83E\uDD91 - Woomy: **All** Big Man commands", false)
                    .addField("", "\uD83C\uDFB6 - DJ: Music commands", false)
                    .addField("", "\uD83C\uDFAE - eSports: View the eSports channel", false)
                    .addField("", "\uD83D\uDD2E - Random: Misc. commands (Fortune, Dice Roller)", false);
            event.getChannel().sendMessageEmbeds(roleEmbed.build()).queue(new Consumer<Message>() {
                @Override
                public void accept(Message message) {
                    message.addReaction(SQUID).queue();
                    message.addReaction(MUSIC).queue();
                    message.addReaction(GAMING).queue();
                    message.addReaction(FORTUNE).queue();
                    embedID = message.getId();
                }
            });
        };
    }
    @Override
    public void onMessageReactionAdd (@Nonnull MessageReactionAddEvent event)
    {
        Member reactor = event.getMember();
        String messageID = event.getMessageId();
        Guild guild = event.getGuild();

        if(messageID.equals(embedID))
        {
            Role Squid = event.getGuild().getRolesByName("Woomy", false).get(0);
            Role Music = event.getGuild().getRolesByName("DJ", false).get(0);
            Role Gaming = event.getGuild().getRolesByName("Big Sports", false).get(0);
            Role Random = event.getGuild().getRolesByName("Big Random", false).get(0);

            if(event.getEmoji().asUnicode().equals(SQUID))
            {
                List<Role> roles = reactor.getRoles();
                if(!roles.contains(Squid))
                {
                    guild.addRoleToMember(reactor, Squid).queue();
                }
            }
            if(event.getEmoji().asUnicode().equals(MUSIC))
            {
                List<Role> roles = reactor.getRoles();
                if(!roles.contains(Music))
                {
                    guild.addRoleToMember(reactor, Music).queue();
                }
            }
            if(event.getEmoji().asUnicode().equals(GAMING))
            {
                List<Role> roles = reactor.getRoles();
                if(!roles.contains(Gaming))
                {
                    guild.addRoleToMember(reactor, Gaming).queue();
                }
            }
            if(event.getEmoji().asUnicode().equals(FORTUNE))
            {
                List<Role> roles = reactor.getRoles();
                if(!roles.contains(Random))
                {
                    guild.addRoleToMember(reactor, Random).queue();
                }
            }
        }

    }
    @Override
    public void onMessageReactionRemove (@Nonnull MessageReactionRemoveEvent event)
    {
        String user = event.getUserId();
        String messageID = event.getMessageId();
        Guild guild = event.getGuild();

        if(messageID.equals(embedID))
        {
            Role Squid = event.getGuild().getRolesByName("Woomy", false).get(0);
            Role Music = event.getGuild().getRolesByName("DJ", false).get(0);
            Role Gaming = event.getGuild().getRolesByName("Big Sports", false).get(0);
            Role Random = event.getGuild().getRolesByName("Big Random", false).get(0);

            if(event.getEmoji().asUnicode().equals(SQUID))
            {
                guild.removeRoleFromMember(UserSnowflake.fromId(user), Squid).queue();
            }
            if(event.getEmoji().asUnicode().equals(MUSIC))
            {
                guild.removeRoleFromMember(UserSnowflake.fromId(user), Music).queue();
            }
            if(event.getEmoji().asUnicode().equals(GAMING))
            {
                guild.removeRoleFromMember(UserSnowflake.fromId(user), Gaming).queue();
            }
            if(event.getEmoji().asUnicode().equals(FORTUNE))
            {
                guild.removeRoleFromMember(UserSnowflake.fromId(user), Random).queue();
            }
        }
    }
}