package ir.servicea.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
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
import ir.servicea.app.G;
import ir.servicea.app.PreferenceUtil;
import ir.servicea.model.dbModel.ModelProduceGroup;

public class AdapterListProduceGroup extends RecyclerView.Adapter<AdapterListProduceGroup.ViewHolder> {
    Context context;
    LayoutInflater layoutInflater;
    List<ModelProduceGroup> models;
    PreferenceUtil preferenceUtil;
    static String newvage = "";
    public static HashMap save_khedmat;
    static boolean edit_mode = true;//1 for enable and 2 fro confirm edit
    private Handler handler = new Handler();
    private Runnable runnable;

    public AdapterListProduceGroup(Context context, List<ModelProduceGroup> models, OnItemClickListener listener) {
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

    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(ModelProduceGroup model, CheckBox item, ViewHolder holder, int position);

        void onWageChange(ModelProduceGroup model, String changeWage, int position);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        holder.txt_kala.setTypeface(G.ExtraBold);
        holder.txt_kala.setText(models.get(position).getTitle());
        holder.edt_vage.setText(models.get(position).getChange_wage());
        holder.checkbox.setChecked(models.get(position).isExist());
        if (models.get(position).isExist()) {
            holder.ly_check_msg.setVisibility(View.GONE);
        } else {
            holder.ly_check_msg.setVisibility(View.GONE);
        }

        holder.edt_vage.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // Before text changed
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {


            }

            @Override

            public void afterTextChanged(Editable s) {
                newvage = s.toString();


            }
        });


        holder.checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (compoundButton.isPressed()) {

                    models.get(position).setExist(b);
                    if (b) {
                        addProductGroup(models.get(position).getProductGroupId(), models.get(position).getTitle());
                        holder.ly_check_msg.setVisibility(View.GONE);
                    } else {
                        removeProductGroup(models.get(position).getProductGroupId());
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

        holder.bind(context, models.get(position), holder, listener);
    }

    public void addProductGroup(String product_group_id, String title) {
        try {
            JSONArray array = new JSONArray(G.preference.getString("myProductGroups", "[]"));
            for (int i = 0; i < array.length(); i++) {
                JSONObject object = array.getJSONObject(i);
                if (object.getString("product_group_id") == (product_group_id)) {
                    array.remove(i);
                }
            }
            JSONObject object = new JSONObject();
            object.put("product_group_id", product_group_id);
            object.put("title", title);
            array.put(object);
            G.preference.edit().putString("myProductGroups", array.toString()).apply();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void removeProductGroup(String product_group_id) {
        try {
            JSONArray array = new JSONArray(G.preference.getString("myProductGroups", "[]"));
            for (int i = 0; i < array.length(); i++) {
                JSONObject object = array.getJSONObject(i);
                if (object.getString("product_group_id").equals(product_group_id)) {
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
        ImageView iv_edit;
        EditText edt_vage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_kala = itemView.findViewById(R.id.txt_title);
            checkbox = itemView.findViewById(R.id.checkbox);
            chbox_message = itemView.findViewById(R.id.chbox_message);
            ly_check_msg = itemView.findViewById(R.id.ly_check_msg);
            iv_edit = itemView.findViewById(R.id.iv_edit_wage);
            edt_vage = itemView.findViewById(R.id.edt_wage);
        }

        public void bind(Context context, final ModelProduceGroup item, final ViewHolder holder, final OnItemClickListener listener) {

            checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    if (compoundButton.isPressed()) {
                        checkbox.setChecked(b);
                        listener.onItemClick(item, checkbox, holder, getAdapterPosition());
                    }
                }
            });
            iv_edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (edit_mode) {
                        if (item.isExist()) {
                            edt_vage.setEnabled(true);
                            G.toast("برای تغییر اجرت روی تیک کلیک کنید");
                            iv_edit.setImageResource(R.drawable.ic_confirm);
                            edit_mode = false;
                        } else {
                            G.toast("ابتدا گروه کالا را انتخاب کنید");
                        }
                    } else {
                        newvage = edt_vage.getText().toString();
                        iv_edit.setImageResource(R.drawable.ic_edit);
                        G.toast("اجرت با موفقیت تغییر داده شد.");
                        edt_vage.setEnabled(false);
                        edit_mode = true;
                        listener.onWageChange(item, newvage, getAdapterPosition());

                    }
                }
            });
        }

    }

}
