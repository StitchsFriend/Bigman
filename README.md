# bigmanTest

Big Man - A Discord Bot
--------------------------------------------------
Project by:
Xuan Lu, Edward Baker

--------------------------------------------------
Features:
-Join multiple servers
-Help command
--help
-Music commands
--play <keyword or url>
--pause
--resume
--playskip
--queue
--nowplaying
--stop
-Moderation Commands
--roles
-Fun Commands
--roll <# of rolls>d<sides of dice>
--fortune
-eSports
--eAdd
--eDelete

-------------------------------------------------
Necessary Applications:
IntelliJ IDEA
Maven compiler for IntelliJ IDEA
Discord

-------------------------------------------------
To get Big Man to work on YOUR Discord server:
1. Go to https://discord.com/developers/applications and create a new application. 
   Since Big Man is not public, you will need your own bot to run his code. 
2. Select your application, go to Bot, and selete Add a Bot. 
   This is the bot you will add to your server.
3. Scroll to the bottom of the bot's page and select the Administrator permission. 
   This will give the bot proper permission.
4. Open IntelliJ IDEA and navigate to the Big Man directory. Open the pom.xml file.
5. Use the Maven add-on to compile the Big Man folder.
6. On the Bot profile on Discord, copy the bot token.
7. Navigate to the BotStartUp.class file.  
   bigmanTest-main -> src -> main -> java -> bigman -> BotStartUp.class
   Open it in the editor. On line 62,
   replace the string "insert your bot token here" with your bot's token.
8. Add the Bot to a server you have Administrator privelages in. 
   (This will most likely be a server you or someone you trust owns)
9. Give the bot the Big Man role.
10. Run the BotStartUp.class file.
11. Congratulations! Big Man works on your server.

