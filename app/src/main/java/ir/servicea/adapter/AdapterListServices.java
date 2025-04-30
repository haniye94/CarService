package ir.servicea.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import ir.servicea.app.DataBaseHelper;
import ir.servicea.app.G;

import java.util.List;

import ir.servicea.R;

import ir.servicea.model.ModelServicesCustomer;

public class AdapterListServices extends RecyclerView.Adapter<AdapterListServices.ViewHolder> {
    Context context;
    LayoutInflater layoutInflater;
    List<ModelServicesCustomer> models;
    Activity activity;
    DataBaseHelper mDBHelper;
    private OnItemClickListener listener;
    boolean isListReserved;

    public AdapterListServices(Activity activity, Context context, List<ModelServicesCustomer> models, OnItemClickListener listener, boolean isListReserved) {
        this.context = context;
        this.activity = activity;
        this.listener = listener;
        this.models = models;
        layoutInflater = LayoutInflater.from(context);
        mDBHelper = new DataBaseHelper(context);
        this.isListReserved = isListReserved;
    }

    public interface OnItemClickListener {
        void onItemClick(ModelServicesCustomer model, ImageView item, ViewHolder holder, int position, boolean isMenuClicked);
    }

    @Override
    public int getItemViewType(int position) {
        ModelServicesCustomer serviceCustomer = models.get(position);
        int viewType = 0;
        if (isListReserved)
            viewType = 0;
        else
            viewType = 1;

        return viewType;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        int layout = R.layout.item_list_service;
        switch (viewType) {
            case 0:
                layout = R.layout.item_list_reserved;
                break;

            case 1:
                layout = R.layout.item_list_service;
                break;
        }
        return new AdapterListServices.ViewHolder(layoutInflater.inflate(layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        holder.txt_name_customer.setTypeface(G.ExtraBold);
        holder.txt_name_car.setTypeface(G.ExtraBold);
        if (!isListReserved) {
            holder.txt_km_next.setTypeface(G.Normal);
            holder.txt_km_now.setTypeface(G.Normal);
            if (models.get(position).getKm_now().toString().length() > 0) {
                holder.ly_km_now.setVisibility(View.VISIBLE);
                holder.txt_km_now.setText(G.getDecimalFormattedString(models.get(position).getKm_now().toString() + ""));
            } else {
                holder.ly_km_now.setVisibility(View.INVISIBLE);
            }
            if (models.get(position).getKm_next().toString().length() > 0) {
                holder.ly_km_next.setVisibility(View.VISIBLE);
                holder.txt_km_next.setText(G.getDecimalFormattedString(models.get(position).getKm_next().toString() + ""));
            } else {
                holder.ly_km_next.setVisibility(View.INVISIBLE);
            }
        }


        holder.txt_date_service.setTypeface(G.Normal);
        holder.txt_time_service.setTypeface(G.Normal);

        holder.txt_name_customer.setText(models.get(position).getFirst_name() + " " + models.get(position).getLast_name());
        holder.txt_name_car.setText(models.get(position).getName_car().toString());


        holder.txt_date_service.setText(models.get(position).getDate_services().toString());
        if (models.get(position).getTime_services() != null) {
            holder.ly_service_time.setVisibility(View.VISIBLE);
            holder.txt_time_service.setText(models.get(position).getTime_services().toString());
        } else {
            holder.ly_service_time.setVisibility(View.GONE);
        }


        holder.bind(context, models.get(position), holder, listener, isListReserved);
    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView txt_name_customer, txt_name_car, txt_km_now, txt_km_next, txt_date_service, txt_time_service, txt_phone_customer;
        ImageView icon_menu;
        ViewGroup root, ly_km_now, ly_km_next, ly_service_time;

        public void bind(Context context, final ModelServicesCustomer item, final ViewHolder holder, final OnItemClickListener listener, boolean isListReserved) {

            if (!isListReserved) {
                icon_menu.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        listener.onItemClick(item, icon_menu, holder, getAdapterPosition(), true);
                    }
                });
            }
            root.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(item, icon_menu, holder, getAdapterPosition(), false);
                }
            });

        }

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_name_customer = itemView.findViewById(R.id.txt_name_customer);
            txt_name_car = itemView.findViewById(R.id.txt_name_car);
            txt_km_now = itemView.findViewById(R.id.txt_km_now);
            txt_km_next = itemView.findViewById(R.id.txt_km_next);
            txt_date_service = itemView.findViewById(R.id.txt_date_service);
            txt_time_service = itemView.findViewById(R.id.txt_time_service);
            icon_menu = itemView.findViewById(R.id.icon_menu);
            root = itemView.findViewById(R.id.root);
            ly_km_now = itemView.findViewById(R.id.ly_km_now);
            ly_km_next = itemView.findViewById(R.id.ly_km_next);
            ly_service_time = itemView.findViewById(R.id.ly_service_time);
        }
    }

    public void swapList(List<ModelServicesCustomer> list) {
        this.models = list;
        notifyDataSetChanged();
    }

}
