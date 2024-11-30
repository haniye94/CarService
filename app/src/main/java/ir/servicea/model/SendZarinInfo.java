package ir.servicea.model;

/**
 * @author haniye94 .
 * @since on 11/7/2024.
 */
public class SendZarinInfo {
    private String merchant_id;
    private int amount;
    private String callback_url;
    private String description;
    private ZarinMetadata metadata;
    private String currency;

    public String getMerchant_id() {
        return merchant_id;
    }

    public void setMerchant_id(String merchant_id) {
        this.merchant_id = merchant_id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getCallback_url() {
        return callback_url;
    }

    public void setCallback_url(String callback_url) {
        this.callback_url = callback_url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ZarinMetadata getZarinMetadata() {
        return metadata;
    }

    public void setZarinMetadata(ZarinMetadata zarinMetadata) {
        this.metadata = zarinMetadata;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}
