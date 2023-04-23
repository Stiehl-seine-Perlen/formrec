package de.thi.formrec;

import de.thi.formrec.model.Receipt;
import io.quarkus.test.junit.QuarkusTest;
import org.hamcrest.collection.IsCollectionWithSize;
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
	public void shouldRecognizeReceipt() throws IOException
	{
		// when
		Receipt receipt = formRecognizer.recognize();

		// then
		assertEquals(receipt.getMerchant(), "Contoso");
		assertThat(receipt.getItems(), hasSize(2));
	}
}
