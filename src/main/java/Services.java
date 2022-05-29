public interface Services {
    int registerGroup(int customersNumber);

    void unregisterGroup(int groupNumber);

    int[] state();
}