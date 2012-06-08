#include "P435.h"



// ############################################################ //
//																//
//			M E T O D O S      L O C A L E S					//
//																//
// ############################################################ //

//
// Constructor
//
ImpresorFiscalP435::ImpresorFiscalP435 ()
{
	// Inicializaci�n de variables de uso general

	// Inicializaci�n de variables de tama�o de campos
	PrintNonFiscalTextTicketSize	= 45;
	HeaderTrailerTicketSize			= 45;
	PrintFiscalTextTicketSize		= 31;
	PrintItemTextTicketSize			= 23;
	RemitoCantDecimals				= 10;
	ReciboTextTicketSize			= 40;
}


// ############################################################ //
//																//
//			M E T O D O S   G E N E R A L E S					//
//																//
// ############################################################ //

//
// Obtener Datos de Memoria de Trabajo
//
void
ImpresorFiscalP435::ObtenerDatosMemoriaDeTrabajo (RTA_ObtenerDatosMemoriaDeTrabajo *R) throw (Excepcion)
{
	// printf ("Comando ObtenerDatosMemoriaDeTrabajo ejecutado en P435\n");

	// Ejecutamos el m�todo de la superclase 'ImpresorFiscal16Bits'
	ImpresorFiscal16Bits::ObtenerDatosMemoriaDeTrabajo (R);

	// Completamos los par�metros correspondientes a los campos
	// de respuesta particulares que se agregan en este modelo
	// m�s alla de los comunes a todos los modelos
	if (R != NULL)
		R->CantidadNCCanceladas			= RespuestaInt (20, "Obtener Datos de Memoria de Trabajo");
}


//
// Abrir Caj�n de Dinero
//
void
ImpresorFiscalP435::AbrirCajon () throw(Excepcion)
{
	// printf ("Comando AbrirCajon ejecutado en P435\n");

	// Enviamos el comando fiscal y evaluamos los status
	EnviarComandoFiscal(OpCode(CMD_OPEN_DRAWER));
}


//
// Avanzar papel en estaci�n especificada
//
void
ImpresorFiscalP435::AvanzarPapel(TiposDePapel Papel, unsigned Lineas) throw (Excepcion)
{
	unsigned Comando;

	// printf ("Comando AvanzarPapel ejecutado en P435\n");
	
	switch (Papel)
	{
		case PAPEL_TICKET_Y_DIARIO:
			Comando = CMD_FEED_RECEIPT_JOURNAL;
			break;
		case PAPEL_DIARIO:
			Comando = CMD_FEED_JOURNAL;
			break;
		case PAPEL_TICKET:
		default:	
			Comando = CMD_FEED_RECEIPT;
			break;
	}

	// Verificamos que la cantidad de l�neas no supere
	// el m�ximo valor aceptado por el CF
	if (Lineas > 99)
		Lineas = 99;

	// Enviamos el comando fiscal y evaluamos los status
	EnviarComandoFiscal(OpCode(ImpresorFiscal::OpCodes(Comando)) + FS + Numero(Lineas));
}


// Capacidad de impresi�n de documentos en estaci�n Ticket
void
ImpresorFiscalP435::CapacidadDocumentoTicket (bool &SoportaFA, bool &SoportaNC)
{
	// printf ("Comando CapacidadDocumentoTicket ejecutado en P435\n");

	SoportaFA = true;
	SoportaNC = true;
}


// Capacidad de impresi�n en estaci�n Slip / Ticket
void
ImpresorFiscalP435::CapacidadEstacion (bool &SoportaTicket, bool &SoportaSlip)
{
	// printf ("Comando CapacidadEstacion ejecutado en P435\n");

	SoportaTicket = true;
	SoportaSlip = true;
}


// Capacidad de manejo y soporte de Caj�n de Dinero
void
ImpresorFiscalP435::CapacidadCajon (bool &SoportaCajon)
{
	// printf ("Comando CapacidadCajon ejecutado en P435\n");

	SoportaCajon = true;
}


// ############################################################ //
//																//
//			M E T O D O S   D E    C O N F I G U R A C I O N	//
//																//
// ############################################################ //

//
// Obtener Configuracion del Controlador
//
void
ImpresorFiscalP435::ObtenerConfiguracion (RTA_ObtenerConfiguracion *R) throw (Excepcion)
{
	// printf ("Comando ObtenerConfiguracion ejecutado en P435\n");

	// Ejecutamos el m�todo de la superclase 'ImpresorFiscal16Bits'
	ImpresorFiscal16Bits::ObtenerConfiguracion (R);

	// Completamos los par�metros correspondientes a los campos
	// de respuesta particulares que se agregan en este modelo
	// m�s alla de los comunes a todos los modelos
	if (R != NULL)
	{
		R->AltoHoja						= static_cast<ImpresorFiscal::TiposDeAltoHoja>(RespuestaString (13, "Obtener Configuracion del Controlador") [0]);
		R->AnchoHoja					= static_cast<ImpresorFiscal::TiposDeAnchoHoja>(RespuestaString (14, "Obtener Configuracion del Controlador") [0]);
		R->EstacionImpresionReportesXZ	= static_cast<ImpresorFiscal::TiposDeEstacion>(RespuestaString (15, "Obtener Configuracion del Controlador") [0]);
		R->ModoImpresion				= static_cast<ImpresorFiscal::TiposDeModoImpresion>(RespuestaString (16, "Obtener Configuracion del Controlador") [0]);
	}
}


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
ImpresorFiscalP435::AbrirDF (DocumentosFiscales Tipo) throw (Excepcion)
{
	// printf ("Comando AbrirDF ejecutado en P435\n");

	// Verificamos si el Tipo de DF es v�lido para este modelo
	if ( !(	Tipo == FACTURA_A		     ||
            Tipo == FACTURA_B		     ||
			Tipo == NOTA_DEBITO_A	     ||
			Tipo == NOTA_DEBITO_B	     ||
			Tipo == RECIBO_A		     ||
			Tipo == RECIBO_B             ||
            Tipo == TICKET_FACTURA_A     ||
            Tipo == TICKET_FACTURA_B     ||
			Tipo == TICKET_NOTA_DEBITO_A ||
			Tipo == TICKET_NOTA_DEBITO_B ||
            Tipo == TICKET_C ) )
		throw Excepcion(Excepcion::IMPRESOR_FISCAL_ERROR_NO_IMPLEMENTADO, "Abrir DF");

	// Ejecutamos el m�todo de la SuperClase 'ImpresorFiscal16Bits'
	ImpresorFiscal16Bits::AbrirDF (Tipo);
}


//
// Especificar Monto de IVA No Inscripto
//
void
ImpresorFiscalP435::EspecificarIVANoInscripto (double /* Monto */) throw (Excepcion)
{
	// printf ("Comando EspecificarIVANoInscripto ejecutado en P435\n");

	throw Excepcion(Excepcion::IMPRESOR_FISCAL_ERROR_NO_IMPLEMENTADO, "Especificar Monto de IVA No Inscripto");
}


//
// Cerrar DF
//
unsigned
ImpresorFiscalP435::CerrarDF (unsigned Copias) throw (Excepcion)
{
	// printf ("Comando CerrarDF ejecutado en P435\n");

	// Ejecutamos el m�todo de la superclase 'ImpresorFiscal16Bits'
	unsigned NumeroDFRecienEmitido = ImpresorFiscal16Bits::CerrarDF (Copias);

	// Recuperamos el N�mero de Hojas Numeradas del DF
	// reci�n emitido de la respuesta.
	NumberOfPages = RespuestaInt(3, "Cerrar DF");

	// Retornamos el N�mero de DF reci�n emitido
	return NumeroDFRecienEmitido;
}


// ############################################################ //
//																//
//			M E T O D O S   D E    D N F						//
//																//
// ############################################################ //



// ############################################################ //
//																//
//			M E T O D O S   D E    D N F H						//
//																//
// ############################################################ //

//
// Cerrar DNFH
//
unsigned
ImpresorFiscalP435::CerrarDNFH (unsigned Copias) throw (Excepcion)
{
	// printf ("Comando CerrarDNFH ejecutado en P435\n");

	// Ejecutamos el m�todo de la superclase 'ImpresorFiscal16Bits'
	unsigned NumeroDNFHRecienEmitido = ImpresorFiscal16Bits::CerrarDNFH (Copias);

	// Recuperamos el N�mero de Hojas Numeradas del DNFH
	// reci�n emitido de la respuesta.
	NumberOfPages = RespuestaInt(3, "Cerrar DNFH");

	// Retornamos el N�mero de DNFH reci�n emitido
	return NumeroDNFHRecienEmitido;
}


