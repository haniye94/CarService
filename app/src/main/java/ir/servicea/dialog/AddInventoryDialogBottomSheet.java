package ir.servicea.dialog;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import ir.servicea.R;
import ir.servicea.app.G;
import ir.servicea.app.PreferenceUtil;
import ir.servicea.model.ModelAddedProductCenter;
import ir.servicea.model.ReserveProduct;
import ir.servicea.retrofit.Api;
import ir.servicea.retrofit.RetrofitClient;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;

/**
 * @author haniye94 .
 * @since on 12/30/2024.
 */
public class AddInventoryDialogBottomSheet extends BottomSheetDialogFragment {
    ReserveProduct reserveProduct;
    Context context;

    Spinner spinner_packaging, spinner_amount;

    boolean editMode = false;

    private final List<String> listPackaging = new ArrayList<>();
    private final List<String> listAmount = new ArrayList<>();
    ModelAddedProductCenter modelAddProduct = new ModelAddedProductCenter();
    TextView product_title, txt_customer_price;
    EditText edt_inventory, edt_price, edt_discount;
    Button btn_add, btn_cancel;
    ImageView img_product, img_edit;
    String imgUrl = "";
    LinearLayout ll_btn;

    public AddInventoryDialogBottomSheet(@NonNull Context context, ReserveProduct reserveProduct) {
        this.context = context;
        this.reserveProduct = reserveProduct;
    }

    public AddInventoryDialogBottomSheet(@NonNull Context context, ModelAddedProductCenter modelAddProduct) {
        this.context = context;
        this.modelAddProduct = modelAddProduct;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = getLayoutInflater().inflate(R.layout.add_inventory_botom_sheet_old, null);
        FindView(view);
        getSpinners();
        OnClick();

        return view;
    }

    private void FindView(View view) {
        spinner_packaging = view.findViewById(R.id.spinner_packaging);
        spinner_amount = view.findViewById(R.id.spinner_amount);
        product_title = view.findViewById(R.id.product_title);
        img_product = view.findViewById(R.id.img_product);
        img_edit = view.findViewById(R.id.img_edit);
        ll_btn = view.findViewById(R.id.ll_btn);


        edt_inventory = view.findViewById(R.id.edt_inventory);
        edt_price = view.findViewById(R.id.edt_real_price);
        edt_discount = view.findViewById(R.id.edt_amount_discount);
        btn_add = view.findViewById(R.id.btn_add);
        btn_cancel = view.findViewById(R.id.btn_cancel);
        txt_customer_price = view.findViewById(R.id.txt_customer_price);

        edt_inventory.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                edt_price.requestFocus();
                return true;
            }
            return false;
        });
        edt_price.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                edt_discount.requestFocus();
                return true;
            }
            return false;
        });

        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                try {
                    int value = Integer.parseInt(s.toString());
                    if (value < 0 || value > 101) {
                        edt_discount.setError("درصد تخفیف را بین 0 تا 99 وارد کنید");
                    }
                } catch (NumberFormatException e) {
                    edt_discount.setError("Invalid input");
                }
                String discount = edt_discount.getText().toString().trim();
                String price = edt_price.getText().toString().trim();
                if (!discount.isEmpty()) {
                    if (!price.isEmpty()) updateTextView();
                    else txt_customer_price.setText("0");

                } else {
                    txt_customer_price.setText(edt_price.getText().toString());
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        };

        if (reserveProduct != null) {
            product_title.setText(reserveProduct.getProduct_name());
            imgUrl = G.PreImagesURL + "product_images/" + reserveProduct.getImageUrl();
        } else {
            showEditMode();
            editMode = true;

        }
        Picasso.get().load(imgUrl).into(img_product);
        edt_discount.addTextChangedListener(textWatcher);
        edt_price.addTextChangedListener(textWatcher);

    }

    private void showEditMode() {
        img_edit.setVisibility(View.VISIBLE);
        ll_btn.setVisibility(View.GONE);
        product_title.setText(modelAddProduct.getName());
        imgUrl = G.PreImagesURL + "product_images/" + modelAddProduct.getImageUrl();
        edt_inventory.setText(String.valueOf(modelAddProduct.getInventory()));
        edt_price.setText(String.valueOf(modelAddProduct.getReal_price()));
        edt_discount.setText(String.valueOf(modelAddProduct.getAmount_discount()));
        txt_customer_price.setText(String.valueOf(modelAddProduct.getCustomer_price()));
        edt_inventory.setEnabled(false);
        edt_price.setEnabled(false);
        edt_discount.setEnabled(false);
        txt_customer_price.setEnabled(false);
        spinner_packaging.setEnabled(false);
        spinner_amount.setEnabled(false);
    }

    private void enableEditMode() {
        img_edit.setVisibility(View.GONE);
        ll_btn.setVisibility(View.VISIBLE);
        edt_inventory.setEnabled(true);
        edt_price.setEnabled(true);
        edt_discount.setEnabled(true);
        txt_customer_price.setEnabled(true);
        spinner_packaging.setEnabled(true);
        spinner_amount.setEnabled(true);
    }

    private void updateTextView() {

        int realPrice = Integer.parseInt(edt_price.getText().toString());
        int discount = Integer.parseInt(edt_discount.getText().toString());
        int finalPrice = realPrice - (discount * realPrice / 100);
        txt_customer_price.setText(String.valueOf(finalPrice));
    }

    private void OnClick() {
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Dismiss the dialog when Cancel is clicked
                dismiss();
            }
        });
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String discount = edt_discount.getText().toString().trim(); // Trim whitespace
                String inventory = edt_inventory.getText().toString().trim(); // Trim whitespace
                String price = edt_price.getText().toString().trim(); // Trim whitespace

                if (TextUtils.isEmpty(inventory)) {
                    edt_discount.setError("درصد تخفیف رابین 0 تا 99 وارد کنید");
                } else if (TextUtils.isEmpty(inventory)) {
                    edt_inventory.setError("موجودی انبار را وارد کنید");
                } else if (TextUtils.isEmpty(price)) {
                    edt_price.setError("قیمت محصول را وارد کنید");
                } else {
                    modelAddProduct.setInventory(Integer.parseInt(edt_inventory.getText().toString()));
                    modelAddProduct.setReal_price(Integer.parseInt(edt_price.getText().toString()));
                    modelAddProduct.setAmount_discount(Integer.parseInt(edt_discount.getText().toString()));
                    modelAddProduct.setCustomer_price(Integer.parseInt(txt_customer_price.getText().toString()));
                    addServiceCenterProductInventory();
                }
            }
        });

        img_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                enableEditMode();
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

    public void addServiceCenterProductInventory() {
        G.loading(context);

        JSONObject object = new JSONObject();
        try {
            if (editMode) object.put("product_name_id", modelAddProduct.getProduct_name_id());
            else object.put("product_name_id", reserveProduct.getProduct_id());
            object.put("id", modelAddProduct.getId());
            object.put("amount", modelAddProduct.getAmount());
            object.put("packaging", modelAddProduct.getPackaging());
            object.put("inventory", modelAddProduct.getInventory());
            object.put("customer_price", modelAddProduct.getCustomer_price());
            object.put("real_price", modelAddProduct.getReal_price());
            object.put("amount_discount", modelAddProduct.getAmount_discount());
            object.put("status", 1);
            object.put("service_center_id", Integer.parseInt(PreferenceUtil.getD_id()));
            object.put("product_group_id", modelAddProduct.getProduct_group_id());

        } catch (JSONException e) {
            e.printStackTrace();
        }
        G.Log(object + "");
        Api api = RetrofitClient.createService(Api.class, G.api_username, G.api_password);
        Call<ResponseBody> request;
        if (editMode)
            request = api.editServicesCenterProduct(modelAddProduct.getId(), G.returnBody(object.toString()));
        else
            request = api.addServicesCenterProduct(G.returnBody(object.toString()));
        request.enqueue(new retrofit2.Callback<ResponseBody>() {
            @Override
            public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {

                int code = response.code();
                if (response.isSuccessful()) {

                    if (code == 200) {
                        if (editMode) {
                            sendProductEditedToActivity(modelAddProduct);
                            G.toast(" محصول با موفقیت ویرایش شد.");
                        } else
                            sendProductAddedToActivity(reserveProduct.getProduct_id());
                        G.toast("معرفی محصول با موفقیت صورت گرفت.");
                    } else {
                        if (editMode)
                            sendProductEditedToActivity(modelAddProduct);
                        else
                            sendProductAddedToActivity(reserveProduct.getProduct_id());
                        G.toast("خطا در معرفی محصول");

                    }
                }
                G.stop_loading();
                dismiss();

            }

            @Override
            public void onFailure(@NonNull Call<ResponseBody> call, @NonNull Throwable t) {
                G.stop_loading();
                G.toast("مشکل در برقراری ارتباط با سرور");
            }
        });
    }

    public void getSpinners() {
        listPackaging.clear();
        listPackaging.add("فلزی");
        listPackaging.add("پلاستیکی");

        spinner_packaging.setAdapter(G.setFontToSpinner(listPackaging));
        if (editMode) {
            for (int i = 0; i < listPackaging.size(); i++) {
                String s = listPackaging.get(i);
                String v = modelAddProduct.getPackaging();
                if (v.equals(s)) {
                    spinner_packaging.setSelection(i);
                }
            }

        }
        spinner_packaging.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                String selectedItem = parent.getItemAtPosition(position).toString();

                modelAddProduct.setPackaging(selectedItem);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

        });
        listAmount.clear();
        listAmount.add("1 لیتری");
        listAmount.add("3 لیتری");
        listAmount.add("3.5 لیتری");
        listAmount.add("4 لیتری");
        listAmount.add("4.5 لیتری");
        listAmount.add("5 لیتری");
        spinner_amount.setAdapter(G.setFontToSpinner(listAmount));
        if (editMode) {
            for (int i = 0; i < listAmount.size(); i++) {
                String s = listAmount.get(i);
                String v = modelAddProduct.getAmount();
                if (v.equals(s)) {
                    spinner_amount.setSelection(i);
                }
            }

        }
        spinner_amount.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = parent.getItemAtPosition(position).toString();
                modelAddProduct.setAmount(selectedItem);


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public interface BottomSheetListener {
        void onProductAdded(int product_id);

        void onProductEdited(ModelAddedProductCenter modelAddedProductCenter);
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
    private void sendProductAddedToActivity(int product_id) {
        if (listener != null) {
            listener.onProductAdded(product_id);
        }
    }

    private void sendProductEditedToActivity(ModelAddedProductCenter modelAddedProductCenter) {
        if (listener != null) {
            listener.onProductEdited(modelAddedProductCenter);
        }
    }


}
