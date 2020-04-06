package india.abhijeet.k.farmafriend;

import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.Map;

public class AdminRequestReview extends AppCompatActivity {


    public static final String MAP_TEXT="india.abhijeet.k.farmafriend.MAP_TEXT";



    TextView fName,fAdress,fGatNo,fLandType,fLandArea,fSugarcaneType,fDate,fLocation;
    EditText fRemark;

    ImageView fFarmImg,fSatbaraImg;

    RadioGroup rdGrp;
    RadioButton rdoApprove,rdoReject,checkedbtn;

    private StorageReference mStorageRef;
    private DatabaseReference mDatabaseRef;

    private FirebaseAuth firebaseAuth;

    UploadingFarmer  mUploadingFarmers =new UploadingFarmer();



    LocationListener locationListener;
    LocationManager locationManager;
    Location Currentlocation;


    CharSequence Remark,CurStatus;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_request_review);





      //  final FirebaseUser firebaseUser= FirebaseAuth.getInstance().getCurrentUser();
    //    DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Forms");



        Intent intent=getIntent();
        String usrId= intent.getStringExtra(AdminUserListAdapter.EXTRA_TEXT);


        rdGrp=findViewById(R.id.RdoGrp_AdmReqRev_Status);


          fName=findViewById(R.id.lbl_AdmReqRev_FarmerName);
          fAdress=findViewById(R.id.lbl_AdmReqRev_FarmerAddress);
          fGatNo=findViewById(R.id.lbl_AdmReqRev_FarmerGatNo);
          fLandType=findViewById(R.id.lbl_AdmReqRev_FarmerLandType);
          fLandArea=findViewById(R.id.lbl_AdmReqRev_FarmerLandArea);


        //  fSugarcaneType=findViewById(R.id.lbl_AdmReqRev_FarmerSugarcaneType);

        fSugarcaneType=findViewById(R.id.lbl_AdmReqRev_FormSugarCaneType);

          fDate=findViewById(R.id.lbl_AdmReqRev_FarmerSubDate);
          fLocation=findViewById(R.id.lbl_AdmReqRev_FarmerLocation);


        fRemark=findViewById(R.id.txt_AdmReqRev_FormRemark);


        fFarmImg=findViewById(R.id.Imgvw_AdmReqRev_FarmImg);
         fSatbaraImg=findViewById(R.id.Imgvw_AdmReqRev_SatBaraImage);



        firebaseAuth =FirebaseAuth.getInstance();

        mStorageRef= FirebaseStorage.getInstance().getReference("Forms");
        mDatabaseRef= FirebaseDatabase.getInstance().getReference("Forms").child(usrId.trim());


              fetchData(usrId);

    }




    public void dispFarmLocation(View view)
    {

        Intent intent=new Intent(this,MapsActivity.class);

       intent.putExtra(MAP_TEXT,fLocation.getText().toString().trim());

        startActivity(intent);



    }







    public void fetchData(String usrId)
    {

       // Toast.makeText(this," Fetching Data. of ....."+usrId,Toast.LENGTH_SHORT).show();


      // final String url ="https://farma-friend.firebaseio.com/Forms/-LazimXEYkdHweEJta8d";
       // final UploadingFarmer  mUploadingFarmers =new UploadingFarmer();

       // final FirebaseUser firebaseUser= FirebaseAuth.getInstance();
     // final DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Forms").child(usrId);





        //////////////fetching operation

        mDatabaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                 UploadingFarmer uploadingFarmer = dataSnapshot.getValue(UploadingFarmer.class);

                            fName.setText(uploadingFarmer.getName());

                        fAdress.setText(uploadingFarmer.getAdress());
                         fGatNo.setText(uploadingFarmer.getGatNo());
                        fLandType.setText(uploadingFarmer.getLandName());
                        fLandArea.setText(uploadingFarmer.getLandArea());

                        try {
                            fSugarcaneType.setText(uploadingFarmer.getSugaecaneName());
                            Toast.makeText(getApplicationContext()," sugarfcane "+uploadingFarmer.getSugaecaneName(),Toast.LENGTH_SHORT).show();


                        }
                        catch (Exception esc)
                        {
                            Toast.makeText(getApplicationContext()," Sugar....:"+esc,Toast.LENGTH_SHORT).show();

                        }
                        finally {

                           fSugarcaneType.setText("92-5");


                        }

                         fDate.setText(uploadingFarmer.getDate());
                         fLocation.setText(uploadingFarmer.getcurLocation());

                        // fFarmImg.setImageURI(Uri.parse(uploadingFarmer.getFarmImgUri().trim()));
                        fSatbaraImg.setImageURI(Uri.parse(uploadingFarmer.getSatBaraImgUri().trim()));

                Glide.with(getApplicationContext())
                        .load(uploadingFarmer.getFarmImgUri())
                        .into(fFarmImg);


                Glide.with(getApplicationContext())
                        .load(uploadingFarmer.getSatBaraImgUri())
                        .into(fSatbaraImg);



                try {
                    Currentlocation = (Location) fLocation.getText();
                }catch (Exception ee)
                {

                   // Toast.makeText(getApplicationContext()," location excp"+Currentlocation.toString(),Toast.LENGTH_SHORT).show();

                }




//                Toast.makeText(getApplicationContext()," Sugar....."+uploadingFarmer.getSugaecaneName(),Toast.LENGTH_SHORT).show();


              //  Toast.makeText(getApplicationContext(),"Satbara"+uploadingFarmer.getFormRemarks(),Toast.LENGTH_SHORT).show();




             //   Toast.makeText(getApplicationContext(),"Status :"+uploadingFarmer.getFormStatus()+" remark :"+uploadingFarmer.getFormRemarks(),Toast.LENGTH_SHORT).show();







            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }





    public void RdbStatusCheck(View view)
    {

        int checked=rdGrp.getCheckedRadioButtonId();

        checkedbtn=findViewById(checked);

        CurStatus=checkedbtn.getText().toString();
        Toast.makeText(this," "+checkedbtn.getText().toString(),Toast.LENGTH_SHORT).show();

         Remark=fRemark.getText().toString();

    }




    public void ReviewForm(View view)
    {


        final FirebaseUser firebaseUser= FirebaseAuth.getInstance().getCurrentUser();
      //  DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Forms");


        UploadingFarmer uploadingFarmer=new UploadingFarmer();

        Remark=fRemark.getText().toString();

        uploadingFarmer.setFormStatus(CurStatus.toString().trim());
        uploadingFarmer.setFormRemarks(Remark.toString().trim());

        mDatabaseRef.child("formStatus").setValue(CurStatus.toString().trim());
        mDatabaseRef.child("formRemarks").setValue(Remark.toString().trim());





    }

















}
