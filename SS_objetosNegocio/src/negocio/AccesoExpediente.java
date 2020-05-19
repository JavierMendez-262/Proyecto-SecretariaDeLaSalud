/*
 * Expediente.java
 *
 * Creado en Mayo 17, 2020. 11:53.
 */
package negocio;

/**
 * Clase que representa la autorización a un expediente para un medico
 *
 * @author JavierMéndez 00000181816 & EnriqueMendoza 00000181798
 */
public class AccesoExpediente {

    private int idExpediente;
    private int idMedico;
    private boolean autorizacion;

    /**
     * Constructor por omisión.
     */
    public AccesoExpediente() {
    }

    /**
     * Constructor que inicializa las variables de la clase al valor de sus
     * parametros.
     *
     * @param idExpediente Expediente al que el medico desea tener acceso
     * @param idMedico Medico que solicita el acceso
     * @param autorizacion true si el acceso esta autorizado, false en caso
     * contrario
     */
    public AccesoExpediente(int idExpediente, int idMedico, boolean autorizacion) {
        this.idExpediente = idExpediente;
        this.idMedico = idMedico;
        this.autorizacion = autorizacion;
    }

    /**
     * Obtiene el id del expediente
     *
     * @return Id del expediente
     */
    public int getIdExpediente() {
        return idExpediente;
    }

    /**
     * Establece el id del expediente
     *
     * @param idExpediente Id del expediente.
     */
    public void setIdExpediente(int idExpediente) {
        this.idExpediente = idExpediente;
    }

    /**
     * Obtiene el id del médico
     *
     * @return Id del médico
     */
    public int getIdMedico() {
        return idMedico;
    }

    /**
     * Establece el id del médico
     *
     * @param idMedico Id del médico
     */
    public void setIdMedico(int idMedico) {
        this.idMedico = idMedico;
    }

    /**
     * Obtiene la autorización al medico para acceso al expediente expediente.
     *
     * @return True si el acceso fue autorizado, false en caso contrario
     */
    public boolean estaAutorizado() {
        return autorizacion;
    }

    /**
     * Establece la autorización al médico para tener acceso al expediente.
     *
     * @param autorizacion true si esta autorizado, false caso contrario
     */
    public void setAutorizacion(boolean autorizacion) {
        this.autorizacion = autorizacion;
    }
    
    public boolean equals(AccesoExpediente accesoExpediente) {
        return this.idExpediente == accesoExpediente.getIdExpediente() && this.idMedico == accesoExpediente.getIdMedico();
    }

    
}
