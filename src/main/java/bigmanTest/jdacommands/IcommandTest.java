package bigmanTest.jdacommands;

import bigmanTest.jdacommands.executeargs;

public interface IcommandTest {
    void execute(executeargs var1);

    String getName();

    String helpMessage();

    boolean needOwner();
}
