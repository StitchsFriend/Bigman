package bigman.commands;

import bigman.JDACommands.ExecuteArgs;
import bigman.JDACommands.ICommand;
import net.dv8tion.jda.api.EmbedBuilder;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class FortuneENG implements ICommand {
    @Override
    public void execute(ExecuteArgs event) {
        try {
            Scanner s1 = new Scanner(new File("D:/2022 Fall/CM456 Capstone/bigman/src/main/java/bigman/files/poetry.txt"));
        Scanner s3 = new Scanner(new File("D:/2022 Fall/CM456 Capstone/bigman/src/main/java/bigman/files/fortuneEN.txt"));
        Scanner s4 = new Scanner(new File("D:/2022 Fall/CM456 Capstone/bigman/src/main/java/bigman/files/explainEN.txt"));
        ArrayList<String> fortuneEN = new ArrayList<String>(100);
        ArrayList<String> explainEN= new ArrayList<String>(100);
        ArrayList<String> poetryList= new ArrayList<String>(100);
            while (s1.hasNextLine())
            {
                poetryList.add(s1.nextLine());
            }
            s1.close();
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
        Random randomMsg = new Random();
        int numberEng =randomMsg.nextInt(fortuneEN.size()+1);
            EmbedBuilder info = new EmbedBuilder();
            info.setFooter("From Tokyo Sensō-ji").setColor(0xff8859);

        event.channel().sendMessage("<@" + event.getAuthor().getId() + ">" + " you got No." + (numberEng + 1) +" fortune").queue();
        event.channel().sendMessageEmbeds(info.setTitle(fortuneEN.get(numberEng)).setDescription(poetryList.get(numberEng)).addField("Explain", "||" + explainEN.get(numberEng) + "||", true).build()).queue();
    }
        catch (FileNotFoundException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public String getName() {
        return "fortune";
    }


    @Override
    public boolean needOwner() {
        return false;
    }
}

