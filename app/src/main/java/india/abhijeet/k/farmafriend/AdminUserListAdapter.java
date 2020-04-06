package india.abhijeet.k.farmafriend;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class AdminUserListAdapter extends RecyclerView.Adapter<AdminUserListAdapter.ViewHolder> {

         public static final String EXTRA_TEXT="india.abhijeet.k.farmafriend.EXTRA_TEXT";

    private Context mContext;
    private List<UploadingFarmer> muploadingFarmers;


    public AdminUserListAdapter(Context mContext,List<UploadingFarmer> muploadingFarmers)
    {

        this.muploadingFarmers=muploadingFarmers;
        this.mContext=mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {


        View view =LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.user_list_item, null, false);
        //return new AdminUserListAdapter.ViewHolder(view);

        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

         final UploadingFarmer uploadingFarmer =muploadingFarmers.get(position);

         holder.FarmUserName.setText(uploadingFarmer.getName());


               holder.FarmUserName.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View v) {


                       Toast.makeText(mContext,"Clicked "+uploadingFarmer.getName(),Toast.LENGTH_SHORT).show();

                       uploadingFarmer.getUsrId();



                          //Intent intent=new Intent(mContext,AdminRequestReview.class);


                        //  mContext.startActivity(intent);

                      // startActivity(new Intent(this,FarmarDash.class));




                       Intent intent = new Intent().setClass(mContext,AdminRequestReview.class);
                       intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
                       intent.putExtra(EXTRA_TEXT,uploadingFarmer.getUsrId().toString());

// Launch the new activity and add the additional flags to the intent
                       mContext.startActivity(intent);













                   }
               });




    }

    @Override
    public int getItemCount() {
        return muploadingFarmers.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder
 {

   public TextView FarmUserName;

    public ViewHolder(View itemView)
    {
        super(itemView);


        FarmUserName= itemView.findViewById(R.id.lbl_AdminReqList_Usrname);
    }






 }



}
