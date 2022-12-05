package bigman.commands;

import bigman.JDACommands.ExecuteArgs;
import bigman.JDACommands.ICommand;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.*;
import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.function.Consumer;

public class esportsWebhook implements ICommand
{
    @Override
    public void execute(ExecuteArgs event) {
        //check if the big esports results channel already exists
        if(event.getGuild().getTextChannelsByName("big-esports-results",true).size()>=1)
        {
            event.getTextChannel().sendMessage("This channel/Webhook is already implemented.").queue();
            return;
        }
        else {
            List<TextChannel> createdTextChannel;

            Role everyone = event.getGuild().getPublicRole();

            Role eSporter = null;
            //check to see if the role already exists
            try{eSporter = event.getGuild().getRolesByName("Big Sports", false).get(0);}
            catch(IndexOutOfBoundsException e)
            { }
            if(eSporter == null) {
                event.getGuild().createRole().setName("Big Sports").complete();
            }
            eSporter =  event.getGuild().getRolesByName("Big Sports", false).get(0);
            Role BigMan = event.getGuild().getRolesByName("Big Man", false).get(0);
            Permission view = Permission.VIEW_CHANNEL;
            Permission write = Permission.MESSAGE_SEND;
            //create the new text channel with specific permission overrides
            //for public role (@everyone), the eSports role, and Big Man
            event.getGuild().createTextChannel("Big-eSports-Results")
                    .addRolePermissionOverride(everyone.getIdLong(), null, Collections.singleton(view)) //@everyone role cant view channel
                    .addRolePermissionOverride(eSporter.getIdLong(), Collections.singleton(view), Collections.singleton(write)) //big sporter role can view channel
                    .addRolePermissionOverride(BigMan.getIdLong(), EnumSet.of(view,write), null)
                    .complete();
            createdTextChannel =  event.getGuild().getTextChannelsByName("big-esports-results", true);
            final TextChannel eSportsTextChannel = createdTextChannel.get(0);
            //create the new Webhook, on successful creation do accept method
            eSportsTextChannel.createWebhook("Big Man eSports").queue(new Consumer<Webhook>() {
                @Override
                public void accept(Webhook webhook) {
                    event.getTextChannel().sendMessage("A new text channel has been created for this Webhook. " +
                            "Refer to #Big-eSports-Results for more details.").queue();
                    String eSportsURL = webhook.getUrl();
                    String test1 = eSportsURL.substring(0,24);
                    String test2 = eSportsURL.substring(28);
                    eSportsURL = test1 + test2;
                    EmbedBuilder valSetup = new EmbedBuilder();
                    valSetup.setTitle("Big Man Valorant eSports Setup", null);
                    valSetup.setColor(new Color(255, 84, 97));
                    valSetup.setDescription("A brief walkthrough on setting up the Big Man Valorant eSports Webhook");
                    valSetup.addField("Setup", "To setup this Webhook, first go to this link: "
                            + "https://www.vlr.gg/settings (You **will** need a vlr.gg account.) Then, copy this URL: "
                            + eSportsURL + " and paste it into the **Discord Channel Webhook URL** field. Once saved, "
                            + "This Webhook will post professional Valorant content to your server.", false);
                    eSportsTextChannel.sendMessageEmbeds(valSetup.build()).queue();
                }
            });
        }
    }

    @Override
    public String getName() {
        return "eAdd";
    }




    @Override
    public boolean needOwner() {
        return false;
    }
}