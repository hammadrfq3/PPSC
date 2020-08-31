package com.companyname.ppsc.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.companyname.ppsc.view.MatricActivity;
import com.companyname.ppsc.model.Class;
import com.companyname.ppsc.R;

import java.util.ArrayList;

public class ClassAdapter extends RecyclerView.Adapter<ClassAdapter.Holder> {

    private Context context;
    private ArrayList<Class> classes;

    public ClassAdapter(Context context, ArrayList<Class> classes) {
        this.context = context;
        this.classes = classes;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.class_list_item,parent,false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, final int position) {
        Class classObj=classes.get(position);
        holder.textView.setText(classObj.getTitle());

        int resourceId=context.getResources().getIdentifier(classObj.getImage(),"drawable",context.getPackageName());
        holder.imageView.setImageResource(resourceId);

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(position==0){
                    Intent matricIntent=new Intent(context, MatricActivity.class);
                    matricIntent.putExtra("EXAM_TYPE","examtype1");
                    context.startActivity(matricIntent);
                }else if(position==1){
                    Intent matricIntent=new Intent(context, MatricActivity.class);
                    matricIntent.putExtra("EXAM_TYPE","examtype2");
                    context.startActivity(matricIntent);
                }else if(position==2){
                    Intent matricIntent=new Intent(context, MatricActivity.class);
                    matricIntent.putExtra("EXAM_TYPE","examtype3");
                    context.startActivity(matricIntent);
                }else if(position==3){
                    Intent matricIntent=new Intent(context, MatricActivity.class);
                    matricIntent.putExtra("EXAM_TYPE","examtype4");
                    context.startActivity(matricIntent);
                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return classes.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        TextView textView;
        ImageView imageView;
        CardView cardView;

        public Holder(@NonNull View itemView) {
            super(itemView);
            textView=itemView.findViewById(R.id.classTextView);
            imageView=itemView.findViewById(R.id.classImageView);
            cardView=itemView.findViewById(R.id.cardViewClass);
        }
    }

}
