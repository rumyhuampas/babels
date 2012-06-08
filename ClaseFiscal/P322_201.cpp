#include "P322_201.h"


std::string ImpresorFiscalP322_201::Version ("HASAR SMH/P-322F V: 02.01" /* " - 22/01/2004" */);


// ############################################################ //
//																//
//			M E T O D O S      L O C A L E S					//
//																//
// ############################################################ //

//
// Constructor
//
ImpresorFiscalP322_201::ImpresorFiscalP322_201 ()
{
	// Inicializaci�n de variables de uso general

	// Inicializaci�n de variables de tama�o de campos

}


//
// Obtener la Descripci�n del Modelo Seleccionado
//
std::string
ImpresorFiscalP322_201::DescripcionModelo () const
{
	return 	(Version);
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
ImpresorFiscalP322_201::ObtenerDatosMemoriaDeTrabajo (RTA_ObtenerDatosMemoriaDeTrabajo *R) throw (Excepcion)
{
	// printf ("Comando ObtenerDatosMemoriaDeTrabajo ejecutado en P322_201\n");

	// Ejecutamos el m�todo de la superclase 'ImpresorFiscal16Bits'
	ImpresorFiscal16Bits::ObtenerDatosMemoriaDeTrabajo (R);

	// Completamos los par�metros correspondientes a los campos
	// de respuesta particulares que se agregan en este modelo
	// m�s alla de los comunes a todos los modelos
	if (R != NULL)
		R->CantidadNCCanceladas			= RespuestaInt (20, "Obtener Datos de Memoria de Trabajo");
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
// Cerrar DF
//
unsigned
ImpresorFiscalP322_201::CerrarDF (unsigned Copias) throw (Excepcion)
{
	// printf ("Comando CerrarDF ejecutado en P322_201\n");

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
// Cerrar DNF
//
void
ImpresorFiscalP322_201::CerrarDNF (unsigned Copias) throw (Excepcion)
{
	// printf ("Comando CerrarDNF ejecutado en P322_201\n");

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
ImpresorFiscalP322_201::AbrirDNFH (DocumentosNoFiscalesHomologados Tipo, const std::string &Nro) throw (Excepcion)
{
	// printf ("Comando AbrirDNFH ejecutado en P322_201\n");

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
ImpresorFiscalP322_201::CerrarDNFH (unsigned Copias) throw (Excepcion)
{
	// printf ("Comando CerrarDNFH ejecutado en P322_201\n");

	// Ejecutamos el m�todo de la superclase 'ImpresorFiscal16Bits'
	unsigned NumeroDNFHRecienEmitido = ImpresorFiscal16Bits::CerrarDNFH (Copias);

	// Recuperamos el N�mero de Hojas Numeradas del DNFH
	// reci�n emitido de la respuesta.
	NumberOfPages = RespuestaInt(3, "Cerrar DNFH");

	// Retornamos el N�mero de DNFH reci�n emitido
	return NumeroDNFHRecienEmitido;
}


