package com.company;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    private static Scanner sc = new Scanner(System.in);

    private static ArrayList<Room> rooms = new ArrayList<>();

    public static void main(String[] args) {
	    printTitle();

        String userChoice;

        boolean running = true;
        while (running)
        {
            System.out.printf("Please enter one of the following:%n" +
                    "0. Exit%n" +
                    "1. Create a room%n" +
                    "2. Delete a room%n" +
                    "3. Paint a room%n" +
                    "4. Display a room's dimensions%n" +
                    "5. List Rooms%n" +
                    "RoomShell > ");
            userChoice = sc.nextLine();
            switch (userChoice)
            {
                case "0":
                    System.out.println("Program Terminated");
                    running = false;
                    break;
                case "1":
                    createRoom();
                    break;
                case "2":
                    deleteRoom();
                    break;
                case "3":
                    paintRoom();
                    break;
                case "4":
                    displayDimensions();
                    break;
                case "5":
                    listRooms();
                    break;
                default:
                    System.out.println("Invalid Choice");
                    break;
            }
        }

    }

    private static void printTitle()
    {
        System.out.printf("   ▄████████  ▄██████▄   ▄██████▄    ▄▄▄▄███▄▄▄▄   %n" +
                "  ███    ███ ███    ███ ███    ███ ▄██▀▀▀███▀▀▀██▄ %n" +
                "  ███    ███ ███    ███ ███    ███ ███   ███   ███ %n" +
                " ▄███▄▄▄▄██▀ ███    ███ ███    ███ ███   ███   ███ %n" +
                "▀▀███▀▀▀▀▀   ███    ███ ███    ███ ███   ███   ███ %n" +
                "▀███████████ ███    ███ ███    ███ ███   ███   ███ %n" +
                "  ███    ███ ███    ███ ███    ███ ███   ███   ███ %n" +
                "  ███    ███  ▀██████▀   ▀██████▀   ▀█   ███   █▀  %n" +
                "  ███    ███       %n");
    }

    private static String getInput(String message)
    {
        System.out.printf("%s%nRoomShell > ", message);
        return sc.nextLine();
    }


    private static void createRoom()
    {
        boolean floorUniform;
        double floorWidth;
        double floorHeight;
        double wallHeight;
        boolean validInput = false;
        int wallQuantity = 4;

        while (!validInput)
        {
            if (getInput("Is your floor a rectangular? Y/N").strip().toLowerCase().equals("y"))
            {
                floorUniform = true;
                validInput = true;
            }

            else if (getInput("Is your floor a rectangular? Y/N").strip().toLowerCase().equals("n"))
            {
                floorUniform = false;
                validInput = true;
            }
            else
            {
                System.out.println("Invalid Choice.");
            }
        }
        validInput = false;




        double[] wallLengths = new double[wallQuantity];
    }

    private static Surface createSurface()
    {

        return null;
    }


    private static void deleteRoom()
    {

    }

    private static void paintRoom()
    {

    }

    private static void displayDimensions()
    {

    }

    private static void listRooms()
    {

    }
}
