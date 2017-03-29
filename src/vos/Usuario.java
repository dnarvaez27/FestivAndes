package vos;

import org.codehaus.jackson.annotate.JsonProperty;

public class Usuario
{
	public static final String USUARIO_REGISTRADO = "Usuario Registrado";
	
	public static final String USUARIO_NO_REGISTRADO = "Usuario No-Registrado";
	
	public static final String USUARIO_ADMINISTRADOR = "Usuario Administrador";
	
	public static final String USUARIO_ORGANIZADOR = "Usuario Organizador";
	
	public static final String USUARIO_COMPANIA = "Compania De Teatro";
	
	public static final Usuario UNREGISTERED_USER = new Usuario( -1L, "NR", null, "1234", USUARIO_NO_REGISTRADO, USUARIO_NO_REGISTRADO, 1L );
	
	@JsonProperty( value = "identificacion" )
	protected Long identificacion;
	
	@JsonProperty( value = "tipoIdentificacion" )
	protected String tipoIdentificacion;
	
	@JsonProperty( value = "email" )
	protected String email;
	
	@JsonProperty( value = "password" )
	protected String password;
	
	@JsonProperty( value = "nombre" )
	protected String nombre;
	
	@JsonProperty( value = "rol" )
	protected String rol;
	
	@JsonProperty( value = "idFestival" )
	protected Long idFestival;
	
	public Usuario( )
	{
		
	}
	
	public Usuario( Long identificacion, String tipoIdentificacion, String email, String password, String nombre, String rol, Long idFestival )
	{
		this.identificacion = identificacion;
		this.tipoIdentificacion = tipoIdentificacion;
		this.email = email;
		this.password = password;
		this.nombre = nombre;
		this.rol = rol;
		this.idFestival = idFestival;
	}
	
	/**
	 * Retrieves the tipoIdentificacion of the Usuario
	 *
	 * @return The tipoIdentificacion of the Usuario
	 */
	public String getTipoIdentificacion( )
	{
		return tipoIdentificacion;
	}
	
	/**
	 * Updates the tipoIdentificacion of the Usuario by the one given by parameter
	 *
	 * @param tipoIdentificacion The new tipoIdentificacion of the Usuario
	 */
	public void setTipoIdentificacion( String tipoIdentificacion )
	{
		this.tipoIdentificacion = tipoIdentificacion;
	}
	
	public Long getIdentificacion( )
	{
		return identificacion;
	}
	
	public void setIdentificacion( Long identificacion )
	{
		this.identificacion = identificacion;
	}
	
	public String getEmail( )
	{
		return email;
	}
	
	public void setEmail( String email )
	{
		this.email = email;
	}
	
	public String getPassword( )
	{
		return password;
	}
	
	public void setPassword( String password )
	{
		this.password = password;
	}
	
	public String getNombre( )
	{
		return nombre;
	}
	
	public void setNombre( String nombre )
	{
		this.nombre = nombre;
	}
	
	public String getRol( )
	{
		return rol;
	}
	
	public void setRol( String rol )
	{
		this.rol = rol;
	}
	
	public Long getIdFestival( )
	{
		return idFestival;
	}
	
	public void setIdFestival( Long idFestival )
	{
		this.idFestival = idFestival;
	}
	
	//	public Usuario(
	//			@JsonProperty( value = "identificacion" ) Long identificacion,
	//			@JsonProperty( value = "email" ) String email,
	//			@JsonProperty( value = "password" ) String password,
	//			@JsonProperty( value = "nombre" ) String nombre,
	//			@JsonProperty( value = "rol" ) String rol,
	//			@JsonProperty( value = "idFestival" ) Long idFestival )
	//	{
	//		super( );
	//		this.identificacion = identificacion;
	//		this.email = email;
	//		this.password = password;
	//		this.nombre = nombre;
	//		this.rol = rol;
	//		this.idFestival = idFestival;
	//	}
	
}
