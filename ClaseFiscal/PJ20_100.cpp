#include "PJ20_100.h"


std::string ImpresorFiscalPJ20_100::Version ("OLIVETTI Artjet 20-F v1.00" /*" - 15/12/2000" */);


// ############################################################ //
//																//
//			M E T O D O S      L O C A L E S					//
//																//
// ############################################################ //

//
// Constructor
//
ImpresorFiscalPJ20_100::ImpresorFiscalPJ20_100 ()
{
	// Inicializaci�n de variables de uso general

	// Inicializaci�n de variables de tama�o de campos
	PrintNonFiscalTextTicketSize	= 120;	// Por seguridad !!!	
	PrintFiscalTextTicketSize		= 50;	// Por seguridad !!!
	PrintItemTextTicketSize			= 50;	// Por seguridad !!!
	RemitoCantDecimals				= 4;
	ReciboTextTicketSize			= 106;	// Por seguridad !!!
}


//
// Obtener la Descripci�n del Modelo Seleccionado
//
std::string
ImpresorFiscalPJ20_100::DescripcionModelo () const
{
	return 	(Version);
}


// ############################################################ //
//																//
//			M E T O D O S   G E N E R A L E S					//
//																//
// ############################################################ //

//
// Baja Fiscal
//
void
ImpresorFiscalPJ20_100::BajaFiscal (const std::string &NumeroDeSerie) throw(Excepcion)
{
    // printf ("Comando BajaFiscal ejecutado en PJ20_100\n");

	// Enviamos el comando fiscal y evaluamos los status
	EnviarComandoFiscal(OpCode(CMD_KILL_EPROM));
}


//
// Cambiar Fecha de Inicio de Actividades
//
void
ImpresorFiscalPJ20_100::CambiarFechaInicioActividades (const FECHA & /* Fecha */) throw(Excepcion)
{
    // printf ("Comando CambiarFechaInicioActividades ejecutado en PJ20_100\n");

	throw Excepcion(Excepcion::IMPRESOR_FISCAL_ERROR_NO_IMPLEMENTADO, "Cambiar Fecha de Inicio de Actividades");
}


//
// P�ginas de Ultimo Documento
//
// Nota: este m�todo no es v�lido para este modelo.
//
unsigned
ImpresorFiscalPJ20_100::PaginasDeUltimoDocumento () throw(Excepcion)
{
    // printf ("Comando PaginasDeUltimoDocumento ejecutado en PJ20_100\n");

	throw Excepcion(Excepcion::IMPRESOR_FISCAL_ERROR_NO_IMPLEMENTADO, "P�ginas de Ultimo Documento");
}


//
// CAI del Ultimo Documento
//
// Nota: este m�todo no es v�lido para este modelo.
//
void
ImpresorFiscalPJ20_100::CAIUltimoDocumentoA (RTA_CAIUltimoDocumentoA * /* R */) throw (Excepcion)
{
    // printf ("Comando CAIUltimoDocumento ejecutado en PJ20_100\n");
	
	throw Excepcion(Excepcion::IMPRESOR_FISCAL_ERROR_NO_IMPLEMENTADO, "CAI del Ultimo Documento");
}


// ############################################################ //
//																//
//			M E T O D O S   D E    C O N F I G U R A C I O N	//
//																//
// ############################################################ //



// ############################################################ //
//																//
//			M E T O D O S   D E    R E P O R T E S				//
//																//
// ############################################################ //



// ############################################################ //
//																//
//			M E T O D O S   D E    D F							//
//																//
// ############################################################ //

//
// Abrir DF
// Env�a el comando de Apertura de DF en la estaci�n indicada
//
void
ImpresorFiscalPJ20_100::AbrirDF (DocumentosFiscales Tipo) throw (Excepcion)
{
    // printf ("Comando AbrirDF ejecutado en PJ20_100\n");

	// Verificamos si el Tipo de DF es v�lido para este modelo
	if ( !(	Tipo == FACTURA_A		||
			Tipo == FACTURA_B		||
			Tipo == NOTA_DEBITO_A	||
			Tipo == NOTA_DEBITO_B	||
			Tipo == RECIBO_A		||
			Tipo == RECIBO_B ) )
		throw Excepcion(Excepcion::IMPRESOR_FISCAL_ERROR_NO_IMPLEMENTADO, "Abrir DF");

	// Ejecutamos el m�todo de la SuperClase 'ImpresorFiscal16Bits'
	ImpresorFiscal16Bits::AbrirDF (Tipo);
}


//
// Subtotal
//
void
ImpresorFiscalPJ20_100::Subtotal (bool Imprime, RTA_Subtotal *R) throw (Excepcion)
{
    // printf ("Comando Subtotal ejecutado en PJ20_100\n");
	
	// Ejecutamos el m�todo de la superclase 'ImpresorFiscal'
	ImpresorFiscal::Subtotal (Imprime, R);

	// Completamos los par�metros correspondientes a los campos
	// de respuesta particulares que se agregan en este modelo
	// m�s alla de los comunes a todos los modelos
	if (R != NULL)
		R->MontoIVANoInscripto		= RespuestaDouble (6, "Subtotal");
}


//
// Cerrar DF
//
unsigned
ImpresorFiscalPJ20_100::CerrarDF (unsigned Copias) throw (Excepcion)
{
    // printf ("Comando CerrarDF ejecutado en PJ20_100\n");

	// Verificamos que la cantidad de copias sea 1 (este
	// modelo no soporta la indicaci�n de copias en el
	// comando de cierre).
	if (Copias != 1)
		throw Excepcion(Excepcion::IMPRESOR_FISCAL_ERROR_PARAMETRO_NO_VALIDO, "Cerrar DF");

	// Ejecutamos el m�todo de la superclase 'ImpresorFiscal16Bits'.
	return ImpresorFiscal16Bits::CerrarDF (Copias);
}


// ############################################################ //
//																//
//			M E T O D O S   D E    D N F						//
//																//
// ############################################################ //

//
// Abrir DNF
// Env�a el comando de Apertura de DNF en la estaci�n indicada
//
void
ImpresorFiscalPJ20_100::AbrirDNF (TiposDeEstacion EstacionDeImpresion) throw (Excepcion)
{
    // printf ("Comando AbrirDNF ejecutado en PJ20_100\n");

	// Ejecutamos el m�todo de la SuperClase 'ImpresorFiscal'.
	ImpresorFiscal::AbrirDNF (ESTACION_SLIP);
}


//
// Cerrar DNF
//
void
ImpresorFiscalPJ20_100::CerrarDNF (unsigned Copias) throw (Excepcion)
{
    // printf ("Comando CerrarDNF ejecutado en PJ20_100\n");

	// Verificamos que la cantidad de copias sea 1 (este
	// modelo no soporta la indicaci�n de copias en el
	// comando de cierre).
	if (Copias != 1)
		throw Excepcion(Excepcion::IMPRESOR_FISCAL_ERROR_PARAMETRO_NO_VALIDO, "Cerrar DF");

	// Ejecutamos el m�todo de la superclase 'ImpresorFiscal16Bits'.
	ImpresorFiscal16Bits::CerrarDNF (Copias);
}


// ############################################################ //
//																//
//			M E T O D O S   D E    D N F H						//
//																//
// ############################################################ //

//
// Abrir DNFH
// Env�a el comando de Apertura de DNFH en la estaci�n indicada
//
void
ImpresorFiscalPJ20_100::AbrirDNFH (DocumentosNoFiscalesHomologados Tipo, const std::string &Nro) throw (Excepcion)
{
    // printf ("Comando AbrirDNFH ejecutado en PJ20_100\n");

	// Verificamos si el Tipo de DNFH es v�lido para este modelo
	if ( !(	Tipo == NOTA_CREDITO_A	 ||
			Tipo == NOTA_CREDITO_B	 ||
			Tipo == REMITO			 ||
			Tipo == RECIBO_X		 ||
			Tipo == ORDEN_SALIDA	 ||
			Tipo == RESUMEN_CUENTA	 ||
			Tipo == CARGO_HABITACION ||
			Tipo == COTIZACION ) )
		throw Excepcion(Excepcion::IMPRESOR_FISCAL_ERROR_NO_IMPLEMENTADO, "Abrir DNFH");

	// Ejecutamos el m�todo de la SuperClase 'ImpresorFiscal16Bits'
	ImpresorFiscal16Bits::AbrirDNFH (Tipo, Nro);
}


//
// Imprimir Informaci�n en DNFH
//
// Nota: esta m�todo no es v�lido para este modelo.
//
void
ImpresorFiscalPJ20_100::ImprimirInfoEnDNFH (const unsigned /* IDCampo */,
											const std::string & /* Texto */,
											double /* Cantidad */) throw (Excepcion)
{
    // printf ("Comando ImprimirInfoEnDNFH ejecutado en PJ20_100\n");

	throw Excepcion(Excepcion::IMPRESOR_FISCAL_ERROR_NO_IMPLEMENTADO, "Imprimir Informaci�n en DNFH");
}


//
// Cerrar DNFH
//
unsigned
ImpresorFiscalPJ20_100::CerrarDNFH (unsigned Copias) throw (Excepcion)
{
    // printf ("Comando CerrarDNFH ejecutado en PJ20_100\n");

	// Verificamos que la cantidad de copias sea 1 (este
	// modelo no soporta la indicaci�n de copias en el
	// comando de cierre).
	if (Copias != 1)
		throw Excepcion(Excepcion::IMPRESOR_FISCAL_ERROR_PARAMETRO_NO_VALIDO, "Cerrar DF");

	// Ejecutamos el m�todo de la superclase 'ImpresorFiscal16Bits'.
	return ImpresorFiscal16Bits::CerrarDNFH (Copias);
}


//
// Imprimir Voucher de Tarjeta
//
void
ImpresorFiscalPJ20_100::ImprimirVoucher (const std::string & /* NombreCliente */,
									const std::string & /* NombreTarjeta */,
									TiposDeVouchers /* TipoVoucher */,
									const std::string & /* NumeroDeTarjeta */,
									FECHA /* FechaDeVencimiento */,
									TiposDeTarjetas /* TipoTarjeta */,
									unsigned /* Cuotas */,
									const std::string & /* CodigoDeComercio */,
									unsigned /* NumeroDeTerminal */,
									unsigned /* NumeroDeLote */,
									unsigned /* NumeroCupon */,
									TiposDeIngresoDeTarjeta /* TipoIngreso */,
									TiposOperacionDeTarjeta /* TipoOperacion */,
									unsigned /* NumeroAutorizacion */,
									const std::string & /* Monto */,
									const std::string & /* NumeroComprobanteAsociado */,
									unsigned /* Copias */,
									const std::string & /* Vendedor */,
									TiposDeEstacion /* Estacion */) throw (Excepcion)
{
    // printf ("Comando ImprimirVoucher ejecutado en PJ20_100\n");

	throw Excepcion(Excepcion::IMPRESOR_FISCAL_ERROR_NO_IMPLEMENTADO, "Imprimir Voucher de Tarjeta");
}


