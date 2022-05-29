import java.util.Scanner;

public class Main {
    static String NO_CUSTOMER_TEXT = "FREE";

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String line = null;
        Manager swimpoolManager = new Manager(10);
        String menu = " 1 - register \n 2 - unregister \n 3 - show state \n 4 - exit";

        System.out.println("Swimming Pool opens up! \n Hi! \n");
        int groupId = - 1;

        while (true) {
            System.out.println();
            System.out.println(menu);

            int command = readCommandFromLine(input);
            System.out.println("\nread: " + line);

            switch (command) {
                case 1:
                    System.out.println(" Registering a new group.\n How many customers came in?\n");
                    int customers = readCommandFromLine(input);
                    groupId = swimpoolManager.registerGroup(customers);
                    System.out.println("group #" + groupId + " with " + customers + " registered");
                    break;
                case 2:
                    System.out.println("Which group goes out?\n");
                    groupId = readCommandFromLine(input);
                    swimpoolManager.unregisterGroup(groupId);
                    break;
                case 3:
                    System.out.println("Current state is:\n");
                    System.out.println(stringifyState(swimpoolManager.state()));
                    System.out.println();
                    break;
                case 4:
                    System.out.println("Swimming Pool closes up! \nGoodbye!");
                    System.exit(0);

                default:
                    System.out.println("Try again...");
            }
        }
    }

    private static int readCommandFromLine(Scanner input) {
        String line = null;

        try {
            if (input.hasNext()) {
                line = input.nextLine();
                System.out.println("read: " + line);
                return Integer.parseInt(line);
            }
        } catch (NumberFormatException nfex) {
            System.out.println(String.format("cannot parse number '%s'", line));
            return -1;
        }
        return -1;

    }

    private static String stringifyState(int[] placesAtPool) {
        StringBuilder state = new StringBuilder();
        for (int i = 0; i < placesAtPool.length; i++)
            state.append( String.format(" %d) %s \n",
                    i,
                    placesAtPool[i] == -1 ? NO_CUSTOMER_TEXT : String.valueOf(placesAtPool[i]) + " group")
            );
        return state.toString();
    }
}
