#include "ImpresorFiscal.h"

class Hasar715CLRConfigEnumFuncs
{
public:
	static ImpresorFiscal::ParametrosDeConfiguracion ObtenerParamConfiguracion(char *valor);
	static ImpresorFiscal::NumerosDeCopias ObtenerParamNumeroCopias(char *valor);
	static ImpresorFiscal::TiposDeCorteDePapel ObtenerParamTipoCorte(char *valor);
	static ImpresorFiscal::TiposDeAltoHoja ObtenerParamAltoHoja(char *valor);
	static ImpresorFiscal::TiposDeAnchoHoja ObtenerParamAnchoHoja(char *valor);
	static ImpresorFiscal::TiposDeEstacion ObtenerParamTipoEstacion(char *valor);
	static ImpresorFiscal::TiposDeModoImpresion ObtenerParamTipoModoImpresion(char *valor);
	static ImpresorFiscal::DocumentosFiscales ObtenerParamDocumentosFiscales(char *valor);
};