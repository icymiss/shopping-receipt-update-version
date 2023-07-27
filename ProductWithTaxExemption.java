public enum ProductWithTaxExemption {
    POTATO_CHIPS_CA("POTATO CHIPS", "CA"),
    POTATO_CHIPS_NY("POTATO CHIPS", "NY"),
    SHIRT_NY("SHIRT", "NY");

    private String product;
    private String location;

    ProductWithTaxExemption(String product, String location){
        this.product=product;
        this.location=location;
    }

    public String getProduct(){
            return product;
    }

    public String getLocation(){
            return location;
        }
}
