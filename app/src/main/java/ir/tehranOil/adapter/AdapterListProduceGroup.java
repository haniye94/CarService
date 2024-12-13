package ir.tehranOil.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import ir.servicea.R;
import ir.tehranOil.app.G;
import ir.tehranOil.app.PreferenceUtil;
import ir.tehranOil.model.dbModel.ModelProduceGroup;

public class AdapterListProduceGroup extends RecyclerView.Adapter<AdapterListProduceGroup.ViewHolder> {
    Context context;
    LayoutInflater layoutInflater;
    List<ModelProduceGroup> models;
    PreferenceUtil preferenceUtil;
    public static HashMap save_khedmat;

    public AdapterListProduceGroup(Context context, List<ModelProduceGroup> models, AdapterListProduceGroup.OnItemClickListener listener) {
        this.context = context;
        this.models = models;
        this.listener = listener;
        layoutInflater = LayoutInflater.from(context);
        preferenceUtil = new PreferenceUtil(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(layoutInflater.inflate(R.layout.item_layout_produce_group, parent, false));
    }

    private AdapterListProduceGroup.OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(ModelProduceGroup model, CheckBox item, AdapterListProduceGroup.ViewHolder holder, int position);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        holder.txt_kala.setTypeface(G.ExtraBold);

        holder.txt_kala.setText(models.get(position).getOnvan().toString());
        holder.checkbox.setChecked(models.get(position).isCheck());
        if (models.get(position).isCheck()) {
            holder.ly_check_msg.setVisibility(View.GONE);
        } else {
            holder.ly_check_msg.setVisibility(View.GONE);
        }
//        List<CheckBox> items = new ArrayList<CheckBox>();
//
//        for (CheckBox item : items) {
//            if (item.isChecked()) {
//                preferenceUtil.cashType_service(item.getText().toString());
//                Toast.makeText(context, "" + item.getText().toString(), Toast.LENGTH_SHORT).show();
//            }
//        }
//        holder.checkbox.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                CheckBox checkBox = (CheckBox) view;
//                if (checkBox.isChecked()) {
//                    Toast.makeText(context, checkBox.isChecked()+"", Toast.LENGTH_SHORT).show();
//                    save_khedmat.put(models.get(position).getOnvan(),checkBox.isChecked());
//                    holder.ly_check_msg.setVisibility(View.VISIBLE);
//                } else holder.ly_check_msg.setVisibility(View.GONE);
//
//            }
//        });

        holder.checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (compoundButton.isPressed()) {

                    models.get(position).setCheck(b);
                    if (b) {
                        addProductGroup(models.get(position).getId(), models.get(position).getOnvan(), models.get(position).getKm_usage());
                        holder.ly_check_msg.setVisibility(View.GONE);
                    } else {
                        removeProductGroup(models.get(position).getId());
                        holder.ly_check_msg.setVisibility(View.GONE);
                    }

                    List<CheckBox> items2 = new ArrayList<CheckBox>();
                    for (CheckBox itemm : items2) {
                        if (itemm.isChecked()) {
                            preferenceUtil.cashType_service(itemm.getText().toString());
                            // Toast.makeText(context, "" + itemm.getText().toString(), Toast.LENGTH_SHORT).show();
                        }
                    }

                }
            }
        });

        holder.chbox_message.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                // if (b) {
                // context.startActivity(new Intent(context, SendMessageActivity.class).putExtra("firstName", "null"));
                // }
            }
        });
        holder.chbox_message.setChecked(models.get(position).isSend_msg());

        holder.bind(context, models.get(position), holder, listener);
    }

    public void addProductGroup(int id, String title, int km) {
        try {
            JSONArray array = new JSONArray(G.preference.getString("myProductGroups", "[]"));
            for (int i = 0; i < array.length(); i++) {
                JSONObject object = array.getJSONObject(i);
                if (object.getInt("id") == (id)) {
                    array.remove(i);
                }
            }
            JSONObject object = new JSONObject();
            object.put("id", id);
            object.put("km_usage", km);
            object.put("title", title);
            array.put(object);
            G.preference.edit().putString("myProductGroups", array.toString()).apply();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void removeProductGroup(int id) {
        try {
            JSONArray array = new JSONArray(G.preference.getString("myProductGroups", "[]"));
            for (int i = 0; i < array.length(); i++) {
                JSONObject object = array.getJSONObject(i);
                if (object.getInt("id") == (id)) {
                    array.remove(i);
                }
            }
            G.preference.edit().putString("myProductGroups", array.toString()).apply();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView txt_kala;
        CheckBox checkbox, chbox_message;
        LinearLayout ly_check_msg;

        public void bind(Context context, final ModelProduceGroup item, final AdapterListProduceGroup.ViewHolder holder, final AdapterListProduceGroup.OnItemClickListener listener) {

            checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    if (compoundButton.isPressed()) {
                        checkbox.setChecked(b);
                        listener.onItemClick(item, checkbox, holder, getAdapterPosition());
                    }
                }
            });
        }

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_kala = itemView.findViewById(R.id.txt_kala);
            checkbox = itemView.findViewById(R.id.checkbox);
            chbox_message = itemView.findViewById(R.id.chbox_message);
            ly_check_msg = itemView.findViewById(R.id.ly_check_msg);
        }
    }


}
