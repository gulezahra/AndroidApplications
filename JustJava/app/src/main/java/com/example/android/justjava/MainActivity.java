package com.example.android.justjava;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.Menu;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {

    int quantity = 1;
    private View view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // Setting the activity main xml layout onto the content view of the activity.
    }

    /**
     * This method is called when the plus button is clicked.
     */
    public void increment(View view) {
        if (quantity == 100) {
            Toast.makeText(this, "You cannot have more than 100 cups of coffee.", Toast.LENGTH_SHORT).show();
            return;
        }
        quantity += 1;
        displayQuantity(quantity);
    }

    /**
     * This method is called when the minus button is clicked.
     */
    public void decrement(View view) {
        if (quantity == 1) {
            Toast.makeText(this, "You cannot have less than 1 cups of coffee.", Toast.LENGTH_SHORT).show();
            return;
        }
        quantity -= 1;
        displayQuantity(quantity);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        // Get user's name
        EditText nameField = (EditText) findViewById(R.id.name_field);
        Editable nameEditable = nameField.getText();
        String name = nameEditable.toString();

        // Figure out if the user wants whipped cream topping
        CheckBox whippedCreamCheckBox = (CheckBox) findViewById(R.id.whipped_cream_checkbox);
        boolean hasWhippedCream = whippedCreamCheckBox.isChecked();

        // Figure out if the user wants whipped cream topping
        CheckBox chocolateCheckBox = (CheckBox) findViewById(R.id.chocolate_checkbox);
        boolean hasChocolate = chocolateCheckBox.isChecked();

        // Calculate the price
        int price = calculatePrice(hasWhippedCream, hasChocolate); //sending thr boolean variables in it

        // Display the order summary on the screen
        String priceMessage = createOrderSummary(name, price, hasWhippedCream, hasChocolate);

        //Send an intent to an email app
        Intent intent = new Intent(Intent.ACTION_SEND);

        if (name.equals("")) {
            Toast.makeText(MainActivity.this, "Please enter your name", Toast.LENGTH_SHORT).show();
        } else {
            // Use any one of these three
            intent.setType("text/html"); // this will pop up the window of many apps to select from them that use which app for opening this email
            //intent.setType("plain/text"); //work same as above
            //intent.setType("message/rfc822"); work same as above

            // **********************  OR these two lines when work only for Gmail   **************************
            //intent.setData(Uri.parse("mailto:")); //this works only for email apps and do not set any address for the person whom mail is sent
            //intent.setClassName("com.google.android.gm", "com.google.android.gm.ComposeActivityGmail"); //setting this only works for gmail
            // ************************************************************************************************

            //intent.putExtra(Intent.EXTRA_EMAIL, new String[]{"mail to that cafe"}); //This sets the address for the person whom mail is sent

            intent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.order_summary_email_subject) + name); //Writes the subject text
            intent.putExtra(Intent.EXTRA_TEXT, priceMessage); //Writes the email content
            startActivity(intent);
        }
    }

    private int calculatePrice(boolean addWhippedCream, boolean addChocolate) {
        // First calculate the price of one cup of coffee
        int basePrice = 20; //it has to be a local variable otherwise every time it will update to price from outside method as well

        // If the user wants whipped cream, add $1 per cup
        if (addWhippedCream) {
            basePrice = basePrice + 1;
        }

        // If the user wants chocolate, add $2 per cup
        if (addChocolate) {
            basePrice = basePrice + 2;
        }

        // Calculate the total order price by multiplying by the quantity
        return quantity * basePrice;
    }

    /**
     * Create summary of the order.
     * <p/>
     * name            on the order
     * price           of the order
     * coffeeType      on the order
     * addWhippedCream is whether or not to add whipped cream to the coffee
     * addChocolate    is whether or not to add chocolate to the coffee
     *
     * @return text summary
     */

    private String createOrderSummary(String name, int price, boolean addWhippedCream, boolean addChocolate) {
        String priceMessage = getString(R.string.order_summary_name) + " " + name;
        priceMessage += "\n" + getString(R.string.order_summary_whipped_cream) + " " +  addWhippedCream;
        priceMessage += "\n" + getString(R.string.order_summary_chocolate) + " " +  addChocolate;
        priceMessage += "\n" + getString(R.string.order_summary_quantity) + " " +  quantity;
        priceMessage += "\n" + getString(R.string.order_summary_price) +  " " + NumberFormat.getCurrencyInstance().format(price); // set up the currency symbol for the used country
        priceMessage += "\n" + getString(R.string.thank_you); //Referring to the string made in strings.xml file
        return priceMessage;
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number); // As munber is int , so have to give a starting int, so that it makes int as string.
    }
}