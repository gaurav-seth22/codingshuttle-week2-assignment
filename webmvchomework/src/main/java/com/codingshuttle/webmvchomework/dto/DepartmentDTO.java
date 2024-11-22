package com.codingshuttle.webmvchomework.dto;

import com.codingshuttle.webmvchomework.annotations.Password;
import com.codingshuttle.webmvchomework.annotations.PrimeNumber;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.validator.constraints.CreditCardNumber;
import org.hibernate.validator.constraints.URL;

import java.time.LocalDateTime;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentDTO {

    private Long id;

    @NotNull(message = "{NotNull.department.title}")
    @Size(min = 3, max = 50, message = "{Size.department.title}")
    private String title;


    @NotNull(message = "Active status cannot be null")
    @AssertTrue(message = "{AssertTrue.department.isActive}")
    @JsonProperty("isActive")
    private Boolean isActive;

    @PastOrPresent(message = "Created date must be in the past or present")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt;

    @URL(message = "Documentation URL must be a valid URL")
    private String Url;

    @CreditCardNumber(message = "Invalid credit card number.")
    private String creditCardNumber;

    @PrimeNumber(message = "The number needs to a prime number.")
    private Integer primeNumber;

    @Password(message = "Password must contain an uppercase letter, a lowercase letter, a special character, and  at least 10 characters long.")
    private String password;
}
