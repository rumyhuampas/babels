#include "PPL8.h"
 


// ############################################################ //
//																//
//			M E T O D O S      L O C A L E S					//
//																//
// ############################################################ //

//
// Constructor
//
ImpresorFiscalPPL8::ImpresorFiscalPPL8 ()
{
	// Inicializaci�n de variables de uso general

	// Inicializaci�n de variables de tama�o de campos
	PrintNonFiscalTextTicketSize	= 120;	// Por seguridad !!!	
	PrintFiscalTextTicketSize		= 50;	// Por seguridad !!!
	PrintItemTextTicketSize			= 50;	// Por seguridad !!!
	ReciboTextTicketSize			= 106;	// Por seguridad !!!
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

//
// Abrir DF
// Env�a el comando de Apertura de DF en la estaci�n indicada
//
void
ImpresorFiscalPPL8::AbrirDF (DocumentosFiscales Tipo) throw (Excepcion)
{
	// printf ("Comando AbrirDF ejecutado en PPL8\n");

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
ImpresorFiscalPPL8::AbrirDNF (TiposDeEstacion EstacionDeImpresion) throw (Excepcion)
{
	// printf ("Comando AbrirDNF ejecutado en PPL8\n");

	// Ejecutamos el m�todo de la SuperClase 'ImpresorFiscal'.
	ImpresorFiscal::AbrirDNF (ESTACION_SLIP);
}


// ############################################################ //
//																//
//			M E T O D O S   D E    D N F H						//
//																//
// ############################################################ //


