import java.util.Scanner;
import java.io.*;
import java.util.ArrayList;

public class ContactList {
    public static ArrayList<Contact> loadContacts(ArrayList<Contact> initContact) throws FileNotFoundException {
        FileReader contacts = new FileReader("list.txt");
        Scanner filescanner = new Scanner(contacts);
        ArrayList<Contact> result = initContact;
        while (filescanner.hasNextLine()) {

            boolean foundcomma = false;
            String name = "";
            String number = "";
            String linetext = filescanner.nextLine();

            for (int i = 0; i < linetext.length(); i++) {

                if (!foundcomma) {

                    if (linetext.charAt(i) == ',') {
                        foundcomma = true;
                    } else {
                        name = name + linetext.substring(i, i + 1);
                    }

                } else {
                    if (linetext.charAt(i) == '\n') {
                        foundcomma = false;
                    } else {
                        number = number + linetext.substring(i, i + 1);
                    }
                }

            }
            result.add(new Contact(number, name));

        }
        filescanner.close();
        return result;
    }

    public static int Search(ArrayList<Contact> initContact, String name) {

        for (int i = 0; i < initContact.size(); i++) {

            if (initContact.get(i).getName().equals(name)) {
                return i;
            }

        }
        return -1;

    }

    public static void addContact(ArrayList<Contact> initContact) {
        Scanner scanner2 = new Scanner(System.in);
        System.out.print("Please input a name for the Contact: ");
        String name = scanner2.nextLine();
        System.out.print("Please input the number for the Contact: ");
        String number = scanner2.nextLine();
        if (initContact.size() == 0) {
            initContact.add(new Contact(number, name));
        } else {
            for (int i = 0; i < initContact.size(); i++) {

                if (initContact.get(i).getName().compareTo(name) > 0) {
                    initContact.add(i, new Contact(number, name));
                    System.out.println();
                    break;
                } else if (i == initContact.size() - 1) {
                    initContact.add(new Contact(number, name));
                    System.out.println();
                    break;
                }

            }
        }

    }

    public static void findContact(ArrayList<Contact> initContact) {
        Scanner scanner3 = new Scanner(System.in);
        System.out.print("Please enter the name of the Contact that you are searching for: ");
        String name = scanner3.nextLine();

        if (Search(initContact, name) != -1) {
            System.out.println();
            System.out.println(initContact.get(Search(initContact, name)));
        } else {
            System.out.println(name + " not found");
        }
    }

    public static void removeContact(ArrayList<Contact> initContact) {
        Scanner scanner4 = new Scanner(System.in);
        System.out.print("Please enter the name of the Contact that you want to remove: ");
        String name = scanner4.nextLine();
        System.out.println();
        if (Search(initContact, name) != -1) {
            initContact.remove(Search(initContact, name));
            System.out.println(name + " removed successfully");
            System.out.println();
        } else {
            System.out.println(name + " not found");
            System.out.println();
        }
    }

    public static void showAll(ArrayList<Contact> initContact) {
        for (Contact con : initContact) {
            System.out.println(con);
        }
    }

    public static void main(String[] args) throws IOException {
        Scanner scanner1 = new Scanner(System.in);
        ArrayList<Contact> Contact = new ArrayList<Contact>();
        Contact = loadContacts(Contact);

        while (true) {
            System.out.print(
                    "   Menu\n1 - Add phone number\n2 - Find Contact\n3 - Remove Contact\n4 - Show All\n5 - Save Contacts\n6 - Exit\nEnter Selection: ");
            int input = scanner1.nextInt();
            System.out.println();
            if (input == 1) {
                addContact(Contact);
            } else if (input == 2) {
                findContact(Contact);
            } else if (input == 3) {
                removeContact(Contact);
            } else if (input == 4) {
                showAll(Contact);
            } else if (input == 5) {
                FileWriter ContactListtxt = new FileWriter("list.txt", false);
                for (int i = 0; i < Contact.size(); i++) {
                    ContactListtxt.write(Contact.get(i).getName() + "," + Contact.get(i).getPhoneNumber() + "\n");
                }
                ContactListtxt.close();
                System.out.println("Contacts saved.");
                System.out.println();
            } else if (input == 6) {
                break;
            } else {
                System.out.println("Invalid input.");
                System.out.println();
            }
        }

        scanner1.close();

    }
}

class Contact {
    private final String phonenumber;
    private final String name;

    public Contact(String initphonenumber, String initname) {
        phonenumber = initphonenumber;
        name = initname;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phonenumber;
    }

    public String toString() {
        return ("Name: " + name + "\n" + "Phone: " + phonenumber + "\n" + "--------------------");
    }
}