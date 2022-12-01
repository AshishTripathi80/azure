package org.nagarro.product.catalouge.repository;

import org.nagarro.product.catalouge.model.Availability;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AvailableRepo extends JpaRepository<Availability, String> {

    Availability findByCodeAndPinCode(String code, Long pinCode);

}
