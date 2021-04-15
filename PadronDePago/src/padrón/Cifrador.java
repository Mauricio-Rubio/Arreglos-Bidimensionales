package padrón;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

public class Cifrador {

    private final String key = "1010101010101010";
    private Key aesKey;
    private Cipher cifrado;

    public String cifrar(String contenido) {
        try {
            cifrado = Cipher.getInstance("AES");
            cifrado.init(Cipher.ENCRYPT_MODE, aesKey);
            byte[] mensajeEncriptado = cifrado.doFinal(contenido.getBytes());
            return Base64.encode(mensajeEncriptado);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException ex) {
            Logger.getLogger(Cifrador.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    private String descifrar(String contenido) {
        byte[] contenidoCifrado = Base64.decode(contenido.replace("\n", ""));
        try {
            cifrado = Cipher.getInstance("AES");
            cifrado.init(Cipher.DECRYPT_MODE, aesKey);
            String mensajeDesencriptado = new String(cifrado.doFinal(contenidoCifrado));
            return mensajeDesencriptado;
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException ex) {
            Logger.getLogger(Cifrador.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public Cifrador() {
        aesKey = new SecretKeySpec(key.getBytes(), "AES");

    }
}
