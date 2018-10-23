package com.example.android.hurlingjerseylab;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.Settings;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView mNameTextView, mNumberTextView;
    private Jersey mCurrentJersey;
    private Jersey mClearedJersey;
    private boolean mPurpleColour = false;
    private boolean saved_color_state;


    // Constants:
    private final static String PREFS = "PREFS";
    private static final String KEY_JERSEY_NAME = "KEY_JERSEY_NAME";
    private static final String KEY_JERSEY_NUMBER = "KEY_JERSEY_NUMBER";
    private static final String KEY_JERSEY_COLOUR = "KEY_JERSEY_COLOUR";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mNameTextView = findViewById(R.id.name_text);
        mNumberTextView = findViewById(R.id.number_text);
        ImageView image = findViewById(R.id.my_image);

        mCurrentJersey = new Jersey();



        SharedPreferences prefs = getSharedPreferences(PREFS, MODE_PRIVATE);
        String name = "Ireland";
        name = prefs.getString(KEY_JERSEY_NAME,getString(R.string.name_initial));
        mCurrentJersey.setName(name);
        // TODO: Get the other fields. Then use them all
        Integer number =16;
        number = prefs.getInt(KEY_JERSEY_NUMBER, -1);
        mCurrentJersey.setPlayerNumber(number);
        mPurpleColour = prefs.getBoolean(KEY_JERSEY_COLOUR, false);

        if (mPurpleColour == true) {
            image.setImageResource(R.drawable.purple_jersey);
        }
        else{
            image.setImageResource(R.drawable.green_jersey);
        }


        // Boilerplate code. Don't mess with it.
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertJersey();
            }
        });
        showCurrentItem();
    }

    private void insertJersey() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view = getLayoutInflater().inflate(R.layout.dialog_add, null, false);
        builder.setView(view);

        final EditText namedEditText = view.findViewById(R.id.edit_name_dialog);
        final EditText numberEditText = view.findViewById(R.id.edit_number_dialog);

        namedEditText.setText(mCurrentJersey.getName());
        numberEditText.setText("" + mCurrentJersey.getPlayerNumber());

        builder.setNegativeButton(android.R.string.cancel, null);
        builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String name = namedEditText.getText().toString();
                int newNumber = Integer.parseInt(numberEditText.getText().toString());

                mCurrentJersey.setName(name);
                mCurrentJersey.setPlayerNumber(newNumber);

                showCurrentItem();
            }
        });
        builder.create().show();
    }

    private void showCurrentItem() {

        ImageView image = findViewById(R.id.my_image);
        mNumberTextView.setText(getString(R.string.number_initial, mCurrentJersey.getPlayerNumber()));
        mNameTextView.setText(getString(R.string.name_initial,mCurrentJersey.getName()));
        if (mPurpleColour == true) {
            image.setImageResource(R.drawable.purple_jersey);
        }
        else{
            image.setImageResource(R.drawable.green_jersey);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        // Boilerplate code. Don't mess with it.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        // TODO: Later worry about menus.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            startActivity(new Intent(Settings.ACTION_LOCALE_SETTINGS));

            return true;
        }

        if (id ==R.id.actions_reset) {
            mClearedJersey = mCurrentJersey;
            saved_color_state = mPurpleColour;
            mPurpleColour = false;
            mCurrentJersey = new Jersey();
            mCurrentJersey.setName("Ireland");
            mCurrentJersey.setPlayerNumber(16);
            ImageView image = findViewById(R.id.my_image);
            if (mPurpleColour == true) {
                image.setImageResource(R.drawable.purple_jersey);
            }
            else{
                image.setImageResource(R.drawable.green_jersey);
            }


            showCurrentItem();
            Snackbar snackbar = Snackbar.make(findViewById(R.id.coordinator_layout),
                    "Item cleared", Snackbar.LENGTH_LONG);
            snackbar.setAction("UNDO", new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mCurrentJersey = mClearedJersey;
                    mPurpleColour = saved_color_state;

                    showCurrentItem();
                    Snackbar.make(findViewById(R.id.coordinator_layout),
                            "Item restored", Snackbar.LENGTH_SHORT).show();
                }
            });
            snackbar.show();
            return true;
        }



        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences prefs = getSharedPreferences(PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(KEY_JERSEY_NAME, mCurrentJersey.getName());
        editor.putInt(KEY_JERSEY_NUMBER, mCurrentJersey.getPlayerNumber());
        editor.putBoolean(KEY_JERSEY_COLOUR, mPurpleColour);
// Put the other fields into the editor
        editor.commit();
    }


    public void handlePurpleJersey(View view) {
        ImageView image = findViewById(R.id.my_image);


        mPurpleColour = !mPurpleColour;
        if (mPurpleColour == true) {
            image.setImageResource(R.drawable.purple_jersey);
        }
        else{
            image.setImageResource(R.drawable.green_jersey);
        }
    }
}
