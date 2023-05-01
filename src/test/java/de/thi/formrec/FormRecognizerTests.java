package de.thi.formrec;

import de.thi.formrec.model.Receipt;
import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.jupiter.api.Assertions.assertEquals;

@QuarkusTest
public class FormRecognizerTests
{
	@Inject
	FormRecognizer formRecognizer;

	@Test
	public void shouldRecognizeHardwareReceipt() throws IOException
	{
		// given
		String url = "https://formrecognizer.appliedai.azure.com/documents/samples/prebuilt/receipt.png";

		// when
		Receipt receipt = formRecognizer.recognize(url);

		// then
		assertEquals(receipt.getMerchant(), "Contoso");
		assertThat(receipt.getItems(), hasSize(2));
	}

	@Test
	public void shouldRecognizeRestaurantReceipt() throws IOException
	{
		// given
		String url = "https://nextcloud.herhoffer.net/apps/files_sharing/publicpreview/b2c5QN67XJLxHje?file=/&fileId=3695&x=3840&y=2160&a=true";

		// when
		Receipt receipt = formRecognizer.recognize(url);

		// then
		assertEquals(receipt.getMerchant(), "Yapapa Restaurant");
		assertThat(receipt.getItems(), hasSize(4));
		assertEquals("EUR 15.90", receipt.getItems().get(0).getTotalPrice().toString());
	}
}
