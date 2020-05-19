/*
 * Expediente.java
 *
 * Documentado en Mayo 17, 2020. 11:53.
 */
package negocio;

/**
 * Clase entidad de Expediente.
 *
 * @author JavierMéndez 00000181816 & EnriqueMendoza 00000181798
 */
public class Expediente {

    private int id;
    private String nombre;
    private char sexo;
    private int edad;
    private String domicilio;

    /**
     * Constructor por omisión.
     */
    public Expediente() {

    }

    /**
     * Constructor que inicializa los atributos de la clase.
     *
     * @param id ID de paciente
     * @param nombre Nombre del paciente
     * @param sexo Sexo del paciente
     * @param edad Edad del paciente
     * @param domicilio Domicilio del paciente
     */
    public Expediente(int id, String nombre, char sexo, int edad, String domicilio) {
        this.id = id;
        this.nombre = nombre;
        this.sexo = sexo;
        this.edad = edad;
        this.domicilio = domicilio;
    }

    /**
     * Método que regresa el id del paciente.
     *
     * @return id
     */
    public int getId() {
        return id;
    }

    /**
     * Método que establece el id del paciente.
     *
     * @param id ID del paciente
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Método que regresa el nombre del paciente.
     *
     * @return nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Método que establece el nombre dle paciente
     *
     * @param nombre Nombre del paciente
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Método que regresa el sexo del paciente.
     *
     * @return sexo
     */
    public char getSexo() {
        return sexo;
    }

    /**
     * Método que establece el sexo del paciente.
     *
     * @param sexo Sexo del paciente
     */
    public void setSexo(char sexo) {
        this.sexo = sexo;
    }

    /**
     * Método que regresa la edad del paciente.
     *
     * @return edad
     */
    public int getEdad() {
        return edad;
    }

    /**
     * Método que establece la edad del paciente.
     *
     * @param edad Edad del paciente
     */
    public void setEdad(int edad) {
        this.edad = edad;
    }

    /**
     * Método que regresa el domicilio del paciente.
     *
     * @return domicilio
     */
    public String getDomicilio() {
        return domicilio;
    }

    /**
     * Método que establece el domicilio del paciente.
     *
     * @param domicilio Domicilio del paciente
     */
    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    /**
     * Método que regresa una cadena con los atributos de la clase.
     *
     * @return Cadena con los atributos de la clase
     */
    @Override
    public String toString() {
        return "Expediente | " + "ID: " + id + ", Nombre: " + nombre + ", Sexo: " + sexo + ", Edad: " + edad + ", Domicilio: " + domicilio;
    }
}
