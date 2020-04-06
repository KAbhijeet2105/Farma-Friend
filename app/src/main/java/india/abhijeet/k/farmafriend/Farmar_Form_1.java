package india.abhijeet.k.farmafriend;

import android.Manifest;
import android.content.ClipData;
import android.content.ClipData.Item;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

public class Farmar_Form_1 extends AppCompatActivity {

    EditText FarmarName,FarmerAddress,FarmGatNo,LandNameType,LandArea,SugarcaneType;
    Integer  uptextcode=1;

    CharSequence FormStatus="Not Approved",FormRemarks="No Remarks",UsrId;

    CheckBox checkForm;
    ImageView FarmImage,SatBaraImage;
    private static final int PICK_IMG_REQUEST =1;

    private Uri Farmimg_uri,SatbaraImgUri,FarmDownloadUri,SatbaraDownloadUri;

    private StorageTask FarmImgUploadTask,SatBaraImgUploadTask;

private Handler mHandlar= new Handler();

    private StorageReference mStorageRef;
   private DatabaseReference mDatabaseRef;

    private FirebaseAuth firebaseAuth;


    LocationListener locationListener;
    LocationManager locationManager;
    Location Currentlocation;


    TextView FarmerDateTime,FarmerLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_farmar__form_1);




        FarmerLocation=findViewById(R.id.lbl_Farmer_location);

        FarmarName=findViewById(R.id.txtFarmerName);
        FarmerAddress=findViewById(R.id.txtFarmerAddress);
        FarmGatNo=findViewById(R.id.txtFarm_GatNo);
        LandNameType=findViewById(R.id.txtFarmLandName_type);
        LandArea=findViewById(R.id.txtFarmLandArea);
        SugarcaneType=findViewById(R.id.txt_SugarcaneType);

        FarmerDateTime=findViewById(R.id.lbl_Farmer_date);
         checkForm=findViewById(R.id.chkBox_FarmerForm);


         FarmImage=findViewById(R.id.Imgvw_Farmer_FarmImg);
         SatBaraImage=findViewById(R.id.Imgvw_Farmer_SatBaraImage);



         firebaseAuth =FirebaseAuth.getInstance();

         mStorageRef= FirebaseStorage.getInstance().getReference("Forms");
         mDatabaseRef= FirebaseDatabase.getInstance().getReference("Forms");




        UsrId= firebaseAuth.getCurrentUser().getUid().toString();



       //////////TEXT VALIDATION STARTS FROM HERE//////////////

        FarmarName.addTextChangedListener(new TextWatcher() {
            @Override

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                if (FarmarName.getText().toString().length() <= 0) {
                    FarmarName.setError("Please enter the name!!");
                }
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String temp;

                temp=FarmarName.getText().toString().trim();

                if (FarmarName.getText().toString().length() <= 0) {
                    FarmarName.setError("Please enter the name!!");
                    uptextcode=1;

                } else if (temp.contains("1")||temp.contains("2")||temp.contains("3")||temp.contains("4")||temp.contains("5")||temp.contains("6")||temp.contains("7")||temp.contains("8")||temp.contains("9")||temp.contains("0")||temp.contains("/")||temp.contains("+")||temp.contains("-")||temp.contains("*"))
                {
                    FarmarName.setError("Enter Name in Text format!!!");

                    uptextcode=1;

                }
                 else
                    {
                    FarmarName.setError(null);

                        uptextcode=0;

                }
            }
        });



        FarmerAddress.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                if (FarmerAddress.getText().toString().length() <= 0) {
                    uptextcode=1;
                    FarmerAddress.setError("Please enter the Address properly!!");
                }
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (FarmerAddress.getText().toString().length() <= 0) {
                    FarmerAddress.setError("Please enter the Address properly!!!");
                    uptextcode=1;
                }
                else {
                    FarmerAddress.setError(null);
                    uptextcode=0;

                }
            }
        });




        FarmGatNo.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                if ( FarmGatNo.getText().toString().length() <= 0) {
                    FarmGatNo.setError("Please enter the Gat number of your farm!!");

                    uptextcode=1;
                }
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                if ( FarmGatNo.getText().toString().length() <= 0) {
                    FarmGatNo.setError("Please enter the Gat number of your farm!!");
                    uptextcode=1;
                }
                else {
                    FarmGatNo.setError(null);
                    uptextcode=0;

                }

            }
        });




        LandNameType.addTextChangedListener(new TextWatcher() {
            @Override

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                if (LandNameType.getText().toString().length() <= 0) {
                    LandNameType.setError("Please enter the Type/Name of your land!!");
                    uptextcode=1;
                }
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String temp;

                temp=LandNameType.getText().toString().trim();

                if (LandNameType.getText().toString().length() <= 0) {
                    LandNameType.setError("Please enter the Type/Name of your land!!");
                    uptextcode=1;

                } else if (temp.contains("1")||temp.contains("2")||temp.contains("3")||temp.contains("4")||temp.contains("5")||temp.contains("6")||temp.contains("7")||temp.contains("8")||temp.contains("9")||temp.contains("0")||temp.contains("/")||temp.contains("+")||temp.contains("-")||temp.contains("*"))
                {
                    LandNameType.setError("Enter Name in Text format!!!");

                    uptextcode=1;
                }
                else
                {
                    LandNameType.setError(null);
                    uptextcode=0;
                }
            }
        });


        LandArea.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                if (LandArea.getText().toString().length() <= 0) {
                    uptextcode=1;
                    LandArea.setError("Please enter the Area of your land!!");
                }
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (LandArea.getText().toString().length() <= 0) {

                    uptextcode=1;
                    LandArea.setError("Please enter the Area of your land!!");
                }
                else {
                    LandArea.setError(null);
                    uptextcode=0;

                }
            }
        });



        SugarcaneType.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {


                if (SugarcaneType.getText().toString().length() <= 0) {

                    uptextcode=1;
                    SugarcaneType.setError("Please enter the name/type of sugarcane!!");
                }

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {


            }

            @Override
            public void afterTextChanged(Editable s) {

                if (SugarcaneType.getText().toString().length() <= 0) {
                    SugarcaneType.setError("Please enter the name/type of sugarcane!!");
                    uptextcode=1;

                }
                else {
                    SugarcaneType.setError(null);
                    uptextcode=0;


                }
            }
        });


       /////editText validation ends here.....























        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling

                return;
            }
        } else {

            Toast.makeText(this, "Please make sure is your Location Service is Active and accept the location permission ", Toast.LENGTH_LONG).show();

        }




        //Date


        Calendar calendar = Calendar.getInstance();

        FarmerDateTime.setText("   "+DateFormat.getDateInstance(DateFormat.SHORT).format(calendar.getTime()));



        locationListener=new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {


               Currentlocation =location;          // Location  Used in  Form


               // LatLng loc =(LatLng)location;
                FarmerLocation.setText(""+Currentlocation);


            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {


            }

            @Override
            public void onProviderDisabled(String provider) {

            }
        };

        locationManager=(LocationManager)getApplicationContext().getSystemService(Context.LOCATION_SERVICE);
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,0,0,locationListener);











    }




    public void FarmImageBtn(View view)
    {

        openFileChooser();
    }





    private void openFileChooser()
    {

        Intent intent =new Intent();
        intent.setType("image/*");
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE,true);
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent.createChooser(intent,"Select Farm Image and Sat Bara Image"),2);

    }



    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if( data !=  null && data.getData()!=null  )
        {


            ClipData clipData =data.getClipData();
            if (clipData!=null)
            {
                FarmImage.setImageURI(clipData.getItemAt(0).getUri());
                SatBaraImage.setImageURI(clipData.getItemAt(1).getUri());

                Farmimg_uri=clipData.getItemAt(0).getUri();
                SatbaraImgUri=clipData.getItemAt(1).getUri();


                for (int i=0;i<clipData.getItemCount();i++) // for debug purpose
                {

                    ClipData.Item item =clipData.getItemAt(i);
                    Uri tempuri =item.getUri();
                    Log.e("Img at ",tempuri.toString());

                }


            }




        }

        else {

            Toast.makeText(this,"something wrong here!!!!",Toast.LENGTH_LONG).show();

        }

    }





    public void uploadImgs()
    {


        if (Farmimg_uri!=null&&SatbaraImgUri!=null)
        {

          //  Toast.makeText(this, "Form submitted successfully...... location is "+Currentlocation, Toast.LENGTH_SHORT).show();

            final StorageReference fileref= mStorageRef.child(System.currentTimeMillis()+".Farm");


            //Farm image uploading.....


///////////////main code changed listner

            FarmImgUploadTask= fileref.putFile(Farmimg_uri);

            FarmImgUploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                @Override
                public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                    if (!task.isSuccessful())
                    {
                        throw task.getException();

                    }

                    return fileref.getDownloadUrl();
                }
            }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                @Override
                public void onComplete(@NonNull Task<Uri> task) {

                    if (task.isSuccessful()){

                        FarmDownloadUri=task.getResult();
                        //vurl= download_uri.toString().trim();
                        Toast.makeText(Farmar_Form_1.this,"Upload Successfully Farm Img "+FarmDownloadUri,Toast.LENGTH_LONG).show();


                        //UploadingFarmer uploadingFarmer = new UploadingFarmer(Farmimg_uri.toString().trim());
//final data uploading herer

                       // String uploadId = mDatabaseRef.push().getKey();
                       // mDatabaseRef.child(uploadId).setValue(uploadingFarmer);


                    }


                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {

                    Toast.makeText(Farmar_Form_1.this,"Farm img fail "+e.getMessage(),Toast.LENGTH_SHORT).show();
                }
            });



           /////////Satbara Image uploading.....

           mHandlar.postDelayed(new Runnable() {
               @Override
               public void run() {
                   ///satbara code

                   final StorageReference SatBaarstoRef = mStorageRef.child(System.currentTimeMillis()+".Satbara");


                   SatBaraImgUploadTask=SatBaarstoRef.putFile(SatbaraImgUri);


                   SatBaraImgUploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                       @Override
                       public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                           if (!task.isSuccessful())
                           {
                               throw task.getException();

                           }


                           return SatBaarstoRef.getDownloadUrl();
                       }
                   }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                       @Override
                       public void onComplete(@NonNull Task<Uri> task) {

                           if (task.isSuccessful()){

                               SatbaraDownloadUri=task.getResult();
                               //vurl= download_uri.toString().trim();
                               Toast.makeText(Farmar_Form_1.this,"Upload Successfully satbara "+SatbaraDownloadUri,Toast.LENGTH_LONG).show();





                    ////all data (Form) uploading here


                               FormStatus="Under Process";
                               FormRemarks="No Remarks";



                               try {
                                   UploadingFarmer uploadingFarmer = new UploadingFarmer( UsrId.toString(),FarmarName.getText().toString().trim(), FarmerAddress.getText().toString().trim(), FarmGatNo.getText().toString().trim(), LandNameType.getText().toString().trim(), LandArea.getText().toString().trim(), SugarcaneType.getText().toString(), FarmerDateTime.getText().toString().trim(), FarmDownloadUri.toString(), SatbaraDownloadUri.toString(), FarmerLocation.getText().toString().trim(), FormStatus.toString().trim(),FormRemarks.toString().trim());
                                   //final data uploading herer

                                   String uploadId = mDatabaseRef.push().getKey();
                                   mDatabaseRef.child(firebaseAuth.getUid().toString()).setValue(uploadingFarmer);

                               }
                               catch (Exception exc)
                               {

                                   Toast.makeText(Farmar_Form_1.this,"Sorryyy cant upload.. ",Toast.LENGTH_LONG).show();

                               }

                           }


                       }
                   }).addOnFailureListener(new OnFailureListener() {
                       @Override
                       public void onFailure(@NonNull Exception e) {

                           Toast.makeText(Farmar_Form_1.this,"err satbara upload "+e.getMessage(),Toast.LENGTH_SHORT).show();
                       }
                   });












               }
           },500);

            //////satbara image uploading........



        }

        else {

            Toast.makeText(this, "Please select Farm and satbara images.... ", Toast.LENGTH_SHORT).show();


        }



        Toast.makeText(this, " Data  Uploaded .. ", Toast.LENGTH_SHORT).show();


    }

















    public void submitForm(View v)
    {


        if (checkForm.isChecked()==true&&uptextcode==0) {



            uploadImgs();


            Toast.makeText(this, "Form submitted successfully...... location is "+Currentlocation, Toast.LENGTH_SHORT).show();

        }

        else
        {

            Toast.makeText(this, "Form Not Submitted !!......", Toast.LENGTH_SHORT).show();

        }


    }





}

