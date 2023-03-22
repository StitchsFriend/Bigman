package bigman;

import bigman.JDACommands.ICommand;
import bigman.JDACommands.JDACommands;
import bigman.commands.*;

import bigmanTest.commands.playcommand;
import net.dv8tion.jda.api.*;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.Command;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.MemberCachePolicy;
import net.dv8tion.jda.api.utils.cache.CacheFlag;
import javax.security.auth.login.LoginException;
import java.util.Arrays;
import java.util.EventListener;
import java.util.HashMap;
import java.util.Map;

public class BotStartUp
{
    // INTENTS is basically what will allow the bot listen for
   public static final GatewayIntent[] INTENTS = {GatewayIntent.DIRECT_MESSAGES,GatewayIntent.GUILD_MESSAGES,GatewayIntent.GUILD_MESSAGE_REACTIONS,GatewayIntent.MESSAGE_CONTENT,
                                                    GatewayIntent.GUILD_VOICE_STATES, GatewayIntent.GUILD_MEMBERS, GatewayIntent.GUILD_PRESENCES, };

    public static JDA jda;
        public static void main(String[] args) throws LoginException, InterruptedException {

               JDACommands jdaCommands = new JDACommands("b!g");
               jdaCommands.registerCommand(new Play());
               jdaCommands.registerCommand(new PlaySkip());
               jdaCommands.registerCommand(new QueueCommand());
               jdaCommands.registerCommand(new PauseCommand());
               jdaCommands.registerCommand(new ResumeCommand());
               jdaCommands.registerCommand(new SkipCommand());
               jdaCommands.registerCommand(new StopCommand());
               jdaCommands.registerCommand(new NowPlayingCommand());

               jdaCommands.registerCommand(new PingPong());
               jdaCommands.registerCommand(new react());
               jdaCommands.registerCommand(new mention());

               jdaCommands.registerCommand(new diceRoller());
               jdaCommands.registerCommand(new FortuneCHNommand());
               jdaCommands.registerCommand(new FortuneENG());

               jdaCommands.registerCommand(new esportsWebhook());
               jdaCommands.registerCommand(new esportWebhokDelete());
              // jdaCommands.registerCommand(new HelpCommand());
              // jdaCommands.registerCommand(new logger());
              // jdaCommands.registerCommand(new roleReactions());

           jda =  JDABuilder.createDefault("BOT TOKEN", Arrays.asList(INTENTS))
                        .setMemberCachePolicy(MemberCachePolicy.ALL)
                        .setActivity(Activity.playing("b!g Splatoon3"))
                        .setStatus(OnlineStatus.ONLINE)
                        .enableCache(CacheFlag.VOICE_STATE)

                       // .addEventListeners(new JoinCommand())
                   .addEventListeners(new HelpCommand())
                   .addEventListeners(new logger())
                   .addEventListeners(new roleReactions())
                   .addEventListeners(jdaCommands)
                        .build();
            try {
                jda.getRestPing().queue(ping ->
                        System.out.println("Logged in with ping: " + ping)
                );

                jda.awaitReady();

                System.out.println("Guilds: " + jda.getGuildCache().size());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }


