package com.carsolutions;
/*
 * Name: <Buğra TUTUMLU>
 * Number: <8230800>
 * Class: <LEI_PP>
 */
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

/**
 * Handles importing and exporting service orders to and from JSON format.
 */
public class JSONHandler {
    private ServiceOrderManagement serviceOrderManagement;

    /**
     * Constructs a new JSONHandler with the specified service order management.
     *
     * @param serviceOrderManagement the service order management to use
     */
    public JSONHandler(ServiceOrderManagement serviceOrderManagement) {
        this.serviceOrderManagement = serviceOrderManagement;
    }

    /**
     * Exports all service orders to a JSON file.
     *
     * @param filePath the path to the JSON file
     * @throws IOException if an I/O error occurs
     */
    public void exportToJSON(String filePath) throws IOException {
        JSONArray serviceOrdersJSON = new JSONArray();
        for (ServiceOrder order : serviceOrderManagement.getAllServiceOrders()) {
            JSONObject orderJSON = new JSONObject();
            orderJSON.put("id", order.getId());
            orderJSON.put("creationDate", order.getCreationDate().getTime());
            orderJSON.put("estimatedCompletionDate", order.getEstimatedCompletionDate().getTime());
            orderJSON.put("actualCompletionDate", order.getActualCompletionDate() != null ? order.getActualCompletionDate().getTime() : null);
            orderJSON.put("vehicleRegistration", order.getVehicleRegistration());

            JSONObject clientJSON = new JSONObject();
            Client client = order.getClient();
            if (client instanceof Business) {
                clientJSON.put("type", "business");
                clientJSON.put("clientId", client.getClientId());
                clientJSON.put("name", client.getName());
                clientJSON.put("taxId", ((Business) client).getTaxId());
            } else if (client instanceof Individual) {
                clientJSON.put("type", "individual");
                clientJSON.put("clientId", client.getClientId());
                clientJSON.put("name", client.getName());
                clientJSON.put("personalId", ((Individual) client).getPersonalId());
            }
            orderJSON.put("client", clientJSON);

            JSONArray itemsJSON = new JSONArray();
            for (OrderItem item : order.getItems()) {
                if (item != null) {
                    JSONObject itemJSON = new JSONObject();
                    if (item instanceof Service) {
                        itemJSON.put("type", "Service");
                        itemJSON.put("description", item.getDescription());
                        itemJSON.put("executionTime", ((Service) item).getExecutionTime());
                    } else if (item instanceof Part) {
                        itemJSON.put("type", "Part");
                        itemJSON.put("description", item.getDescription());
                        itemJSON.put("quantity", ((Part) item).getQuantity());
                    }
                    itemsJSON.add(itemJSON);
                }
            }
            orderJSON.put("items", itemsJSON);

            serviceOrdersJSON.add(orderJSON);
        }

        try (FileWriter file = new FileWriter(filePath)) {
            file.write(serviceOrdersJSON.toJSONString());
            file.flush();
        }
    }

    /**
     * Imports service orders from a JSON file.
     *
     * @param filePath the path to the JSON file
     * @throws IOException    if an I/O error occurs
     * @throws ParseException if a parsing error occurs
     */
    public void importFromJSON(String filePath) throws IOException, ParseException {
        JSONParser jsonParser = new JSONParser();
        try (FileReader reader = new FileReader(filePath)) {
            Object obj = jsonParser.parse(reader);
            JSONArray serviceOrdersList = (JSONArray) obj;
            serviceOrdersList.forEach(order -> parseServiceOrderObject((JSONObject) order));
        }
    }

    /**
     * Parses a JSON object into a ServiceOrder object and adds it to the management system.
     *
     * @param orderJSON the JSON object representing a service order
     */
    private void parseServiceOrderObject(JSONObject orderJSON) {
        String id = (String) orderJSON.get("id");
        Date creationDate = new Date((Long) orderJSON.get("creationDate"));
        Date estimatedCompletionDate = new Date((Long) orderJSON.get("estimatedCompletionDate"));
        Date actualCompletionDate = orderJSON.get("actualCompletionDate") != null ? new Date((Long) orderJSON.get("actualCompletionDate")) : null;
        String vehicleRegistration = (String) orderJSON.get("vehicleRegistration");

        JSONObject clientJSON = (JSONObject) orderJSON.get("client");
        String clientType = (String) clientJSON.get("type");
        Client client = null;
        if (clientType.equals("business")) {
            String clientId = (String) clientJSON.get("clientId");
            String name = (String) clientJSON.get("name");
            String taxId = (String) clientJSON.get("taxId");
            client = new Business(clientId, name, taxId);
        } else if (clientType.equals("individual")) {
            String clientId = (String) clientJSON.get("clientId");
            String name = (String) clientJSON.get("name");
            String personalId = (String) clientJSON.get("personalId");
            client = new Individual(clientId, name, personalId);
        }

        ServiceOrder serviceOrder = new ServiceOrder(id, creationDate, estimatedCompletionDate, vehicleRegistration, client);
        serviceOrder.setActualCompletionDate(actualCompletionDate);

        JSONArray itemsJSON = (JSONArray) orderJSON.get("items");
        for (Object itemObj : itemsJSON) {
            JSONObject itemJSON = (JSONObject) itemObj;
            String type = (String) itemJSON.get("type");
            String description = (String) itemJSON.get("description");
            if (type.equals("Service")) {
                int executionTime = ((Long) itemJSON.get("executionTime")).intValue();
                serviceOrder.addItem(new Service(description, executionTime));
            } else if (type.equals("Part")) {
                int quantity = ((Long) itemJSON.get("quantity")).intValue();
                serviceOrder.addItem(new Part(description, quantity));
            }
        }

        serviceOrderManagement.createServiceOrder(serviceOrder);
    }
}
