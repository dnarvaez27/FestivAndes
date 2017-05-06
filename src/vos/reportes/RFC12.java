package vos.reportes;

import vos.UsuarioRegistrado;

public class RFC12 extends UsuarioRegistrado
{
	private Integer numBoletas;
	
	public RFC12( UsuarioRegistrado usuario, int numBoletas )
	{
		this.nombre = usuario.getNombre( );
		this.identificacion = usuario.getIdentificacion( );
		this.tipoIdentificacion = usuario.getTipoIdentificacion( );
		this.email = usuario.getEmail( );
		this.password = usuario.getPassword( );
		this.rol = usuario.getRol( );
		this.idFestival = usuario.getIdFestival( );
		this.imagen = usuario.getImagen( );
		this.edad = usuario.getEdad( );
		this.numBoletas = numBoletas;
	}
	
	public Integer getNumBoletas( )
	{
		return numBoletas;
	}
	
	public void setNumBoletas( Integer numBoletas )
	{
		this.numBoletas = numBoletas;
	}
}