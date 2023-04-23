package de.thi.formrec;

import com.azure.ai.formrecognizer.documentanalysis.DocumentAnalysisClient;
import com.azure.ai.formrecognizer.documentanalysis.DocumentAnalysisClientBuilder;
import com.azure.ai.formrecognizer.documentanalysis.models.AnalyzeResult;
import com.azure.ai.formrecognizer.documentanalysis.models.OperationResult;
import com.azure.core.credential.AzureKeyCredential;
import com.azure.core.util.polling.SyncPoller;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.thi.formrec.model.Receipt;
import org.apache.commons.lang3.NotImplementedException;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.io.IOException;

@ApplicationScoped
public class FormRecognizer
{
	private static final Logger LOG = LoggerFactory.getLogger(FormRecognizer.class);

	@ConfigProperty(name = "de.thi.azure.endpoint")
	String endpoint;

	@ConfigProperty(name = "de.thi.azure.key")
	String key;

	@Inject
	ReceiptFactory receiptFactory;

	public Receipt recognize() throws IOException
	{
		DocumentAnalysisClient client = new DocumentAnalysisClientBuilder()
			.credential(new AzureKeyCredential(key))
			.endpoint(endpoint)
			.buildClient();

		String documentUrl = "https://formrecognizer.appliedai.azure.com/documents/samples/prebuilt/receipt.png";
		String modelId = "prebuilt-receipt";
		SyncPoller<OperationResult, AnalyzeResult> analyzeDocumentPoller =
			client.beginAnalyzeDocumentFromUrl(modelId, documentUrl);

		AnalyzeResult analyzeResult = analyzeDocumentPoller.getFinalResult();

		if (analyzeResult.getDocuments().size() > 1)
		{
			throw new NotImplementedException("Only one page receipts are supported for now");
		}

		return receiptFactory.create(analyzeResult.getDocuments().get(0));
	}
}