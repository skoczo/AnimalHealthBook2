package com.skoczo.animalheathbook.activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.skoczo.animalheathbook.R;
import com.skoczo.animalheathbook.db.DatabaseManager;
import com.skoczo.animalheathbook.db.entieties.Animal;
import com.skoczo.animalheathbook.db.entieties.Breed;
import com.skoczo.animalheathbook.db.entieties.Weigth;
import com.skoczo.animalheathbook.dbgen.DatabaseHelper;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.List;

public class AnimalAddActivity extends AppCompatActivity {

    private static final int SELECT_PICTURE = 1;
    private static final int REQUEST_IMAGE_CAPTURE = 2;
    DatabaseHelper dbhelper;

    private static String[] BREEDS;
    private Button openGallery;
    private ImageView animalImage;
    private Button openCamera;
    private Bitmap animalImg;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animal_add);

        dbhelper = new DatabaseHelper(getApplicationContext());

        try {
            List<Breed> breeds = dbhelper.getBreedDao().queryForAll();
            BREEDS = new String[breeds.size()];
            for(int i=0; i<breeds.size(); i++) {
                BREEDS[i] = breeds.get(i).getName();
            }
        } catch (SQLException e ) {
            Log.e(getClass().getName(), "Error during breeds queryForAll", e);
        }

        final ArrayAdapter<String> adapter = new
                ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, BREEDS);
        AutoCompleteTextView textView = (AutoCompleteTextView)
                findViewById(R.id.breed_value);
        textView.setAdapter(adapter);

        animalImage = (ImageView)findViewById(R.id.animalImageView);

        
        final EditText name = (EditText)findViewById(R.id.animal_name_value);

        final AutoCompleteTextView breedName = (AutoCompleteTextView) findViewById(R.id.breed_value);
        final EditText animalAge = (EditText)findViewById(R.id.age_value);
        final EditText animalWeigth = (EditText)findViewById(R.id.weigth_value);


        openGallery = (Button)findViewById(R.id.gallery_button);
        openGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent,
                        "Select Picture"), SELECT_PICTURE);
            }
        });


        openCamera = (Button)findViewById(R.id.camera_button);
        openCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(intent, REQUEST_IMAGE_CAPTURE);
                }

            }
        });


        Button addAnimal = (Button) findViewById(R.id.add_animal_button);

        addAnimal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(name.getText().length() == 0) {
                    Toast.makeText(getApplicationContext(), "Name can't be empty", Toast.LENGTH_LONG).show();
                    return;
                }

                if(breedName.getText().length() == 0) {
                    Toast.makeText(getApplicationContext(), "Breed can't be empty", Toast.LENGTH_LONG).show();
                    return;
                }

                Breed breed = new Breed();
                breed.setName(breedName.getText().toString());

                Animal animal = new Animal();
                animal.setName(name.getText().toString());

                if(animalImg != null) {
                    ByteArrayOutputStream stream = new ByteArrayOutputStream();
                    animalImg.compress(Bitmap.CompressFormat.JPEG, 50, stream);
                    animal.setPicture(stream.toByteArray());
                }

                Weigth weigth = null;
                if(animalWeigth.getText().length() != 0) {
                    weigth = new Weigth();
                    weigth.setWeigth(Integer.parseInt(animalWeigth.getText().toString()));
                    weigth.setDate(Calendar.getInstance().getTime());
                }


                try {
                    if(breed != null) {
                        breed = dbhelper.getBreedDao().createIfNotExists(breed);
                        animal.setBreed(breed);
                    }

                    if(weigth != null) {
                        weigth = dbhelper.getWeigthDao().createIfNotExists(weigth);
                        animal.getWeigthList().add(weigth);
                    }

                    int res = dbhelper.getAnimalDao().create(animal);
                } catch (SQLException e) {
                    Log.e(getClass().getName(), "Error during add", e);
                    Snackbar.make(view, "Exception", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                }

                AnimalAddActivity.this.finish();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            animalImg = null;

            if (requestCode == SELECT_PICTURE) {
                Uri selectedImageUri = data.getData();
                try {
                    animalImg = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImageUri);
                } catch (IOException e) {
                    Log.e(getClass().getName(), "Can't set image from gallery: " + e.getMessage());
                    Toast.makeText(getApplicationContext(), "Error during image selection", Toast.LENGTH_LONG).show();
                    return;
                }
                animalImage.setImageURI(selectedImageUri);
            } else if(requestCode == REQUEST_IMAGE_CAPTURE) {
                animalImg = data.getParcelableExtra("data");
            }

            if(animalImg != null) {
                animalImg = Bitmap.createScaledBitmap(animalImg, 400, 400, false);
                animalImage.setImageBitmap(animalImg);
            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }
}
