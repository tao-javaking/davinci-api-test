package com.example.demo.domain;

import java.io.Serializable;
import java.util.concurrent.atomic.AtomicLong;

public class DataCounter implements Serializable {

    private AtomicLong all;
    private AtomicLong success;
    private AtomicLong fail;

    public void init() {
        all = new AtomicLong();
        success = new AtomicLong();
        fail = new AtomicLong();
    }

    public DataCounter() {
        init();
    }

    public AtomicLong getAll() {
        return all;
    }

    public void setAll(AtomicLong all) {
        this.all = all;
    }

    public AtomicLong getSuccess() {
        return success;
    }

    public void setSuccess(AtomicLong success) {
        this.success = success;
    }

    public AtomicLong getFail() {
        return fail;
    }

    public void setFail(AtomicLong fail) {
        this.fail = fail;
    }

    @Override
    public String toString() {
        return "DataCounter{" +
                "all=" + all.get() +
                ", success=" + success.get() +
                ", fail=" + fail.get() +
                '}';
    }
}
