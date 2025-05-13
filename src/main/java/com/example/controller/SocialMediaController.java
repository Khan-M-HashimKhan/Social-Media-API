package com.example.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.Account;
import com.example.entity.Message;
import com.example.service.AccountService;
import com.example.service.MessageService;
import com.example.exception.*;


@RestController
public class SocialMediaController {
    
    @Autowired
    private AccountService accountServce;
    @Autowired
    private MessageService messageService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Account acc){
        try{
            Account created = accountServce.register(acc);
            return ResponseEntity.status(200).body(created);

        }
        catch(DuplicateUsernameException d){
            return ResponseEntity.status(409).body("");
        }
        catch(InvalidAccountException i){
            return ResponseEntity.status(400).body("");
        }
        
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Account acc){
        try{
            Account logedAccount = accountServce.login(acc);
            return ResponseEntity.status(200).body(logedAccount);
        }
        catch(InvalidLoginException i){
            return ResponseEntity.status(401).body("");
        }

    }

    @PostMapping("/messages")
    public ResponseEntity<?> createMessage(@RequestBody Message msg){
        try{
            Message createdMessage = messageService.createMessage(msg);
            return ResponseEntity.status(200).body(createdMessage);
        }
        catch (InvalidMessageTextException i){
            return ResponseEntity.status(400).body(null);
        }
        catch(InvalidAccountException ia){
            return ResponseEntity.status(400).body(null);
        }
    }

    @GetMapping("/messages")
    public ResponseEntity<?> getAllMessages(){
        return ResponseEntity.status(200).body(messageService.getAllMessages());
    }

    @GetMapping("/messages/{messageId}")
    public ResponseEntity<?> getMessageById(@PathVariable Integer messageId){
        return ResponseEntity.status(200).body(messageService.getById(messageId));
    }

    @DeleteMapping("/messages/{messageId}")
    public ResponseEntity<?> deleteMessageById(@PathVariable Integer messageId){
        try{
            
            int respone = messageService.deleteById(messageId);
            System.out.println("response");
            return ResponseEntity.status(200).body(respone);
        }
        catch (MessageDoesNotExist md){
            return ResponseEntity.status(200).body(null);
        }
    }

    @PatchMapping("/messages/{messageId}")
    public ResponseEntity<?> updateMessageTextById(@PathVariable int messageId, @RequestBody Message messageText){
        try{
            int response = messageService.updateTextById(messageId, messageText.getMessageText());
            return ResponseEntity.status(200).body(response);
        }
        catch (InvalidMessageTextException m){
            return ResponseEntity.status(400).body(null);
        }
        catch (MessageDoesNotExist d){
            return ResponseEntity.status(400).body(null);
        }
    }

    @GetMapping("/accounts/{accountId}/messages")
    public ResponseEntity<?> getMessagesbyUser(@PathVariable int accountId){
        try{
            List<Message> messages = messageService.getMessagesbyUser(accountId);
            return ResponseEntity.status(200).body(messages);
        }
        catch (InvalidAccountException i){
            return ResponseEntity.status(200).body(null);
        }
    }


}
