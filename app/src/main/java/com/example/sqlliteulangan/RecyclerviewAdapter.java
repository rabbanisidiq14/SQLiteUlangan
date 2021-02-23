package com.example.sqlliteulangan;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Date;
import java.util.List;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
public class RecyclerviewAdapter extends RecyclerView.Adapter<RecyclerviewAdapter.UserViewHolder> {
    Context context;
    OnUserClickListener listener;
    List<PersonBean> listPersonInfo;
    public RecyclerviewAdapter(Context context, List<PersonBean> listPersonInfo,OnUserClickListener listener) {
        this.context=context;
        this.listPersonInfo=listPersonInfo;
        this.listener=listener;
    }
    public interface OnUserClickListener{
        void onUserClick(PersonBean currentPerson, String action);
    }
    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view=
                LayoutInflater.from(parent.getContext()).inflate(R.layout.user_row_item,parent,false);
        UserViewHolder userViewHolder=new UserViewHolder(view);
        return userViewHolder;
    }
    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, final int position) {
        final PersonBean currentPerson=listPersonInfo.get(position);
        Date date = new Date();
        date.setTime(currentPerson.getDate());
        holder.ctxtDate.setText(date.toString());
        holder.ctxtTitle.setText(currentPerson.getTitle());
        holder.ctxtDesc.setText(currentPerson.getDesc());
        holder.imgEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onUserClick(currentPerson,"Edit");
            }
        });
        holder.imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onUserClick(currentPerson,"Delete");
            }
        });
    }
    @Override
    public int getItemCount() {
        return listPersonInfo.size();
    }
    public class UserViewHolder extends RecyclerView.ViewHolder {
        TextView ctxtDesc,ctxtTitle, ctxtDate;
        ImageView imgDelete,imgEdit;
        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            ctxtDate=itemView.findViewById(R.id.ctxtDate);
            ctxtDesc=itemView.findViewById(R.id.ctxtDesc);
            ctxtTitle=itemView.findViewById(R.id.ctxtTitle);
            imgDelete=itemView.findViewById(R.id.imgdelete);
            imgEdit=itemView.findViewById(R.id.imgedit);
        }
    }
}