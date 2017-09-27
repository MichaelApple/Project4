package model.entities;

import model.enums.WorkKind;
import model.enums.WorkScale;

import java.time.LocalDate;

/**
 * Created by Miha on 08.09.2017.
 */
public class UserRequest {
    private int id;
    private int userId;
    private WorkKind workKind;
    private WorkScale workScale;
    private LocalDate desiredDateTime;


    public static class RequestBuilder {

        private int id;
        private int userId;
        private WorkKind workKind;
        private WorkScale workScale;
        private LocalDate desiredDateTime;


        public RequestBuilder setId(int id) {
            this.id = id;
            return this;
        }

        public RequestBuilder setUserId(int userId) {
            this.userId = userId;
            return this;
        }

        public RequestBuilder setWorkKind(WorkKind workKind) {
           this.workKind = workKind;
            return this;
        }

        public RequestBuilder setWorkScale(WorkScale workScale) {
            this.workScale = workScale;
            return this;
        }

        public RequestBuilder setDesiredDateTime(LocalDate desiredDateTime) {
            this.desiredDateTime = desiredDateTime;
            return this;
        }

        public UserRequest build() {
            UserRequest userRequest = new UserRequest();
            userRequest.setId(id);
            userRequest.setUserId(userId);
            userRequest.setWorkKind(workKind);
            userRequest.setWorkScale(workScale);
            userRequest.setDesiredDateTime(desiredDateTime);
            return userRequest;
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public WorkKind getWorkKind() {
        return workKind;
    }

    public WorkScale getWorkScale() {
        return workScale;
    }

    public LocalDate getDesiredDateTime() {
        return desiredDateTime;
    }

    public void setWorkKind(WorkKind workKind) {
        this.workKind = workKind;
    }

    public void setWorkScale(WorkScale workScale) {
        this.workScale = workScale;
    }

    public void setDesiredDateTime(LocalDate desiredDateTime) {
        this.desiredDateTime = desiredDateTime;
    }

    @Override
    public String toString() {
        return "UserRequest{" +
                "id=" + id +
                ", userId=" + userId +
                ", workKind=" + workKind +
                ", workScale=" + workScale +
                ", desiredDateTime=" + desiredDateTime +
                '}';
    }
}
