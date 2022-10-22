package bigman.commands;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.channel.unions.MessageChannelUnion;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.utils.FileUpload;

import java.util.Random;

public class FortuneCommand extends ListenerAdapter  {

    public String prefix = "b!g ";
  /*  public void MessageEmbed(String title, String des, int color, MessageEmbed.Footer footer, List<MessageEmbed.Field> fields)
    {
        EmbedBuilder info = new EmbedBuilder();


    }

   */

    public void onMessageReceived(MessageReceivedEvent event) {
        //String[] args = event.getMessage().getContentRaw().split(" ");
        String[] msg = new String[5];
        msg[0] = "大吉";
        msg[1] = "小吉";
        msg[2] = "凶";
        msg[3] = "吉";
        msg[4] = "凶";
        /*
        msg[5] = "末吉";
        msg[6] = "凶";
        msg[7] = "大吉";
        msg[8] = "大吉";
        msg[9] = "大吉";
        msg[10] = "大吉";
        msg[11] = "大吉";
        msg[12] = "大吉";
        msg[13] = "末吉";
        msg[14] = "凶";
        msg[15] = "吉";
        msg[16] = "凶";
        msg[17] = "吉";
        msg[18] = "末小吉";
        msg[19] = "吉";
        msg[20] = "吉";


         */
        String[] poetry = new String[5];
        poetry[0] = "七寶浮圖塔 高峰頂上安 眾人皆仰望 莫作等閒看";
        poetry[1] = "月被浮雲翳 立事自昏迷 幸乞陰公祐 何慮不開眉";
        poetry[2] = "愁惱損忠良 青宵一炷香 雖然防小過 閑慮覺時長";
        poetry[3] = "累有興雲志 君恩祿未封 若逢侯手印 好事始總總";
        poetry[4] = "家道未能昌 危々保禍殃 暗雲侵月桂 佳人一炷香";
        /*
        poetry[5] = "宅墓鬼凶多 人事有爻訛 傷財防損失 祈福始中和";
        poetry[6] = "登舟待便風 月色暗朦朧 欲輾香輪去 高山千万重";
        poetry[7] = "勿頭中見尾 文華須得理 禾刀自偶然 当遇非常喜";
        poetry[8] = "有名須得遇 三望一朝遷 貴人来指処 華果応時鮮";
        poetry[9] = "舊用多成破 新更始見財 改求雲外望 枯木遭春開";
        poetry[10] = "有禄興家業 文華達帝都 雲中乗好箭 兼得貴人扶";
        poetry[11] = "楊柳遇春時 残花発旧枝 重々霜雪裡 黄金色更輝";
        poetry[12] = "手把大陽輝 東君發舊枝 稼苗方欲秀 猶更上雲梯";
        poetry[13] = "玉石未分時 憂心轉更悲 前途通大道 花發應殘枝";
        poetry[14] = "年乖數亦孤 久病未能蘇 岸危舟未發 龍臥失明珠";
        poetry[15] = "破改重成望 前途喜亦寧 貴人相助處 祿馬照前程";
        poetry[16] = "怪異防憂惱 人宅見分離 惜華還值雨 杯酒惹閑非";
        poetry[17] = "離暗出明時 麻衣變綠衣 舊憂終是退 遇祿應交輝";
        poetry[18] = "家道生荊棘 兒孫防虎威 香前祈福厚 方得免分離";
        poetry[19] = "月出漸分明 家財每每興 何言先有滯 更變立功名";
        poetry[20] = "洗出經年否 光華得再清 所求終吉利 重日照前程";



         */
       String[] msgEng = new String[5];
       msgEng[0] = "Best Fortune";
       msgEng[1] = "Better Fortune";
       msgEng[2] ="Bad Fortune Lack";
        msgEng[3] ="Good Fortune";
        msgEng[4] ="Bad Fortune Lack";
        /*
        msgEng[5] ="Good Fortune In Future";
        msgEng[6] ="Bad Fortune";
        msgEng[7] ="Best Fortune";
        msgEng[8] ="Best Fortune";
        msgEng[9] ="Best Fortune";
        msgEng[10] ="Best Fortune";
        msgEng[11] ="Best Fortune";
        msgEng[12] ="Best Fortune";
        msgEng[13] ="Good Fortune In Future";
        msgEng[14] ="Bad Fortune";
        msgEng[15] ="Good Fortune";
        msgEng[16] ="Bad Fortune";
        msgEng[17] ="Good Fortune";
        msgEng[18] ="Good Fortune In Future";
        msgEng[19] ="Good Fortune";
        msgEng[20] ="Good Fortune";


         */

        Random randomMsg = new Random();
        EmbedBuilder info = new EmbedBuilder();
        MessageChannelUnion messageChanel= event.getChannel();

        info.setFooter("From Tokyo Sensō-ji").setColor(0xff8859);

        int number = randomMsg.nextInt(msg.length-1);

        if (event.getMessage().getContentRaw().equals(prefix + "求签")) {
            messageChanel.sendMessage("<@" + event.getAuthor().getId() + ">"+" 抽到第"+(number+1)+"签").queue();
            if (number == 0) {
                messageChanel.sendMessageEmbeds(info.setTitle(msg[0]).setDescription(" " + poetry[0])
                        .addField("解签", "||就像出現了用美麗寶石做成的佛塔般地，似乎會有非常好的事情。" +
                                "因為能改用放眼萬事的立場，可以得到周圍的人們的信賴吧。" +
                                "合乎正道的你的行為，能被很多人的認同及鼓勵。" +
                                "不用隨便的態度看事情，用正確的心思會招來更多的好的結果。||", true)
                        .addField("回答", "||願望：會充分地實現吧。疾病：會治癒吧。盼望的人：會出現吧。遺失物：變得遲遲地才找到吧。蓋新居、搬家、嫁娶、旅行、交往等：全部很好吧。" +
                                "萬事行為謹慎。粗心大意行事的話，就會發生意想之外的災害吧。||", true)
                        .build()).queue();
            }

            if (number == 1) {
                messageChanel.sendMessageEmbeds(info.setTitle(msg[1]).setDescription(" " + poetry[1])
                                .addField("解签","||似乎抱著強烈的願望，但是照目前的樣子，似乎無法達成願望。因為光是想著要怎麼作，持續著沒有決心的情形。" +
                                        "為了人而變得盡全力努力，幸福將會來到。似乎會有令人高興的事情發生。" +
                                        "根據這件好事，不擔心未來的事也沒有關係了。 ||",true)
                        .addField("回答","||願望：因為持續不斷地努力必定會實現。疾病：雖然拖長，但是之後可以康復吧。" +
                                "盼望的人：遲遲地才出現吧。遺失物：不能找出來吧。交往：要節制吧。蓋新居、搬家：都不壞吧。結親緣、旅行：順利進行吧。||",true)
                        .build()).queue();
                ;
            }
            if (number == 2) {
                messageChanel.sendMessageEmbeds(info.setTitle(" " + msg[2]).setDescription(" " + poetry[2])
                        .addField("解签","||層層疊疊嘆氣與苦惱，被回報的事很少吧。就像向著天燒香祈禱般地，你的願望無法傳達天聽吧。" +
                                "雖這樣說，但就算只有一點點善行也好，做了可以逃離災厄吧。東想西想之間，似乎不知不覺就像過了很長的時間。" +
                                "等待時機的到來吧。||",true)
                        .addField("回答","||願望：難實現吧。疾病：雖然拖長，但是會治好吧。遺失物：難以找到吧。盼望的人：要花很久的時間吧。" +
                                "旅行：因為很壞，放棄吧。蓋新居、搬家：勉勉強強地算好吧。結婚交往：要節制吧。||" ,true)
                        .build()).queue();
                ;
            }
            if (number == 3) {
                messageChanel.sendMessageEmbeds(info.setTitle(" " + msg[3]).setDescription(" " + poetry[3])
                        .addField("解签","||拼命地要出人頭地，可以看出你的志向。但是遺憾地是，你的不成熟還不能得到居上位者的" +
                                "認同。然而，就像是如果已經寫了好文章的話，就立刻得到認同般地，好好傳遞自己的心思" +
                                "是很重要的。好事也似乎會越來越接踵而起吧。||",true)
                        .addField("回答","||願望：能實現吧。如果這樣的話，終生幸福吧。疾病：變得遲遲地才會治好吧。遺失物：遲遲地才找到吧。" +
                                "盼望的人：會出現吧。旅行：途中要忍耐各式各樣的困難吧。蓋新居、搬家、結親緣、交往：萬事都好吧。||",true)
                        .build()).queue();
                ;
            }


            info.clear();
        }

        if (event.getMessage().getContentRaw().equals(prefix + "fortune")) {
            messageChanel.sendMessage("<@" + event.getAuthor().getId() + ">"+" got No."+(number+1)+" fortune").queue();
            if (number == 0) {
                messageChanel.sendMessageEmbeds(info.setTitle(msgEng[0]).setDescription(" "+ poetry[0]).addField("Explain", "The tower of cloisonne with seven treasures is standing in a stately manner upon the high summit. "
                        + "People look at it and praise it. " + "You fortune is just like this tower. When you look at it more carefully, your fortune will be more excellent.", true)
                                .addField("Answers", "Your wishes will be realized. " + "A sick person will recover. "
                                        + "The person you are waiting for will come soon. "
                                        +"The lost article will be found but it will take a little while. "
                                        + "Building a new house, moving, marriage, taking a trip, employment are all good. "
                                        + "Be careful for everything that you want to do. "
                                        + "If you are careless, unexpected disasters will occur. ", true)
                        .build()).queue();

            }
            if ((number== 1))
            {
                messageChanel.sendMessageEmbeds(info.setTitle(msgEng[1]).setDescription(" "+ poetry[1]).addField("","",true)
                        .addField("","",true).build()).queue();
            }

        }



    }
}
