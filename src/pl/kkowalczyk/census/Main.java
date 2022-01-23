package pl.kkowalczyk.census;

import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        while (true)
        {
            System.out.println("Select action:");
            System.out.println("1. Add person");
            System.out.println("2. List people");
            System.out.println("3. Exit");

            Scanner scan = new Scanner(System.in);
            int val = scan.nextInt();

            if(val == 1)
            {
                System.out.println("Name:");
                String name = scan.next();
                System.out.println("Surname:");
                String surname = scan.next();
                scan.nextLine();
                System.out.println("Location:");
                String position = scan.nextLine();
                PersonalDataProviderProxy.Instance().ProvideData(name, surname, position);
                System.out.println("Person added!");
            }
            else if(val == 2)
            {
                System.out.println("People:");
                PersonalDataHolder.Instance().ListPeople();
            }
            else if(val == 3)
            {
                PersonalDataHolder.Instance().Save();
                FlyweightFactory.Instance().Save();
                System.exit(0);
            }
            else
            {
                System.out.println("Wrong action!");
            }
        }
    }
}

