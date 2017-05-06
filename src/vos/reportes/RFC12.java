package vos.reportes;
import vos.UsuarioRegistrado;

public class RFC12 extends UsuarioRegistrado {

	
	private Integer cant;
	public RFC12(UsuarioRegistrado usuario) {
		this.nombre = usuario.getNombre();
		this.identificacion = usuario.getIdentificacion();
		this.tipoIdentificacion = usuario.getTipoIdentificacion();
		this.email = usuario.getEmail();
		this.password = usuario.getPassword();
		this.rol = usuario.getRol();
		this.idFestival = usuario.getIdFestival();
		this.imagen = usuario.getImagen();
		this.edad = usuario.getEdad();
	}
	public Integer getCant() {
		return cant;
	}
	public void setCant(Integer cant) {
		this.cant = cant;
	}
	
}
