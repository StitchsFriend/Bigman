package bigman.JDACommands;


    public interface ICommand {
        void execute(ExecuteArgs var1);

        String getName();

        String helpMessage();

        boolean needOwner();
    }
