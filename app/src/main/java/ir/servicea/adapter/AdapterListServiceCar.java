package ir.servicea.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Point;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textfield.MaterialAutoCompleteTextView;
import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import ir.servicea.R;
import ir.servicea.activity.ProductGroupActivity;
import ir.servicea.app.DataBaseHelper;
import ir.servicea.app.G;
import ir.servicea.app.PreferenceUtil;
import ir.servicea.model.Product;
import ir.servicea.model.dbModel.ModelKhadamat;
import ir.servicea.model.dbModel.ModelSaveKhadamat;
import ir.servicea.retrofit.Api;
import ir.servicea.retrofit.RetrofitClient;
import kr.ry4nkim.objectspinner.ObjectSpinner;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;

public class AdapterListServiceCar extends RecyclerView.Adapter<AdapterListServiceCar.ViewHolder> {
    Context context;
    LayoutInflater layoutInflater;
    public static List<ModelKhadamat> models;
    AlertDialog alertDialogs_detect_type;
    DataBaseHelper mDBHelper;
    private SQLiteDatabase mDatabase;
    int idserver;
    private OnItemClickListener listener;
    List<ModelSaveKhadamat> saveKhadamats = new ArrayList<>();
    private ArrayAdapter<String> liveSearch;
    private MaterialAutoCompleteTextView edt_value, edt_title;
    private TextInputLayout edt_titleX;
    private ObjectSpinner edt_type;
    private List<Product> Products = new ArrayList<>();
    private int product_name_id = 1;
    private String product_name = "";
    public static ViewGroup baztaviz;
    public static TextView noet;

    public AdapterListServiceCar(Context context, List<ModelKhadamat> models, OnItemClickListener listener, int idserver) {
        this.context = context;
        this.models = models;
        this.listener = listener;
        this.idserver = idserver;
        mDBHelper = new DataBaseHelper(context);
        mDatabase = mDBHelper.getReadableDatabase();
        layoutInflater = LayoutInflater.from(context);
        mDBHelper = new DataBaseHelper(context);
        saveKhadamats = mDBHelper.getListsave_khadamat(mDatabase, idserver);

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(layoutInflater.inflate(R.layout.item_list_service_car, parent, false));
    }

    public interface OnItemClickListener {
        void onItemClickB(ModelKhadamat model, TextView itemT, TextView itemB, TextView center, TextView txt_detect_type, ViewHolder holder, int position);

        void onItemClickT(ModelKhadamat model, TextView itemT, TextView itemB, TextView center, TextView txt_detect_type, ViewHolder holder, int position);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        holder.txt_name_task.setTypeface(G.ExtraBold);
        holder.txt_detect_type.setTextColor(context.getResources().getColor(R.color.text_low_dark));
        holder.txt_name_task.setText(models.get(position).getTitle().toString());
        if (position == 0) {
            baztaviz = holder.baztavizx;
            noet = holder.txt_detect_type;
        }
        if (models.get(position).getType().length() > 1) {
            holder.txt_detect_type.setText(models.get(position).getType());
        } else {
            holder.txt_detect_type.setText("");
            holder.txt_detect_type.setHint("نوع");
        }
        if (models.get(position).getStatus() == 1) {
            holder.txt_detect_type.setVisibility(View.INVISIBLE);
        } else {
            holder.txt_detect_type.setVisibility(View.VISIBLE);
        }
        holder.txt_detect_type.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                product_groups(models.get(position).getId(), models.get(position));
                G.preference.edit().putInt("product_group_id", models.get(position).getId()).apply();
                DialogDetectType(context, holder, models.get(position));
            }
        });

        holder.txt_bazdid.setBackgroundResource(R.drawable.shap_btn_simple_right_off);
        holder.txt_taviz.setBackgroundResource(R.drawable.shap_btn_simple_left_off);
        holder.txt_detect_type.setVisibility(View.VISIBLE);
        holder.txt_detect_type.setText("");

        if (!saveKhadamats.isEmpty()) {
            for (int i = 0; i < saveKhadamats.size(); i++) {
                if (saveKhadamats.get(i).getTitle().equals(models.get(position).getTitle())) {

                    if (saveKhadamats.get(i).getStatus() == 1) {
                        holder.txt_bazdid.setBackgroundResource(R.drawable.shap_btn_simple_right);
                        holder.txt_detect_type.setVisibility(View.INVISIBLE);
//                        holder.txt_taviz.setBackgroundResource(R.drawable.shap_btn_simple_left_off);
                        models.get(position).setSelectB("false");
                        models.get(position).setSelectT("true");


                    } else if (saveKhadamats.get(i).getStatus() == 2) {
                        //if (mDBHelper.getListsave_khadamat(mDatabase, idserver).get(i).getTitle().equals(holder.txt_name_task)) {
                        holder.txt_taviz.setBackgroundResource(R.drawable.shap_btn_simple_left);
                        holder.txt_detect_type.setVisibility(View.VISIBLE);
                        String type = (saveKhadamats.get(i).getType() + "").replace("null", "");
                        String value = (saveKhadamats.get(i).getValue() + "").replace("null", "");

                        if (type.length() > 0) {

                            holder.txt_detect_type.setText(type);
                            holder.txt_detect_type.setTextColor(context.getResources().getColor(R.color.text_low_dark));
                            models.get(position).setType(type);
                            models.get(position).setValue(value);
                        } else {
                            holder.txt_detect_type.setText("");
                            models.get(position).setType("");
                            models.get(position).setValue("");
                        }
//                     holder.txt_bazdid.setBackgroundResource(R.drawable.shap_btn_simple_right_off);
                        models.get(position).setSelectB("true");
                        models.get(position).setSelectT("false");
                        // }

                    }
                }
            }
        }
        if (models.get(position).getSelectB().equals("true") && models.get(position).getSelectT().equals("false")) {
            holder.txt_taviz.setBackgroundResource(R.drawable.shap_btn_simple_left);
            holder.txt_detect_type.setVisibility(View.VISIBLE);
            if (models.get(position).getType().length() > 0) {
                String type = (models.get(position).getType() + "").replace("null", "");
                String value = (models.get(position).getValue() + "").replace("null", "");
                models.get(position).setType(type);
                models.get(position).setValue(value);
                holder.txt_detect_type.setText(type);
                holder.txt_detect_type.setTextColor(context.getResources().getColor(R.color.text_low_dark));
            }

        }
        if (models.get(position).getSelectB().equals("false") && models.get(position).getSelectT().equals("true")) {
            models.get(position).setType("");
            models.get(position).setValue("");
            holder.txt_bazdid.setBackgroundResource(R.drawable.shap_btn_simple_right);
            holder.txt_detect_type.setVisibility(View.INVISIBLE);
        }

        holder.add.setVisibility(View.GONE);
        holder.desc.setVisibility(View.GONE);
        //Compare size and add button at buttom of view,ie arraylist size
        if (position == models.size() - 1) {
            holder.add.setVisibility(View.VISIBLE);
        }
        if (position == 0) {
            holder.desc.setVisibility(View.VISIBLE);
        }
        holder.add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                G.preference.edit().putBoolean("changeProductGroup", true).apply();
                context.startActivity(new Intent(context, ProductGroupActivity.class));
            }
        });
        holder.bind(context, models.get(position), holder, listener);
    }


    @Override
    public int getItemCount() {
        return models.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView txt_name_task, txt_detect_type, txt_bazdid, txt_taviz, txt_center;
        ViewGroup baztavizx;
        AppCompatButton add, desc;

        public void bind(Context context, final ModelKhadamat item, final ViewHolder holder, final OnItemClickListener listener) {
            txt_bazdid.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClickB(item, txt_taviz, txt_bazdid, txt_center, txt_detect_type, holder, getAdapterPosition());
                }
            });

            txt_taviz.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClickT(item, txt_taviz, txt_bazdid, txt_center, txt_detect_type, holder, getAdapterPosition());
                }
            });


        }

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_name_task = itemView.findViewById(R.id.txt_name_task);
            txt_detect_type = itemView.findViewById(R.id.txt_detect_type);
            txt_bazdid = itemView.findViewById(R.id.txt_bazdid);
            txt_taviz = itemView.findViewById(R.id.txt_taviz);
            txt_center = itemView.findViewById(R.id.txt_center);
            baztavizx = itemView.findViewById(R.id.baztaviz);
            add = itemView.findViewById(R.id.add);
            desc = itemView.findViewById(R.id.desc);


        }
    }

    void DialogDetectType(Context context, ViewHolder holder, ModelKhadamat modelKhadamat) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.layout_item_dialog_detect_type, null);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
        alertDialogBuilder.setView(view);
        alertDialogBuilder.setCancelable(true);

        alertDialogs_detect_type = alertDialogBuilder.create();

        alertDialogs_detect_type.getWindow().setGravity(Gravity.CENTER_VERTICAL);
        alertDialogs_detect_type.setCancelable(false);
        WindowManager.LayoutParams layoutParams = alertDialogs_detect_type.getWindow().getAttributes();
        alertDialogs_detect_type.getWindow().setAttributes(layoutParams);
        alertDialogs_detect_type.getWindow().setBackgroundDrawable(context.getResources().getDrawable(R.drawable.shap_simple_rec));
        alertDialogs_detect_type.show();
        ImageView img_close;
        Button btn_save;
        ;
        img_close = view.findViewById(R.id.img_close);
        btn_save = view.findViewById(R.id.btn_save);
        edt_type = view.findViewById(R.id.edt_type);
        edt_value = view.findViewById(R.id.edt_value);
        edt_title = view.findViewById(R.id.edt_title);
        edt_titleX = view.findViewById(R.id.edt_titleX);
        if (modelKhadamat.getType_id() >= 0 && modelKhadamat.getValue().length() > 0) {
            edt_value.setText(modelKhadamat.getValue());
        }
        if (modelKhadamat.getType_id() >= 0 && modelKhadamat.getValue().length() > 0) {
            edt_value.setText(modelKhadamat.getValue());
        }

        img_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialogs_detect_type.dismiss();
            }
        });
        edt_type.setOnItemSelectedListener((viewx, position, item) -> {
            edt_titleX.setVisibility(View.GONE);
            product_name_id = Products.get(position).getId();
            product_name = Products.get(position).getName();

        });
        edt_title.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (edt_title.getText().toString().length() > 1) {
                    edt_titleX.setHelperTextEnabled(false);
                    edt_type.setVisibility(View.GONE);
                    product_name_id = 0;
                    product_name = edt_title.getText().toString();
                } else {
                    edt_titleX.setHelperTextEnabled(true);
                    edt_titleX.setHelperText("اگر در لیست بالا محصول یافت نشد در این قسمت بنویسید");
                    edt_type.setVisibility(View.VISIBLE);
                    product_name_id = 0;
                    product_name = "";
                }
            }
        });
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String value = edt_value.getText().toString();

                if (!TextUtils.isEmpty(product_name)) {
                    holder.txt_detect_type.setText(product_name);
                    holder.txt_detect_type.setTextColor(context.getResources().getColor(R.color.text_low_dark));
                    mDBHelper.updateKhadamatType(modelKhadamat.getTitle() + "", product_name, value);
                    modelKhadamat.setType(product_name);
                    modelKhadamat.setType_id(product_name_id);
                    modelKhadamat.setValue(value);
                    alertDialogs_detect_type.dismiss();
                } else {
                    modelKhadamat.setType("");
                    modelKhadamat.setType_id(0);
                    modelKhadamat.setValue("");
                    G.toast("نوع خدمت را وارد کنید");
                }
            }
        });

        Display display = ((Activity) context).getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;

        width = (int) ((width) * ((double) 9 / 10));
        alertDialogs_detect_type.getWindow().setLayout(width, LinearLayout.LayoutParams.WRAP_CONTENT);

    }

    public void product_groups(int product_group_id, ModelKhadamat modelKhadamat) {

//        G.loading(G.Activity);
        String d_id = PreferenceUtil.getD_id();
        Api api = RetrofitClient.createService(Api.class, G.api_username, G.api_password);
        Call<ResponseBody> request = api.products_names("product_group_id,eq," + product_group_id);
        request.enqueue(new retrofit2.Callback<ResponseBody>() {
            @Override
            public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {
                assert response.body() != null;
                int positionSelected = 0;
                String title_selected = "";
                try {
                    String result = response.body().string();
                    JSONObject object = G.StringtoJSONObject(result);
                    JSONArray records = object.getJSONArray("records");
                    if (records.length() > 0) {
                        Products = new ArrayList<>();

                        for (int i = 0; i < records.length(); i++) {
                            JSONObject obj = records.getJSONObject(i);
                            Product product = new Product();
                            int id = obj.getInt("id");
                            String title = obj.getString("name");
                            product.setId(id);
                            product.setName(title);
                            Products.add(product);

                            if (id == modelKhadamat.getType_id()) {
                                title_selected = title;
                                if (title_selected.length() > 2 && !title_selected.contains("نامشخص")) {
                                    positionSelected = i;
                                }
                            }
                        }
                        edt_type.setItemList(Products);

                    }
                    if (modelKhadamat.getType().length() > 0) {
                        if (title_selected.length() <= 2 || title_selected.contains("نامشخص")) {
                            edt_title.setText(modelKhadamat.getType());
                            edt_titleX.setHelperTextEnabled(false);
                            edt_type.setVisibility(View.GONE);
                            product_name_id = modelKhadamat.getType_id();
                            product_name = edt_title.getText().toString();
                        } else {
                            edt_type.setVisibility(View.VISIBLE);
                            edt_type.setSelection(positionSelected);
                            product_name_id = modelKhadamat.getType_id();
                            product_name = title_selected;
                        }


                    }
                } catch (JSONException | IOException e) {
                    G.toast("مشکل در تجزیه اطلاعات");
                    e.printStackTrace();
                }
                G.stop_loading();
            }

            @Override
            public void onFailure(@NonNull Call<ResponseBody> call, @NonNull Throwable t) {
                G.stop_loading();
                G.toast("مشکل در برقراری ارتباط");
            }
        });


    }

}
