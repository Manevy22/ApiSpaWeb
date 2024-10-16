/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.madeTUP.AppSpa.Service;

import com.madeTUP.AppSpa.DTO.SesionDTO;
import com.madeTUP.AppSpa.DTO.SesionPersonalDTO;
import com.madeTUP.AppSpa.Model.Cliente;
import com.madeTUP.AppSpa.Model.Servicio;
import com.madeTUP.AppSpa.Model.Sesion;
import com.madeTUP.AppSpa.Repository.ISesionRepository;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Virginia
 */
@Service
public class SesionService implements ISesionService {

    @Autowired
    private ISesionRepository sesionrepo;
    @Autowired IClienteService clienteservis;

    @Override
    public boolean cancelarAsistencia(Long id) {
        // Buscar la sesión por ID
        Optional<Sesion> sesionOptional = sesionrepo.findById(id);
        if (sesionOptional.isPresent()) {
            Sesion sesion = sesionOptional.get();
            sesion.setAsistencia("RECHAZADO"); // Actualizar asistencia a 0 (cancelado)
            sesionrepo.save(sesion); // Guardar los cambios en la base de datos
            return true;
        } else {
            return false;
        }
    }
    @Override
    public List<Sesion> getServicio() {
        return sesionrepo.findAll();
    }

    @Override
    public void saveSesion(Sesion sesion) {
        sesionrepo.save(sesion);

    }

    @Override
    public void deleteSesion(Long id) {
        sesionrepo.deleteById(id);
    }

    @Override
    public Sesion findSesion(Long id) {
        return sesionrepo.findById(id).orElse(null);
    }

    @Override
    public void editSesion(Long id, Servicio servicio, Cliente cliente, LocalDateTime fecha, Double costo, String  asistencia) {
        Sesion sesion = this.findSesion(id);
        sesion.setServicio(servicio);
        sesion.setCliente(cliente);
        sesion.setFecha(fecha);
        sesion.setCosto(costo);
        sesion.setAsistencia(asistencia);
        this.saveSesion(sesion);
    }

    @Override
    public void editSesionII(Sesion sesion) {
        this.saveSesion(sesion);
    }



    @Override
    public List<SesionPersonalDTO> getSesionFecha(LocalDateTime localDate) {
        // Suponiendo que tienes un método en el repositorio para encontrar sesiones por fecha
        List<Sesion> sesiones = this.getServicio();

        List <SesionPersonalDTO> s= new ArrayList<>();
        for(Sesion sesion:sesiones){
            if (sesion.getFecha().equals(localDate)){
                SesionPersonalDTO n=new SesionPersonalDTO();
                n.setId(sesion.getId());
                n.setIdCliente(sesion.getId_Cliente().getId());
                n.setNombreCliente(sesion.getId_Cliente().getNombre());
                n.setServicio(sesion.getId_Servicio().getNombreServicio());
                n.setFecha(sesion.getFecha());
                n.setCosto(sesion.getCosto());
                n.setAsistencia(sesion.getAsistencia());
                s.add(n);
            }
        }
        return s;
    }
      @Override
    public List<SesionDTO> getSesionCliente(Long id_Cliente) {
        // Suponiendo que tienes un método en el repositorio para encontrar 
        //sesiones por fecha
        Cliente cliente= clienteservis.findCliente(id_Cliente);
        List<Sesion> sesiones = cliente.getListaSesiones();

        List <SesionDTO> s= new ArrayList<>();
        
        for(Sesion sesion:sesiones){
            SesionDTO ses=new SesionDTO(sesion.getServicio().getNombreServicio(),
                    sesion.getFecha(),sesion.getCosto(),sesion.getAsistencia());
                    s.add(ses);
            }
        
        return s;
    }
}



