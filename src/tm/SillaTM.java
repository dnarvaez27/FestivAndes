package tm;

import java.sql.SQLException;
import java.util.List;

import dao.DAOSilla;
import vos.Silla;

public class SillaTM extends TransactionManager {
	public SillaTM(String contextPathP) {
		super(contextPathP);
	}

	public Silla createSilla(Silla silla) throws SQLException {
		Silla pSilla;
		DAOSilla dao = new DAOSilla();
		try {
			this.connection = getConnection();
			dao.setConnection(this.connection);
			pSilla = dao.createSilla(silla);
			connection.commit();
		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			closeDAO(dao);
		}
		return pSilla;
	}

	public List<Silla> getSillas() throws SQLException {
		List<Silla> list;
		DAOSilla dao = new DAOSilla();
		try {
			this.connection = getConnection();
			dao.setConnection(this.connection);
			list = dao.getSillas();
			connection.commit();
		} catch (SQLException e) {
			System.err.println("SQLException: " + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException: " + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			closeDAO(dao);
		}
		return list;
	}

	public Silla getSilla(Integer num_silla, Integer num_fila, Long id_lugar, Long id_localidad) throws SQLException {
		Silla pSilla;
		DAOSilla dao = new DAOSilla();
		try {
			this.connection = getConnection();
			dao.setConnection(this.connection);
			pSilla = dao.getSilla(num_silla, num_fila, id_lugar, id_localidad);
			connection.commit();
		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			closeDAO(dao);
		}
		return pSilla;
	}

	public Silla updateSilla(Integer num_silla, Integer num_fila, Long id_lugar, Long id_localidad, Silla silla)
			throws SQLException {
		Silla pSilla;
		DAOSilla dao = new DAOSilla();
		try {
			this.connection = getConnection();
			dao.setConnection(this.connection);
			pSilla = dao.updateSilla(num_silla, num_fila, id_lugar, id_localidad, silla);
			connection.commit();
		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			closeDAO(dao);
		}
		return pSilla;
	}

	public void deleteSilla(Integer num_silla, Integer num_fila, Long id_lugar, Long id_localidad) throws SQLException {
		DAOSilla dao = new DAOSilla();
		try {
			this.connection = getConnection();
			dao.setConnection(this.connection);
			dao.deleteSilla(num_silla, num_fila, id_lugar, id_localidad);
		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			closeDAO(dao);
		}
	}
}
