package bigman.commands;


import bigman.BotStartUp;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.Random;

public class FortuneCommand extends ListenerAdapter {
    public String prefix = "b!g ";


    public void onMessageReceived(MessageReceivedEvent event) {

        String[] msgEng = new String[1];
        msgEng[0] = "You got No.1 Best Fortune";
        // msgEng[1] = "No.2 Better Fortune";

        Random randomMsgEng = new Random();
        EmbedBuilder infoEng = new EmbedBuilder();


        if (event.getMessage().getContentRaw().equals(prefix + "fortune")) {

            infoEng.setTitle(" " + msgEng[(randomMsgEng.nextInt(msgEng.length))]);

            if ((randomMsgEng.nextInt(msgEng.length) == 0)) {
                infoEng.setDescription("七寶浮圖塔 高峰頂上安 眾人皆仰望 莫作等閒看");

                infoEng.addField("Explain", "The tower of cloisonne with seven treasures is standing in a stately manner upon the high summit. "
                        + "People look at it and praise it. " + "You fortune is just like this tower. When you look at it more carefully, your fortune will be more excellent.", true);
                infoEng.addBlankField(true);
                infoEng.addField("Answers", "Your wishes will be realized. " + "A sick person will recover. "
                        + "The person you are waiting for will come soon. " +
                        "The lost article will be found but it will take a little while. "
                        + "Building a new house, moving, marriage, taking a trip, employment are all good. "
                        + "Be careful for everything that you want to do. "
                        + "If you are careless,unexpected disasters will occur. ", true);
            }
        }

        infoEng.setColor(0xff8859);
        event.getChannel().sendMessage("<@" + event.getAuthor().getId() + ">").setEmbeds(infoEng.build()).complete();
        infoEng.clear();

    }
}


