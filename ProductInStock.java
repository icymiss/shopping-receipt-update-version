public enum ProductInStock {
    BOOK("BOOK"),
    POTATO_CHIPS("POTATO CHIPS"),
    PENCIL("PENCIL"),
    SHIRT("SHIRT");

    private String fullName;

    ProductInStock(String fullName){
        this.fullName = fullName;
    }

    public String getFullName(){
        return fullName;
    }
}
