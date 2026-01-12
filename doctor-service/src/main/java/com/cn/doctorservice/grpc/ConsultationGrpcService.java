package com.cn.doctorservice.grpc;

import com.cn.doctorservice.entity.ConsultationSession;
import com.cn.doctorservice.entity.Doctor;
import com.cn.doctorservice.exceptions.ConsultationSessionNotExists;
import com.cn.doctorservice.exceptions.DoctorNotExists;
import com.cn.doctorservice.repository.ConsultationRepository;
import com.cn.doctorservice.repository.DoctorRepository;
import com.cn.protos.ConsultationSessionGetRequest;
import com.cn.protos.ConsultationSessionGetResponse;
import com.cn.protos.DoctorGetResponse;
import io.grpc.Status;
import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.server.service.GrpcService;
import com.cn.protos.ConsultationServiceGrpc;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;
import java.util.UUID;

@GrpcService
@Slf4j
public class ConsultationGrpcService extends ConsultationServiceGrpc.ConsultationServiceImplBase {

    @Autowired
    ConsultationRepository consultationRepository;
//    @Override
//    public void getConsultationDetails(ConsultationSessionGetRequest request,
//                                       StreamObserver<ConsultationSessionGetResponse> responseObserver) {
//
//        try {
//            Optional<ConsultationSession> optional = consultationRepository.findById(UUID.fromString(request.getSessionId()));
//
//            if (optional.isEmpty()) {
//                ConsultationSessionNotExists ex = new ConsultationSessionNotExists("Consultation not found with id " + request.getSessionId());
//                responseObserver.onError(
//                        Status.NOT_FOUND
//                                .withDescription(ex.getMessage())
//                                .asRuntimeException()
//                );
//                return;
//            }
//
//            ConsultationSession session = optional.get();
//
//            DoctorGetResponse response =  DoctorGetResponse.newBuilder()
//                    .setDoctorId(doctor.getId().toString())
//                    .setName(doctor.getName())
//                    .build();
//
//            responseObserver.onNext(response);
//            responseObserver.onCompleted();
//
//        } catch (IllegalArgumentException e) {
//            responseObserver.onError(
//                    Status.INVALID_ARGUMENT
//                            .withDescription("Invalid Consultation Session ID")
//                            .asRuntimeException()
//            );
//        } catch (Exception e) {
//            log.error("Service Failed");
//            responseObserver.onError(
//                    Status.INTERNAL
//                            .withDescription("Doctor service failure")
//                            .asRuntimeException()
//            );
//        }
//
//
//    }


}
