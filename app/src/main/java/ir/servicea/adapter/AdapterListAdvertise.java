package ir.servicea.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import ir.servicea.app.G;
import com.squareup.picasso.Picasso;

import java.util.List;

import ir.servicea.R;
import ir.servicea.activity.WebViewActivity;
import ir.servicea.model.ModelAdvertise;

public class AdapterListAdvertise extends RecyclerView.Adapter<AdapterListAdvertise.ViewHolder> {
    Context context;
    LayoutInflater layoutInflater;
    List<ModelAdvertise> models;

    public AdapterListAdvertise(Context context, List<ModelAdvertise> models) {
        this.context = context;
        this.models = models;
        layoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(layoutInflater.inflate(R.layout.item_layout_advertise_main, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        holder.txt_description_advertise.setTypeface(G.Bold);
        holder.txt_see_more.setTypeface(G.Normal);

        holder.txt_description_advertise.setText(models.get(position).getDescriptionAdvertise());
        Picasso.get().load(models.get(position).getImageAdvertise()).into(holder.img_advertise);
        holder.txt_see_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, WebViewActivity.class)
                        .putExtra("LINK", G.LINK_BLOG+models.get(position).getSlug())
                        .putExtra("TITLE", models.get(position).getDescriptionAdvertise()));

            }
        });

    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView txt_description_advertise, txt_see_more;
        ImageView img_advertise;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_description_advertise = itemView.findViewById(R.id.txt_description_advertise);
            txt_see_more = itemView.findViewById(R.id.txt_see_more);
            img_advertise = itemView.findViewById(R.id.img_advertise);
        }
    }

}
