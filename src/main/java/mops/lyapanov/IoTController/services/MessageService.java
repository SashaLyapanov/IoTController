package mops.lyapanov.IoTController.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import mops.lyapanov.IoTController.models.Message;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService {
    private final ObjectMapper mapper = new ObjectMapper();

    public String serializeMessages(List<Message> messages) throws JsonProcessingException {
        return mapper.writeValueAsString(messages);
    }

    public List<Message> deserializeMessages(String json) throws JsonProcessingException {
        return mapper.readValue(json, new TypeReference<List<Message>>() {});
    }

    public String serializeMessage(Message message) throws JsonProcessingException {
        return mapper.writeValueAsString(message);
    }

    public Message deserializeMessage(String json) throws JsonProcessingException {
        return mapper.readValue(json, Message.class);
    }

}
