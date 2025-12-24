package com.cn.doctorservice.grpc;

import com.cn.doctorservice.entity.Doctor;
import com.cn.doctorservice.exceptions.DoctorNotExists;
import com.cn.doctorservice.repository.DoctorRepository;

import com.cn.protos.DoctorGetRequest;
import com.cn.protos.DoctorGetResponse;
import com.cn.protos.DoctorServiceGrpc;
import io.grpc.Status;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;
import java.util.UUID;


@GrpcService
public class DoctorGrpcService extends DoctorServiceGrpc.DoctorServiceImplBase {

    @Autowired
    DoctorRepository doctorRepository;

    @Override
    public void getDoctorDetails(DoctorGetRequest request, StreamObserver<DoctorGetResponse> responseObserver) {

        Optional<Doctor> optional = doctorRepository.findById(UUID.fromString(request.getDoctorId()));
        if (!optional.isPresent()) {
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

    }
}
