package bigman.commands;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.channel.unions.MessageChannelUnion;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.Random;

public class 求签command extends ListenerAdapter  {

    public String prefix = "b!g ";


    public void onMessageReceived(MessageReceivedEvent event) {
        //String[] args = event.getMessage().getContentRaw().split(" ");
        String[] msg = new String[3];
        msg[0] = "抽到第一签 大吉 ";
        msg[1] = "抽到第二签 小吉 ";
        msg[2] = "抽到第三签  ";

        String[] poetry = new String[3];
        poetry[0] = "七寶浮圖塔 高峰頂上安 眾人皆仰望 莫作等閒看";
        poetry[1] = "IUYYUG";
        poetry[2] = "fsjhef";



        String[] msgEng = new String[2];
        msgEng[0] = "You got No.1 Best Fortune";
        msgEng[1] = "No.2 Better Fortune";

        Random randomMsg = new Random();
        EmbedBuilder info = new EmbedBuilder();
        MessageChannelUnion messageChanel= event.getChannel();

        int number = randomMsg.nextInt(msg.length-1);

        if (event.getMessage().getContentRaw().equals(prefix + "求签")) {
            messageChanel.sendMessage("<@" + event.getAuthor().getId() + ">").queue();
            if (number == 0) {
                messageChanel.sendMessageEmbeds(info.setTitle(" " + msg[0]).setDescription(" " + poetry[0])
                        .addField("解签", "||就像出現了用美麗寶石做成的佛塔般地，似乎會有非常好的事情。" +
                                "因為能改用放眼萬事的立場，可以得到周圍的人們的信賴吧。" +
                                "合乎正道的你的行為，能被很多人的認同及鼓勵。" +
                                "不用隨便的態度看事情，用正確的心思會招來更多的好的結果。||", true)
                        .addField("回答", "||願望：會充分地實現吧。疾病：會治癒吧。盼望的人：會出現吧。遺失物：變得遲遲地才找到吧。蓋新居、搬家、嫁娶、旅行、交往等：全部很好吧。" +
                                "萬事行為謹慎。粗心大意行事的話，就會發生意想之外的災害吧。||", true)
                        .setColor(0xff8859).build()).queue();
            }

            if (number == 1) {
                messageChanel.sendMessageEmbeds(info.setTitle(" " + msg[1]).setDescription(" " + poetry[1]).setColor(0xff8859).build()).queue();
                ;
            }
            if (number == 2) {
                messageChanel.sendMessageEmbeds(info.setTitle(" " + msg[2]).setDescription(" " + poetry[2]).setColor(0xff8859).build()).queue();
                ;
            }

            info.clear();
        }

        Random randomMsgEng = new Random();
        if (event.getMessage().getContentRaw().equals(prefix + "fortune")) {

            if ((randomMsgEng.nextInt(msgEng.length) == 1)) {
                info.setTitle(" " + msgEng[(randomMsgEng.nextInt(msgEng.length))]);
                info.setDescription(" "+ poetry[1]);

                info.addField("Explain", "The tower of cloisonne with seven treasures is standing in a stately manner upon the high summit. "
                        + "People look at it and praise it. " + "You fortune is just like this tower. When you look at it more carefully, your fortune will be more excellent.", true);
                info.addBlankField(true);
                info.addField("Answers", "Your wishes will be realized. " + "A sick person will recover. "
                        + "The person you are waiting for will come soon. "
                        +"The lost article will be found but it will take a little while. "
                        + "Building a new house, moving, marriage, taking a trip, employment are all good. "
                        + "Be careful for everything that you want to do. "
                        + "If you are careless,unexpected disasters will occur. ", true);
            }
            if ((randomMsgEng.nextInt(msgEng.length) == 2))
            {
                info.setTitle(" " + msg[(randomMsg.nextInt(msg.length))]);
                info.setDescription("2222");
            }

        }

    }
}
