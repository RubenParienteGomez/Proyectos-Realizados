package es.iesclaradelrey.da2d1a.tiendaagtjpfrpg.security.components;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.authentication.event.LogoutSuccessEvent;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
public class LoggerRegistrosAutenticacion {
    private static final Logger logger = LoggerFactory.getLogger(LoggerRegistrosAutenticacion.class);

    @EventListener
    public void onAuthenticationFailure(AuthenticationFailureBadCredentialsEvent event) {
        logger.warn("Intento de login fallido para usuario: " + event.getAuthentication().getName());
    }

    @EventListener
    public void onAuthenticationSuccess(AuthenticationSuccessEvent event) {
        logger.info("Login exitoso para usuario: " + event.getAuthentication().getName());
    }

    @EventListener
    public void onLogoutSuccess(LogoutSuccessEvent event) {
        Authentication auth = event.getAuthentication();
        logger.info("Logout exitoso para usuario: " + auth.getName());
    }
}