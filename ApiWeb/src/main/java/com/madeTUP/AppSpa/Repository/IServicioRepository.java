/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.madeTUP.AppSpa.Repository;

import com.madeTUP.AppSpa.Model.Servicio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Virginia
 */
@Repository
public interface IServicioRepository extends JpaRepository<Servicio,Long>{
    
}
