package com.barclays.controllers;

import com.azure.communication.email.EmailClient;
import com.azure.communication.email.EmailClientBuilder;
import com.azure.communication.email.models.EmailMessage;
import com.azure.communication.email.models.EmailSendResult;
import com.azure.communication.email.models.EmailSendStatus;
import com.azure.core.util.polling.LongRunningOperationStatus;
import com.azure.core.util.polling.PollResponse;
import com.azure.core.util.polling.SyncPoller;
import com.barclays.dto.CaseStatusNotifModel;
import com.barclays.entity.Case;
import com.barclays.entity.User;
import com.barclays.repository.CasesRepository;
import com.barclays.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/api/notify")
public class NotificationController {
    public static final Duration POLLER_WAIT_TIME = Duration.ofSeconds(10);

    private final Logger log =  LoggerFactory.getLogger(NotificationController.class);

    @Autowired
    private CasesRepository casesRepository;

    @Autowired
    private UserRepository userRepository;

    @Value("${email.endpoint}")
    private String connectionString;

    @Value("${email.sender}")
    private String senderAddress;

    @PostMapping("/case-status")
    public ResponseEntity<String> notification(@RequestBody CaseStatusNotifModel caseStatusModel){
        //connectionString = "endpoint=https://dc-comm-service.unitedstates.communication.azure.com/;accesskey=ylcz+wVAkZSzHIFGjFtYUpBBkUf++N3stWHr+fT/S0mnG3sgLvaW1xTZKSmaSRUEP3uFDbu5BGwCfHqf/8gj0A==";
        //senderAddress = "DoNotReply@18189ffc-978a-4d75-9a64-6fdb6b29f61c.azurecomm.net";
        //String recipientAddress = "gangulyshantanu@gmail.com";

        User userObj = null;
        Case caseObj = casesRepository.findByCaseId(caseStatusModel.getCaseId());
        if(caseObj!=null){
            userObj = userRepository.findByUserId(caseObj.getClient_id());
            if(userObj==null){
                return ResponseEntity.status(404).body("User Not found");
            }
        }else{
            return ResponseEntity.status(404).body("Case Not found"+caseStatusModel.getCaseId());
        }

        String recipientAddress = userObj.getEmail();


        EmailClient client = new EmailClientBuilder()
                .connectionString(connectionString)
                .buildClient();

        EmailMessage message = new EmailMessage()
                .setSenderAddress(senderAddress)
                .setToRecipients(recipientAddress)
                .setSubject("Case Status has Changed")
                .setBodyPlainText("This status for case:"+caseStatusModel.getCaseId()+" changed to:"+caseStatusModel.getCaseStatus())
                .setBodyHtml("<html><h1>This status for case:"+caseStatusModel.getCaseId()+" changed to:"+caseStatusModel.getCaseStatus()+"</h1></html>");

        try
        {
            SyncPoller<EmailSendResult, EmailSendResult> poller = client.beginSend(message, null);
            PollResponse<EmailSendResult> pollResponse = null;
            Duration timeElapsed = Duration.ofSeconds(0);

            while (pollResponse == null
                    || pollResponse.getStatus() == LongRunningOperationStatus.NOT_STARTED
                    || pollResponse.getStatus() == LongRunningOperationStatus.IN_PROGRESS)
            {
                pollResponse = poller.poll();
                log.info("Email send poller status: " + pollResponse.getStatus());

                Thread.sleep(POLLER_WAIT_TIME.toMillis());
                timeElapsed = timeElapsed.plus(POLLER_WAIT_TIME);

                if (timeElapsed.compareTo(POLLER_WAIT_TIME.multipliedBy(18)) >= 0)
                {
                    throw new RuntimeException("Polling timed out.");
                }
            }

            if (poller.getFinalResult().getStatus() == EmailSendStatus.SUCCEEDED)
            {
                log.info("Successfully sent the email (operation id: %s)", poller.getFinalResult().getId());
            }
            else
            {
                throw new RuntimeException(poller.getFinalResult().getError().getMessage());
            }
        }
        catch (Exception exception)
        {
            log.info(exception.getMessage());
            return ResponseEntity.status(500).body("Error sending email!");
        }

        return ResponseEntity.ok("Email Sent");
    }


}
