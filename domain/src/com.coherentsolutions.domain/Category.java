public class Category {
    private String name;
    private List<Product> productList;

    public Category (Category name){
        this.name = name;
        this.productList = new ArrayList<>();
    }
}
