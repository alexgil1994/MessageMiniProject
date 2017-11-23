class Action {
    private int method;
    private RepositoryInMemory repositoryInMem;

    Action(int method, RepositoryInMemory repositoryInMem) {
        this.method = method;
        this.repositoryInMem = repositoryInMem;
    }

    public int getMethod() {
        return method;
    }

    public RepositoryInMemory getRepositoryInMem() {
        return repositoryInMem;
    }

    public void newAction(int actionMethod, RepositoryInMemory repositoryInMemory) {
        method = actionMethod;
        repositoryInMem = repositoryInMemory;
        setNewAction(method , repositoryInMem);
    }

    private void setNewAction(int method , RepositoryInMemory repositoryInMem){
        this.repositoryInMem = repositoryInMem;
        this.method = method;
    }
}