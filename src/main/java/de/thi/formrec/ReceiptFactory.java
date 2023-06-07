package de.thi.formrec;

import com.azure.ai.formrecognizer.documentanalysis.models.AnalyzedDocument;
import com.azure.ai.formrecognizer.documentanalysis.models.DocumentField;
import de.thi.formrec.model.Item;
import de.thi.formrec.model.Receipt;
import org.javamoney.moneta.Money;

import javax.enterprise.context.ApplicationScoped;
import java.util.Map;
import java.util.Optional;

@ApplicationScoped
public class ReceiptFactory {
	public Receipt create(AnalyzedDocument analyzedDocument) {
		Map<String, DocumentField> fields = analyzedDocument.getFields();
		DocumentField date = fields.get("TransactionDate");
		Receipt receipt = new Receipt();
		if (date != null) {
			receipt.setDate(date.getValueAsDate());
		}
		setHeaderFields(fields, receipt);

		for (DocumentField field : fields.get("Items").getValueAsList()) {
			Map<String, DocumentField> map = field.getValueAsMap();
			Item item = new Item();
			DocumentField description = map.get("Description");
			item.setDescription((description != null) ? map.get("Description").getValueAsString() : null);

			DocumentField quantity = map.get("Quantity");
			item.setQuantity((quantity != null) ? quantity.getValueAsDouble() : null);

			DocumentField totalPrice = map.get("TotalPrice");
			item.setTotalPrice(asMoney((totalPrice != null) ? totalPrice.getValueAsDouble() : null));

			receipt.addItem(item);
		}

		return receipt;
	}

	private void setHeaderFields(Map<String, DocumentField> fields, Receipt receipt) {
		String merchantName = Optional.ofNullable(fields.get("MerchantName"))
				.map(DocumentField::getValueAsString)
				.orElse(null);
		receipt.setMerchant(merchantName);

		Money total = asMoney(Optional.ofNullable(fields.get("Total"))
				.map(DocumentField::getValueAsDouble)
				.orElse(null));
		receipt.setTotal(total);

		Money subtotal = asMoney(Optional.ofNullable(fields.get("Subtotal"))
				.map(DocumentField::getValueAsDouble)
				.orElse(null));
		receipt.setSubTotal(subtotal);

		Money totalTax = asMoney(Optional.ofNullable(fields.get("TotalTax"))
				.map(DocumentField::getValueAsDouble)
				.orElse(null));
		receipt.setTotalTax(totalTax);
	}

	private static Money asMoney(Double currencyValue) {
		if (currencyValue == null) {
			return null;
		}
		return Money.of(currencyValue, "EUR");
	}
}
