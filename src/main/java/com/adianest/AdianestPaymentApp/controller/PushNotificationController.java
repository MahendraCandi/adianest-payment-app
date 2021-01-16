package com.adianest.AdianestPaymentApp.controller;

import com.adianest.AdianestPaymentApp.dto.NotifikasiDto;
import com.adianest.AdianestPaymentApp.fcm.PushNotificationRequest;
import com.adianest.AdianestPaymentApp.fcm.PushNotificationResponse;
import com.adianest.AdianestPaymentApp.fcm.PushNotificationService;
import com.adianest.AdianestPaymentApp.model.Notifikasi;
import com.adianest.AdianestPaymentApp.service.INotifikasi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/notification")
public class PushNotificationController {

    @Autowired
    private PushNotificationService pushNotificationService;

    @Autowired
    private INotifikasi notifikasiService;

    @PostMapping("/topic")
    public ResponseEntity sendNotification(@RequestBody PushNotificationRequest request) {
        pushNotificationService.sendPushNotificationWithoutData(request);
        return new ResponseEntity<>(new PushNotificationResponse(
                HttpStatus.OK.value(), "Notification has been sent."), HttpStatus.OK);
    }
    @PostMapping("/token")
    public ResponseEntity sendTokenNotification(@RequestBody PushNotificationRequest request) {
        pushNotificationService.sendPushNotificationToToken(request);
        return new ResponseEntity<>(new PushNotificationResponse(
                HttpStatus.OK.value(), "Notification has been sent."), HttpStatus.OK);
    }
    @PostMapping("/data")
    public ResponseEntity sendDataNotification(@RequestBody PushNotificationRequest request) {
        pushNotificationService.sendPushNotification(request);
        return new ResponseEntity<>(new PushNotificationResponse(
                HttpStatus.OK.value(), "Notification has been sent."), HttpStatus.OK);
    }

    @PostMapping("/all")
    public List<NotifikasiDto> getAllNotifikasiByIdUser(@RequestParam("id") String idUser) {
        return notifikasiService.getAllByUserId(idUser)
                .stream()
                .map(p -> new NotifikasiDto(
                        p.getId(),
                        p.getUserId(),
                        p.getStatus().toString(),
                        p.getTransaksiId(),
                        p.getTransaksiKategori(),
                        p.getMessage(),
                        p.getTglTransaksi().toLocalDateTime()
                                .format(DateTimeFormatter.ofPattern("dd MMMM yyyy", Locale.forLanguageTag("id-ID")))))
                .collect(Collectors.toList());
    }
}
