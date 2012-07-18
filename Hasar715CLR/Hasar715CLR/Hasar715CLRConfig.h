#include "ImpresorFiscal.h"
#include "Logger.h"

class Hasar715CLRConfig
{
public:
	Hasar715CLRConfig(ImpresorFiscal *imp, Logger *log);

	void EstablecerPuertoSerie(int puerto);
	void EstablecerManejadorDeEventos();
	void EstablecerInterlineadoDeImpresion(int value);
	void ObtenerEstadoInterno();
	void ObtenerDatosDeInicializacion();
	void ObtenerDatosMemoriaDeTrabajo();
	void CambiarResponsabilidadIVA();
	void CambiarCodigoIngresosBrutos(char *codigo);
	void CambiarFechaInicioActividades(char *fechaInicio);
	void ObtenerUltimosDocumentos();
	void EstablecerFechaHora(char *fecha, char *hora);
	void ObtenerConfiguracionCF();
private:
	ImpresorFiscal *impresor;
	Logger *logger;
};