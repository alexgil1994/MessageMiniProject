// Action object gets created with every new request from the user. This action object gets passed as an argument in the ActionToRun class in order to run the desired request.
class Action {
    private int method;

    Action(int method) {
        this.method = method;
    }

    public int getMethod() {
        return method;
    }

}