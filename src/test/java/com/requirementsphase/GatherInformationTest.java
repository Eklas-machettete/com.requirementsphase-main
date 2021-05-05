package com.requirementsphase;

import static org.fest.assertions.api.Assertions.assertThat;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.test.ProcessEngineTestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.requirementsphase.delegate.UpdateEpicDelegate; 

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations =  ("classpath*:/spring/applicationContext-test.xml"))

//@ContextConfiguration("classpath:applicationContext-test.xml")
public class GatherInformationTest extends ProcessEngineTestCase {

	//@Autowired
	private UpdateEpicDelegate gatherInformation=new UpdateEpicDelegate();

	@Test
	public void testExecute() throws Exception {
	
		DelegateExecution execution = mock(DelegateExecution.class);
		when(execution.getVariable("summaryProposal")).thenReturn("myValue");

		gatherInformation.execute(execution);

		assertThat(execution.getVariable("summaryProposal")).isEqualTo("myValue");
	}
}