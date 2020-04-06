package india.abhijeet.k.farmafriend;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class FarmarDash extends AppCompatActivity {

    final int STORAGE_PERMISSION_CODE = 1;
    int checkPermi=0;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_farmar_dash);

        firebaseAuth=FirebaseAuth.getInstance();

//check for permission ,request for permission
        if(ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)==

                getPackageManager().PERMISSION_GRANTED)

        {
            //  Toast.makeText(this,"Permission Granted ",Toast.LENGTH_SHORT).show();


        }
        else

        {
            reqPermi();

        }


    }


    public void goForm(View v) {

        if (checkPermi==1) {
            Intent x;
            x = new Intent(this, Farmar_Form_1.class);
            startActivity(x);
        }
        else
        {

            reqPermi();
        }

    }




    public void goStatus(View v) {

        if (checkPermi==1) {
            Intent x;
            x = new Intent(this,Farmer_Form_Status.class);
            startActivity(x);
        }
        else
        {

            reqPermi();
        }

    }



    public void goPrivacy(View v) {

        if (checkPermi==1) {
            Intent x;
            x = new Intent(this,PrivacyPolicy.class);
            startActivity(x);
        }
        else
        {

            reqPermi();
        }

    }















    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {


        if (requestCode==STORAGE_PERMISSION_CODE){

            if (grantResults.length>0 && grantResults[0]== PackageManager.PERMISSION_GRANTED)
            {

              checkPermi=1;

                //    Toast.makeText(this,"Permission Granted!!  ",Toast.LENGTH_SHORT).show();


            }
            else {
                Toast.makeText(this,"Permission Not Granted!! Please Allow the permission  ",Toast.LENGTH_LONG).show();


            }
        }
    }
    //permission request method
    public void reqPermi()
    {

        if (ActivityCompat.shouldShowRequestPermissionRationale(this , Manifest.permission.ACCESS_FINE_LOCATION)){

            new AlertDialog.Builder(this)
                    .setTitle("Permission needed!!")
                    .setMessage("Permission require for getting your location!")
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            ActivityCompat.requestPermissions(FarmarDash.this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION},STORAGE_PERMISSION_CODE);

                        }
                    }).create().show();
        }
        else{


            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION},STORAGE_PERMISSION_CODE);

        }

    }




     public void farmer_logout(View v)
    {


        startActivity(new Intent(this,LoginActivity.class));

        firebaseAuth.signOut();

        finish();


    }



}
