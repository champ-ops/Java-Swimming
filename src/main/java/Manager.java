public class Manager implements Services {
    int NO_CUSTOMER = -1;
    int places[];
    static int groupIdCounter = 0;

    public Manager(int placesNumber) {
        this.places = new int[placesNumber];
        for (int i = 0; i < placesNumber; i++) this.places[i] = NO_CUSTOMER;
    }

    @Override
    public int registerGroup(int customersNumber) {
        validate(customersNumber);

        boolean lookedAroundPool = false;
        int startedFrom = -1;
        int accum = 0;
        int curPos = -1;

        while (!lookedAroundPool && ++curPos < this.places.length) {
            if (curPos >= this.places.length) {
                curPos = 0;
                lookedAroundPool = true;
            }

            if (this.places[curPos] == -1) {
                if (accum == 0) {
                    startedFrom = curPos;
                    accum++;
                } else {
                    accum++;
                }
            } else {
                startedFrom = -1;
                accum = 0;
            }

            if (accum == customersNumber) {
                markPlaces(startedFrom, customersNumber, groupIdCounter);
                break;
            }
        }

        return groupIdCounter++;
    }

    @Override
    public void unregisterGroup(int groupNumber) {
        for (int i = 0; i < this.places.length; i++)
            if (this.places[i] == groupNumber) this.places[i] = NO_CUSTOMER;
    }

    @Override
    public int[] state() {
        return this.places;
    }

    private void markPlaces(int startedFrom, int customesNumber, int groupId) {
        int pos = startedFrom;
        while (customesNumber-- > 0) {
            this.places[pos++] = groupId;

            if (pos == this.places.length) pos = 0;
        }
    }

    void validate(int customersNumber) {
        if (customersNumber > this.places.length) throw new IllegalArgumentException(
                String.format("Group(%d customers) is too big(%d places all)", customersNumber, this.places.length)
        );

        if (customersNumber < 1) throw new IllegalArgumentException(
                String.format("Must be a positive number of customers(given %d)", customersNumber)
        );
    }

}
