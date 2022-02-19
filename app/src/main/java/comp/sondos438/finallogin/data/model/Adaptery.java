package comp.sondos438.finallogin.data.model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import comp.sondos438.finallogin.R;

public class Adaptery extends RecyclerView.Adapter<Adaptery.MyViewHolder> {



    private Context mContext;
    private List<MovieModelClass> mData;
    public Adaptery(Context mContext,List<MovieModelClass> mData){
        this.mContext=mContext;
        this.mData=mData;
    }

    @NonNull
    @Override
    public Adaptery.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v;
        LayoutInflater inflater = LayoutInflater.from(mContext);
        v = inflater.inflate(R.layout.movie_item,parent,false);
        return new MyViewHolder(v);

    }

    @Override
    public void onBindViewHolder(@NonNull Adaptery.MyViewHolder holder, int position) {
holder.id.setText(mData.get(position).getId());
holder.name.setText(mData.get(position).getName());
//https://image.tmdb.org/t/p/w500/aWeKITRFbbwY8txG5uCj4rMCfSP.jpg
        Glide.with(mContext).load("https://image.tmdb.org/t/p/w500"+mData.get(position).getImage()).into(holder.image);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder{
TextView id;
TextView name;
ImageView image;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            id= itemView.findViewById(R.id.txtv_id);
            name = itemView.findViewById(R.id.txtv_name);
            image = itemView.findViewById(R.id.imageView);
        }
    }
}
