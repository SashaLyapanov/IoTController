package mops.lyapanov.IoTController.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("controller/general")
public class MessageController {

    @PostMapping("/sendingMessage")
    void gettingMessages() {
        
    }

}
