/*
 * Usuario.java
 *
 * Documentado en Mayo 18, 2020. 17:31.
 */
package negocio;

/**
 * Clase entidad de Usuario.
 *
 * @author JavierMéndez 00000181816 & EnriqueMendoza 00000181798
 */
public class Usuario {

    private String nickname;
    private String password;
    private int id;
    private boolean esMedico;

    /**
     * Constructor por omisión.
     */
    public Usuario() {

    }

    /**
     * Constructor que inicializa los atributos de la clase.
     *
     * @param nickname Nickname del usuario
     * @param password Contraseña del usuario
     * @param id ID del usuario
     * @param esMedico Es o no es médico
     */
    public Usuario(String nickname, String password, int id, boolean esMedico) {
        this.nickname = nickname;
        this.password = password;
        this.id = id;
        this.esMedico = esMedico;
    }

    /**
     * Método que regresa el nickname del usuario.
     *
     * @return nickname
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * Método que establece el nickname del usuario.
     *
     * @param nickname Nickname del usuario
     */
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    /**
     * Método que regresa la contraseña del usuario.
     *
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Método que establece la contraseña del usuario.
     *
     * @param password Contraseña del usuario
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Método que regresa el ID del usuario.
     *
     * @return id
     */
    public int getId() {
        return id;
    }

    /**
     * Método que establece el ID del usuario.
     *
     * @param id ID del usuario
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Método que regresa si un usuario es médico o no.
     *
     * @return esMedico
     */
    public boolean isEsMedico() {
        return esMedico;
    }

    /**
     * Método que establece si un usuario es médico o no.
     *
     * @param esMedico Usuario médico o no
     */
    public void setEsMedico(boolean esMedico) {
        this.esMedico = esMedico;
    }

    /**
     * Método que regresa una cadena con los atributos de la clase.
     *
     * @return Cadena con los atributos de la clase
     */
    @Override
    public String toString() {
        return "Usuario | " + "Nickname: " + nickname + ", Password: " + password + ", ID: " + id + ", Es médico: " + esMedico;
    }
}
