import java.util.ArrayList;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Queue;
import java.util.Scanner;

// CustomPizza 类用于表示定制披萨
class CustomPizza {
    private String toppings;
    private double price;

    public CustomPizza(String toppings, double price) {
        this.toppings = toppings;
        this.price = price;
    }

    public String getToppings() {
        return toppings;
    }

    public void setToppings(String toppings) {
        this.toppings = toppings;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "CustomPizza{" +
                "toppings='" + toppings + '\'' +
                ", price=" + price +
                '}';
    }
}

// HandleOrders 类用于处理披萨订单
class HandleOrders {
    private ArrayList<CustomPizza> customPizzas = new ArrayList<>();

    public void handleOrder() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Do you want to order a custom pizza? (yes/no)");
        String choice = scanner.nextLine();
        if ("yes".equalsIgnoreCase(choice)) {
            StringBuilder customPizzaToppings = new StringBuilder();
            System.out.println("Enter toppings (separated by comma):");
            String[] toppingsArray = scanner.nextLine().split(",");
            for (String topping : toppingsArray) {
                customPizzaToppings.append(topping.trim()).append(", ");
            }
            if (customPizzaToppings.length() > 0) {
                customPizzaToppings.setLength(customPizzaToppings.length() - 2);
            }
            System.out.println("Enter the price of the custom pizza:");
            double customPizzaPrice = scanner.nextDouble();
            CustomPizza customPizza = new CustomPizza(customPizzaToppings.toString(), customPizzaPrice);
            customPizzas.add(customPizza);
        }
    }

    public void displayCustomPizzas() {
        for (CustomPizza pizza : customPizzas) {
            System.out.println(pizza);
        }
    }
}

// OrderLogs 类用于管理订单日志，使用栈结构
class OrderLogs {
    private Deque<String> orderLogs = new ArrayDeque<>();

    public OrderLogs() {
    }

    public void addOrderLog(String log) {
        orderLogs.push(log);
    }

    private void mostRecentLogEntry() {
        if (!orderLogs.isEmpty()) {
            System.out.println("Most recent log entry: " + orderLogs.peek());
        } else {
            System.out.println("The log is empty.");
        }
    }

    private String getOrderLog() {
        if (!orderLogs.isEmpty()) {
            return orderLogs.pop();
        } else {
            System.out.println("The log is empty.");
            return null;
        }
    }

    private void removeAllLogEntries() {
        orderLogs.clear();
        System.out.println("All log entries removed.");
    }

    private boolean orderLogsEmpty() {
        return orderLogs.isEmpty();
    }

    public void handleLogs() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Choose what you want to do with order logs:");
            System.out.println("1. Display all the logs");
            System.out.println("2. Display the most recent logs");
            System.out.println("3. Remove a log entry");
            System.out.println("4. Remove all log entries");
            System.out.println("5. Check if the log is completely empty");
            System.out.println("Enter your choice (1 - 5)");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    System.out.println("All log entries:");
                    for (String log : orderLogs) {
                        System.out.println(log);
                    }
                    break;
                case 2:
                    mostRecentLogEntry();
                    break;
                case 3:
                    String removedLog = getOrderLog();
                    if (removedLog != null) {
                        System.out.println("Removed log entry: " + removedLog);
                    }
                    break;
                case 4:
                    removeAllLogEntries();
                    break;
                case 5:
                    if (orderLogsEmpty()) {
                        System.out.println("The log is completely empty");
                    } else {
                        System.out.println("The log is not completely empty");
                    }
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}

// PizzaOrderQueue 类用于管理披萨订单队列
class PizzaOrderQueue {
    private Queue<String> pizzaOrders = new ArrayDeque<>();

    public PizzaOrderQueue() {
    }

    public void addOrder(String order) {
        pizzaOrders.add(order);
    }

    private void peekOrder() {
        if (!pizzaOrders.isEmpty()) {
            System.out.println("The next order in line: " + pizzaOrders.peek());
        } else {
            System.out.println("There are no orders in the queue yet.");
        }
    }

    private String removeOrder() {
        if (!pizzaOrders.isEmpty()) {
            return pizzaOrders.poll();
        } else {
            System.out.println("There are no orders to remove.");
            return null;
        }
    }

    private boolean isQueueEmpty() {
        return pizzaOrders.isEmpty();
    }

    public void handleQueueOperations() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Choose what you want to do with pizza orders queue:");
            System.out.println("1. Add an order");
            System.out.println("2. View the next order");
            System.out.println("3. Remove an order");
            System.out.println("4. Check if the queue is empty");
            System.out.println("Enter your choice (1 - 4)");
            int choice = scanner.nextInt();
            scanner.nextLine(); // 清除换行符
            switch (choice) {
                case 1:
                    System.out.println("Enter the order details:");
                    String newOrder = scanner.nextLine();
                    addOrder(newOrder);
                    break;
                case 2:
                    peekOrder();
                    break;
                case 3:
                    String removedOrder = removeOrder();
                    if (removedOrder != null) {
                        System.out.println("Removed order: " + removedOrder);
                    }
                    break;
                case 4:
                    if (isQueueEmpty()) {
                        System.out.println("The order queue is empty.");
                    } else {
                        System.out.println("The order queue is not empty.");
                    }
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}

// Main 类用于测试上述功能
public class Main {
    public static void main(String[] args) {
        HandleOrders handleOrders = new HandleOrders();
        handleOrders.handleOrder();
        handleOrders.displayCustomPizzas();

        OrderLogs orderLogs = new OrderLogs();
        orderLogs.addOrderLog("Order 1 placed");
        orderLogs.addOrderLog("Order 2 placed");
        orderLogs.handleLogs();

        PizzaOrderQueue pizzaOrderQueue = new PizzaOrderQueue();
        pizzaOrderQueue.addOrder("Pepperoni Pizza");
        pizzaOrderQueue.addOrder("Mushroom Pizza");
        pizzaOrderQueue.handleQueueOperations();
    }
}