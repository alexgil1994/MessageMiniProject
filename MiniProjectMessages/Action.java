// TA COMMENTS EINAI GIA THN RepositoryInMemory. Sthn periptwsh xwris vash eprepe na pername kai to repository mesw tou Action dioti me thn dhmiourgia
// kainouriou RepositoryInMemory object tha xanotan otidhpote apothhkeumeno sthn lista me apotelesma na mhn exei ti na ektupwsei g ton xrhsth.
class Action {
    private int method;
//    private RepositoryInMemory repositoryInMem;
//
//    Action(int method, RepositoryInMemory repositoryInMem) {
//        this.method = method;
//        this.repositoryInMem = repositoryInMem;
//    }

    Action(int method) {
        this.method = method;
    }

    public int getMethod() {
        return method;
    }

//    public RepositoryInMemory getRepositoryInMem() {
//        return repositoryInMem;
//    }
//
//    public void newAction(int actionMethod, RepositoryInMemory repositoryInMemory) {
//        method = actionMethod;
//        repositoryInMem = repositoryInMemory;
//        setNewAction(method , repositoryInMem);
//    }

    public void newAction(int actionMethod) {
        method = actionMethod;
        setNewAction(method);
    }

//    private void setNewAction(int method , RepositoryInMemory repositoryInMem){
//        this.repositoryInMem = repositoryInMem;
//        this.method = method;
//    }

    private void setNewAction(int method){
        this.method = method;
    }
}