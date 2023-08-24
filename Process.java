import java.util.ArrayList;
import java.util.Scanner;

public class Process {
    static Scanner keyboard = new Scanner(System.in);
    static ArrayList<Product>product = new ArrayList<Product>();
    static Double productWithTax = 0.0;
    static String site;

    public static void main(String[] args) {
        buyItems(keyboard);
    }

    public static void buyItems(Scanner keyboard) {
        checkSite(keyboard);

        Boolean allItemsInputDone = true;
        Double subtotal = 0.0;
        Double tax = 0.0;
        String productName;
        do {
            inputProductDetails();

            Boolean inputCorrect= true;
            String answer;
            do {
                System.out.println("Continue? Y/N");
                answer = keyboard.nextLine();
                if (!(answer.toUpperCase().equals("Y") || answer.toUpperCase().equals("N"))){
                    inputCorrect = false;
                }else inputCorrect=true;
            }while(inputCorrect==false);

            if (!"Y".equals(answer.toUpperCase())) {
                 allItemsInputDone = false;
            }
        } while (allItemsInputDone == true);

        System.out.println("Shopping receipt is printing\n");
        System.out.println("Total product buy as follows:");

        for(int i = 0; i<product.size(); i++){
            System.out.println(product.get(i));
            Double amountOfProduct=product.get(i).getPrice() * product.get(i).getQuantity();
            System.out.println("Amount of the product: "+amountOfProduct);
            subtotal += amountOfProduct;

            productWithTax(product.get(i).getName(), site, amountOfProduct);     
            tax += productWithTax;
        }
        System.out.printf("Subtotal: %.2f\n", subtotal);
        System.out.printf("Tax %f\n", tax);
        Double taxAfterRoundUp = Math.round(tax * 5)/ 5.0;
        System.out.printf("Tax after round up: %.2f\n", taxAfterRoundUp);
        Double total = subtotal + taxAfterRoundUp;
        System.out.printf("Total: %.2f", total);
    }

    public static String checkSite(Scanner keyboard){
        Boolean locationWithService = true;
        do{
            System.out.println("Please input site of the purchase:");               
            site = keyboard.nextLine();
            for(Location location: Location.values()) {
                if (!(site.toUpperCase().equals(location.getShortCode()))) {
                    locationWithService = false;
                } else {
                    locationWithService = true;
                    break;
                }
            }if (locationWithService == false){
                System.out.println("No service in this state. Please choose from CA and NY");
            }
        } while (locationWithService == false);
        return site;
    }

    public static void inputProductDetails(){
        String productName;
        Boolean isProductInStock = true;
        do{
            System.out.println("Please input product name:");
            productName = keyboard.nextLine();
                for (ProductInStock stock : ProductInStock.values()) {
                    if (productName.toUpperCase().equals(stock.getFullName())) {       
                        System.out.println("Product is in stock :)");
                        isProductInStock = true;
                        break;
                    } else isProductInStock = false;
                   }
                if (isProductInStock == false)
                    System.out.println("Sorry, this product is out of stock");
        }while(isProductInStock==false);

        System.out.println("Please input price:");
        Double price = keyboard.nextDouble();
        System.out.println("Please input quantity:");
        Integer quantity = keyboard.nextInt();
        keyboard.nextLine();
        product.add(new Product(productName,price,quantity));
    }

    public static Double productWithTax(String productName, String site, Double productTotal){      
        for(ProductWithTaxExemption product : ProductWithTaxExemption.values()){
            if((productName.toUpperCase().equals(product.getProduct())) && (site.toUpperCase().equals(product.getLocation()))){
                productWithTax = 0.0;
                break;
            }else if(site.toUpperCase().equals("CA")){
                productWithTax =productTotal * 9.75 /100;
            }else  productWithTax = productTotal*8.875/100;
        }
        return productWithTax;
    }
}
