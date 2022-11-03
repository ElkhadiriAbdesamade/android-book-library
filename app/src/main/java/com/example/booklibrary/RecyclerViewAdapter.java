package com.example.booklibrary;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.io.ByteArrayOutputStream;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>
{


    private Context mContext ;
    private List<Livre> mData ;


    public RecyclerViewAdapter(Context mContext, List<Livre> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view ;
        LayoutInflater mInflater = LayoutInflater.from(mContext);
        view = mInflater.inflate(R.layout.cardview_item_book,parent,false);
        return new MyViewHolder(view);
    }



    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {

       byte[] bitmapdata=mData.get(position).getImage();


        holder.tv_book_title.setText(mData.get(position).getTitre());
        holder.img_book_cover.setImageBitmap(BitmapFactory.decodeByteArray(bitmapdata,0,bitmapdata.length));
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(mContext,LivreActivity.class);

                // passing data to the book activity
                intent.putExtra("Title",mData.get(position).getTitre());
                intent.putExtra("Editeur",mData.get(position).getEditeur());
                intent.putExtra("Genre",mData.get(position).getGenre());
                intent.putExtra("Descrep",mData.get(position).getDescription());
                intent.putExtra("Cover",mData.get(position).getImage());
                // start the activity
                mContext.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder
    {
        TextView tv_book_title;
        ImageView img_book_cover;
        CardView cardView ;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_book_title =  itemView.findViewById(R.id.titreCardView) ;
            img_book_cover =  itemView.findViewById(R.id.coverCardView);
            cardView = itemView.findViewById(R.id.cardviewid);


        }
    }

}
