package india.abhijeet.k.farmafriend;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Admin_UserList_FormsReq extends AppCompatActivity {



    private RecyclerView mRecyclerView;

    private AdminUserListAdapter adminUserListAdapter;

    private List<UploadingFarmer> mUploadingFarmers;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin__user_list__forms_req);

        mRecyclerView=findViewById(R.id.RecyclerView_Req_UsrList);

        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));


        mUploadingFarmers =new ArrayList<>();

        readUsers();



    }


    public void readUsers()
    {


        final FirebaseUser firebaseUser= FirebaseAuth.getInstance().getCurrentUser();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Forms");

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                mUploadingFarmers.clear();

                for (DataSnapshot snapshot: dataSnapshot.getChildren())
                {

                    UploadingFarmer uploadingFarmer = snapshot.getValue(UploadingFarmer.class);

                    mUploadingFarmers.add(uploadingFarmer);


                    Toast.makeText(getApplicationContext(),"fetching users",Toast.LENGTH_SHORT).show();


                }


                adminUserListAdapter = new AdminUserListAdapter(getApplicationContext(),mUploadingFarmers);
                mRecyclerView.setAdapter(adminUserListAdapter);





            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }


}
