package de.thi.formrec;

import com.azure.ai.formrecognizer.documentanalysis.models.AnalyzedDocument;
import com.azure.ai.formrecognizer.documentanalysis.models.CurrencyValue;
import com.azure.ai.formrecognizer.documentanalysis.models.DocumentField;
import de.thi.formrec.model.Item;
import de.thi.formrec.model.Receipt;
import org.javamoney.moneta.Money;

import javax.enterprise.context.ApplicationScoped;
import javax.print.Doc;
import java.util.Currency;
import java.util.Map;

@ApplicationScoped
public class ReceiptFactory
{
	public Receipt create(AnalyzedDocument analyzedDocument)
	{
		Map<String, DocumentField> fields = analyzedDocument.getFields();

		Receipt receipt = new Receipt();
		receipt.setMerchant(fields.get("MerchantName").getValueAsString());
		receipt.setTotal(asMoney(fields.get("Total").getValueAsDouble()));
		receipt.setSubTotal(asMoney(fields.get("Subtotal").getValueAsDouble()));
		receipt.setTotalTax(asMoney(fields.get("TotalTax").getValueAsDouble()));

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

	private static Money asMoney(Double currencyValue)
	{
		return Money.of(currencyValue, "EUR");
	}
}
