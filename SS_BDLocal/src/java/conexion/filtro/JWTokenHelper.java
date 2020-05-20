/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conexion.filtro;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author javie
 */
public class JWTokenHelper {

    private static JWTokenHelper jwTokenHelper;
    private static final long EXPIRATION_LIMIT = 150;
    private static final String PRIVATE_KEY = "secretaria";

    private JWTokenHelper() {
    }

    public static JWTokenHelper getInstance() {
        if (jwTokenHelper == null) {
            jwTokenHelper = new JWTokenHelper();
        }
        return jwTokenHelper;
    }

    public String crearToken(String nickname, String password) {
        String token = null;
        try {
            Algorithm algorithm = Algorithm.HMAC256(PRIVATE_KEY);
            token = JWT.create()
                    .withIssuer("auth0")
                    .withClaim("usr", nickname)
                    .withClaim("pass", password)
                    .withExpiresAt(getFechaDeExpiracion())
                    .sign(algorithm);
        } catch (JWTCreationException exception) {
        }
        return token;
    }

    public void verificarToken(String token) throws JWTVerificationException {
        Algorithm algorithm = Algorithm.HMAC256(PRIVATE_KEY);
        JWTVerifier verifier = JWT.require(algorithm)
                .withIssuer("auth0").build();

    }

    private Date getFechaDeExpiracion() {
        long currentTimeInMillis = System.currentTimeMillis();
        long expMilliSeconds = TimeUnit.MINUTES.toMillis(EXPIRATION_LIMIT);
        return new Date(currentTimeInMillis + expMilliSeconds);
    }
}
