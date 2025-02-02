package Meals;

public class Meal {

    private int mealId;
    private String mealName;
    private String ingredients;
    private double price;
    private int orderCnt;
    private String iconPath = "";

    public Meal(int mealId, String mealName, String ingredients, double price, int orderCnt, String iconPath) {
        this.mealId = mealId;
        this.mealName = mealName;
        this.ingredients = ingredients;
        this.price = price;
        this.orderCnt = orderCnt;
        this.iconPath = iconPath;
    }

    public Meal(int mealId, String mealName, String ingredients, double price, String iconPath) {
        this.mealId = mealId;
        this.mealName = mealName;
        this.ingredients = ingredients;
        this.price = price;
        this.orderCnt = 0;
        this.iconPath = iconPath;
    }

    public int getId() {
        return mealId;
    }

    public String getIconPath() {
        return iconPath;
    }

    public int getOrderCnt() {
        return orderCnt;
    }

    public String getName() {
        return mealName;
    }

    public String getIngredients() {
        return ingredients;
    }

    public double getPrice() {
        return price;
    }

    public void addCnt(int num) {
        System.out.println("num: " + num);
        orderCnt += num;
        MealsManagment.updateMealInternally(mealId, new Meal(mealId, mealName, ingredients, price, orderCnt, iconPath));
    }

    @Override
    public String toString() {
        return mealId  + "||" + mealName + "||" + ingredients + "||" + price + "||" + orderCnt + "||" + iconPath;
    }

    public static Meal fromString(String line) {
        String[] parts = line.split("\\|\\|", 6);
        return new Meal(
                Integer.parseInt(parts[0]),
                parts[1],
                parts[2],
                Double.parseDouble(parts[3]),
                Integer.parseInt(parts[4]),
                parts[5]
        );
    }
}
