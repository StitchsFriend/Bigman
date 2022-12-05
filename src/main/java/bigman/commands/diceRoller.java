package bigman.commands;
import bigman.JDACommands.ExecuteArgs;
import bigmanTest.jdacommands.ExecuteArgs2;
import bigman.JDACommands.ICommand;
import java.util.*;

public class diceRoller implements ICommand
{
    @Override
    public void execute(ExecuteArgs event) {
        //Cut the message to only get the important data (rolls)
        String command = event.getMessage().getContentRaw().substring(9);
        int rollsStart = 0;
        int rollsEnd = command.indexOf("d");
        ArrayList<Integer> rolls = new ArrayList<Integer>();
        int total = 0;
        Random rand = new Random();

        String numberOfRolls = command.substring(rollsStart, rollsEnd);
        //If a user doesn't type a leading number, it will automatically roll 1 die
        if(numberOfRolls.equals(""))
            numberOfRolls="1";

        String diceFacets = command.substring(rollsEnd + 1);
        Integer.parseInt(diceFacets);

        for(int i=0; i<Integer.parseInt(numberOfRolls); i++)
        {
            int count = 0;
            count = rand.nextInt(Integer.parseInt(diceFacets)) + 1;
            total = total + count;
            rolls.add(i,count);
        }
        event.getTextChannel().sendMessage("**Total:** " + total + "\n" + "**Details: **" + rolls.toString()).queue();
    }

    @Override
    public String getName() {
        return "roll";
    }



    @Override
    public boolean needOwner() {
        return false;
    }
}