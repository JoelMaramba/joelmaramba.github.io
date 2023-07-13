import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class App {
    static Scanner scanner = new Scanner(System.in);
    private Map<String, String> accounts;
    private Map<String, String> roles;
    static Map<String, ArrayList<String>> userFeedbacks = new HashMap<>();
    static String[][] studentInfo = new String[50][4];
    static String[] studentEntities = {"Full name: ", "Student No.: ", "Section: ", "Year: "};
    static String[][] records = new String[50][5];
    static String[] events = new String[5];
    static String[][] studentfeedback= new String[50][2];
    static String[] feedback = {"student name: ", "student feedback:"};
    static int totalfeedback = 0;
    static int totalEvents = 0;
    static int totalStudInfo = 0;
    static int totalRecords = 0;
    static String choose = "";

    public App() {
        accounts = new HashMap<>();
        roles = new HashMap<>();
    }

    public void registerAccount(String username, String password, String role) {
        try {
            if (accounts.containsKey(username)) {
                throw new IllegalArgumentException("Username already exists. Please choose a different username.");
            }

            accounts.put(username, password);
            roles.put(username, role);
            System.out.println("Account registered successfully.");
        } catch (IllegalArgumentException e) {
            System.out.println("Registration failed: " + e.getMessage());
        }
    }

    public String login(String username, String password) {
        try {
            if (!accounts.containsKey(username) || !accounts.get(username).equals(password)) {
                throw new IllegalArgumentException("Invalid username or password.");
            }

            return roles.get(username);
        } catch (IllegalArgumentException e) {
            System.out.println("Login failed: " + e.getMessage());
            return null;
        }
    }

    public static void main(String[] args) {
        App accountManager = new App();

        while (true) {
            System.out.println("1. Register Account");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            int choice;
            try {
                choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline
            } catch (Exception e) {
                System.out.println("Invalid input. Please try again.");
                scanner.nextLine(); // Consume invalid input
                continue;
            }

            switch (choice) {
                case 1:
                    System.out.print("Enter username: ");
                    String username = scanner.nextLine();
                    System.out.print("Enter password: ");
                    String password = scanner.nextLine();
                    System.out.print("Enter role (admin/user): ");
                    String role = scanner.nextLine();
                    accountManager.registerAccount(username, password, role);
                    break;
                case 2:
                    System.out.print("Enter username: ");
                    username = scanner.nextLine();
                    System.out.print("Enter password: ");
                    password = scanner.nextLine();
                    String userRole = accountManager.login(username, password);
                    if (userRole != null) {
                        System.out.println("Login successful. Welcome, " + username + "!");
                        if (userRole.equals("user")) {
                            userSelection();
                        } else if (userRole.equals("admin")) {
                            System.out.println("Performing admin actions...");
                            adminSelection();
                        }
                    }
                    break;
                case 3:
                    System.out.println("Exiting...");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }

            System.out.println();
        }
    }

    static void userSelection() {
        while (true) {
            if (totalEvents != 0) {
                System.out.print("\tAvailable Events\n");
                for (int i = 0; i < totalEvents; i++) {
                    System.out.println("[" + (i + 1) + "] " + events[i]);
                }
                System.out.print("\n\nchoose: ");
                choose = scanner.nextLine();
                if (choose.equals("0")) {
                    System.out.print("\tInvalid Input\n");
                } else {
                    String input = "";
                    System.out.println("\n\tStudent Info");
                    for (int j = 0; j < studentInfo[totalStudInfo].length; j++) {
                        if (studentEntities[j].equals(studentEntities[1])) {
                            do {
                                try {
                                    System.out.print(studentEntities[j]);
                                    input = scanner.nextLine();
                                    if (!input.matches("[0-9]+")) {
                                        throw new IllegalArgumentException("Invalid input. Please enter a number.");
                                    }
                                } catch (Exception e) {
                                    System.out.println("Invalid input: " + e.getMessage());
                                    continue;
                                }
                            } while (!input.matches("[0-9]+"));
                            studentInfo[totalStudInfo][j] = input;
                        } else if (studentEntities[j].equals(studentEntities[3])) {
                            do {
                                try {
                                    System.out.print(studentEntities[j]);
                                    input = scanner.nextLine();
                                    if (!input.matches("[0-9]+")) {
                                        throw new IllegalArgumentException("Invalid input. Please enter a number.");
                                    }
                                } catch (Exception e) {
                                    System.out.println("Invalid input: " + e.getMessage());
                                    continue;
                                }
                            } while (!input.matches("[0-9]+"));
                            studentInfo[totalStudInfo][j] = input;
                        } else {
                            do {
                                try {
                                    System.out.print(studentEntities[j]);
                                    input = scanner.nextLine();
                                    if (!containsText(input)) {
                                        throw new IllegalArgumentException("Invalid input. Please enter text.");
                                    }
                                } catch (Exception e) {
                                    System.out.println("Invalid input: " + e.getMessage());
                                    continue;
                                }
                            } while (!containsText(input));
                            studentInfo[totalStudInfo][j] = input;
                        }
                    }
                    totalStudInfo++;
                    if (Integer.parseInt(choose) <= totalEvents) { // used to record all information of a student.
                        records[totalRecords][0] = events[Integer.parseInt(choose) - 1];
                        records[totalRecords][1] = studentInfo[totalRecords][0];
                        records[totalRecords][2] = studentInfo[totalRecords][1];
                        records[totalRecords][3] = studentInfo[totalRecords][2];
                        records[totalRecords][4] = studentInfo[totalRecords][3];
                        totalRecords++;
                        break;
                    }
                }
            } else {
                System.out.print("\tEvents is not available, wait for the admin\n");
                break;
            }

        }
    }

    static boolean isExist(String search) {
        boolean isExist = false;
        for (int i = 0; i < totalEvents; i++) {
            if (search.equalsIgnoreCase(events[i])) {
                isExist = true;
            }
        }
        return isExist;
    }

    static void adminSelection() {
        while (true) {
            System.out.print("\tAdmin\n[1] Add Event\n[2] Delete Event\n[3] Edit Event\n[4] Search Event\n[5] Display all available events\n[6] Records\n[7] View feedback\n[0] Logout\n: ");
            choose = scanner.nextLine();
            if (choose.equals("0")) {
                break;
            }
            switch (choose) {
                case "1":
                    if (totalEvents != 4) {

                        do {
                            try {
                                System.out.print("\n\tAdd Event\nenter event name: ");
                                choose = scanner.nextLine();
                                if (!containsText(choose)) {
                                    throw new IllegalArgumentException("Invalid input. Please enter text.");
                                } else if (!isExist(choose)) {
                                    events[totalEvents] = choose;
                                    totalEvents++;
                                    System.out.println("\n\tentered event was add.\n");
                                } else {
                                    System.out.println("\n\tentered event already exists.\n");
                                }
                            } catch (Exception e) {
                                System.out.println("Invalid input: " + e.getMessage());
                                continue;
                            }
                        } while (!containsText(choose));

                    } else {
                        System.out.println("\n\tThe maximum events to add has been reached.");
                    }
                    System.out.println("\n");
                    break;
                case "2":
                    if (totalEvents != 0) {
                        boolean found = false;
                        do {
                            try {
                                System.out.print("\n\tDelete Event\nenter event name: ");
                                choose = scanner.nextLine();
                                if (!containsText(choose)) {
                                    throw new IllegalArgumentException("Invalid input. Please enter text.");
                                } else {
                                    for (int i = 0; i < totalEvents; i++) {
                                        if (choose.equalsIgnoreCase(events[i])) {
                                            found = true;
                                            while (i < totalEvents) {
                                                events[i] = events[i + 1];
                                                i++;
                                            }
                                            totalEvents--;
                                            break;
                                        }
                                    }
                                }
                            } catch (Exception e) {
                                System.out.println("Invalid input: " + e.getMessage());
                                continue;
                            }
                        } while (!containsText(choose));
                        if (!found) {
                            System.out.println("\tNot Found\n");
                        } else {
                            System.out.println("\tEvent was deleted.\n");
                        }
                    } else {
                        System.out.println("\tEvents are not available, add an event first\n");
                    }
                    System.out.println("\n");
                    break;
                case "3":
                    if (totalEvents != 0) {
                        boolean found = false;
                        do {
                            try {
                                System.out.print("\n\tEdit Event\nenter event name: ");
                                choose = scanner.nextLine();
                                if (!containsText(choose)) {
                                    throw new IllegalArgumentException("Invalid input. Please enter text.");
                                } else {
                                    for (int i = 0; i < totalEvents; i++) {
                                        if (choose.equalsIgnoreCase(events[i])) {
                                            found = true;
                                            System.out.print("enter new event name: ");
                                            String newEvent = scanner.nextLine();
                                            events[i] = newEvent;
                                            break;
                                        }
                                    }
                                }
                            } catch (Exception e) {
                                System.out.println("Invalid input: " + e.getMessage());
                                continue;
                            }
                        } while (!containsText(choose));
                        if (!found) {
                            System.out.println("\tNot Found\n");
                        } else {
                            System.out.println("\n\tEvent was changed.\n");
                        }
                    } else {
                        System.out.println("\tEvents are not available, add an event first\n");
                    }
                    System.out.println("\n");
                    break;
                case "4":
                    if (totalEvents != 0) {
                        boolean found = false;
                        do {
                            try {
                                System.out.print("\n\tSearch Event\nenter event name: ");
                                choose = scanner.nextLine();
                                if (!containsText(choose)) {
                                    throw new IllegalArgumentException("Invalid input. Please enter text.");
                                } else {
                                    for (int i = 0; i < totalEvents; i++) {
                                        if (choose.equalsIgnoreCase(events[i])) {
                                            found = true;
                                            System.out.println("\n\n\tFound!\nEvent Name: " + events[i]);
                                            break;
                                        }
                                    }
                                }
                            } catch (Exception e) {
                                System.out.println("Invalid input: " + e.getMessage());
                                continue;
                            }
                        } while (!containsText(choose));
                        if (!found) {
                            System.out.println("\tNot Found\n");
                        }
                    } else {
                        System.out.println("\tEvents are not available, add an event first\n");
                    }
                    System.out.println("\n");
                    break;
                case "5":
                    if (totalEvents != 0) {
                        System.out.println("\n\tAvailable Events");
                        for (int i = 0; i < totalEvents; i++) {
                            System.out.println("[" + (i + 1) + "] " + events[i]);
                        }
                    } else {
                        System.out.println("\n\tEvents are not available, add an event first\n");
                    }
                    System.out.println("\n");
                    break;
                case "6":
                    if (totalRecords != 0) {
                        System.out.println("\tRecords\n");
                        for (int i = 0; i < totalRecords; i++) {
                            System.out.println("\n\tEvent Name: " + records[i][0]);
                            System.out.println("\t" + studentEntities[0] + records[i][1]);
                            System.out.println("\t" + studentEntities[1] + records[i][2]);
                            System.out.println("\t" + studentEntities[2] + records[i][3]);
                            System.out.println("\t" + studentEntities[3] + records[i][4]);
                        }
                    } else {
                        System.out.println("\tRecords are empty, perform user actions first\n");
                    }
                    System.out.println("\n");
                    break;
                    case"7":
                    if (totalRecords != 0) {
                        System.out.println("\tRecords\n");
                        for (int i = 0; i < totalRecords; i++) {
                            System.out.println("\n\tEvent Name: " + records[i][0]);
                            System.out.println("\t" + studentEntities[0] + records[i][1]);
                            System.out.println("\t" + studentEntities[1] + records[i][2]);
                            System.out.println("\t" + studentEntities[2] + records[i][3]);
                            System.out.println("\t" + studentEntities[3] + records[i][4]);
                        }
                    } else {
                        System.out.println("\tRecords are empty, perform user actions first\n");
                    }
                    

                    

                default:
                    System.out.println("\tInvalid input\n");
            }
        }
    }

    static boolean containsText(String str) {
        return str.matches("[a-zA-Z]+");
    }
}
