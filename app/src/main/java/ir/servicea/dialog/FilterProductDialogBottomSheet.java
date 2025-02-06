package ir.servicea.dialog;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import ir.servicea.R;
import ir.servicea.app.G;
import ir.servicea.app.PreferenceUtil;
import ir.servicea.model.ModelSpinner;
import ir.servicea.retrofit.Api;
import ir.servicea.retrofit.RetrofitClient;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @author haniye94 .
 * @since on 1/8/2025.
 */
public class FilterProductDialogBottomSheet extends BottomSheetDialogFragment {
    Context context;

    Spinner spinner_group, spinner_brand;
    ModelSpinner groupProduct, brandProduct;
    boolean change = false;

    private final List<ModelSpinner> listGroup = new ArrayList<>();
    private final List<ModelSpinner> listBrand = new ArrayList<>();
    List<String> spinnerGroup = new ArrayList<>();
    List<String> spinnerBrand = new ArrayList<>();


    Button btn_filter, btn_cancel;

    public interface BottomSheetListener {
        void onValueReceived(boolean change, ModelSpinner group, ModelSpinner brand);
    }

    private BottomSheetListener listener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            listener = (BottomSheetListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement BottomSheetListener");
        }
    }

    // Call this method to send data before dismissing
    private void sendValueToActivity(boolean change, ModelSpinner group, ModelSpinner brand) {
        if (listener != null) {
            listener.onValueReceived(change, group, brand);
        }
    }

    @Override
    public void onDismiss(@NonNull DialogInterface dialog) {
        super.onDismiss(dialog);
        // Call sendValueToActivity when the dialog is dismissed
        sendValueToActivity(change, groupProduct, brandProduct);
    }

    public FilterProductDialogBottomSheet(@NonNull Context context) {
        this.context = context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = getLayoutInflater().inflate(R.layout.brand_group_botom_sheet, null);
        listener = (BottomSheetListener) context;
        FindView(view);
        fetchProductsGroup();
        OnClick();

        return view;
    }

    private void FindView(View view) {
        spinner_group = view.findViewById(R.id.spinner_group);
        spinner_brand = view.findViewById(R.id.spinner_brand);
        btn_filter = view.findViewById(R.id.btn_filter);
        btn_cancel = view.findViewById(R.id.btn_cancel);

    }

    private void OnClick() {
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                change = false;
                dismiss();
            }
        });
        btn_filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                change = true;
                sendValueToActivity(change, groupProduct, brandProduct);
                dismiss();
            }
        });

    }

    @Override
    public void onStart() {
        super.onStart();
        if (getDialog() != null) {
            getDialog().getWindow().setBackgroundDrawableResource(android.R.color.transparent); // Set transparent for Border
        }
    }


    public void setSpinners() {
        spinnerGroup.clear();
        spinnerGroup.add("انتخاب کنید");
        for (int i = 0; i < listGroup.size(); i++) {
            spinnerGroup.add(listGroup.get(i).getName());
        }
        spinner_group.setAdapter(G.setFontToSpinner(spinnerGroup));

        spinner_group.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                groupProduct = new ModelSpinner();
                String selectedItem = parent.getItemAtPosition(position).toString();
                if (selectedItem.equals("انتخاب کنید")) {
                    groupProduct.setName("انتخاب کنید");
                    groupProduct.setId("0");
                } else {
                    for (int i = 0; i < listGroup.size(); i++) {
                        if (selectedItem.equals(listGroup.get(i).getName())) {
                            groupProduct.setId(listGroup.get(i).getId());
                            groupProduct.setName(listGroup.get(i).getName());
                        }
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinnerBrand.clear();
        spinnerBrand.add("انتخاب کنید");
        for (int i = 0; i < listBrand.size(); i++) {
            spinnerBrand.add(listBrand.get(i).getName());
        }
        spinner_brand.setAdapter(G.setFontToSpinner(spinnerBrand));
        spinner_brand.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                brandProduct = new ModelSpinner();
                String selectedItem = parent.getItemAtPosition(position).toString();
                if (selectedItem.equals("انتخاب کنید")) {
                    brandProduct.setName("انتخاب کنید");
                    brandProduct.setId("0");
                } else {
                    for (int i = 0; i < listBrand.size(); i++) {
                        if (selectedItem.equals(listBrand.get(i).getName())) {
                            brandProduct.setId(listBrand.get(i).getId());
                            brandProduct.setName(listBrand.get(i).getName());
                        }
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void fetchProductsGroup() {
        Api api = RetrofitClient.createService(Api.class, G.api_username, G.api_password);
        int service_center_id = Integer.parseInt(PreferenceUtil.getD_id());

        Call<ResponseBody> request = api.getProductsGroupAndBrand(service_center_id);
        request.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                String jsonString = G.getResult(response);
                parseJson(jsonString);
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                G.stop_loading();
                G.toast("مشکل در برقراری ارتباط با سرور");            }
        });

    }

    public void parseJson(String jsonResponse) {


        try {
            JSONObject jsonObject = new JSONObject(jsonResponse);

            // Parsing Product Groups
            JSONArray productGroupArray = jsonObject.getJSONArray("product_groups");
            for (int i = 0; i < productGroupArray.length(); i++) {
                ModelSpinner modelGroupSpinner = new ModelSpinner();

                JSONObject productGroupObject = productGroupArray.getJSONObject(i);
                modelGroupSpinner.setId(productGroupObject.getString("product_group_id"));
                modelGroupSpinner.setName(productGroupObject.getString("product_group_title"));
                listGroup.add(modelGroupSpinner);

            }


            // Parsing Brands
            JSONArray brandArray = jsonObject.getJSONArray("brands");
            for (int i = 0; i < brandArray.length(); i++) {
                ModelSpinner modelBrandSpinner = new ModelSpinner();
                JSONObject brandObject = brandArray.getJSONObject(i);
                modelBrandSpinner.setId(brandObject.getString("brand_id"));
                modelBrandSpinner.setName(brandObject.getString("brand_name"));
                listBrand.add(modelBrandSpinner);
            }
            setSpinners();

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}

