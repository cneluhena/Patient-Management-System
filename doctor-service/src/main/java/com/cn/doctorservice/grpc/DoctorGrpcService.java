package com.cn.doctorservice.grpc;

import com.cn.doctorservice.entity.Doctor;
import com.cn.doctorservice.exceptions.DoctorNotExists;
import com.cn.doctorservice.repository.DoctorRepository;

import com.cn.protos.DoctorGetRequest;
import com.cn.protos.DoctorGetResponse;
import com.cn.protos.DoctorServiceGrpc;
import io.grpc.Status;
import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;
import java.util.UUID;


@GrpcService
@Slf4j
public class DoctorGrpcService extends DoctorServiceGrpc.DoctorServiceImplBase {

    @Autowired
    DoctorRepository doctorRepository;

    @Override
    public void getDoctorDetails(DoctorGetRequest request, StreamObserver<DoctorGetResponse> responseObserver) {

        try {
            Optional<Doctor> optional = doctorRepository.findById(UUID.fromString(request.getDoctorId()));

            if (optional.isEmpty()) {
                DoctorNotExists ex = new DoctorNotExists("Doctor not found with id " + request.getDoctorId());
                responseObserver.onError(
                        Status.NOT_FOUND
                                .withDescription(ex.getMessage())
                                .asRuntimeException()
                );
                return;
            }

            Doctor doctor = optional.get();

            DoctorGetResponse response =  DoctorGetResponse.newBuilder()
                    .setDoctorId(doctor.getId().toString())
                    .setName(doctor.getName())
                    .build();

            responseObserver.onNext(response);
            responseObserver.onCompleted();

        } catch (IllegalArgumentException e) {
            responseObserver.onError(
                    Status.INVALID_ARGUMENT
                            .withDescription("Invalid doctor ID")
                            .asRuntimeException()
            );
        } catch (Exception e) {
            log.error("Service Failed");
            responseObserver.onError(
                    Status.INTERNAL
                            .withDescription("Doctor service failure")
                            .asRuntimeException()
            );
        }

    }

}
