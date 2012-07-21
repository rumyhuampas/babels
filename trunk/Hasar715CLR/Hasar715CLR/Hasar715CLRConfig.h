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
	void ObtenerReporteStatusImpresor();
	void ObtenerReporteStatusFiscal();
	void ObtenerUltimosDocumentos();
	void EstablecerFechaHora(char *fecha, char *hora);
	void ObtenerConfiguracion();
	void ConfigurarControlador(char *parametro, char *valor);
	void Hasar715CLRConfig::ConfigurarControlador(bool Imprimir, bool Defaults, double LimiteConsumidorFinal, double LimiteTicketFactura,
		double PorcentajeIVANoInscripto, char *CopiasMaximo, bool ImprimeCambio, bool ImprimeLeyendasOpcionales, char *TipoCorte,
		bool ImprimeMarco, bool ReImprimeDocumentos, char *DescripcionMedioDePago, bool Sonido, char *AltoDeHoja, char *AnchoDeHoja,
		char *TipoEstacion, char *TipoModoImpresion);
private:
	ImpresorFiscal *impresor;
	Logger *logger;
};