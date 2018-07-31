package lambtoncollege.com.perfectmillageapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;
import java.io.File;

import lambtoncollege.com.perfectmillageapp.Database.DatabaseOperations;
import lambtoncollege.com.perfectmillageapp.Database.TableData;

public class ProfileAct extends AppCompatActivity {

    private static final int CONTENT_REQUEST=1337;
    private File output=null;
    ImageView profile;
    EditText fname,lname;
    SharedPreferences preferences;
    public static String PROFILE_PREFF = "profilepreff";
    String imageDecode = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        profile = findViewById(R.id.profilePicture);
        fname = findViewById(R.id.firstName);
        lname = findViewById(R.id.lastName);
        preferences = getSharedPreferences(PROFILE_PREFF,MODE_PRIVATE);

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(takePictureIntent, CONTENT_REQUEST);
                }
            }

        });
        fname.setText(preferences.getString("firstName",""));
        lname.setText(preferences.getString("lastName",""));
        if (preferences.getString("profilePic","").equals("")){


        }else {
            profile.setImageBitmap(decodeBase64(preferences.getString("profilePic","")));
        }

        Button save = findViewById(R.id.save);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("firstName",fname.getText().toString());
                editor.putString("lastName",lname.getText().toString());
                editor.putString("profilePic",imageDecode);
                editor.commit();
                DatabaseOperations dop  = new DatabaseOperations(ProfileAct.this);
                int count =  dop.getCount(TableData.Tableinfo.PROFILE_TABLE_NAME);
                if (count == 0){
                    dop.putProfile(dop,fname.getText().toString(),lname.getText().toString());
                }else {
                    Log.d("Count",""+count);
                }
                startActivity(new Intent(ProfileAct.this,MainActivity.class));
                finish();

            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(ProfileAct.this,MainActivity.class));
        finish();
    }
    public static String encodeTobase64(Bitmap image) {
        Bitmap immage = image;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        immage.compress(Bitmap.CompressFormat.PNG, 100, baos);
        byte[] b = baos.toByteArray();
        String imageEncoded = Base64.encodeToString(b, Base64.DEFAULT);

        Log.d("Image Log:", imageEncoded);
        return imageEncoded;
    }

    public static Bitmap decodeBase64(String input) {
        byte[] decodedByte = Base64.decode(input, 0);
        return BitmapFactory
                .decodeByteArray(decodedByte, 0, decodedByte.length);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode,
                                    Intent data) {
        if (requestCode == CONTENT_REQUEST) {
            if (resultCode == RESULT_OK) {
                if (requestCode == CONTENT_REQUEST && resultCode == RESULT_OK) {
                    Bundle extras = data.getExtras();
                    Bitmap imageBitmap = (Bitmap) extras.get("data");
                    profile.setImageBitmap(imageBitmap);
                    imageDecode =   encodeTobase64(imageBitmap);

                }
            }
        }
    }
}
//L6T 0B2