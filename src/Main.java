import java.util.Scanner;
import java.util.ArrayList;
import javaClasses.Shoe;
import java.util.Random;

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
        do {
            System.out.print("Input shoe's release[dd-mm-yyyy]: ");
            date = scan.nextLine();
        } while ( !verifyDate(date) );
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
        Shoe thisShoe = new Shoe(name, category, date, randomNumber(), price);
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
            System.out.printf("%d %s-%s\n", i+1, shoes.get(i).getShoeName(), shoes.get(i).getProductCode());
            System.out.println("======================");
            System.out.printf("Category: %s\n", shoes.get(i).getCategory());
            System.out.printf("Release Date: %s\n", shoes.get(i).getReleaseDate());
            System.out.printf("Price: %d\n\n", shoes.get(i).getPrice());
        }
    }

    public static String randomNumber() {
        Random randomizer = new Random();
        int code = randomizer.nextInt(1000);
        String stringCode = "SH";
        for ( int i = 0 ; i < 3-Math.ceil(Math.log10(code)) ; i++ ) {
            stringCode = stringCode.concat("0");
        }
        stringCode = stringCode.concat(toString(code));
        return stringCode;
    }

    public static String toString( int number ) {
        String result = "";
        int denominator = (int) Math.pow(10, (int) Math.floor(Math.log10(number)));
        while ( denominator > 0 ) {
            int digit = number/denominator;
            result = result + (char)(digit + '0');
            number %= denominator;
            denominator /= 10;
        }
        return result;
    }

    public static boolean verifyDate( String date ) {
        if ( date.length() != 10 ) {
            return false;
        }
        if ( date.charAt(2) != '-' || date.charAt(5) != '-' ) {
            return false;
        }
        boolean leapYear = false;
        int dateYear = ((date.charAt(6) - '0') * 1000) + ((date.charAt(7) - '0') + 100) + ((date.charAt(8) - '0') * 10) + (date.charAt(9) - '0');
        if ( dateYear % 100 == 0 && dateYear % 400 == 0 ) {
            leapYear = true;
        }
        if ( dateYear % 100 != 0 && dateYear % 4 == 0 ) {
            leapYear = true;
        }
        int[] monthDays = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        int dateMonth = ((date.charAt(3) - '0') * 10) + ((date.charAt(4)) - '0');
        if ( dateMonth < 1 || dateMonth > 12 ) {
            return false;
        }
        int dateDate = ((date.charAt(0) - '0') * 10) + (date.charAt(1) - '0');
        if ( leapYear && dateMonth == 2 ) {
            return dateDate >= 1 && dateDate <= 29;
        } else {
            return dateDate >= 1 && dateDate <= monthDays[dateMonth - 1];
        }
    }

}
