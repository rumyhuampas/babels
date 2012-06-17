#include "ImpresorFiscal.h"
#include "Logger.h"

class Hasar715CLRConfig
{
public:
	Hasar715CLRConfig(ImpresorFiscal *imp, Logger *log);

	void EstablecerPuertoSerie(int puerto);
	void EstablecerManejadorDeEventos();
	void EstablecerInterlineadoDeImpresion(int value);
	void EstadoInterno();
private:
	ImpresorFiscal *impresor;
	Logger *logger;
};