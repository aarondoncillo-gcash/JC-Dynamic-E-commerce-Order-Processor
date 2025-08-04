package org.example;

import java.util.Scanner;
public class InteractiveOrderProcessor {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean hasFreeShipping = false;
        // Get Order Data from User
        System.out.println("Welcome to the Interactive Order Processor!\n");
        System.out.println("--- Enter Order Details ---");
        System.out.print("Enter unit price: ");
        double unitPrice = sc.nextDouble();
        System.out.print("Enter quantity: ");
        int quantity = sc.nextInt();
        sc.nextLine();
        System.out.print("Is customer a member (true/false)?: ");
        String isMember = sc.nextLine();
        System.out.print("Enter customer tier (Regular, Silver, Gold): ");
        String customerTier = sc.nextLine();
        System.out.print("Enter shipping zone (ZoneA, ZoneB, ZoneC, Unknown): ");
        String shippingZone = sc.nextLine();
        System.out.print("Enter discount code (SAVE10, FREESHIP, or \"\" for none): ");
        String discountCode = sc.nextLine();

        System.out.println("\n--- Order Details ---");
        System.out.printf("Unit Price: $%.2f\n", unitPrice);
        System.out.println("Quantity: " + quantity);
        System.out.println("Is Member: " + isMember);
        System.out.println("Customer Tier: " + customerTier);
        System.out.println("Shipping Zone: " + shippingZone);
        System.out.println("Discount Code: " + discountCode);

        System.out.println("\n--- Calculation Steps ---");
        // Subtotal Calculation
        double subTotal = unitPrice * quantity;
        System.out.printf("Initial Subtotal: $%.2f\n", subTotal);

        // Tier-Based Discount
        if (customerTier.equals("Gold")) {
            // apply 15% discount on the current total
            subTotal -= subTotal * .15;
            System.out.printf("After Tier Discount (Gold - 15%%): $%.2f\n", subTotal);
        } else if (customerTier.equals("Silver")) {
            // apply 10% discount on the current total
            subTotal -= subTotal * .10;
            System.out.printf("After Tier Discount (Silver - 10%%): $%.2f\n", subTotal);
        } else {
            // No disounts applied
            System.out.printf("After Tier Discount (Regular - No discount deducted): $%.2f\n", subTotal);
        }

        // Quantity Discount
        if (quantity >= 5) {
            // apply an additional 5% discount on the current total
            subTotal -= subTotal * .05;
            System.out.printf("After Quantity Discount (5%% for >=5 items): $%.2f\n", subTotal);
        }

        // Promotional Code Application
        if (discountCode.equals("SAVE10") && subTotal > 75.00) {
            // apply a flat $10.00 reduction from the total
            subTotal -= 10.00;
            System.out.printf("After Promotional Code (SAVE10 for >$75): $%.2f\n", subTotal);
        } else if (discountCode.equalsIgnoreCase("FREESHIP")) {
            //the shipping cost for this order will be $0.00, overriding any zone-based shipping cost.
            hasFreeShipping = true;
            System.out.printf("After Promotional Code (FREESHIP - You got free shipping!): $%.2f\n", subTotal);
        }

        // Small Order Surcharge
        boolean hasSurcharge = subTotal < 25.00;
        // apply a $3.00 small order surcharge if order total (before shipping) is less than $25.00
        subTotal += hasSurcharge ? 3.00 : 0;
        System.out.printf("After Small Order Surcharge (if applicable): $%.2f %s\n", subTotal, hasSurcharge ? "(With surcharge - additional $3.00)" : "(No surcharge)");

        // Shipping Cost Calculation
        double shippingCost = 0.00;
        if (!hasFreeShipping) {
            // if no free shipping fee coupon applied
            switch (shippingZone) {
                case "ZoneA":
                    shippingCost = 5.00;
                    System.out.printf("\nShipping Cost: $%.2f (ZoneA)\n", shippingCost);
                    break;

                case "ZoneB":
                    shippingCost = 12.50;
                    System.out.printf("\nShipping Cost: $%.2f (ZoneB)\n", shippingCost);
                    break;
                case "ZoneC":
                    shippingCost = 20.00;
                    System.out.printf("\nShipping Cost: $%.2f (ZoneC)\n", shippingCost);
                    break;
                default:
                    shippingCost = 25.00;
                    System.out.printf("\nShipping Cost: $%.2f (Unknown)\n", shippingCost);
            }
        } else {
            // free shipping fee coupon is applied
            System.out.printf("\nShipping Cost: $%.2f (Free Shipping Applied)\n", shippingCost);
        }

        // Final Total
        double finalOrderTotal = subTotal + shippingCost;
        System.out.printf("\nFinal Order Total: $%.2f\n", finalOrderTotal);

        //String equality sub program
        part2InteractiveStringEquality(sc);

        // Close resource
        sc.close();
    }

    public static void part2InteractiveStringEquality (Scanner sc) {
        System.out.println("\n--- String Equality Demo ---");
        // Get Strings from User
        System.out.printf("Enter first string for comparison: ");
        String string1 = sc.nextLine();
        System.out.printf("Enter second string for comparison: ");
        String string2 = sc.nextLine();

        System.out.printf("\nString 1: \"%s\"\n", string1);
        System.out.printf("String 2: \"%s\"\n", string2);

        // String Equality Demonstrations with Explanations
        boolean equalityUsingEqualSymbol = string1 == string2;
        boolean equalityUsingEquals = string1.equals(string2);
        boolean equalityUsingEqualsIgnoreCase = string1.equalsIgnoreCase(string2);

        // Compare the two user-entered strings using ==
        System.out.printf("\nString 1 == String 2: %b (%s)", equalityUsingEqualSymbol, equalityUsingEqualSymbol ?
                        "Have the same references since the two string inputs have the same value, they reference the same object in the string pool" :
                        "Compares references, which are different for user input strings");
        System.out.printf("\n*** Using == checks only the reference, not the actual value of the strings ***");
        System.out.printf("\n*** Using == to compare strings that are input from the console will return false, \nbecause nextLine() creates a new String object each time. This means the 2 strings have different \nreferences in memory, and they are not interned in the String pool");

        // Compare the two user-entered strings using .equals()
        System.out.printf("\nString 1 .equals() String 2: %b (%s)", equalityUsingEquals, equalityUsingEquals ?
                "Content is identical, with same case" :
                "Content is different due to case");
        System.out.printf("\n*** Using .equals() is preferred in comparing Strings as it checks if both strings have the same content/value ***");

        // Compare the two user-entered strings using .equalsIgnoreCase()
        System.out.printf("\nString 1 .equalsIgnoreCase() String 2: %b (%s)", equalityUsingEqualsIgnoreCase, equalityUsingEqualsIgnoreCase ?
                "Content is identical, ignoring case" :
                "Content is different");
    }
}