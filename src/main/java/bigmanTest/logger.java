package bigmanTest;

import bigman.BotStartUp;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.entities.channel.unions.MessageChannelUnion;
import net.dv8tion.jda.api.entities.emoji.Emoji;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.events.message.react.MessageReactionAddEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.managers.AudioManager;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.cache.CacheFlag;

import javax.annotation.Nonnull;
import javax.security.auth.login.LoginException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.EnumSet;


public class logger extends ListenerAdapter {
    // See https://emojipedia.org/red-heart/ and find the codepoints

    public String botPrefix = "b!g ";

    BotStartUp botStartUp = new BotStartUp();

    public static final Emoji HEART = Emoji.fromUnicode("U+2764");

    public void main(String[] args) throws IOException {
        // Possible ways to provide the token:


        // 1. From a file:


        // This would just be some text file with only the token in it
        // Use Files.readString in java 11+
        // String token = "MTAxNjg5NzIzNDkzNzMxNTM3OA.Gz2m-J._fUKFgUDfVJsPi76bcIArc7riN_mYcqA2APK18";

        // 2. Using environment variable:
        // String token = System.getenv("TOKEN");

        // 3. Using system property as -Dtoken=...
        // This leaks the token in command line (task manager) and thread dumps to any other users on the same machine
        // String token = System.getProperty("token");

        // 4. From the command line directly
        // This leaks the token in command line (task manager) to any other users on the same machine
        // String token = args[0];


        // Pick which intents we need to use in our code.
        // To get the best performance, you want to make the most minimalistic list of intents, and have all others disabled.
        // When an intent is enabled, you will receive events and cache updates related to that intent.
        // For more information:
        //
        // - The documentation for GatewayIntent: https://ci.dv8tion.net/job/JDA/javadoc/net/dv8tion/jda/api/requests/GatewayIntent.html
        // - The wiki page for intents and caching: https://jda.wiki/using-jda/gateway-intents-and-member-cache-policy/
/*
        EnumSet<GatewayIntent> intents = EnumSet.of(
                // Enables MessageReceivedEvent for guild (also known as servers)
                GatewayIntent.GUILD_MESSAGES,
                // Enables the event for private channels (also known as direct messages)
                GatewayIntent.DIRECT_MESSAGES,
                // Enables access to message.getContentRaw()
                GatewayIntent.MESSAGE_CONTENT,
                // Enables MessageReactionAddEvent for guild
                GatewayIntent.GUILD_MESSAGE_REACTIONS,
                // Enables MessageReactionAddEvent for private channels
                GatewayIntent.DIRECT_MESSAGE_REACTIONS,
                // Enables MessageReactionAddEvent for guild members
                GatewayIntent.GUILD_MEMBERS,
                // Enables MessageReactionAddEvent for guild voice states
                GatewayIntent.GUILD_VOICE_STATES
        );
*/

        // To start the bot, you have to use the JDABuilder.

        // You can choose one of the factory methods to build your bot:
        // - createLight(...)
        // - createDefault(...)
        // - create(...)
        // Each of these factory methods use different defaults, you can check the documentation for more details.

        try {
            // By using createLight(token, intents), we use a minimalistic cache profile (lower ram usage)
            // and only enable the provided set of intents. All other intents are disabled, so you won't receive events for those.
            /*
            JDA jda = JDABuilder.create(token, Arrays.asList(INTENTS))
                    // On this builder, you are adding all your event listeners and session configuration
                    .addEventListeners(new logger())
                    // You can do lots of configuration before starting, checkout all the setters on the JDABuilder class!
                    .setActivity(Activity.watching("for your messages"))
                    // Once you're done configuring your jda instance, call build to start and login the bot.
                    .enableCache(CacheFlag.VOICE_STATE)

                    .build();
(           */
            // Here you can now start using the jda instance before its fully loaded,
            // this can be useful for stuff like creating background services or similar.

            // The queue(...) means that we are making a REST request to the discord API server!
            // Usually, this is done asynchronously on another thread which handles scheduling and rate-limits.
            // The (ping -> ...) is called a lambda expression, if you're unfamiliar with this syntax it is HIGHLY recommended to look it up!
            botStartUp.bot.getRestPing().queue(ping ->
                    // shows ping in milliseconds
                    System.out.println("Logged in with ping: " + ping)
            );

            // If you want to access the cache, you can use awaitReady() to block the main thread until the jda instance is fully loaded
            botStartUp.bot.awaitReady();

            // Now we can access the fully loaded cache and show some statistics or do other cache dependent things
            System.out.println("Guilds: " + botStartUp.bot.getGuildCache().size());
        } catch (InterruptedException e) {
            // Thrown if the awaitReady() call is interrupted
            e.printStackTrace();
        }
    }

    // This overrides the method called onMessageReceived in the ListenerAdapter class
    // Your IDE (such as intellij or eclipse) can automatically generate this override for you, by simply typing "onMessage" and auto-completing it!
    @Override
    public void onMessageReceived(@Nonnull MessageReceivedEvent event) {

        // The user who sent the message
        User author = event.getAuthor();
        // This is a special class called a "union", which allows you to perform specialization to more concrete types such as TextChannel or NewsChannel
        MessageChannelUnion channel = event.getChannel();
        // The actual message sent by the user, this can also be a message the bot sent itself, since you *do* receive your own messages after all
        Message message = event.getMessage();

        // Check whether the message was sent in a guild / server
        if (event.isFromGuild()) {
            // This is a message from a server
            System.out.printf("[%s] [%#s] %#s: %s\n",
                    event.getGuild().getName(), // The name of the server the user sent the message in, this is generally referred to as "guild" in the API
                    channel, // The %#s makes use of the channel name and displays as something like #general
                    author,  // The %#s makes use of User#getAsTag which results in something like Minn#6688
                    message.getContentDisplay());// This removes any unwanted mention syntax and converts it to a readable string
        }


        //Reply to ping message with pong
        if (message.getContentRaw().equals( botPrefix + "ping"))
        {
            //System.out.printf("pingpong lets go");
            channel.sendMessage("pong!").queue();
        }

        //Add heart reaction to a message
        if (message.getContentRaw().equals( botPrefix + "react"))
        {
            channel.sendMessage("You got it, boss!").queue();
            message.addReaction(HEART).queue();
        }

        //@the user when they ask for it
        if (message.getContentRaw().equals( botPrefix + "mention"))
        {

            channel.sendMessage("Hello, <@" + author.getId()+">").queue();

        }
        // checking usr if in audio channel for using play music



        // Using specialization, you can check concrete types of the channel union

        if (channel.getType() == ChannelType.TEXT) {
            System.out.println("The channel topic is: " + channel.asTextChannel().getTopic());
        }

        if (channel.getType().isThread()) {
            System.out.println("This thread is part of channel #" +
                    channel.asThreadChannel()  // Cast the channel union to thread
                            .getParentChannel() // Get the parent of that thread, which is the channel it was created in (like forum or text channel)
                            .getName()          // And then print out the name of that channel
            );

        }
    }

}
