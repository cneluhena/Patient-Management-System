package com.cn.appointmentservice.cb;

import io.grpc.Status;
import io.grpc.StatusRuntimeException;
import lombok.extern.slf4j.Slf4j;

import java.util.function.Predicate;

@Slf4j
public class GrpcBusinessExceptionPredicate implements Predicate<Throwable> {

    @Override
    public boolean test(Throwable t) {
        if (t instanceof StatusRuntimeException ex) {
            Status.Code code = ex.getStatus().getCode();

            return code == Status.Code.INTERNAL
                    || code == Status.Code.DEADLINE_EXCEEDED;
        }
        return false;

    }
}

