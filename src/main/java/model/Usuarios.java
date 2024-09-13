package model;

import java.sql.Date;

public class Usuarios {
    
    private int id;
    private String num_serie;
    private String nombre;
    private String apellido;
    private String email;
    private String telefono;
    private String password;
    private String guardiasRealizadas;
    private String administracion;
    private Date fechaNacimiento;

    // Getters y setters
    public String getNum_serie() {
        return num_serie;
    }

    public void setNum_serie(String num_serie) {
        this.administracion = num_serie;
    }
    
    public String getAdministracion() {
        return administracion;
    }

    public void setAdministracion(String administracion) {
        this.administracion = administracion;
    }

    public String getTelefono(){
        return telefono;
    }
    
    public void setTelefono(String telefono){
        this.telefono = telefono;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getGuardiasRealizadas() {
        return guardiasRealizadas;
    }

    public void setGuardiasRealizadas(String guardiasRealizadas) {
        this.guardiasRealizadas = guardiasRealizadas;
    }
    
    
    
}
