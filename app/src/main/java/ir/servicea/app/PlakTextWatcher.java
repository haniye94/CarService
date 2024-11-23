package ir.servicea.app;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

public class PlakTextWatcher implements TextWatcher {
    private EditText currentEditText;
    private EditText nextEditText;

    public PlakTextWatcher(EditText currentEditText, EditText nextEditText) {
        this.currentEditText = currentEditText;
        this.nextEditText = nextEditText;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if (currentEditText.getText().toString().length() == 1) {
            nextEditText.requestFocus();
        }
    }

    @Override
    public void afterTextChanged(Editable s) {}
}