package kr.ac.kopo.waltdev29.bookmarket.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import kr.ac.kopo.waltdev29.bookmarket.domain.*;
import kr.ac.kopo.waltdev29.bookmarket.service.BookService;
import kr.ac.kopo.waltdev29.bookmarket.service.CartService;
import kr.ac.kopo.waltdev29.bookmarket.service.OrderProService;
import kr.ac.kopo.waltdev29.bookmarket.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @Autowired
    private CartService cartService;

    @Autowired
    private OrderProService orderProService;

    @Autowired
    private BookService bookService;

    List<Book> listOfBooks;
    Order order;

    @GetMapping("/{cartId}")
    public String requestCartList(@PathVariable String cartId, Model model) {
        Cart cart = cartService.read(cartId);

        order = new Order();
        listOfBooks = new ArrayList<Book>();

        for (CartItem item : cart.getCartItems().values()) {
            OrderItem orderItem = new OrderItem();

            Book book = item.getBook();

            listOfBooks.add(book);

            orderItem.setBookId(book.getBookId());
            orderItem.setQuantity(item.getQuantity());
            orderItem.setTotalPrice(item.getTotalPrice());

            order.getOrderItems().put(book.getBookId(), orderItem);
        }

        order.setCustomer(new Customer());
        order.setShipping(new Shipping());
        order.setGrandTotal(cart.getGrandTotal());


        return "redirect:/order/orderCustomerInfo";
    }

    @GetMapping("/orderCustomerInfo")
    public String requestCustomerInfoForm(Model model) {
        model.addAttribute("customer", order.getCustomer());
        return "orderCustomerInfo";
    }

    @PostMapping("/orderCustomerInfo")
    public String requestCustomerInfo(@ModelAttribute Customer customer, Model model) {
        order.setCustomer(customer);
        return "redirect:/order/orderShippingInfo";
    }

    @GetMapping("/orderShippingInfo")
    public String RequestShippingInfoForm(Model model) {
        model.addAttribute("shipping", order.getShipping());
        return "orderShippingInfo";
    }

    @PostMapping("/orderShippingInfo")
    public String RequestShippingInfo(@ModelAttribute Shipping shipping, Model model, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) return "orderShippingInfo";

        order.setShipping(shipping);
        return "redirect:/order/orderConfirmation";
    }

    @GetMapping("/orderConfirmation")
    public String requestOrderConfirmation(Model model) {
        model.addAttribute("bookList", listOfBooks);
        model.addAttribute("order", order);
        return"orderConfirmation";
    }

    @GetMapping("/orderFinished")
    public String requestOrderFinished(HttpServletRequest request, Model model) {
        orderService.saveOrder(order);
        model.addAttribute("order", order);
        HttpSession session = request.getSession();

        if (session != null) session.invalidate();

        return "orderFinished";
    }

    @GetMapping("/orderCancelled")
    public String requestCancelled(HttpServletRequest request) {
        HttpSession session = request.getSession();

        if (session != null) session.invalidate();

        return "orderCancelled";
    }
}
