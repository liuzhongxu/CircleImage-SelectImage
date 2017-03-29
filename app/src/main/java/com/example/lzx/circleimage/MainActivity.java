package com.example.lzx.circleimage;

import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private ImageView imageView;
    private final String IMAGE_TYPE="image/*";
    private final int IMAGE_CODE=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = (ImageView) findViewById(R.id.image);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent getIntent = new Intent(Intent.ACTION_GET_CONTENT);
                getIntent.setType(IMAGE_TYPE);
                startActivityForResult(getIntent, 1);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode==RESULT_CANCELED)
        {
            // action cancelled
        }
        if(resultCode==RESULT_OK)
        {
            Uri selectedimg = data.getData();
            try {
                imageView.setImageBitmap(MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedimg));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
