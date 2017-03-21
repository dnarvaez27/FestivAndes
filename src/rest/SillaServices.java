package rest;

import java.sql.SQLException;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import tm.SillaTM;
import vos.Silla;

@Path("sillas")
public class SillaServices extends Services {
	@POST
	public Response createSilla(Silla silla) {
		SillaTM tm = new SillaTM(getPath());
		try {
			silla = tm.createSilla(silla);
		} catch (SQLException e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(silla).build();
	}

	@GET
	public Response getSilla() {
		List<Silla> list;
		SillaTM tm = new SillaTM(getPath());
		try {
			list = tm.getSillas();
		} catch (SQLException e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(list).build();
	}

	@GET
	@Path("{id_lugar}/{id_localidad}/{numero_fila}/{numero_silla}")
	public Response getSilla(@PathParam("id_lugar") Long id_lugar,@PathParam("id_localidad") Long id_localidad,@PathParam("numero_fila") Integer numero_fila,@PathParam("numero_silla") Integer numero_silla) {
		Silla pSilla;
		SillaTM tm = new SillaTM(getPath());
		try {
			pSilla = tm.getSilla(numero_silla, numero_fila, id_lugar, id_localidad);
		} catch (SQLException e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(pSilla).build();
	}
}
