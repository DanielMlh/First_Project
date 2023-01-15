import CoinsType.Coin;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        System.out.println("Welcome to currency converter");
        choiceScreen();
        showResult();
    }
    static ArrayList<String>resultsList=new ArrayList<>();
    static void choiceScreen(){
        Scanner scannerNum=new Scanner(System.in);
       Scanner scannerTxt=new Scanner(System.in);
        String userChoice="";
        CoinFactory coinFactory=new CoinFactory();

        //--------main function screen1------
        do {
            int choiceCurrency = 0;
            double input = 0;
            System.out.println("Please choose ayn option (1/2):" + '\n'
                                + "1. Dollars to Shekels" + '\n'
                                + "2. Shekels to Dollars");
            for (int i=5; i>=0; i--){
                try {
                    choiceCurrency=scannerNum.nextInt();
                }
                 catch (InputMismatchException e) {
                    scannerNum.next();
                 }

                if (choiceCurrency==1 || choiceCurrency==2){
                    System.out.println("Please enter an amount to convert");
                    //--------validation function currency selection------
                    for (int ii=5; ii>=0; ii--) {
                        try {
                            input = scannerNum.nextDouble();
                        } catch (InputMismatchException e) {
                            scannerNum.next();
                        }
                        if (input > 0) {
                            Coin currencyCoin = coinFactory.getCoinInstance(Coins.values()[choiceCurrency - 1]);
                            currencyCoin.calculate(input);
                            ii = 0;
                            System.out.println(currencyCoin.calculate(input)+currencyCoin.getSimbol());
                            resultsList.add(String.valueOf(currencyCoin.calculate(input))+currencyCoin.getSimbol());
                        } else {
                            System.out.println("Wrong choice, please try again" + '\n'
                                    + "You have " + ii + " attempts");
                        }
                    }

                    i = 0;
                }
                else {
                    System.out.println("Wrong choice, please try again" + '\n'
                            + "You have "+ i + " attempts");
                }

            }
//--------validation function Y/N selection------
            for (int i=1; i!=0;i++){
                System.out.println("to start over? Y / N");
                userChoice=scannerTxt.nextLine().toLowerCase();
                if (userChoice.equals("y")|| userChoice.equals("n")){
                    i=-1;
                }
                else {
                    System.out.println("Wrong choice, please try again");
                }
            }

        }while (userChoice.equals("y"));
        System.out.println("Thanks for using our currency converter");
        
    }
    //--------function printing and creating a list of results, screen 4------
    static void showResult(){
        String filePath="C:\\Users\\D\\Downloads\\23-11_אוטומציה\\Class_7\\First Project\\First_Project\\ListResult.txt";
        File file=new File(filePath);
        Desktop desktop=Desktop.getDesktop();
        for (String i:resultsList

        ) {
            System.out.println(i);
            try {
                Files.write(Paths.get(filePath),resultsList);

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        try {
            desktop.open(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
