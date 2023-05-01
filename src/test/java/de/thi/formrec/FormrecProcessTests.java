package de.thi.formrec;

import de.thi.FormrecModel;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;
import org.kie.kogito.Model;
import org.kie.kogito.process.Process;
import org.kie.kogito.process.ProcessInstance;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@QuarkusTest
public class FormrecProcessTests
{
	@Inject
	@Named("formrec")
	Process<FormrecModel> formrecProcess;

	@Test
	public void shouldRecognizeRestaurantReceipt()
	{
		// given
		assertNotNull(formrecProcess);

		Map<String, Object> parameters = new HashMap<>();
		parameters.put("url", "https://nextcloud.herhoffer.net/apps/files_sharing/publicpreview/b2c5QN67XJLxHje?file=/&fileId=3695&x=3840&y=2160&a=true");

		Model model = formrecProcess.createModel();
		model.fromMap(parameters);

		// when
		ProcessInstance<FormrecModel> processInstance = (ProcessInstance<FormrecModel>)formrecProcess.createInstance(model);
		processInstance.start();

		// then
		assertEquals(ProcessInstance.STATE_COMPLETED, processInstance.status());
		assertNotNull(processInstance.variables().getReceipt());
		assertEquals("Yapapa Restaurant", processInstance.variables().getReceipt().getMerchant());
		assertEquals("288 Love Forever", processInstance.variables().getReceipt().getItems().get(0).getDescription());
	}
}
