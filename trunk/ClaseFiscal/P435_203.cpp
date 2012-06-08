#include "P435_203.h"


std::string ImpresorFiscalP435_203::Version ("HASAR SMH/P-435F V: 02.03" /* " - 17/10/2008" */);


// ############################################################ //
//																//
//			M E T O D O S      L O C A L E S					//
//																//
// ############################################################ //

//
// Constructor
//
ImpresorFiscalP435_203::ImpresorFiscalP435_203 ()
{
	// Inicialización de variables de uso general

	// Inicialización de variables de tamaño de campos

}


//
// Obtener la Descripción del Modelo Seleccionado
//
std::string
ImpresorFiscalP435_203::DescripcionModelo () const
{
	return 	(Version);
}


//
// Establecer Modalidad Resumen IVA
//
void
ImpresorFiscalP435_203::EstablecerModalidadResumenIVA (TiposDeResumenIVA Modo)
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
ImpresorFiscalP435_203::ObtenerDatosMemoriaDeTrabajo (RTA_ObtenerDatosMemoriaDeTrabajo *R) throw (Excepcion)
{
    // printf ("Comando ObtenerDatosMemoriaDeTrabajo ejecutado en P435_203\n");

	// Ejecutamos el método de la superclase 'ImpresorFiscalP435'
	ImpresorFiscalP435::ObtenerDatosMemoriaDeTrabajo (R);

	// Completamos los parámetros correspondientes a los campos
	// de respuesta particulares que se agregan en este modelo
	// más alla de los comunes a todos los modelos
	if (R != NULL)
	{
		R->CantidadDFBCEmitidos				= RespuestaInt (21, "Obtener Datos de Memoria de Trabajo");
		R->CantidadDFAEmitidos				= RespuestaInt (22, "Obtener Datos de Memoria de Trabajo");
		R->CantidadNCBCEmitidas				= RespuestaInt (23, "Obtener Datos de Memoria de Trabajo");
		R->CantidadNCAEmitidas				= RespuestaInt (24, "Obtener Datos de Memoria de Trabajo");
	}
}


//
// Cargar Datos del Cliente
//
void
ImpresorFiscalP435_203::DatosCliente (
						const std::string &RazonSocial,
						const std::string &NroDocumento,
						TiposDeDocumentoCliente TipoDocumento,
						TiposDeResponsabilidadesCliente ResponsabilidadIVA,
						const std::string &Direccion) throw(Excepcion)
{
	// printf ("Comando DatosCliente ejecutado en P435_203\n");

	assert(RazonSocialSize != 0);
	assert(DireccionSize != 0);

	// Enviamos el comando fiscal y evaluamos los status

	// Verificamos la existencia del parámetro dirección
	// (si no se recibe la dirección, no se incluye el campo en el comando)
	if (Direccion.length() == 0)
		EnviarComandoFiscal(OpCode(CMD_SET_CUSTOMER_DATA) + FS +
							Cadena (RazonSocial, RazonSocialSize) + FS +
							Cadena (NroDocumento, 11) + FS +
							Caracter (ResponsabilidadIVA) + FS +
							Caracter (TipoDocumento));
	else
		EnviarComandoFiscal(OpCode(CMD_SET_CUSTOMER_DATA) + FS +
							Cadena (RazonSocial, RazonSocialSize) + FS +
							Cadena (NroDocumento, 11) + FS +
							Caracter (ResponsabilidadIVA) + FS +
							Caracter (TipoDocumento) + FS +
							Cadena (Direccion, DireccionSize));
}


// ############################################################ //
//																//
//			M E T O D O S   D E    C O N F I G U R A C I O N	//
//																//
// ############################################################ //

//
// Borrar Líneas de Nombre de Fantasía, Encabezado y Cola
//
void
ImpresorFiscalP435_203::BorrarFantasiaEncabezadoCola (bool BorrarFantasia, bool BorrarEncabezado, bool BorrarCola) throw (Excepcion)
{
	// printf ("Comando BorrarFantasiaEncabezadoCola ejecutado en P435_203\n");

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
// Borrar Información Línea de Remito / Comprobante Original
//
void
ImpresorFiscalP435_203::BorrarInformacionRemitoComprobanteOriginal () throw (Excepcion)
{
	// printf ("Comando BorrarInformacionRemitoComprobanteOriginal ejecutado en P435_203\n");

	// Enviamos el comando fiscal y evaluamos los status
	EnviarComandoFiscal(OpCode(CMD_SET_EMBARK_NUMBER) + FS + "0" + FS + ERASE_LINE);
}


//
// Obtener Configuracion del Controlador
//
void
ImpresorFiscalP435_203::ObtenerConfiguracion (RTA_ObtenerConfiguracion *R) throw (Excepcion)
{
	// printf ("Comando ObtenerConfiguracion ejecutado en P435_203\n");

	// Ejecutamos el método de la superclase 'ImpresorFiscalP435'
	ImpresorFiscalP435::ObtenerConfiguracion (R);

	// Completamos los parámetros correspondientes a los campos
	// de respuesta particulares que se agregan en este modelo
	if (R != NULL)
	{
		R->ChequeoDesbordeCompleto		= ((RespuestaString (17, "Obtener Configuracion del Controlador") [0]) == 'P');
		R->ChequeoTapaAbierta			= ((RespuestaString (18, "Obtener Configuracion del Controlador") [0]) == 'P');
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
ImpresorFiscalP435_203::ReporteZ (RTA_ReporteZX *R) throw (Excepcion)
{
	// printf ("Comando ReporteZ ejecutado en P435_203\n");

	// Ejecutamos el método de la superclase 'ImpresorFiscal16Bits'
	ImpresorFiscal16Bits::ReporteZ (R);

	// Completamos los parámetros correspondientes a los campos de
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
ImpresorFiscalP435_203::ReporteX (RTA_ReporteZX *R) throw (Excepcion)
{
	// printf ("Comando ReporteX ejecutado en P435_203\n");

	// Ejecutamos el método de la superclase 'ImpresorFiscal16Bits'
	ImpresorFiscal16Bits::ReporteX (R);

	// Completamos los parámetros correspondientes a los campos de
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
// Devolver un pago previamente almacenado
//
double
ImpresorFiscalP435_203::DevolverPago (
						const std::string &Texto,
						double Monto,
						const std::string &DescripcionAdicional) throw (Excepcion)
{
	// printf ("Comando DevolverPago ejecutado en P435_203\n");

	assert(TotalTenderTextSize != 0);

	// Enviamos el comando fiscal y evaluamos los status

	EnviarComandoFiscal(OpCode(CMD_TOTAL_TENDER) + FS +
						Cadena (Texto, TotalTenderTextSize) + FS +
						Numero (Monto, 2) + FS + "R" + FS +
						Caracter (ModoDisplay));

	// Retornamos el Vuelto o Monto por Pagar
	return RespuestaDouble (2, "Devolver Pago");
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
// Abrir DNFH
// Envía el comando de Apertura de DNFH en la estación indicada
//
void
ImpresorFiscalP435_203::AbrirDNFH (DocumentosNoFiscalesHomologados Tipo, const std::string &Nro) throw (Excepcion)
{
	// printf ("Comando AbrirDNFH ejecutado en P435_203\n");

	// Verificamos si el Tipo de DNFH es válido para este modelo
	if ( !(	Tipo == NOTA_CREDITO_A	         		||
			Tipo == NOTA_CREDITO_B	         		||
			Tipo == REMITO			         		||
			Tipo == RECIBO_X		         		||
			Tipo == ORDEN_SALIDA	         		||
			Tipo == RESUMEN_CUENTA	         		||
			Tipo == CARGO_HABITACION         		||
			Tipo == COTIZACION               		||
            Tipo == CLAUSULA_CREDITO         		||
            Tipo == CLAUSULA_SEGURO          		||
            Tipo == PAGARE                   		||
            Tipo == POLIZA_SEGURO            		||
            Tipo == RECORDATORIO             		||
            Tipo == SOLICITUD_CREDITO 		 		||
            Tipo == COMUNICACION_CLIENTE	 		||
            Tipo == OFRECIMIENTO_CREDITO	 		||
            Tipo == OFRECIMIENTO_TARJETA	 		||
            Tipo == MINUTA_CREDITO			 		||
            Tipo == OFRECIMIENTO_PASAPORTE	 		||
            Tipo == RENOVACION_CREDITO		 		||
            Tipo == ADELANTO_REMUNERACION	 		||
            Tipo == SOLICITUD_TARJETA_DEBITO 		||
            Tipo == SOLICITUD_CLAVE_TARJETA	 		||
            Tipo == RESCATE_MERCADERIA		 		||
            Tipo == INGRESO_EGRESO_SUCURSAL	 		||
			Tipo == TICKET_NOTA_CREDITO_A 	 		||
			Tipo == TICKET_NOTA_CREDITO_B 	 		||
		    Tipo == TICKET_RECIBO_X          		||
		    Tipo == TICKET_PAGARE         	 		||
			Tipo == TICKET_PRESUPUESTO    	 		||
			Tipo == TICKET_ENTREGA 	   		 		||
			Tipo == TICKET_ESTACIONAMIENTO   		||
			Tipo == TICKET_COBRO_SERVICIO 			||
			Tipo == TICKET_INGRESO_DINERO    		||
			Tipo == TICKET_RETIRO_DINERO 	   	 	||
			Tipo == TICKET_TALON_CAMBIO    	 		||
			Tipo == TICKET_TALON_REPARTO 	   	 	||
			Tipo == TICKET_TALON_REGALO    	 		||
			Tipo == TICKET_TALON_CUENTA_CORRIENTE 	||
			Tipo == TICKET_AVISO_OPERACION_CREDITO 	||
			Tipo == TICKET_CUPON_PROMOCION 	 		||
			Tipo == TICKET_USO_INTERNO_FARMACIA	) )
		throw Excepcion(Excepcion::IMPRESOR_FISCAL_ERROR_NO_IMPLEMENTADO, "Abrir DNFH");

	// Ejecutamos el método de la SuperClase 'ImpresorFiscal16Bits'
	ImpresorFiscal16Bits::AbrirDNFH (Tipo, Nro);
}


//
// Imprimir Información en DNFH
//
void
ImpresorFiscalP435_203::ImprimirInfoEnDNFH (const unsigned IDCampo,
										  const std::string &Texto,
										  double Cantidad) throw (Excepcion)
{
	// printf ("Comando ImprimirInfoEnDNFH ejecutado en P435_203\n");

	assert(PrintDNFHTextSize != 0);

	// Enviamos el comando fiscal y evaluamos los status
	if ( Cantidad == 0.0 )
		EnviarComandoFiscal(OpCode(CMD_PRINT_INFO_DNFH) + FS +
							Numero (IDCampo) + FS +
							Cadena (Texto, PrintDNFHTextSize) + FS +
							Caracter (ModoDisplay));
	else
		EnviarComandoFiscal(OpCode(CMD_PRINT_INFO_DNFH) + FS +
							Numero (IDCampo) + FS +
							Cadena (Texto, PrintDNFHTextSize) + FS +
							Caracter (ModoDisplay) + FS +
							Numero (Cantidad, 10));
}


//
// Imprimir Firma en Cierre de DNFH
//
void
ImpresorFiscalP435_203::ImprimirFirmaEnCierreDNFH (bool ImprimeFirma) throw (Excepcion)
{
	// printf ("Comando ImprimirFirmaEnCierreDNFH ejecutado en P435_203\n");

	EnviarComandoFiscal(OpCode(CMD_PRINT_SIGN_DNFH) + FS + (ImprimeFirma ? "P" : "N"));
}


//
// Imprimir Voucher de Tarjeta
//
// Nota: esta método -en donde la fecha de vencimiento pasa a ser un string-
//		 solo es válido para este modelo y posteriores.
//
void
ImpresorFiscalP435_203::ImprimirVoucher (const std::string &NombreCliente,
									const std::string &NombreTarjeta,
									TiposDeVouchers TipoVoucher,
									const std::string &NumeroDeTarjeta,
									const std::string &FechaDeVencimiento,
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
									unsigned Copias,
									const std::string &Vendedor,
									TiposDeEstacion Estacion) throw (Excepcion)
{
	// printf ("Comando ImprimirVoucher ejecutado en P435_203\n");

	// Enviamos el comando fiscal 'SetVoucher1' y evaluamos los status
	EnviarComandoFiscal(OpCode(CMD_SET_VOUCHER_DATA_1) + FS +
						Cadena (NombreCliente, VouchCustNameSize) + FS +
						Cadena (NombreTarjeta, VouchCardNameSize) + FS +
						Caracter (TipoVoucher) + FS +
						Cadena (NumeroDeTarjeta, VouchCardNumSize) + FS +
						FechaDeVencimiento.substr(0, 4) + FS +
						Caracter (TipoTarjeta) + FS +
						Numero (Cuotas));

	// Enviamos el comando fiscal 'SetVoucher2' y evaluamos los status
	EnviarComandoFiscal(OpCode(CMD_SET_VOUCHER_DATA_2) + FS +
						Cadena (CodigoDeComercio, VouchComCodeSize) + FS +
						Numero (NumeroDeTerminal) + FS +
						Numero (NumeroDeLote) + FS +
						Numero (NumeroCupon) + FS +
						Caracter (TipoIngreso) + FS +
						Caracter (TipoOperacion) + FS +
						Numero (NumeroAutorizacion) + FS +
						Cadena (Monto, VouchImportSize) + FS + 
						Cadena (NumeroComprobanteAsociado, VouchCompNumSize) + FS +
						Cadena (Vendedor, VouchVendNameSize));

	// Enviamos el comando fiscal 'PrintVoucher' y evaluamos los status
	EnviarComandoFiscal(OpCode(CMD_PRINT_VOUCHER) + FS +
						Numero (Copias) + FS +
						Caracter (Estacion));
}
