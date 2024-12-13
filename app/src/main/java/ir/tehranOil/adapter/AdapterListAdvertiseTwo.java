package ir.tehranOil.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import ir.tehranOil.app.G;
import ir.tehranOil.model.ModelAdvertise2;

import java.util.List;

import ir.servicea.R;

public class AdapterListAdvertiseTwo extends RecyclerView.Adapter<AdapterListAdvertiseTwo.ViewHolder> {
    Context context;
    LayoutInflater layoutInflater;
    List<ModelAdvertise2> models;

    public AdapterListAdvertiseTwo(Context context, List<ModelAdvertise2> models) {
        this.context = context;
        this.models = models;
        layoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(layoutInflater.inflate(R.layout.item_layout_advertise_main_two, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
       holder.txt_description_advertise.setTypeface(G.Normal);

        holder.txt_description_advertise.setText(models.get(position).getDescriptionAdvertise());
        holder.img_advertise.setBackgroundResource(models.get(position).getImageAdvertise());

    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView txt_description_advertise;
        ImageView img_advertise;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_description_advertise = itemView.findViewById(R.id.txt_description_advertise);
            img_advertise = itemView.findViewById(R.id.img_advertise);
        }
    }

}
