package models.entity;

import models.enums.WorkKind;
import models.enums.WorkScale;

import java.time.LocalDateTime;

/**
 * Created by Miha on 08.09.2017.
 */
public class Request {
    private WorkKind workKind;
    private WorkScale workScale;
    private LocalDateTime desiredDateTime;

    public Request() {
    }

    private Request(RequestBuilder builder) {
        this.workKind = builder.workKind;
        this.workScale = builder.workScale;
        this.desiredDateTime = builder.desiredDateTime;
    }

    public static class RequestBuilder {

        private WorkKind workKind;
        private WorkScale workScale;
        private LocalDateTime desiredDateTime;

        public RequestBuilder(WorkKind workKind, WorkScale workScale, LocalDateTime desiredDateTime) {
            this.workKind = workKind;
            this.workScale = workScale;
            this.desiredDateTime = desiredDateTime;
        }

        public RequestBuilder setWorkKind(WorkKind workKind) {
           this.workKind = workKind;
            return this;
        }

        public RequestBuilder setWorkScale(WorkScale workScale) {
            this.workScale = workScale;
            return this;
        }

        public RequestBuilder setDesiredDateTime(LocalDateTime desiredDateTime) {
            this.desiredDateTime = desiredDateTime;
            return this;
        }

        public Request build() {
            return new Request(this);
        }
    }


    public WorkKind getWorkKind() {
        return workKind;
    }

    public WorkScale getWorkScale() {
        return workScale;
    }

    public LocalDateTime getDesiredDateTime() {
        return desiredDateTime;
    }

    @Override
    public String toString() {
        return "Request{" +
                "workKind=" + workKind +
                ", workScale=" + workScale +
                ", desiredDateTime=" + desiredDateTime +
                '}';
    }
}
