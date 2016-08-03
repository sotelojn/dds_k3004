package dominio.entidadesExternasBancos;

import java.util.List;

public interface InterfaceBancoExt {

	public List<BancoExterno> buscarBancosExt(String nombreBanco, String Servicio);
}
