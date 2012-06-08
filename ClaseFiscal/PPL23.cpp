#include "PPL23.h"



// ############################################################ //
//																//
//			M E T O D O S      L O C A L E S					//
//																//
// ############################################################ //

//
// Constructor
//
ImpresorFiscalPPL23::ImpresorFiscalPPL23 ()
{
	// Inicializaci�n de variables de uso general

	// Inicializaci�n de variables de tama�o de campos
	PrintNonFiscalTextTicketSize	= 120;	// Por seguridad !!!
	PrintFiscalTextTicketSize		= 50;	// Por seguridad !!!
	PrintItemTextTicketSize			= 50;	// Por seguridad !!!
	RemitoCantDecimals				= 10;
	ReciboTextTicketSize			= 106;	// Por seguridad !!!
}


//
// Establecer Modalidad Resumen IVA
//
void
ImpresorFiscalPPL23::EstablecerModalidadResumenIVA (TiposDeResumenIVA Modo)
{
	ModalidadResumenIVA = Modo;
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
ImpresorFiscalPPL23::ObtenerDatosMemoriaDeTrabajo (RTA_ObtenerDatosMemoriaDeTrabajo *R) throw (Excepcion)
{
    // printf ("Comando ObtenerDatosMemoriaDeTrabajo ejecutado en PPL23\n");

	// Ejecutamos el m�todo de la superclase 'ImpresorFiscal16Bits'
	ImpresorFiscal16Bits::ObtenerDatosMemoriaDeTrabajo (R);

	// Completamos los par�metros correspondientes a los campos
	// de respuesta particulares que se agregan en este modelo
	// m�s alla de los comunes a todos los modelos
	if (R != NULL)
	{
		R->CantidadDFBCEmitidos				= RespuestaInt (21, "Obtener Datos de Memoria de Trabajo");
		R->CantidadDFAEmitidos				= RespuestaInt (22, "Obtener Datos de Memoria de Trabajo");
		R->CantidadNCBCEmitidas				= RespuestaInt (23, "Obtener Datos de Memoria de Trabajo");
		R->CantidadNCAEmitidas				= RespuestaInt (24, "Obtener Datos de Memoria de Trabajo");
	}
}


//
// Estado Interno del Impresor
//
void
ImpresorFiscalPPL23::EstadoInternoImpresor (RTA_EstadoInternoImpresor *R) throw (Excepcion)
{
	// printf ("Comando EstadoInternoImpresor ejecutado en ImpresorFiscalPPL23\n");

	// Enviamos el comando fiscal y evaluamos los status
	EnviarComandoFiscal(OpCode(CMD_STATUS_PJL));

	// Completamos los par�metros correspondientes a los campos de respuesta
	if (R != NULL)
	{
		R->Estado							= RespuestaInt (2, "Estado Interno del Impresor");
		R->Descripcion						= RespuestaString (3, "Estado Interno del Impresor");
	}
}


//
// Establecer Interlineado de Impresi�n
//
void
ImpresorFiscalPPL23::EstablecerInterlineado (const unsigned LineasPorPulgada) throw (Excepcion)
{
	// printf ("Comando EstablecerInterlineado ejecutado en ImpresorFiscalPPL23\n");

	// Enviamos el comando fiscal y evaluamos los status
	EnviarComandoFiscal(OpCode(CMD_SET_CONFIG_PAGE) + FS + "LP:" + Numero (LineasPorPulgada));
}


//
// Obtener Interlineado de Impresi�n
//
unsigned
ImpresorFiscalPPL23::ObtenerInterlineado () throw (Excepcion)
{
	// printf ("Comando ObtenerInterlineado ejecutado en ImpresorFiscalPPL23\n");

	// Enviamos el comando fiscal y evaluamos los status
	EnviarComandoFiscal(OpCode(CMD_GET_CONFIG_PAGE));

	// Retornamos la informaci�n obtenida de la respuesta

	// Buscamos en el campo 2 de la respuesta la cadena 'LP:'.
	// Si la encontramos, retornamos el n�mero que la sucede.
	std::string CfgPageStr = RespuestaString(2, "Obtener Interlineado de Impresi�n");
	unsigned i = CfgPageStr.find ("LP:");

	if ( i == CfgPageStr.npos )
		throw Excepcion(Excepcion::IMPRESOR_FISCAL_ERROR_CAMPO_NO_ENCONTRADO, "Obtener Interlineado de Impresi�n");

	return atoi (CfgPageStr.substr (i+3).c_str());
}


//
// Apagar el Indicador del Tambor del Impresor
//
void
ImpresorFiscalPPL23::ApagarIndicadorDeTambor () throw (Excepcion)
{
	// printf ("Comando ApagarIndicadorDeTambor ejecutado en ImpresorFiscalPPL23\n");

	// Enviamos el comando fiscal y evaluamos los status
	EnviarComandoFiscal(OpCode(CMD_SET_CONFIG_PAGE) + FS + "RP:1");
}


// ############################################################ //
//																//
//			M E T O D O S   D E    C O N F I G U R A C I O N	//
//																//
// ############################################################ //

//
// Borrar L�neas de Nombre de Fantas�a, Encabezado y Cola
//
void
ImpresorFiscalPPL23::BorrarFantasiaEncabezadoCola (bool BorrarFantasia, bool BorrarEncabezado, bool BorrarCola) throw (Excepcion)
{
	// printf ("Comando BorrarFantasiaEncabezadoCola ejecutado en PPL23\n");

	if ( BorrarFantasia )
		EnviarComandoFiscal(OpCode(CMD_SET_FANTASY_NAME) + FS + "0" + FS + ERASE_LINE);

	if ( BorrarEncabezado )
	{
		if ( BorrarCola )
			EnviarComandoFiscal(OpCode(CMD_SET_HEADER_TRAILER) + FS + "0" + FS + ERASE_LINE);
		else
			EnviarComandoFiscal(OpCode(CMD_SET_HEADER_TRAILER) + FS + "-1" + FS + ERASE_LINE);
	}
	else
		if ( BorrarCola )
			EnviarComandoFiscal(OpCode(CMD_SET_HEADER_TRAILER) + FS + "-2" + FS + ERASE_LINE);
}


//
// Borrar Informaci�n L�nea de Remito / Comprobante Original
//
void
ImpresorFiscalPPL23::BorrarInformacionRemitoComprobanteOriginal () throw (Excepcion)
{
	// printf ("Comando BorrarInformacionRemitoComprobanteOriginal ejecutado en PPL23\n");

	// Enviamos el comando fiscal y evaluamos los status
	EnviarComandoFiscal(OpCode(CMD_SET_EMBARK_NUMBER) + FS + "0" + FS + ERASE_LINE);
}


//
// Obtener Configuracion del Controlador
//
void
ImpresorFiscalPPL23::ObtenerConfiguracion (RTA_ObtenerConfiguracion *R) throw (Excepcion)
{
	// printf ("Comando ObtenerConfiguracion ejecutado en PPL23\n");

	// Ejecutamos el m�todo de la superclase 'ImpresorFiscal16Bits'
	ImpresorFiscal16Bits::ObtenerConfiguracion (R);

	// Completamos los par�metros correspondientes a los campos
	// de respuesta particulares que se agregan en este modelo
	if (R != NULL)
	{
		R->AltoHoja						= static_cast<ImpresorFiscal::TiposDeAltoHoja>(RespuestaString (13, "Obtener Configuracion del Controlador") [0]);
		R->ChequeoDesbordeCompleto		= ((RespuestaString (17, "Obtener Configuracion del Controlador") [0]) == 'P');
	}
}


// ############################################################ //
//																//
//			M E T O D O S   D E    R E P O R T E S				//
//																//
// ############################################################ //

//
// Reporte Z
//
void
ImpresorFiscalPPL23::ReporteZ (RTA_ReporteZX *R) throw (Excepcion)
{
	// printf ("Comando ReporteZ ejecutado en PPL23\n");

	// Ejecutamos el m�todo de la superclase 'ImpresorFiscal16Bits'
	ImpresorFiscal16Bits::ReporteZ (R);

	// Completamos los par�metros correspondientes a los campos de
	// respuesta particulares que se agregan en este modelo
	if (R != NULL)
	{
		R->CantidadNCCanceladas				= RespuestaInt (23, "Reporte Z");
		R->CantidadDFBCEmitidos				= RespuestaInt (24, "Reporte Z");
		R->CantidadDFAEmitidos				= RespuestaInt (25, "Reporte Z");
		R->CantidadNCBCEmitidas				= RespuestaInt (26, "Reporte Z");
		R->CantidadNCAEmitidas				= RespuestaInt (27, "Reporte Z");
	}
}


//
// Reporte X
//
void
ImpresorFiscalPPL23::ReporteX (RTA_ReporteZX *R) throw (Excepcion)
{
	// printf ("Comando ReporteX ejecutado en PPL23\n");

	// Ejecutamos el m�todo de la superclase 'ImpresorFiscal16Bits'
	ImpresorFiscal16Bits::ReporteX (R);

	// Completamos los par�metros correspondientes a los campos de
	// respuesta particulares que se agregan en este modelo
	if (R != NULL)
	{
		R->CantidadNCCanceladas				= RespuestaInt (23, "Reporte Z");
		R->CantidadDFBCEmitidos				= RespuestaInt (24, "Reporte Z");
		R->CantidadDFAEmitidos				= RespuestaInt (25, "Reporte Z");
		R->CantidadNCBCEmitidas				= RespuestaInt (26, "Reporte Z");
		R->CantidadNCAEmitidas				= RespuestaInt (27, "Reporte Z");
	}
}


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
ImpresorFiscalPPL23::AbrirDF (DocumentosFiscales Tipo) throw (Excepcion)
{
    // printf ("Comando AbrirDF ejecutado en PPL23\n");

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
// Especificar Monto de IVA No Inscripto
//
void
ImpresorFiscalPPL23::EspecificarIVANoInscripto (double /* Monto */) throw (Excepcion)
{
    // printf ("Comando EspecificarIVANoInscripto ejecutado en PPL23\n");

	throw Excepcion(Excepcion::IMPRESOR_FISCAL_ERROR_NO_IMPLEMENTADO, "Especificar Monto de IVA No Inscripto");
}


//
// Devolver un pago previamente almacenado
//
double
ImpresorFiscalPPL23::DevolverPago (
						const std::string &Texto,
						double Monto,
						const std::string &DescripcionAdicional) throw (Excepcion)
{
	// printf ("Comando DevolverPago ejecutado en PPL23\n");

	assert(TotalTenderTextSize != 0);

	// Enviamos el comando fiscal y evaluamos los status

	EnviarComandoFiscal(OpCode(CMD_TOTAL_TENDER) + FS +
						Cadena (Texto, TotalTenderTextSize) + FS +
						Numero (Monto, 2) + FS + "R" + FS +
						Caracter (ModoDisplay));

	// Retornamos el Vuelto o Monto por Pagar
	return RespuestaDouble (2, "Devolver Pago");
}


//
// Cerrar DF
//
unsigned
ImpresorFiscalPPL23::CerrarDF (unsigned Copias) throw (Excepcion)
{
    // printf ("Comando CerrarDF ejecutado en PPL23\n");

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

//
// Abrir DNF
// Env�a el comando de Apertura de DNF en la estaci�n indicada
//
void
ImpresorFiscalPPL23::AbrirDNF (TiposDeEstacion EstacionDeImpresion) throw (Excepcion)
{
    // printf ("Comando AbrirDNF ejecutado en PPL23\n");

	// Ejecutamos el m�todo de la SuperClase 'ImpresorFiscal'.
	ImpresorFiscal::AbrirDNF (ESTACION_SLIP);
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
ImpresorFiscalPPL23::AbrirDNFH (DocumentosNoFiscalesHomologados Tipo, const std::string &Nro) throw (Excepcion)
{
    // printf ("Comando AbrirDNFH ejecutado en PPL23\n");

	// Verificamos si el Tipo de DNFH es v�lido para este modelo
	if ( !(	Tipo == NOTA_CREDITO_A	         ||
			Tipo == NOTA_CREDITO_B	         ||
			Tipo == REMITO			         ||
			Tipo == RECIBO_X		         ||
			Tipo == ORDEN_SALIDA	         ||
			Tipo == RESUMEN_CUENTA	         ||
			Tipo == CARGO_HABITACION         ||
			Tipo == COTIZACION               ||
            Tipo == CLAUSULA_CREDITO         ||
            Tipo == CLAUSULA_SEGURO          ||
            Tipo == PAGARE                   ||
            Tipo == POLIZA_SEGURO            ||
            Tipo == RECORDATORIO             ||
            Tipo == SOLICITUD_CREDITO        ||
            Tipo == COMUNICACION_CLIENTE	 ||
            Tipo == OFRECIMIENTO_CREDITO	 ||
            Tipo == OFRECIMIENTO_TARJETA	 ||
            Tipo == MINUTA_CREDITO			 ||
            Tipo == OFRECIMIENTO_PASAPORTE	 ||
            Tipo == RENOVACION_CREDITO		 ||
            Tipo == ADELANTO_REMUNERACION	 ||
            Tipo == SOLICITUD_TARJETA_DEBITO ||
            Tipo == SOLICITUD_CLAVE_TARJETA	 ||
            Tipo == RESCATE_MERCADERIA		 ||
            Tipo == INGRESO_EGRESO_SUCURSAL	) )
		throw Excepcion(Excepcion::IMPRESOR_FISCAL_ERROR_NO_IMPLEMENTADO, "Abrir DNFH");

	// Ejecutamos el m�todo de la SuperClase 'ImpresorFiscal16Bits'
	ImpresorFiscal16Bits::AbrirDNFH (Tipo, Nro);
}


//
// Cerrar DNFH
//
unsigned
ImpresorFiscalPPL23::CerrarDNFH (unsigned Copias) throw (Excepcion)
{
    // printf ("Comando CerrarDNFH ejecutado en PPL23\n");

	// Ejecutamos el m�todo de la superclase 'ImpresorFiscal16Bits'
	unsigned NumeroDNFHRecienEmitido = ImpresorFiscal16Bits::CerrarDNFH (Copias);

	// Recuperamos el N�mero de Hojas Numeradas del DNFH
	// reci�n emitido de la respuesta.
	NumberOfPages = RespuestaInt(3, "Cerrar DNFH");

	// Retornamos el N�mero de DNFH reci�n emitido
	return NumeroDNFHRecienEmitido;
}


