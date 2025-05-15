package com.nizum.prueba.persistence.reporsitory;

import com.nizum.prueba.persistence.entity.Phone;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhoneRepository extends JpaRepository<Phone, Integer> {
}
