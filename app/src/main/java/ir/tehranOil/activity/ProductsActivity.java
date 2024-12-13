package ir.tehranOil.activity;

import static ir.tehranOil.app.G.context;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomsheet.BottomSheetDialog;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ir.servicea.R;
import ir.tehranOil.adapter.AdapterListReserveProduct;
import ir.tehranOil.app.G;
import ir.tehranOil.app.PreferenceUtil;
import ir.tehranOil.model.ReserveProduct;
import ir.tehranOil.model.SliderItem;
import ir.tehranOil.retrofit.Api;
import ir.tehranOil.retrofit.RetrofitClient;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductsActivity extends AppCompatActivity {

    private AdapterListReserveProduct adapterListReserveProduct;
    RecyclerView reserveProductRecycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);
        reserveProductRecycler = findViewById(R.id.rv_products);
        reserveProductRecycler.setLayoutManager(new GridLayoutManager(this, 2));
        fetchProducts();
        // Create a list of products
      /*  List<ReserveProduct> productList = new ArrayList<>();
        productList.add(new ReserveProduct("روغن موتور موبیل", "5w40 - SN", "","","",R.drawable.product));
        productList.add(new ReserveProduct("روغن موتور لوبریفنت", "5w40 - SN","","","", R.drawable.product));
        productList.add(new ReserveProduct("روغن موتور بهتام", "5w40 - SN", "","","", R.drawable.product));
        productList.add(new ReserveProduct("روغن موتور فلوکس", "5w40 - SN", "","","", R.drawable.product));
*/
        // Set up the adapter
        /* adapterListReserveProduct = new AdapterListReserveProduct(productList, this);
        reserveProductRecycler.setAdapter(adapterListReserveProduct);*/
    }

    private void fetchProducts() {
        Api api = RetrofitClient.createService(Api.class, G.api_username, G.api_password);
        Call<ResponseBody> request = api.products_names("");
        request.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    List<ReserveProduct> products = new ArrayList<>();
                    // Set up the adapter with the fetched products

                    try {
                        String result = response.body().string();
                        JSONObject object = G.StringtoJSONObject(result);
                        JSONArray records = object.getJSONArray("records");
                        if (records.length() > 0) {
                            for (int i = 0; i < records.length(); i++) {
                                ReserveProduct product = new ReserveProduct();
                                JSONObject obj = records.getJSONObject(i);
                                SliderItem sliderItem = new SliderItem();
                                int id = obj.getInt("id");
                                String title = obj.getString("name");
                                product.setId(id);
                                product.setName(title);

                                products.add(product);
                                adapterListReserveProduct = new AdapterListReserveProduct(products, ProductsActivity.this, new AdapterListReserveProduct.OnAddProductClick(){
                                    @Override
                                    public void onAddProductClick(int productId) {
                                        showBottomSheetDialog(productId);
                                    }
                                });
                                reserveProductRecycler.setAdapter(adapterListReserveProduct);
                            }
                        } else {
                            Toast.makeText(ProductsActivity.this, "Failed to retrieve products", Toast.LENGTH_SHORT).show();
                        }
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(ProductsActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    public void addServiceCenterProductGroup(int productId) {
        G.loading(this);

        String created_at = G.converToEn(DateFormat.format("yyyy-MM-dd HH:mm:ss", new Date()).toString());
        JSONObject object = new JSONObject();
        try {
            int jobid = G.preference.getInt("job_category_id", 0);
            if (jobid <= 0) {
                G.toast("در قسمت پروفایل دسته شغلی خود را انتخاب کنید");
                return;
            }
            String d_id = PreferenceUtil.getD_id();

            object.put("product_group_id", productId);
            object.put("service_center_id", d_id);
            object.put("status", 1);
            object.put("is_default", 0);
            object.put("created_at", created_at);
            object.put("updated_at", created_at);
            object.put("deleted_at", JSONObject.NULL);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        G.Log(object + "");
        Api api = RetrofitClient.createService(Api.class, G.api_username, G.api_password);
        Call<ResponseBody> request = api.addServicesCenterProductGroup(G.returnBody(object.toString()));
        request.enqueue(new retrofit2.Callback<ResponseBody>() {
            @Override
            public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {
                String result = G.getResult(response);
                G.Log(call.request().toString());
                G.Log(result);
            }
            @Override
            public void onFailure(@NonNull Call<ResponseBody> call, @NonNull Throwable t) {
            }
        });
    }
    private void showBottomSheetDialog(int productId) {
        // Inflate the bottom sheet layout
        View view = getLayoutInflater().inflate(R.layout.bottom_sheet_add_product, null);

        // Create the BottomSheetDialog
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this);
        bottomSheetDialog.setContentView(view);

        // Get references to views in the bottom sheet layout
        TextView productTitle = view.findViewById(R.id.product_title);
        TextView productSubtitle = view.findViewById(R.id.product_subtitle);
        EditText stockAvailable = view.findViewById(R.id.stock_input);
        EditText salePrice = view.findViewById(R.id.sale_price_input);
        EditText discount = view.findViewById(R.id.discount_input);
        Button cancelButton = view.findViewById(R.id.cancel_button);
        Button addButton = view.findViewById(R.id.add_button);

        // Set data for the product (replace with actual product data)
        productTitle.setText("روغن موتور لوبریفنت");
        productSubtitle.setText("5w40 - SN");

        // Set button click listeners
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Dismiss the dialog when Cancel is clicked
                bottomSheetDialog.dismiss();
            }
        });

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Add product logic here
                String stock = stockAvailable.getText().toString();
                String price = salePrice.getText().toString();
                String discountValue = discount.getText().toString();

                // Add logic for adding the product
                // ...
                addServiceCenterProductGroup(productId);
                Toast.makeText(context,  " added", Toast.LENGTH_SHORT).show();

                // Dismiss the dialog after adding
                bottomSheetDialog.dismiss();
            }
        });

        // Show the BottomSheetDialog
        bottomSheetDialog.show();
    }
}