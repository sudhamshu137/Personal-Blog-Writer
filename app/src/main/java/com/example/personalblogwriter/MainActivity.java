package com.example.personalblogwriter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    EditText titleEt, bodyEt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        titleEt = findViewById(R.id.titleEt);
        bodyEt = findViewById(R.id.bodyEt);

    }

    public void create(View view){
        if(ActivityCompat.checkSelfPermission( MainActivity.this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE) ==
                PackageManager.PERMISSION_GRANTED){

            saveit();

        }
        else{
            ActivityCompat.requestPermissions(MainActivity.this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    100);
        }


    }


    public void saveit() {

        String title = titleEt.getText().toString().trim();
        String body = bodyEt.getText().toString().trim();
        String data = title + "\n\n" + body;

        FileOutputStream fos = null;
        try {
            long time= System.currentTimeMillis();
            fos = openFileOutput(String.valueOf(time), MODE_PRIVATE);
            fos.write(data.getBytes());
            Toast.makeText(this, "Saved to Android" + getFilesDir() + "/" + time + ".txt",
                    Toast.LENGTH_LONG).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }


    public void requestfocus(View view){
        bodyEt.requestFocus();
    }

}