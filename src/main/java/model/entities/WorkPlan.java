package model.entities;

import model.entities.brigade.Brigade;

import javax.ejb.Local;
import java.time.LocalDate;
import java.util.Queue;

/**
 * Created by Miha on 22.09.2017.
 */
public class WorkPlan {

    private int requestId;
    private int brigadeId;
    private LocalDate desiredDate;

    public static class Builder{
        private int requestId;
        private int brigadeId;
        private LocalDate desiredDate;

        public Builder setRequestId(int requestId) {
            this.requestId = requestId;
            return this;
        }

        public Builder setBrigadeId(int brigadeId) {
            this.brigadeId = brigadeId;
            return this;
        }

        public Builder setDesiredDate(LocalDate desiredDate) {
            this.desiredDate = desiredDate;
            return this;
        }

        public WorkPlan build(){
            WorkPlan workPlan = new WorkPlan();
            workPlan.setRequestId(requestId);
            workPlan.setBrigadeId(brigadeId);
            workPlan.setDesiredDate(desiredDate);
            return workPlan;
        }
    }


    public int getRequestId() {
        return requestId;
    }

    public void setRequestId(int requestId) {
        this.requestId = requestId;
    }

    public int getBrigadeId() {
        return brigadeId;
    }

    public void setBrigadeId(int brigadeId) {
        this.brigadeId = brigadeId;
    }

    public LocalDate getDesiredDate() {
        return desiredDate;
    }

    public void setDesiredDate(LocalDate desiredDate) {
        this.desiredDate = desiredDate;
    }

    @Override
    public String toString() {
        return "WorkPlan{" +
                "requestId=" + requestId +
                ", brigadeId=" + brigadeId +
                ", desiredDate=" + desiredDate +
                '}';
    }
}
