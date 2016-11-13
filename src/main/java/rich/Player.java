package rich;

public class Player {
    private Status status = Status.WAIT_FOR_COMMAND;
    private Command command;

    public Status getStatus() {
        return status;
    }

    public void execute(Command command) {
        this.command = command;
        status = this.command.execute(this);
    }

    public void respond(Response response) {
        status = command.respond(this, response);
    }

    public enum Status {WAIT_FOR_RESPONSE, TURN_END, WAIT_FOR_COMMAND}
}
