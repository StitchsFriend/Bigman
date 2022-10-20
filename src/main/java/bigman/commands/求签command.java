package bigman.commands;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.Random;

public class 求签command {

    public String prefix = "b!g ";

    public void onMessageReceived(MessageReceivedEvent event) {
        //String[] args = event.getMessage().getContentRaw().split(" ");
        String[] msg = new String[1];
        msg[0] = "抽到第一签 大吉 ";
        // msg[1] = "抽到第二签 小吉 ";

        Random randomMsg = new Random();
        EmbedBuilder info = new EmbedBuilder();

        if (event.getMessage().getContentRaw().equals(prefix + "求签")) {

            info.setTitle(" " + msg[(randomMsg.nextInt(msg.length))]);

            if ((randomMsg.nextInt(msg.length) == 0)) {
                info.setDescription("七寶浮圖塔 高峰頂上安 眾人皆仰望 莫作等閒看");
                info.addField("签解", "就像出現了用美麗寶石做成的佛塔般地，似乎會有非常好的事情。" +
                        "因為能改用放眼萬事的立場，可以得到周圍的人們的信賴吧。" +
                        "合乎正道的你的行為，能被很多人的認同及鼓勵。" +
                        "不用隨便的態度看事情，用正確的心思會招來更多的好的結果。", true);
                info.addBlankField(true);
                info.addField("回答", "願望：會充分地實現吧。" + "疾病：會治癒吧。" + "盼望的人：會出現吧。" +
                        "遺失物：變得遲遲地才找到吧。" + "蓋新居、搬家、嫁娶、旅行、交往等：全部很好吧。" + "萬事行為謹慎。" +
                        "粗心大意行事的話，就會發生意想之外的災害吧。", true);

                //event.getChannel().sendMessage("").setEphemeral(true).queue();
            }
            info.setColor(0xff8859);
            event.getChannel().sendMessage("<@" + event.getAuthor().getId() + ">").setEmbeds(info.build()).complete();
            info.clear();
        }
    }
}
