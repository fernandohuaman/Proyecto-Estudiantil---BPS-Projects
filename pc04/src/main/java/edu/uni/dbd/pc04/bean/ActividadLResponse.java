package edu.uni.dbd.pc04.bean;

public class ActividadLResponse {
    String codigo;
    String nombre;
    String fecha_inicio_real;
    String estado;
    int nro_obs;

    public ActividadLResponse(String codigo, String nombre, String fecha_inicio_real, String estado, int nro_obs) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.fecha_inicio_real = fecha_inicio_real;
        this.estado = estado;
        this.nro_obs = nro_obs;
    }

    public String getCodigo() {
        return codigo;
    }
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getFecha_inicio_real() {
        return fecha_inicio_real;
    }
    public void setFecha_inicio_real(String fecha_inicio_real) {
        this.fecha_inicio_real = fecha_inicio_real;
    }
    public String getEstado() {
        return estado;
    }
    public void setEstado(String estado) {
        this.estado = estado;
    }
    public int getNro_obs() {
        return nro_obs;
    }
    public void setNro_obs(int nro_obs) {
        this.nro_obs = nro_obs;
    }
}
