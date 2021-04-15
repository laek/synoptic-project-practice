package com.example.synopticprojectpractice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping(path="/app")
public class MainController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PaymentCardRepository paymentCardRepository;

    @PostMapping(path="/addUser")
    public @ResponseBody String addNewUser(@RequestParam String employeeName, @RequestParam Integer employeeId) {

        PaymentCard newCard = new PaymentCard();
        paymentCardRepository.save(newCard);

        User newUser = new User();
        newUser.setName(employeeName);
        newUser.setEmployeeId(employeeId);
        newUser.setPaymentCard(newCard.getPaymentCardId());
        userRepository.save(newUser);
        return "Payment card and user created";
    }

    @PostMapping(path="/topUp")
    public @ResponseBody String topUpBalance(@RequestParam Integer employeeId, @RequestParam Double amount) {
        Optional<User> user = userRepository.findById(employeeId);
        if (user.isPresent()) {
            Integer paymentCardId = user.get().getPaymentCardId();
            Optional<PaymentCard> card = paymentCardRepository.findById(paymentCardId);
            card.get().addBalance(amount);
            paymentCardRepository.save(card.get());

            return "balance added";
        }

        return "user not found";
    }

    @PostMapping(path="/deduct")
    public @ResponseBody String deductBalance(@RequestParam Integer employeeId, @RequestParam Double amount) {
        Optional<User> user = userRepository.findById(employeeId);

        if (user.isPresent()) {
            Integer paymentCardId = user.get().getPaymentCardId();
            Optional<PaymentCard> card = paymentCardRepository.findById(paymentCardId);

            if (card.get().getBalance() >= amount) {
                card.get().deductBalance(amount);
                paymentCardRepository.save(card.get());
                return "payment made";
            } else {
                return "insufficient balance";
            }
        }

        return "user not found";
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
