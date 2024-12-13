package ir.tehranOil.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ir.tehranOil.app.Constants;
import ir.tehranOil.app.DataBaseHelper;
import ir.tehranOil.app.G;
import ir.servicea.R;
import ir.tehranOil.activity.InformationCustomersActivity;
import ir.tehranOil.app.PLakUtils;
import ir.tehranOil.model.dbModel.ModelCustomer;

public class AdapterListCustomer extends RecyclerView.Adapter<AdapterListCustomer.ViewHolder> {
    Context context;
    LayoutInflater layoutInflater;
    List<ModelCustomer> models;
    DataBaseHelper mDBHelper;
    private SQLiteDatabase mDatabase;
    private OnItemClickListener listener;

    public AdapterListCustomer(Context context, List<ModelCustomer> models, OnItemClickListener listener) {
        this.context = context;
        this.models = models;
        layoutInflater = LayoutInflater.from(context);
        mDBHelper = new DataBaseHelper(context);
        mDatabase = mDBHelper.getReadableDatabase();
        this.listener = listener;
    }

    public interface OnItemClickListener {
        void onItemClick(ModelCustomer model, ImageView item, ViewHolder holder, int position);
    }

    @Override
    public int getItemViewType(int position) {
        ModelCustomer customer = models.get(position);
        int viewType = 0;
        switch (customer.getPlak_type()) {
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
        int layout = R.layout.item_list_customer;
        switch (viewType) {
            case 1:
                layout = R.layout.item_list_customer;
                break;

            case 2:
                layout = R.layout.item_list_customer_taxi;
                break;

            case 3:
                layout = R.layout.item_list_customer_edari;
                break;

            case 4:
                layout = R.layout.item_list_customer_entezami;
                break;

            case 5:
                layout = R.layout.item_list_customer_malolin;
                break;

            case 6:
                layout = R.layout.item_list_customer_azad_new;
                break;

            case 7:
                layout = R.layout.item_list_customer_azad_old;
                break;

        }
        return new ViewHolder(layoutInflater.inflate(layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        ModelCustomer customer = models.get(position);
        String plak = (customer.getPlak() + "").replace(" ", "").replace("null", "");
//        String plak = "12345666کیش";
        if (plak.length() > 3) {
            setPlakBasedViewType(plak, holder);
        } else {
            holder.plaks.setVisibility(View.GONE);
        }
        holder.txt_name_customer.setText(customer.getFirst_name().toString() + " " + customer.getLast_name().toString());
        holder.txt_name_car.setText(customer.getName_car());
        holder.txt_phone_customer.setText(customer.getPhone());
        holder.bind(context, customer, holder, listener);
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
    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView txt_name_customer, txt_name_car, txt_phone_customer;
        ImageView icon_menu;
        TextView txt_plak_customer1, txt_plak_customer2, txt_plak_customer3, txt_plak_customer4;
        ViewGroup plaks, root;

        public void bind(Context context, final ModelCustomer item, final ViewHolder holder, final OnItemClickListener listener) {
            icon_menu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(item, icon_menu, holder, getAdapterPosition());
                }
            });
            root.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, InformationCustomersActivity.class);
                    intent.putExtra("idCustomer", item.getId() + "");
                    intent.putExtra("id_car", item.getCar_id() + "");
                    intent.putExtra("firstName", item.getFirst_name());
                    intent.putExtra("lastName", item.getLast_name());
                    intent.putExtra("phone", item.getPhone());
                    intent.putExtra("nameCar", item.getName_car());
                    intent.putExtra("plak", item.getPlak());
                    intent.putExtra(Constants.CAR_PLATE_TYPE, item.getPlak_type());
                    intent.putExtra("gender", item.getGender());
                    intent.putExtra("date_birthday", item.getDate_birthday());
                    intent.putExtra("type_fule", item.getType_fuel());
                    intent.putExtra("date_save", item.getDate_save_customer());
                    intent.putExtra("type_car", item.getType_car());
                    intent.putExtra("car_name_id", item.getCar_name_id());
                    intent.putExtra("car_tip_id", item.getCar_tip_id());
                    intent.putExtra("car_model_id", item.getCar_model_id());
                    intent.putExtra("fuel_type_id", item.getFuel_type_id());
                    context.startActivity(intent);
                }
            });
        }

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_name_customer = itemView.findViewById(R.id.txt_name_customer);
            txt_name_car = itemView.findViewById(R.id.txt_name_car);
            plaks = itemView.findViewById(R.id.plaks);
            txt_phone_customer = itemView.findViewById(R.id.txt_phone_customer);
            txt_plak_customer1 = itemView.findViewById(R.id.txt_plak_customer1);
            txt_plak_customer2 = itemView.findViewById(R.id.txt_plak_customer2);
            txt_plak_customer3 = itemView.findViewById(R.id.txt_plak_customer3);
            txt_plak_customer4 = itemView.findViewById(R.id.txt_plak_customer4);
            icon_menu = itemView.findViewById(R.id.icon_menu);
            root = itemView.findViewById(R.id.root);
            txt_name_customer.setTypeface(G.ExtraBold);
            txt_name_car.setTypeface(G.ExtraBold);
            txt_phone_customer.setTypeface(G.Normal);

        }
    }
}
