package Orders;

import Users.Customer;

import java.io.*;
import java.util.*;

public class OrderManagment {
    public static void addOrder(Customer customer, List<OrderItem> items) throws IOException {
        Order order = new Order(0 ,customer.getName(), items);
        customer.newOrder(order);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("orders.txt", true))) {
            writer.write(order.toString());
            writer.newLine();
        }
    }
    public static List<Order> getOrders() throws IOException {
        List<Order> orders = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("orders.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                orders.add(Order.fromString(line));
            }
        }
        return orders;
    }
}
