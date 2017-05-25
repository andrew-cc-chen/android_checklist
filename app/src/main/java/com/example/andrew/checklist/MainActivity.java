package com.example.andrew.checklist;

import android.content.DialogInterface;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<Item> itemList = new ArrayList<Item>();
    ArrayList<String> nameList = new ArrayList<String>();
    ArrayAdapter listAdapter;
    ListView checklist;

    private GestureDetectorCompat gestureDetectorCompat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, nameList);
        checklist = (ListView) findViewById(R.id.checklist);
        checklist.setAdapter(listAdapter);

        View view = findViewById(R.id.checklist);


        //gestureDetectorCompat = new GestureDetectorCompat(this, new MyGestureListener());
        gestureDetectorCompat = new GestureDetectorCompat(view.getContext(), new MyGestureListener());



        //delete if there are l/r swipes on an item
        /*checklist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });*/

        //edit if there's long press on an item
        /*checklist.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this, nameList.get(position), Toast.LENGTH_SHORT).show();
                editItem(position);
                Log.d("hihi", "andrew");
                return false;
            }
        });*/
    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
        this.gestureDetectorCompat.onTouchEvent(event);
        return super.onTouchEvent(event);
        //return gestureDetectorCompat.onTouchEvent(event);
    }

    class MyGestureListener extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onDown(MotionEvent event) {
            String word = "down";
            Toast.makeText(MainActivity.this, word, Toast.LENGTH_SHORT).show();
            Log.d("hihi", "qwerqwerqwerqwer");
            return true;
        }

        @Override
        public boolean onFling(MotionEvent event1, MotionEvent event2,
                               float velocityX, float velocityY) {
            String word = "fling";
            Toast.makeText(MainActivity.this, word, Toast.LENGTH_SHORT).show();
            Log.d("hihi", "asdfadsfasdfasdf");
            return true;
        }
    }

    //popup dialog to add item
    public void addItem(View view) {
        /*Dialog addItemDialog = new Dialog(this);
        addItemDialog.setContentView(R.layout.add_item_dialog);
        addItemDialog.setCancelable(true);
        addItemDialog.show();
        addItemFlag = true;*/

        //set view to custom layout
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        final View addItemLayout = inflater.inflate(R.layout.add_item_dialog, null);
        builder.setView(addItemLayout);

        //able to cancel by pressing outside of the dialog
        builder.setCancelable(true);

        //yes dialog button
        builder.setPositiveButton(R.string.add_item_add, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                EditText itemText = (EditText) addItemLayout.findViewById(R.id.itemName);
                System.out.println(itemText.getText().toString());
                String item = itemText.getText().toString();
                if (!item.isEmpty()) {
                    //update two corresponding lists
                    itemList.add(new Item(item));
                    nameList.add(item);

                    listAdapter.notifyDataSetChanged();
                    Toast.makeText(MainActivity.this, item + " is added to checklist", Toast.LENGTH_SHORT).show();
                } else {
                    String emptyItemWarning = "please enter an item";
                    Toast.makeText(MainActivity.this, emptyItemWarning, Toast.LENGTH_SHORT).show();
                }
            }
        });
        //no dialog button
        builder.setNegativeButton(R.string.add_item_cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        final AlertDialog addItemDialog = builder.create();
        addItemDialog.show();

        //String con = checklist.getContext().toString();
        String con = getApplicationContext().toString();
        Toast.makeText(MainActivity.this, con, Toast.LENGTH_LONG).show();
    }

    //popup dialog to edit item
    public void editItem(final int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        final View editItemLayout = inflater.inflate(R.layout.add_item_dialog, null);
        //set text to item name
        final EditText itemText = (EditText) editItemLayout.findViewById(R.id.itemName);
        itemText.setText(nameList.get(position));

        //setup pos and neg buttons
        builder.setPositiveButton(R.string.edit_item_edit, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String oriText = nameList.get(position);
                String newText = itemText.getText().toString();

                //update two corresponding lists
                itemList.set(position, new Item(newText));
                nameList.set(position, newText);

                listAdapter.notifyDataSetChanged();
                Toast.makeText(MainActivity.this, "changed " + oriText+ " to " + newText,
                        Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton(R.string.add_item_cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        builder.setView(editItemLayout);
        builder.create().show();
    }
}
