#ifndef IMPRESOR_FISCAL_P435_100_H
#define IMPRESOR_FISCAL_P435_100_H

#include "P435.h"

class ImpresorFiscalP435_100 : public ImpresorFiscalP435
{
public:

	// Constructor
	ImpresorFiscalP435_100();

	// String de Versi�n
	static std::string Version;

	// M�todos locales
	std::string DescripcionModelo () const;

	// M�todos generales

	// M�todos de configuraci�n

	// M�todos de reportes

	// M�todos de documentos fiscales

	// M�todos de documentos no fiscales

	// M�todos de documentos no fiscales homologados
	void AbrirDNFH (DocumentosNoFiscalesHomologados Tipo, const std::string &Nro = "") throw (Excepcion);

protected:

}; 

#endif


