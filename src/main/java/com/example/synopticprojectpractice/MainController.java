package com.example.synopticprojectpractice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path="/app")
public class MainController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PaymentCardRepository paymentCardRepository;

    @PostMapping(path="/addUser")
    public @ResponseBody String addNewUser(@RequestParam String employeeName) {

        PaymentCard newCard = new PaymentCard();
        paymentCardRepository.save(newCard);

        User newUser = new User();
        newUser.setName(employeeName);
        newUser.setPaymentCard(newCard.getPaymentCardId());
        userRepository.save(newUser);
        return "Payment card and user created";
    }

    @GetMapping(path="/allUsers")
    public @ResponseBody Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping(path="/allPaymentCards")
    public @ResponseBody Iterable<PaymentCard> getAllPaymentCards() {
        return paymentCardRepository.findAll();
    }
}
