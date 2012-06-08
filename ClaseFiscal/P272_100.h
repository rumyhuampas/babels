#ifndef IMPRESOR_FISCAL_P272_100_H
#define IMPRESOR_FISCAL_P272_100_H

#include "ImpresorFiscal8Bits.h"

class ImpresorFiscalP272_100 : public ImpresorFiscal8Bits
{
public:

	// Constructor
        ImpresorFiscalP272_100();

	// String de Versi�n
	static std::string Version;

	// M�todos locales
	std::string DescripcionModelo () const;

	// M�todos generales
	std::string ObtenerVersionDelControlador () throw (Excepcion);

	void BajaFiscal (const std::string &NumeroDeSerie = "") throw(Excepcion);

	void CambiarCodigoIngresosBrutos (const std::string &CodigoIngBr) throw(Excepcion);
	void CambiarFechaInicioActividades (const FECHA &Fecha) throw(Excepcion);

	TiposDeDocumento DocumentoEnCurso () throw(Excepcion);
	std::string DescripcionDocumentoEnCurso () throw(Excepcion);
	bool UltimoDocumentoFueCancelado () throw(Excepcion);

	unsigned PrimerNumeroDeDocumentoActual () throw(Excepcion);

	void CancelarComprobanteFiscal () throw(Excepcion);
	void CancelarComprobante () throw(Excepcion);

	// M�todos de configuraci�n
	std::string ObtenerNombreDeFantasia (unsigned Linea) throw (Excepcion);

	void ConfigurarControladorPorBloque (
						double LimiteConsumidorFinal,
						double LimiteTicketFactura,
						double PorcentajeIVANoInscripto,
						NumerosDeCopias TipoDeCopiasMaximo,
						bool ImpresionCambio,
						bool ImpresionLeyendasOpcionales,
						TiposDeCorteDePapel TipoDeCorte) throw (Excepcion);

	// M�todos de reportes
	void ReporteZ (RTA_ReporteZX *R = NULL) throw (Excepcion);
	void ReporteX (RTA_ReporteZX *R = NULL) throw (Excepcion);

	// M�todos de documentos fiscales
	void AbrirDF (DocumentosFiscales Tipo) throw (Excepcion);

	// M�todos de documentos no fiscales
	void AbrirDNF (TiposDeEstacion EstacionDeImpresion) throw (Excepcion);
	void Subtotal (bool Imprime, RTA_Subtotal *R = NULL) throw (Excepcion);

	// M�todos de documentos no fiscales homologados
	void ImprimirVoucher (const std::string &NombreCliente,
						const std::string &NombreTarjeta,
						TiposDeVouchers TipoVoucher,
						const std::string &NumeroDeTarjeta,
						FECHA FechaDeVencimiento,
						TiposDeTarjetas TipoTarjeta,
						unsigned Cuotas,
						const std::string &CodigoDeComercio,
						unsigned NumeroDeTerminal,
						unsigned NumeroDeLote,
						unsigned NumeroCupon,
						TiposDeIngresoDeTarjeta TipoIngreso,
						TiposOperacionDeTarjeta TipoOperacion,
						unsigned NumeroAutorizacion,
						const std::string &Monto,
						const std::string &NumeroComprobanteAsociado,
						unsigned Copias = 1,
						const std::string &Vendedor = "",
						TiposDeEstacion Estacion = ImpresorFiscal::ESTACION_TICKET) throw (Excepcion);

protected:

}; 

#endif


