import java.util.Scanner;
import java.util.ArrayList;
import javaClasses.Shoe;
import javaClasses.DateChecker;

public class Main {

    public static ArrayList<Shoe> shoes = new ArrayList<>();

    public static void main ( String[] args ) {
        while ( true ) {
            int choice;
            choice = askMenu();
            switch ( choice ) {
                case 1:
                    view();
                    break;
                case 2:
                    add();
                    break;
                case 3:
                    delete();
                    break;
                default:
                    quit();
                    return;
            }
        }
    }

    public static int askMenu() {
        Scanner scan = new Scanner(System.in);
        int choice;
        do {
            showMenu();
            System.out.print(">> ");
            try {
                choice = scan.nextInt();
            } catch ( Exception e ) {
                choice = 0;
            }
        } while ( choice < 1 || choice > 4 );
        return choice;
    }

    public static void showMenu() {
        System.out.println("Shoe Shop");
        System.out.println("=========");
        System.out.println("1. View Shoes");
        System.out.println("2. Add Shoes");
        System.out.println("3. Delete Shoes");
        System.out.println("4. Exit");
    }

    public static void quit() {
        System.out.println("Thank you for using this application!");
    }

    public static void view() {
        Scanner scan = new Scanner(System.in);
        if ( shoes.isEmpty() ) {
            System.out.println("No shoes available...");
        } else {
            showData();
        }
        System.out.println("Press enter to continue...");
        scan.nextLine();
    }


    public static void add() {
        Scanner scan = new Scanner(System.in);
        String name;
        do {
            System.out.print("Input shoe's name[name ends with shoe, example: \"Fire shoe\"]: ");
            name = scan.nextLine();
        } while ( name.length() < 5 || !name.endsWith(" shoe"));
        String category;
        do {
            System.out.print("Input shoe's category[Sneaker | Running | Boot] (case sensitive): ");
            category = scan.nextLine();
        } while ( !category.equals("Sneaker") && !category.equals("Running") && !category.equals("Boot"));
        String date;
        DateChecker dateCheck = new DateChecker();
        do {
            System.out.print("Input shoe's release[dd-mm-yyyy]: ");
            date = scan.nextLine();
            dateCheck.setDate(date);
        } while ( !dateCheck.isValid() );
        int price;
        do {
            System.out.print("Input shoe's price[more than or equals to 5000]: ");
            try {
                price = scan.nextInt();
                scan.nextLine();
            } catch ( Exception e ) {
                price = -1;
            }
        } while ( price < 5000 );
        Shoe thisShoe = new Shoe(name, category, date, price);
        shoes.add(thisShoe);
        System.out.println("Shoe added!");
        System.out.println("Press enter to continue...");
        scan.nextLine();
    }

    public static void delete() {
        Scanner scan = new Scanner(System.in);
        if ( shoes.isEmpty() ) {
            System.out.println("No shoes available...");
        } else {
            showData();
            int target;
            do {
                try {
                    System.out.printf("Choose shoe's number to delete[1...%d]: ", shoes.size());
                    target = scan.nextInt();
                    scan.nextLine();
                } catch ( Exception e ) {
                    target = -1;
                }
            } while ( target < 1 || target > shoes.size() );
            shoes.remove(target-1);
            System.out.println("Shoe removed!");
        }
        System.out.println("Press enter to continue...");
        scan.nextLine();
    }

    public static void showData() {
        for ( int i = 0 ; i < shoes.size() ; i++ ) {
            shoes.get(i).print(i+1);
        }
    }

}
