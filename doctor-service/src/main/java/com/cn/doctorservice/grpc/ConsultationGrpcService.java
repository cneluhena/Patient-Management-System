package com.cn.doctorservice.grpc;

import com.cn.doctorservice.exceptions.SessionAlreadyFullyBooked;
import com.cn.doctorservice.repository.ConsultationRepository;
import com.cn.protos.*;
import io.grpc.Status;
import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

@GrpcService
@Slf4j
public class ConsultationGrpcService extends ConsultationServiceGrpc.ConsultationServiceImplBase {

    @Autowired
    ConsultationRepository consultationRepository;

    //reserve session
    @Override
    public void reserveConsultationSession(SessionReserveRequest request,
                                       StreamObserver<SessionReserveResponse> responseObserver) {
        try {

            int updated = consultationRepository.reserveSession(UUID.fromString(request.getSessionId()));

            if (updated == 0) {
                log.error("Session Already Fully Booked");
                SessionAlreadyFullyBooked ex = new SessionAlreadyFullyBooked("Session Already Fully Booked");
                responseObserver.onError(
                        Status.FAILED_PRECONDITION
                                .withDescription("SESSION_BOOKED")
                                .asRuntimeException()
                );
                return;
            } else {

                SessionReserveResponse response =  SessionReserveResponse.newBuilder()
                        .setSessionResponse(true)
                        .build();
                responseObserver.onNext(response);
                responseObserver.onCompleted();

            }

        } catch (IllegalArgumentException e) {
            responseObserver.onError(
                    Status.INVALID_ARGUMENT
                            .withDescription("Invalid Consultation Session ID")
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
