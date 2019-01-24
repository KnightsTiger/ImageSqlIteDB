package com.xsample.imagesqlitedb;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static final int PICK_IMAGE = 1;
    EditText editText;
    Button button;
    DatabaseHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = findViewById(R.id.editText);
        button = findViewById(R.id.button);
        db = new DatabaseHandler(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK && requestCode==PICK_IMAGE){
            Uri uri = data.getData();
            String x = getPath(uri);
            Toast.makeText(this, x, Toast.LENGTH_SHORT).show();
        }
    }

    private String getPath(Uri uri) {
        if(uri==null)return null;
        String[] projection = {MediaStore.Images.Media.DATA};
        Cursor cursor = managedQuery(uri,projection,null,null,null);
        if(cursor!=null){
            int column_index= cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            return cursor.getString(column_index);
        }
        return null;
    }

    public void insertimg(View view) {
        Intent intent = new Intent(Intent.ACTION_PICK, Uri.parse("contents://media/internal/images/media"));
        startActivityForResult(intent,PICK_IMAGE);

    }
}
