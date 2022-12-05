package bigman.commands;

import bigman.JDACommands.ExecuteArgs;
import bigman.JDACommands.ICommand;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.channel.unions.MessageChannelUnion;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class FortuneCHNommand implements ICommand {
    @Override
    public void execute(ExecuteArgs event) {
        try {
            Scanner s = new Scanner(new File("D:/2022 Fall/CM456 Capstone/bigman/src/main/java/bigman/files/fortuneCN.txt"));
            Scanner s1 = new Scanner(new File("D:/2022 Fall/CM456 Capstone/bigman/src/main/java/bigman/files/poetry.txt"));
            Scanner s2 = new Scanner(new File("D:/2022 Fall/CM456 Capstone/bigman/src/main/java/bigman/files/explain.txt"));
            ArrayList<String> fortuneCN = new ArrayList<String>(100);
            ArrayList<String> poetryList= new ArrayList<String>(100);
            ArrayList<String> explain= new ArrayList<String>(100);

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

            EmbedBuilder info = new EmbedBuilder();
            info.setFooter("From Tokyo Sensō-ji").setColor(0xff8859);

            Random randomMsg = new Random();


            int number = randomMsg.nextInt(fortuneCN.size()+1);

                event.channel().sendMessage("<@" + event.getAuthor().getId() + ">" + " 抽到第" + (number + 1) +" 签").queue();
            event.channel().sendMessageEmbeds(info.setTitle(fortuneCN.get(number))
                        .setDescription(poetryList.get(number)).addField("解签", "||" + explain.get(number) + "||", true).build()).queue();



        } catch (FileNotFoundException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public String getName() {
        return "求签";
    }



    @Override
    public boolean needOwner() {
        return false;
    }
}



