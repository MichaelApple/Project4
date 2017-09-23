package model.entities.brigade;

import java.time.LocalDate;

/**
 * Created by Miha on 09.09.2017.
 */
public abstract class Brigade {
    private int id;
    private int requestId;
    private String name;
    private int workerCount;

    public static class Builder {
        private int id;
        private int requestId;
        private String name;
        private int workerCount;

        public Builder setId(int id) {
            this.id = id;
            return this;
        }

        public Builder setRequestId(int requestId) {
            this.requestId = requestId;
            return this;
        }

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setWorkerCount(int workerCount) {
            this.workerCount = workerCount;
            return this;
        }

//        public Brigade build() {
//            Brigade brigade;
//            brigade.setId();
//
//        }
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRequestId() {
        return requestId;
    }

    public void setRequestId(int requestId) {
        this.requestId = requestId;
    }

    public abstract void work();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWorkerCount() {
        return workerCount;
    }

    public void setWorkerCount(int workerCount) {
        this.workerCount = workerCount;
    }

    @Override
    public String toString() {
        return "Brigade{" +
                "id=" + id +
                ", requestId=" + requestId +
                ", name='" + name + '\'' +
                ", workerCount=" + workerCount +
                '}';
    }
}
