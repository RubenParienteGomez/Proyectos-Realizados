public interface RolUsuario {
    boolean esAdmin();

    String getNombreRol();

    boolean puedeCrearSalas();

    boolean puedeEliminarSalas();
}

class RolCliente implements RolUsuario {

    @Override
    public boolean esAdmin() {
        return false;
    }

    @Override
    public String getNombreRol() {
        return "CLIENTE";
    }

    @Override
    public boolean puedeCrearSalas() {
        return false;
    }

    @Override
    public boolean puedeEliminarSalas() {
        return false;
    }
}

class RolAdmin implements RolUsuario {

    @Override
    public boolean esAdmin() {
        return true;
    }

    @Override
    public String getNombreRol() {
        return "ADMIN";
    }

    @Override
    public boolean puedeCrearSalas() {
        return true;
    }

    @Override
    public boolean puedeEliminarSalas() {
        return true;
    }
}

