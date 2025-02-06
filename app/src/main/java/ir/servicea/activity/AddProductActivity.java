package ir.servicea.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import ir.servicea.R;

import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import ir.servicea.app.G;
import ir.servicea.model.ReserveProduct;
import ir.servicea.retrofit.Api;
import ir.servicea.retrofit.RetrofitClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddProductActivity extends AppCompatActivity {

    private EditText etProductName, etProductPrice, etProductBrand, etProductGrade, etProductDescription;
    private Spinner spinnerProductType;
    private Button btnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);

        etProductName = findViewById(R.id.et_product_name);
        etProductPrice = findViewById(R.id.et_product_price);
        etProductBrand = findViewById(R.id.et_product_brand);
        etProductDescription = findViewById(R.id.et_product_description);
        spinnerProductType = findViewById(R.id.spinner_product_type);
        btnSubmit = findViewById(R.id.btn_submit);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submitProduct();
            }
        });
    }

    private void submitProduct() {
        // Get values from UI
        String productName = etProductName.getText().toString().trim();
        String productPrice = etProductPrice.getText().toString().trim();
        String productBrand = etProductBrand.getText().toString().trim();
        String productGrade = etProductGrade.getText().toString().trim();
        String productDescription = etProductDescription.getText().toString().trim();
        String productType = spinnerProductType.getSelectedItem().toString();

        // Validate input
        if (TextUtils.isEmpty(productName) || TextUtils.isEmpty(productPrice) ||
                TextUtils.isEmpty(productBrand) || TextUtils.isEmpty(productType)) {
            Toast.makeText(this, "لطفاً تمام فیلدهای ضروری را پر کنید", Toast.LENGTH_SHORT).show();
            return;
        }

        // Create product object
//        ReserveProduct product = new ReserveProduct(productName, productPrice, productBrand, productGrade, productType, productDescription, 0);
        ReserveProduct product = new ReserveProduct();
        // Make the API call
        Api apiService = RetrofitClient.createService(Api.class, G.api_username, G.api_password);
        Call<ReserveProduct> call = apiService.addProduct(product);

        call.enqueue(new Callback<ReserveProduct>() {
            @Override
            public void onResponse(Call<ReserveProduct> call, Response<ReserveProduct> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(AddProductActivity.this, "محصول با موفقیت ارسال شد", Toast.LENGTH_LONG).show();
                    clearForm();
                } else {
                    Toast.makeText(AddProductActivity.this, "خطا در ارسال محصول: " + response.message(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ReserveProduct> call, Throwable t) {
                Toast.makeText(AddProductActivity.this, "خطا: " + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void clearForm() {
        etProductName.setText("");
        etProductPrice.setText("");
        etProductBrand.setText("");
        etProductDescription.setText("");
        spinnerProductType.setSelection(0);
    }
}