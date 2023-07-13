import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class sc1 {
    static Scanner scanner = new Scanner(System.in);
    private static Map<String, String> accounts;
    private static Map<String, String> roles;
    static ArrayList<String> studentfeed = new ArrayList<>();
    static String[][] studentInfo = new String[50][4];
    static String[] studentEntities = {"Full name: ", "Student No.: ", "Section: ", "Year: "};
    static String[][] records = new String[50][5];
    static String[] events = new String[5];
    static String[][] studentfeedback = new String[50][2];
    static String[] feedback = {"student name: ", "student feedback.: "};
    static int totalEvents = 0;
    static int totalStudInfo = 0;
    static int totalstudentfeedback=0;
    static int totalRecords = 0;
    static String choose = "";

    public static  void main(String[] args) {
        accounts = new HashMap<>();
        roles = new HashMap<>();
        accountmanagement();
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

    public static void accountmanagement() {
        sc1 accountManager = new sc1();

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
    private static void main() {
        sc1 accountManager = new sc1();

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
            try {
                System.out.println("User Management");
                System.out.println("[1] Send Feedback");
                System.out.println("[2] Select Event");
                System.out.println("[0] Log Out");
                System.out.print(": ");
                int select = scanner.nextInt();
                scanner.nextLine();
    
                switch (select) {
                    case 1:
                        System.out.println("Feedback Management");
                        System.out.print("Enter your name: ");
                        String name = scanner.nextLine();
                        System.out.print("Enter your message: ");
                        String feedback = scanner.nextLine();
                        System.out.println();
    
                        studentfeed.add(name);
                        studentfeed.add(feedback);
                        totalstudentfeedback++;
                        break;
                    case 2:
                        if (totalEvents != 0) {
                            System.out.println("\tAvailable Events");
                            for (int i = 0; i < totalEvents; i++) {
                                System.out.println("[" + (i + 1) + "] " + events[i]);
                            }
                            System.out.print("\nChoose: ");
                            choose = scanner.nextLine();
                            if (choose.equals("0")) {
                                System.out.println("\tInvalid Input\n");
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
                                            } catch (IllegalArgumentException e) {
                                                System.out.println("Invalid input: " + e.getMessage());
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
                                            } catch (IllegalArgumentException e) {
                                                System.out.println("Invalid input: " + e.getMessage());
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
                                            } catch (IllegalArgumentException e) {
                                                System.out.println("Invalid input: " + e.getMessage());
                                            }
                                        } while (!containsText(input));
                                        studentInfo[totalStudInfo][j] = input;
                                    }
                                }
                                totalStudInfo++;
                                if (Integer.parseInt(choose) <= totalEvents) {
                                    records[totalRecords][0] = events[Integer.parseInt(choose) - 1];
                                    records[totalRecords][1] = studentInfo[totalRecords][0];
                                    records[totalRecords][2] = studentInfo[totalRecords][1];
                                    records[totalRecords][3] = studentInfo[totalRecords][2];
                                    records[totalRecords][4] = studentInfo[totalRecords][3];
                                    totalRecords++;
                                }
                            }
                        } else {
                            System.out.println("\tEvents are not available, please wait for the admin.");
                        }
                        break;
                    case 0:
                        accountmanagement();
                        return;
                    default:
                        System.out.println("Invalid input. Please try again.");
                }
            } catch (Exception e) {
                System.out.println("Invalid input. Please try again.");
                scanner.nextLine(); // Consume invalid input
            }
        }
    }
    
    static void adminSelection() {
        while (true) {
            System.out.print("\tAdmin" +
                    "\n[1] Add Event" +
                    "\n[2] Delete Event" +
                    "\n[3] Edit Event" +
                    "\n[4] Search Event" +
                    "\n[5] Display allavailable events" +
                    "\n[6] Records" +
                    "\n[7] View Feedback" +
                    "\n[0] Logout" +
                    "\n: ");
            choose = scanner.nextLine();
            switch (choose) {
                case "1":
                    if (totalEvents != 4) {

                        do {
                            System.out.print("\n\tAdd Event\nenter event name: ");
                            choose = scanner.nextLine();
                            if (!containsText(choose)) {
                                System.out.println("\tInvalid Input");
                            } else if (!isExist(choose)) {
                                events[totalEvents] = choose;
                                totalEvents++;
                                System.out.println("\n\tentered event was add.\n");
                            } else {
                                System.out.println("\n\tentered event is already exist.\n");
                            }
                        } while (!containsText(choose));

                    } else {
                        System.out.println("\n\tThe maximum events to add had reached.");
                    }
                    System.out.println("\n");
                    break;
                case "2":
                    if (totalEvents != 0) {
                        boolean found = false;
                        do {
                            System.out.print("\n\tDelete Event\nenter event name: ");
                            choose = scanner.nextLine();
                            if (!containsText(choose)) {
                                System.out.println("\tInvalid Input");
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
                        } while (!containsText(choose));
                        if (!found) {
                            System.out.println("\tNot Found\n");
                        } else {
                            System.out.println("\tEvent was deleted.\n");
                        }
                    } else {
                        System.out.println("\tEvents is not available, add event first\n");
                    }
                    System.out.println("\n");
                    break;
                case "3":
                    if (totalEvents != 0) {
                        boolean found = false;
                        do {
                            System.out.print("\n\tEdit Event\nenter event name: ");
                            choose = scanner.nextLine();
                            if (!containsText(choose)) {
                                System.out.println("\tInvalid Input");
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
                        } while (!containsText(choose));
                        if (!found) {
                            System.out.println("\tNot Found\n");
                        } else {
                            System.out.println("\n\tEvent was changed.\n");
                        }
                    } else {
                        System.out.println("\tEvents is not available, add event first\n");
                    }
                    System.out.println("\n");
                    break;
                case "4":
                    if (totalEvents != 0) {
                        boolean found = false;
                        do {
                            System.out.print("\n\tSearch Event\nenter event name: ");
                            choose = scanner.nextLine();
                            if (!containsText(choose)) {
                                System.out.println("\tInvalid Input");
                            } else {
                                for (int i = 0; i < totalEvents; i++) {
                                    if (choose.equalsIgnoreCase(events[i])) {
                                        found = true;
                                        System.out.println("\n\n\tFound!\nEvent Name: " + events[i]);
                                        break;
                                    }
                                }
                            }
                        } while (!containsText(choose));
                        if (!found) {
                            System.out.println("\tNot Found\n");
                        }
                    } else {
                        System.out.println("\tEvents is not available, add event first\n");
                    }
                    System.out.println("\n");
                    break;
                case "5":
                    if (totalEvents != 0) {
                        System.out.println("\tAll Added Events");
                        for (int i = 0; i < totalEvents; i++) {
                            System.out.println((i + 1) + ". " + events[i]);
                        }
                    } else {
                        System.out.println("\tEvents is not available, add event first\n");
                    }
                    System.out.println("\n");
                    break;
                case "6":
                    if (totalEvents != 0) {
                        if (totalRecords != 0) {
                            for (int i = 0; i < totalRecords; i++) {
                                System.out.println("\t" + records[i][0]);
                                for (int j = 0, c = 1; j < 4; j++) {
                                    System.out.println(studentEntities[j] + records[i][c]);
                                    c++;
                                }
                            }
                        } else {
                            System.out.println("\tNo records yet, add the student info. first\n");
                        }
                    } else {
                        System.out.println("\tEvents is not available, add event first\n");
                    }
                    System.out.println("\n");
                    break;
                case "7":
                    int count = 0;
                    for(int i = 0; i < totalstudentfeedback; i++){
                        System.out.println("Feedback(s) #" + (i + 1));
                        System.out.println("Name: " + studentfeed.get(count));
                        System.out.println("Feedback: " + studentfeed.get(1 + count));
                        count+=2;
                        System.out.println();

                    }
                    adminSelection();

                case "0":
                    accountmanagement();


                default:
                    System.out.println("\tInvalid Input");
            }
        }
    }

    private static boolean isExist(String choose2) {
        return false;
    }

    static boolean containsText(String str) {
        return str.matches(".*[a-zA-Z0-9\\p{Punct}]+.*");
    }
}