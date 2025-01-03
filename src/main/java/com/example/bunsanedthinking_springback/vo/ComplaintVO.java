package com.example.bunsanedthinking_springback.vo;

import com.example.bunsanedthinking_springback.entity.complaint.Complaint;
import com.example.bunsanedthinking_springback.entity.complaint.ComplaintProcessStatus;
import com.example.bunsanedthinking_springback.entity.complaint.ComplaintType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ComplaintVO {
    private int id;
    private int complaint_type;
    private String content;
    private LocalDate date;
    private String employee_name;
    private LocalDate processing_date;
    private int process_status;
    private String result;
    private String title;
    private int customer_id;

    public Complaint getEntity() {
        Complaint complaint = new Complaint();
        complaint.setId(id);
        complaint.setComplaintType(ComplaintType.values()[complaint_type]);
        complaint.setContent(content);
        complaint.setPostDate(Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant()));

        complaint.setEmployeeName(employee_name);
        complaint.setProcessingDate(processing_date == null ? null :
                Date.from(processing_date.atStartOfDay(ZoneId.systemDefault()).toInstant()));
        complaint.setProcessStatus(ComplaintProcessStatus.values()[process_status]);
        complaint.setResult(result);
        complaint.setTitle(title);
        complaint.setCustomerID(customer_id);
        return complaint;
    }
}
