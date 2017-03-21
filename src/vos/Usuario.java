package vos;

import org.codehaus.jackson.annotate.JsonProperty;

public class Usuario
{
	public static final String USUARIO_REGISTRADO = "Usuario Registrado";
	
	public static final String USUARIO_NO_REGISTRADO = "Usuario No Registrado";
	
	public static final String USUARIO_ADMINISTRADOR = "Usuario Administrador";
	
	public static final String USUARIO_ORGANIZADOR = "Usuario Organizador";
	
	public static final String USUARIO_COMPANIA = "Usuario Compania";
	
	@JsonProperty( value = "identificacion" )
	protected Long identificacion;
	
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
	
	public Usuario( )
	{
		
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
