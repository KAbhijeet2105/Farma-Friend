package india.abhijeet.k.farmafriend;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class Farmer_Form_Status extends AppCompatActivity {

    TextView FormStatus,FormRemarks;

    private StorageReference mStorageRef;
    private DatabaseReference mDatabaseRef;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_farmer__form__status);

        FormStatus=findViewById(R.id.lbl_FarmerForm_Status);
        FormRemarks= findViewById(R.id.lbl_FarmerForm_Remarks);


        firebaseAuth= FirebaseAuth.getInstance();
     //   final FirebaseUser user = firebaseAuth.getCurrentUser();

    //    mStorageRef= FirebaseStorage.getInstance().getReference("Forms");
        mDatabaseRef= FirebaseDatabase.getInstance().getReference("Forms");





    }


    public void Refresh_Status(View view) {



            FirebaseUser user=FirebaseAuth.getInstance().getCurrentUser();
            String userid=user.getUid();



            FirebaseDatabase database = FirebaseDatabase.getInstance();

            DatabaseReference myRef = database.getReference("Forms").child(userid);


          //  myRef.child(userid).setValue(user);


           // Query specific_user =myRef.child(user.getUid());

         //   DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Forms").child(userid);



            myRef.addListenerForSingleValueEvent(
                   new ValueEventListener() {
                       @Override
                      public void onDataChange(DataSnapshot dataSnapshot) {
                            //here you will get the data
                           try {


                               UploadingFarmer uploadingFarmer =dataSnapshot.getValue(UploadingFarmer.class);

                                String Status =uploadingFarmer.getFormStatus().toString().trim();

                               String remark =uploadingFarmer.getFormRemarks().toString().trim();


                               FormStatus.setText(Status);

                               FormRemarks.setText(remark);

                                Toast.makeText(getApplicationContext(),"during retrivation!"+Status,Toast.LENGTH_SHORT).show();


                            }catch (Exception ee)
                            {
                                Toast.makeText(getApplicationContext(),"Error during retrivation!!"+ee,Toast.LENGTH_SHORT).show();

                            }

//
//
                        }
//
                        @Override
                        public void onCancelled(DatabaseError databaseError) {


                        }
                    });






/*

        myRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                //Log.d("foods",dataSnapshot.getValue().toString());




                //here you will get the data
                try {


                    UploadingFarmer uploadingFarmer =dataSnapshot.getValue(UploadingFarmer.class);

                    String Status =uploadingFarmer.getFormStatus().toString().trim();

                    String remark =uploadingFarmer.getFormRemarks().toString().trim();

                    FormStatus.setText(Status);

                    FormRemarks.setText(remark);
                   // Toast.makeText(getApplicationContext(),"during retrivation!"+Status,Toast.LENGTH_SHORT).show();


                }catch (Exception ee)
                {
                    Toast.makeText(getApplicationContext(),"Error during retrivation!!"+ee,Toast.LENGTH_SHORT).show();

                }






            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });







*/





    }

}
