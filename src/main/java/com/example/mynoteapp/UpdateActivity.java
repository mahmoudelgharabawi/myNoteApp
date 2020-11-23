package com.example.mynoteapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateActivity extends AppCompatActivity {

    EditText title_input,author_input,pages_input;
    Button update_button , delete_button;
    String id , title, author , pages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        title_input = (EditText)findViewById(R.id.title_input2);
        author_input = (EditText)findViewById(R.id.author_input2);
        pages_input = (EditText)findViewById(R.id.pages_input2);
        update_button = (Button) findViewById(R.id.update_button);
        delete_button = (Button) findViewById(R.id.delete_button);

        getAndsetData();


        update_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(UpdateActivity.this);

                title = title_input.getText().toString();
                author = author_input.getText().toString();
                pages = pages_input.getText().toString();
                myDB.updateData(id,title,author,pages);
                finish();
            }
        });

        delete_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmDialog();
            }
        });


    }

    private void confirmDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete "+title+" ?");
        builder.setMessage("Are you sure you want to delete "+title+" ?");

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(UpdateActivity.this);
                myDB.deleteOneRow(id);
                finish();
            }
        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.create().show();

    }

    private void getAndsetData() {
        if (getIntent().hasExtra("id")&&
                getIntent().hasExtra("title") &&
                getIntent().hasExtra("author")&&
                getIntent().hasExtra("pages")
        ){

            id = getIntent().getStringExtra("id");
            title = getIntent().getStringExtra("title");
            author = getIntent().getStringExtra("author");
            pages = getIntent().getStringExtra("pages");

            title_input.setText(title);
            author_input.setText(author);
            pages_input.setText(pages);

        }else{
            Toast.makeText(this,"No Data. ",Toast.LENGTH_SHORT).show();
        }


    }
}