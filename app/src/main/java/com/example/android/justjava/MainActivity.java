package com.example.android.justjava;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    int p, q = 0, t = 0, c = 0,d=0;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;


    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {

        displayOrder(CreateOrderSummary());

    }

    public void confirmOrder(View view) {
        if (d==1) {
            Intent intent = new Intent(Intent.ACTION_SENDTO);
            intent.setData(Uri.parse("mailto:"));
            intent.putExtra(Intent.EXTRA_EMAIL, new String[]{"baristaHyd@baristamail.com"});
            intent.putExtra(Intent.EXTRA_TEXT, CreateOrderSummary());
            if (intent.resolveActivity(getPackageManager()) != null)
                startActivity(intent);
        }
    }

    public void increase(View view) {

//        if (WhippedCreamCheckBox.isChecked())
//        {
//            t=10;
//        }
        q++;

        displayQuantity(q);

    }

    public void decrease(View view) {
        q--;

        if (q <= 0) {
            q = 0;
        }


        displayQuantity(q);

    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int number) {
        TextView quantityTextView = (TextView) findViewById(
                R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    private void displayOrder(String message) {
        TextView priceTextView = (TextView) findViewById(
                R.id.price_text_view);
        priceTextView.setText(message);
    }

    private String CreateOrderSummary() {
        CheckBoxState();
        CheckBoxState2();
        EditText name, phone;
        String PriceMessage;
        String name_value, phone_value;

        name = (EditText) findViewById(R.id.name);
        phone = (EditText) findViewById(R.id.phone);

        name_value = name.getText().toString();

        phone_value = phone.getText().toString();
        if (phone_value.length() < 10) {
            PriceMessage = "Incorrect Phone Number, Try Again";d=0;
        } else {
            phone_value = "+91" + phone_value;

            String Price = NumberFormat.getCurrencyInstance().format(p);

            PriceMessage = "Name: " + name_value + "\nPhone: " + phone_value + "\n";
            PriceMessage += "Coffee Cup(s): " + q + "\n";
            PriceMessage += "Whipped Cream:" + CheckBoxState() + "\n";
            PriceMessage += "Chocolate Topping:" + CheckBoxState2() + "\n";
            PriceMessage +="The price is: " + Price;
            d=1;





        }
        return PriceMessage;
    }

    private String CheckBoxState() {

        CheckBox checkBox = (CheckBox) findViewById(R.id.whipped_cream_checkbox);
        String state;
        if (checkBox.isChecked()) {
            state = " Yes ";
            t = 10;

        } else {
            state = " No ";
            t = 0;

        }

        p = q * (20 + t + c);
        return state;
    }

    private String CheckBoxState2() {
        CheckBox checkBox2 = (CheckBox) findViewById(R.id.choc_checkbox);
        String state2;
        if (checkBox2.isChecked()) {
            state2 = " Yes ";
            c = 15;

        } else {
            state2 = " No ";
            c = 0;

        }
        p = q * (20 + t + c);
        return state2;

    }


    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.example.android.justjava/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.example.android.justjava/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }
}