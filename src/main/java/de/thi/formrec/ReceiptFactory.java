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
public class ReceiptFactory
{
	public Receipt create(AnalyzedDocument analyzedDocument)
	{
		Map<String, DocumentField> fields = analyzedDocument.getFields();

		Receipt receipt = new Receipt();
		setHeaderFields(fields, receipt);

		for (DocumentField field : fields.get("Items").getValueAsList())
		{
			Map<String, DocumentField> map = field.getValueAsMap();

			Item item = new Item();
			item.setDescription(map.get("Description").getValueAsString());
			item.setQuantity(map.get("Quantity").getValueAsDouble());
			item.setTotalPrice(asMoney(map.get("TotalPrice").getValueAsDouble()));

			receipt.addItem(item);
		}

		return receipt;
	}

	private void setHeaderFields(Map<String, DocumentField> fields, Receipt receipt)
	{
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

	private static Money asMoney(Double currencyValue)
	{
		if (currencyValue == null)
		{
			return null;
		}
		return Money.of(currencyValue, "EUR");
	}
}
