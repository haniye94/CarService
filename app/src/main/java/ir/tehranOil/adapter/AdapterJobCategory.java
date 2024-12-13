package ir.tehranOil.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ir.servicea.R;
import ir.tehranOil.app.DataBaseHelper;
import ir.tehranOil.app.G;
import ir.tehranOil.model.ModelJobCategory;

public class AdapterJobCategory extends RecyclerView.Adapter<AdapterJobCategory.ViewHolder> {
    Context context;
    LayoutInflater layoutInflater;
    List<ModelJobCategory> models;
    AlertDialog alertDialogs_detect_type;
    DataBaseHelper mDBHelper;

    public AdapterJobCategory(Context context, List<ModelJobCategory> models) {
        this.context = context;
        this.models = models;
        layoutInflater = LayoutInflater.from(context);
        mDBHelper = new DataBaseHelper(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(layoutInflater.inflate(R.layout.item_job_category, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        holder.job_category.setTypeface(G.ExtraBold);
        if (models.get(position).getStatus()==1) {
            holder.job_category.setText(models.get(position).getTitle().toString());
            holder.job_category.setBackgroundResource(R.drawable.item_job_category);
            holder.job_category.setTextColor(Color.parseColor("#ffffff"));
        } else {
            holder.job_category.setText(models.get(position).getTitle().toString());
            holder.job_category.setBackgroundResource(R.drawable.item_job_category_false);
            holder.job_category.setTextColor(Color.parseColor("#222222"));
        }

    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView job_category;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            job_category = itemView.findViewById(R.id.job_category);

        }
    }


}
