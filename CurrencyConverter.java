/*
C:\CurrencyConverterApp>javac -cp .;json-20231013.jar CurrencyConverter.java

C:\CurrencyConverterApp>java -cp .;json-20231013.jar CurrencyConverter
Welcome to the Currency Converter! ?
Enter the base currency code (e.g., USD): INR
Enter the target currency code (e.g., EUR): USD
Enter the amount to convert: 1000
Result: 1000.00 INR = 11.85 USD

*/
import java.util.Scanner;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import org.json.JSONObject;

/**
 * Simple console-based Currency Converter
 * using real-time exchange rates from open.er-api.com
 */
public class CurrencyConverter {

    // Base API URL for fetching exchange rates
    private static final String API_URL = "https://open.er-api.com/v6/latest/";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Currency Converter ===");

        // Step 1: Get base currency input
        System.out.print("Enter base currency code (e.g., USD): ");
        String baseCurrency = scanner.nextLine().trim().toUpperCase();

        // Step 2: Get target currency input
        System.out.print("Enter target currency code (e.g., INR): ");
        String targetCurrency = scanner.nextLine().trim().toUpperCase();

        // Step 3: Get amount input
        System.out.print("Enter amount to convert: ");
        double amount = 0;
        try {
            amount = Double.parseDouble(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid amount. Please enter a numeric value.");
            scanner.close();
            return;
        }

        try {
            // Step 4: Fetch exchange rate and calculate conversion
            double rate = getExchangeRate(baseCurrency, targetCurrency);
            double result = amount * rate;

            // Step 5: Show result
            System.out.printf("Converted: %.2f %s = %.2f %s%n", amount, baseCurrency, result, targetCurrency);

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        scanner.close();
    }

    // Method to fetch the exchange rate between two currencies
    private static double getExchangeRate(String base, String target) throws Exception {
        HttpClient client = HttpClient.newHttpClient();
        URI endpoint = URI.create(API_URL + base);

        HttpRequest request = HttpRequest.newBuilder(endpoint).GET().build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        // Parse JSON response
        JSONObject json = new JSONObject(response.body());

        if (!json.getString("result").equalsIgnoreCase("success")) {
            throw new Exception("Failed to retrieve exchange rates.");
        }

        JSONObject rates = json.getJSONObject("rates");
        if (!rates.has(target)) {
            throw new Exception("Invalid target currency.");
        }

        return rates.getDouble(target);
    }
}

