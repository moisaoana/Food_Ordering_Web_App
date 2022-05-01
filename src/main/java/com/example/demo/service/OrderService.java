package com.example.demo.service;


import com.example.demo.dto.FoodItemDTO;
import com.example.demo.dto.OrderDTO;
import com.example.demo.dto.OrderWithStatusDTO;
import com.example.demo.model.FoodItem;
import com.example.demo.model.Order;
import com.example.demo.model.Restaurant;
import com.example.demo.model.User;
import com.example.demo.model.enums.Status;
import com.example.demo.repository.FoodItemRepository;
import com.example.demo.repository.OrderRepository;
import com.example.demo.repository.RestaurantRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.*;
import java.io.IOException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This class defines the service methods for the Order class.
 * @author Moisa Oana Miruna
 * @version 1.0
 * @since 25.04.2022
 */
@Service
public class OrderService {
    private final static Logger LOGGER = Logger.getLogger(OrderService.class.getName());

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private FoodItemRepository foodItemRepository;

    @Value("${spring.mail.username}")
    private String email;
    @Value("${spring.mail.password}")
    private String pass;


    /**
     * Method for placing a new order and inserting it into the DB.
     * @param orderDTO a DTO with all the information required for inserting a new order in the DB
     */
    public void addOrder(OrderDTO orderDTO){
        LOGGER.info("Adding a new order to the DB");
        Optional<Restaurant> restaurant=restaurantRepository.findByName(orderDTO.getRestaurant());
        Optional<User>admin=userRepository.findById(restaurant.get().getAdmin().getId());
        Optional<User> customer=userRepository.findByUsername(orderDTO.getCustomer());
        if(restaurant.isPresent() && customer.isPresent()) {
            Order newOrder = new Order(Status.PENDING, customer.get(), restaurant.get());
            List<FoodItem> foodItemList=new ArrayList<>();
            for(FoodItemDTO foodItemDTO: orderDTO.getItems()){
                //FoodItem foodItem=foodItemMapper.convertFromDTO(foodItemDTO, restaurant.get());
                FoodItem foodItem=foodItemRepository.findByNameAndRestaurant(foodItemDTO.getName(),restaurant.get()).get(0);
                foodItemList.add(foodItem);
            }
            newOrder.setFoodItems(foodItemList);
            for(FoodItem f: foodItemList){
                f.getOrdersSet().add(newOrder);
            }
            restaurant.get().getOrders().add(newOrder);
            customer.get().getOrders().add(newOrder);
            orderRepository.save(newOrder);
            sendEmailToAdmin(admin.get().getEmail(),newOrder);
            LOGGER.info("Insertion of new order was successful");
        }
    }

    /**
     * Method for retrieving all orders placed for a restaurant.
     * @param name a String representing the name of the restaurant.
     * @return a list of Order objects
     */
    public List<Order> getOrdersByRestaurant(String name){
        LOGGER.info("Retrieving all orders of restaurant "+name);
        return orderRepository.findAllByRestaurant_Name(name);
    }

    /**
     * Method for retrieving all orders placed by a customer.
     * @param name a String representing the username of the customer
     * @return  a list of Order objects
     */
    public List<Order> getOrdersByCustomer(String name){
        LOGGER.info("Retrieving all orders of customer "+name);
        return orderRepository.findAllByCustomer_Username(name);
    }

    /**
     * Method for updating the status of an order.
     * @param orderWithStatusDTO a DTO representing the Order object that needs to be updated, containing the new status
     */
    public void updateOrderStatus(OrderWithStatusDTO orderWithStatusDTO){
        Optional<Order> order=orderRepository.findById(orderWithStatusDTO.getId());
        if(order.isPresent()){
            LOGGER.info("Updating status of order from "+order.get().getOrderStatus()+" to "+orderWithStatusDTO.getStatus());
            order.get().setOrderStatus(orderWithStatusDTO.getStatus());
            orderRepository.save(order.get());
        }
    }

    /**
     * Method for sending an email to the administrator of a restaurant when a customer has placed a new order.
     * @param adminEmail the email address of the administrator of the restaurant that the customer has placed an order to
     * @param order an object of type Order with all the information related to the placed order
     */
    public void sendEmailToAdmin(String adminEmail,Order order) {
        LOGGER.info("Sending email to "+adminEmail);
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(email, pass);
            }
        });
        Message msg = new MimeMessage(session);
        try {
            msg.setFrom(new InternetAddress(email, false));
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(adminEmail));
            msg.setSubject("New order placed");
            StringBuilder items=new StringBuilder("<h2>Items ordered:</h2>");
            double sum=0;
            for(FoodItem item: order.getFoodItems()){
                sum+=item.getPrice();
                items.append("<p>");
                items.append(item.getName());
                items.append(" - ");
                items.append(item.getPrice());
                items.append("</p>");
            }
            items.append("<h2>Total price: ").append(sum).append(" </h2>");
            msg.setContent(items.toString(), "text/html");
            msg.setSentDate(new Date());
            Transport.send(msg);
        } catch (MessagingException e) {
            LOGGER.log(Level.SEVERE, "Exception occurred when sending email: ", e);
        }
    }
}
