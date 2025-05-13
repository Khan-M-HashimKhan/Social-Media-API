package com.example.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Message;
import com.example.exception.*;
import com.example.repository.AccountRepository;
import com.example.repository.MessageRepository;

@Service
public class MessageService {
    @Autowired
    private MessageRepository messageRepository;
    @Autowired
    private AccountRepository accountRepository;

    public Message createMessage(Message msg){
        if (msg.getMessageText() == null || msg.getMessageText().length() > 255 || msg.getMessageText().isBlank()){
            throw new InvalidMessageTextException();
        }
        if( !accountRepository.existsByAccountId(msg.getPostedBy()) ){
            throw new InvalidAccountException();
        }
        return messageRepository.save(msg);
    }

    public List<Message> getAllMessages(){
        return messageRepository.findAll();
    }

    public Message getById(int mid){
        if (messageRepository.existsByMessageId(mid)){
            return messageRepository.findByMessageId(mid);
        }
        return null;
    }

    @Transactional
    public int deleteById(int mid){
        System.out.println("helo");
        if(messageRepository.existsByMessageId(mid)){
            System.out.println("m found");
            return messageRepository.deleteByMessageId(mid);
        }
        throw new MessageDoesNotExist();
    }

    public int updateTextById(int mid, String text){
        System.err.println("A:");
        System.err.println(text);
        System.err.println(text == "");
        if(messageRepository.existsByMessageId(mid)){
            if (text == null || text.length()>255 || text.isBlank() || text.isEmpty() || text == ""){
                System.err.println("C");
                throw new InvalidMessageTextException();
            }
            return messageRepository.updateTextById(mid,text);
        }
        throw new MessageDoesNotExist();
    }

    public List<Message> getMessagesbyUser(int accountID){
        if (accountRepository.existsByAccountId(accountID)){
            return messageRepository.findByPostedBy(accountID);
        }
        throw new InvalidAccountException();
    }


}
