#include "P435_100.h"


std::string ImpresorFiscalP435_100::Version ("HASAR SMH/P-435F V: 01.00" /* " - 22/11/2004" */);


// ############################################################ //
//																//
//			M E T O D O S      L O C A L E S					//
//																//
// ############################################################ //

//
// Constructor
//
ImpresorFiscalP435_100::ImpresorFiscalP435_100 ()
{
	// Inicialización de variables de uso general

	// Inicialización de variables de tamaño de campos

}


//
// Obtener la Descripción del Modelo Seleccionado
//
std::string
ImpresorFiscalP435_100::DescripcionModelo () const
{
	return 	(Version);
}


// ############################################################ //
//																//
//			M E T O D O S   G E N E R A L E S					//
//																//
// ############################################################ //



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
ImpresorFiscalP435_100::AbrirDNFH (DocumentosNoFiscalesHomologados Tipo, const std::string &Nro) throw (Excepcion)
{
	// printf ("Comando AbrirDNFH ejecutado en P435_100\n");

	// Verificamos si el Tipo de DNFH es válido para este modelo
	if ( !(	Tipo == NOTA_CREDITO_A	         ||
			Tipo == NOTA_CREDITO_B	         ||
	        Tipo == TICKET_NOTA_CREDITO_A	 ||
			Tipo == TICKET_NOTA_CREDITO_B	 ||
			Tipo == REMITO			         ||
			Tipo == RECIBO_X		         ||
			Tipo == TICKET_RECIBO_X		     ||
			Tipo == ORDEN_SALIDA	         ||
			Tipo == RESUMEN_CUENTA	         ||
			Tipo == CARGO_HABITACION         ||
			Tipo == COTIZACION               ||
            Tipo == CLAUSULA_CREDITO         ||
            Tipo == CLAUSULA_SEGURO          ||
            Tipo == PAGARE                   ||
            Tipo == TICKET_PAGARE            ||
            Tipo == POLIZA_SEGURO            ||
            Tipo == RECORDATORIO             ||
            Tipo == SOLICITUD_CREDITO ) )
		throw Excepcion(Excepcion::IMPRESOR_FISCAL_ERROR_NO_IMPLEMENTADO, "Abrir DNFH");

	// Ejecutamos el método de la SuperClase 'ImpresorFiscal16Bits'
	ImpresorFiscal16Bits::AbrirDNFH (Tipo, Nro);
}



