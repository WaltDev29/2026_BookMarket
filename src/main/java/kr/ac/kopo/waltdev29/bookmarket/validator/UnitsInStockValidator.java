package kr.ac.kopo.waltdev29.bookmarket.validator;

import kr.ac.kopo.waltdev29.bookmarket.domain.Book;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import org.springframework.stereotype.Component;

@Component
public class UnitsInStockValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return Book.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Book book = (Book) target;
        if(book.getUnitPrice() != null && book.getUnitPrice().intValue() >= 10000 && book.getUnitsInStock() > 99) {
            errors.rejectValue("unitsInStock", "UnitsInStockValidator.message");
        }
    }
}
