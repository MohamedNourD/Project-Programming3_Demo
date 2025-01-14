import Meals.Meal;
import Meals.MealsManagment;
import Orders.*;

import java.io.IOException;
import java.util.List;

public class Main {

    public static void main (String[] args) throws IOException{
        System.out.println(MealsManagment.createMeal("Burger", "Potatp, Tomato", 50.3).getMsg());
        System.out.println(MealsManagment.createMeal("Piz", "fdk, Tomato", 5).getMsg());
        System.out.println(MealsManagment.createMeal("fsl", "ato", 53).getMsg());

        MealsManagment.deleteMeal("hhh");
        List<Meal> meals = MealsManagment.getMeals();
        for (Meal meal: meals) {
            System.out.println(meal.getId() + " " + meal.getName());
        }
//        System.out.println(OrderManagment.countOrdersForToday());
//        List<User> users = UserManagement.getUsers();
//        List<Customer> customers = new ArrayList<>();
//        for (User tempUser : users) {
//            if (tempUser.getUserType() == 1) {
//                Customer c = (Customer) tempUser;
//                customers.add(c);
//            }
//        }
//        List<Order> orders = OrderManagment.getOrders();
//        List<Meal> meals = MealsManagment.getMeals();


//        System.out.println(MealsManagment.createMeal("Burger", "Tomato, ", 900).getMsg());
//        User u1 = new User();
//        System.out.println(u1.createAccount("Super", "Admin", "SuperAdmin@Mgroup.org", "0000", "0000", 0));
//        UserManagement.addUser(u1);

//        User u2 = new User();
//        System.out.println(u2.createAccount("Ahmad", "Khamis", "a.khamis@gmail.com", "123", "123", 1));
//        UserManagement.addUser(u2);

//        System.out.println(u1.toString());
//        List<OrderItem> orderItems = new ArrayList<>();
//        for (Meal meal : meals) {
//            OrderItem o1 = new OrderItem(meal, 2);
//            orderItems.add(o1);
////            System.out.println(meal.getName());
//        }
//        OrderManagment.addOrder((Customer) users.get(1), orderItems);
//
//        System.out.println(UserManagement.logIn("m.zakaria@sanadyouth.org", "123"));

//        Notififcation n = new Notififcation();
//        n.getNotification("Hello", "Miss you MR.Mulham");
    }


}
