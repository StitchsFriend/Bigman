package bigman.commands;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.channel.unions.MessageChannelUnion;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Fortune extends ListenerAdapter {
    public String prefix = "b!g ";
    public void onMessageReceived(MessageReceivedEvent event) {
        try {
            Scanner s = new Scanner(new File("D:/2022 Fall/CM456 Capstone/bigman/src/main/java/bigman/files/fortuneCN.txt"));
            Scanner s1 = new Scanner(new File("D:/2022 Fall/CM456 Capstone/bigman/src/main/java/bigman/files/poetry.txt"));
            Scanner s2 = new Scanner(new File("D:/2022 Fall/CM456 Capstone/bigman/src/main/java/bigman/files/explain.txt"));
            ArrayList<String> fortuneCN = new ArrayList<String>(100);
            ArrayList<String> poetryList= new ArrayList<String>(100);
            ArrayList<String> explain= new ArrayList<String>(100);


            Scanner s3 = new Scanner(new File("D:/2022 Fall/CM456 Capstone/bigman/src/main/java/bigman/files/fortuneEN.txt"));
            Scanner s4 = new Scanner(new File("D:/2022 Fall/CM456 Capstone/bigman/src/main/java/bigman/files/explainEN.txt"));
            ArrayList<String> fortuneEN = new ArrayList<String>(100);
            ArrayList<String> explainEN= new ArrayList<String>(100);

            while (s.hasNextLine())
            {
                fortuneCN.add(s.nextLine());
            }
            s.close();

            while (s1.hasNextLine())
            {
                poetryList.add(s1.nextLine());
            }
            s1.close();

            while (s2.hasNextLine())
            {
                explain.add(s2.nextLine());
            }
            s2.close();

            while (s3.hasNextLine())
            {
                fortuneEN.add(s3.nextLine());
            }
            s3.close();

            while (s4.hasNextLine())
            {
                explainEN.add(s4.nextLine());
            }
            s4.close();

            EmbedBuilder info = new EmbedBuilder();
            info.setFooter("From Tokyo Sensō-ji").setColor(0xff8859);

            Random randomMsg = new Random();
            MessageChannelUnion messageChanel= event.getChannel();
            int number = randomMsg.nextInt(fortuneCN.size()+1);
            int numberEng =randomMsg.nextInt(fortuneEN.size()+1);

            if (event.getMessage().getContentRaw().equals(prefix + "求签")) {
                messageChanel.sendMessage("<@" + event.getAuthor().getId() + ">" + " 抽到第" + (number + 1) +" 签").queue();
                messageChanel.sendMessageEmbeds(info.setTitle(fortuneCN.get(number)).setDescription(poetryList.get(number)).addField("解签", "||" + explain.get(number) + "||", true).build()).queue();
            }
            else if(event.getMessage().getContentRaw().equals(prefix + "fortune")) {
                messageChanel.sendMessage("<@" + event.getAuthor().getId() + ">" + " you got No." + (number + 1) +" fortune").queue();
                messageChanel.sendMessageEmbeds(info.setTitle(fortuneEN.get(numberEng)).setDescription(poetryList.get(numberEng)).addField("Explain", "||" + explainEN.get(numberEng) + "||", true).build()).queue();
            }

        } catch (FileNotFoundException ex) {
            throw new RuntimeException(ex);
        }


    }
}
