package ir.tehranOil.adapter;

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

import ir.tehranOil.app.DataBaseHelper;
import ir.tehranOil.app.G;
import java.util.List;

import ir.servicea.R;

import ir.tehranOil.app.PLakUtils;
import ir.tehranOil.model.ModelServicesCustomer;

public class AdapterListServices extends RecyclerView.Adapter<AdapterListServices.ViewHolder> {
    Context context;
    LayoutInflater layoutInflater;
    List<ModelServicesCustomer> models;
    Activity activity;
    DataBaseHelper mDBHelper;
    private OnItemClickListener listener;

    public AdapterListServices(Activity activity, Context context, List<ModelServicesCustomer> models, OnItemClickListener listener) {
        this.context = context;
        this.activity = activity;
        this.listener = listener;
        this.models = models;
        layoutInflater = LayoutInflater.from(context);
        mDBHelper = new DataBaseHelper(context);
    }

    public interface OnItemClickListener {
        void onItemClick(ModelServicesCustomer model, ImageView item, ViewHolder holder, int position, boolean isMenuClicked);
    }

    @Override
    public int getItemViewType(int position) {
        ModelServicesCustomer serviceCustomer = models.get(position);
        int viewType = 0;
        switch (serviceCustomer.getPlak_type()) {
            case PLAK_GENERAL:
                viewType = 1;
                break;

            case PLAK_TAXI:
                viewType = 2;
                break;

            case PLAK_EDARI:
                viewType = 3;
                break;

            case PLAK_ENTEZAMI:
                viewType = 4;
                break;

            case PLAK_MAOLOIN:
                viewType = 5;
                break;

            case PLAK_AZAD_NEW:
                viewType = 6;
                break;

            case PLAK_AZAD_OLD:
                viewType = 7;
                break;
        }
        return viewType;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        int layout = R.layout.item_list_service;
        switch (viewType) {
            case 1:
                layout = R.layout.item_list_service;
                break;

            case 2:
                layout = R.layout.item_list_service_taxi;
                break;

            case 3:
                layout = R.layout.item_list_service_edari;
                break;

            case 4:
                layout = R.layout.item_list_service_entezami;
                break;

            case 5:
                layout = R.layout.item_list_service_malolin;
                break;

            case 6:
                layout = R.layout.item_list_service_azad_new;
                break;

            case 7:
                layout = R.layout.item_list_service_azad_old;
                break;


        }
        return new AdapterListServices.ViewHolder(layoutInflater.inflate(layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        holder.txt_name_customer.setTypeface(G.ExtraBold);
        holder.txt_name_car.setTypeface(G.ExtraBold);
        holder.txt_km_next.setTypeface(G.Normal);
        holder.txt_km_now.setTypeface(G.Normal);

        holder.txt_date_service.setTypeface(G.Normal);
        holder.txt_time_service.setTypeface(G.Normal);

        holder.txt_name_customer.setText(models.get(position).getFirst_name() + " " + models.get(position).getLast_name());
        holder.txt_name_car.setText(models.get(position).getName_car().toString());
        if (models.get(position).getKm_now().toString().length() > 0) {
            holder.ly_km_now.setVisibility(View.VISIBLE);
            holder.txt_km_now.setText(G.getDecimalFormattedString(models.get(position).getKm_now().toString() + ""));
        } else{
            holder.ly_km_now.setVisibility(View.INVISIBLE);
        }
        if (models.get(position).getKm_next().toString().length() > 0) {
            holder.ly_km_next.setVisibility(View.VISIBLE);
            holder.txt_km_next.setText(G.getDecimalFormattedString(models.get(position).getKm_next().toString() + ""));
        }else{
            holder.ly_km_next.setVisibility(View.INVISIBLE);
        }

        holder.txt_date_service.setText(models.get(position).getDate_services().toString());
        if(models.get(position).getTime_services() != null){
            holder.ly_service_time.setVisibility(View.VISIBLE);
            holder.txt_time_service.setText(models.get(position).getTime_services().toString());
        }else{
            holder.ly_service_time.setVisibility(View.GONE);
        }

        String plak =  (models.get(position).getPlak()+"").replace(" ","").replace("null","");
        if (plak.length() > 3) {
            setPlakBasedViewType(plak, holder);
        } else {
            holder.plaks.setVisibility(View.GONE);
        }
        holder.bind(context, models.get(position), holder, listener);
    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView txt_name_customer, txt_name_car, txt_km_now, txt_km_next, txt_date_service,txt_time_service, txt_phone_customer;
        ImageView icon_menu;
        TextView txt_plak_customer1,txt_plak_customer2,txt_plak_customer3,txt_plak_customer4;
        ViewGroup plaks,root, ly_km_now, ly_km_next, ly_service_time;
        public void bind(Context context, final ModelServicesCustomer item, final ViewHolder holder, final OnItemClickListener listener) {
            icon_menu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(item, icon_menu, holder, getAdapterPosition(), true);
                }
            });
            root.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                 /*   Intent intent = new Intent(context, InformationServiceCarActivity.class);
                    intent.putExtra("position", getAdapterPosition());
                    intent.putExtra("idCustomer", item.getId_customer() + "");
                    intent.putExtra("id_car", item.getCar_id() + "");
                    intent.putExtra("idservice", item.getId() + "");
                    intent.putExtra("id_service", item.getId() + "");
                    intent.putExtra("firstName", item.getFirst_name());
                    intent.putExtra("lastName", item.getLast_name());
                    intent.putExtra("phone", item.getPhone());
                    intent.putExtra("plak", item.getPlak());
                    intent.putExtra("km_now", item.getKm_now());
                    intent.putExtra("nameCar", item.getName_car());
                    intent.putExtra("km_next", item.getKm_next());
                    intent.putExtra("avg_function", item.getAvg_function());
                    intent.putExtra("description", item.getDescription());
                    intent.putExtra("date_service", item.getDate_services());
                    intent.putExtra("services_price", item.getAll_services_price());
                    intent.putExtra("detail_service", item.getDetail_service());
                    intent.putExtra("car_name_id", item.getCar_name_id());
                    intent.putExtra("car_tip_id", item.getCar_tip_id());
                    intent.putExtra("car_model_id", item.getCar_model_id());
                    intent.putExtra("fuel_type_id", item.getFuel_type_id());
                    intent.putExtra("center_id", item.getCenter_id()+"");
                    intent.putExtra("center_name", item.getCenter_name()+"");

                    context.startActivity(intent);*/
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
            plaks = itemView.findViewById(R.id.plaks);
            root = itemView.findViewById(R.id.root);
            ly_km_now = itemView.findViewById(R.id.ly_km_now);
            ly_km_next = itemView.findViewById(R.id.ly_km_next);
            ly_service_time = itemView.findViewById(R.id.ly_service_time);
            txt_phone_customer = itemView.findViewById(R.id.txt_phone_customer);
            txt_plak_customer1 = itemView.findViewById(R.id.txt_plak_customer1);
            txt_plak_customer2 = itemView.findViewById(R.id.txt_plak_customer2);
            txt_plak_customer3 = itemView.findViewById(R.id.txt_plak_customer3);
            txt_plak_customer4 = itemView.findViewById(R.id.txt_plak_customer4);
        }
    }

    public void swapList(List<ModelServicesCustomer> list) {
        this.models = list;
        notifyDataSetChanged();
    }

    private void setPlakBasedViewType(String plak, ViewHolder holder) {
        switch (holder.getItemViewType()) {
            case 1:
            case 2:
            case 3:
            case 4: {
                holder.plaks.setVisibility(View.VISIBLE);
                String c1 = plak.substring(0, 2);
                String c2 = plak.substring(2, plak.length() - 3);
                String c3 = plak.substring(plak.length() - 3, plak.length() - 1);
                String c4 = plak.substring(plak.length() - 1);
                holder.txt_plak_customer1.setText(c1);
                holder.txt_plak_customer2.setText(c4);
                holder.txt_plak_customer3.setText(c2);
                holder.txt_plak_customer4.setText(c3);
                break;
            }
            case 5:
            {
                holder.plaks.setVisibility(View.VISIBLE);
                String c1 = plak.substring(0, 2);
                String c2 = plak.substring(2, plak.length() - 3);
                String c3 = plak.substring(plak.length() - 3, plak.length() - 1);
                holder.txt_plak_customer1.setText(c1);
                holder.txt_plak_customer2.setVisibility(View.GONE);
                holder.txt_plak_customer3.setText(c2);
                holder.txt_plak_customer4.setText(c3);
                break;
            }
            case 6: {
                holder.plaks.setVisibility(View.VISIBLE);
                String c1 = plak.substring(0, 6);
                String c4 = plak.substring(6, plak.length());
                holder.txt_plak_customer1.setText(c1);
                holder.txt_plak_customer2.setText(c4);
                holder.txt_plak_customer3.setText(PLakUtils.convertPersianToEnglish(c1));
                holder.txt_plak_customer4.setText(PLakUtils.convertPersianToEnglish(c4));
                break;
            }
            case 7: {
                holder.plaks.setVisibility(View.VISIBLE);
                String c1 = plak.substring(0, 6);
                String c4 = plak.substring(6, plak.length());
                holder.txt_plak_customer1.setText(c1);
                holder.txt_plak_customer2.setText(PLakUtils.convertPersianToEnglish(c1));
                holder.txt_plak_customer3.setText(c4);
                holder.txt_plak_customer4.setVisibility(View.GONE);
                break;
            }
        }
       /* holder.txt_plak_customer1.setTypeface(G.ExtraBold);
        holder.txt_plak_customer2.setTypeface(G.ExtraBold);
        holder.txt_plak_customer3.setTypeface(G.ExtraBold);
        holder.txt_plak_customer4.setTypeface(G.ExtraBold);*/
    }
}
