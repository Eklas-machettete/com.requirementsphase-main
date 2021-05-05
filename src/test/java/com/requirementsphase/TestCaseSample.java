package com.requirementsphase;

import org.camunda.bpm.engine.runtime.ProcessInstance;

import org.camunda.bpm.engine.test.Deployment;
import org.camunda.bpm.engine.test.ProcessEngineRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static org.camunda.bpm.engine.test.assertions.ProcessEngineTests.*;

@RunWith(MockitoJUnitRunner.class)
public class TestCaseSample {
    private static final String PROCESS_KEY = "start_requirements_phase";

    @Rule
    public ProcessEngineRule rule = new ProcessEngineRule();

    @Test
    @Deployment(resources = {"requirements_phase.bpmn"})
    public void testSampleCase_happyPath() {

        ProcessInstance instance = runtimeService().startProcessInstanceByKey(PROCESS_KEY);

        assertThat(instance)
                .isActive()
                .hasPassed("StartEvent_1")
                .isWaitingAtExactly("Activity_0j4nfi1")
                .task().isAssignedTo("demo");

        complete(task(), withVariables(
                "sendEmails", "dpoint",
                "attribute1", "value1"
        ));
        
        assertThat(instance)
        .hasPassed("Activity_0j4nfi1")
        .hasPassedInOrder("Activity_0j4nfi1", "send_email_for_meeting")
        .isWaitingAt("Activity_1t75bia")
        .task().isAssignedTo("demo");

    complete(task(), withVariables("attributeService", "variableServicevalue"));
        
        assertThat(instance)
            .hasPassed("Activity_0j4nfi1")
            .hasPassedInOrder("Activity_0j4nfi1", "Activity_1t75bia")
            .isWaitingAt("Activity_1m799wd")
            .task().isNotAssigned();
 
        complete(task(), withVariables("attributeService", "variableServicevalue"));
        assertThat(instance)
             .hasPassedInOrder("userTask2", "endEvent")
             .isEnded();

        
        

        

    }
}
