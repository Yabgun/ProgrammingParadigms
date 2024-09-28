package com.carsolutions;
/*
 * Name: <Buğra TUTUMLU>
 * Number: <8230800>
 * Class: <LEI_PP>
 */
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/**
 * Main class for the Car Solutions application.
 * Handles user interaction and main application logic.
 */
public class Main {
    private static ServiceOrderManagement serviceOrderManagement = new ServiceOrderManagement();
    private static JSONHandler jsonHandler = new JSONHandler(serviceOrderManagement);
    private static IStatistics statistics = new Statistics(serviceOrderManagement); // IStatistics interface is using
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    /**
     * Main method to run the application.
     *
     * @param args command-line arguments
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("1. Create Service Order");
            System.out.println("2. Update Service Order");
            System.out.println("3. Delete Service Order");
            System.out.println("4. View Service Orders");
            System.out.println("5. Export to JSON");
            System.out.println("6. Import from JSON");
            System.out.println("7. View Statistics");
            System.out.println("8. Remove Item from Service Order");
            System.out.println("0. Exit");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    createServiceOrder(scanner);
                    break;
                case 2:
                    updateServiceOrder(scanner);
                    break;
                case 3:
                    deleteServiceOrder(scanner);
                    break;
                case 4:
                    viewServiceOrders();
                    break;
                case 5:
                    exportToJSON(scanner);
                    break;
                case 6:
                    importFromJSON(scanner);
                    break;
                case 7:
                    viewStatistics();
                    break;
                case 8:
                    removeItemFromServiceOrder(scanner);
                    break;
                case 0:
                    System.exit(0);
                default:
                    System.out.println("Invalid choice, please try again.");
            }
        }
    }

    /**
     * Creates a new service order based on user input.
     *
     * @param scanner Scanner object for user input
     */
    private static void createServiceOrder(Scanner scanner) {
        System.out.println("Service Order ID:");
        String id = scanner.nextLine();
        Date creationDate = new Date();
        System.out.println("Estimated Completion Date (yyyy-MM-dd):");
        String estimatedDateStr = scanner.nextLine();
        Date estimatedCompletionDate;
        try {
            estimatedCompletionDate = dateFormat.parse(estimatedDateStr);
        } catch (java.text.ParseException e) {
            System.out.println("Invalid date format.");
            return;
        }
        System.out.println("Vehicle Registration Number:");
        String vehicleRegistration = scanner.nextLine();

        System.out.println("Enter client type (business/individual):");
        String clientType = scanner.nextLine();
        Client client = null;

        if (clientType.equalsIgnoreCase("business")) {
            System.out.println("Enter Business Client ID:");
            String clientId = scanner.nextLine();
            System.out.println("Enter Business Name:");
            String name = scanner.nextLine();
            System.out.println("Enter Tax ID:");
            String taxId = scanner.nextLine();
            client = new Business(clientId, name, taxId);
        } else if (clientType.equalsIgnoreCase("individual")) {
            System.out.println("Enter Individual Client ID:");
            String clientId = scanner.nextLine();
            System.out.println("Enter Individual Name:");
            String name = scanner.nextLine();
            System.out.println("Enter Personal ID:");
            String personalId = scanner.nextLine();
            client = new Individual(clientId, name, personalId);
        } else {
            System.out.println("Invalid client type.");
            return;
        }

        ServiceOrder order = new ServiceOrder(id, creationDate, estimatedCompletionDate, vehicleRegistration, client);

        while (true) {
            System.out.println("Add an item to the service order? (yes/no):");
            String addItem = scanner.nextLine();
            if (addItem.equalsIgnoreCase("no")) {
                break;
            }

            System.out.println("Enter item type (service/part):");
            String itemType = scanner.nextLine();

            System.out.println("Enter item description:");
            String description = scanner.nextLine();

            if (itemType.equalsIgnoreCase("service")) {
                System.out.println("Enter execution time in minutes:");
                int executionTime = scanner.nextInt();
                scanner.nextLine();
                order.addItem(new Service(description, executionTime));
            } else if (itemType.equalsIgnoreCase("part")) {
                System.out.println("Enter quantity:");
                int quantity = scanner.nextInt();
                scanner.nextLine();
                order.addItem(new Part(description, quantity));
            } else {
                System.out.println("Invalid item type.");
            }
        }

        serviceOrderManagement.createServiceOrder(order);
        System.out.println("Service order created.");
    }

    /**
     * Updates an existing service order based on user input.
     *
     * @param scanner Scanner object for user input
     */
    private static void updateServiceOrder(Scanner scanner) {
        System.out.println("Service Order ID to update:");
        String id = scanner.nextLine();
        ServiceOrder order = serviceOrderManagement.getServiceOrder(id);
        if (order != null) {
            System.out.println("New Estimated Completion Date (yyyy-MM-dd):");
            String estimatedDateStr = scanner.nextLine();
            Date estimatedCompletionDate;
            try {
                estimatedCompletionDate = dateFormat.parse(estimatedDateStr);
            } catch (java.text.ParseException e) {
                System.out.println("Invalid date format.");
                return;
            }
            System.out.println("New Vehicle Registration Number:");
            String vehicleRegistration = scanner.nextLine();
            order.setEstimatedCompletionDate(estimatedCompletionDate);
            order.setVehicleRegistration(vehicleRegistration);
            serviceOrderManagement.updateServiceOrder(id, order);
            System.out.println("Service order updated.");
        } else {
            System.out.println("Service order not found.");
        }
    }

    /**
     * Deletes a service order based on user input.
     *
     * @param scanner Scanner object for user input
     */
    private static void deleteServiceOrder(Scanner scanner) {
        System.out.println("Service Order ID to delete:");
        String id = scanner.nextLine();
        serviceOrderManagement.deleteServiceOrder(id);
        System.out.println("Service order deleted.");
    }

    /**
     * Displays all service orders.
     */
    private static void viewServiceOrders() {
        for (ServiceOrder order : serviceOrderManagement.getAllServiceOrders()) {
            System.out.println("ID: " + order.getId());
            System.out.println("Creation Date: " + order.getCreationDate());
            System.out.println("Estimated Completion Date: " + order.getEstimatedCompletionDate());
            System.out.println("Vehicle Registration: " + order.getVehicleRegistration());
            System.out.println("Client Name: " + order.getClient().getName());
            System.out.println("Items:");
            for (OrderItem item : order.getItems()) {
                if (item != null) { // Check for null to avoid NullPointerException
                    System.out.println("  " + item.getDescription());
                }
            }
            System.out.println();
        }
    }

    /**
     * Exports service orders to a JSON file based on user input.
     *
     * @param scanner Scanner object for user input
     */
    private static void exportToJSON(Scanner scanner) {
        System.out.println("JSON file path:");
        String filePath = scanner.nextLine();
        try {
            jsonHandler.exportToJSON(filePath);
            System.out.println("Data exported to JSON.");
        } catch (IOException e) {
            System.out.println("Error exporting to JSON: " + e.getMessage());
            e.printStackTrace(); // Print stack trace for more detailed debugging
        }
    }

    /**
     * Imports service orders from a JSON file based on user input.
     *
     * @param scanner Scanner object for user input
     */
    private static void importFromJSON(Scanner scanner) {
        System.out.println("JSON file path:");
        String filePath = scanner.nextLine();
        try {
            jsonHandler.importFromJSON(filePath);
            System.out.println("Data imported from JSON.");
        } catch (IOException | ParseException e) {
            System.out.println("Error importing from JSON: " + e.getMessage());
            e.printStackTrace(); // Print stack trace for more detailed debugging
        }
    }

    /**
     * Displays statistics about the service orders.
     */
    private static void viewStatistics() {
        System.out.println("Total Service Orders: " + statistics.getTotalServiceOrders());
        System.out.println("Total Services: " + statistics.getTotalServices());
        System.out.println("Total Parts: " + statistics.getTotalParts());
    }

    /**
     * Removes an item from a service order based on user input.
     *
     * @param scanner Scanner object for user input
     */
    private static void removeItemFromServiceOrder(Scanner scanner) {
        System.out.println("Service Order ID:");
        String orderId = scanner.nextLine();
        ServiceOrder order = serviceOrderManagement.getServiceOrder(orderId);
        if (order != null) {
            System.out.println("Enter description of the item to remove:");
            String description = scanner.nextLine();
            OrderItem itemToRemove = null;
            for (OrderItem item : order.getItems()) {
                if (item != null && item.getDescription().equals(description)) {
                    itemToRemove = item;
                    break;
                }
            }
            if (itemToRemove != null) {
                order.removeItem(itemToRemove);
                System.out.println("Item removed from service order.");
            } else {
                System.out.println("Item not found in service order.");
            }
        } else {
            System.out.println("Service order not found.");
        }
    }
}
