package com.company;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * General commentary:
 * From my testing, the programming in generally function and is able to satisfy the given requirements.
 * Allowing the use of composite walls significantly complicated this project, and resulted in the final product
 * being less finished, and messier than desired.
 * By messier, I am referring to the frequent copy and pasting of similar code. Many components will run similar
 * code but require slight adjustments to the specific scenarios, these could have been implemented as functions
 * that alter their behaviour slightly when given certain arguments, however, this would have most definitely taken
 * me over the 4-hour time constraint, thus I copy and pasted a lot of code.
 */

public class Main {

    private static final Scanner sc = new Scanner(System.in);

    private static final ArrayList<Room> rooms = new ArrayList<>();

    public static void main(String[] args) {
        System.out.printf("Ascii art generated using: https://patorjk.com/software/taag/ %n%n");
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
        boolean floorUniform = true;
        String userSelection;
        boolean validInput = false;
        int wallQuantity = 4;
        Surface floor = null;
        Surface[] currSubSurfaces;
        Surface[] walls;

        // A rectangular wall, is defined as "uniform" within the code.
        // A wall of any other shape, made up of rectangles, is defined as composite.
        while (!validInput)
        {
            userSelection = getInput("Is your floor rectangular? Y/N");
            // Floor uniform by default, no need to re-assign
            if (userSelection.strip().equalsIgnoreCase("y"))
            {
                validInput = true;
            }

            else if (userSelection.strip().equalsIgnoreCase("n"))
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

        while (!validInput)
        {
            System.out.println("==Generating Floor==");
            if (floorUniform)
            {
                try
                {
                    floor = getSurface();
                    if (floor == null)
                    {
                        continue;
                    }
                    validInput = true;
                }
                catch (NumberFormatException e)
                {
                    System.out.println("Invalid Values");
                }
            }
            else
            {
                try
                {
                    currSubSurfaces = getSubSurfaces();
                    floor = new Surface(currSubSurfaces);
                    validInput = true;
                }
                catch (NumberFormatException e)
                {
                    System.out.println("Invalid Values");
                }
            }
        }
        validInput = false;

        while (!validInput)
        {
            try
            {
                wallQuantity = Integer.parseInt(getInput("Please enter number of walls (4 default)"));
                if (wallQuantity < 1)
                {
                    System.out.println("Can't have less than 1 wall!");
                    continue;
                }
                validInput = true;
            }
            catch (NumberFormatException e)
            {
                System.out.println("Invalid value");
            }
        }
        validInput = false;
        int loopVariable = 0;
        walls = new Surface[wallQuantity];
        while (loopVariable < wallQuantity)
        {
            boolean currWallUniform = true;
            while (!validInput)
            {
                userSelection = getInput("Is your wall rectangular? Y/N");
                // Uniform by default, no need to re-assign.
                if (userSelection.strip().equalsIgnoreCase("y"))
                {
                    validInput = true;
                }

                else if (userSelection.strip().equalsIgnoreCase("n"))
                {
                    currWallUniform = false;
                    validInput = true;
                }
                else
                {
                    System.out.println("Invalid Choice.");
                }
            }
            validInput = false;

            while (!validInput)
            {
                // All walls required to be of equal height, thus uniform walls allow the entry of height once for all
                // walls. However, enforcing equal height for subsurface walls is complicated, and would result in me
                // going over the time limit
                if (currWallUniform)
                {
                    double wallWidth;

                    try
                    {
                        wallWidth = Double.parseDouble(getInput("Please enter a wall height"));
                        if (wallWidth < 0.1)
                        {
                            System.out.println("Length can't be less than 0.1!");
                            continue;
                        }
                    }
                    catch (NumberFormatException e)
                    {
                        System.out.println("Invalid input");
                        continue;
                    }

                    try
                    {
                        double wallLength = getWallSurface();

                        if (wallLength == -1)
                        {
                            continue;
                        }
                        walls[loopVariable] = new Surface(wallLength, wallWidth);
                        validInput = true;
                    }
                    catch (NumberFormatException e)
                    {
                        System.out.println("Invalid Values");
                    }
                }
                else
                {
                    try
                    {
                        currSubSurfaces = getSubSurfaces();
                        walls[loopVariable] = new Surface(currSubSurfaces);
                        validInput = true;
                    }
                    catch (NumberFormatException e)
                    {
                        System.out.println("Invalid Values");
                    }
                }
            }
            validInput = false;
            ++loopVariable;
        }
        Room currRoom = new Room(floor, walls);
        rooms.add(currRoom);
        System.out.println("==Room Created Successfully==");
        System.out.printf("Dimensions:%n" +
                "Width: %.2f%n" +
                "Length: %.2f%n" +
                "Height: %.2f%n" +
                "Surface Area: %.2f%n" +
                "Volume: %.2f%n", currRoom.getWidth(), currRoom.getLength(), currRoom.getHeight(),
                currRoom.getTotalSurfaceArea(), currRoom.getVolume());
        printTitle();

    }


    private static Surface getSurface()
    {
        double length;
        double width;
        length = Double.parseDouble(getInput("Please enter a length"));
        if (length < 0)
        {
            System.out.println("Length can't be less than 0!");
            return null;
        }
        width = Double.parseDouble(getInput("Please enter a width"));
        if (width < 0)
        {
            System.out.println("Width can't be less than 0!");
            return null;
        }
        return new Surface(length, width);
    }

    // separate method required to ensure even wall height;
    private static double getWallSurface()
    {
        double length;
        length = Double.parseDouble(getInput("Please enter a length"));
        if (length < 0.1)
        {
            System.out.println("Length can't be less than 0.1!");
            return -1;
        }
        return length;
    }


    // Used to build an array of sub-surfaces, which is used to generate a full surface.
    private static Surface[] getSubSurfaces()
    {
        int subSurfaceQuantity = Integer.parseInt(getInput("Enter the quantity of sub-surfaces"));
        Surface[] surfaces = new Surface[subSurfaceQuantity];
        // making sure every object is null for later code.
        for (int i = 0; i < subSurfaceQuantity; ++i)
        {
            surfaces[i] = null;
        }
        int loopVariable = 0;
        // Only iterate if the sub-surface was created properly
        while (loopVariable < subSurfaceQuantity)
        {
            surfaces[loopVariable] = getSurface();
            if (surfaces[loopVariable] != null)
            {
                ++loopVariable;
            }
        }
        return surfaces;
    }


    private static void deleteRoom()
    {
        try
        {
            String message = "Please enter the room number 0 - " + (rooms.size() - 1);
            int roomIndex = Integer.parseInt(getInput(message));
            rooms.remove(roomIndex);
        }
        catch (NumberFormatException e)
        {
            System.out.println("Invalid integer!");
        }
        catch (ArrayIndexOutOfBoundsException e)
        {
            System.out.println("Invalid index!");
        }

        System.out.println("Room removed");
    }

    // Display total surface area required to paint a room
    private static void paintRoom()
    {
        try
        {
            String message = "Please enter the room number 0 - " + (rooms.size() - 1);
            int roomIndex = Integer.parseInt(getInput(message));
            double paintNeeded = rooms.get(roomIndex).getWallsArea();
            System.out.printf("Enough paint needed to cover an area of %.2f%n", paintNeeded);
        }
        catch (NumberFormatException e)
        {
            System.out.println("Invalid integer!");
        }
        catch (ArrayIndexOutOfBoundsException e)
        {
            System.out.println("Invalid index!");
        }

    }

    private static void displayDimensions()
    {
        boolean validInput = false;
        while (!validInput)
        {
            try
            {
                String message = "Please enter the room number 0 - " + (rooms.size() - 1);
                int roomIndex = Integer.parseInt(getInput(message));
                Room currRoom = rooms.get(roomIndex);
                System.out.printf("Dimensions:%n" +
                                "Width: %.2f%n" +
                                "Length: %.2f%n" +
                                "Height: %.2f%n" +
                                "Surface Area: %.2f%n" +
                                "Volume: %.2f%n", currRoom.getWidth(), currRoom.getLength(), currRoom.getHeight(),
                        currRoom.getTotalSurfaceArea(), currRoom.getVolume());
                validInput = true;
            }
            catch (NumberFormatException e)
            {
                System.out.println("Invalid integer!");
            }
            catch (ArrayIndexOutOfBoundsException e)
            {
                System.out.println("Invalid index!");
            }
        }



    }

    // Display all room numbers
    private static void listRooms()
    {
        for (int i = 0; i < rooms.size(); ++i)
        {
            System.out.printf("Room: %d%n", i);
        }
    }
}
